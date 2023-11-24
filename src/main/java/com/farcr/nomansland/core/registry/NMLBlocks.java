package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.block.GrassSproutsBlock;
import com.farcr.nomansland.core.content.block.LogBlock;
import com.farcr.nomansland.core.content.world.tree.PineTreeGrower;
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

    public static final RegistryObject<Block> GRASS_SPROUTS = registerBlock("grass_sprouts",
            () -> new GrassSproutsBlock(Block.Properties.copy(Blocks.FERN).offsetType(BlockBehaviour.OffsetType.XZ)));

    //Stone
    public static final RegistryObject<Block> FADED_STONE_BRICKS = registerBlock("faded_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistryObject<Block> POLISHED_STONE = registerBlock("polished_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistryObject<Block> POLISHED_STONE_STAIRS = registerBlock("polished_stone_stairs",
            () -> new StairBlock(() -> POLISHED_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(NMLBlocks.POLISHED_STONE.get())));
    public static final RegistryObject<Block> POLISHED_STONE_SLAB = registerBlock("polished_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(NMLBlocks.POLISHED_STONE.get())));


    //Pine
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
    public static final RegistryObject<Block> PINE_SAPLING = registerBlock("pine_sapling",
            () -> new SaplingBlock((new PineTreeGrower()), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> POTTED_PINE_SAPLING = BLOCKS.register("potted_pine_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.PINE_SAPLING,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING).noOcclusion()));
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
    //wip stuff under here
    public static final RegistryObject<Block> PINE_BOOKSHELF = registerBlock("pine_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> TRIMMED_PINE_PLANKS = registerBlock("trimmed_pine_planks",
            () -> new Block(BlockBehaviour.Properties.copy(NMLBlocks.PINE_PLANKS.get())));


    //Dye Sacks
    public static final RegistryObject<Block> WHITE_DYE_SACK = registerBlock("white_dye_sack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> LIGHT_GRAY_DYE_SACK = registerBlock("light_gray_dye_sack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_WOOL)));
    public static final RegistryObject<Block> GRAY_DYE_SACK = registerBlock("gray_dye_sack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRAY_WOOL)));
    public static final RegistryObject<Block> BLACK_DYE_SACK = registerBlock("black_dye_sack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL)));
    public static final RegistryObject<Block> BROWN_DYE_SACK = registerBlock("brown_dye_sack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BROWN_WOOL)));
    public static final RegistryObject<Block> RED_DYE_SACK = registerBlock("red_dye_sack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RED_WOOL)));
    public static final RegistryObject<Block> ORANGE_DYE_SACK = registerBlock("orange_dye_sack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.ORANGE_WOOL)));
    public static final RegistryObject<Block> YELLOW_DYE_SACK = registerBlock("yellow_dye_sack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.YELLOW_WOOL)));
    public static final RegistryObject<Block> LIME_DYE_SACK = registerBlock("lime_dye_sack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.LIME_WOOL)));
    public static final RegistryObject<Block> GREEN_DYE_SACK = registerBlock("green_dye_sack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GREEN_WOOL)));
    public static final RegistryObject<Block> CYAN_DYE_SACK = registerBlock("cyan_dye_sack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CYAN_WOOL)));
    public static final RegistryObject<Block> LIGHT_BLUE_DYE_SACK = registerBlock("light_blue_dye_sack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_WOOL)));
    public static final RegistryObject<Block> BLUE_DYE_SACK = registerBlock("blue_dye_sack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BLUE_WOOL)));
    public static final RegistryObject<Block> PURPLE_DYE_SACK = registerBlock("purple_dye_sack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.PURPLE_WOOL)));
    public static final RegistryObject<Block> MAGENTA_DYE_SACK = registerBlock("magenta_dye_sack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MAGENTA_WOOL)));
    public static final RegistryObject<Block> PINK_DYE_SACK = registerBlock("pink_dye_sack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.PINK_WOOL)));

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
