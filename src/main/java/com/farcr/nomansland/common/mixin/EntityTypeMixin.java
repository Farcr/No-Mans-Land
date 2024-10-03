package com.farcr.nomansland.common.mixin;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityType.class)
public abstract class EntityTypeMixin {

    @Inject(method = "getDimensions", at = @At("RETURN"), cancellable = true)
    private void getDimensions(CallbackInfoReturnable<EntityDimensions> cir) {
//        if (((EntityType<?>)(Object)this).is((HolderSet<EntityType<?>>) EntityType.COW)) {
//        }
    }
}
