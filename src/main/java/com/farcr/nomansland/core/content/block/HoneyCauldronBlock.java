package com.farcr.nomansland.core.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.Map;
import java.util.function.Predicate;

public class HoneyCauldronBlock extends LayeredCauldronBlock {
    public HoneyCauldronBlock(Properties pProperties, Predicate<Biome.Precipitation> pFillPredicate, Map<Item, CauldronInteraction> pInteractions) {
        super(pProperties, pFillPredicate, pInteractions);
    }

    public void fillUp(BlockState state, Level level, BlockPos pos) {
        int additionalLevel = state.getValue(LEVEL) + 1;
        BlockState newState = additionalLevel == 0 ? Blocks.CAULDRON.defaultBlockState() : state.setValue(LEVEL, additionalLevel);
        level.setBlockAndUpdate(pos, newState);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newState));
    }

    @Override
    protected boolean canReceiveStalactiteDrip(Fluid pFluid) {
        return false;
    }
    @Override
    public void handlePrecipitation(BlockState pState, Level pLevel, BlockPos pPos, Biome.Precipitation pPrecipitation) {
    }
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack honey = new ItemStack(Items.HONEY_BOTTLE);
        // Give player honey block if cauldron is full
        if (pPlayer.isHolding(Items.GLASS_BOTTLE)) {
            if (!pPlayer.addItem(honey)) {
                pPlayer.drop(honey, false);
            } else {
                pPlayer.setItemInHand(pHand, new ItemStack(Items.GLASS_BOTTLE, pPlayer.getItemInHand(pHand).getCount() - 1));
                pLevel.playSound(pPlayer,
                        pPlayer.getX(),
                        pPlayer.getY(),
                        pPlayer.getZ(),
                        SoundEvents.BOTTLE_FILL,
                        SoundSource.PLAYERS,
                        0.2F,
                        (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 1.4F + 2.0F);
                lowerFillLevel(pState, pLevel, pPos);
                return InteractionResult.sidedSuccess(pLevel.isClientSide);
            }
        } else if (pPlayer.isHolding(Items.HONEY_BOTTLE)) {
                pPlayer.setItemInHand(pHand, new ItemStack(Items.HONEY_BOTTLE, pPlayer.getItemInHand(pHand).getCount() - 1));
                pLevel.playSound(pPlayer,
                        pPlayer.getX(),
                        pPlayer.getY(),
                        pPlayer.getZ(),
                        SoundEvents.BOTTLE_EMPTY,
                        SoundSource.PLAYERS,
                        0.2F,
                        (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 1.4F + 2.0F);
                fillUp(pState, pLevel, pPos);
                return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
        return InteractionResult.FAIL;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return Blocks.CAULDRON.getCloneItemStack(state, target, level, pos, player);
    }
}
