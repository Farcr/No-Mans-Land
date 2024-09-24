package com.farcr.nomansland.core.events;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.block.cauldrons.NMLCauldronBlock;
import com.farcr.nomansland.core.registry.NMLBlocks;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BambooSaplingBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static net.minecraft.world.level.block.SnowyDirtBlock.SNOWY;

@EventBusSubscriber(modid = NoMansLand.MODID)
@SuppressWarnings("unused")
public class PathMakingEvents {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();

        if ((stack.is(ItemTags.HOES) || stack.is(ItemTags.SHOVELS)) && !player.isSpectator() && state.canBeReplaced()) {
            pos = pos.below();
            state = level.getBlockState(pos);
        }

        List<Block> pathableBlocks = List.of(
                Blocks.GRAVEL,
                Blocks.SAND,
                Blocks.RED_SAND,
                Blocks.MYCELIUM,
                Blocks.PODZOL,
                Blocks.DIRT,
                Blocks.COARSE_DIRT,
                Blocks.ROOTED_DIRT,
                Blocks.SNOW_BLOCK,
                Blocks.GRASS_BLOCK
        );
        //Paths
        if ((pathableBlocks.contains(state.getBlock()) || (state.is(Blocks.GRASS_BLOCK) && state.getValue(SNOWY))) && event.getFace() != Direction.DOWN && stack.is(ItemTags.SHOVELS) && !player.isSpectator() && (level.isEmptyBlock(pos.above()) || level.getBlockState(pos.above()).canBeReplaced())) {
            if (state.is(BlockTags.SAND)) {
                level.playSound(player, pos, SoundEvents.SAND_FALL, SoundSource.BLOCKS, 1, 1);
            } else if (state.is(Blocks.GRAVEL)) {
                level.playSound(player, pos, SoundEvents.GRAVEL_FALL, SoundSource.BLOCKS, 1, 1);
            } else if (((state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.ROOTED_DIRT) || state.is(Blocks.GRASS_BLOCK)) && level.getBlockState(pos.above()).is(Blocks.SNOW)) || state.is(Blocks.SNOW)) {
                level.playSound(player, pos, SoundEvents.SNOW_BREAK, SoundSource.BLOCKS, 1, 1);
            } else {
                level.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1, 1);
            }
            if (!level.isClientSide) {
                stack.hurtAndBreak(1, player, stack.getEquipmentSlot());

                BlockState pathState = ImmutableMap.ofEntries(
                        Map.entry(Blocks.GRAVEL, NMLBlocks.GRAVEL_PATH),
                        Map.entry(Blocks.SAND, NMLBlocks.SAND_PATH),
                        Map.entry(Blocks.RED_SAND, NMLBlocks.RED_SAND_PATH),
                        Map.entry(Blocks.GRASS_BLOCK, Blocks.DIRT_PATH.defaultBlockState().getBlockHolder()),
                        Map.entry(Blocks.MYCELIUM, NMLBlocks.MYCELIUM_PATH),
                        Map.entry(Blocks.PODZOL, NMLBlocks.PODZOL_PATH),
                        Map.entry(Blocks.DIRT, NMLBlocks.DIRT_PATH),
                        Map.entry(Blocks.COARSE_DIRT, NMLBlocks.DIRT_PATH),
                        Map.entry(Blocks.ROOTED_DIRT, NMLBlocks.DIRT_PATH),
                        Map.entry(Blocks.SNOW_BLOCK, NMLBlocks.SNOW_PATH)
                ).get(state.getBlock()).value().defaultBlockState();

                if ((state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.ROOTED_DIRT) || state.is(Blocks.GRASS_BLOCK)) && level.getBlockState(pos.above()).is(Blocks.SNOW)) level.setBlockAndUpdate(pos, NMLBlocks.SNOWY_GRASS_PATH.get().defaultBlockState());
                else level.setBlockAndUpdate(pos, pathState);
            }
            event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
            event.setCanceled(true);
        }

        //Dirt Path into Farmland
        if (stack.is(ItemTags.HOES) && state.is(NMLBlocks.DIRT_PATH.get()) && !player.isSpectator() && level.isEmptyBlock(pos.above())) {
            level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (!level.isClientSide()) {
                stack.hurtAndBreak(1, player, stack.getEquipmentSlot());

                level.setBlockAndUpdate(pos, Blocks.FARMLAND.defaultBlockState());
            }
            event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
            event.setCanceled(true);
        }

        // Farmland
        if ((state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT)) && stack.is(ItemTags.HOES)) {
            level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1, 1);
            if (!level.isClientSide()) {
                stack.hurtAndBreak(1, player, stack.getEquipmentSlot());

                level.setBlockAndUpdate(pos, Blocks.FARMLAND.defaultBlockState());
                if (level.getBlockState(pos.above()).canBeReplaced()) level.destroyBlock(pos.above(), false);
            }
            event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
            event.setCanceled(true);
        }

        //Farmland untilling
        if (event.getFace() != Direction.DOWN && stack.is(ItemTags.SHOVELS) && state.is(Blocks.FARMLAND) && !player.isSpectator() && level.isEmptyBlock(pos.above())) {
            level.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (!level.isClientSide()) {
                stack.hurtAndBreak(1, player, stack.getEquipmentSlot());

                level.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
            }
            event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
            event.setCanceled(true);

        }
    }
}

