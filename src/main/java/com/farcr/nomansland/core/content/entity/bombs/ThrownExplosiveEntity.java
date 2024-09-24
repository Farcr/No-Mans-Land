package com.farcr.nomansland.core.content.entity.bombs;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class ThrownExplosiveEntity extends ThrowableBombEntity{
    protected ThrownExplosiveEntity(EntityType<? extends ThrowableProjectile> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void explode() {

    }

    @Override
    protected ParticleOptions getParticle() {
        return null;
    }
}

//TODO: THIS