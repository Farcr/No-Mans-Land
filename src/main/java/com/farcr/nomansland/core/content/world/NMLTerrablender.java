package com.farcr.nomansland.core.content.world;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

public class NMLTerrablender {
    public static void registerRegions() {
        Regions.register(new NMLOverworldRegion(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "overworld"), 10));
//        Regions.register(new AutumnalRegion(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "overworld"), 10));
    }

    public static void registerSurfaceData() {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, NoMansLand.MODID, NMLSurfaceData.makeRules());
    }
}
