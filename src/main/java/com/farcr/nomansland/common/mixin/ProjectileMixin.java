package com.farcr.nomansland.common.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;

@Mixin(Projectile.class)
public abstract class ProjectileMixin extends EntityMixin {
    @Shadow @Nullable public abstract Entity getOwner();
}
