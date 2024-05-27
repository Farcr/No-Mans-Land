package com.farcr.nomansland.core.content.block;

import com.farcr.nomansland.core.registry.NMLBlocks;
import com.farcr.nomansland.core.registry.NMLParticleTypes;
import com.farcr.nomansland.core.registry.NMLTags;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;

public class TapBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

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

    public BlockState getBlockStateBehind(Level level, BlockPos pos, BlockState state) {
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
        return 0.3F;
    }


    public static BlockPos getCauldronPos(Level level, BlockPos tapPos) {
        // Get the position and state of the closest cauldron downwards (up to a max of 3 blocks down)
        for (int i = 0; i <= 3; i++) {
            BlockState blockUnder = level.getBlockState(tapPos.relative(Direction.DOWN, i));
            if (blockUnder.getBlock() instanceof ResinCauldronBlock || blockUnder.getBlock() instanceof CauldronBlock) {
                return tapPos.relative(Direction.DOWN, i);
            }
        }
        return null;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Ensure only 0.3 of ticks actually cause the function to run
        if(random.nextFloat() > getTickingChance()) return;

        // Ensure there is a cauldron within 3 blocks under the tap
        BlockPos cauldronPos = getCauldronPos(level, pos);
        if(cauldronPos == null) return;
        BlockState cauldronState = level.getBlockState(cauldronPos);

        // Fill selected cauldron with resin
        if (cauldronState.getBlock() instanceof ResinCauldronBlock resinCauldronBlock) {
            if (!resinCauldronBlock.isFull(cauldronState)) resinCauldronBlock.receiveResin(cauldronState, level, cauldronPos);
        } else if (cauldronState.getBlock() instanceof CauldronBlock) {
            level.setBlockAndUpdate(cauldronPos, NMLBlocks.RESIN_CAULDRON.get().defaultBlockState());
            level.gameEvent(GameEvent.BLOCK_CHANGE, cauldronPos, GameEvent.Context.of(cauldronState));
        }
    }

    private static void spawnResinParticle(Level pLevel, BlockPos pPos, BlockState pState) {
        Vec3 offset = pState.getOffset(pLevel, pPos);
        double x = (double)pPos.getX() + 0.5 + offset.x;
        double y = (double)((float)(pPos.getY() + 1) - 0.75F) - 0.07;
        double z = (double)pPos.getZ() + 0.32 + offset.z;
        ParticleOptions particle = (ParticleOptions) NMLParticleTypes.RESIN_DROPLET.get();
        pLevel.addParticle(particle, x, y, z, 0.0, 0.0, 0.0);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        BlockState blockStateBehind = getBlockStateBehind(level, pos, state);
        if (blockStateBehind.is(NMLTags.CONIFEROUS_LOGS)) {
            if (random.nextFloat() <= 0.05F) {
                spawnResinParticle(level, pos, state);
            }
        }
    }
}
