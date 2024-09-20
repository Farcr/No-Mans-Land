package com.farcr.nomansland.core.content.block;

import com.farcr.nomansland.core.registry.NMLTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BaseCoralWallFanBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ShelfMushroomBlock extends BaseCoralWallFanBlock {
    public ShelfMushroomBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    public static boolean isAcceptableNeighbour(BlockGetter blockReader, BlockPos neighborPos) {
        BlockState blockstate = blockReader.getBlockState(neighborPos);
        return blockstate.is(BlockTags.LOGS) || blockstate.is(NMLTags.MUSHROOM_BLOCKS);
    }

    //TODO: Change bounding box
    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos blockpos = pos.relative(direction.getOpposite());
        BlockState blockstate = level.getBlockState(blockpos);
        return blockstate.is(BlockTags.LOGS) || blockstate.is(NMLTags.MUSHROOM_BLOCKS);
    }
}
