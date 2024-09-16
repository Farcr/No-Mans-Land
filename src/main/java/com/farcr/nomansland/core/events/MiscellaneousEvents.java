package com.farcr.nomansland.core.events;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.config.NMLConfig;
import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.BlockGrowFeatureEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import static com.farcr.nomansland.core.content.block.FrostedGrassBlock.SNOWLOGGED;
import static net.minecraft.world.level.block.SnowyDirtBlock.SNOWY;

public class MiscellaneousEvents {
    @EventBusSubscriber(modid = NoMansLand.MODID)
    public static class NeoForgeEvents {
        @SubscribeEvent
        public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
            Level level = event.getLevel();
            BlockPos pos = event.getPos();
            BlockState state = level.getBlockState(pos);
            Player player = event.getEntity();
            ItemStack stack = event.getItemStack();

            //Torch Extinguishing
            if (stack.is(ItemTags.SHOVELS) && !player.isSpectator() && NMLConfig.TORCH_EXTINGUISHING.get()) {
                if (state.is(Blocks.TORCH) ||
                        state.is(Blocks.WALL_TORCH) ||
                        state.is(Blocks.SOUL_TORCH) ||
                        state.is(Blocks.SOUL_WALL_TORCH) ||
                        state.is(NMLBlocks.SCONCE_TORCH.get()) ||
                        state.is(NMLBlocks.SCONCE_SOUL_TORCH.get()) ||
                        state.is(NMLBlocks.SCONCE_WALL_TORCH.get()) ||
                        state.is(NMLBlocks.SCONCE_SOUL_WALL_TORCH.get())) {
                    level.playSound(player, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.4F, 1.0F);
                    if (!level.isClientSide) {
                        stack.hurtAndBreak(1, player, stack.getEquipmentSlot());

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

            // Grass Frosting
            if (stack.is(Blocks.SNOW.asItem()) && !player.isSpectator() && state.is(Blocks.SHORT_GRASS)) {
                level.setBlockAndUpdate(pos, NMLBlocks.FROSTED_GRASS.get().defaultBlockState().setValue(SNOWLOGGED, true));
                stack.consume(1, player);
                level.playSound(player, pos, SoundEvents.SNOW_PLACE, SoundSource.PLAYERS, 1, (level.random.nextFloat() - level.random.nextFloat()) * 0.6F + 1.2F);
                BlockPos posUnder = pos.relative(Direction.DOWN);
                BlockState stateUnder = level.getBlockState(posUnder);
                if (stateUnder.getBlock() instanceof SnowyDirtBlock)
                    level.setBlockAndUpdate(posUnder, stateUnder.setValue(SNOWY, true));
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                event.setCanceled(true);
            }

            // Snow grassing?
            if ((stack.is(NMLBlocks.FROSTED_GRASS.asItem()) || stack.is(Blocks.SHORT_GRASS.asItem())) && !player.isSpectator() && state.is(Blocks.SNOW)) {
                BlockPos posUnder = pos.relative(Direction.DOWN);
                BlockState stateUnder = level.getBlockState(posUnder);
                if (stateUnder.getBlock() instanceof SnowyDirtBlock || stateUnder.getBlock() == Blocks.DIRT) {
                    level.setBlockAndUpdate(pos, NMLBlocks.FROSTED_GRASS.get().defaultBlockState().setValue(SNOWLOGGED, true));
                    stack.consume(1, player);
                    level.playSound(player, pos, SoundEvents.GRASS_PLACE, SoundSource.PLAYERS, 1, (level.random.nextFloat() - level.random.nextFloat()) * 0.2F + 1.2F);
                    if (stateUnder.getBlock() instanceof SnowyDirtBlock)
                        level.setBlockAndUpdate(posUnder, stateUnder.setValue(SNOWY, true));
                    event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                    event.setCanceled(true);
                }
            }
        }

        @SubscribeEvent
        public static void onFarmlandTrample(BlockEvent.FarmlandTrampleEvent event) {
            if (!NMLConfig.TRAMPLING.get()) event.setCanceled(true);
        }

        @SubscribeEvent
        public static void onPodzolSpread(BlockGrowFeatureEvent event) {
//            if ((event.getFeature().is(TreeFeatures.MEGA_PINE)) || (event.getFeature().is(TreeFeatures.MEGA_SPRUCE))) event.setCanceled(true);
        }
    }

    @EventBusSubscriber(modid = NoMansLand.MODID, bus = EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            //    TODO: BURIED AND MOOSE
//            event.put(NMLEntities.BURIED.get(), BuriedEntity.createAttributes().build());
//            event.put(NMLEntities.MOOSE.get(), MooseEntity.createAttributes().build());
        }
    }
}
