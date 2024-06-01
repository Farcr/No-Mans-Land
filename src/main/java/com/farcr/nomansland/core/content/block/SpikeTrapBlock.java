package com.farcr.nomansland.core.content.block;

import com.farcr.nomansland.core.registry.NMLDamageTypes;
import com.farcr.nomansland.core.registry.NMLSounds;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

import static java.lang.Boolean.FALSE;

public class SpikeTrapBlock extends DirectionalBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape EAST_POWERED_AABB = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_POWERED_AABB = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_POWERED_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    protected static final VoxelShape NORTH_POWERED_AABB = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape UP_POWERED_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    protected static final VoxelShape DOWN_POWERED_AABB = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 9.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_AABB = Block.box(7.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 9.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 7.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape UP_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);
    protected static final VoxelShape DOWN_AABB = Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D);



    public SpikeTrapBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.valueOf(false))
                .setValue(FACING, Direction.UP).setValue(POWERED, Boolean.valueOf(true)));
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return null;
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(POWERED)) {
            return switch (pState.getValue(FACING)) {
                default -> UP_POWERED_AABB;
                case NORTH -> NORTH_POWERED_AABB;
                case SOUTH -> SOUTH_POWERED_AABB;
                case WEST -> WEST_POWERED_AABB;
                case EAST -> EAST_POWERED_AABB;
                case DOWN -> DOWN_POWERED_AABB;
            };
        } else return switch (pState.getValue(FACING)) {
            default -> UP_AABB;
            case NORTH -> NORTH_AABB;
            case SOUTH -> SOUTH_AABB;
            case WEST -> WEST_AABB;
            case EAST -> EAST_AABB;
            case DOWN -> DOWN_AABB;
        };
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
            if (pState.getValue(WATERLOGGED)) {
                pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
            }
            return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
        }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, POWERED, WATERLOGGED);
    }

    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getClickedFace()).setValue(POWERED, FALSE);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean b) {
        if (!neighborBlock.equals(state.getBlock())) {
            if (!level.isClientSide) {
                this.checkIfPowered(level, pos, state);
            }
        }
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof Player player && player.isCreative()) return;
        if (state.getValue(POWERED)) return;
        if (entity instanceof LivingEntity le && entity.isAlive()) {
            boolean up = state.getValue(FACING) == Direction.UP;
            //does not reset fall distance
            float fall = entity.fallDistance;
//            entity.makeStuckInBlock(state, new Vec3(0.95, 1, 0.95));
//            entity.fallDistance = fall;

//            if ((entity.getY() < pos.getY()+0.3 && up) || ())
//                le.moveTo(le.getX(), le.getY()+0.4, le.getZ());

            if (!level.isClientSide) {
                if (up && entity instanceof Player && entity.isShiftKeyDown()) return;
                entity.hurt(NMLDamageTypes.getSimpleDamageSource(level, NMLDamageTypes.SPIKE), 1.5f);
            }
        }
    }

    // TODO: fix wonky behaviour (block entity?)
    private void checkIfPowered(Level level, BlockPos pos, BlockState state) {
        boolean flag = false;
        for (Direction d : Direction.values()) {
            if (level.hasSignal(pos.relative(d), d)) {
                flag = true;
                break;
            }
        }
        if (flag && !state.getValue(POWERED)) {
            level.setBlockAndUpdate(pos, state.setValue(POWERED, true));
            level.playSound(null, pos, NMLSounds.SPIKE_TRAP_INACTIVE.get(), SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.2F + 0.6F);
            level.gameEvent(GameEvent.BLOCK_DEACTIVATE, pos, GameEvent.Context.of(state));
        } else if (!flag && state.getValue(POWERED)) {
            level.setBlockAndUpdate(pos, state.setValue(POWERED, false));
            level.playSound(null, pos, NMLSounds.SPIKE_TRAP_ACTIVE.get(), SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.2F + 0.6F);
            level.gameEvent(GameEvent.BLOCK_ACTIVATE, pos, GameEvent.Context.of(state));
            Predicate<LivingEntity> livingEntityPredicate = LivingEntity::isAlive;
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, new AABB(pos), livingEntityPredicate);
            if(!entities.isEmpty()) entities.forEach(entity -> entity.hurt(NMLDamageTypes.getSimpleDamageSource(level, NMLDamageTypes.SPIKE), 12));
        }
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallingDistance) {
        if (state.getValue(FACING) == Direction.UP) {
            entity.causeFallDamage(fallingDistance + 2.0F, 2.0F, NMLDamageTypes.getSimpleDamageSource(level, NMLDamageTypes.SPIKE));
        } else {
            super.fallOn(level, state, pos, entity, fallingDistance);
        }
    }

    public void onPlace(BlockState newState, Level level, BlockPos pos, BlockState state, boolean p_60229_) {
        if (!state.is(newState.getBlock())) {
            if (!level.isClientSide) {
                this.checkIfPowered(level, pos, newState);
            }
        }
    }

    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        return true;
    }

    @Override
    public boolean isPathfindable(BlockState p_154112_, BlockGetter p_154113_, BlockPos p_154114_, PathComputationType p_154115_) {
        return false;
    }
}
