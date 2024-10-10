package com.farcr.nomansland.integration.biolith;

import com.farcr.nomansland.common.registry.NMLBiomes;
import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;

public class NMLBiomePlacements {

    public static void registerBiomes() {
        BiomePlacement.replaceOverworld(
                Biomes.DARK_FOREST,
                NMLBiomes.OLD_GROWTH_FOREST,
                0.25F
        );

        BiomePlacement.replaceOverworld(
                Biomes.OLD_GROWTH_BIRCH_FOREST,
                NMLBiomes.OLD_GROWTH_FOREST,
                0.1F
        );

        BiomePlacement.addSubOverworld(
                NMLBiomes.OLD_GROWTH_FOREST,
                NMLBiomes.OLD_GROWTH_FOREST_CLEARING,
                CriterionBuilder.allOf(CriterionBuilder.deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, .05F), CriterionBuilder.NEAR_INTERIOR)
        );

        BiomePlacement.addSubOverworld(
                NMLBiomes.OLD_GROWTH_FOREST,
                NMLBiomes.OLD_GROWTH_FOREST_EDGE,
                CriterionBuilder.anyOf(
                        CriterionBuilder.allOf(
                                CriterionBuilder.NEAR_BORDER,
                                CriterionBuilder.not(CriterionBuilder.NEAR_INTERIOR)
                        ),
                        CriterionBuilder.anyOf(CriterionBuilder.BEACHSIDE, CriterionBuilder.OCEANSIDE, CriterionBuilder.RIVERSIDE)
                )
        );
    }
}
