package mc.craig.software.craftplus.common.entities;

import mc.craig.software.craftplus.common.ModDamageSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(POSE, Pose.HIDING.id());
        getEntityData().define(IS_ALIVE, random.nextBoolean());
    }

    public boolean isLivingStatue(){
        return getEntityData().get(IS_ALIVE);
    }

    public void setLivingStatue(boolean alive){
        getEntityData().set(IS_ALIVE, alive);
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
        return entity.hurt(ModDamageSource.OWL_CLAWS, random.nextIntBetweenInclusive(1, 3));
    }

    @Override
    public void knockback(double p_147241_, double p_147242_, double p_147243_) {
        // No no
    }

    @Override
    public void tick() {
        setGlowingTag(true);
        setNoAi(!isLivingStatue());
        if(!isLivingStatue()) return;
        super.tick();
    }

    @Override
    public boolean shouldShowName() {
        return false;
    }

    @Override
    public void invokeSeen(Player player) {
        super.invokeSeen(player);
        if (getSeenTime() == 1 && isLivingStatue()) {
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
        setLivingStatue(compound.getBoolean("is_living"));
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compound = super.serializeNBT();
        compound.putString("stalker_pose", getStalkerPose().id());
        compound.putBoolean("is_living", isLivingStatue());
        return compound;
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
