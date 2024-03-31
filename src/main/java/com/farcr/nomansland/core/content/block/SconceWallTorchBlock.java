package com.farcr.nomansland.core.content.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class SconceWallTorchBlock extends WallTorchBlock {

    private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, Block.box(5.5D, 1.5D, 11.0D, 10.5D, 13.5D, 16.0D),
            Direction.SOUTH, Block.box(5.5D, 1.5D, 0.0D, 10.5D, 13.5D, 5.0D),
            Direction.WEST, Block.box(11.0D, 1.5D, 5.5D, 16.0D, 13.5D, 10.5D),
            Direction.EAST, Block.box(0.0D, 1.5D, 5.5D, 5.0D, 13.5D, 10.5D)
    ));

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return getShape(pState);
    }
    public static VoxelShape getShape(BlockState pState) {
        return AABBS.get(pState.getValue(FACING));
    }

    //TODO:PARTICLES ARE FUCKED UP!!!!!

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        Direction direction = pState.getValue(FACING);
        double d0 = (double)pPos.getX() + 0.45D;
        double d1 = (double)pPos.getY() + 0.7D;
        double d2 = (double)pPos.getZ() + 0.5D;
        Direction direction1 = direction.getOpposite();
        pLevel.addParticle(ParticleTypes.SMOKE, d0 + 0.27D * (double)direction1.getStepX(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getStepZ(), 0.0D, 0.0D, 0.0D);
        pLevel.addParticle(this.flameParticle, d0 + 0.27D * (double)direction1.getStepX(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getStepZ(), 0.0D, 0.0D, 0.0D);
    }
    public SconceWallTorchBlock(Properties pProperties, ParticleOptions pFlameParticle) {
        super(pProperties, pFlameParticle);
    }
}
