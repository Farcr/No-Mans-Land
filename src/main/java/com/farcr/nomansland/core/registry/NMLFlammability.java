package com.farcr.nomansland.core.registry;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;

public class NMLFlammability {

    public static void init() {
        registerFlammables();
    }
    public static void registerFlammables() {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;

        fireBlock.setFlammable(NMLBlocks.YELLOW_BIRCH_LEAVES.get(), 30, 60);
        fireBlock.setFlammable(NMLBlocks.AUTUMNAL_OAK_LEAVES.get(), 30, 60);

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
