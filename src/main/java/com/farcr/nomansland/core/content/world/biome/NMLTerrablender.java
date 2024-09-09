package com.farcr.nomansland.core.content.world.biome;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class NMLTerrablender {
    public static void registerBiomes() {
        Regions.register(new NMLOverworldRegion(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "overworld"), 4));
    }
}
