package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class NMLTags {
    public static final TagKey<Item> FIRESTARTERS = createItemTag("firestarters");
    public static final TagKey<Block> BONEMEAL_SPREADS = createBlockTag("bonemeal_spreads");
    public static final TagKey<Block> BONEMEAL_SPREADS_UPWARDS = createBlockTag("bonemeal_spreads_upwards");
    public static final TagKey<Block> CONIFEROUS_LOGS = createBlockTag("coniferous_logs");
    public static final TagKey<Block> FIREBOMB_EXPLODE = createBlockTag("firebomb_explode");
    private static TagKey<Item> createItemTag(String pName) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(NoMansLand.MODID, pName));
    }
    private static TagKey<Block> createBlockTag(String pName) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(NoMansLand.MODID, pName));
    }
}
