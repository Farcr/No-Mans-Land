package com.farcr.nomansland.common.mixin.variants;

import com.farcr.nomansland.common.mixin.RenderLayerMixin;
import com.farcr.nomansland.common.mixinduck.MooshroomDuck;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.kinds.IdF;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.MushroomCowMushroomLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MushroomCowMushroomLayer.class)
public abstract class MushroomCowMushroomLayerMixin<T extends MushroomCow> extends RenderLayerMixin<T, CowModel<T>> {
    @Shadow @Final private BlockRenderDispatcher blockRenderer;

    @Shadow protected abstract void renderMushroomBlock(PoseStack poseStack, MultiBufferSource buffer, int packedLight, boolean outlineOnly, BlockState state, int packedOverlay, BakedModel model);

    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/animal/MushroomCow;FFFFFF)V", at = @At("HEAD"), cancellable = true)
    private void getBlockState(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        if (!livingEntity.isBaby()) {
            Minecraft minecraft = Minecraft.getInstance();
            boolean flag = minecraft.shouldEntityAppearGlowing(livingEntity) && livingEntity.isInvisible();
            if (!livingEntity.isInvisible() || flag) {
                BlockState blockstate = ((MooshroomDuck)livingEntity).noMansLand$getMushroomBlock(((MooshroomDuck)livingEntity).noMansLand$getCustomVariant());
                int i = LivingEntityRenderer.getOverlayCoords(livingEntity, 0);
                BakedModel bakedmodel = this.blockRenderer.getBlockModel(blockstate);
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48));
                poseStack.scale(-1, -1, 1);
                poseStack.translate(-0.5F, -0.3F, -0.5F);
                this.renderMushroomBlock(poseStack, buffer, packedLight, flag, blockstate, i, bakedmodel);
                poseStack.popPose();
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(42));
                poseStack.translate(0.1F, 0, -0.6F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48));
                poseStack.scale(-1, -1, 1);
                poseStack.translate(-0.5F, -0.3F, -0.5F);
                this.renderMushroomBlock(poseStack, buffer, packedLight, flag, blockstate, i, bakedmodel);
                poseStack.popPose();
                poseStack.pushPose();
                this.getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.translate(0, -0.6F, -0.2F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-78));
                poseStack.scale(-1, -1, 1);
                poseStack.translate(-0.5F, -0.3F, -0.5F);
                this.renderMushroomBlock(poseStack, buffer, packedLight, flag, blockstate, i, bakedmodel);
                poseStack.popPose();
            }
        }
        ci.cancel();
    }
}
