package com.farcr.nomansland;

import com.farcr.nomansland.common.registry.*;
import com.farcr.nomansland.integration.biolith.NMLBiomePlacements;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(NoMansLand.MODID)
public class NoMansLand {
    public static final String MODID = "nomansland";
    public static final Logger LOGGER = LogUtils.getLogger();

    public NoMansLand(IEventBus modEventBus, ModContainer modContainer) {

        NMLItems.ITEMS.register(modEventBus);
        NMLBlocks.BLOCKS.register(modEventBus);
        NMLEntities.ENTITIES.register(modEventBus);
        NMLFeatures.FEATURES.register(modEventBus);
        NMLFoliagePlacerType.FOLIAGE_PLACER_TYPES.register(modEventBus);
        NMLTrunkPlacerType.TRUNK_PLACER_TYPES.register(modEventBus);
        NMLSounds.SOUND_EVENTS.register(modEventBus);
        NMLCreativeTabs.CREATIVE_TABS.register(modEventBus);
        NMLParticleTypes.PARTICLE_TYPES.register(modEventBus);
        NMLBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        NMLLootModifiers.LOOT_MODIFIERS.register(modEventBus);
        NMLTreeDecoratorType.TREE_DECORATOR_TYPES.register(modEventBus);
        NMLDataSerializers.ENTITY_DATA_SERIALIZERS.register(modEventBus);
//        NMLTerrablender.registerRegions();
//        NMLTerrablender.registerSurfaceData();
        NMLBiomePlacements.registerBiomes();

        modEventBus.addListener(NMLItems::addCreative);
        modEventBus.addListener(this::commonSetup);

        modContainer.registerConfig(ModConfig.Type.COMMON, NMLConfig.COMMON_CONFIG);
        modContainer.registerConfig(ModConfig.Type.CLIENT, NMLConfig.CLIENT_CONFIG);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            NMLFlammables.register();
            NMLPottables.register();
        });
    }

}
