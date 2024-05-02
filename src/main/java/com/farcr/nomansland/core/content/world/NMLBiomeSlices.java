package com.farcr.nomansland.core.content.world;

import com.farcr.nomansland.core.NoMansLand;
import com.teamabnormals.blueprint.common.world.modification.ModdedBiomeSlice;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class NMLBiomeSlices {

    public static final ResourceKey<ModdedBiomeSlice> AUTUMNAL = createKey("autumnal");
    ///TODO: FINISH THIS

    public static ResourceKey<ModdedBiomeSlice> createKey(String name) {
        return ResourceKey.create(BlueprintDataPackRegistries.MODDED_BIOME_SLICES, new ResourceLocation(NoMansLand.MODID, name));
    }
}
