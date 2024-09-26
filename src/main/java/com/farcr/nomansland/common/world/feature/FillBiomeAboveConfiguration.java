package com.farcr.nomansland.common.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record FillBiomeAboveConfiguration(Holder<Biome> replacing, Holder<Biome> newBiome, int yAboveSeaLevel) implements FeatureConfiguration {
    public static final Codec<FillBiomeAboveConfiguration> CODEC = RecordCodecBuilder.create(
            record -> record.group(
                    Biome.CODEC.fieldOf("replacing").forGetter((otherConfig) -> otherConfig.replacing),
                    Biome.CODEC.fieldOf("newBiome").forGetter((otherConfig) -> otherConfig.newBiome),
                    Codec.INT.fieldOf("yAboveSeaLevel").forGetter((otherConfig) -> otherConfig.yAboveSeaLevel)
            ).apply(record, FillBiomeAboveConfiguration::new));
}
