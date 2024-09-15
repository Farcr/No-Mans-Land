package com.farcr.nomansland.core.content.block.fruit_trees;

import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

import static com.farcr.nomansland.core.content.block.fruit_trees.FruitBlock.AGE;
import static com.farcr.nomansland.core.content.block.fruit_trees.FruitBlock.getGrowthSpeed;

public class FruitLeavesBlock extends LeavesBlock {

    public Supplier<? extends Block> fruit;
    public Block leaves;

    public FruitLeavesBlock(Properties properties, FruitType fruitType, Block leaves) {
        super(properties);
        this.fruit = fruitType.getFruitBlock();
        this.leaves = leaves;
    }


    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
        int fruitLeaves = 0;
        int regularLeaves = 0;
        if (!state.getValue(PERSISTENT)) {
            for (Direction direction : Direction.values()) {
                BlockPos relativePos = pos.relative(direction);
                if (level.getBlockState(relativePos).is(this)) fruitLeaves++;
                if (level.getBlockState(relativePos).is(leaves)) regularLeaves++;
                for (Direction direction1 : Direction.values()) {
                    if (direction1 == direction.getOpposite()) continue;
                    BlockPos relativePos1 = relativePos.relative(direction1);
                    if (level.getBlockState(relativePos1).is(this)) fruitLeaves++;
                    if (level.getBlockState(relativePos1).is(leaves)) regularLeaves++;
                }
            }
            int distance = state.getValue(DISTANCE);
            boolean waterlogged = state.getValue(WATERLOGGED);
            if (regularLeaves < 4) level.setBlockAndUpdate(pos, leaves.defaultBlockState().setValue(DISTANCE, distance).setValue(WATERLOGGED, waterlogged));
            if (fruitLeaves > 4) level.setBlockAndUpdate(pos, leaves.defaultBlockState().setValue(DISTANCE, distance).setValue(WATERLOGGED, waterlogged));
        }
        // place or grow the fruit block underneath
        BlockPos fruitPos = pos.below();
        BlockState fruitState = level.getBlockState(fruitPos);
        if (!level.isAreaLoaded(fruitPos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (level.getRawBrightness(fruitPos.below(), 0) >= 9 && random.nextInt((int)(25.0F / getGrowthSpeed()) + 1) == 0) {
            if (fruitState.getBlock() instanceof FruitBlock fruitBlock) {
                int fruitAge = fruitState.getValue(AGE);
                if (fruitAge < fruitBlock.getMaxAge()) {
                    level.setBlockAndUpdate(fruitPos, fruitState.setValue(AGE, fruitAge+1));
                }
            } else if (fruitState.isAir()) level.setBlockAndUpdate(fruitPos, fruit.get().defaultBlockState().setValue(AGE, 0));
        }
    }
}
