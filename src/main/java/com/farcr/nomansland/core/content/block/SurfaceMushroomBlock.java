package com.farcr.nomansland.core.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class SurfaceMushroomBlock extends MushroomBlock {
    public SurfaceMushroomBlock(ResourceKey<ConfiguredFeature<?, ?>> pFeature, Properties pProperties) {
        super(pFeature, pProperties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.DIRT);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
//        BlockPos lowerPos = pos.below();
//        BlockState lowerState = level.getBlockState(lowerPos);
//        TriState soilDecision = lowerState.canSustainPlant(level, lowerPos, net.minecraft.core.Direction.UP, state);
//        return lowerState.is(BlockTags.MUSHROOM_GROW_BLOCK) || (soilDecision.isDefault() ? this.mayPlaceOn(lowerState, level, lowerPos) : soilDecision.isTrue());
    }
}
