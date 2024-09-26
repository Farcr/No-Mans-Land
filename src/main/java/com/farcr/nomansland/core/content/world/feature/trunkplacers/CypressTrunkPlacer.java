package com.farcr.nomansland.core.content.world.feature.trunkplacers;

import com.farcr.nomansland.core.registry.NMLTrunkPlacerType;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.ToFloatFunction;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class CypressTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<CypressTrunkPlacer> CODEC;
    protected final IntProvider rootHeight;
    protected final IntProvider branchCount;
    protected final IntProvider branchLength;
    protected final IntProvider branchMinHeight;
    protected final IntProvider branchMaxHeight;

    public CypressTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, IntProvider rootHeight, IntProvider branchCount, IntProvider branchLength, IntProvider branchMinHeight, IntProvider branchMaxHeight) {
        super(baseHeight, heightRandA, heightRandB);
        this.rootHeight = rootHeight;
        this.branchCount = branchCount;
        this.branchLength = branchLength;
        this.branchMinHeight = branchMinHeight;
        this.branchMaxHeight = branchMaxHeight;
    }

    @Override
    protected TrunkPlacerType<?> type() { return NMLTrunkPlacerType.CYPRESS_TRUNK_PLACER.get(); }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
        setDirtAt(level, blockSetter, random, pos.below(), config);
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        // Roots
        for (int x = 0; x < 2; ++x) {
            for (int z = 0; z < 2; ++z) {
                int height = rootHeight.sample(random);
                for (int i = 0; i < height; ++i) {
                    this.placeLog(level, blockSetter, random, pos.offset(x, i, z), config);
                }
            }
        }

        // Trunk
        BlockPos trunkPos = pos.offset(random.nextInt(2), 0, random.nextInt(2));
        for (int i = 0; i < freeTreeHeight; ++i) {
            this.placeLog(level, blockSetter, random, trunkPos.above(i), config);
        }
        list.add(new FoliagePlacer.FoliageAttachment(trunkPos.above(freeTreeHeight), 0, false));

        // Branches
        int branches = branchCount.sample(random);

        ArrayList<Direction> directions = Lists.newArrayList();
        while (directions.size() < branches) {
            Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            if (!directions.contains(dir)) {
                directions.add(dir);
            }
        }

        int minBranchHeight = branchMinHeight.sample(random);
        int maxBranchHeight = branchMaxHeight.sample(random);
        for (Direction dir : directions) {
            int height = random.nextIntBetweenInclusive(minBranchHeight, freeTreeHeight - maxBranchHeight);

            BlockPos foliagePos = makeLimb(level, blockSetter, random, trunkPos.above(height), branchLength.sample(random), dir, config);
            list.add(new FoliagePlacer.FoliageAttachment(foliagePos, 0, false));
        }

        return list;
    }

    private BlockPos makeLimb(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos basePos, int steps, Direction dir, TreeConfiguration config) {
        int wobble = random.nextInt(3) - 1;
        int wobbleStart = random.nextInt(steps);
        Function<BlockState, BlockState> function = (log) -> {
            return (BlockState) log.trySetValue(RotatedPillarBlock.AXIS, dir.getAxis());
        };
        for (int i = 0; i < steps; i++) {
            BlockPos currentBlockPos = basePos;
            if (i >= wobbleStart) {
                currentBlockPos = basePos.relative(dir, i+1).relative(dir.getClockWise(), wobble);
            } else {
                currentBlockPos = basePos.relative(dir, i+1);
            }

            this.placeLog(level, blockSetter, random, currentBlockPos, config, function);
        }
        return basePos.relative(dir, steps).relative(dir.getClockWise(), wobble).above();
    }

    static {
        CODEC = RecordCodecBuilder.mapCodec((instance) -> {
            return trunkPlacerParts(instance).and(instance.group(IntProvider.codec(1, 5).fieldOf("root_height").forGetter((tree) -> {
                return tree.rootHeight;
            }), IntProvider.codec(1, 8).fieldOf("branch_count").forGetter((tree) -> {
                return tree.branchCount;
            }), IntProvider.codec(1, 8).fieldOf("branch_length").forGetter((tree) -> {
                return tree.branchLength;
            }), IntProvider.codec(1, 8).fieldOf("branch_min_height").forGetter((tree) -> {
                return tree.branchMinHeight;
            }), IntProvider.codec(1, 8).fieldOf("branch_max_height").forGetter((tree) -> {
                return tree.branchMaxHeight;
            }))).apply(instance, CypressTrunkPlacer::new);
        });
    }
}
