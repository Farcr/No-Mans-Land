package com.farcr.nomansland.core.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SconceTorchBlock extends TorchBlock {
    protected static final VoxelShape AABB = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 12.0D, 10.0D);

    public SconceTorchBlock(SimpleParticleType pFlameParticle, Properties pProperties) {
        super(pFlameParticle, pProperties);

    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return AABB;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        double d0 = (double) pPos.getX() + 0.5D;
        double d1 = (double) pPos.getY() + 0.8D;
        double d2 = (double) pPos.getZ() + 0.5D;
        pLevel.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        pLevel.addParticle(this.flameParticle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
}
