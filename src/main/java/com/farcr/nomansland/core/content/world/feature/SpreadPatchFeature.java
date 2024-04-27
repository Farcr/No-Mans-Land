package com.farcr.nomansland.core.content.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;

public class SpreadPatchFeature extends Feature<RandomPatchConfiguration> {
    public SpreadPatchFeature(Codec<RandomPatchConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<RandomPatchConfiguration> pContext) {
        BlockPos origin = pContext.origin();
        WorldGenLevel level = pContext.level();
        RandomSource random = pContext.random();
        RandomPatchConfiguration config = pContext.config();

        int tries = config.tries();
        int xzSpread = config.xzSpread();
        int ySpread = config.ySpread();

        for (int i = 0; i <tries ; i++) {
            int x = (int) (origin.getX() + random.nextGaussian() * xzSpread);
            int z = (int) (origin.getZ() + random.nextGaussian() * xzSpread);
            int y = origin.getY() + random.nextInt(-ySpread, ySpread);
            BlockPos Pos = new BlockPos(x,y,z);
            config.feature().value().place(level, pContext.chunkGenerator(), random, Pos);
        }
        return true;
    }
}
