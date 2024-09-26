package com.farcr.nomansland.common.block.cauldrons;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.EffectCures;
import net.neoforged.neoforge.event.EventHooks;

import java.util.ArrayList;
import java.util.Iterator;

public class MilkCauldron extends NMLCauldronBlock {
    public MilkCauldron() {
        super(NMLCauldronType.MILK);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (player.isHolding(Items.BUCKET) && isFull(state)) {
            if (!(player.isCreative() && player.getInventory().hasAnyMatching(s -> s.getItem() == Items.MILK_BUCKET))) {
                ItemStack item = new ItemStack(Items.MILK_BUCKET);
                if (!player.isCreative()) player.setItemInHand(hand, new ItemStack(player.getItemInHand(hand).getItem(), player.getItemInHand(hand).getCount()-1));
                player.addItem(item);
            }
            player.awardStat(Stats.ITEM_USED.get(Items.BUCKET));
            level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state));
            level.playSound(player, pos, SoundEvents.BUCKET_FILL, SoundSource.PLAYERS, 1, 1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        } else if (player.isHolding(Items.MILK_BUCKET) && !isFull(state)) {
            if (!player.isCreative()) player.setItemInHand(hand, new ItemStack(Items.BUCKET));
            if (player.isCreative() && !player.getInventory().hasAnyMatching(s -> s.getItem() == Items.BUCKET)) {
                ItemStack item = new ItemStack(Items.BUCKET);
                player.addItem(item);
            }
            player.awardStat(Stats.ITEM_USED.get(Items.MILK_BUCKET));
            level.setBlockAndUpdate(pos, state.setValue(LEVEL, 3));
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state));
            level.playSound(player, pos, SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS, 1, 1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        } else if (player.getItemInHand(hand).isEmpty()) {
            Iterator<MobEffectInstance> itr = player.getActiveEffects().iterator();
            ArrayList<Holder<MobEffect>> compatibleEffects = new ArrayList<>();

            MobEffectInstance selectedEffect;
            while(itr.hasNext()) {
                selectedEffect = itr.next();
                if (selectedEffect.getCures().contains(EffectCures.MILK)) {
                    compatibleEffects.add(selectedEffect.getEffect());
                }
            }

            if (!compatibleEffects.isEmpty()) {
                selectedEffect = player.getEffect(compatibleEffects.get(level.random.nextInt(compatibleEffects.size())));
                if (selectedEffect != null && !EventHooks.onEffectRemoved(player, selectedEffect, EffectCures.MILK)) {
                    player.removeEffect(selectedEffect.getEffect());
                }
            }
            level.playSound(player, pos, SoundEvents.WANDERING_TRADER_DRINK_MILK, SoundSource.PLAYERS, 0.5F, 1);
            lowerFillLevel(state, level, pos);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        return ItemInteractionResult.FAIL;
    }
}
