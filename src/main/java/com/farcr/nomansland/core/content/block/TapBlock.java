package com.farcr.nomansland.core.content.block;

import com.farcr.nomansland.core.config.NMLConfig;
import com.farcr.nomansland.core.content.block.cauldrons.NMLCauldronBlock;
import com.farcr.nomansland.core.content.blockentity.TapBlockEntity;
import com.farcr.nomansland.core.registry.NMLBlockEntities;
import com.farcr.nomansland.core.registry.NMLBlocks;
import com.farcr.nomansland.core.registry.NMLParticleTypes;
import com.farcr.nomansland.core.registry.NMLTags;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;

import static net.minecraft.world.level.block.BeehiveBlock.HONEY_LEVEL;

public class TapBlock extends BaseEntityBlock {
    public static final MapCodec<TapBlock> CODEC = simpleCodec(TapBlock::new);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, Block.box(6.0D, 3.0D, 10.0D, 10.0D, 8.0D, 16.0D),
            Direction.SOUTH, Block.box(6.0D, 3.0D, 0.0D, 10.0D, 8.0D, 6.0D),
            Direction.WEST, Block.box(10.0D, 3.0D, 6.0D, 16.0D, 8.0D, 10.0D),
            Direction.EAST, Block.box(0.0D, 3.0D, 6.0D, 6.0D, 8.0D, 10.0D)
    ));

    public TapBlock(Properties pProperties) {
        super(pProperties);

    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    public static VoxelShape getShape(BlockState state) {
        return AABBS.get(state.getValue(FACING));
    }

    public static BlockState getBlockStateBehind(Level level, BlockPos pos, BlockState state) {
        return level.getBlockState(pos.relative(state.getValue(FACING).getOpposite()));
    }

    public static BlockPos getCauldronPos(Level level, BlockPos tapPos) {
        // Get the position and state of the closest cauldron downwards (up to a max of 3 blocks down)
        for (int i = 0; i <= 3; i++) {
            BlockPos posBelow = tapPos.below(i);
            BlockState stateBelow = level.getBlockState(posBelow);
            if (stateBelow.getBlock() instanceof AbstractCauldronBlock) {
                return posBelow;
            } else if (stateBelow.isCollisionShapeFullBlock(level, posBelow)) return null;
        }
        return null;
    }

    public static void spawnDrippingParticles(Level level, BlockPos pos, BlockState state, SimpleParticleType particleType) {
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        switch (state.getValue(FACING)) {
            case NORTH: {
                x = (double) pos.getX() + 0.5;
                y = (double) ((float) (pos.getY() + 1) - 0.75F) - 0.09;
                z = (double) pos.getZ() + 0.68;
                break;
            }
            case SOUTH: {
                x = (double) pos.getX() + 0.5;
                y = (double) ((float) (pos.getY() + 1) - 0.75F) - 0.09;
                z = (double) pos.getZ() + 0.32;
                break;
            }
            case EAST: {
                x = (double) pos.getX() + 0.32;
                y = (double) ((float) (pos.getY() + 1) - 0.75F) - 0.09;
                z = (double) pos.getZ() + 0.5;
                break;
            }
            case WEST: {
                x = (double) pos.getX() + 0.68;
                y = (double) ((float) (pos.getY() + 1) - 0.75F) - 0.09;
                z = (double) pos.getZ() + 0.5;
                break;
            }
        }
        ParticleOptions particle = particleType;
        if (level.isClientSide) level.addParticle(particle, x, y, z, 0.0, 0.0, 0.0);
        else {
            ServerLevel serverLevel = (ServerLevel) level;
            serverLevel.sendParticles(particle, x, y, z, 1, 0, 0, 0, 0);
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = this.defaultBlockState();
        LevelReader levelreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Direction[] adirection = context.getNearestLookingDirections();

        for (Direction direction : adirection) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction1 = direction.getOpposite();
                blockstate = blockstate.setValue(FACING, direction1);
                if (blockstate.canSurvive(levelreader, blockpos)) {
                    return blockstate;
                }
            }
        }

        return null;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape(state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState stateBehind = level.getBlockState(pos.relative(state.getValue(FACING).getOpposite()));
        return stateBehind.isCollisionShapeFullBlock(level, pos) || stateBehind.getBlock() instanceof AbstractCauldronBlock;
    }

    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return facing.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    public BlockState rotate(BlockState state, Rotation pRotation) {
        return state.setValue(FACING, pRotation.rotate(state.getValue(FACING)));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Ensure only 0.3 of ticks actually cause the function to run

        // Ensure there is a cauldron within 3 blocks under the tap
        BlockPos cauldronPos = getCauldronPos(level, pos);
        if (cauldronPos == null) return;
        BlockState cauldronState = level.getBlockState(cauldronPos);

        BlockState stateBehind = getBlockStateBehind(level, pos, state);

        // Fill selected cauldron with resin
        if (stateBehind.is(NMLTags.CONIFEROUS_LOGS)) tryResin(cauldronState, cauldronPos, level, random);
        if (stateBehind.is(NMLBlocks.MAPLE_LOG)) tryMaple(cauldronState, cauldronPos, level, random);
    }

    public void tryResin(BlockState cauldronState, BlockPos cauldronPos, Level level, RandomSource random) {
            if (cauldronState.getBlock() instanceof NMLCauldronBlock cauldron && cauldron.getCauldronBlock() == NMLBlocks.RESIN_CAULDRON
                    && !cauldron.isFull(cauldronState) && random.nextFloat() < 0.1F * NMLConfig.FILLING_SPEED_MULTIPLIER.get()) {
                cauldron.fillUp(cauldronState, level, cauldronPos);
            } else if (cauldronState.getBlock() instanceof CauldronBlock) {
                level.setBlockAndUpdate(cauldronPos, NMLBlocks.RESIN_CAULDRON.get().defaultBlockState());
                level.gameEvent(GameEvent.BLOCK_CHANGE, cauldronPos, GameEvent.Context.of(cauldronState));
            }
    }

    public void tryMaple(BlockState cauldronState, BlockPos cauldronPos, Level level, RandomSource random) {
        if (cauldronState.getBlock() instanceof NMLCauldronBlock cauldron && cauldron.getCauldronBlock() == NMLBlocks.MAPLE_SYRUP_CAULDRON &&
                !cauldron.isFull(cauldronState) && random.nextFloat() < 0.1F * NMLConfig.FILLING_SPEED_MULTIPLIER.get()) {
            cauldron.fillUp(cauldronState, level, cauldronPos);
        } else if (cauldronState.getBlock() instanceof CauldronBlock) {
            level.setBlockAndUpdate(cauldronPos, NMLBlocks.MAPLE_SYRUP_CAULDRON.get().defaultBlockState());
            level.gameEvent(GameEvent.BLOCK_CHANGE, cauldronPos, GameEvent.Context.of(cauldronState));
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        BlockState stateBehind = getBlockStateBehind(level, pos, state);
        if (stateBehind.is(NMLTags.CONIFEROUS_LOGS)) {
            if (random.nextFloat() <= 0.05F) {
                spawnDrippingParticles(level, pos, state, NMLParticleTypes.RESIN_DROPLET.get());
            }
        }
        if (stateBehind.hasProperty(HONEY_LEVEL) && stateBehind.getValue(HONEY_LEVEL) > 0) {
            if (random.nextFloat() <= (0.05F * stateBehind.getValue(HONEY_LEVEL))) {
                spawnDrippingParticles(level, pos, state, ParticleTypes.FALLING_HONEY);
            }
        }
        if (stateBehind.is(NMLBlocks.MAPLE_LOG)) {
            if (random.nextFloat() <= 0.05F) {
                spawnDrippingParticles(level, pos, state, NMLParticleTypes.MAPLE_SYRUP_DROPLET.get());
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TapBlockEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
        return level.isClientSide ? null : createTickerHelper(blockEntity, NMLBlockEntities.TAP.get(), TapBlockEntity::tick);
    }
}
