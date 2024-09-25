package com.farcr.nomansland.core.content.mixin.variants;

import com.farcr.nomansland.core.content.entity.variant.FoxVariant;
import com.farcr.nomansland.core.content.mixinduck.FoxDuck;
import net.minecraft.client.renderer.entity.FoxRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Fox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FoxRenderer.class)
public class FoxRendererMixin {
    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/resources/ResourceLocation;", at = @At("RETURN"), cancellable = true)
    private void getTextureLocation(Entity entity, CallbackInfoReturnable<ResourceLocation> cir) {
        FoxVariant variant = ((FoxDuck)entity).noMansLand$getCustomVariant().value();
        Fox fox = (Fox) entity;
        ResourceLocation texture = fox.isSleeping() ? variant.sleepingTexture() : variant.texture();
        cir.setReturnValue(texture.withPath((path) -> "textures/" + path + ".png"));
    }
}
