package com.farcr.nomansland.core.content.world;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.registry.NMLBiomes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.worldgen.DefaultOverworldRegion;

public class NMLTerrablender {
    public static void registerRegions() {
        Regions.register(new NMLRegion(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "overworld"), 10));
//        Regions.remove(RegionType.OVERWORLD, ResourceLocation.withDefaultNamespace("overworld"));
    }

    public static void registerSurfaceData() {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, NoMansLand.MODID, NMLSurfaceData.makeRules());
    }
}
