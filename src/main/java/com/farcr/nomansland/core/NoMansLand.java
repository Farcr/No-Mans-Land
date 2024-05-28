package com.farcr.nomansland.core;

import com.farcr.nomansland.client.render.FirebombRenderer;
import com.farcr.nomansland.core.config.NMLConfig;
import com.farcr.nomansland.client.render.BuriedRenderer;
import com.farcr.nomansland.client.render.MooseRenderer;
import com.farcr.nomansland.client.render.NMLBoatRenderer;
import com.farcr.nomansland.core.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(NoMansLand.MODID)
public class NoMansLand {
    public static final String MODID = "nomansland";
    private static final Logger LOGGER = LogUtils.getLogger();

    public NoMansLand() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        NMLBlocks.BLOCKS.register(modEventBus);

        NMLItems.ITEMS.register(modEventBus);

        NMLEntities.ENTITY_TYPES.register(modEventBus);

        NMLParticleTypes.PARTICLE_TYPES.register(modEventBus);

        NMLBlockEntities.BLOCK_ENTITIES.register(modEventBus);

        NMLCreativeTabs.CREATIVE_TABS.register(modEventBus);

        NMLFeatures.FEATURES.register(modEventBus);

        modEventBus.addListener(NMLItems::addCreative);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NMLConfig.COMMON_CONFIG);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            NMLFlammability.init();
        });
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.PINE_SAPLING.getId(), NMLBlocks.POTTED_PINE_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.YELLOW_BIRCH_SAPLING.getId(), NMLBlocks.POTTED_YELLOW_BIRCH_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.AUTUMNAL_OAK_SAPLING.getId(), NMLBlocks.POTTED_AUTUMNAL_OAK_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.PALE_CHERRY_SAPLING.getId(), NMLBlocks.POTTED_PALE_CHERRY_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.FIELD_MUSHROOM.getId(), NMLBlocks.POTTED_FIELD_MUSHROOM);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.MAPLE_SAPLING.getId(), NMLBlocks.POTTED_MAPLE_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.RED_MAPLE_SAPLING.getId(), NMLBlocks.POTTED_RED_MAPLE_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.WALNUT_SAPLING.getId(), NMLBlocks.POTTED_WALNUT_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.ACONITE.getId(), NMLBlocks.POTTED_ACONITE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.YELLOW_LUPINE.getId(), NMLBlocks.POTTED_YELLOW_LUPINE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.BLUE_LUPINE.getId(), NMLBlocks.POTTED_BLUE_LUPINE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.RED_LUPINE.getId(), NMLBlocks.POTTED_RED_LUPINE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.PINK_LUPINE.getId(), NMLBlocks.POTTED_PINK_LUPINE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.WILD_MINT.getId(), NMLBlocks.POTTED_WILD_MINT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.AUTUMN_CROCUS.getId(), NMLBlocks.POTTED_AUTUMN_CROCUS);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.SUCCULENT.getId(), NMLBlocks.POTTED_SUCCULENT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.BARREL_CACTUS.getId(), NMLBlocks.POTTED_BARREL_CACTUS);
        });
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.YELLOW_BIRCH_LEAVES.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.AUTUMNAL_OAK_LEAVES.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.PALE_CHERRY_LEAVES.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.RED_MAPLE_LEAVES.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.MAPLE_LEAVES.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.PINE_LEAVES.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.WALNUT_LEAVES.get(), 0.30f);

            ComposterBlock.COMPOSTABLES.put(NMLBlocks.YELLOW_BIRCH_SAPLING.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.AUTUMNAL_OAK_SAPLING.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.PALE_CHERRY_SAPLING.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.RED_MAPLE_SAPLING.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.MAPLE_SAPLING.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.PINE_SAPLING.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.WALNUT_SAPLING.get(), 0.30f);

            ComposterBlock.COMPOSTABLES.put(NMLBlocks.GRASS_SPROUTS.get(), 0.10f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.OAT_GRASS.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.FIDDLEHEAD.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.CATTAIL.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.ACONITE.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.PINK_LUPINE.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.RED_LUPINE.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.YELLOW_FLOWERBED.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.BLUE_LUPINE.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.AUTUMN_CROCUS.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.WILD_MINT.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.RAFFLESIA.get(), 0.85f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.BARREL_CACTUS.get(), 0.50f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.SUCCULENT.get(), 0.50f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.CUT_VINE.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.CUT_SUGAR_CANE.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.CLOVER_PATCH.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.VIOLET_FLOWERBED.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.WHITE_FLOWERBED.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.RED_FLOWERBED.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.YELLOW_FLOWERBED.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(NMLBlocks.BLUE_FLOWERBED.get(), 0.65f);

        });
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(NMLEntities.BOAT.get(), pContext -> new NMLBoatRenderer(pContext, false));
            EntityRenderers.register(NMLEntities.CHEST_BOAT.get(), pContext -> new NMLBoatRenderer(pContext, true));
        
            EntityRenderers.register(NMLEntities.BURIED.get(), BuriedRenderer::new);
            EntityRenderers.register(NMLEntities.MOOSE.get(), MooseRenderer::new);

            EntityRenderers.register(NMLEntities.FIREBOMB.get(), FirebombRenderer::new);
        }
    }

}
