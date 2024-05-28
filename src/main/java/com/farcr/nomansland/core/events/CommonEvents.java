package com.farcr.nomansland.core.events;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.registry.NMLBlocks;
import com.farcr.nomansland.core.registry.NMLTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;

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
                    }
                    else if (state.is(Blocks.GRAVEL)) {
                        level.playSound(player, pos, SoundEvents.GRAVEL_FALL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    }
                    else  {
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
            //Sugarcane Cutting
            if (event.getFace() != Direction.DOWN && stack.is(Items.SHEARS) && state.is(Blocks.SUGAR_CANE) && !player.isSpectator()) {
                level.playSound(player, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!level.isClientSide()) {
                    stack.hurtAndBreak(1, player, (damage) -> {
                        damage.broadcastBreakEvent(event.getHand());
                    });
                    level.setBlockAndUpdate(pos, NMLBlocks.CUT_SUGAR_CANE.get().defaultBlockState());
                }
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                event.setCanceled(true);

            }
            //Torch Extinguishing
            if (stack.is(ItemTags.SHOVELS) && !player.isSpectator()) {
                if (state.is(Blocks.TORCH) ||
                        state.is(Blocks.WALL_TORCH) ||
                        state.is(Blocks.SOUL_TORCH) ||
                        state.is(Blocks.SOUL_WALL_TORCH) ||
                        state.is(NMLBlocks.SCONCE_TORCH.get()) ||
                        state.is(NMLBlocks.SCONCE_SOUL_TORCH.get()) ||
                        state.is(NMLBlocks.SCONCE_WALL_TORCH.get()) ||
                        state.is(NMLBlocks.SCONCE_SOUL_WALL_TORCH.get()))
                {
                        level.playSound(player, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.4F, 1.0F);
                    if (!level.isClientSide) {
                        stack.hurtAndBreak(1, player, (damage) -> {
                            damage.broadcastBreakEvent(event.getHand());
                        });
                        level.setBlockAndUpdate(pos,
                                state.is(Blocks.WALL_TORCH) ? NMLBlocks.EXTINGUISHED_WALL_TORCH.get().withPropertiesOf(state) :
                                        state.is(Blocks.SOUL_TORCH) ? NMLBlocks.EXTINGUISHED_SOUL_TORCH.get().defaultBlockState() :
                                                state.is(Blocks.SOUL_WALL_TORCH) ? NMLBlocks.EXTINGUISHED_SOUL_WALL_TORCH.get().withPropertiesOf(state) :
                                                        state.is(NMLBlocks.SCONCE_TORCH.get()) ? NMLBlocks.EXTINGUISHED_SCONCE_TORCH.get().defaultBlockState() :
                                                                state.is(NMLBlocks.SCONCE_WALL_TORCH.get()) ? NMLBlocks.EXTINGUISHED_SCONCE_WALL_TORCH.get().withPropertiesOf(state) :
                                                                        state.is(NMLBlocks.SCONCE_SOUL_TORCH.get()) ? NMLBlocks.EXTINGUISHED_SCONCE_SOUL_TORCH.get().defaultBlockState() :
                                                                                state.is(NMLBlocks.SCONCE_SOUL_WALL_TORCH.get()) ? NMLBlocks.EXTINGUISHED_SCONCE_SOUL_WALL_TORCH.get().withPropertiesOf(state) :
                                                        NMLBlocks.EXTINGUISHED_TORCH.get().defaultBlockState());
                    }
                    event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                    event.setCanceled(true);
                }
            }
            //Bone-Mealing #bonemeal_spreads

            //TODO: Finish placement and play particles
            if (stack.is(Items.BONE_MEAL) && !player.isSpectator()) {
                if (state.is(NMLTags.BONEMEAL_SPREADS)) {
                    level.playSound(player, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1F, 1F);
//                    level.addParticle();
                    if (!level.isClientSide) {
                        if (!player.isCreative()) stack.shrink(1);

                        int x = pos.getX();
                        int y = pos.getY();
                        int z = pos.getZ();

                        Iterator<BlockPos> it = BlockPos.betweenClosedStream(x-3, y-1, z-3, x+3, y+2, z+3).iterator();
                        while (it.hasNext()) {
                            BlockPos bp = it.next();
                            Block block = level.getBlockState(bp).getBlock();
                            if(level.random.nextFloat() <= 0.3F && state.canSurvive(level, bp) && level.getBlockState(bp) == Blocks.AIR.defaultBlockState()) level.setBlockAndUpdate(bp, state);
                        }
                    }
                    event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                    event.setCanceled(true);
                }
                if (state.getBlock().equals(Blocks.DIRT) && level.getBlockState(pos.above()) == Blocks.AIR.defaultBlockState()) {
                    for (Direction d : Direction.values()) {
                        if(level.getBlockState(pos.relative(d)).getBlock() instanceof SpreadingSnowyDirtBlock) {
                            if (!player.isCreative()) stack.shrink(1);
                            bonemealDirt(level, pos, level.getBlockState(pos.relative(d)));
                            event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                            event.setCanceled(true);
                            break;
                        } else {
                            for (Direction d1 : Direction.values()) {
                                if(!(d == d1) && level.getBlockState(pos.relative(d).relative(d1)).getBlock() instanceof SpreadingSnowyDirtBlock) {
                                    if (!player.isCreative()) stack.shrink(1);
                                    bonemealDirt(level, pos, level.getBlockState(pos.relative(d).relative(d1)));
                                    event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                                    event.setCanceled(true);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            //Bone-Mealing things that grow upwards #bonemeal_spreads_above

            //TODO: Check if above is air and play particles
            if (stack.is(Items.BONE_MEAL) && !player.isSpectator()) {
                if (state.is(NMLTags.BONEMEAL_SPREADS_UPWARDS) && level.isEmptyBlock(pos.above())) {
                    level.playSound(player, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1F, 1F);
//                    level.addParticle();
                    if (!level.isClientSide) {
                        if (!player.isCreative()) stack.shrink(1);
                        level.setBlockAndUpdate(pos.above(), state);
                    }
                    event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                    event.setCanceled(true);
                }
            }
        }

        public static void bonemealDirt(Level level, BlockPos pos, BlockState state) {
            if (level.isClientSide) return;
            level.setBlockAndUpdate(pos, state);

            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();

            Iterator<BlockPos> it = BlockPos.betweenClosedStream(x-3, y-1, z-3, x+3, y+2, z+3).iterator();
            while (it.hasNext()) {
                BlockPos bp = it.next();
                Block block = level.getBlockState(bp).getBlock();
                if (level.random.nextFloat() <= 0.3F && level.getBlockState(bp) == Blocks.DIRT.defaultBlockState()) {
                    for (Direction d : Direction.values()) {
                        if (level.getBlockState(bp.relative(d)).getBlock() instanceof SpreadingSnowyDirtBlock) {
                            level.setBlockAndUpdate(bp, state);
                            break;
                        } else {
                            for (Direction d1 : Direction.values()) {
                                if (!(d == d1) && level.getBlockState(bp.relative(d).relative(d1)).getBlock() instanceof SpreadingSnowyDirtBlock) {
                                    level.setBlockAndUpdate(bp, state);
                                    return;
                                }
                            }
                        }
                    }
                }
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
