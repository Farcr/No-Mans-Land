package com.farcr.nomansland.data.server;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.HashSet;
import java.util.Set;

public class NMLLootTableProvider extends BlockLootSubProvider {

    private final Set<Block> generatedLootTables = new HashSet<>();
    public NMLLootTableProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
//        dropSelf(NMLBlocks.COD_BARREL.get());
//        dropSelf(NMLBlocks.PUFFERFISH_BARREL.get());
//        dropSelf(NMLBlocks.SALMON_BARREL.get());
//        dropSelf(NMLBlocks.TROPICAL_FISH_BARREL.get());
//        dropSelf(NMLBlocks.RED_DYE_SACK.get());
//        dropSelf(NMLBlocks.PINK_DYE_SACK.get());
//        dropSelf(NMLBlocks.ORANGE_DYE_SACK.get());
//        dropSelf(NMLBlocks.YELLOW_DYE_SACK.get());
//        dropSelf(NMLBlocks.LIME_DYE_SACK.get());
//        dropSelf(NMLBlocks.GREEN_DYE_SACK.get());
//        dropSelf(NMLBlocks.CYAN_DYE_SACK.get());
//        dropSelf(NMLBlocks.BLUE_DYE_SACK.get());
//        dropSelf(NMLBlocks.LIGHT_BLUE_DYE_SACK.get());
//        dropSelf(NMLBlocks.MAGENTA_DYE_SACK.get());
//        dropSelf(NMLBlocks.PURPLE_DYE_SACK.get());
//        dropSelf(NMLBlocks.BLACK_DYE_SACK.get());
//        dropSelf(NMLBlocks.WHITE_DYE_SACK.get());
//        dropSelf(NMLBlocks.LIGHT_GRAY_DYE_SACK.get());
//        dropSelf(NMLBlocks.BROWN_DYE_SACK.get());


    }

    @Override
    protected void add(Block block, LootTable.Builder builder) {
        this.generatedLootTables.add(block);
        this.map.put(block.getLootTable(), builder);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return generatedLootTables;
    }
}