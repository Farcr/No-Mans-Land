package com.farcr.nomansland.common.mixin;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RenderLayer.class)
public abstract class RenderLayerMixin<T extends Entity, M extends EntityModel<T>> {
    @Shadow public abstract M getParentModel();
}
