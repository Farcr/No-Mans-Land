package com.farcr.nomansland.data;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.data.client.NMLBlockStateProvider;
import com.farcr.nomansland.data.client.NMLItemModelProvider;
import com.farcr.nomansland.data.server.NMLBlockTagsProvider;
import com.farcr.nomansland.data.server.NMLItemTagsProvider;
import com.farcr.nomansland.data.server.NMLRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
        }

    }
}
