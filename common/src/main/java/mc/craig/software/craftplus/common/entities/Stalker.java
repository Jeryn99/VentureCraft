package mc.craig.software.craftplus.common.entities;

import mc.craig.software.craftplus.common.ModDamageSource;
import mc.craig.software.craftplus.common.ModSounds;
import mc.craig.software.craftplus.util.ModTags;
import mc.craig.software.craftplus.util.ViewUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.client.NetworkHooks;
import net.threetag.palladiumcore.network.NetworkManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Stalker extends QuantumLockedLifeform implements RangedAttackMob {

    private static final EntityDataAccessor<String> POSE = SynchedEntityData.defineId(Stalker.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Boolean> IS_ALIVE = SynchedEntityData.defineId(Stalker.class, EntityDataSerializers.BOOLEAN);

    public AnimationState POSES_STATE = new AnimationState();

    public Stalker(EntityType<? extends QuantumLockedLifeform> type, Level worldIn) {
        super(worldIn, type);

    }

    @Override
    protected PathNavigation createNavigation(Level p_33802_) {
        return new WallClimberNavigation(this, p_33802_);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new ClimbOnTopOfPowderSnowGoal(this, this.level));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    protected boolean teleport() {
        if (!this.level.isClientSide() && this.isAlive()) {
            double xCoord = this.getX() + (this.random.nextDouble() - 0.5D) * 64.0D;
            double yCoord = this.getY() + (double) (this.random.nextInt(64) - 32);
            double zCoord = this.getZ() + (this.random.nextDouble() - 0.5D) * 64.0D;
            return this.teleport(xCoord, yCoord, zCoord);
        } else {
            return false;
        }
    }

    boolean teleportTowards(Entity p_32501_) {
        Vec3 vec3 = new Vec3(this.getX() - p_32501_.getX(), this.getY(0.5D) - p_32501_.getEyeY(), this.getZ() - p_32501_.getZ());
        vec3 = vec3.normalize();
        double xCoord = this.getX() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3.x * 16.0D;
        double yCoord = this.getY() + (double) (this.random.nextInt(16) - 8) - vec3.y * 16.0D;
        double zCoord = this.getZ() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3.z * 16.0D;
        return this.teleport(xCoord, yCoord, zCoord);
    }

    private boolean teleport(double x, double y, double z) {
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(x, y, z);

        while (mutableBlockPos.getY() > this.level.getMinBuildHeight() && !this.level.getBlockState(mutableBlockPos).getMaterial().blocksMotion()) {
            mutableBlockPos.move(Direction.DOWN);
        }

        BlockState blockstate = this.level.getBlockState(mutableBlockPos);
        boolean flag = blockstate.getMaterial().blocksMotion();
        boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
        if (flag && !flag1) {
            boolean flag2 = this.randomTeleport(x, y, z, false);
            if (flag2) {
                if (!this.isSilent()) {
                    this.level.playSound(null, this.xo, this.yo, this.zo, ModSounds.STALKER_LAUGH.get(), this.getSoundSource(), 1.0F, 1.0F);
                    this.playSound(ModSounds.STALKER_LAUGH.get(), 1.0F, 1.0F);
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
        getEntityData().define(POSE, Pose.randomPose(random).id());
        getEntityData().define(IS_ALIVE, random.nextBoolean());
    }

    public boolean getIsAlive() {
        return getEntityData().get(IS_ALIVE);
    }


    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.STONE_HIT;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return random.nextBoolean() ? ModSounds.STALKER_LAUGH.get() : ModSounds.STALKER_BREATH.get();
    }

    @Override
    protected void playStepSound(BlockPos blockPos, BlockState state) {
        if (!state.getMaterial().isLiquid()) {
            BlockState blockstate = this.level.getBlockState(blockPos.above());
            SoundType soundtype = blockstate.is(Blocks.SNOW) ? blockstate.getSoundType() : state.getSoundType();
            this.playSound(ModSounds.STALKER_MOVE.get(), soundtype.getVolume() * 0.15F, soundtype.getPitch());
        }
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
        boolean hurt = entity.hurt(ModDamageSource.STATUE, random.nextIntBetweenInclusive(3, 5));
        if (hurt) {
            playSound(ModSounds.STALKER_STRIKE.get());
        }
        return hurt;
    }

    @Override
    public void knockback(double p_147241_, double p_147242_, double p_147243_) {
        // No no
    }

    @Override
    public void tick() {

        if(!POSES_STATE.isStarted()){
            POSES_STATE.start(tickCount - random.nextInt(10000));
        }

        if (getIsAlive()) {
            setNoAi(true);
        } else {
            setNoAi(isSeen());
        }
        super.tick();

        if ((ViewUtil.isInPrison(this)) && tickCount % 200 == 0) {
            teleport();
        }
    }

    @Override
    public boolean shouldShowName() {
        return false;
    }

    @Override
    public int getAmbientSoundInterval() {
        return 400;
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

        if(damageSource.getEntity() instanceof Player player){
            boolean hasTotem = player.getInventory().contains(ModTags.TOTEMS);
            return hasTotem && player.getMainHandItem().getItem() instanceof PickaxeItem && super.hurt(damageSource, amount);
        }

        return false;
    }

    @Override
    public boolean onClimbable() {
        return horizontalCollision || super.onClimbable();
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkManager.createAddEntityPacket(this);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        compound.putString("stalker_pose", getStalkerPose().id());
        super.addAdditionalSaveData(compound);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        setStalkerPose(Pose.getPoseByName(compound.getString("stalker_pose")));
        super.readAdditionalSaveData(compound);
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void doPush(Entity p_27415_) {
        super.doPush(p_27415_);
    }

    @Override
    protected void pushEntities() {
        super.pushEntities();
    }

    @Override
    public void performRangedAttack(LivingEntity target, float velocity) {
        ItemStack itemStack = this.getProjectile(this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, Items.BOW)));
        AbstractArrow abstractArrow = ProjectileUtil.getMobArrow(this, itemStack, velocity);
        double d = target.getX() - this.getX();
        double e = target.getY(0.3333333333333333) - abstractArrow.getY();
        double f = target.getZ() - this.getZ();
        double g = Math.sqrt(d * d + f * f);
        abstractArrow.shoot(d, e + g * 0.20000000298023224, f, 1.6F, (float)(14 - this.level.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(abstractArrow);
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
