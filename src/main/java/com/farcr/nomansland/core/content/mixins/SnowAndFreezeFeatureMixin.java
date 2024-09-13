package com.farcr.nomansland.core.content.mixins;

import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.SnowAndFreezeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.farcr.nomansland.core.content.block.FrostedGrassBlock.SNOWLOGGED;
import static net.minecraft.world.level.block.SnowyDirtBlock.SNOWY;

@Mixin(SnowAndFreezeFeature.class)
public class SnowAndFreezeFeatureMixin {

    @Inject(method = "place", at = @At(value = "TAIL"))
    private void place(FeaturePlaceContext<NoneFeatureConfiguration> p_160368_, CallbackInfoReturnable<Boolean> cir) {
        WorldGenLevel worldgenlevel = p_160368_.level();
        BlockPos blockpos = p_160368_.origin();
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();


        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int k = blockpos.getX() + i;
                int l = blockpos.getZ() + j;
                int i1 = worldgenlevel.getHeight(Heightmap.Types.MOTION_BLOCKING, k, l);
                blockpos$mutableblockpos.set(k, i1, l);
                blockpos$mutableblockpos1.set(blockpos$mutableblockpos).move(Direction.DOWN, 1);
                Biome biome = worldgenlevel.getBiome(blockpos$mutableblockpos).value();

                if (no_Mans_Land$isFrostedGrass(biome, worldgenlevel, blockpos$mutableblockpos)) {
                    worldgenlevel.setBlock(blockpos$mutableblockpos, NMLBlocks.FROSTED_GRASS.get().defaultBlockState().setValue(SNOWLOGGED, true), 2);
                    BlockState blockstate = worldgenlevel.getBlockState(blockpos$mutableblockpos1);
                    if (blockstate.hasProperty(SNOWY)) {
                        worldgenlevel.setBlock(blockpos$mutableblockpos1, blockstate.setValue(SNOWY, Boolean.valueOf(true)), 2);
                    }
                }
            }
        }
    }

    @Unique
    private boolean no_Mans_Land$isFrostedGrass(Biome biome, LevelReader level, BlockPos pos) {
        if (!biome.warmEnoughToRain(pos)) {
            if (pos.getY() >= level.getMinBuildHeight()
                    && pos.getY() < level.getMaxBuildHeight()
                    && level.getBrightness(LightLayer.BLOCK, pos) < 10) {
                BlockState blockstate = level.getBlockState(pos);
                return blockstate.is(NMLBlocks.FROSTED_GRASS);
            }

        }
        return false;
    }
}
