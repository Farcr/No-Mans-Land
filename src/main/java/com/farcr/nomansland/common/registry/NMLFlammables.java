package com.farcr.nomansland.common.registry;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;

public class NMLFlammables {
    public static void register() {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;

        fireBlock.setFlammable(NMLBlocks.YELLOW_BIRCH_LEAVES.get(), 30, 60);
        fireBlock.setFlammable(NMLBlocks.AUTUMNAL_OAK_LEAVES.get(), 30, 60);
        fireBlock.setFlammable(NMLBlocks.PALE_CHERRY_LEAVES.get(), 30, 60);
        fireBlock.setFlammable(NMLBlocks.FROSTED_LEAVES.get(), 30, 60);

        fireBlock.setFlammable(NMLBlocks.APPLE_FRUIT_LEAVES.get(), 30, 60);
        fireBlock.setFlammable(NMLBlocks.PEAR_FRUIT_LEAVES.get(), 30, 60);

        fireBlock.setFlammable(NMLBlocks.GRASS_SPROUTS.get(), 30, 60);
        fireBlock.setFlammable(NMLBlocks.OAT_GRASS.get(), 30, 60);
        fireBlock.setFlammable(NMLBlocks.FIDDLEHEAD.get(), 30, 60);
        fireBlock.setFlammable(NMLBlocks.DRIED_GRASS.get(), 45, 60);
        fireBlock.setFlammable(NMLBlocks.FROSTED_GRASS.get(), 30, 60);
        fireBlock.setFlammable(NMLBlocks.BEARD_MOSS.get(), 30, 60);

        fireBlock.setFlammable(NMLBlocks.PINE_PLANKS.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.PINE_STAIRS.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.PINE_SLAB.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.PINE_FENCE.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.PINE_LEAVES.get(), 30, 60);
        fireBlock.setFlammable(NMLBlocks.PINE_BOOKSHELF.get(), 30, 20);
        fireBlock.setFlammable(NMLBlocks.TRIMMED_PINE_PLANKS.get(), 5, 20);

        fireBlock.setFlammable(NMLBlocks.MAPLE_PLANKS.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.MAPLE_STAIRS.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.MAPLE_SLAB.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.MAPLE_FENCE.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.MAPLE_LEAVES.get(), 30, 60);
        fireBlock.setFlammable(NMLBlocks.RED_MAPLE_LEAVES.get(), 30, 60);
        fireBlock.setFlammable(NMLBlocks.MAPLE_BOOKSHELF.get(), 30, 20);
        fireBlock.setFlammable(NMLBlocks.TRIMMED_MAPLE_PLANKS.get(), 5, 20);

        fireBlock.setFlammable(NMLBlocks.WALNUT_PLANKS.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.WALNUT_STAIRS.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.WALNUT_SLAB.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.WALNUT_FENCE.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.WALNUT_LEAVES.get(), 30, 60);
        fireBlock.setFlammable(NMLBlocks.WALNUT_BOOKSHELF.get(), 30, 20);
        fireBlock.setFlammable(NMLBlocks.TRIMMED_WALNUT_PLANKS.get(), 5, 20);

        fireBlock.setFlammable(NMLBlocks.WILLOW_PLANKS.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.WILLOW_STAIRS.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.WILLOW_SLAB.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.WILLOW_FENCE.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.WILLOW_LEAVES.get(), 30, 60);
        fireBlock.setFlammable(NMLBlocks.WILLOW_BOOKSHELF.get(), 30, 20);
        fireBlock.setFlammable(NMLBlocks.TRIMMED_WILLOW_PLANKS.get(), 5, 20);

        fireBlock.setFlammable(NMLBlocks.TRIMMED_OAK_PLANKS.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.SPRUCE_BOOKSHELF.get(), 30, 20);
        fireBlock.setFlammable(NMLBlocks.TRIMMED_SPRUCE_PLANKS.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.BIRCH_BOOKSHELF.get(), 30, 20);
        fireBlock.setFlammable(NMLBlocks.TRIMMED_BIRCH_PLANKS.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.JUNGLE_BOOKSHELF.get(), 30, 20);
        fireBlock.setFlammable(NMLBlocks.TRIMMED_JUNGLE_PLANKS.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.DARK_OAK_BOOKSHELF.get(), 30, 20);
        fireBlock.setFlammable(NMLBlocks.TRIMMED_DARK_OAK_PLANKS.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.CHERRY_BOOKSHELF.get(), 30, 20);
        fireBlock.setFlammable(NMLBlocks.TRIMMED_CHERRY_PLANKS.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.MANGROVE_BOOKSHELF.get(), 30, 20);
        fireBlock.setFlammable(NMLBlocks.TRIMMED_MANGROVE_PLANKS.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.BAMBOO_BOOKSHELF.get(), 30, 20);
        fireBlock.setFlammable(NMLBlocks.TRIMMED_BAMBOO_PLANKS.get(), 5, 20);
    }


}
