package com.farcr.nomansland.core.content.block.cauldrons;

import com.farcr.nomansland.core.registry.NMLBlocks;
import com.farcr.nomansland.core.registry.NMLItems;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public enum BottledCauldronType {

    HONEY(NMLBlocks.HONEY_CAULDRON, Items.GLASS_BOTTLE.getDefaultInstance().getItemHolder(), Items.HONEY_BOTTLE.getDefaultInstance().getItemHolder()),
    MAPLE(NMLBlocks.MAPLE_SYRUP_CAULDRON, Items.GLASS_BOTTLE.getDefaultInstance().getItemHolder(), NMLItems.MAPLE_SYRUP_BOTTLE);

    private final Holder<Block> cauldron;
    private final Holder<Item> emptyBottle;
    private final Holder<Item> fullBottle;

    BottledCauldronType(Holder<Block> cauldron, Holder<Item> emptyBottle, Holder<Item> fullBottle) {
        this.cauldron = cauldron;
        this.emptyBottle = emptyBottle;
        this.fullBottle = fullBottle;
    }

    public Holder<Block> getCauldronBlock() {
        return cauldron;
    }

    public Holder<Item> getEmptyBottle() {
        return emptyBottle;
    }

    public Holder<Item> getFullBottle() {
        return fullBottle;
    }
}
