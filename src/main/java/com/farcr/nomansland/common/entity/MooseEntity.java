package com.farcr.nomansland.common.entity;

//    TODO: MOOSE

//public class MooseEntity extends Animal {
//    public MooseEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
//        super(pEntityType, pLevel);
//    }
//
//    public static AttributeSupplier.Builder createAttributes() {
//        return Animal.createLivingAttributes()
//                .add(Attributes.MAX_HEALTH, 20D)
//                .add(Attributes.FOLLOW_RANGE, 24D)
//                .add(Attributes.MOVEMENT_SPEED, 0.25D)
//                .add(Attributes.ARMOR_TOUGHNESS, 0.1f)
//                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
//                .add(Attributes.ATTACK_DAMAGE, 2f);
//    }
//
//    @Override
//    public void tick() {
//        super.tick();
//    }
//
//    @Override
//    protected void registerGoals() {
//        this.goalSelector.addGoal(0, new FloatGoal(this));
//
//        this.goalSelector.addGoal(1, new BreedGoal(this, 1.15D));
//        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2D, Ingredient.of(Items.COOKED_BEEF), false));
//
//        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
//
//        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.1D));
//        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 3f));
//        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
//
//    }
//
//    @Nullable
//    @Override
//    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
//        return NMLEntities.MOOSE.get().create(pLevel);
//    }
//
//    @Override
//    public boolean isFood(ItemStack pStack) {
//        return pStack.is(Items.COOKED_BEEF);
//    }
//
//    @Nullable
//    @Override
//    protected SoundEvent getAmbientSound() {
//        return SoundEvents.HOGLIN_AMBIENT;
//    }
//
//    @Nullable
//    @Override
//    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
//        return SoundEvents.RAVAGER_HURT;
//    }
//
//    @Nullable
//    @Override
//    protected SoundEvent getDeathSound() {
//        return SoundEvents.DOLPHIN_DEATH;
//    }
//}