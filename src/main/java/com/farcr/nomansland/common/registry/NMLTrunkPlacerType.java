package com.farcr.nomansland.common.registry;

import com.farcr.nomansland.NoMansLand;
import com.farcr.nomansland.common.world.feature.trunkplacers.BranchyTrunkPlacer;
import com.farcr.nomansland.common.world.feature.trunkplacers.CypressTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NMLTrunkPlacerType {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, NoMansLand.MODID);

    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<BranchyTrunkPlacer>> BRANCHY_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("branchy_trunk_placer", () -> new TrunkPlacerType<>(BranchyTrunkPlacer.CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<CypressTrunkPlacer>> CYPRESS_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("cypress_trunk_placer", () -> new TrunkPlacerType<>(CypressTrunkPlacer.CODEC));
}
