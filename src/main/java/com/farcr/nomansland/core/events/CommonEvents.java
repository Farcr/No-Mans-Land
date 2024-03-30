package com.farcr.nomansland.core.events;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CommonEvents {
    @Mod.EventBusSubscriber(modid = NoMansLand.MODID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
            Level level = event.getLevel();
            BlockPos pos = event.getPos();
            BlockState state = level.getBlockState(pos);
            Player player = event.getEntity();
            ItemStack stack = event.getItemStack();
            if (event.getFace() != Direction.DOWN && stack.canPerformAction(ToolActions.SHOVEL_FLATTEN) && !player.isSpectator() && level.isEmptyBlock(pos.above())) {
                if (state.is(Blocks.PODZOL) || state.is(Blocks.MYCELIUM) || state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.ROOTED_DIRT)) {
                    level.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                    if (!level.isClientSide) {
                        stack.hurtAndBreak(1, player, (damage) -> {
                            damage.broadcastBreakEvent(event.getHand());
                        });
                        level.setBlock(pos, state.is(Blocks.PODZOL) ? NMLBlocks.PODZOL_PATH.get().defaultBlockState() : state.is(Blocks.MYCELIUM) ? NMLBlocks.MYCELIUM_PATH.get().defaultBlockState() : NMLBlocks.DIRT_PATH.get().defaultBlockState(), 11);
                    }
                    event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                    event.setCanceled(true);
                }
            } else if (stack.canPerformAction(ToolActions.HOE_TILL) && state.is(NMLBlocks.DIRT_PATH.get()) && !player.isSpectator() && level.isEmptyBlock(pos.above())) {
                level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!level.isClientSide()) {
                    stack.hurtAndBreak(1, player, (damage) -> {
                        damage.broadcastBreakEvent(event.getHand());
                    });
                    level.setBlock(pos, Blocks.FARMLAND.defaultBlockState(), 11);
                }
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                event.setCanceled(true);
            }
        }

        @Mod.EventBusSubscriber(modid = NoMansLand.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class ModEventBusEvents {
            @SubscribeEvent
            public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            }

            @SubscribeEvent
            public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            }
        }
    }

}
