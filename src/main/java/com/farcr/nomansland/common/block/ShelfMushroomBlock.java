package com.farcr.nomansland.common.block;

import com.farcr.nomansland.common.registry.NMLTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseCoralWallFanBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ShelfMushroomBlock extends BaseCoralWallFanBlock {
    public ShelfMushroomBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!(player.isCreative() && player.getInventory().hasAnyMatching(stack -> stack.getItem() == this.asItem()))) {
            ItemStack item = new ItemStack(this);
            if (!player.addItem(item) ) {
                player.drop(item, false);
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

    public static boolean isAcceptableNeighbour(BlockGetter blockReader, BlockPos neighborPos) {
        BlockState blockstate = blockReader.getBlockState(neighborPos);
        return blockstate.is(BlockTags.LOGS) || blockstate.is(NMLTags.MUSHROOM_BLOCKS);
    }

    //TODO: Change bounding box
    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos blockpos = pos.relative(direction.getOpposite());
        BlockState blockstate = level.getBlockState(blockpos);
        return blockstate.is(BlockTags.LOGS) || blockstate.is(NMLTags.MUSHROOM_BLOCKS);
    }
}
