package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class NMLBiomes {
    public static final ResourceKey<Biome> MAPLE_FOREST = createKey("maple_forest");
    public static final ResourceKey<Biome> AUTUMNAL_FOREST = createKey("autumnal_forest");
    public static final ResourceKey<Biome> AUTUMNAL_FIELDS = createKey("autumnal_fields");
    public static final ResourceKey<Biome> OLD_GROWTH_FOREST = createKey("old_growth_forest");




    public static final ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(NoMansLand.MODID, name));
    }
}
