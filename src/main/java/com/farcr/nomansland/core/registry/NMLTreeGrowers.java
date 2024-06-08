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
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "maple"))),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "large_maple"))),
            Optional.empty(),
            Optional.empty()
    );

    public static final TreeGrower RED_MAPLE = new TreeGrower(
            "red_maple",
            0.1F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "red_maple"))),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "large_red_maple"))),
            Optional.empty(),
            Optional.empty()
    );

    public static final TreeGrower PALE_CHERRY = new TreeGrower(
            "pale_cherry",
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "pale_cherry"))),
            Optional.empty()
    );

    public static final TreeGrower AUTUMNAL_OAK = new TreeGrower(
            "autumnal_oak",
            0.1F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "autumnal_oak"))),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "large_autumnal_oak"))),
            Optional.empty(),
            Optional.empty()
    );

    public static final TreeGrower PINE = new TreeGrower(
            "pine",
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "pine"))),
            Optional.empty()
    );

    public static final TreeGrower WALNUT = new TreeGrower(
            "walnut",
            0.1F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "walnut"))),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "small_walnut"))),
            Optional.empty(),
            Optional.empty()
    );

    public static final TreeGrower YELLOW_BIRCH = new TreeGrower(
            "yellow_birch",
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "yellow_birch"))),
            Optional.empty()
    );
}