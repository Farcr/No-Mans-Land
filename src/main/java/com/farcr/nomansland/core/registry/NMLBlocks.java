package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NMLBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NoMansLand.MODID);

    public static final DeferredBlock<Block> PINE_PLANKS = registerBlock("pine_planks", () -> new Block(Block.Properties.copy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> PINE_STAIRS = registerBlock("pine_stairs", () -> new StairBlock(() -> NMLBlocks.PINE_PLANKS.get()
            .defaultBlockState(), Block.Properties.copy(NMLBlocks.PINE_PLANKS.get())));
    public static final DeferredBlock<Block> PINE_SLAB = registerBlock("pine_slab", () -> new SlabBlock(Block.Properties.copy(NMLBlocks.PINE_PLANKS.get())));
    public static final DeferredBlock<Block> PINE_LOG = registerBlock("pine_log", () -> new Block(Block.Properties.copy(Blocks.OAK_LOG)));




    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<Item> registerBlockItem(String name, DeferredBlock<T> block) {
        return NMLItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        NoMansLand.LOGGER.info("Registering NoMansLandBlocks for" + NoMansLand.MODID);
    }

}
