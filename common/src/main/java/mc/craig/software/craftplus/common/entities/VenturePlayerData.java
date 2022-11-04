package mc.craig.software.craftplus.common.entities;

import dev.architectury.injectables.annotations.ExpectPlatform;
import mc.craig.software.craftplus.common.ModItems;
import mc.craig.software.craftplus.common.items.ParagliderItem;
import mc.craig.software.craftplus.networking.packets.MessageSyncCap;
import mc.craig.software.craftplus.networking.packets.SyncSkillPointsMessage;
import mc.craig.software.craftplus.util.GliderUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class VenturePlayerData {

    // TODO max xp per point might change, set to 50 for testing
    public static final int XP_PER_SKILL_POINT = 50;

    @NotNull
    private final Player player;
    private boolean isClimbing = false;

    private boolean falling = false;

    private int skillXP = 0;
    private int skillPoints = 0;
    private int stamina = 200;
    private int maxStamina = 200;

    public enum AnimationStates {
        FALLING, GLIDING, GLIDER_OPENING, BREATHING, RUNNING
    }

    public AnimationState glideAnimation = new AnimationState();
    public AnimationState fallingAnimation = new AnimationState();
    public AnimationState gliderOpeningAnimation = new AnimationState();
    public AnimationState breathing = new AnimationState();
    public AnimationState runningAnimation = new AnimationState();

    public VenturePlayerData(@NotNull Player player) {
        this.player = player;
    }

    @ExpectPlatform
    public static Optional<VenturePlayerData> get(LivingEntity player) {
        throw new AssertionError();
    }

    public void tick(LivingEntity livingEntity) {
        if (glideAndFallLogic(livingEntity)) return;

        if (!livingEntity.level.isClientSide()) {
            if (livingEntity.isOnGround()) {
                setClimbing(false);
            }
        }

        if (livingEntity.isSprinting()) {
            if (!runningAnimation.isStarted()) {
                runningAnimation.start(player.tickCount);
            }
        } else {
            runningAnimation.stop();
        }

        if (isRecharging()) {
            if (getStamina() < getMaxStamina()) {
                setStamina(getStamina() + 1);
            }
        }

        if (isClimbing()) {
            setStamina(getStamina() - 1);
        }

        float f = (float) this.player.getDeltaMovement().horizontalDistance();
        if (f < 0.01F) {
            if (!breathing.isStarted()) {
                breathing.start(player.tickCount);
            }
        } else {
            breathing.stop();
        }
    }

    private boolean glideAndFallLogic(LivingEntity livingEntity) {

        if (!livingEntity.level.isClientSide) {
            boolean prev = isFalling();
            setFalling(player.fallDistance > 1.1309066 && !GliderUtil.isGlidingWithActiveGlider(livingEntity));
            if (isFalling() != prev) {
                sync();
            }

            ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);
            strikePlayerLightning(livingEntity, chestItem);
        }

        if (GliderUtil.isGlidingWithActiveGlider(livingEntity)) {
            if (!glideAnimation.isStarted()) {
                glideAnimation.start(livingEntity.tickCount);
            }

            if (!gliderOpeningAnimation.isStarted()) {
                gliderOpeningAnimation.start(livingEntity.tickCount);
            }
            return true;
        } else {
            glideAnimation.stop();
            gliderOpeningAnimation.stop();
        }

        if (isFalling()) {
            if (!fallingAnimation.isStarted()) {
                fallingAnimation.start(livingEntity.tickCount);
            }
            return true;
        } else {
            fallingAnimation.stop();
        }
        return false;
    }

    private void strikePlayerLightning(LivingEntity livingEntity, ItemStack chestItem) {
        if (livingEntity.level.random.nextInt(10000) == 0 && GliderUtil.isGlidingWithActiveGlider(livingEntity) && livingEntity.level.canSeeSky(livingEntity.blockPosition()) && !ParagliderItem.hasBeenStruck(chestItem) && livingEntity.level.isRainingAt(livingEntity.blockPosition())) {
            LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(livingEntity.level);
            lightningbolt.moveTo(Vec3.atBottomCenterOf(livingEntity.blockPosition()));
            lightningbolt.setVisualOnly(false);
            livingEntity.level.addFreshEntity(lightningbolt);
        }
    }

    public boolean isClimbing() {
        return isClimbing;
    }

    public void setClimbing(boolean climbing) {
        this.isClimbing = climbing;
    }

    public boolean canClimb(LivingEntity livingEntity) {
        return getStamina() > 0 && player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.CLIMBING_GEAR.get();
    }

    public void sync() {
        if (this.player.level.isClientSide) {
            throw new IllegalStateException("Don't sync client -> server");
        }

        new MessageSyncCap(this.player.getId(), serializeNBT()).sendToTracking(this.player);
    }

    public void syncTo(ServerPlayer receiver) {
        if (this.player.level.isClientSide) {
            throw new IllegalStateException("Don't sync client -> server");
        }

        new MessageSyncCap(this.player.getId(), serializeNBT()).send(receiver);
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isRecharging() {
        return !GliderUtil.isGlidingWithActiveGlider(player) && getStamina() < getMaxStamina() && !isFalling() && GliderUtil.isPlayerOnGroundOrWater(player) && !isClimbing();
    }

    public AnimationState getAnimation(AnimationStates animationStates) {
        return switch (animationStates) {
            case FALLING -> fallingAnimation;
            case GLIDING -> glideAnimation;
            case GLIDER_OPENING -> gliderOpeningAnimation;
            case BREATHING -> breathing;
            case RUNNING -> runningAnimation;
        };
    }

    public void setSkillXP(int skillXP) {
        this.skillXP = Mth.clamp(skillXP, 0, 49);
    }

    public int getSkillXP() {
        return this.skillXP;
    }

    public void addSkillXP(int skillXP) {
        this.skillXP += skillXP;

        while (this.skillXP >= XP_PER_SKILL_POINT) {
            this.skillPoints++;
            this.skillXP -= XP_PER_SKILL_POINT;
        }

        while (this.skillXP <= -XP_PER_SKILL_POINT) {
            this.skillPoints--;
            this.skillXP += XP_PER_SKILL_POINT;
        }

        if (this.skillXP < 0) {
            this.skillPoints--;
            this.skillXP = XP_PER_SKILL_POINT - Mth.abs(this.skillXP);
        }

        this.skillPoints = Mth.clamp(this.skillPoints, 0, 99);

        if (this.player instanceof ServerPlayer serverPlayer) {
            new SyncSkillPointsMessage(this.skillXP, this.skillPoints).send(serverPlayer);
        }
    }

    public int getNeededXPForNextPoint() {
        return XP_PER_SKILL_POINT - this.skillXP;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = Mth.clamp(skillPoints, 0, 99);

        if (this.player instanceof ServerPlayer serverPlayer) {
            new SyncSkillPointsMessage(this.skillXP, this.skillPoints).send(serverPlayer);
        }
    }

    public int getSkillPoints() {
        return this.skillPoints;
    }

    public void addSkillPoints(int skillPoints) {
        this.setSkillPoints(this.getSkillPoints() + skillPoints);
    }

    public void setStamina(int stamina) {
        this.stamina = Mth.clamp(stamina, 0, maxStamina);
    }

    public int getStamina() {
        return stamina;
    }

    public void setMaxStamina(int stamina) {
        this.maxStamina = stamina;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putBoolean("IsClimbing", this.isClimbing);
        compoundTag.putBoolean("IsFalling", this.falling);
        compoundTag.putInt("SkillPoints", this.skillPoints);
        compoundTag.putInt("Stamina", this.stamina);
        compoundTag.putInt("MaxStamina", this.maxStamina);
        return compoundTag;
    }

    public void deserializeNBT(CompoundTag nbt) {
        this.isClimbing = nbt.getBoolean("IsClimbing");
        this.falling = nbt.getBoolean("IsFalling");
        this.stamina = nbt.getInt("Stamina");
        this.maxStamina = nbt.getInt("MaxStamina");
    }

}
