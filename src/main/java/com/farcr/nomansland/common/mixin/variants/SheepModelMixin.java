package com.farcr.nomansland.common.mixin.variants;

import com.farcr.nomansland.client.model.NMLCowModel;
import com.farcr.nomansland.client.model.NMLSheepModel;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SheepModel.class)
public class SheepModelMixin {
    @Inject(method = "createBodyLayer", at = @At("RETURN"), cancellable = true)
    private static void createBodyLayer(CallbackInfoReturnable<LayerDefinition> cir) {
        cir.setReturnValue(NMLSheepModel.createBodyLayer());
    }
}
