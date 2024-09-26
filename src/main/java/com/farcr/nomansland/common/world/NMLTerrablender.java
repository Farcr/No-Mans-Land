package com.farcr.nomansland.common.world;

import com.farcr.nomansland.NoMansLand;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

public class NMLTerrablender {
    public static void registerRegions() {
        Regions.register(new NMLRegion(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "overworld"), 10));
    }

    public static void registerSurfaceData() {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, NoMansLand.MODID, NMLSurfaceData.makeRules());
    }
}
