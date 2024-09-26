package com.farcr.nomansland.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;

//Thank you greatest cappin for helping with this
public class SpreadPatchFeature extends Feature<RandomPatchConfiguration> {
    public SpreadPatchFeature(Codec<RandomPatchConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        RandomPatchConfiguration config = context.config();

        int tries = config.tries();
        int xzSpread = config.xzSpread();
        int ySpread = config.ySpread();

        BlockPos.MutableBlockPos pos = origin.mutable();
        for (int i = 0; i < tries; i++) {
            int x = (int) (origin.getX() + random.nextGaussian() * xzSpread);
            int z = (int) (origin.getZ() + random.nextGaussian() * xzSpread);
            int y = origin.getY() + random.nextInt(-ySpread, ySpread);
            pos.set(x, y, z);

            if (this.shouldPlace(level, random, pos)) {
                config.feature().value().place(level, context.chunkGenerator(), random, pos);
            }
        }
        return true;
    }

    // determines if we should place at a block - if it's not yet loaded, we don't place anything there!!
    // also does some dithering so that it'll smoothly fade if it encounters an unloaded chunk,
    // rather than a really ugly hard cutoff
    // .
    // this might be really laggy. give it a try to make sure it's not.
    private boolean shouldPlace(WorldGenLevel level, RandomSource random, BlockPos pos) {
        // exit early if this chunk doesn't yet exist
        if (!level.hasChunkAt(pos)) {
            return false;
        }

        // dithering
        int successfulTries = 0;
        BlockPos.MutableBlockPos checkPos = pos.mutable();
        for (int i = 0; i < 8; i++) {
            checkPos.offset((int) (random.nextGaussian() * 4), 0, (int) (random.nextGaussian() * 4));
            if (level.hasChunkAt(pos)) {
                successfulTries++;
            }
        }

        // totally arbitrary threshold
        return successfulTries > 5;
    }
}
