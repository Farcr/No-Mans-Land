package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.entity.variant.DolphinVariant;
import com.farcr.nomansland.core.content.entity.variant.FoxVariant;
import com.farcr.nomansland.core.content.entity.variant.GoatVariant;
import com.farcr.nomansland.core.content.entity.variant.SalmonVariant;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class NMLVariants {
    public static final ResourceKey<Registry<GoatVariant>> GOAT_VARIANT_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "variants/goat"));
    public static final ResourceKey<Registry<DolphinVariant>> DOLPHIN_VARIANT_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "variants/dolphin"));
    public static final ResourceKey<Registry<FoxVariant>> FOX_VARIANT_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "variants/fox"));
    public static final ResourceKey<Registry<SalmonVariant>> SALMON_VARIANT_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "variants/salmon"));

}
