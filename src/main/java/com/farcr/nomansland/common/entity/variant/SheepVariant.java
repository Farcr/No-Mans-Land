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

public record SheepVariant(ResourceLocation texture, ResourceLocation babyTexture, ResourceLocation shearedTexture, Optional<HolderSet<Biome>> biomes) {
    public static final Codec<SheepVariant> DIRECT_CODEC = RecordCodecBuilder.create((record) -> record.group(
                    ResourceLocation.CODEC.fieldOf("texture").forGetter(SheepVariant::texture),
                    ResourceLocation.CODEC.fieldOf("baby_texture").forGetter(SheepVariant::babyTexture),
                    ResourceLocation.CODEC.fieldOf("sheared_texture").forGetter(SheepVariant::shearedTexture),
                    RegistryCodecs.homogeneousList(Registries.BIOME).optionalFieldOf("biomes").forGetter(SheepVariant::biomes))
            .apply(record, SheepVariant::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, SheepVariant> DIRECT_STREAM_CODEC;
    public static final Codec<Holder<SheepVariant>> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<SheepVariant>> STREAM_CODEC;

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            boolean equals;
            if (other instanceof SheepVariant v) {
                equals = Objects.equals(this.texture, v.texture) && Objects.equals(this.biomes, v.biomes);
            } else {
                equals = false;
            }

            return equals;
        }
    }

    static {
        DIRECT_STREAM_CODEC = StreamCodec.composite(ResourceLocation.STREAM_CODEC, SheepVariant::texture, ResourceLocation.STREAM_CODEC, SheepVariant::babyTexture, ResourceLocation.STREAM_CODEC, SheepVariant::shearedTexture, ByteBufCodecs.optional(ByteBufCodecs.holderSet(Registries.BIOME)), SheepVariant::biomes, SheepVariant::new);
        CODEC = RegistryFileCodec.create(NMLVariants.SHEEP_VARIANT_KEY, DIRECT_CODEC);
        STREAM_CODEC = ByteBufCodecs.holder(NMLVariants.SHEEP_VARIANT_KEY, DIRECT_STREAM_CODEC);
    }
}


