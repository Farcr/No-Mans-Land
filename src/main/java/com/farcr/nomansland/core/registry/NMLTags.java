package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class NMLTags {
    public static final TagKey<Item> FIRESTARTERS = createItemTag("firestarters");
    public static final TagKey<Block> MUSHROOM_BLOCKS = createBlockTag("mushroom_blocks");
    public static final TagKey<Block> BONEMEAL_SPREADS = createBlockTag("bonemeal_spreads");
    public static final TagKey<Block> BONEMEAL_SPREADS_UPWARDS = createBlockTag("bonemeal_spreads_upwards");
    public static final TagKey<Block> CONIFEROUS_LOGS = createBlockTag("coniferous_logs");
    public static final TagKey<Biome> HAS_DENSE_FOG = createBiomeTag("has_dense_fog");
    public static final TagKey<Block> FIREBOMB_EXPLODE = createBlockTag("firebomb_explode");
    public static final TagKey<Block> HEAT_SOURCES = createBlockTag("heat_sources");
    public static final TagKey<EntityType<?>> ANCHOR_BLACKLIST = createEntityTag("anchor_blacklist");

    private static TagKey<Item> createItemTag(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, name));
    }

    private static TagKey<Block> createBlockTag(String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, name));
    }

    private static TagKey<Biome> createBiomeTag(String name) {
        return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, name));
    }

    private static TagKey<EntityType<?>> createEntityTag(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, name));
    }
}
