package com.farcr.nomansland.core.content.mixin.variants;

import com.farcr.nomansland.core.content.entity.LivingEntityDuck;
import net.minecraft.client.renderer.entity.GoatRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GoatRenderer.class)
public class GoatRendererMixin {
    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/resources/ResourceLocation;", at = @At("RETURN"), cancellable = true)
    private void getTextureLocation(Entity par1, CallbackInfoReturnable<ResourceLocation> cir) {
        cir.setReturnValue(((LivingEntityDuck)par1).nomansland$getVariant().value().texture().withPath((path) -> "textures/" + path + ".png"));
    }
}
