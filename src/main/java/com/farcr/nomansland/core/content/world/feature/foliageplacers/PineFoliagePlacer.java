package com.farcr.nomansland.core.content.world.feature.foliageplacers;

import com.farcr.nomansland.core.registry.NMLFoliagePlacerType;
import com.google.common.collect.Sets;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class PineFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<PineFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> foliagePlacerParts(instance).apply(instance, PineFoliagePlacer::new));

    public Set<BlockPos> leafPositions;
    public Set<BlockPos> leafPositions20;

    public PineFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    protected @NotNull FoliagePlacerType<?> type() {
        return NMLFoliagePlacerType.PINE_FOLIAGE_PLACER.get();
    }

    protected void createFoliage(@NotNull LevelSimulatedReader level, @NotNull FoliageSetter blockSetter, @NotNull RandomSource random, @NotNull TreeConfiguration config, int maxFreeTreeHeight, @NotNull FoliageAttachment attachment, int foliageHeight, int foliageRadius, int foliageOffset) {
        this.leafPositions = Sets.newHashSet();
        this.leafPositions20 = Sets.newHashSet();

        int numCanopies = (foliageHeight - 5) / 2;
        // Add tree topper
        for (int di = 0; di < 4; di++) {
            Direction d = Direction.from2DDataValue(di);
            this.leafPositions.add(attachment.pos().below(2).relative(d));
            this.leafPositions.add(attachment.pos().below(2).relative(d).relative(d.getClockWise()));
            this.leafPositions.add(attachment.pos().below().relative(d));
            this.leafPositions20.add(attachment.pos().below().relative(d).relative(d.getClockWise()));
            this.leafPositions.add(attachment.pos().relative(d));
        }
        this.leafPositions.add(attachment.pos());
        this.leafPositions.add(attachment.pos().above());
        this.leafPositions.add(attachment.pos().above(2));
        this.leafPositions20.add(attachment.pos().above(3));

        for (int i = 0; i < (Math.min(numCanopies, 3)); i++) {
            placeLayer(attachment.pos().below(4 + 2*i), foliageRadius);
        }
        if (numCanopies >= 3) {
            for (int i = 0; i < numCanopies - 3; i++) {
                placeLayer(attachment.pos().below(10 + 2*i), foliageRadius + 1);
            }
        }

        this.leafPositions.add(attachment.pos().below(3 + 2*numCanopies).north());
        this.leafPositions.add(attachment.pos().below(3 + 2*numCanopies).east());
        this.leafPositions.add(attachment.pos().below(3 + 2*numCanopies).south());
        this.leafPositions.add(attachment.pos().below(3 + 2*numCanopies).west());

        // Place the foliage
        for (BlockPos leafPos : leafPositions) {
            tryPlaceLeaf(level, blockSetter, random, config, leafPos);
        }
        for (BlockPos leafPos : leafPositions20) {
            if (random.nextFloat() > 0.1)
                tryPlaceLeaf(level, blockSetter, random, config, leafPos);
        }
    }



    private void placeLayer(BlockPos localOrigin, int size) {
        for (int x = -size; x <= size; x++) {
            for (int z = -size; z <= size; z++) {
                if (Math.abs(x) + Math.abs(z) < size + 2) {
                    this.leafPositions.add(localOrigin.offset(x, 0, z));
                }
                if (Math.abs(x) + Math.abs(z) < size) {
                    this.leafPositions.add(localOrigin.offset(x, 1, z));
                }
            }
        }
    }

    public int foliageHeight(@NotNull RandomSource random, int height, @NotNull TreeConfiguration config) {
        return height;
    }

    protected boolean shouldSkipLocation(@NotNull RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return false;
    }
}
