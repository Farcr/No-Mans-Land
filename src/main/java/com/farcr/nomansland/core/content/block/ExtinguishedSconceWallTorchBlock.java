package com.farcr.nomansland.core.content.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class ExtinguishedSconceWallTorchBlock extends ExtinguishedWallTorchBlock{
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
    public ExtinguishedSconceWallTorchBlock(Properties pProperties, Block mainBlock, ParticleOptions pFlameParticle) {
        super(pProperties, mainBlock, pFlameParticle);
    }

}
