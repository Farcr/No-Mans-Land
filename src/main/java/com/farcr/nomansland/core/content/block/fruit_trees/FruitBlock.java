package com.farcr.nomansland.core.content.block.fruit_trees;

import com.farcr.nomansland.core.registry.NMLBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Set;
import java.util.function.Supplier;

public class FruitBlock extends BushBlock {
    public static final int MAX_AGE = 4;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;
    private final VoxelShape[] shapesByAge;
    private final Holder<Block> fruitLeaves;
    private final ItemStack fruitDrops;

    public FruitBlock(Properties properties, FruitType fruitType) {
        super(properties);
        this.shapesByAge = fruitType.getShapesByAge();
        this.fruitLeaves = fruitType.getFruitLeaves();
        this.fruitDrops = fruitType.getFruitDrops();
        registerDefaultState(stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return null;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos.above(2)).is(fruitLeaves.value());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Vec3 vec3 = state.getOffset(level, pos);
        VoxelShape voxelshape = shapesByAge[state.getValue(AGE)];
        return voxelshape.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    protected VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return super.getOcclusionShape(state, level, pos);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (state.getValue(AGE) != getMaxAge()) return InteractionResult.PASS;
        if (!(player.isCreative() && player.getInventory().hasAnyMatching(stack -> stack.getItem() == fruitDrops.getItem()))) {
            if (!player.addItem(fruitDrops) ) {
                player.drop(fruitDrops, false);
            } else {
                level.playSound(player,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.ITEM_PICKUP,
                        SoundSource.PLAYERS,
                        0.2F,
                        (level.random.nextFloat() - level.random.nextFloat()) * 1.4F + 2.0F);
            }
        } else {
            level.playSound(player,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.ITEM_PICKUP,
                    SoundSource.PLAYERS,
                    0.2F,
                    (level.random.nextFloat() - level.random.nextFloat()) * 1.4F + 2.0F);
        }
        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    public int getMaxAge() {
        return 4;
    }

    @Override
    protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (state.getValue(AGE) != getMaxAge()) return;
        level.destroyBlock(hit.getBlockPos(), true);
    }
}
