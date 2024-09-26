package com.farcr.nomansland.common.world.feature;

import com.farcr.nomansland.common.block.BeardMossBlock;
import com.farcr.nomansland.common.registry.NMLBlocks;
import com.farcr.nomansland.common.registry.NMLTreeDecoratorType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class BeardMossDecorator extends TreeDecorator {
    public static final MapCodec<BeardMossDecorator> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(f -> f.probability),
                    IntProvider.codec(0, 16).fieldOf("size").forGetter(f -> f.size)
            ).apply(instance, BeardMossDecorator::new)
    );
    protected final float probability;
    protected final IntProvider size;

    public BeardMossDecorator(float probability, IntProvider size) {
        this.probability = probability;
        this.size = size;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return NMLTreeDecoratorType.BEARD_MOSS.get();

    }

    @Override
    public void place(Context context) {
        for (BlockPos pos : context.leaves()) {
            if (context.level().isStateAtPosition(pos.below(), BlockState::isAir)) {
                if (context.random().nextFloat() < probability) {
                    int placedSize = size.sample(context.random());
                    for (int i = 1; i <= placedSize; i++) {
                        context.setBlock(pos.below(i), NMLBlocks.BEARD_MOSS.get().defaultBlockState().setValue(BeardMossBlock.HALF, DoubleBlockHalf.UPPER));
                    }
                    context.setBlock(pos.below(placedSize), NMLBlocks.BEARD_MOSS.get().defaultBlockState().setValue(BeardMossBlock.HALF, DoubleBlockHalf.LOWER));
                }
            }
        }
    }
}
