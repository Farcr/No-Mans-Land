package com.farcr.nomansland.core.content.block.fruit_trees;

import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public enum FruitType {
    APPLE_OAK(NMLBlocks.APPLE_FRUIT, NMLBlocks.APPLE_FRUIT_LEAVES, null, 3, new ItemStack(Items.APPLE), new VoxelShape[]{
            Block.box(6, 10, 7, 10, 14, 11),
            Block.box(6, 10, 7, 10, 14, 11),
            Block.box(5.5, 9, 6.5, 10.5, 14, 11.5),
            Block.box(5.5, 9, 6.5, 10.5, 14, 11.5),
            Block.box(5.5, 9, 6.5, 10.5, 14, 11.5)
    });

    private final Supplier<? extends Block> fruit;
    private final Supplier<? extends Block> fruitLeaves;
    private final Supplier<? extends Block> leaves;
    private final int growthSpeed;
    private final ItemStack fruitDrops;
    private final VoxelShape[] shapesByAge;

    private FruitType(Supplier<? extends Block> fruit, Supplier<? extends Block> fruitLeaves, Supplier<? extends Block> leaves, int growthSpeed, ItemStack fruitDrops, VoxelShape[] shapesByAge) {
        this.fruit = fruit;
        this.fruitLeaves = fruitLeaves;
        this.leaves = leaves;
        this.growthSpeed = growthSpeed;
        this.fruitDrops = fruitDrops;
        this.shapesByAge = shapesByAge;
    }

    public Supplier<? extends Block> getFruitBlock() {
        return fruit;
    }
    public Supplier<? extends Block> getFruitLeaves() {
        return fruitLeaves;
    }
    public Supplier<? extends Block> getLeaves() {
        return leaves;
    }
    public int getGrowthSpeed() {
        return growthSpeed;
    }
    public ItemStack getFruitDrops() {
        return fruitDrops;
    }
    public VoxelShape[] getShapesByAge() {
        return shapesByAge;
    }
}
