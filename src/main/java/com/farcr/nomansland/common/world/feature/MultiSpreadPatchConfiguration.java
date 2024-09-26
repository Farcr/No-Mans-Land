package com.farcr.nomansland.common.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public record MultiSpreadPatchConfiguration(int tries, int xzSpread, int ySpread, HolderSet<PlacedFeature> features) implements FeatureConfiguration {
    public static final Codec<MultiSpreadPatchConfiguration> CODEC = RecordCodecBuilder.create(
            record -> record.group(
                    ExtraCodecs.POSITIVE_INT.fieldOf("tries").orElse(128).forGetter(MultiSpreadPatchConfiguration::tries),
                    ExtraCodecs.NON_NEGATIVE_INT.fieldOf("xz_spread").orElse(7).forGetter(MultiSpreadPatchConfiguration::xzSpread),
                    ExtraCodecs.NON_NEGATIVE_INT.fieldOf("y_spread").orElse(3).forGetter(MultiSpreadPatchConfiguration::ySpread),
                    PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(MultiSpreadPatchConfiguration::features)
            ).apply(record, MultiSpreadPatchConfiguration::new)
    );
}
