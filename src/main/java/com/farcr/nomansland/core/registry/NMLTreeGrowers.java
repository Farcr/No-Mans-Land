package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

public final class NMLTreeGrowers {
    public static final TreeGrower MAPLE = new TreeGrower(
            "maple",
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "maple"))),
            Optional.empty()
    );

    public static final TreeGrower RED_MAPLE = new TreeGrower(
            "red_maple",
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "red_maple"))),
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
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "autumnal_oak"))),
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
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "small_walnut"))),
            Optional.empty()
    );

    public static final TreeGrower YELLOW_BIRCH = new TreeGrower(
            "yellow_birch",
            Optional.empty(),
            Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "yellow_birch"))),
            Optional.empty()
    );
}
