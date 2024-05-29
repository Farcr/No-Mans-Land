package com.farcr.nomansland.core;

import com.farcr.nomansland.core.config.NMLConfig;
import com.farcr.nomansland.core.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(NoMansLand.MODID)
public class NoMansLand {
    public static final String MODID = "nomansland";
    private static final Logger LOGGER = LogUtils.getLogger();

    public NoMansLand() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NMLConfig.COMMON_CONFIG);

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

        NMLLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            NMLFlammables.register();
            NMLCompostabies.register();
            NMLPottables.register();
        });
    }

}
