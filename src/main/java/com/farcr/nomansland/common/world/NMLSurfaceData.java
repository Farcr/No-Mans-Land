package com.farcr.nomansland.common.world;

import com.farcr.nomansland.common.registry.NMLBiomes;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class NMLSurfaceData {
    private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource PODZOL = makeStateRule(Blocks.PODZOL);
    private static final SurfaceRules.RuleSource MUD = makeStateRule(Blocks.MUD);

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
                        SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.25), COARSE_DIRT))),
                //Bog
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(NMLBiomes.BOG),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(2.0), MUD))),
                //Bayou
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(NMLBiomes.BAYOU),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(2.0), MUD), SurfaceRules.ifTrue(surfaceNoiseAbove(1.5), PODZOL))),
                //Maple Forest
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(NMLBiomes.MAPLE_FOREST),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.25), PODZOL))),
                //Dark Forest
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.DARK_FOREST),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.25), PODZOL))),
                //Dark Forest, TODO: ADD SWAMPINESS TO WATER BORDER
                SurfaceRules.ifTrue(
                         SurfaceRules.isBiome(NMLBiomes.DARK_SWAMP),
                         SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.25), PODZOL))),
                //Jungle
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.JUNGLE),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.25), COARSE_DIRT)))
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
