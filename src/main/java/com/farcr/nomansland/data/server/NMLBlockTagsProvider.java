package com.farcr.nomansland.data.server;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class NMLBlockTagsProvider extends BlockTagsProvider {
    public NMLBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, NoMansLand.MODID, existingFileHelper);

    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.registerModTags();
        this.registerMinecraftTags();
        this.registerForgeTags();
        this.registerCompatibilityTags();

        this.registerBlockMineables();
    }

    protected void registerBlockMineables() {
    }

    protected void registerModTags() {
    }

    protected void registerMinecraftTags() {

//      this.tag(BlockTags.LOGS_THAT_BURN)
//              .add(NMLBlocks.PINE_LOG.get())
//              .add(NMLBlocks.PINE_WOOD.get())
//              .add(NMLBlocks.STRIPPED_PINE_LOG.get())
//              .add(NMLBlocks.STRIPPED_PINE_WOOD.get());
//      this.tag(BlockTags.OVERWORLD_NATURAL_LOGS)
//              .add(NMLBlocks.PINE_LOG.get())
//              .add(NMLBlocks.PINE_WOOD.get())
//              .add(NMLBlocks.STRIPPED_PINE_LOG.get())
//              .add(NMLBlocks.STRIPPED_PINE_WOOD.get());
//      this.tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL)
//              .add(NMLBlocks.PINE_LOG.get())
//              .add(NMLBlocks.PINE_WOOD.get())
//              .add(NMLBlocks.STRIPPED_PINE_LOG.get())
//              .add(NMLBlocks.STRIPPED_PINE_WOOD.get());
//              this.tag(BlockTags.FENCES)
//              .add(NMLBlocks.PINE_FENCE.get());
//               this.tag(Tags.Blocks.FENCES_WOODEN)
//                .add(NMLBlocks.PINE_FENCE.get());
//
//        this.tag(BlockTags.PLANKS)
//                .add(NMLBlocks.PINE_PLANKS.get());
    }

    protected void registerForgeTags() {
    }

    protected void registerCompatibilityTags() {
    }
}
