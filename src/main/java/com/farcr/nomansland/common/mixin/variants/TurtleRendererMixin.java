package com.farcr.nomansland.common.mixin.variants;

import com.farcr.nomansland.common.entity.variant.TurtleVariant;
import net.minecraft.client.renderer.entity.TurtleRenderer;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.VariantHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TurtleRenderer.class)
public class TurtleRendererMixin {
    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/resources/ResourceLocation;", at = @At("RETURN"), cancellable = true)
    private void getTextureLocation(Entity entity, CallbackInfoReturnable<ResourceLocation> cir) {
        cir.setReturnValue(((VariantHolder<Holder<TurtleVariant>>)entity).getVariant().value().texture().withPath((path) -> "textures/" + path + ".png"));
    }
}
