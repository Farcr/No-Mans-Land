package com.farcr.nomansland.core.content.entity.variant;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.registry.NMLVariants;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.animal.WolfVariant;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.Objects;
import java.util.Optional;

public class GoatVariants {
    public static final ResourceKey<GoatVariant> DEFAULT = createKey("default");

    public GoatVariants() {
    }

    private static ResourceKey<GoatVariant> createKey(String name) {
        return ResourceKey.create(NMLVariants.GOAT_VARIANT_KEY, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, name));
    }

    public static Holder<GoatVariant> getSpawnVariant(RegistryAccess registryAccess, Holder<Biome> biome) {
        Registry<GoatVariant> registry = registryAccess.registryOrThrow(NMLVariants.GOAT_VARIANT_KEY);
        Optional<Holder.Reference<GoatVariant>> variant = registry.holders()
                .filter((p_332674_) -> p_332674_.value().biomes().contains(biome)).findFirst()
                .or(() -> registry.getHolder(DEFAULT));
        Objects.requireNonNull(registry);
        return variant.or(registry::getAny).orElseThrow();
    }
}
