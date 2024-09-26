//package com.farcr.nomansland.core.registry.integration;
////
////import com.farcr.nomansland.core.registry.NMLBlocks;
////import net.minecraft.world.item.Item;
////import net.minecraft.world.level.block.Block;
////import net.minecraft.world.level.block.Blocks;
////import vectorwing.farmersdelight.common.block.CabinetBlock;
////import vectorwing.farmersdelight.common.block.MushroomColonyBlock;
////import vectorwing.farmersdelight.common.item.MushroomColonyItem;
//
//import com.farcr.nomansland.core.registry.NMLBlocks;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
//import vectorwing.farmersdelight.common.block.CabinetBlock;
//import vectorwing.farmersdelight.common.block.MushroomColonyBlock;
//
//import java.util.function.Supplier;
//
//public class FDIntegration {
//    public static Supplier<? extends Block> mushroomColony() {
//        return () -> new MushroomColonyBlock(NMLBlocks.FIELD_MUSHROOM.asItem().builtInRegistryHolder(), Block.Properties.ofFullCopy(NMLBlocks.FIELD_MUSHROOM.get()));
//    }
//
////    public static Supplier<? extends Item> mushroomColonyItem() {
////        return () -> new MushroomColonyItem(NMLBlocks.FIELD_MUSHROOM_COLONY.get(), new Item.Properties());
////    }
//
//    public static Supplier<? extends Block> cabinetBlock() {
//        return () -> new CabinetBlock(Block.Properties.ofFullCopy(Blocks.BARREL));
//    }
//}
//// TODO: when FD is added
