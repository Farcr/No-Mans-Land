package com.farcr.nomansland.core.events;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class PathMakingEvents {
    @Mod.EventBusSubscriber(modid = NoMansLand.MODID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
            Level level = event.getLevel();
            BlockPos pos = event.getPos();
            BlockState state = level.getBlockState(pos);
            Player player = event.getEntity();
            ItemStack stack = event.getItemStack();

            //Dirt Paths
            if (event.getFace() != Direction.DOWN && stack.is(ItemTags.SHOVELS) && !player.isSpectator() && level.isEmptyBlock(pos.above())) {
                if (state.is(Blocks.PODZOL) ||
                        state.is(Blocks.MYCELIUM) ||
                        state.is(Blocks.SAND) ||
                        state.is(Blocks.RED_SAND) ||
                        state.is(Blocks.GRAVEL) ||
                        state.is(Blocks.DIRT) ||
                        state.is(Blocks.COARSE_DIRT) ||
                        state.is(Blocks.ROOTED_DIRT)) {
                    if (state.is(BlockTags.SAND)) {
                        level.playSound(player, pos, SoundEvents.SAND_FALL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    } else if (state.is(Blocks.GRAVEL)) {
                        level.playSound(player, pos, SoundEvents.GRAVEL_FALL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    } else {
                        level.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                    }
                    if (!level.isClientSide) {
                        stack.hurtAndBreak(1, player, (damage) -> {
                            damage.broadcastBreakEvent(event.getHand());
                        });
                        level.setBlockAndUpdate(pos,
                                state.is(Blocks.PODZOL) ? NMLBlocks.PODZOL_PATH.get().defaultBlockState() :
                                        state.is(Blocks.MYCELIUM) ? NMLBlocks.MYCELIUM_PATH.get().defaultBlockState() :
                                                state.is(Blocks.SAND) ? NMLBlocks.SAND_PATH.get().defaultBlockState() :
                                                        state.is(Blocks.RED_SAND) ? NMLBlocks.RED_SAND_PATH.get().defaultBlockState() :
                                                                state.is(Blocks.GRAVEL) ? NMLBlocks.GRAVEL_PATH.get().defaultBlockState() :
                                                                        NMLBlocks.DIRT_PATH.get().defaultBlockState());
                    }
                    event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                    event.setCanceled(true);
                }
            }

            //Snow Path (TODO: Add extra checks for snow layers on top)
            if (event.getFace() != Direction.DOWN && stack.is(ItemTags.SHOVELS) && !player.isSpectator() && level.isEmptyBlock(pos.above())) {
                if (state.is(Blocks.SNOW_BLOCK)) {
                    level.playSound(player, pos, SoundEvents.SNOW_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
                    if (!level.isClientSide) {
                        stack.hurtAndBreak(1, player, (damage) -> {
                            damage.broadcastBreakEvent(event.getHand());
                        });
                        level.setBlockAndUpdate(pos, NMLBlocks.SNOW_PATH.get().defaultBlockState());
                    }
                    event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                    event.setCanceled(true);
                }
            }

            //Dirt Path into Farmland
            if (stack.is(ItemTags.HOES) && state.is(NMLBlocks.DIRT_PATH.get()) && !player.isSpectator() && level.isEmptyBlock(pos.above())) {
                level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!level.isClientSide()) {
                    stack.hurtAndBreak(1, player, (damage) -> {
                        damage.broadcastBreakEvent(event.getHand());
                    });
                    level.setBlockAndUpdate(pos, Blocks.FARMLAND.defaultBlockState());
                }
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                event.setCanceled(true);
            }
            //Farmland untilling
            if (event.getFace() != Direction.DOWN && stack.is(ItemTags.SHOVELS) && state.is(Blocks.FARMLAND) && !player.isSpectator() && level.isEmptyBlock(pos.above())) {
                level.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!level.isClientSide()) {
                    stack.hurtAndBreak(1, player, (damage) -> {
                        damage.broadcastBreakEvent(event.getHand());
                    });
                    level.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
                }
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                event.setCanceled(true);

            }
        }
    }
}
