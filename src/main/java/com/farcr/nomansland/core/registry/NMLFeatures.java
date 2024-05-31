package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.world.feature.SpreadPatchFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NMLFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, NoMansLand.MODID);

    public static final DeferredHolder<Feature<?>, Feature<RandomPatchConfiguration>> SPREAD_PATCH = FEATURES.register("spread_patch",
            () -> new SpreadPatchFeature(RandomPatchConfiguration.CODEC));
}
