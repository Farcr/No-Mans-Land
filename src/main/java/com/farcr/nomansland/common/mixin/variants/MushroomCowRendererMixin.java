package com.farcr.nomansland.common.mixin.variants;

import com.farcr.nomansland.common.entity.variant.CowVariant;
import com.farcr.nomansland.common.mixinduck.MooshroomDuck;
import net.minecraft.client.renderer.entity.MushroomCowRenderer;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.MushroomCow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MushroomCowRenderer.class)
public class MushroomCowRendererMixin {
    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/resources/ResourceLocation;", at = @At("RETURN"), cancellable = true)
    private void getTextureLocation(Entity entity, CallbackInfoReturnable<ResourceLocation> cir) {
        CowVariant variant = ((MooshroomDuck)entity).noMansLand$getCustomVariant().value();
        MushroomCow mooshroom = (MushroomCow) entity;
        ResourceLocation texture = mooshroom.isBaby() ? variant.babyTexture() : variant.texture();
        cir.setReturnValue(texture.withPath((path) -> "textures/" + path + ".png"));
    }
}
