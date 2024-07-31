package com.farcr.nomansland.core.content.block;

import com.farcr.nomansland.core.registry.NMLBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class BuddingQuartziteBlock extends BuddingAmethystBlock {
    public static final MapCodec<BuddingAmethystBlock> CODEC = simpleCodec(BuddingQuartziteBlock::new);
    @Override
    public MapCodec<BuddingAmethystBlock> codec() {
        return CODEC;
    }

    public static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();

    public BuddingQuartziteBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[pRandom.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pPos.relative(direction);
            BlockState blockstate = pLevel.getBlockState(blockpos);
            Block block = null;
            if (canClusterGrowAtState(blockstate)) {
                block = NMLBlocks.SMALL_QUARTZITE_BUD.get();
            } else if (blockstate.is(NMLBlocks.SMALL_QUARTZITE_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = NMLBlocks.MEDIUM_QUARTZITE_BUD.get();
            } else if (blockstate.is(NMLBlocks.MEDIUM_QUARTZITE_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = NMLBlocks.LARGE_QUARTZITE_BUD.get();
            } else if (blockstate.is(NMLBlocks.LARGE_QUARTZITE_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = NMLBlocks.QUARTZITE_CLUSTER.get();
            }

            if (block != null) {
                BlockState blockstate1 = block.defaultBlockState().setValue(AmethystClusterBlock.FACING, direction).setValue(AmethystClusterBlock.WATERLOGGED, Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
                pLevel.setBlockAndUpdate(blockpos, blockstate1);
            }

        }
    }
}
