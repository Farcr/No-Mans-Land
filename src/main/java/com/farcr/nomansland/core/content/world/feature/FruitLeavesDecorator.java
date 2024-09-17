package com.farcr.nomansland.core.content.world.feature;

import com.farcr.nomansland.core.registry.NMLTreeDecoratorType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.HashSet;
import java.util.Set;

public class FruitLeavesDecorator extends TreeDecorator {
    public static final MapCodec<FruitLeavesDecorator> CODEC = RecordCodecBuilder.mapCodec(
            p_225996_ -> p_225996_.group(
                            Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(f -> f.probability),
                            Codec.intRange(0, 16).fieldOf("exclusion_radius_xz").forGetter(f -> f.exclusionRadiusXZ),
                            BlockStateProvider.CODEC.fieldOf("leaves_provider").forGetter(f -> f.leavesProvider),
                            BlockStateProvider.CODEC.fieldOf("fruit_provider").forGetter(f -> f.leavesProvider)
                            )
                    .apply(p_225996_, FruitLeavesDecorator::new)
    );
    protected final float probability;
    protected final int exclusionRadiusXZ;
    protected final BlockStateProvider leavesProvider;
    protected final BlockStateProvider fruitProvider;

    public FruitLeavesDecorator(float probability, int exclusionRadiusXZ, BlockStateProvider leavesProvider, BlockStateProvider fruitProvider) {
        this.probability = probability;
        this.exclusionRadiusXZ = exclusionRadiusXZ;
        this.leavesProvider = leavesProvider;
        this.fruitProvider = fruitProvider;
    }

    @Override
    public void place(TreeDecorator.Context context) {
        Set<BlockPos> set = new HashSet<>();
        RandomSource randomsource = context.random();
        if (randomsource.nextFloat() < this.probability) {
            for (BlockPos blockpos : Util.shuffledCopy(context.leaves(), randomsource)) {
                if (!set.contains(blockpos) && context.isAir(blockpos.below())) {
                    BlockPos blockpos2 = blockpos.offset(-this.exclusionRadiusXZ, 0, -this.exclusionRadiusXZ);
                    BlockPos blockpos3 = blockpos.offset(this.exclusionRadiusXZ, 0, this.exclusionRadiusXZ);

                    for (BlockPos blockpos4 : BlockPos.betweenClosed(blockpos2, blockpos3)) {
                        set.add(blockpos4.immutable());
                    }

                    context.setBlock(blockpos, this.leavesProvider.getState(randomsource, blockpos));
                    context.setBlock(blockpos.below(), this.fruitProvider.getState(randomsource, blockpos.below()));
                }
            }
        }
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return NMLTreeDecoratorType.FRUIT_LEAVES.get();
    }
}

