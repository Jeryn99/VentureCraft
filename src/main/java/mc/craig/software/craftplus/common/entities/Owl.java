package mc.craig.software.craftplus.common.entities;

import mc.craig.software.craftplus.common.ModDamageSource;
import mc.craig.software.craftplus.common.ModEntities;
import mc.craig.software.craftplus.common.ModSounds;
import mc.craig.software.craftplus.common.entities.ai.owl.OwlChargeAttackGoal;
import mc.craig.software.craftplus.common.entities.ai.owl.OwlSitOnBlocks;
import mc.craig.software.craftplus.common.entities.ai.owl.OwlWanderGoal;
import mc.craig.software.craftplus.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;


public class Owl extends ShoulderRidingEntity implements FlyingAnimal, NeutralMob {

    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(Owl.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Owl.class, EntityDataSerializers.INT);
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);


    public AnimationState flyingAnimationState = new AnimationState();
    public AnimationState standingAnimationState = new AnimationState();
    public AnimationState sittinAnimationState = new AnimationState();
    public float flap, flapSpeed, oFlapSpeed, oFlap;
    private float nextFlap = 1.0F, flapping = 1.0F;

    private int ticksSinceEaten;

    public Owl(EntityType<? extends ShoulderRidingEntity> shoulder, Level level) {
        super(shoulder, level);
        this.moveControl = new FlyingMoveControl(this, 20, false);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.COCOA, -1.0F);
        this.setCanPickUpLoot(true);
    }


    @Override
    public void playAmbientSound() {
        if (level.isDay()) return;
        super.playAmbientSound();
    }


    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.OWL_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.OWL_HOOTS.get();
    }

    @Override
    protected float getSoundVolume() {
        return 0.1F;
    }


    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData p_29392_, @Nullable CompoundTag p_29393_) {
        this.setVariant(serverLevelAccessor.getRandom().nextInt(6));
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, p_29392_, p_29393_);
    }

    @Override
    public double getMeleeAttackRangeSqr(LivingEntity livingEntity) {
        return this.getBbWidth() * 4.0F * this.getBbWidth() * 4.0F + livingEntity.getBbWidth();
    }


    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Variant", this.getVariant());
        this.addPersistentAngerSaveData(compoundTag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setVariant(compoundTag.getInt("Variant"));
        this.readPersistentAngerSaveData(level, compoundTag);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_VARIANT_ID, 0);
        this.getEntityData().define(DATA_REMAINING_ANGER_TIME, 0);
    }

    public int getVariant() {
        return Mth.clamp(this.getEntityData().get(DATA_VARIANT_ID), 0, 7);
    }

    public void setVariant(int variant) {
        this.getEntityData().set(DATA_VARIANT_ID, variant);
    }

    private void calculateFlapping() {
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (float) (!this.onGround && !this.isPassenger() ? 4 : -1) * 0.3F;
        this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        Vec3 vec3 = this.getDeltaMovement();
        if (!this.onGround && vec3.y < 0.0D) {
            this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
        }

        this.flap += this.flapping * 2.0F;
    }

    @Override
    protected void onFlap() {
        this.playSound(SoundEvents.PARROT_FLY, 0.15F, 1.0F);
        this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
    }

    @Override
    protected boolean isFlapping() {
        return this.flyDist > this.nextFlap;
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        return entity.hurt(ModDamageSource.OWL_CLAWS, (float) ((int) this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor levelAccessor, MobSpawnType mobSpawnType) {
        return !level.isDay() && super.checkSpawnRules(levelAccessor, mobSpawnType);
    }
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        if (!this.isTame() && (itemstack.is(Items.SPIDER_EYE) || itemstack.is(Items.FERMENTED_SPIDER_EYE))) {
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            if (!this.isSilent()) {
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.PARROT_EAT, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            }

            if (!this.level.isClientSide) {
                if (this.random.nextInt(10) == 0 && !ForgeEventFactory.onAnimalTame(this, player)) {
                    this.tame(player);
                    this.level.broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level.broadcastEntityEvent(this, (byte) 6);
                }
            }

            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else if (itemstack.is(ModTags.BIRD_POISON)) {
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            this.addEffect(new MobEffectInstance(MobEffects.POISON, 900));
            if (player.isCreative() || !this.isInvulnerable()) {
                this.hurt(DamageSource.playerAttack(player), Float.MAX_VALUE);
            }

            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else if (!this.isFlying() && this.isTame() && this.isOwnedBy(player)) {
            if (!this.level.isClientSide) {
                this.setOrderedToSit(!this.isOrderedToSit());
            }

            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(player, interactionHand);
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, 0.4F).add(Attributes.ATTACK_DAMAGE, 2F).add(Attributes.ATTACK_KNOCKBACK, 0.4F).add(Attributes.MOVEMENT_SPEED, 0.2F);
    }

    @Override
    protected void pickUpItem(ItemEntity itemEntity) {
        ItemStack itemstack = itemEntity.getItem();
        if (this.canHoldItem(itemstack)) {
            int i = itemstack.getCount();
            if (i > 1) {
                ItemEntity itementity = new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), itemstack.split(i - 1));
                this.level.addFreshEntity(itementity);
            }
            this.onItemPickup(itemEntity);
            this.setItemSlot(EquipmentSlot.MAINHAND, itemstack.split(1));
            this.setGuaranteedDrop(EquipmentSlot.MAINHAND);
            this.take(itemEntity, itemstack.getCount());
            itemEntity.discard();
            this.ticksSinceEaten = 0;
        }
    }

    @Override
    public boolean canHoldItem(ItemStack itemStack) {
        return itemStack.is(ModTags.OWL_FOOD);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 5.0F, 1.0F, true));
        this.goalSelector.addGoal(3, new LandOnOwnersShoulderGoal(this));
        this.goalSelector.addGoal(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(ModTags.OWL_FOOD), false));
        this.goalSelector.addGoal(3, new OwlWanderGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));

        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0D, 1.2D));
        this.targetSelector.addGoal(1, new OwlSitOnBlocks(this, 1.1D, 8));

        // Attack
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(4, new OwlChargeAttackGoal(this));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractFish.class, 20, false, false, (mob) -> mob instanceof AbstractFish));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, PathfinderMob.class, 20, false, false, (mob) -> mob.getType().is(ModTags.OWL_ATTACK)));
        this.targetSelector.addGoal(1, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));

        // Breeding
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.25D));


    }

    @Override
    public boolean causeFallDamage(float p_148989_, float p_148990_, DamageSource p_148991_) {
        return false;
    }

    @Override
    protected void checkFallDamage(double p_29370_, boolean p_29371_, BlockState p_29372_, BlockPos p_29373_) {
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, level);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    @Override
    public boolean onClimbable() {
        return false;
    }

    @Override
    public void tick() {
        if (level.isClientSide) {

            if (isFlying()) {
                Vec3 movement = getDeltaMovement();
                if (!flyingAnimationState.isStarted()) {
                    flyingAnimationState.start(tickCount);
                }
            } else {
                flyingAnimationState.stop();
                if (isOrderedToSit()) {
                    if (!sittinAnimationState.isStarted()) {
                        sittinAnimationState.start(tickCount);
                    }
                } else {
                    sittinAnimationState.stop();
                    if (!standingAnimationState.isStarted()) {
                        standingAnimationState.start(tickCount);
                    }
                }
            }
        }
        super.tick();
    }

    @Override
    public void aiStep() {
        this.calculateFlapping();
        super.aiStep();

        if (!this.level.isClientSide && this.isAlive() && this.isEffectiveAi()) {
            ++this.ticksSinceEaten;
            ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (this.canEat(itemstack)) {
                setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.AIR));
                setItemInHand(InteractionHand.OFF_HAND, itemstack); //Move to Offhand (Mouth)
                if (this.ticksSinceEaten > 600) {
                    ItemStack itemstack1 = itemstack.finishUsingItem(this.level, this);
                    if (!itemstack1.isEmpty()) {
                        this.setItemSlot(EquipmentSlot.OFFHAND, itemstack1);
                    }

                    this.ticksSinceEaten = 0;
                } else if (this.ticksSinceEaten > 560 && this.random.nextFloat() < 0.1F) {
                    this.playSound(this.getEatingSound(itemstack), 1.0F, 1.0F);
                    this.level.broadcastEntityEvent(this, (byte) 45);
                }
            }
        }

        if (!this.level.isClientSide) {
            this.updatePersistentAnger((ServerLevel) this.level, true);
        }
    }

    private boolean canEat(ItemStack p_28598_) {
        return p_28598_.getItem().isEdible() && this.getTarget() == null && this.onGround && !this.isSleeping();
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        Owl owl = new Owl(ModEntities.OWL.get(), serverLevel);
        return owl;
    }

    @Override
    public boolean isFlying() {
        return !this.onGround;
    }

    public int getRemainingPersistentAngerTime() {
        return this.getEntityData().get(DATA_REMAINING_ANGER_TIME);
    }

    public void setRemainingPersistentAngerTime(int p_30404_) {
        this.getEntityData().set(DATA_REMAINING_ANGER_TIME, p_30404_);
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    @Nullable
    private UUID persistentAngerTarget;

    @Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    public void setPersistentAngerTarget(@Nullable UUID p_30400_) {
        this.persistentAngerTarget = p_30400_;
    }
}
