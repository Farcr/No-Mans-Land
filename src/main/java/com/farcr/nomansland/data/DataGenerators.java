package com.farcr.nomansland.data;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.data.client.NMLBlockStateProvider;
import com.farcr.nomansland.data.client.NMLItemModelProvider;
import com.farcr.nomansland.data.server.NMLBlockTagsProvider;
import com.farcr.nomansland.data.server.NMLItemTagsProvider;
import com.farcr.nomansland.data.server.NMLLootTableProvider;
import com.farcr.nomansland.data.server.NMLRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = NoMansLand.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        var lookup = event.getLookupProvider();

        if (event.includeClient()) {

            // Client Data Generation
            generator.addProvider(true, new NMLBlockStateProvider(packOutput, existingFileHelper));
            generator.addProvider(true, new NMLItemModelProvider(packOutput, existingFileHelper));
        }

        if (event.includeServer()) {

            NMLBlockTagsProvider blockTags = new NMLBlockTagsProvider(packOutput, lookup, existingFileHelper);

            // Server Data Generation
            generator.addProvider(true, new NMLRecipeProvider(packOutput));
            generator.addProvider(true, blockTags);
            generator.addProvider(true, new NMLItemTagsProvider(packOutput, lookup, blockTags, existingFileHelper));
            generator.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(), List.of(
                    new LootTableProvider.SubProviderEntry(NMLLootTableProvider::new, LootContextParamSets.BLOCK)
            )));
        }

    }
}
