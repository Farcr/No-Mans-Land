package com.farcr.nomansland.core.content.block;

import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.ScaffoldingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class WoodenScaffoldingBlock extends ScaffoldingBlock {
    public static final IntegerProperty DISTANCE = BlockStateProperties.STABILITY_DISTANCE;

    public WoodenScaffoldingBlock(Properties pProperties) {
        super(pProperties);
    }
    //TODO: DISTANCE DOESNT WORK
    public static int getDistance(BlockGetter pLevel, BlockPos pPos) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable().move(Direction.DOWN);
        BlockState blockstate = pLevel.getBlockState(blockpos$mutableblockpos);
        int i = 7;
        if (blockstate.is(NMLBlocks.WOODEN_SCAFFOLDING.get())) {
            i = blockstate.getValue(DISTANCE);
        } else if (blockstate.isFaceSturdy(pLevel, blockpos$mutableblockpos, Direction.UP)) {
            return 0;
        }

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            BlockState blockstate1 = pLevel.getBlockState(blockpos$mutableblockpos.setWithOffset(pPos, direction));
            if (blockstate1.is(NMLBlocks.WOODEN_SCAFFOLDING.get())) {
                i = Math.min(i, blockstate1.getValue(DISTANCE) + 1);
                if (i == 1) {
                    break;
                }
            }
        }

        return i;
    }
}
