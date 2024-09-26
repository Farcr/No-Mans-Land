package com.farcr.nomansland.common.world.feature.trunkplacers;

import com.farcr.nomansland.common.registry.NMLTrunkPlacerType;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class BranchyTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<BranchyTrunkPlacer> CODEC;
    private final IntProvider branchCount;
    private final IntProvider branchLength;
    private final IntProvider branchMinHeight;
    private final IntProvider branchMaxHeight;

    public BranchyTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, IntProvider branchCount, IntProvider branchLength, IntProvider branchMinHeight, IntProvider branchMaxHeight) {
        super(baseHeight, heightRandA, heightRandB);
        this.branchCount = branchCount;
        this.branchLength = branchLength;
        this.branchMinHeight = branchMinHeight;
        this.branchMaxHeight = branchMaxHeight;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return NMLTrunkPlacerType.BRANCHY_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
        setDirtAt(level, blockSetter,  random, pos.below(), config);
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        // Trunk
        for (int i = 0; i < freeTreeHeight; ++i) {
            this.placeLog(level, blockSetter, random, pos.above(i), config);
        }
        list.add(new FoliagePlacer.FoliageAttachment(pos.above(freeTreeHeight), 0, false));

        // Branches
        int branchCountSampled = this.branchCount.sample(random);
        int minHeight = this.branchMinHeight.sample(random);
        int maxHeight = freeTreeHeight - this.branchMaxHeight.sample(random);
        for (int i = 0; i < branchCountSampled; ++i) {
            int height = minHeight;
            if (minHeight < maxHeight)
                height = random.nextIntBetweenInclusive(minHeight, maxHeight);
            Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            BlockPos foliagePos = makeLimb(level, blockSetter, random, pos.above(height), this.branchLength.sample(random), dir, config);
            list.add(new FoliagePlacer.FoliageAttachment(foliagePos, 0, false));
        }

        return list;
    }

    private BlockPos makeLimb(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos basePos, int steps, Direction dir, TreeConfiguration config) {
        BlockPos currentBlockPos = basePos.relative(dir);
        BlockPos lastBlockPos = basePos;
        Function<BlockState, BlockState> function = (log) -> {
            return (BlockState) log.trySetValue(RotatedPillarBlock.AXIS, dir.getAxis());
        };
        for (int i = 0; i < steps; i++) {
            this.placeLog(level, blockSetter, random, currentBlockPos, config, function);
            lastBlockPos = currentBlockPos;
            currentBlockPos = currentBlockPos.relative(dir).relative(dir.getClockWise(), random.nextInt(3) - 1);
        }
        return lastBlockPos.above();
    }

    static {
        CODEC = RecordCodecBuilder.mapCodec((instance) -> {
            return trunkPlacerParts(instance).and(instance.group(IntProvider.codec(1, 8).fieldOf("branch_count").forGetter((tree) -> {
                return tree.branchCount;
            }), IntProvider.codec(1, 8).fieldOf("branch_length").forGetter((tree) -> {
                return tree.branchLength;
            }), IntProvider.codec(0, 8).fieldOf("branch_min_height").forGetter((tree) -> {
                return tree.branchMinHeight;
            }), IntProvider.codec(0, 8).fieldOf("branch_max_height").forGetter((tree) -> {
                return tree.branchMaxHeight;
            }))).apply(instance, BranchyTrunkPlacer::new);
        });
    }
}
