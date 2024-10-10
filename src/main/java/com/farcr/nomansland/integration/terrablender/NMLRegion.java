//package com.farcr.nomansland.integration.terrablender;
//
//import com.mojang.datafixers.util.Pair;
//import net.minecraft.core.Registry;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraft.world.level.biome.Climate;
//import terrablender.api.Region;
//import terrablender.api.RegionType;
//
//import java.util.function.Consumer;
//
//
//public class NMLRegion extends Region {
//    public NMLRegion(ResourceLocation name, int weight) {
//        super(name, RegionType.OVERWORLD, weight);
//    }
//
//    @Override
//    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
//        (new NMLBiomeBuilder()).addBiomes(registry, mapper);
////        Regions.get(RegionType.OVERWORLD).forEach(region -> {
////            (new NMLBiomeBuilder()).addBiomes(registry, mapper);
////        });
//    }
//}
