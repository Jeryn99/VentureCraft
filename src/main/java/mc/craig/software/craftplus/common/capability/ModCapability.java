package mc.craig.software.craftplus.common.capability;

import mc.craig.software.craftplus.common.ModItems;
import mc.craig.software.craftplus.common.items.ParagliderItem;
import mc.craig.software.craftplus.networking.Network;
import mc.craig.software.craftplus.networking.packets.MessageSyncCap;
import mc.craig.software.craftplus.util.GliderUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nonnull;

public class ModCapability implements ICap {


    public static final Capability<ICap> CAPABILITY = CapabilityManager.get(new CapabilityToken<ICap>() {
    });
    private Player player;
    private boolean isClimbing = false;

    private boolean falling = false;

    private int stamina = 200;
    private int maxStamina = 200;

    public enum AnimationStates {
        FALLING, GLIDING, GLIDER_OPENING, BREATHING
    }

    public AnimationState glideAnimation = new AnimationState();
    public AnimationState fallingAnimation = new AnimationState();
    public AnimationState gliderOpeningAnimation = new AnimationState();
    public AnimationState breathing = new AnimationState();

    public ModCapability() {

    }

    public ModCapability(Player livingEntity) {
        this.player = livingEntity;
    }

    @Nonnull
    public static LazyOptional<ICap> get(LivingEntity player) {
        return player.getCapability(ModCapability.CAPABILITY, null);
    }

    @Override
    public void tick(LivingEntity livingEntity) {
        if (glideAndFallLogic(livingEntity)) return;

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

        if(!livingEntity.level.isClientSide){
            boolean prev = isFalling();
            setFalling(player.fallDistance > 1.1309066 && !GliderUtil.isGlidingWithActiveGlider(livingEntity));
            if(isFalling() != prev){
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

    @Override
    public boolean isClimbing() {
        return isClimbing;
    }

    @Override
    public void setClimbing(boolean climbing) {
        this.isClimbing = climbing;
    }

    @Override
    public boolean canClimb(LivingEntity livingEntity) {
        return getStamina() > 0 && player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.CLIMBING_GEAR.get();
    }

    @Override
    public void sync() {
        if (player != null && player.level.isClientSide) {
            throw new IllegalStateException("Don't sync client -> server");
        }

        CompoundTag nbt = serializeNBT();
        Network.INSTANCE.send(PacketDistributor.DIMENSION.with(() -> player.getCommandSenderWorld().dimension()), new MessageSyncCap(this.player.getUUID(), nbt));
    }

    @Override
    public boolean isFalling() {
        return falling;
    }

    @Override
    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    @Override
    public boolean isRecharging() {
        return !GliderUtil.isGlidingWithActiveGlider(player) && getStamina() < getMaxStamina() && !isFalling() && GliderUtil.isPlayerOnGroundOrWater(player) && !isClimbing();
    }

    @Override
    public AnimationState getAnimation(AnimationStates animationStates) {
        return switch (animationStates) {
            case FALLING -> fallingAnimation;
            case GLIDING -> glideAnimation;
            case GLIDER_OPENING -> gliderOpeningAnimation;
            case BREATHING -> breathing;
        };
    }

    @Override
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    @Override
    public int getStamina() {
        return stamina;
    }

    @Override
    public void setMaxStamina(int stamina) {
        this.maxStamina = stamina;
    }

    @Override
    public int getMaxStamina() {
        return maxStamina;
    }


    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putBoolean("isClimbing", isClimbing);
        compoundTag.putBoolean("isFalling", falling);
        compoundTag.putInt("stamina", stamina);
        compoundTag.putInt("maxStamina", maxStamina);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        isClimbing = nbt.getBoolean("isClimbing");
        falling = nbt.getBoolean("isFalling");
        stamina = nbt.getInt("stamina");
        maxStamina = nbt.getInt("maxStamina");
    }
}
