package com.farcr.nomansland.core.content.block.fruit_trees;

import com.farcr.nomansland.core.registry.NMLBlocks;
import com.farcr.nomansland.core.registry.NMLItems;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.shapes.VoxelShape;

public enum FruitType {
    APPLE_OAK(NMLBlocks.APPLE_FRUIT, NMLBlocks.APPLE_FRUIT_LEAVES, Blocks.OAK_LEAVES.defaultBlockState().getBlockHolder(), 3, Items.APPLE.getDefaultInstance().getItemHolder(), new VoxelShape[]{
            Block.box(6, 11, 7, 10, 15, 11),
            Block.box(6, 11, 7, 10, 15, 11),
            Block.box(5.5, 10, 6.5, 10.5, 15, 11.5),
            Block.box(5.5, 10, 6.5, 10.5, 15, 11.5),
            Block.box(5.5, 10, 6.5, 10.5, 15, 11.5)
    }),
    PEAR_AUTUMNAL_OAK(NMLBlocks.PEAR_FRUIT, NMLBlocks.PEAR_FRUIT_LEAVES, NMLBlocks.AUTUMNAL_OAK_LEAVES.get().defaultBlockState().getBlockHolder(), 3, NMLItems.PEAR, new VoxelShape[]{
            Block.box(6, 11, 7, 10, 15, 11),
            Block.box(6, 11, 7, 10, 15, 11),
            Block.box(5.5, 10, 6.5, 10.5, 15, 11.5),
            Block.box(5.5, 10, 6.5, 10.5, 15, 11.5),
            Block.box(5.5, 10, 6.5, 10.5, 15, 11.5)
    });

    private final Holder<Block> fruit;
    private final Holder<Block> fruitLeaves;
    private final Holder<Block> leaves;
    private final int growthSpeed;
    private final Holder<Item> fruitDrops;
    private final VoxelShape[] shapesByAge;

    FruitType(Holder<Block> fruit, Holder<Block> fruitLeaves, Holder<Block> leaves, int growthSpeed, Holder<Item> fruitDrops, VoxelShape[] shapesByAge) {
        this.fruit = fruit;
        this.fruitLeaves = fruitLeaves;
        this.leaves = leaves;
        this.growthSpeed = growthSpeed;
        this.fruitDrops = fruitDrops;
        this.shapesByAge = shapesByAge;
    }

    public Holder<Block> getFruitBlock() {
        return fruit;
    }
    public Holder<Block> getFruitLeaves() {
        return fruitLeaves;
    }
    public Holder<Block> getLeaves() {
        return leaves;
    }
    public int getGrowthSpeed() {
        return growthSpeed;
    }
    public Holder<Item> getFruitDrops() {
        return fruitDrops;
    }
    public VoxelShape[] getShapesByAge() {
        return shapesByAge;
    }
}
