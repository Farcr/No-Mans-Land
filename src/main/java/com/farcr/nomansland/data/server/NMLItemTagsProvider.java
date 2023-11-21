package com.farcr.nomansland.data.server;

public class NMLItemTagsProvider extends ItemTagsProvider {


    public NMLItemTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, BlockTagsProvider blocks, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, blocks.contentsGetter(), NoMansland.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
//        tag(Tags.Items.EGGS).add(ItemRegistry.UGG_EGG.get());
//
//        this.tag(ItemTags.LOGS_THAT_BURN)
//                .add(NMLBlockRegistry.PINE_LOG.get().asItem())
//                .add(NMLBlockRegistry.PINE_WOOD.get().asItem())
//                .add(NMLBlockRegistry.STRIPPED_PINE_LOG.get().asItem())
//                .add(NMLBlockRegistry.STRIPPED_PINE_WOOD.get().asItem());

        this.tag(ItemTags.PLANKS)
                .add(NMLBlocks.PINE_PLANKS.get().asItem());
    }
}

