package com.farcr.nomansland.core.content.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
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
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

import static net.minecraft.world.level.block.SnowyDirtBlock.SNOWY;

public class FrostedGrassBlock extends BushBlock {
    public static final BooleanProperty SNOWLOGGED = BooleanProperty.create("snowlogged");
    protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);
    protected static final VoxelShape SNOWLOGGED_SHAPE = Shapes.or(SHAPE, Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0));

    //TODO: change shape when snowy
    public FrostedGrassBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(SNOWLOGGED, Boolean.FALSE));
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (player.getMainHandItem().is(Blocks.SNOW.asItem()) && !state.getValue(SNOWLOGGED)) {
            level.setBlockAndUpdate(pos, state.setValue(SNOWLOGGED, true));
            stack.consume(1, player);
            level.playSound(player, pos, SoundEvents.SNOW_PLACE, SoundSource.PLAYERS, 1.0F, (level.random.nextFloat() - level.random.nextFloat()) * 0.6F + 1.2F);
            BlockPos posBelow = pos.below();
            BlockState stateUnder = level.getBlockState(posBelow);
            if (stateUnder.getBlock() instanceof SnowyDirtBlock)
                level.setBlockAndUpdate(posBelow, stateUnder.setValue(SNOWY, true));
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        return ItemInteractionResult.FAIL;
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        if (useContext.getItemInHand().is(Blocks.SNOW.asItem()) || useContext.getItemInHand().is(Blocks.SHORT_GRASS.asItem())) {
            return false;
        }
        return super.canBeReplaced(state, useContext);
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(SNOWLOGGED) ? SNOWLOGGED_SHAPE : SHAPE;
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SNOWLOGGED);
    }
}
