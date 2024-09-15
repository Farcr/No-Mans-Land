package com.farcr.nomansland.data;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.data.recipes.NMLRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = NoMansLand.MODID, bus = EventBusSubscriber.Bus.MOD)
public class NMLDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

//        BlockTags blockTags = new BlockTags(output, lookupProvider, helper);
//        generator.addProvider(event.includeServer(), blockTags);

//        generator.addProvider(event.includeServer(), new ItemTags(output, lookupProvider, blockTags.contentsGetter(), helper));

        generator.addProvider(event.includeServer(), new NMLRecipes(output, lookupProvider));

//        generator.addProvider(true, new WorldGen(output, event.getLookupProvider()));


//        generator.addProvider(event.includeServer(), new LootTableProvider(output, Collections.emptySet(), List.of(
//                new LootTableProvider.SubProviderEntry(SBlockLoot::new, LootContextParamSets.BLOCK)
//        ), lookupProvider));

//        Blockstates blockStates = new Blockstates(output, helper);
//        generator.addProvider(event.includeClient(), blockStates);

//        generator.addProvider(event.includeClient(), new ItemModels(output, blockStates.models().existingFileHelper));
    }
}

