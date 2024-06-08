package com.farcr.nomansland.core.registry.integration;
//
//import com.farcr.nomansland.core.registry.NMLBlocks;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
//import vectorwing.farmersdelight.common.block.CabinetBlock;
//import vectorwing.farmersdelight.common.block.MushroomColonyBlock;
//import vectorwing.farmersdelight.common.item.MushroomColonyItem;

import java.util.function.Supplier;

public class FDIntegration {
    public static Supplier mushroomColony() {
//        return () -> new MushroomColonyBlock(Block.Properties.copy(NMLBlocks.FIELD_MUSHROOM.get()), () -> NMLBlocks.FIELD_MUSHROOM.get().asItem());
        return null;
    }

    public static Supplier mushroomColonyItem() {
//        return () -> new MushroomColonyItem(NMLBlocks.FIELD_MUSHROOM_COLONY.get(), new Item.Properties());
        return null;
    }

    public static Supplier cabinetBlock() {
//        return () -> new CabinetBlock(Block.Properties.copy(Blocks.BARREL));
        return null;
    }
}
// TODO: when FD is added
