package com.farcr.nomansland.data.server;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class NMLItemTagsProvider extends ItemTagsProvider {


    public NMLItemTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, BlockTagsProvider blocks, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, blocks.contentsGetter(), NoMansLand.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

//        this.tag(ItemTags.LOGS_THAT_BURN)
//                .add(NMLBlocks.PINE_LOG.get().asItem())
//                .add(NMLBlocks.PINE_WOOD.get().asItem())
//                .add(NMLBlocks.STRIPPED_PINE_LOG.get().asItem())
//                .add(NMLBlocks.STRIPPED_PINE_WOOD.get().asItem());

//        this.tag(ItemTags.PLANKS)
//                .add(NMLBlocks.PINE_PLANKS.get().asItem());
    }
}

