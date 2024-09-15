package com.farcr.nomansland.core.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.util.TriState;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class SurfaceMushroomBlock extends MushroomBlock {
    public SurfaceMushroomBlock(ResourceKey<ConfiguredFeature<?, ?>> pFeature, Properties pProperties) {
        super(pFeature, pProperties);
    }

    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        if (ModList.get().isLoaded("farmersdelight")) {
            return pState.is(BlockTags.DIRT) || pState.is(Blocks.FARMLAND) || pState.is(ModBlocks.ORGANIC_COMPOST.get());
        }
        return pState.is(BlockTags.DIRT) || pState.is(Blocks.FARMLAND);
    }


    // TODO: fix
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        if (pState.getBlock() == this)
            return pLevel.getBlockState(blockpos).canSustainPlant(pLevel, blockpos, Direction.UP, this.defaultBlockState()) == TriState.TRUE;
        return this.mayPlaceOn(pLevel.getBlockState(blockpos), pLevel, blockpos);
    }
}
