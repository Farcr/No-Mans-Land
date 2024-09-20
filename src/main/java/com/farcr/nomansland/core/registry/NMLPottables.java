package com.farcr.nomansland.core.registry;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;

public class NMLPottables {
    public static void register() {
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.PINE_SAPLING.getId(), NMLBlocks.POTTED_PINE_SAPLING);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.YELLOW_BIRCH_SAPLING.getId(), NMLBlocks.POTTED_YELLOW_BIRCH_SAPLING);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.AUTUMNAL_OAK_SAPLING.getId(), NMLBlocks.POTTED_AUTUMNAL_OAK_SAPLING);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.PALE_CHERRY_SAPLING.getId(), NMLBlocks.POTTED_PALE_CHERRY_SAPLING);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.MAPLE_SAPLING.getId(), NMLBlocks.POTTED_MAPLE_SAPLING);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.RED_MAPLE_SAPLING.getId(), NMLBlocks.POTTED_RED_MAPLE_SAPLING);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.WALNUT_SAPLING.getId(), NMLBlocks.POTTED_WALNUT_SAPLING);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.FIELD_MUSHROOM.getId(), NMLBlocks.POTTED_FIELD_MUSHROOM);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.ACONITE.getId(), NMLBlocks.POTTED_ACONITE);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.YELLOW_LUPINE.getId(), NMLBlocks.POTTED_YELLOW_LUPINE);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.BLUE_LUPINE.getId(), NMLBlocks.POTTED_BLUE_LUPINE);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.RED_LUPINE.getId(), NMLBlocks.POTTED_RED_LUPINE);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.PINK_LUPINE.getId(), NMLBlocks.POTTED_PINK_LUPINE);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.WILD_MINT.getId(), NMLBlocks.POTTED_WILD_MINT);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.PICKLEWEED.getId(), NMLBlocks.POTTED_PICKLEWEED);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.AUTUMN_CROCUS.getId(), NMLBlocks.POTTED_AUTUMN_CROCUS);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.SUCCULENT.getId(), NMLBlocks.POTTED_SUCCULENT);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NMLBlocks.BARREL_CACTUS.getId(), NMLBlocks.POTTED_BARREL_CACTUS);

    }
}
