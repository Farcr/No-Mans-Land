package com.farcr.nomansland.common.entity.variant;

import com.farcr.nomansland.common.registry.NMLVariants;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.Objects;
import java.util.Optional;

public record CodVariant(ResourceLocation texture, Optional<HolderSet<Biome>> biomes) {
    public static final Codec<CodVariant> DIRECT_CODEC = RecordCodecBuilder.create((record) -> record.group(
                    ResourceLocation.CODEC.fieldOf("texture").forGetter((config) -> config.texture),
                    RegistryCodecs.homogeneousList(Registries.BIOME).optionalFieldOf("biomes").forGetter(CodVariant::biomes))
            .apply(record, CodVariant::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, CodVariant> DIRECT_STREAM_CODEC;
    public static final Codec<Holder<CodVariant>> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<CodVariant>> STREAM_CODEC;
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            boolean equals;
            if (other instanceof CodVariant v) {
                equals = Objects.equals(this.texture, v.texture) && Objects.equals(this.biomes, v.biomes);
            } else {
                equals = false;
            }

            return equals;
        }
    }

    static {
        DIRECT_STREAM_CODEC = StreamCodec.composite(ResourceLocation.STREAM_CODEC, CodVariant::texture, ByteBufCodecs.optional(ByteBufCodecs.holderSet(Registries.BIOME)), CodVariant::biomes, CodVariant::new);
        CODEC = RegistryFileCodec.create(NMLVariants.COD_VARIANT_KEY, DIRECT_CODEC);
        STREAM_CODEC = ByteBufCodecs.holder(NMLVariants.COD_VARIANT_KEY, DIRECT_STREAM_CODEC);
    }
}


