package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.block.*;
import com.farcr.nomansland.core.content.world.tree.HugeMushrooms;
import com.farcr.nomansland.core.content.world.tree.PineTreeGrower;
import com.google.common.collect.Sets;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class NMLBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, NoMansLand.MODID);

    public static LinkedHashSet<RegistryObject<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();

    public static final RegistryObject<Block> GRASS_SPROUTS = registerBlock("grass_sprouts",
            () -> new GrassSproutsBlock(Block.Properties.copy(Blocks.FERN).offsetType(BlockBehaviour.OffsetType.XZ)));

    public static final RegistryObject<Block> PEBBLES = registerBlock("pebbles",
            () -> new PebbleBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).noCollission().instabreak().sound(SoundType.STONE).pushReaction(PushReaction.DESTROY)));

    //Stone
    public static final RegistryObject<Block> FADED_STONE_BRICKS = registerBlock("faded_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistryObject<Block> POLISHED_STONE = registerBlock("polished_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistryObject<Block> POLISHED_STONE_STAIRS = registerBlock("polished_stone_stairs",
            () -> new StairBlock(() -> POLISHED_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(NMLBlocks.POLISHED_STONE.get())));
    public static final RegistryObject<Block> POLISHED_STONE_SLAB = registerBlock("polished_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(NMLBlocks.POLISHED_STONE.get())));

    //Trimmed Planks and Bookshelves
    public static final RegistryObject<Block> TRIMMED_OAK_PLANKS = registerBlock("trimmed_oak_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> SPRUCE_BOOKSHELF = registerBlock("spruce_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> TRIMMED_SPRUCE_PLANKS = registerBlock("trimmed_spruce_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_PLANKS)));
    public static final RegistryObject<Block> BIRCH_BOOKSHELF = registerBlock("birch_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> TRIMMED_BIRCH_PLANKS = registerBlock("trimmed_birch_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_PLANKS)));
    public static final RegistryObject<Block> JUNGLE_BOOKSHELF = registerBlock("jungle_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> TRIMMED_JUNGLE_PLANKS = registerBlock("trimmed_jungle_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_PLANKS)));
    public static final RegistryObject<Block> DARK_OAK_BOOKSHELF = registerBlock("dark_oak_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> TRIMMED_DARK_OAK_PLANKS = registerBlock("trimmed_dark_oak_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_PLANKS)));
    public static final RegistryObject<Block> ACACIA_BOOKSHELF = registerBlock("acacia_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> TRIMMED_ACACIA_PLANKS = registerBlock("trimmed_acacia_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS)));
    public static final RegistryObject<Block> MANGROVE_BOOKSHELF = registerBlock("mangrove_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> TRIMMED_MANGROVE_PLANKS = registerBlock("trimmed_mangrove_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.copy(Blocks.MANGROVE_PLANKS)));
    public static final RegistryObject<Block> CHERRY_BOOKSHELF = registerBlock("cherry_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> TRIMMED_CHERRY_PLANKS = registerBlock("trimmed_cherry_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.copy(Blocks.CHERRY_PLANKS)));
    public static final RegistryObject<Block> WARPED_BOOKSHELF = registerBlock("warped_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> TRIMMED_WARPED_PLANKS = registerBlock("trimmed_warped_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_PLANKS)));
    public static final RegistryObject<Block> CRIMSON_BOOKSHELF = registerBlock("crimson_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> TRIMMED_CRIMSON_PLANKS = registerBlock("trimmed_crimson_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS)));

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
    public static final RegistryObject<Block> PINE_SIGN = BLOCKS.register("pine_sign",
            () -> new NMLStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), NMLWoodTypes.PINE));
    public static final RegistryObject<Block> PINE_WALL_SIGN = BLOCKS.register("pine_wall_sign",
            () -> new NMLWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), NMLWoodTypes.PINE));
    public static final RegistryObject<Block> PINE_HANGING_SIGN = BLOCKS.register("pine_hanging_sign",
            () -> new NMLHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), NMLWoodTypes.PINE));
    public static final RegistryObject<Block> PINE_HANGING_WALL_SIGN = BLOCKS.register("pine_wall_hanging_sign",
            () -> new NMLWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), NMLWoodTypes.PINE));
    public static final RegistryObject<Block> PINE_BOOKSHELF = registerBlock("pine_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> TRIMMED_PINE_PLANKS = registerBlock("trimmed_pine_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.copy(NMLBlocks.PINE_PLANKS.get())));


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

    //Storage
    public static final RegistryObject<Block> COD_BARREL = registerBlock("cod_barrel",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BARREL)));
    public static final RegistryObject<Block> SALMON_BARREL = registerBlock("salmon_barrel",
            () -> new Block(BlockBehaviour.Properties.copy(NMLBlocks.COD_BARREL.get())));
    public static final RegistryObject<Block> PUFFERFISH_BARREL = registerBlock("pufferfish_barrel",
            () -> new Block(BlockBehaviour.Properties.copy(NMLBlocks.COD_BARREL.get())));
    public static final RegistryObject<Block> TROPICAL_FISH_BARREL = registerBlock("tropical_fish_barrel",
            () -> new Block(BlockBehaviour.Properties.copy(NMLBlocks.COD_BARREL.get())));

    //Mushrooms
    public static final RegistryObject<Block> FIELD_MUSHROOM = registerBlock("field_mushroom",
            () -> new MushroomBlock((BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).mapColor(MapColor.TERRACOTTA_WHITE)), HugeMushrooms.HUGE_FIELD_MUSHROOM));
    public static final RegistryObject<Block> FIELD_MUSHROOM_BLOCK = registerBlock("field_mushroom_block",
            () -> new HugeMushroomBlock((BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM_BLOCK))));
    public static final RegistryObject<Block> POTTED_FIELD_MUSHROOM = BLOCKS.register("potted_field_mushroom",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.FIELD_MUSHROOM,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_RED_MUSHROOM).noOcclusion()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        CREATIVE_TAB_ITEMS.add(registerBlockItem(name, toReturn));
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return NMLItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
