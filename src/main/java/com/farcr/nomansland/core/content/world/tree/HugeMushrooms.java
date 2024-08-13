package com.farcr.nomansland.core.content.world.tree;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class HugeMushrooms {
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_FIELD_MUSHROOM =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "huge_field_mushroom"));

}
