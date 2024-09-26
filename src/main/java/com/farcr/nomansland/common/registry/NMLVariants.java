package com.farcr.nomansland.common.registry;

import com.farcr.nomansland.NoMansLand;
import com.farcr.nomansland.common.entity.variant.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.FrogVariant;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NMLVariants {
    public static final ResourceKey<Registry<DolphinVariant>> DOLPHIN_VARIANT_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "variants/dolphin"));
    public static final ResourceKey<Registry<FoxVariant>> FOX_VARIANT_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "variants/fox"));
    public static final ResourceKey<Registry<GoatVariant>> GOAT_VARIANT_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "variants/goat"));
    public static final ResourceKey<Registry<PigVariant>> PIG_VARIANT_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "variants/pig"));
    public static final ResourceKey<Registry<SalmonVariant>> SALMON_VARIANT_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "variants/salmon"));
    public static final ResourceKey<Registry<TurtleVariant>> TURTLE_VARIANT_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "variants/turtle"));

    public static final DeferredRegister<FrogVariant> FROG_VARIANTS = DeferredRegister.create(BuiltInRegistries.FROG_VARIANT, NoMansLand.MODID);
    public static final DeferredHolder<FrogVariant, FrogVariant> MUD = FROG_VARIANTS.register("mud", () -> new FrogVariant(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "textures/entity/variants/mud_frog.png")));

}
