package com.farcr.nomansland.core.content.mixin.variants;

import com.farcr.nomansland.core.content.entity.variant.PigVariant;
import com.farcr.nomansland.core.content.entity.variant.GoatVariant;
import com.farcr.nomansland.core.content.entity.variant.PigVariant;
import com.farcr.nomansland.core.content.mixinduck.FoxDuck;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GoatRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Pig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PigRenderer.class)
public abstract class PigRendererMixin  {

    @Shadow public abstract ResourceLocation getTextureLocation(Pig entity);

    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/resources/ResourceLocation;", at = @At("RETURN"), cancellable = true)
    private void getTextureLocation(Entity entity, CallbackInfoReturnable<ResourceLocation> cir) {
        PigVariant variant = ((VariantHolder<Holder<PigVariant>>)entity).getVariant().value();
        Pig pig = (Pig) entity;
        ResourceLocation texture = pig.isBaby() ? variant.babyTexture() : variant.texture();
        cir.setReturnValue(texture.withPath((path) -> "textures/" + path + ".png"));    }
}
