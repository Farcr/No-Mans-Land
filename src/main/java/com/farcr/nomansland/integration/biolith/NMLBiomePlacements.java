package com.farcr.nomansland.integration.biolith;

import com.farcr.nomansland.common.registry.NMLBiomes;
import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.*;

public class NMLBiomePlacements {
    public static void registerBiomes() {

        BiomePlacement.addOverworld(NMLBiomes.CAVES,
                Climate.parameters(
                        Climate.Parameter.span(-1, 1),
                        Climate.Parameter.span(-1, 1),
                        Climate.Parameter.span(-1, 1),
                        Climate.Parameter.span(-1, 1),
                        Climate.Parameter.span(0.1F, 1F),
                        Climate.Parameter.span(-1, 1),
                        0F));

        // Autumnal Forest
        BiomePlacement.replaceOverworld(
                Biomes.FOREST,
                NMLBiomes.AUTUMNAL_FOREST,
                0.2
        );

        // Maple Forest & Grove
        BiomePlacement.replaceOverworld(
                Biomes.FOREST,
                NMLBiomes.MAPLE_FOREST,
                0.2
        );

        BiomePlacement.addSubOverworld(
                Biomes.GROVE,
                NMLBiomes.MAPLE_GROVE,
                CriterionBuilder.allOf(CriterionBuilder.alternate(NMLBiomes.MAPLE_FOREST, Biomes.FOREST))
        );

        // Bog
        BiomePlacement.replaceOverworld(
                Biomes.SWAMP,
                NMLBiomes.BOG,
                0.2
        );

        BiomePlacement.replaceOverworld(
                Biomes.MANGROVE_SWAMP,
                NMLBiomes.BOG,
                0.05
        );

        BiomePlacement.addSubOverworld(
                Biomes.SWAMP,
                NMLBiomes.BOG,
                CriterionBuilder.allOf(CriterionBuilder.neighbor(Biomes.PLAINS))
        );

        // Muskeg
        BiomePlacement.addSubOverworld(
                Biomes.SNOWY_PLAINS,
                NMLBiomes.MUSKEG,
                CriterionBuilder.allOf(CriterionBuilder.alternate(NMLBiomes.BOG, Biomes.SWAMP))
        );

        // Bayou
        BiomePlacement.replaceOverworld(
                Biomes.MANGROVE_SWAMP,
                NMLBiomes.BAYOU,
                0.4
        );

        BiomePlacement.replaceOverworld(
                Biomes.JUNGLE,
                NMLBiomes.BAYOU,
                0.1
        );

        // Dark Swamp
        transitionalBiome(Biomes.SWAMP, Biomes.DARK_FOREST, NMLBiomes.DARK_SWAMP);

        // Old Growth Forest
        BiomePlacement.replaceOverworld(
                Biomes.DARK_FOREST,
                NMLBiomes.OLD_GROWTH_FOREST,
                0.3
        );

        BiomePlacement.replaceOverworld(
                Biomes.OLD_GROWTH_BIRCH_FOREST,
                NMLBiomes.OLD_GROWTH_FOREST,
                0.2F
        );

        edgeBiome(NMLBiomes.OLD_GROWTH_FOREST, NMLBiomes.OLD_GROWTH_FOREST_EDGE);
        clearingBiome(NMLBiomes.OLD_GROWTH_FOREST, NMLBiomes.OLD_GROWTH_FOREST_CLEARING);
    }

    public static void transitionalBiome(ResourceKey<Biome> mainBiome, ResourceKey<Biome> secondaryBiome, ResourceKey<Biome> transitionalBiome) {
        BiomePlacement.addSubOverworld(
                mainBiome,
                transitionalBiome,
                CriterionBuilder.allOf(CriterionBuilder.neighbor(secondaryBiome), CriterionBuilder.not(CriterionBuilder.NEAR_INTERIOR))
        );

        BiomePlacement.addSubOverworld(
                secondaryBiome,
                transitionalBiome,
                CriterionBuilder.allOf(CriterionBuilder.alternate(transitionalBiome, mainBiome), CriterionBuilder.not(CriterionBuilder.NEAR_INTERIOR))
        );
    }

    public static void edgeBiome(ResourceKey<Biome> interiorBiome, ResourceKey<Biome> edgeBiome) {
        BiomePlacement.addSubOverworld(
                interiorBiome,
                edgeBiome,
                CriterionBuilder.anyOf(
                        CriterionBuilder.allOf(
                                CriterionBuilder.NEAR_BORDER,
                                CriterionBuilder.not(CriterionBuilder.NEAR_INTERIOR)
                        ),
                        CriterionBuilder.anyOf(CriterionBuilder.BEACHSIDE, CriterionBuilder.OCEANSIDE, CriterionBuilder.RIVERSIDE)
                )
        );
    }

    public static void clearingBiome(ResourceKey<Biome> interiorBiome, ResourceKey<Biome> clearingBiome) {
        BiomePlacement.addSubOverworld(
                interiorBiome,
                clearingBiome,
                CriterionBuilder.allOf(CriterionBuilder.deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, .05F), CriterionBuilder.NEAR_INTERIOR)
        );
    }
}
