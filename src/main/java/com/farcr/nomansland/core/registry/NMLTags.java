package com.farcr.nomansland.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class NMLTags {
    public static final TagKey<Item> FIRESTARTERS = createKey("firestarters");
    private static TagKey<Item> createKey(String pName) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(pName));
    }
}
