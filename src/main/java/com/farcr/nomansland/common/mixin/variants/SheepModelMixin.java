package com.farcr.nomansland.common.mixin.variants;

import com.farcr.nomansland.client.model.NMLSheepModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.animal.Sheep;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SheepModel.class)
public class SheepModelMixin extends QuadrupedModelMixin<Sheep> {
    @Inject(method = "createBodyLayer", at = @At("RETURN"), cancellable = true)
    private static void createBodyLayer(CallbackInfoReturnable<LayerDefinition> cir) {
        cir.setReturnValue(NMLSheepModel.createBodyLayer());
    }

    @Inject(method = "setupAnim(Lnet/minecraft/world/entity/animal/Sheep;FFFFF)V", at = @At("HEAD"), cancellable = true)
    protected void setupAnim(Sheep entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        NMLSheepModel.setupAnim(entity, root, limbSwing, limbSwingAmount, netHeadYaw, headPitch);
        ci.cancel();
    }
}
