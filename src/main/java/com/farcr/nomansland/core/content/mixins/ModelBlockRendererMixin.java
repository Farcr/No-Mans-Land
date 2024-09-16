package com.farcr.nomansland.core.content.mixins;

import com.farcr.nomansland.core.NoMansLand;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.farcr.nomansland.core.content.block.FrostedGrassBlock.SNOWLOGGED;

@Mixin(ModelBlockRenderer.class)
public abstract class ModelBlockRendererMixin {
    @Inject(method = "tesselateBlock(Lnet/minecraft/world/level/BlockAndTintGetter;Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;ZLnet/minecraft/util/RandomSource;JILnet/neoforged/neoforge/client/model/data/ModelData;Lnet/minecraft/client/renderer/RenderType;)V",
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", shift = At.Shift.AFTER))
    private void nomansland$tesselateBlockSnowlogging(
            BlockAndTintGetter blockAndTintGetter,
            BakedModel model,
            BlockState state,
            BlockPos pos,
            PoseStack poseStack,
            VertexConsumer vertexConsumer,
            boolean checkSides,
            RandomSource random,
            long seed,
            int packedOverlay,
            ModelData modelData,
            RenderType renderType,
            CallbackInfo ci) {
        ModelBlockRenderer me = (ModelBlockRenderer) ((Object) this);
        if (state.getOptionalValue(SNOWLOGGED).orElse(false)) {
            BakedModel snowModel = Minecraft.getInstance().getBlockRenderer().getBlockModel(Blocks.SNOW_BLOCK.defaultBlockState());
            me.tesselateWithAO(blockAndTintGetter, snowModel, Blocks.SNOW_BLOCK.defaultBlockState(), pos, poseStack, vertexConsumer, checkSides, random, seed, packedOverlay, modelData, renderType);
        }
    }
}
