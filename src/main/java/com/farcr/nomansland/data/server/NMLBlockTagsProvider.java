package com.farcr.nomansland.data.server;

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
//
//        this.tag(BlockTags.LOGS_THAT_BURN)
//                .add(NMLBlockRegistry.PINE_LOG.get())
//                .add(NMLBlockRegistry.PINE_WOOD.get())
//                .add(NMLBlockRegistry.STRIPPED_PINE_LOG.get())
//                .add(NMLBlockRegistry.STRIPPED_PINE_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(NMLBlocks.PINE_PLANKS.get());
    }

    protected void registerForgeTags() {
    }

    protected void registerCompatibilityTags() {
    }
}