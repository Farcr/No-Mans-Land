package com.farcr.nomansland.common.mixin;

import com.farcr.nomansland.common.registry.NMLBlocks;
import com.farcr.nomansland.common.registry.NMLItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.world.level.block.LayeredCauldronBlock.LEVEL;

@Mixin(AbstractCauldronBlock.class)
public class AbstractCauldronBlockMixin {

    @Inject(method = "useItemOn", at = @At("HEAD"), cancellable = true)
    private void injected(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<ItemInteractionResult> cir) {
        if (state.getBlock() instanceof CauldronBlock) {
            if (player.isHolding(Items.HONEY_BOTTLE)) {
                player.setItemInHand(hand, ItemUtils.createFilledResult(player.getItemInHand(hand), player, new ItemStack(Items.GLASS_BOTTLE)));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(Items.HONEY_BOTTLE));
                level.setBlockAndUpdate(pos, NMLBlocks.HONEY_CAULDRON.get().defaultBlockState());
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state));
                level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                cir.setReturnValue(ItemInteractionResult.sidedSuccess(level.isClientSide));
            }
            if (player.isHolding(NMLItems.MAPLE_SYRUP_BOTTLE.get())) {
                player.setItemInHand(hand, ItemUtils.createFilledResult(player.getItemInHand(hand), player, new ItemStack(Items.GLASS_BOTTLE)));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(NMLItems.MAPLE_SYRUP_BOTTLE.get()));
                level.setBlockAndUpdate(pos, NMLBlocks.MAPLE_SYRUP_CAULDRON.get().defaultBlockState());
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state));
                level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                cir.setReturnValue(ItemInteractionResult.sidedSuccess(level.isClientSide));
            }
            if (player.isHolding(NMLItems.RESIN_OIL_BOTTLE.get())) {
                player.setItemInHand(hand, ItemUtils.createFilledResult(player.getItemInHand(hand), player, new ItemStack(Items.GLASS_BOTTLE)));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(NMLItems.RESIN_OIL_BOTTLE.get()));
                level.setBlockAndUpdate(pos, NMLBlocks.RESIN_OIL_CAULDRON.get().defaultBlockState());
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state));
                level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                cir.setReturnValue(ItemInteractionResult.sidedSuccess(level.isClientSide));
            }
            if (player.isHolding(Items.MILK_BUCKET)) {
                if (!player.isCreative()) player.setItemInHand(hand, new ItemStack(Items.BUCKET));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(Items.MILK_BUCKET));
                level.setBlockAndUpdate(pos, NMLBlocks.MILK_CAULDRON.get().defaultBlockState().setValue(LEVEL, 3));
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state));
                level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                cir.setReturnValue(ItemInteractionResult.sidedSuccess(level.isClientSide));
            }
            if (player.isHolding(NMLItems.RESIN.get()) && (player.isCreative() || player.getItemInHand(hand).getCount() >= 3)) {
                if (!player.isCreative()) player.setItemInHand(hand, new ItemStack(player.getItemInHand(hand).getItemHolder(), player.getItemInHand(hand).getCount()-3));
                player.awardStat(Stats.USE_CAULDRON);
                level.setBlockAndUpdate(pos, NMLBlocks.RESIN_CAULDRON.get().defaultBlockState());
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state));
                level.playSound(null, pos, SoundEvents.HONEY_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                cir.setReturnValue(ItemInteractionResult.sidedSuccess(level.isClientSide));
            }
        }
    }
}
