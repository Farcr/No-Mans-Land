package com.farcr.nomansland.core.content.blockentity;

import com.farcr.nomansland.core.config.NMLConfig;
import com.farcr.nomansland.core.content.block.HoneyCauldronBlock;
import com.farcr.nomansland.core.registry.NMLBlockEntities;
import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import static com.farcr.nomansland.core.content.block.TapBlock.*;
import static net.minecraft.world.level.block.BeehiveBlock.HONEY_LEVEL;

public class TapBlockEntity extends BlockEntity {
    public int timeHoney;

    public TapBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(NMLBlockEntities.TAP.get(), pPos, pBlockState);
        this.timeHoney = 0;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TapBlockEntity tap) {
        // Ensure there is a cauldron within 3 blocks under the tap
        BlockPos cauldronPos = getCauldronPos(level, pos);
        if (cauldronPos == null) return;
        BlockState cauldronState = level.getBlockState(cauldronPos);

        BlockState blockBehindState = getBlockStateBehind(level, pos, state);

        // Fill selected cauldron with honey if the block behind is a full beehive
        if (blockBehindState.getBlock() instanceof BeehiveBlock && blockBehindState.getValue(HONEY_LEVEL) == 5) {
            tap.timeHoney++;
            boolean honeyConsumed = false;
            if (cauldronState.getBlock() instanceof AbstractCauldronBlock cauldron) {
                if (!cauldron.isFull(cauldronState)) {
                    if (tap.timeHoney < NMLConfig.TICKS_TO_FILL_CAULDRON.get())
                        spawnDrippingParticles(level, pos, state, FluidType.HONEY);
                    else if (cauldronState.getBlock() instanceof HoneyCauldronBlock honeyCauldron) {
                        honeyCauldron.fillUp(cauldronState, level, cauldronPos);
                        honeyConsumed = true;
                    } else if (cauldronState.getBlock() instanceof CauldronBlock) {
                        honeyConsumed = true;
                        level.setBlockAndUpdate(cauldronPos, NMLBlocks.HONEY_CAULDRON.get().defaultBlockState());
                        level.gameEvent(GameEvent.BLOCK_CHANGE, cauldronPos, GameEvent.Context.of(cauldronState));
                    }
                }
            }
            if (honeyConsumed) {
                tap.timeHoney = 0;
                BlockPos beehivePos = pos.relative(state.getValue(FACING).getOpposite());
                level.setBlockAndUpdate(beehivePos, blockBehindState.setValue(HONEY_LEVEL, 0));
                level.gameEvent(GameEvent.BLOCK_CHANGE, beehivePos, GameEvent.Context.of(blockBehindState));
            }
        }
    }
}
