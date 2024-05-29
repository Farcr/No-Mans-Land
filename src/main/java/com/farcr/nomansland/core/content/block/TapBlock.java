package com.farcr.nomansland.core.content.block;

import com.farcr.nomansland.core.content.blockentity.TapBlockEntity;
import com.farcr.nomansland.core.registry.NMLBlockEntities;
import com.farcr.nomansland.core.registry.NMLBlocks;
import com.farcr.nomansland.core.registry.NMLParticleTypes;
import com.farcr.nomansland.core.registry.NMLTags;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;

import static net.minecraft.world.level.block.BeehiveBlock.HONEY_LEVEL;

public class TapBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    enum FluidType {
        RESIN,
        HONEY
    }

    public TapBlock(Properties pProperties) {
        super(pProperties);

    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockstate = this.defaultBlockState();
        LevelReader levelreader = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        Direction[] adirection = pContext.getNearestLookingDirections();

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

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, Block.box(6.0D, 3.0D, 10.0D, 10.0D, 8.0D, 16.0D),
            Direction.SOUTH, Block.box(6.0D, 3.0D, 0.0D, 10.0D, 8.0D, 6.0D),
            Direction.WEST, Block.box(10.0D, 3.0D, 6.0D, 16.0D, 8.0D, 10.0D),
            Direction.EAST, Block.box(0.0D, 3.0D, 6.0D, 6.0D, 8.0D, 10.0D)
    ));

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return getShape(pState);
    }

    public static VoxelShape getShape(BlockState pState) {
        return AABBS.get(pState.getValue(FACING));
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.relative(pState.getValue(FACING).getOpposite())).isSolid();
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing.getOpposite() == pState.getValue(FACING) && !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    public static BlockState getBlockStateBehind(Level level, BlockPos pos, BlockState state) {
        BlockState levelBlockStateBehind = level.getBlockState(pos.relative(state.getValue(FACING).getOpposite()));
        return levelBlockStateBehind;
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    public float getTickingChance() {
        // TODO: check if this is for resin or something else, as different fluids could have different rates
        // The ticking chance is the chance that one tick will actually go through
        return 0.1F;
    }

    public static BlockPos getCauldronPos(Level level, BlockPos tapPos) {
        // Get the position and state of the closest cauldron downwards (up to a max of 3 blocks down)
        for (int i = 0; i <= 3; i++) {
            BlockState blockUnder = level.getBlockState(tapPos.relative(Direction.DOWN, i));
            if (blockUnder.getBlock() instanceof AbstractCauldronBlock) {
                return tapPos.relative(Direction.DOWN, i);
            }
        }
        return null;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Ensure only 0.3 of ticks actually cause the function to run

        // Ensure there is a cauldron within 3 blocks under the tap
        BlockPos cauldronPos = getCauldronPos(level, pos);
        if(cauldronPos == null) return;
        BlockState cauldronState = level.getBlockState(cauldronPos);

        BlockState blockBehindState = getBlockStateBehind(level, pos, state);

        if (blockBehindState.getBlock() instanceof BeehiveBlock && blockBehindState.getValue(HONEY_LEVEL) == 5) {
            boolean honeyConsumed = false;
            if (cauldronState.getBlock() instanceof HoneyCauldronBlock cauldron) {
                if (!cauldron.isFull(cauldronState)) {
                    honeyConsumed = true;
                    cauldron.fillUp(cauldronState, level, cauldronPos);
                }
            } else if (cauldronState.getBlock() instanceof CauldronBlock) {
                honeyConsumed = true;
                level.setBlockAndUpdate(cauldronPos, NMLBlocks.HONEY_CAULDRON.get().defaultBlockState());
                level.gameEvent(GameEvent.BLOCK_CHANGE, cauldronPos, GameEvent.Context.of(cauldronState));
            }
            if (!honeyConsumed) return;
            BlockPos beehivePos = pos.relative(state.getValue(FACING).getOpposite());
            level.setBlockAndUpdate(beehivePos, blockBehindState.setValue(HONEY_LEVEL, 0));
            level.gameEvent(GameEvent.BLOCK_CHANGE, beehivePos, GameEvent.Context.of(blockBehindState));
        }

        // Set ticking speed for resin
        if(random.nextFloat() > getTickingChance()) return;

        // Fill selected cauldron with resin
        tryResin(blockBehindState, cauldronState, cauldronPos, level);
    }

    public void tryResin(BlockState blockBehindState, BlockState cauldronState, BlockPos cauldronPos, Level level) {
        if (blockBehindState.is(NMLTags.CONIFEROUS_LOGS)) {
            if (cauldronState.getBlock() instanceof ResinCauldronBlock cauldron) {
                if (!cauldron.isFull(cauldronState)) cauldron.fillUp(cauldronState, level, cauldronPos);
            } else if (cauldronState.getBlock() instanceof CauldronBlock) {
                level.setBlockAndUpdate(cauldronPos, NMLBlocks.RESIN_CAULDRON.get().defaultBlockState());
                level.gameEvent(GameEvent.BLOCK_CHANGE, cauldronPos, GameEvent.Context.of(cauldronState));
            }
        }
    }

    private static void spawnDrippingParticles(Level pLevel, BlockPos pPos, BlockState pState, FluidType fluidType) {
        double x = pPos.getX();
        double y = pPos.getY();
        double z = pPos.getZ();
        switch (pState.getValue(FACING)) {
            case NORTH: {
                x = (double)pPos.getX()+0.5;
                y = (double)((float)(pPos.getY()+1) - 0.75F) - 0.07;
                z = (double)pPos.getZ()+0.68;
                break;
            }
            case SOUTH: {
                x = (double)pPos.getX()+0.5;
                y = (double)((float)(pPos.getY()+1) - 0.75F) - 0.07;
                z = (double)pPos.getZ()+0.32;
                break;
            }
            case EAST: {
                x = (double)pPos.getX()+0.32;
                y = (double)((float)(pPos.getY()+1) - 0.75F) - 0.07;
                z = (double)pPos.getZ()+0.5;
                break;
            }
            case WEST: {
                x = (double)pPos.getX()+0.68;
                y = (double)((float)(pPos.getY()+1) - 0.75F) - 0.07;
                z = (double)pPos.getZ()+0.5;
                break;
            }
        }
        ParticleOptions particle = null;
        if (fluidType.equals(FluidType.RESIN)) particle = (ParticleOptions) NMLParticleTypes.RESIN_DROPLET.get();
        if (fluidType.equals(FluidType.HONEY)) particle = ParticleTypes.DRIPPING_HONEY;
        pLevel.addParticle(particle, x, y, z, 0.0, 0.0, 0.0);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        BlockState blockStateBehind = getBlockStateBehind(level, pos, state);
        if (blockStateBehind.is(NMLTags.CONIFEROUS_LOGS)) {
            if (random.nextFloat() <= 0.05F) {
                spawnDrippingParticles(level, pos, state, FluidType.RESIN);
            }
        } else if (blockStateBehind.getBlock() instanceof BeehiveBlock && blockStateBehind.getValue(HONEY_LEVEL) > 0) {
            if (random.nextFloat() <= (0.05F * blockStateBehind.getValue(HONEY_LEVEL))) {
                spawnDrippingParticles(level, pos, state, FluidType.HONEY);
            }
        }
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TapBlockEntity(blockPos, blockState);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
        return createTickerHelper(blockEntity, NMLBlockEntities.TAP.get(), TapBlockEntity::tick);
    }
}
