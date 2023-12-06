package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import com.google.common.collect.Sets;
import net.minecraft.world.item.*;
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
            () -> new BowlFoodItem(new Item.Properties().food(NMLFoods.MASHED_POTATOES_WITH_MUSHROOMS)));
    public static final RegistryObject<Item> GRILLED_MUSHROOMS = registerItem("grilled_mushrooms",
            () -> new Item(new Item.Properties().food(NMLFoods.GRILLED_MUSHROOMS)));

    public static final RegistryObject<Item> PINE_SIGN = registerItem("pine_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), NMLBlocks.PINE_SIGN.get(), NMLBlocks.PINE_WALL_SIGN.get()));
    public static final RegistryObject<Item> PINE_HANGING_SIGN = registerItem("pine_hanging_sign",
            () -> new HangingSignItem(NMLBlocks.PINE_HANGING_SIGN.get(), NMLBlocks.PINE_HANGING_WALL_SIGN.get(), new Item.Properties().stacksTo(16)));



    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(NMLBlocks.FADED_STONE_BRICKS);
            event.accept(NMLBlocks.POLISHED_STONE);
            event.accept(NMLBlocks.POLISHED_STONE_STAIRS);
            event.accept(NMLBlocks.POLISHED_STONE_SLAB);

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
            event.accept(NMLBlocks.PEBBLES);
            event.accept(NMLBlocks.FIELD_MUSHROOM);
            event.accept(NMLBlocks.FIELD_MUSHROOM_BLOCK);

            event.accept(NMLBlocks.PINE_LOG);
            event.accept(NMLBlocks.PINE_LEAVES);
            event.accept(NMLBlocks.PINE_SAPLING);

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
        }
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(NMLItems.MASHED_POTATOES_WITH_MUSHROOMS);
            event.accept(NMLItems.GRILLED_MUSHROOMS);
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