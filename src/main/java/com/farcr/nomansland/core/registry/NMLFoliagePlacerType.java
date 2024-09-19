package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.world.feature.foliageplacers.PineFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NMLFoliagePlacerType {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, NoMansLand.MODID);

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<PineFoliagePlacer>> PINE_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("pine_foliage_placer", () -> new FoliagePlacerType(PineFoliagePlacer.CODEC));
}
