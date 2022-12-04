package mc.craig.software.craftplus.common.entities;

import mc.craig.software.craftplus.util.ViewUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class QuantumLockedLifeform extends Monster implements Enemy {
    private static final EntityDataAccessor<Integer> TIME_VIEWED = SynchedEntityData.defineId(QuantumLockedLifeform.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_HOOKED = SynchedEntityData.defineId(QuantumLockedLifeform.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SHOULD_DROP_LOOT = SynchedEntityData.defineId(QuantumLockedLifeform.class, EntityDataSerializers.BOOLEAN);

    public long timeSincePlayedSound = 0;

    public QuantumLockedLifeform(Level worldIn, EntityType<? extends Monster> entityType) {
        super(entityType, worldIn);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createMonsterAttributes().
                add(Attributes.ATTACK_DAMAGE, 8D).
                add(Attributes.MAX_HEALTH, 20D).
                add(Attributes.KNOCKBACK_RESISTANCE, 1.0D).
                add(Attributes.MOVEMENT_SPEED, 0.8D).
                add(Attributes.ARMOR, 2.0D);
    }


    @Override
    public void tick() {
        super.tick();
        if (getSeenTime() == 0) {
            setNoAi(false);
        }
    }

    @Override
    public void aiStep() {
        if (!getMainHandItem().isEmpty()) {
            setPersistenceRequired();
        }

        super.aiStep();
        if (!level.isClientSide) {
            List<Player> players = level.getEntitiesOfClass(Player.class, getBoundingBox().inflate(120));
            players.removeIf(player -> player.isSpectator() || player.isInvisible() || player.isSleeping() || player.level != level);

            if (players.isEmpty()) {
                setSeenTime(0);
                setSpeed(0.5F);
                return;
            }

            Player targetPlayer = null;
            for (Player player : players) {
                if (ViewUtil.isInSight(player, this)) {
                    setSeenTime(getSeenTime() + 1);
                    invokeSeen(player);
                    return;
                }
                if (targetPlayer == null) {
                    targetPlayer = player;
                    setSeenTime(0);
                    setSpeed(0.5F);
                }
            }

            if (isSeen()) return;
            snapLookToPlayer(targetPlayer);
            moveTowards(targetPlayer);
        }
    }


    @Override
    public void makeStuckInBlock(BlockState state, @NotNull Vec3 motionMultiplierIn) {
        if (!state.is(Blocks.COBWEB)) {
            super.makeStuckInBlock(state, motionMultiplierIn);
        }
    }

    private void snapLookToPlayer(Player targetPlayer) {
        Vec3 vecPos = position();
        Vec3 vecPlayerPos = targetPlayer.position();
        float angle = (float) Math.toDegrees((float) Math.atan2(vecPos.z - vecPlayerPos.z, vecPos.x - vecPlayerPos.x));
        yHeadRot = yBodyRot = angle > 180 ? angle : angle + 90;
    }

    public void moveTowards(LivingEntity targetPlayer) {
        getNavigation().moveTo(targetPlayer, getSpeed());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(TIME_VIEWED, 0);
        getEntityData().define(IS_HOOKED, false);
        getEntityData().define(SHOULD_DROP_LOOT, true);
    }

    @Override
    protected boolean shouldDropLoot() {
        return getEntityData().get(SHOULD_DROP_LOOT);
    }

    public boolean isHooked() {
        return getEntityData().get(IS_HOOKED);
    }

    public void setHooked(boolean hooked) {
        getEntityData().set(IS_HOOKED, hooked);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
    }

    public void setDrops(boolean drops) {
        getEntityData().set(SHOULD_DROP_LOOT, drops);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean displayFireAnimation() {
        return false;
    }

    public long getTimeSincePlayedSound() {
        return timeSincePlayedSound;
    }

    public void setTimeSincePlayedSound(long timeSincePlayedSound) {
        this.timeSincePlayedSound = timeSincePlayedSound;
    }

    public boolean isSeen() {
        return getSeenTime() > 0;
    }

    public int getSeenTime() {
        return getEntityData().get(TIME_VIEWED);
    }

    public void setSeenTime(int time) {
        getEntityData().set(TIME_VIEWED, time);
    }

    @Override
    public void knockback(double strength, double x, double z) {

    }

    @Override
    protected @NotNull BodyRotationControl createBodyControl() {
        return new LockBodyRotation(this);
    }

    public void invokeSeen(Player player) {
        getNavigation().moveTo((Path) null, 0);
        setNoAi(true);
    }

    @Override
    protected boolean isImmobile() {
        return getSeenTime() > 0;
    }
}