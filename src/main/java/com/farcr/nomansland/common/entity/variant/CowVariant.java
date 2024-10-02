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

public record CowVariant(ResourceLocation texture, ResourceLocation babyTexture, Optional<Boolean> mooshroom,  Optional<HolderSet<Biome>> biomes) {
    public static final Codec<CowVariant> DIRECT_CODEC = RecordCodecBuilder.create((record) -> record.group(
                    ResourceLocation.CODEC.fieldOf("texture").forGetter(CowVariant::texture),
                    ResourceLocation.CODEC.fieldOf("baby_texture").forGetter(CowVariant::babyTexture),
                    Codec.BOOL.optionalFieldOf("mooshroom").forGetter(CowVariant::mooshroom),
                    RegistryCodecs.homogeneousList(Registries.BIOME).optionalFieldOf("biomes").forGetter(CowVariant::biomes))
            .apply(record, CowVariant::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, CowVariant> DIRECT_STREAM_CODEC;
    public static final Codec<Holder<CowVariant>> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<CowVariant>> STREAM_CODEC;

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            boolean equals;
            if (other instanceof CowVariant v) {
                equals = Objects.equals(this.texture, v.texture) && Objects.equals(this.biomes, v.biomes);
            } else {
                equals = false;
            }

            return equals;
        }
    }

    static {
        DIRECT_STREAM_CODEC = StreamCodec.composite(ResourceLocation.STREAM_CODEC, CowVariant::texture, ResourceLocation.STREAM_CODEC, CowVariant::babyTexture, ByteBufCodecs.optional(ByteBufCodecs.BOOL), CowVariant::mooshroom, ByteBufCodecs.optional(ByteBufCodecs.holderSet(Registries.BIOME)), CowVariant::biomes, CowVariant::new);
        CODEC = RegistryFileCodec.create(NMLVariants.COW_VARIANT_KEY, DIRECT_CODEC);
        STREAM_CODEC = ByteBufCodecs.holder(NMLVariants.COW_VARIANT_KEY, DIRECT_STREAM_CODEC);
    }
}


