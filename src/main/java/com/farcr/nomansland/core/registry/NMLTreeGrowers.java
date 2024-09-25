package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public final class NMLTreeGrowers {
    public static final TreeGrower MAPLE = new TreeGrower(
            "maple",
            0.1F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "maple"))),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "large_maple"))),
            Optional.empty(),
            Optional.empty()
    );

    public static final TreeGrower RED_MAPLE = new TreeGrower(
            "red_maple",
            0.1F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "red_maple"))),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "large_red_maple"))),
            Optional.empty(),
            Optional.empty()
    );

    public static final TreeGrower PALE_CHERRY = new TreeGrower(
            "pale_cherry",
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "pale_cherry"))),
            Optional.empty()
    );

    public static final TreeGrower AUTUMNAL_OAK = new TreeGrower(
            "autumnal_oak",
            0.1F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(NMLFeatures.AUTUMNAL_OAK),
            Optional.of(NMLFeatures.LARGE_AUTUMNAL_OAK),
            Optional.empty(),
            Optional.empty()
    );

    public static final TreeGrower PINE = new TreeGrower(
            "pine",
            0.2f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(NMLFeatures.PINE),
            Optional.of(NMLFeatures.LARGE_PINE),
            Optional.empty(),
            Optional.empty()
    );

    public static final TreeGrower WALNUT = new TreeGrower(
            "walnut",
            0.1F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "walnut"))),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "small_walnut"))),
            Optional.empty(),
            Optional.empty()
    );

    public static final TreeGrower YELLOW_BIRCH = new TreeGrower(
            "yellow_birch",
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "yellow_birch"))),
            Optional.empty()
    );

    //TODO: FINISH
    public static final TreeGrower WILLOW = new TreeGrower(
            "maple",
            0.1F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "maple"))),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "large_maple"))),
            Optional.empty(),
            Optional.empty()
    );
}
