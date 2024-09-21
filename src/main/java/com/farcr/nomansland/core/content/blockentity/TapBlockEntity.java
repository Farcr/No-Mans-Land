package com.farcr.nomansland.core.content.blockentity;

import com.farcr.nomansland.core.config.NMLConfig;
import com.farcr.nomansland.core.content.block.cauldrons.NMLCauldronBlock;
import com.farcr.nomansland.core.registry.NMLBlockEntities;
import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import static com.farcr.nomansland.core.content.block.TapBlock.*;
import static net.minecraft.world.level.block.BeehiveBlock.HONEY_LEVEL;
import static net.minecraft.world.level.block.LayeredCauldronBlock.LEVEL;

public class TapBlockEntity extends BlockEntity {
    public int timeEmptying;

    public TapBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(NMLBlockEntities.TAP.get(), pPos, pBlockState);
        this.timeEmptying = 0;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TapBlockEntity tap) {
        // Ensure there is a cauldron within 3 blocks under the tap
        BlockPos cauldronPos = getCauldronPos(level, pos);
        if (cauldronPos == null) return;
        BlockState cauldronState = level.getBlockState(cauldronPos);
        BlockState stateBehind = getBlockStateBehind(level, pos, state);
        BlockPos posBehind = pos.relative(state.getValue(FACING).getOpposite());
        Block cauldronBlock = cauldronState.getBlock();

        // Fill selected cauldron with honey if the block behind is a full beehive
        if (stateBehind.hasProperty(HONEY_LEVEL) && stateBehind.getValue(HONEY_LEVEL) == 5) {
            boolean honeyConsumed = false;
            if (cauldronBlock instanceof AbstractCauldronBlock cauldron) {
                tap.timeEmptying++;
                if (!cauldron.isFull(cauldronState)) {
                    if (tap.timeEmptying < NMLConfig.TICKS_TO_FILL_CAULDRON.get()) spawnDrippingParticles(level, pos, state, ParticleTypes.FALLING_HONEY);
                    else {
                        level.setBlockAndUpdate(cauldronPos, NMLBlocks.HONEY_CAULDRON.get().defaultBlockState().setValue(LEVEL, cauldronState.getValue(LEVEL)+1));
                        honeyConsumed = true;
                    }
                }
            }
            if (honeyConsumed) {
                tap.timeEmptying = 0;
                BlockPos beehivePos = pos.relative(state.getValue(FACING).getOpposite());
                level.setBlockAndUpdate(beehivePos, stateBehind.setValue(HONEY_LEVEL, 0));
                level.gameEvent(GameEvent.BLOCK_CHANGE, beehivePos, GameEvent.Context.of(stateBehind));
                level.gameEvent(GameEvent.BLOCK_CHANGE, cauldronPos, GameEvent.Context.of(cauldronState));
            }
        }

        if ((stateBehind.hasProperty(LEVEL) || stateBehind.is(Blocks.LAVA_CAULDRON)) && cauldronBlock instanceof AbstractCauldronBlock cauldron && !cauldron.isFull(cauldronState) && (stateBehind.is(cauldronBlock) || cauldronState.is(Blocks.CAULDRON)) && !stateBehind.is(Blocks.POWDER_SNOW_CAULDRON)) {
            tap.timeEmptying++;
            if (tap.timeEmptying < NMLConfig.TICKS_TO_FILL_CAULDRON.get()) {
                spawnDrippingParticles(level, pos, state, stateBehind.getBlock() instanceof NMLCauldronBlock nmlCauldronBlock ? nmlCauldronBlock.getParticleType() : stateBehind.is(Blocks.LAVA_CAULDRON) ? ParticleTypes.FALLING_LAVA : ParticleTypes.FALLING_WATER);
            } else {
                tap.timeEmptying = 0;
                if (stateBehind.is(Blocks.LAVA_CAULDRON)) {
                    level.setBlockAndUpdate(posBehind, Blocks.CAULDRON.defaultBlockState());
                    level.setBlockAndUpdate(cauldronPos, Blocks.LAVA_CAULDRON.defaultBlockState());
                }
                else {
                    LayeredCauldronBlock.lowerFillLevel(stateBehind, level, posBehind);
                    if (cauldronState.hasProperty(LEVEL)) level.setBlockAndUpdate(cauldronPos, stateBehind.setValue(LEVEL, cauldronState.getValue(LEVEL) + 1));
                    else level.setBlockAndUpdate(cauldronPos, stateBehind.getBlock().defaultBlockState());
                }
                level.gameEvent(GameEvent.BLOCK_CHANGE, cauldronPos, GameEvent.Context.of(cauldronState));
            }
        }
    }
}
