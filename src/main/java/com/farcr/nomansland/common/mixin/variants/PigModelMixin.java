package com.farcr.nomansland.common.mixin.variants;

import com.farcr.nomansland.client.model.NMLPigModel;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.animal.Pig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PigModel.class)
public class PigModelMixin extends QuadrupedModelMixin<Pig> {
    @Inject(method = "createBodyLayer", at = @At("RETURN"), cancellable = true)
    private static void createBodyLayer(CubeDeformation cubeDeformation, CallbackInfoReturnable<LayerDefinition> cir) {
        cir.setReturnValue(NMLPigModel.createBodyLayer(cubeDeformation));
    }
}
