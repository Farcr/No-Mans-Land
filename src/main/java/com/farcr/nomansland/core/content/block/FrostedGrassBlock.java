package com.farcr.nomansland.core.content.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import static net.minecraft.world.level.block.SnowyDirtBlock.SNOWY;

public class FrostedGrassBlock extends BushBlock {
    public static final BooleanProperty SNOWLOGGED = BooleanProperty.create("snowlogged");
    protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 13.0D, 15.0D);
    //TODO: change shape when snowy
    public FrostedGrassBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(SNOWLOGGED, Boolean.valueOf(false)));
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (player.getMainHandItem().is(Blocks.SNOW.asItem()) && !state.getValue(SNOWLOGGED)) {
            level.setBlockAndUpdate(pos,state.setValue(SNOWLOGGED, true ));
            stack.consume(1, player);
            level.playSound(player, pos, SoundEvents.SNOW_PLACE, SoundSource.PLAYERS, 1.0F, (level.random.nextFloat() - level.random.nextFloat()) * 0.6F + 1.2F);
            BlockState stateUnder = level.getBlockState(pos.relative(Direction.DOWN));
            if (stateUnder.getBlock() instanceof SnowyDirtBlock)
                level.setBlockAndUpdate(pos,stateUnder.setValue(SNOWY, true ));
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        return ItemInteractionResult.FAIL;
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        if (useContext.getItemInHand().is(Blocks.SNOW.asItem())) {
            return false;
        }
        return super.canBeReplaced(state, useContext);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return null;
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(SNOWLOGGED);
    }
}
