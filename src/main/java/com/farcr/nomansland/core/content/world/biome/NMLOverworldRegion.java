package com.farcr.nomansland.core.content.world.biome;

import com.farcr.nomansland.core.registry.NMLBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

import static terrablender.api.ParameterUtils.*;


public class NMLOverworldRegion  extends Region {
    public NMLOverworldRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
        // Autumnal Forest
        new ParameterPointListBuilder()
                .temperature(Temperature.span(Temperature.NEUTRAL, Temperature.COOL))
                .humidity(Humidity.span(Humidity.DRY, Humidity.WET))
                .continentalness(Continentalness.INLAND)
                .erosion(Erosion.EROSION_1, Erosion.EROSION_5)
                .depth(Depth.SURFACE, Depth.FLOOR)
                .weirdness(Weirdness.VALLEY, Weirdness.LOW_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.HIGH_SLICE_NORMAL_DESCENDING, Weirdness.PEAK_NORMAL)
                .build().forEach(point -> builder.add(point, NMLBiomes.AUTUMNAL_FOREST));
        // Maple Forest
//        new ParameterPointListBuilder()
//                .temperature(Temperature.span(Temperature.COOL, Temperature.ICY))
//                .humidity(Humidity.span(Humidity.DRY, Humidity.NEUTRAL))
//                .continentalness(Continentalness.INLAND)
//                .erosion(Erosion.EROSION_1, Erosion.EROSION_5)
//                .depth(Depth.SURFACE, Depth.FLOOR)
//                .weirdness(Weirdness.VALLEY, Weirdness.LOW_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.HIGH_SLICE_NORMAL_DESCENDING, Weirdness.PEAK_NORMAL)
//                .build().forEach(point -> builder.add(point, NMLBiomes.MAPLE_FOREST));
        // Old Growth Forest
        new ParameterPointListBuilder()
                .temperature(Temperature.NEUTRAL)
                .humidity(Humidity.NEUTRAL)
                .continentalness(Continentalness.INLAND)
                .erosion(Erosion.EROSION_1, Erosion.EROSION_5)
                .depth(Depth.SURFACE, Depth.FLOOR)
                .weirdness(Weirdness.VALLEY, Weirdness.LOW_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.HIGH_SLICE_NORMAL_DESCENDING, Weirdness.PEAK_NORMAL)
                .build().forEach(point -> builder.add(point, NMLBiomes.OLD_GROWTH_FOREST));

        // Add our points to the mapper
        builder.build().forEach(mapper);
    }
}
