package com.farcr.nomansland.core.content.mixin.variants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public abstract BlockPos blockPosition();

    @Shadow public abstract RegistryAccess registryAccess();

    @Shadow @Final
    protected SynchedEntityData entityData;
}
