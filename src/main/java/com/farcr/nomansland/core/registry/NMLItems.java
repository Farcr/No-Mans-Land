package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class NMLItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, NoMansLand.MODID);

    public static final RegistryObject<Item> MASHED_POTATOES_WITH_MUSHROOMS = registerItem("mashed_potatoes_with_mushrooms",
            () -> new BowlFoodItem(new Item.Properties().food(NMLFoods.MASHED_POTATOES_WITH_MUSHROOMS)));

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
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
        }
        if(event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
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
        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(NMLBlocks.GRASS_SPROUTS);
            event.accept(NMLBlocks.FIELD_MUSHROOM);
            event.accept(NMLBlocks.FIELD_MUSHROOM_BLOCK);

            event.accept(NMLBlocks.PINE_LOG);
            event.accept(NMLBlocks.PINE_LEAVES);
            event.accept(NMLBlocks.PINE_SAPLING);

        }
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(NMLBlocks.PINE_BOOKSHELF);
        }
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(NMLItems.MASHED_POTATOES_WITH_MUSHROOMS);
        }


    }
    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item) {
        RegistryObject<T> toReturn = ITEMS.register(name, item);
        return toReturn;
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}