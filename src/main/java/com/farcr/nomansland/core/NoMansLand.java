package com.farcr.nomansland.core;

import com.farcr.nomansland.core.content.entity.client.BuriedRenderer;
import com.farcr.nomansland.core.content.entity.client.MooseRenderer;
import com.farcr.nomansland.core.content.entity.client.NMLBoatRenderer;
import com.farcr.nomansland.core.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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

        NMLBlockEntities.BLOCK_ENTITIES.register(modEventBus);

        NMLCreativeTabs.CREATIVE_TABS.register(modEventBus);

        modEventBus.addListener(NMLItems::addCreative);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            NMLFlammability.init();
        });
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.PINE_SAPLING.getId(), NMLBlocks.POTTED_PINE_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.YELLOW_BIRCH_SAPLING.getId(), NMLBlocks.POTTED_YELLOW_BIRCH_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.AUTUMNAL_OAK_SAPLING.getId(), NMLBlocks.POTTED_AUTUMNAL_OAK_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.FIELD_MUSHROOM.getId(), NMLBlocks.POTTED_FIELD_MUSHROOM);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.MAPLE_SAPLING.getId(), NMLBlocks.POTTED_MAPLE_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.RED_MAPLE_SAPLING.getId(), NMLBlocks.POTTED_RED_MAPLE_SAPLING);
        });
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(NMLEntities.BURIED.get(), BuriedRenderer::new);
            EntityRenderers.register(NMLEntities.MOOSE.get(), MooseRenderer::new);

            EntityRenderers.register(NMLEntities.BOAT.get(), pContext -> new NMLBoatRenderer(pContext, false));
            EntityRenderers.register(NMLEntities.CHEST_BOAT.get(), pContext -> new NMLBoatRenderer(pContext, true));
        }
    }
}
