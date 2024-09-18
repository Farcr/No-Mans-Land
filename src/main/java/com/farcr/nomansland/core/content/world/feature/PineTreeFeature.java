package com.farcr.nomansland.core.content.world.feature;

import com.farcr.nomansland.core.registry.NMLBlocks;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;

import java.util.List;
import java.util.OptionalInt;
import java.util.Set;

public class PineTreeFeature extends Feature<TreeConfiguration> {
    public Set<BlockPos> logPositions;
    public Set<BlockPos> leafPositions;
    public Set<BlockPos> leafPositions50;
    public Set<BlockPos> leafPositions20;

    public boolean large;

    public PineTreeFeature(boolean large, Codec<TreeConfiguration> codec) {
        super(codec);
        this.large = large;
    }

    @Override
    public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
        TreeConfiguration config = (TreeConfiguration) context.config();
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();

        this.logPositions = Sets.newHashSet();
        this.leafPositions = Sets.newHashSet();
        this.leafPositions50 = Sets.newHashSet();
        this.leafPositions20 = Sets.newHashSet();

        if (!isDirt(level.getBlockState(origin.below()))) {
            return false;
        }

        int trunkHeight = 2 + random.nextInt(3); // 2-4
        int numBigDiscs = large ? 1 + random.nextInt(3) : 0; // 1-3
        int numSmallDiscs = 1 + random.nextInt(3); // 1-3

        // Generate base trunk
        for (int i = 0; i < trunkHeight; i++) {
            this.logPositions.add(origin.above(i));
        }

        // Add first hardcoded leaf layer
        this.logPositions.add(origin.above(trunkHeight));
        this.leafPositions.add(origin.above(trunkHeight).north());
        this.leafPositions.add(origin.above(trunkHeight).east());
        this.leafPositions.add(origin.above(trunkHeight).south());
        this.leafPositions.add(origin.above(trunkHeight).west());

        // Add large layers
        for (int i = 0; i < numBigDiscs; i++) {
            placeLargeLayer(origin.above(trunkHeight + 1 + 2*i));
        }

        // Add small layers
        for (int i = 0; i < numSmallDiscs; i++) {
            placeSmallLayer(origin.above(trunkHeight + 1 + 2*numBigDiscs + 2*i));
        }

        // Add the topper as opposed to the subber idk
        BlockPos topperOrigin = origin.above(trunkHeight + 1 + 2*numBigDiscs + 2*numSmallDiscs);
        this.logPositions.add(topperOrigin);
        this.logPositions.add(topperOrigin.above());
        for (int di = 0; di < 4; di++) {
            Direction d = Direction.from2DDataValue(di);
            this.leafPositions.add(topperOrigin.relative(d));
            this.leafPositions.add(topperOrigin.relative(d).relative(d.getClockWise()));
            this.leafPositions.add(topperOrigin.above().relative(d));
            this.leafPositions20.add(topperOrigin.above().relative(d).relative(d.getClockWise()));
            this.leafPositions.add(topperOrigin.above(2).relative(d));
        }
        this.leafPositions.add(topperOrigin.above(2));
        this.leafPositions.add(topperOrigin.above(3));
        this.leafPositions.add(topperOrigin.above(4));
        this.leafPositions20.add(topperOrigin.above(5));

        // Actually place the tree
        if (origin.getY() >= level.getMinBuildHeight() + 1 && topperOrigin.getY() + 6 <= level.getMaxBuildHeight()) {
            // Check the tree isn't in the middle of a solid wall
            for (BlockPos logPos : logPositions) {
                if (!TreeFeature.validTreePos(level, logPos)) {
                    return false;
                }
            }
            // Place the tree
            for (BlockPos leafPos : leafPositions) {
                level.setBlock(leafPos, config.foliageProvider.getState(random, leafPos), 19);
            }
            for (BlockPos leafPos : leafPositions20) {
                if (random.nextInt(5) > 0)
                    level.setBlock(leafPos, config.foliageProvider.getState(random, leafPos), 19);
            }
            for (BlockPos leafPos : leafPositions50) {
                if (random.nextInt(2) > 0)
                    level.setBlock(leafPos, config.foliageProvider.getState(random, leafPos), 19);
            }
            for (BlockPos logPos : logPositions) {
                level.setBlock(logPos, config.trunkProvider.getState(random, logPos), 19);
            }
            return true;
        } else {
            return false;
        }
    }

    private void placeLargeLayer(BlockPos localOrigin) {
        this.logPositions.add(localOrigin);
        this.logPositions.add(localOrigin.above());

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
        this.logPositions.add(localOrigin);
        this.logPositions.add(localOrigin.above());

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
}
