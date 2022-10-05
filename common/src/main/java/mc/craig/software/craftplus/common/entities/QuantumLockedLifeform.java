package mc.craig.software.craftplus.common.entities;

import mc.craig.software.craftplus.util.ViewUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class QuantumLockedLifeform extends Monster implements Enemy {

    private static final EntityDataAccessor<Integer> TIME_VIEWED = SynchedEntityData.defineId(QuantumLockedLifeform.class, EntityDataSerializers.INT);

    public QuantumLockedLifeform(Level worldIn, EntityType<? extends Monster> entityType) {
        super(entityType, worldIn);
    }



    @Override
    public void tick() {
        super.tick();
        if (getSeenTime() == 0 || level.isEmptyBlock(blockPosition().below())) {
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
            List<Player> players = level.getEntitiesOfClass(Player.class, getBoundingBox().inflate(300));
            players.removeIf(player -> player.isSpectator() || player.isInvisible() || player.isSleeping() || player.level != level);

            if (players.isEmpty()) {
                setSeenTime(0);
                setSpeed(0.5F);
            } else {
                Player targetPlayer = null;
                for (Player player : players) {
                    if (ViewUtil.isInSight(player, this) && isOnGround()) {
                        setSeenTime(getSeenTime() + 1);
                        invokeSeen(player);
                        return;
                    } else {
                        setSeenTime(0);
                        setSpeed(0.5F);
                    }
                    if (targetPlayer == null) {
                        targetPlayer = player;
                        setSpeed(0.5F);
                    }
                }

                if (isSeen()) return;
                snapLookToPlayer(targetPlayer);
                moveTowards(targetPlayer);
            }
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
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
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