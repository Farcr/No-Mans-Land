package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class NMLDamageTypes {

    public static final ResourceKey<DamageType> SPIKE_POKE = registerDamageType("spike_poke");
    public static final ResourceKey<DamageType> SPIKE_IMPALE = registerDamageType("spike_impale");
    public static final ResourceKey<DamageType> SPIKE_FALL = registerDamageType("spike_fall");
    public static final ResourceKey<DamageType> SPIKE_SKEWER = registerDamageType("spike_skewer");


    public static ResourceKey<DamageType> registerDamageType(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(NoMansLand.MODID, name));
    }

    public static DamageSource getSimpleDamageSource(Level level, ResourceKey<DamageType> type) {
        return new DamageSource(level.registryAccess().registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(type));
    }
}
