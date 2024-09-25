package com.farcr.nomansland.core.content.entity.variant;

import com.farcr.nomansland.core.registry.NMLVariants;
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

public record FoxVariant(ResourceLocation texture, ResourceLocation sleepingTexture, HolderSet<Biome> biomes) {
    public static final Codec<FoxVariant> DIRECT_CODEC = RecordCodecBuilder.create((record) -> record.group(
                    ResourceLocation.CODEC.fieldOf("texture").forGetter((config) -> config.texture),
                    ResourceLocation.CODEC.fieldOf("sleeping_texture").forGetter((config) -> config.sleepingTexture),
                    RegistryCodecs.homogeneousList(Registries.BIOME).fieldOf("biomes").forGetter(FoxVariant::biomes))
            .apply(record, FoxVariant::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, FoxVariant> DIRECT_STREAM_CODEC;
    public static final Codec<Holder<FoxVariant>> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<FoxVariant>> STREAM_CODEC;
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            boolean equals;
            if (other instanceof FoxVariant v) {
                equals = Objects.equals(this.texture, v.texture) && Objects.equals(this.biomes, v.biomes);
            } else {
                equals = false;
            }

            return equals;
        }
    }

    static {
        DIRECT_STREAM_CODEC = StreamCodec.composite(ResourceLocation.STREAM_CODEC, FoxVariant::texture, ResourceLocation.STREAM_CODEC, FoxVariant::sleepingTexture, ByteBufCodecs.holderSet(Registries.BIOME), FoxVariant::biomes, FoxVariant::new);
        CODEC = RegistryFileCodec.create(NMLVariants.FOX_VARIANT_KEY, DIRECT_CODEC);
        STREAM_CODEC = ByteBufCodecs.holder(NMLVariants.FOX_VARIANT_KEY, DIRECT_STREAM_CODEC);
    }
}


