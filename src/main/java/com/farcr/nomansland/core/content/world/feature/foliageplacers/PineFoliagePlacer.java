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

import java.util.Set;

public class PineFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<PineFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return foliagePlacerParts(instance).apply(instance, PineFoliagePlacer::new);
    });

    public Set<BlockPos> leafPositions;
    public Set<BlockPos> leafPositions50;
    public Set<BlockPos> leafPositions20;

    public PineFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    protected FoliagePlacerType<?> type() {
        return NMLFoliagePlacerType.PINE_FOLIAGE_PLACER.get();
    }

    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int foliageOffset) {
        /*for (int j = foliageOffset; j >= foliageOffset - foliageHeight; --j) {
            this.placeLeavesRow(reader, blockSetter, random, config, attachment.pos(), 2, j, attachment.doubleTrunk());
        }*/
        this.leafPositions = Sets.newHashSet();
        this.leafPositions50 = Sets.newHashSet();
        this.leafPositions20 = Sets.newHashSet();

        int numCanopies = (foliageHeight - 4) / 2;
        // Add tree topper
        for (int di = 0; di < 4; di++) {
            Direction d = Direction.from2DDataValue(di);
            this.leafPositions.add(attachment.pos().relative(d));
            this.leafPositions.add(attachment.pos().relative(d).relative(d.getClockWise()));
            this.leafPositions.add(attachment.pos().above().relative(d));
            this.leafPositions20.add(attachment.pos().above().relative(d).relative(d.getClockWise()));
            this.leafPositions.add(attachment.pos().above(2).relative(d));
        }
        this.leafPositions.add(attachment.pos().above(2));
        this.leafPositions.add(attachment.pos().above(3));
        this.leafPositions.add(attachment.pos().above(4));
        this.leafPositions20.add(attachment.pos().above(5));

        for (int i = 0; i < (numCanopies < 3 ? numCanopies : 3); i++) {
            placeSmallLayer(attachment.pos().below(2 + 2*i));
        }
        if (numCanopies >= 3) {
            for (int i = 0; i < numCanopies - 3; i++) {
                placeLargeLayer(attachment.pos().below(8 + 2*i));
            }
        }

        this.leafPositions.add(attachment.pos().below(1 + 2*numCanopies).north());
        this.leafPositions.add(attachment.pos().below(1 + 2*numCanopies).east());
        this.leafPositions.add(attachment.pos().below(1 + 2*numCanopies).south());
        this.leafPositions.add(attachment.pos().below(1 + 2*numCanopies).west());

        // Place the foliage
        for (BlockPos leafPos : leafPositions) {
            tryPlaceLeaf(level, blockSetter, random, config, leafPos);
        }
        for (BlockPos leafPos : leafPositions20) {
            if (random.nextInt(5) > 0)
                tryPlaceLeaf(level, blockSetter, random, config, leafPos);
        }
        for (BlockPos leafPos : leafPositions50) {
            if (random.nextInt(2) > 0)
                tryPlaceLeaf(level, blockSetter, random, config, leafPos);
        }
    }



    private void placeLargeLayer(BlockPos localOrigin) {
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if (Math.abs(x) != 2 || Math.abs(z) != 2) {
                    this.leafPositions.add(localOrigin.offset(x, 0, z));
                }
                if (Math.abs(x) < 2 && Math.abs(z) < 2) {
                    this.leafPositions.add(localOrigin.offset(x, 1, z));
                }
            }
        }

        for (int di = 0; di < 4; di++) {
            Direction d = Direction.from2DDataValue(di);
            this.leafPositions20.add(localOrigin.relative(d, 3));
            this.leafPositions50.add(localOrigin.relative(d, 3).relative(d.getClockWise()));
            this.leafPositions50.add(localOrigin.relative(d, 3).relative(d.getCounterClockWise()));
            this.leafPositions.add(localOrigin.above().relative(d, 2));
        }
    }

    private void placeSmallLayer(BlockPos localOrigin) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                this.leafPositions.add(localOrigin.offset(x, 0, z));
            }
        }

        for (int di = 0; di < 4; di++) {
            Direction d = Direction.from2DDataValue(di);
            this.leafPositions.add(localOrigin.relative(d, 2));
            this.leafPositions20.add(localOrigin.relative(d, 2).relative(d.getClockWise()));
            this.leafPositions20.add(localOrigin.relative(d, 2).relative(d.getCounterClockWise()));
            this.leafPositions.add(localOrigin.above().relative(d, 1));
        }
    }

    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return height;
    }

    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return false;
    }
}
