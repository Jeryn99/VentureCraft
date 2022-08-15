package mc.craig.software.craftplus.common.entities;

import mc.craig.software.craftplus.common.ModDamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class Stalker extends QuantumLockedLifeform {

    private static final EntityDataAccessor<String> POSE = SynchedEntityData.defineId(Stalker.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Boolean> IS_ALIVE = SynchedEntityData.defineId(Stalker.class, EntityDataSerializers.BOOLEAN);

    public Stalker(EntityType<? extends QuantumLockedLifeform> type, Level worldIn) {
        super(worldIn, type);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new ClimbOnTopOfPowderSnowGoal(this, this.level));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    protected boolean teleport() {
        if (!this.level.isClientSide() && this.isAlive()) {
            double d0 = this.getX() + (this.random.nextDouble() - 0.5D) * 64.0D;
            double d1 = this.getY() + (double) (this.random.nextInt(64) - 32);
            double d2 = this.getZ() + (this.random.nextDouble() - 0.5D) * 64.0D;
            return this.teleport(d0, d1, d2);
        } else {
            return false;
        }
    }

    boolean teleportTowards(Entity p_32501_) {
        Vec3 vec3 = new Vec3(this.getX() - p_32501_.getX(), this.getY(0.5D) - p_32501_.getEyeY(), this.getZ() - p_32501_.getZ());
        vec3 = vec3.normalize();
        double d0 = 16.0D;
        double d1 = this.getX() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3.x * 16.0D;
        double d2 = this.getY() + (double) (this.random.nextInt(16) - 8) - vec3.y * 16.0D;
        double d3 = this.getZ() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3.z * 16.0D;
        return this.teleport(d1, d2, d3);
    }

    private boolean teleport(double p_32544_, double p_32545_, double p_32546_) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(p_32544_, p_32545_, p_32546_);

        while (blockpos$mutableblockpos.getY() > this.level.getMinBuildHeight() && !this.level.getBlockState(blockpos$mutableblockpos).getMaterial().blocksMotion()) {
            blockpos$mutableblockpos.move(Direction.DOWN);
        }

        BlockState blockstate = this.level.getBlockState(blockpos$mutableblockpos);
        boolean flag = blockstate.getMaterial().blocksMotion();
        boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
        if (flag && !flag1) {
            net.minecraftforge.event.entity.EntityTeleportEvent.EnderEntity event = net.minecraftforge.event.ForgeEventFactory.onEnderTeleport(this, p_32544_, p_32545_, p_32546_);
            if (event.isCanceled()) return false;
            Vec3 vec3 = this.position();
            boolean flag2 = this.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
            if (flag2) {
                this.level.gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(this));
                if (!this.isSilent()) {
                    this.level.playSound((Player) null, this.xo, this.yo, this.zo, SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);
                    this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                }
            }

            return flag2;
        } else {
            return false;
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(POSE, Pose.HIDING.id());
        getEntityData().define(IS_ALIVE, random.nextBoolean());
    }


    @Override
    public boolean isCustomNameVisible() {
        return hasCustomName();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.MOVEMENT_SPEED, 0.8F).add(Attributes.KNOCKBACK_RESISTANCE, 0.6F).add(Attributes.ATTACK_KNOCKBACK, 1.0D).add(Attributes.ATTACK_DAMAGE, 6.0D);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        return entity.hurt(ModDamageSource.STATUE, random.nextIntBetweenInclusive(3, 5));
    }

    @Override
    public void knockback(double p_147241_, double p_147242_, double p_147243_) {
        // No no
    }

    @Override
    public void tick() {
        setGlowingTag(true);
        setNoAi(isSeen());
        super.tick();

        if (!isSeen() && getTarget() != null && tickCount % 200 == 0) {
            teleportTowards(getTarget());
        }

    }

    @Override
    public boolean shouldShowName() {
        return false;
    }

    @Override
    public void invokeSeen(Player player) {
        super.invokeSeen(player);
        if (getSeenTime() == 1) {
            setStalkerPose(Pose.randomPose(random));
        }
    }

    public Pose getStalkerPose() {
        return Pose.getPoseByName(getEntityData().get(POSE));
    }

    public void setStalkerPose(Pose pose) {
        getEntityData().set(POSE, pose.id());
    }

    @Override
    public boolean hurt(DamageSource damageSource, float amount) {
        return false;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Override
    public void deserializeNBT(CompoundTag compound) {
        super.deserializeNBT(compound);
        setStalkerPose(Pose.getPoseByName(compound.getString("stalker_pose")));
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compound = super.serializeNBT();
        compound.putString("stalker_pose", getStalkerPose().id());
        return compound;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void doPush(Entity p_27415_) {
    }

    @Override
    protected void pushEntities() {
    }


    // ==== Pose & Emotion ====
    public enum Pose {
        FURIOUS(Emotion.SCREAM), HIDING(Emotion.IDLE), APPROACH(Emotion.ANGRY), IDLE(Emotion.IDLE), SHY(Emotion.ANGRY), ANGRY(Emotion.ANGRY);

        private final Emotion emotion;

        Pose(Emotion emotion) {
            this.emotion = emotion;
        }

        Pose() {
            this.emotion = Emotion.IDLE;
        }

        public String id() {
            return name().toLowerCase();
        }

        public Emotion getEmotion() {
            return emotion;
        }

        public static Pose randomPose(RandomSource randomSource) {
            int x = randomSource.nextInt(Pose.class.getEnumConstants().length);
            return Pose.class.getEnumConstants()[x];
        }

        public static Pose getPoseByName(String name) {
            for (Pose pose : Pose.values()) {
                if (pose.name().toLowerCase().equalsIgnoreCase(name)) {
                    return pose;
                }
            }
            return IDLE;
        }
    }

    public enum Emotion {
        IDLE, SCREAM, ANGRY;

        public String id() {
            return name().toLowerCase();
        }
    }
}
