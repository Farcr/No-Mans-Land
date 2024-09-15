package com.farcr.nomansland.core.content.block.fruit_trees;

import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public enum FruitType {
    APPLE(NMLBlocks.APPLE_FRUIT, NMLBlocks.APPLE_FRUIT_LEAVES, new ItemStack(Items.APPLE));

    private final Supplier<? extends Block> fruit;
    private final Supplier<? extends Block> fruitLeaves;
    private final ItemStack fruitDrops;

    private FruitType(Supplier<? extends Block> fruit, Supplier<? extends Block> fruitLeaves, ItemStack fruitDrops) {
        this.fruit = fruit;
        this.fruitLeaves = fruitLeaves;
        this.fruitDrops = fruitDrops;
    }

    public Supplier<? extends Block> getFruitBlock() {
        return fruit;
    }

    public Supplier<? extends Block> getFruitLeaves() {
        return fruitLeaves;
    }

    public ItemStack getFruitDrops() {
        return fruitDrops;
    }
}
