package com.farcr.nomansland.core.content.block.cauldrons;

import com.farcr.nomansland.core.registry.NMLBlocks;
import com.farcr.nomansland.core.registry.NMLItems;
import com.farcr.nomansland.core.registry.NMLParticleTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

public enum NMLCauldronType {

    HONEY(NMLBlocks.HONEY_CAULDRON, Items.GLASS_BOTTLE.getDefaultInstance().getItemHolder(), Items.HONEY_BOTTLE.getDefaultInstance().getItemHolder(), Items.APPLE.getDefaultInstance().getItemHolder(), NMLItems.HONEYED_APPLE, null, NMLParticleTypes.MAPLE_SYRUP_DROPLET, true),
    MAPLE(NMLBlocks.MAPLE_SYRUP_CAULDRON, Items.GLASS_BOTTLE.getDefaultInstance().getItemHolder(), NMLItems.MAPLE_SYRUP_BOTTLE, NMLItems.PEAR, NMLItems.SYRUPED_PEAR, null, NMLParticleTypes.MAPLE_SYRUP_DROPLET, true),
    RESIN_OIL(NMLBlocks.RESIN_OIL_CAULDRON, Items.GLASS_BOTTLE.getDefaultInstance().getItemHolder(), NMLItems.RESIN_OIL_BOTTLE, null, null, null, NMLParticleTypes.OIL, false),
    RESIN(NMLBlocks.RESIN_CAULDRON, null, null, null, null, NMLItems.RESIN, NMLParticleTypes.RESIN_DROPLET, true);

    private final Holder<Block> cauldron;
    private final Holder<Item> emptyBottle;
    private final Holder<Item> fullBottle;
    private final Holder<Item> inputItem;
    private final Holder<Item> outputItem;
    private final Holder<Item> containedItem;
    private final DeferredHolder<ParticleType<?>, SimpleParticleType> particleType;
    private final boolean sticky;

    NMLCauldronType(Holder<Block> cauldron, Holder<Item> emptyBottle, Holder<Item> fullBottle, Holder<Item> inputItem, Holder<Item> outputItem, Holder<Item> containedItem, DeferredHolder<ParticleType<?>, SimpleParticleType> particleType, boolean sticky) {
        this.cauldron = cauldron;
        this.emptyBottle = emptyBottle;
        this.fullBottle = fullBottle;
        this.inputItem = inputItem;
        this.outputItem = outputItem;
        this.containedItem = containedItem;
        this.sticky = sticky;
        this.particleType = particleType;
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

    public Holder<Item> getInputItem() {
        return inputItem;
    }

    public Holder<Item> getOutputItem() {
        return outputItem;
    }

    public Holder<Item> getContainedItem() {
        return containedItem;
    }

    public DeferredHolder<ParticleType<?>, SimpleParticleType> getParticleType() {
        return particleType;
    }

    public boolean isSticky() {
        return sticky;
    }
}
