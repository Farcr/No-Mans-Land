package com.farcr.nomansland.core.registry;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;

public class NMLFlammability {

    public static void init() {
        registerFlammables();
    }
    public static void registerFlammables() {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        fireBlock.setFlammable(NMLBlocks.PINE_PLANKS.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.PINE_STAIRS.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.PINE_SLAB.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.PINE_FENCE.get(), 5, 20);
        fireBlock.setFlammable(NMLBlocks.PINE_LEAVES.get(), 30, 60);
//        fireBlock.setFlammable(NMLBlocks.PINE_BOOKSHELF.get(), 30, 20);
    }


}
