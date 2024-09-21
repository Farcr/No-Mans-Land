package com.farcr.nomansland.core.events;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.config.NMLConfig;
import com.farcr.nomansland.core.registry.NMLBlocks;
import com.farcr.nomansland.core.registry.NMLFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.BlockGrowFeatureEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
                BlockPos posUnder = pos.below();
                BlockState stateUnder = level.getBlockState(posUnder);
                if (stateUnder.getBlock() instanceof SnowyDirtBlock)
                    level.setBlockAndUpdate(posUnder, stateUnder.setValue(SNOWY, true));
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                event.setCanceled(true);
            }

            // Snow grassing?
            if ((stack.is(NMLBlocks.FROSTED_GRASS.asItem()) || stack.is(Blocks.SHORT_GRASS.asItem())) && !player.isSpectator() && state.is(Blocks.SNOW)) {
                BlockPos posUnder = pos.below();
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
        public static void onBlockGrow(BlockGrowFeatureEvent event) {
            ResourceKey<ConfiguredFeature<?, ?>> feature = event.getFeature().getKey();
            List<ResourceKey<ConfiguredFeature<?, ?>>> regularOakFeatures = new ArrayList<>();
            List<ResourceKey<ConfiguredFeature<?, ?>>> fancyOakFeatures = new ArrayList<>();
            List<ResourceKey<ConfiguredFeature<?, ?>>> autumnalOakFeatures = new ArrayList<>();
            List<ResourceKey<ConfiguredFeature<?, ?>>> spruceFeatures = new ArrayList<>();
            List<ResourceKey<ConfiguredFeature<?, ?>>> pineFeatures = new ArrayList<>();

            BlockPos pos = event.getPos();
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            LevelAccessor level = event.getLevel();
            int fruit = 0;
            RandomSource random = event.getRandom();

            regularOakFeatures.add(TreeFeatures.OAK);
            regularOakFeatures.add(TreeFeatures.OAK_BEES_0002);
            regularOakFeatures.add(TreeFeatures.OAK_BEES_002);
            regularOakFeatures.add(TreeFeatures.OAK_BEES_005);

            fancyOakFeatures.add(TreeFeatures.FANCY_OAK);
            fancyOakFeatures.add(TreeFeatures.FANCY_OAK_BEES_0002);
            fancyOakFeatures.add(TreeFeatures.FANCY_OAK_BEES_002);
            fancyOakFeatures.add(TreeFeatures.FANCY_OAK_BEES_005);

            autumnalOakFeatures.add(NMLFeatures.AUTUMNAL_OAK);
            autumnalOakFeatures.add(NMLFeatures.LARGE_AUTUMNAL_OAK);

            spruceFeatures.add(TreeFeatures.SPRUCE);
            spruceFeatures.add(TreeFeatures.MEGA_SPRUCE);

            pineFeatures.add(TreeFeatures.PINE);
            pineFeatures.add(TreeFeatures.MEGA_PINE);

            boolean apple = regularOakFeatures.contains(feature) || fancyOakFeatures.contains(feature);
            boolean pear = autumnalOakFeatures.contains(feature);

            boolean spruce = spruceFeatures.contains(feature) || pineFeatures.contains(feature);
            boolean pine = feature == NMLFeatures.PINE;

            if (apple || pear) {
                Iterator<BlockPos> it = BlockPos.betweenClosedStream(x - 8, y - 12, z - 8, x + 8, y + 12, z + 8).iterator();
                while (it.hasNext()) {
                    BlockPos bp = it.next();
                    BlockState state = level.getBlockState(bp);
                    if (apple && state.is(NMLBlocks.APPLE_FRUIT)) fruit++;
                    if (pear && state.is(NMLBlocks.PEAR_FRUIT)) fruit++;
                }
                if (fruit >= 12) {
                    if (regularOakFeatures.contains(feature)) event.setFeature(NMLFeatures.OAK_APPLE_05);
                    if (fancyOakFeatures.contains(feature)) event.setFeature(NMLFeatures.FANCY_OAK_APPLE_05);
                    if (feature == NMLFeatures.AUTUMNAL_OAK) event.setFeature(NMLFeatures.AUTUMNAL_OAK_PEAR_05);
                    if (feature == NMLFeatures.LARGE_AUTUMNAL_OAK) event.setFeature(NMLFeatures.LARGE_AUTUMNAL_OAK_PEAR_05);
                }
                else if (fruit > 0) {
                    if (regularOakFeatures.contains(feature)) event.setFeature(NMLFeatures.OAK_APPLE_01);
                    if (fancyOakFeatures.contains(feature)) event.setFeature(NMLFeatures.FANCY_OAK_APPLE_01);
                }
            }

            if ((pine || spruce) && level.getLevelData().isRaining() && !level.getBiome(pos).value().warmEnoughToRain(pos)) {
                if (spruceFeatures.contains(feature)) {
                    if (feature == TreeFeatures.SPRUCE) event.setFeature(NMLFeatures.FROSTED_SPRUCE);
                    if (feature == TreeFeatures.MEGA_SPRUCE) event.setFeature(NMLFeatures.FROSTED_SPRUCE);
                }
                if (pineFeatures.contains(feature)) {
                    if (feature == TreeFeatures.PINE) event.setFeature(NMLFeatures.FROSTED_SPRUCE_ALT);
                    if (feature == TreeFeatures.MEGA_PINE) event.setFeature(NMLFeatures.FROSTED_SPRUCE_ALT);
                }
                if (pine) event.setFeature(NMLFeatures.FROSTED_PINE);
            }
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
