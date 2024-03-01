package com.farcr.nomansland.core.registry.integration;

import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.block.MushroomColonyBlock;

import java.util.function.Supplier;

public class FDIntegration {
    public static Supplier mushroomColony() {
        return () -> new MushroomColonyBlock(Block.Properties.copy(NMLBlocks.FIELD_MUSHROOM.get()), () -> NMLBlocks.FIELD_MUSHROOM.get().asItem());
    }
}
