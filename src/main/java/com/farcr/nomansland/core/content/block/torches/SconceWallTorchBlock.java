package com.farcr.nomansland.core.content.block.torches;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
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
            Direction.NORTH, Block.box(5.5, 1.5, 11.0, 10.5, 13.5, 16.0),
            Direction.SOUTH, Block.box(5.5, 1.5, 0.0, 10.5, 13.5, 5.0),
            Direction.WEST, Block.box(11.0, 1.5, 5.5, 16.0, 13.5, 10.5),
            Direction.EAST, Block.box(0.0, 1.5, 5.5, 5.0, 13.5, 10.5)
    ));

    public SconceWallTorchBlock(SimpleParticleType flameParticle, Properties properties) {
        super(flameParticle, properties);
    }

    public static VoxelShape getShape(BlockState state) {
        return AABBS.get(state.getValue(FACING));
    }

    //TOO:PARTICLES ARE FUCKE UP!!!!!

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape(state);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        Direction direction = state.getValue(FACING).getOpposite();
        double d0 = pos.getX() + 0.45;
        double d1 = pos.getY() + 0.7;
        double d2 = pos.getZ() + 0.5;
        level.addParticle(ParticleTypes.SMOKE, d0 + 0.2 * direction.getStepX(), d1 + 0.22, d2 + 0.2 * direction.getStepZ(), 0.0, 0.0, 0.0);
        level.addParticle(this.flameParticle, d0 + 0.2 * direction.getStepX(), d1 + 0.22, d2 + 0.2 * direction.getStepZ(), 0.0, 0.0, 0.0);
    }
}
