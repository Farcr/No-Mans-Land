package com.farcr.nomansland.core.content.world.tree;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class RedMapleTreeGrower extends AbstractTreeGrower {
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_MAPLE = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NoMansLand.MODID, "red_maple"));

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
        return RED_MAPLE;
    }
}
