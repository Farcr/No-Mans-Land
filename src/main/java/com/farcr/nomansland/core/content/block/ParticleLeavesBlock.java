package com.farcr.nomansland.core.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class ParticleLeavesBlock extends LeavesBlock {
    public final Supplier<ParticleType> particle;
    public ParticleLeavesBlock(BlockBehaviour.Properties p_273704_, Supplier<ParticleType> particle) {
        super(p_273704_);
        this.particle = particle;
    }

    /**
     * Called periodically clientside on blocks near the player to show effects (like furnace fire particles).
     */
    public void animateTick(BlockState p_272714_, Level p_272837_, BlockPos p_273218_, RandomSource p_273360_) {
        super.animateTick(p_272714_, p_272837_, p_273218_, p_273360_);
        if (p_273360_.nextInt(10) == 0) {
            BlockPos blockpos = p_273218_.below();
            BlockState blockstate = p_272837_.getBlockState(blockpos);
            if (!isFaceFull(blockstate.getCollisionShape(p_272837_, blockpos), Direction.UP)) {
                ParticleUtils.spawnParticleBelow(p_272837_, p_273218_, p_273360_, (ParticleOptions) particle.get());
            }
        }
    }
}

