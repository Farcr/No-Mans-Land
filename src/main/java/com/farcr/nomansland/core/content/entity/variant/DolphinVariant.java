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
import java.util.Optional;

public record DolphinVariant(ResourceLocation texture, Optional<HolderSet<Biome>> biomes) {
    public static final Codec<DolphinVariant> DIRECT_CODEC = RecordCodecBuilder.create((record) -> record.group(
                    ResourceLocation.CODEC.fieldOf("texture").forGetter((config) -> config.texture),
                    RegistryCodecs.homogeneousList(Registries.BIOME).optionalFieldOf("biomes").forGetter(DolphinVariant::biomes))
            .apply(record, DolphinVariant::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, DolphinVariant> DIRECT_STREAM_CODEC;
    public static final Codec<Holder<DolphinVariant>> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<DolphinVariant>> STREAM_CODEC;

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            boolean equals;
            if (other instanceof DolphinVariant v) {
                equals = Objects.equals(this.texture, v.texture) && Objects.equals(this.biomes, v.biomes);
            } else {
                equals = false;
            }

            return equals;
        }
    }

    static {
        DIRECT_STREAM_CODEC = StreamCodec.composite(ResourceLocation.STREAM_CODEC, DolphinVariant::texture, ByteBufCodecs.optional(ByteBufCodecs.holderSet(Registries.BIOME)), DolphinVariant::biomes, DolphinVariant::new);
        CODEC = RegistryFileCodec.create(NMLVariants.DOLPHIN_VARIANT_KEY, DIRECT_CODEC);
        STREAM_CODEC = ByteBufCodecs.holder(NMLVariants.DOLPHIN_VARIANT_KEY, DIRECT_STREAM_CODEC);
    }
}


