package com.farcr.nomansland.core.content.block;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.LayeredCauldronBlock;

import java.util.Map;
import java.util.function.Predicate;

public class ResinCauldronBlock extends LayeredCauldronBlock {
    public ResinCauldronBlock(Properties pProperties, Predicate<Biome.Precipitation> pFillPredicate, Map<Item, CauldronInteraction> pInteractions) {
        super(pProperties, pFillPredicate, pInteractions);
    }
}
