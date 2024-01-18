package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.entity.BoatEntity;
import com.farcr.nomansland.core.content.item.BoatItem;
import com.google.common.collect.Sets;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class NMLItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, NoMansLand.MODID);

    public static LinkedHashSet<RegistryObject<Item>> CREATIVE_TAB_ITEMS =
            Sets.newLinkedHashSet();

    public static final RegistryObject<Item> NO_MANS_GLOBE = ITEMS.register("no_mans_globe",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MASHED_POTATOES_WITH_MUSHROOMS = registerItem("mashed_potatoes_with_mushrooms",
            () -> new BowlFoodItem(new Item.Properties().food(NMLFoods.MASHED_POTATOES_WITH_MUSHROOMS).stacksTo(1)));
    public static final RegistryObject<Item> GRILLED_MUSHROOMS = registerItem("grilled_mushrooms",
            () -> new Item(new Item.Properties().food(NMLFoods.GRILLED_MUSHROOMS)));
    public static final RegistryObject<Item> FROG_LEG = registerItem("frog_leg",
            () -> new Item(new Item.Properties().food(NMLFoods.FROG_LEG)));
    public static final RegistryObject<Item> COOKED_FROG_LEG = registerItem("cooked_frog_leg",
            () -> new Item(new Item.Properties().food(NMLFoods.COOKED_FROG_LEG)));

    public static final RegistryObject<Item> BURIED_SPAWN_EGG = ITEMS.register("buried_spawn_egg",
            () -> new ForgeSpawnEggItem(NMLEntities.BURIED, 0x9a8977, 0x96775d, new Item.Properties()));

    public static final RegistryObject<Item> PINE_SIGN = registerItem("pine_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), NMLBlocks.PINE_SIGN.get(), NMLBlocks.PINE_WALL_SIGN.get()));
    public static final RegistryObject<Item> PINE_HANGING_SIGN = registerItem("pine_hanging_sign",
            () -> new HangingSignItem(NMLBlocks.PINE_HANGING_SIGN.get(), NMLBlocks.PINE_HANGING_WALL_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> PINE_BOAT = registerItem("pine_boat",
            () -> new BoatItem(false, BoatEntity.Type.PINE, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> PINE_CHEST_BOAT = registerItem("pine_chest_boat",
            () -> new BoatItem(true, BoatEntity.Type.PINE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MAPLE_SIGN = registerItem("maple_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), NMLBlocks.MAPLE_SIGN.get(), NMLBlocks.MAPLE_WALL_SIGN.get()));
    public static final RegistryObject<Item> MAPLE_HANGING_SIGN = registerItem("maple_hanging_sign",
            () -> new HangingSignItem(NMLBlocks.MAPLE_HANGING_SIGN.get(), NMLBlocks.MAPLE_HANGING_WALL_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> MAPLE_BOAT = registerItem("maple_boat",
            () -> new BoatItem(false, BoatEntity.Type.MAPLE, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MAPLE_CHEST_BOAT = registerItem("maple_chest_boat",
            () -> new BoatItem(true, BoatEntity.Type.MAPLE, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MOOSE_SPAWN_EGG = ITEMS.register("moose_spawn_egg",
            () -> new ForgeSpawnEggItem(NMLEntities.MOOSE, 0x8b4513 , 0xa52a2a, new Item.Properties()));



    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(NMLBlocks.FADED_STONE_BRICKS);
            event.accept(NMLBlocks.POLISHED_STONE);
            event.accept(NMLBlocks.POLISHED_STONE_STAIRS);
            event.accept(NMLBlocks.POLISHED_STONE_SLAB);

            event.accept(NMLBlocks.COBBLESTONE_BRICKS);
            event.accept(NMLBlocks.COBBLESTONE_BRICK_STAIRS);
            event.accept(NMLBlocks.COBBLESTONE_BRICK_SLAB);
            event.accept(NMLBlocks.COBBLESTONE_BRICK_WALL);
            event.accept(NMLBlocks.MOSSY_COBBLESTONE_BRICKS);
            event.accept(NMLBlocks.MOSSY_COBBLESTONE_BRICK_STAIRS);
            event.accept(NMLBlocks.MOSSY_COBBLESTONE_BRICK_SLAB);
            event.accept(NMLBlocks.MOSSY_COBBLESTONE_BRICK_WALL);

            event.accept(NMLBlocks.PINE_LOG);
            event.accept(NMLBlocks.PINE_WOOD);
            event.accept(NMLBlocks.STRIPPED_PINE_LOG);
            event.accept(NMLBlocks.STRIPPED_PINE_WOOD);
            event.accept(NMLBlocks.PINE_PLANKS);
            event.accept(NMLBlocks.PINE_STAIRS);
            event.accept(NMLBlocks.PINE_SLAB);
            event.accept(NMLBlocks.TRIMMED_PINE_PLANKS);
            event.accept(NMLBlocks.PINE_FENCE);
            event.accept(NMLBlocks.PINE_FENCE_GATE);
            event.accept(NMLBlocks.PINE_DOOR);
            event.accept(NMLBlocks.PINE_TRAPDOOR);
            event.accept(NMLBlocks.PINE_PRESSURE_PLATE);
            event.accept(NMLBlocks.PINE_BUTTON);

            event.accept(NMLBlocks.MAPLE_LOG);
            event.accept(NMLBlocks.MAPLE_WOOD);
            event.accept(NMLBlocks.STRIPPED_MAPLE_LOG);
            event.accept(NMLBlocks.STRIPPED_MAPLE_WOOD);
            event.accept(NMLBlocks.MAPLE_PLANKS);
            event.accept(NMLBlocks.MAPLE_STAIRS);
            event.accept(NMLBlocks.MAPLE_SLAB);
            event.accept(NMLBlocks.TRIMMED_MAPLE_PLANKS);
            event.accept(NMLBlocks.MAPLE_FENCE);
            event.accept(NMLBlocks.MAPLE_FENCE_GATE);
            event.accept(NMLBlocks.MAPLE_DOOR);
            event.accept(NMLBlocks.MAPLE_TRAPDOOR);
            event.accept(NMLBlocks.MAPLE_PRESSURE_PLATE);
            event.accept(NMLBlocks.MAPLE_BUTTON);

            event.accept(NMLBlocks.COD_BARREL);
            event.accept(NMLBlocks.SALMON_BARREL);
            event.accept(NMLBlocks.PUFFERFISH_BARREL);
            event.accept(NMLBlocks.TROPICAL_FISH_BARREL);

            event.accept(NMLBlocks.TRIMMED_OAK_PLANKS);
            event.accept(NMLBlocks.TRIMMED_SPRUCE_PLANKS);
            event.accept(NMLBlocks.TRIMMED_BIRCH_PLANKS);
            event.accept(NMLBlocks.TRIMMED_JUNGLE_PLANKS);
            event.accept(NMLBlocks.TRIMMED_ACACIA_PLANKS);
            event.accept(NMLBlocks.TRIMMED_DARK_OAK_PLANKS);
            event.accept(NMLBlocks.TRIMMED_CHERRY_PLANKS);
            event.accept(NMLBlocks.TRIMMED_MANGROVE_PLANKS);
            event.accept(NMLBlocks.TRIMMED_CRIMSON_PLANKS);
            event.accept(NMLBlocks.TRIMMED_WARPED_PLANKS);
            event.accept(NMLBlocks.TRIMMED_BAMBOO_PLANKS);
        }
        if (event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
            event.accept(NMLBlocks.WHITE_DYE_SACK);
            event.accept(NMLBlocks.LIGHT_GRAY_DYE_SACK);
            event.accept(NMLBlocks.GRAY_DYE_SACK);
            event.accept(NMLBlocks.BLACK_DYE_SACK);
            event.accept(NMLBlocks.BROWN_DYE_SACK);
            event.accept(NMLBlocks.RED_DYE_SACK);
            event.accept(NMLBlocks.ORANGE_DYE_SACK);
            event.accept(NMLBlocks.YELLOW_DYE_SACK);
            event.accept(NMLBlocks.LIME_DYE_SACK);
            event.accept(NMLBlocks.GREEN_DYE_SACK);
            event.accept(NMLBlocks.CYAN_DYE_SACK);
            event.accept(NMLBlocks.LIGHT_BLUE_DYE_SACK);
            event.accept(NMLBlocks.BLUE_DYE_SACK);
            event.accept(NMLBlocks.PURPLE_DYE_SACK);
            event.accept(NMLBlocks.MAGENTA_DYE_SACK);
            event.accept(NMLBlocks.PINK_DYE_SACK);
        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(NMLBlocks.GRASS_SPROUTS);
            event.accept(NMLBlocks.OAT_GRASS);
            event.accept(NMLBlocks.CLOVER_PATCH);
            event.accept(NMLBlocks.PEBBLES);
            event.accept(NMLBlocks.YELLOW_BIRCH_LEAVES);
            event.accept(NMLBlocks.YELLOW_BIRCH_SAPLING);
            event.accept(NMLBlocks.AUTUMNAL_OAK_LEAVES);
            event.accept(NMLBlocks.AUTUMNAL_OAK_SAPLING);
            event.accept(NMLBlocks.FIELD_MUSHROOM);
            event.accept(NMLBlocks.DIRT_PATH);
            event.accept(NMLBlocks.MYCELIUM_PATH);
            event.accept(NMLBlocks.PODZOL_PATH);
            event.accept(NMLBlocks.SNOWY_GRASS_PATH);
            event.accept(NMLBlocks.SNOW_PATH);
            event.accept(NMLBlocks.GRAVEL_PATH);
            event.accept(NMLBlocks.SAND_PATH);
            event.accept(NMLBlocks.RED_SAND_PATH);

            event.accept(NMLBlocks.REMAINS);

            event.accept(NMLBlocks.PINE_LOG);
            event.accept(NMLBlocks.PINE_LEAVES);
            event.accept(NMLBlocks.PINE_SAPLING);

            event.accept(NMLBlocks.MAPLE_LOG);
            event.accept(NMLBlocks.MAPLE_LEAVES);
            event.accept(NMLBlocks.MAPLE_SAPLING);
            event.accept(NMLBlocks.RED_MAPLE_LEAVES);
            event.accept(NMLBlocks.RED_MAPLE_SAPLING);

        }
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(NMLBlocks.PINE_BOOKSHELF);
            event.accept(NMLBlocks.SPRUCE_BOOKSHELF);
            event.accept(NMLBlocks.BIRCH_BOOKSHELF);
            event.accept(NMLBlocks.JUNGLE_BOOKSHELF);
            event.accept(NMLBlocks.ACACIA_BOOKSHELF);
            event.accept(NMLBlocks.DARK_OAK_BOOKSHELF);
            event.accept(NMLBlocks.CHERRY_BOOKSHELF);
            event.accept(NMLBlocks.MANGROVE_BOOKSHELF);
            event.accept(NMLBlocks.CRIMSON_BOOKSHELF);
            event.accept(NMLBlocks.WARPED_BOOKSHELF);
            event.accept(NMLBlocks.BAMBOO_BOOKSHELF);
            event.accept(NMLItems.PINE_SIGN);
            event.accept(NMLItems.PINE_HANGING_SIGN);
            event.accept(NMLBlocks.SCONCE_TORCH);
            event.accept(NMLBlocks.SCONCE_SOUL_TORCH);
        }
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(NMLItems.MASHED_POTATOES_WITH_MUSHROOMS);
            event.accept(NMLItems.GRILLED_MUSHROOMS);
            event.accept(NMLItems.FROG_LEG);
            event.accept(NMLItems.COOKED_FROG_LEG);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(NMLItems.PINE_BOAT);
            event.accept(NMLItems.PINE_CHEST_BOAT);
        }
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(NMLItems.BURIED_SPAWN_EGG);
        }


    }
    public static RegistryObject<Item> registerItem(final String name, final Supplier<Item> supplier) {
        RegistryObject<Item> toReturn = ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(toReturn);
        return toReturn;
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}