package com.farcr.nomansland.common.registry;

import com.farcr.nomansland.NoMansLand;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class NMLBiomes {

    //Surface
    public static final ResourceKey<Biome> AUTUMNAL_FOREST = createKey("autumnal_forest");

    public static final ResourceKey<Biome> MAPLE_FOREST = createKey("maple_forest");
    public static final ResourceKey<Biome> MAPLE_GROVE = createKey("maple_grove");

    public static final ResourceKey<Biome> OLD_GROWTH_FOREST = createKey("old_growth_forest");
    public static final ResourceKey<Biome> OLD_GROWTH_FOREST_CLEARING = createKey("old_growth_forest_clearing");
    public static final ResourceKey<Biome> OLD_GROWTH_FOREST_EDGE = createKey("old_growth_forest_edge");

    public static final ResourceKey<Biome> DARK_SWAMP = createKey("dark_swamp");

    public static final ResourceKey<Biome> BAYOU = createKey("bayou");

    public static final ResourceKey<Biome> BOG = createKey("bog");
    public static final ResourceKey<Biome> MUSKEG = createKey("bog");

    //Underground
    public static final ResourceKey<Biome> CAVES = createKey("caves");
    public static final ResourceKey<Biome> DEEP_CAVES = createKey("caves");


    public static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, name));
    }
}
