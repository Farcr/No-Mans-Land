package com.farcr.nomansland.core.content.world.feature.foliageplacers;

import com.farcr.nomansland.core.registry.NMLFoliagePlacerType;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

public class CypressFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<CypressFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> foliagePlacerParts(instance).apply(instance, CypressFoliagePlacer::new));

    public CypressFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    protected @NotNull FoliagePlacerType<?> type() {
        return NMLFoliagePlacerType.CYPRESS_FOLIAGE_PLACER.get();
    }

    protected void createFoliage(@NotNull LevelSimulatedReader level, @NotNull FoliageSetter blockSetter, @NotNull RandomSource random, @NotNull TreeConfiguration config, int maxFreeTreeHeight, @NotNull FoliageAttachment attachment, int foliageHeight, int foliageRadius, int foliageOffset) {
        for (int i = foliageOffset; i >= foliageOffset - 1; --i) {
            int radius = foliageRadius + attachment.radiusOffset() - (i == foliageOffset ? 1 : 0);
            this.placeLeavesRow(level, blockSetter, random, config, attachment.pos(), radius, i, attachment.doubleTrunk());
        }
    }

    public int foliageHeight(@NotNull RandomSource random, int height, @NotNull TreeConfiguration config) {
        return height;
    }

    protected boolean shouldSkipLocation(@NotNull RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return localX == range && localZ == range;
    }
}
