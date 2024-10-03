package com.farcr.nomansland.common.mixin.variants;

import com.farcr.nomansland.NoMansLand;
import com.farcr.nomansland.common.entity.variant.PigVariant;
import com.farcr.nomansland.common.mixin.RenderLayerMixin;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Saddleable;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.animal.Pig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SaddleLayer.class)
public abstract class SaddleLayerMixin<T extends Entity & Saddleable, M extends EntityModel<T>> extends RenderLayerMixin<T, M> {
    @Shadow @Final private M model;

    @Shadow @Final private ResourceLocation textureLocation;

    @Inject(method = "render", at = @At("HEAD"))
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        if (livingEntity.isSaddled()) {
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(livingEntity, limbSwing, limbSwingAmount, partialTicks);
            this.model.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            poseStack.pushPose();
            poseStack.scale(1.1F, 0.9F, 1.1F);
            VertexConsumer vertexconsumer;
            if (livingEntity.getType() == EntityType.PIG) {
                ResourceLocation pigTexture = ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "textures/entity/variants/pig/saddle.png");
                vertexconsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(pigTexture));
            } else {
                vertexconsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(this.textureLocation));
            }
            this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
        }

    }
}
