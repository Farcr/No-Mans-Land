package com.farcr.nomansland.core.registry;

import net.minecraft.world.level.block.ComposterBlock;

public class NMLCompostabies {

    // TODO: replace this with new method
    public static void register() {
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.YELLOW_BIRCH_LEAVES.get(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.AUTUMNAL_OAK_LEAVES.get(), 0.30f);
        // TODO: re-add
//        ComposterBlock.COMPOSTABLES.put(NMLBlocks.PALE_CHERRY_LEAVES.get(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.RED_MAPLE_LEAVES.get(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.MAPLE_LEAVES.get(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.PINE_LEAVES.get(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.WALNUT_LEAVES.get(), 0.30f);

        // TODO: fix
//        ComposterBlock.COMPOSTABLES.put(NMLBlocks.YELLOW_BIRCH_SAPLING.get(), 0.30f);
//        ComposterBlock.COMPOSTABLES.put(NMLBlocks.AUTUMNAL_OAK_SAPLING.get(), 0.30f);
//        ComposterBlock.COMPOSTABLES.put(NMLBlocks.PALE_CHERRY_SAPLING.get(), 0.30f);
//        ComposterBlock.COMPOSTABLES.put(NMLBlocks.RED_MAPLE_SAPLING.get(), 0.30f);
//        ComposterBlock.COMPOSTABLES.put(NMLBlocks.MAPLE_SAPLING.get(), 0.30f);
//        ComposterBlock.COMPOSTABLES.put(NMLBlocks.PINE_SAPLING.get(), 0.30f);
//        ComposterBlock.COMPOSTABLES.put(NMLBlocks.WALNUT_SAPLING.get(), 0.30f);

        ComposterBlock.COMPOSTABLES.put(NMLBlocks.GRASS_SPROUTS.get(), 0.10f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.OAT_GRASS.get(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.FIDDLEHEAD.get(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.CATTAIL.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.ACONITE.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.PINK_LUPINE.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.RED_LUPINE.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.YELLOW_FLOWERBED.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.BLUE_LUPINE.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.AUTUMN_CROCUS.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.WILD_MINT.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.RAFFLESIA.get(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.BARREL_CACTUS.get(), 0.50f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.SUCCULENT.get(), 0.50f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.CUT_VINE.get(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.CUT_SUGAR_CANE.get(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.CLOVER_PATCH.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.VIOLET_FLOWERBED.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.WHITE_FLOWERBED.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.RED_FLOWERBED.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.YELLOW_FLOWERBED.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(NMLBlocks.BLUE_FLOWERBED.get(), 0.65f);
    }
}
