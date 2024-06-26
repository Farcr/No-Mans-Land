package com.farcr.nomansland.core.content.mixin;

import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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

@Mixin(AbstractCauldronBlock.class)
public class AbstractCauldronBlockMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void injected(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> callback) {
        if (player.isHolding(Items.HONEY_BOTTLE) && state.getBlock() instanceof CauldronBlock) {
            player.setItemInHand(hand, ItemUtils.createFilledResult(player.getItemInHand(hand), player, new ItemStack(Items.GLASS_BOTTLE)));
            player.awardStat(Stats.USE_CAULDRON);
            player.awardStat(Stats.ITEM_USED.get(Items.HONEY_BOTTLE));
            level.setBlockAndUpdate(pos, NMLBlocks.HONEY_CAULDRON.get().defaultBlockState());
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state));
            level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            callback.setReturnValue(InteractionResult.sidedSuccess(level.isClientSide));
        }
    }
}
