package com.farcr.nomansland.core.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Iterator;

public class NMLUtil {

    public static void bonemealDirt (Level level, BlockPos pos, BlockState state) {
        if (!level.isClientSide) level.setBlockAndUpdate(pos, state);

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        // Iterate through a cube and find suitable dirt blocks that can be turned into the new block
        Iterator<BlockPos> it = BlockPos.betweenClosedStream(x - 3, y - 1, z - 3, x + 3, y + 2, z + 3).iterator();
        while (it.hasNext()) {
            BlockPos bp = it.next();
            BlockState block = level.getBlockState(bp);
            for (Direction d : Direction.values()) {
                Block newBlock = level.getBlockState(bp.relative(d)).getBlock();
                if (level.random.nextFloat() <= 0.3F && block == Blocks.DIRT.defaultBlockState() && newBlock instanceof SpreadingSnowyDirtBlock && !level.getBlockState(bp.above()).isSolid()) {
                    BlockPos particlePosition = bp.above();
                    if (!level.isClientSide) level.setBlockAndUpdate(bp, state);
                    else {
                        for (int i = 0; i <= 3; i++) {
                            level.addParticle(ParticleTypes.COMPOSTER, particlePosition.getX() + Math.random(), particlePosition.getY() + 0.2 + Math.random(), particlePosition.getZ() + Math.random(), 0, 0, 0);
                        }
                    }
                }
            }
        }
    }
}
