package com.farcr.nomansland.core.content.block;

import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.ScaffoldingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;

public class WoodenScaffoldingBlock extends ScaffoldingBlock {
//    public static final IntegerProperty DISTANCE = BlockStateProperties.STABILITY_DISTANCE;
    public WoodenScaffoldingBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos $$1 = pContext.getClickedPos();
        Level $$2 = pContext.getLevel();
        int $$3 = getDistance($$2, $$1);
        return (BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(WATERLOGGED, $$2.getFluidState($$1).getType() == Fluids.WATER)).setValue(DISTANCE, $$3)).setValue(BOTTOM, this.isBottom($$2, $$1, $$3));
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        int $$4 = getDistance(pLevel, pPos);
        BlockState $$5 = (BlockState)((BlockState)pState.setValue(DISTANCE, $$4)).setValue(BOTTOM, this.isBottom(pLevel, pPos, $$4));
        if ((Integer)$$5.getValue(DISTANCE) == 7) {
            if ((Integer)pState.getValue(DISTANCE) == 7) {
                FallingBlockEntity.fall(pLevel, pPos, $$5);
            } else {
                pLevel.destroyBlock(pPos, true);
            }
        } else if (pState != $$5) {
            pLevel.setBlock(pPos, $$5, 3);
        }

    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return getDistance(pLevel, pPos) < 7;
    }

    // more like isTwink
    private boolean isBottom(BlockGetter pLevel, BlockPos pPos, int pDistance) {
        return pDistance > 0 && !pLevel.getBlockState(pPos.below()).is(this);
    }

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
