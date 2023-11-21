package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.common.block.LogBlock;
import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class NMLBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, NoMansLand.MODID);

    public static final RegistryObject<Block> PINE_PLANKS = registerBlock("pine_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> PINE_STAIRS = registerBlock("pine_stairs",
            () -> new StairBlock(() -> PINE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(NMLBlocks.PINE_PLANKS.get())));
    public static final RegistryObject<Block> PINE_SLAB = registerBlock("pine_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(NMLBlocks.PINE_PLANKS.get())));
    public static final RegistryObject<Block> PINE_LOG = registerBlock("pine_log",
            () -> new LogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> PINE_WOOD = registerBlock("pine_wood",
            () -> new LogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_PINE_LOG = registerBlock("stripped_pine_log",
            () -> new LogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_PINE_WOOD = registerBlock("stripped_pine_wood",
            () -> new LogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> PINE_FENCE = registerBlock("pine_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistryObject<Block> PINE_FENCE_GATE = registerBlock("pine_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), NMLWoodTypes.PINE));
    public static final RegistryObject<Block> PINE_LEAVES = registerBlock("pine_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    //     public static final RegistryObject<Block> PINE_SAPLING = registerBlock("pine_sapling",
//        () -> new SaplingBlock(() -> new SaplingBlock(new PineTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING))));
    public static final RegistryObject<Block> PINE_BUTTON = registerBlock("pine_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON),
                    NMLBlockSetTypes.PINE, 15, true));
    public static final RegistryObject<Block> PINE_PRESSURE_PLATE = registerBlock("pine_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), NMLBlockSetTypes.PINE));
    public static final RegistryObject<Block> PINE_DOOR = registerBlock("pine_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), NMLBlockSetTypes.PINE));
    public static final RegistryObject<Block> PINE_TRAPDOOR = registerBlock("pine_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), NMLBlockSetTypes.PINE));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return NMLItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
