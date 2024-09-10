package com.farcr.nomansland.core.content.world;

import com.farcr.nomansland.core.registry.NMLBiomes;
import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class NMLSurfaceData {
    private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource PODZOL = makeStateRule(Blocks.PODZOL);
    private static final SurfaceRules.RuleSource AIR = makeStateRule(Blocks.AIR);
    private static final SurfaceRules.RuleSource ICE = makeStateRule(Blocks.ICE);
    private static final SurfaceRules.RuleSource WATER = makeStateRule(Blocks.WATER);

    protected static SurfaceRules.RuleSource makeRules() {

        return SurfaceRules.ifTrue(
                SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                //Autumnal Forest
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(NMLBiomes.AUTUMNAL_FOREST),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.75), COARSE_DIRT), SurfaceRules.ifTrue(surfaceNoiseAbove(-0.95), PODZOL))),
                //Old Growth Forest
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(NMLBiomes.OLD_GROWTH_FOREST),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.5), COARSE_DIRT))),
                //Dark Forest
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.DARK_FOREST),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.5), COARSE_DIRT))),
                //Jungle
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.JUNGLE),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.5), COARSE_DIRT)))
            )
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }

    private static SurfaceRules.ConditionSource surfaceNoiseAbove(double value) {
        return SurfaceRules.noiseCondition(Noises.SURFACE, value / 8.25, Double.MAX_VALUE);
    }
}
