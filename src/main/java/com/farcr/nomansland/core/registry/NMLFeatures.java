package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.world.feature.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Optional;

public class NMLFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, NoMansLand.MODID);

    public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> LARGE_PINE_TREE = FEATURES.register("large_pine_tree", ()->new PineTreeFeature(true, TreeConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> PINE_TREE = FEATURES.register("pine_tree", ()->new PineTreeFeature(false, TreeConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<RandomPatchConfiguration>> SPREAD_PATCH = FEATURES.register("spread_patch",
            () -> new SpreadPatchFeature(RandomPatchConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<MultiSpreadPatchConfiguration>> MULTISPREAD_PATCH = FEATURES.register("multispread_patch",
            () -> new MultiSpreadPatchFeature(MultiSpreadPatchConfiguration.CODEC));

    public static final DeferredHolder<Feature<?> ,Feature<FillBiomeAboveConfiguration>> FILL_BIOME_ABOVE = FEATURES.register("fill_biome_above",
            () -> new FillBiomeAboveFeature(FillBiomeAboveConfiguration.CODEC));

    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_APPLE_01 = register("oak_apple_01");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_APPLE_05 = register("oak_apple_05");

    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_OAK_APPLE_01 = register("fancy_oak_apple_01");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_OAK_APPLE_05 = register("fancy_oak_apple_05");

    public static final ResourceKey<ConfiguredFeature<?, ?>> AUTUMNAL_OAK = register("autumnal_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_AUTUMNAL_OAK = register("large_autumnal_oak");

    public static final ResourceKey<ConfiguredFeature<?, ?>> AUTUMNAL_OAK_PEAR_05 = register("autumnal_oak_pear_05");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_AUTUMNAL_OAK_PEAR_05 = register("large_autumnal_oak_pear_05");

    public static ResourceKey<ConfiguredFeature<?, ?>> register(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, name));
    }

}
