package com.farcr.nomansland.core.content.block.fruit_trees;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class FruitBlock extends BushBlock {
    public static final int MAX_AGE = 4;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(6, 10, 7, 10, 14, 11),
            Block.box(6, 10, 7, 10, 14, 11),
            Block.box(5.5, 9, 6.5, 10.5, 14, 11.5),
            Block.box(5.5, 9, 6.5, 10.5, 14, 11.5),
            Block.box(5.5, 9, 6.5, 10.5, 14, 11.5)
            };
    public Supplier<? extends Block> fruitLeaves;
    public ItemStack fruitItem;

    public FruitBlock(Properties properties, FruitType fruitType) {
        super(properties);
        this.fruitLeaves = fruitType.getFruitLeaves();
        this.fruitItem = fruitType.getFruitDrops();
        registerDefaultState(stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return null;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos.above(2)).is(fruitLeaves.get());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    protected VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return super.getOcclusionShape(state, level, pos);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (state.getValue(AGE) != getMaxAge()) return InteractionResult.PASS;
        ItemStack fruit = new ItemStack(Items.APPLE);
        if (!player.addItem(fruit)) {
            if (!player.isCreative()) player.drop(fruit, false);
        }
        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.2F, (level.random.nextFloat() - level.random.nextFloat()) * 1.4F + 2.0F);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    public int getMaxAge() {
        return 4;
    }

    protected static int getGrowthSpeed() {
        return 3;
    }
}
