package com.farcr.nomansland.core.content.entity;

import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public abstract class ThrowableBombEntity extends ThrowableProjectile {

    private static final float VERTICAL_RESTITUTION = 0.3F;
    private static final float HORIZONTAL_RESTITUTION = 0.4F;
    private static final int MAX_LIFE = 200;

    private int explodeTimer = -1;
    private float oRoll;
    private float roll;

    protected ThrowableBombEntity(EntityType<? extends ThrowableProjectile> entityType, Level level) {
        super(entityType, level);
    }

    protected ThrowableBombEntity(EntityType<? extends ThrowableProjectile> entityType, double d, double e, double f, Level level) {
        super(entityType, d, e, f, level);
    }

    protected ThrowableBombEntity(EntityType<? extends ThrowableProjectile> entityType, LivingEntity livingEntity, Level level) {
        super(entityType, livingEntity, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }

    @Override
    protected void onHit(HitResult hitResult) {
        if (hitResult.getType() == HitResult.Type.MISS) {
            return;
        }

        if (hitResult instanceof BlockHitResult blockHitResult) {
            Vec3 motion = this.getDeltaMovement();
            if (motion.lengthSqr() < 0.1) {
                this.setDeltaMovement(Vec3.ZERO);
                this.setOnGround(true);
                return;
            }

            Direction direction = blockHitResult.getDirection();
            switch (direction.getAxis()) {
                case X -> this.setDeltaMovement(
                        -motion.x() * HORIZONTAL_RESTITUTION,
                        motion.y(),
                        motion.z()
                );
                case Y ->
                        this.setDeltaMovement(motion.x() * VERTICAL_RESTITUTION, -motion.y() * VERTICAL_RESTITUTION, motion.z() * VERTICAL_RESTITUTION);
                case Z -> this.setDeltaMovement(
                        motion.x(),
                        motion.y(),
                        -motion.z() * HORIZONTAL_RESTITUTION
                );
            }
            if (this.explodeTimer == -1) {
                this.explodeTimer = 30;
            }
        }

        super.onHit(hitResult);
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        if (!this.level().isClientSide()) {
            this.explode();
        }
    }

    @Override
    protected void updateRotation() {
        Vec3 deltaMovement = this.getDeltaMovement();
        double distanceSq = deltaMovement.horizontalDistanceSqr();
        double distance = Math.sqrt(distanceSq);
        this.setXRot(lerpRotation(this.xRotO, (float) (Mth.atan2(deltaMovement.y, distance) * 180.0F / (float) Math.PI)));
        if (distanceSq > 0.01) {
            this.setYRot(lerpRotation(this.yRotO, (float) (Mth.atan2(deltaMovement.x, deltaMovement.z) * 180.0F / (float) Math.PI)));
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        if (this.explodeTimer > -1) {
            nbt.putByte("ExplodeTime", (byte) this.explodeTimer);
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.explodeTimer = nbt.contains("ExplodeTime", Tag.TAG_INT) ? nbt.getInt("ExplodeTime") : -1;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.oRoll = this.roll;

            double distance = this.getDeltaMovement().lengthSqr();
            if (distance > 0.01) {
                this.roll += Math.sqrt(distance) * 45;
            }

            if (!this.onGround()) {
                this.level().addParticle(this.getParticle(), this.getX(), this.getY() + this.getBbHeight(), this.getZ(), 0, 0, 0);
            }
        } else {
            if (this.explodeTimer > -1) {
                this.explodeTimer--;
                if (this.explodeTimer <= 0) {
                    this.explode();
                }
            } else if (this.tickCount >= MAX_LIFE) {
                this.explode();
            }
        }
    }

    protected abstract void explode();

    protected abstract ParticleOptions getParticle();

    public float getRoll(float partialTicks) {
        return Mth.lerp(partialTicks, this.oRoll, this.roll);
    }
}