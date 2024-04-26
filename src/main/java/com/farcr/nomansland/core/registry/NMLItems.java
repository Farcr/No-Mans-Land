package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.entity.BoatEntity;
import com.farcr.nomansland.core.content.item.BoatItem;
import com.farcr.nomansland.core.content.item.FuelItem;
import com.farcr.nomansland.core.content.item.MapleSyrupBottleItem;
import com.farcr.nomansland.core.registry.integration.FDIntegration;
import com.google.common.collect.Sets;
import net.minecraft.core.Direction;
import net.minecraft.world.item.*;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
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
//Foods
    public static final RegistryObject<Item> MASHED_POTATOES_WITH_MUSHROOMS = registerItem("mashed_potatoes_with_mushrooms",
            () -> new BowlFoodItem(new Item.Properties().food(NMLFoods.MASHED_POTATOES_WITH_MUSHROOMS).stacksTo(1)));
    public static final RegistryObject<Item> GRILLED_MUSHROOMS = registerItem("grilled_mushrooms",
            () -> new Item(new Item.Properties().food(NMLFoods.GRILLED_MUSHROOMS)));
    public static final RegistryObject<Item> FROG_LEG = registerItem("frog_leg",
            () -> new Item(new Item.Properties().food(NMLFoods.FROG_LEG)));
    public static final RegistryObject<Item> COOKED_FROG_LEG = registerItem("cooked_frog_leg",
            () -> new Item(new Item.Properties().food(NMLFoods.COOKED_FROG_LEG)));

    public static final RegistryObject<Item> FIELD_MUSHROOM_COLONY = registerIntegrationItem("field_mushroom_colony",
            !ModList.get().isLoaded("farmersdelight") ? () -> new Item(new Item.Properties())
                    : FDIntegration.mushroomColonyItem(), "farmersdelight");

//Materials
    public static final RegistryObject<Item> RESIN = registerItem("resin",
            () -> new FuelItem(new Item.Properties(), 1000 ));
//    !!!Depends on Alembic
//    public static final RegistryObject<Item> RESIN_OIL = registerItem("resin_oil",
//            () -> new Item(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
    public static final RegistryObject<Item> MAPLE_SYRUP = registerItem("maple_syrup",
            () -> new MapleSyrupBottleItem(new Item.Properties().food(NMLFoods.MAPLE_SYRUP_BOTTLE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
    public static final RegistryObject<Item> SCONCE_TORCH = registerItem("sconce_torch",
            () -> new StandingAndWallBlockItem(NMLBlocks.SCONCE_TORCH.get(), NMLBlocks.SCONCE_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));
    public static final RegistryObject<Item> SCONCE_SOUL_TORCH = registerItem("sconce_soul_torch",
            () -> new StandingAndWallBlockItem(NMLBlocks.SCONCE_SOUL_TORCH.get(), NMLBlocks.SCONCE_SOUL_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> TRINKET = ITEMS.register("trinket",
            () -> new Item(new Item.Properties()));

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

    public static final RegistryObject<Item> WALNUT_SIGN = registerItem("walnut_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), NMLBlocks.WALNUT_SIGN.get(), NMLBlocks.WALNUT_WALL_SIGN.get()));
    public static final RegistryObject<Item> WALNUT_HANGING_SIGN = registerItem("walnut_hanging_sign",
            () -> new HangingSignItem(NMLBlocks.WALNUT_HANGING_SIGN.get(), NMLBlocks.WALNUT_HANGING_WALL_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> WALNUT_BOAT = registerItem("walnut_boat",
            () -> new BoatItem(false, BoatEntity.Type.WALNUT, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> WALNUT_CHEST_BOAT = registerItem("walnut_chest_boat",
            () -> new BoatItem(true, BoatEntity.Type.WALNUT, new Item.Properties().stacksTo(1)));



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
            if (ModList.get().isLoaded("farmersdelight")) {
                event.accept(NMLBlocks.PINE_CABINET);
            }

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
            if (ModList.get().isLoaded("farmersdelight")) {
                event.accept(NMLBlocks.MAPLE_CABINET);
            }

            event.accept(NMLBlocks.WALNUT_LOG);
            event.accept(NMLBlocks.WALNUT_WOOD);
            event.accept(NMLBlocks.STRIPPED_WALNUT_LOG);
            event.accept(NMLBlocks.STRIPPED_WALNUT_WOOD);
            event.accept(NMLBlocks.WALNUT_PLANKS);
            event.accept(NMLBlocks.WALNUT_STAIRS);
            event.accept(NMLBlocks.WALNUT_SLAB);
            event.accept(NMLBlocks.TRIMMED_WALNUT_PLANKS);
            event.accept(NMLBlocks.WALNUT_FENCE);
            event.accept(NMLBlocks.WALNUT_FENCE_GATE);
            event.accept(NMLBlocks.WALNUT_DOOR);
            event.accept(NMLBlocks.WALNUT_TRAPDOOR);
            event.accept(NMLBlocks.WALNUT_PRESSURE_PLATE);
            event.accept(NMLBlocks.WALNUT_BUTTON);
            if (ModList.get().isLoaded("farmersdelight")) {
                event.accept(NMLBlocks.WALNUT_CABINET);
            }

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
            event.accept(NMLBlocks.DRIED_GRASS);
            event.accept(NMLBlocks.FIDDLEHEAD);
            event.accept(NMLBlocks.CATTAIL);
            event.accept(NMLBlocks.CLOVER_PATCH);
            event.accept(NMLBlocks.RED_FLOWERBED);
            event.accept(NMLBlocks.YELLOW_FLOWERBED);
            event.accept(NMLBlocks.BLUE_FLOWERBED);
            event.accept(NMLBlocks.VIOLET_FLOWERBED);
            event.accept(NMLBlocks.WHITE_FLOWERBED);
            event.accept(NMLBlocks.RED_LUPINE);
            event.accept(NMLBlocks.BLUE_LUPINE);
            event.accept(NMLBlocks.PINK_LUPINE);
            event.accept(NMLBlocks.YELLOW_LUPINE);
            event.accept(NMLBlocks.ACONITE);
            event.accept(NMLBlocks.WILD_MINT);
            event.accept(NMLBlocks.AUTUMN_CROCUS);
            event.accept(NMLBlocks.PEBBLES);
            event.accept(NMLBlocks.YELLOW_BIRCH_LEAVES);
            event.accept(NMLBlocks.YELLOW_BIRCH_SAPLING);
            event.accept(NMLBlocks.AUTUMNAL_OAK_LEAVES);
            event.accept(NMLBlocks.AUTUMNAL_OAK_SAPLING);
            event.accept(NMLBlocks.PALE_CHERRY_LEAVES);
            event.accept(NMLBlocks.PALE_CHERRY_SAPLING);
            event.accept(NMLBlocks.FIELD_MUSHROOM);
            if (ModList.get().isLoaded("farmersdelight")) {
                event.accept(NMLItems.FIELD_MUSHROOM_COLONY);
            }
            event.accept(NMLBlocks.FIELD_MUSHROOM_BLOCK);
            event.accept(NMLBlocks.DIRT_PATH);
            event.accept(NMLBlocks.MYCELIUM_PATH);
            event.accept(NMLBlocks.PODZOL_PATH);
            event.accept(NMLBlocks.SNOWY_GRASS_PATH);
            event.accept(NMLBlocks.SNOW_PATH);
            event.accept(NMLBlocks.GRAVEL_PATH);
            event.accept(NMLBlocks.SAND_PATH);
            event.accept(NMLBlocks.RED_SAND_PATH);


            event.accept(NMLBlocks.QUARTZITE);
            event.accept(NMLBlocks.QUARTZITE_CLUSTER);
            event.accept(NMLBlocks.BUDDING_QUARTZITE);
            event.accept(NMLBlocks.PETRIFIED_LOG);
            event.accept(NMLBlocks.PETRIFIED_WOOD);

            event.accept(NMLBlocks.REMAINS);

            event.accept(NMLBlocks.PINE_LOG);
            event.accept(NMLBlocks.PINE_LEAVES);
            event.accept(NMLBlocks.PINE_SAPLING);

            event.accept(NMLBlocks.MAPLE_LOG);
            event.accept(NMLBlocks.MAPLE_LEAVES);
            event.accept(NMLBlocks.MAPLE_SAPLING);
            event.accept(NMLBlocks.RED_MAPLE_LEAVES);
            event.accept(NMLBlocks.RED_MAPLE_SAPLING);

            event.accept(NMLBlocks.WALNUT_LOG);
            event.accept(NMLBlocks.WALNUT_LEAVES);
            event.accept(NMLBlocks.WALNUT_SAPLING);

        }
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(NMLBlocks.PINE_BOOKSHELF);
            event.accept(NMLBlocks.MAPLE_BOOKSHELF);
            event.accept(NMLBlocks.WALNUT_BOOKSHELF);
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
            event.accept(NMLItems.MAPLE_SIGN);
            event.accept(NMLItems.MAPLE_HANGING_SIGN);
            event.accept(NMLItems.WALNUT_SIGN);
            event.accept(NMLItems.WALNUT_HANGING_SIGN);
            event.accept(NMLItems.SCONCE_TORCH);
            event.accept(NMLItems.SCONCE_SOUL_TORCH);
            event.accept(NMLBlocks.TAP);
        }
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(NMLItems.MASHED_POTATOES_WITH_MUSHROOMS);
            event.accept(NMLItems.GRILLED_MUSHROOMS);
            event.accept(NMLItems.FROG_LEG);
            event.accept(NMLItems.COOKED_FROG_LEG);
            event.accept(NMLItems.MAPLE_SYRUP);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(NMLItems.PINE_BOAT);
            event.accept(NMLItems.PINE_CHEST_BOAT);
            event.accept(NMLItems.MAPLE_BOAT);
            event.accept(NMLItems.MAPLE_CHEST_BOAT);
            event.accept(NMLItems.WALNUT_BOAT);
            event.accept(NMLItems.WALNUT_CHEST_BOAT);
            event.accept(Items.BUNDLE);
        }
//        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
//            event.accept(NMLItems.RESIN_OIL);
//        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(NMLItems.RESIN);
//            event.accept(NMLItems.RESIN_OIL);
        }
    }
    public static RegistryObject<Item> registerItem(final String name, final Supplier<Item> supplier) {
        RegistryObject<Item> toReturn = ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(toReturn);
        return toReturn;
    }

    public static RegistryObject<Item> registerIntegrationItem(final String name, final Supplier<Item> supplier, String modId) {
        RegistryObject<Item> toReturn = ITEMS.register(name, supplier);
        if (ModList.get().isLoaded(modId)) {
        CREATIVE_TAB_ITEMS.add(toReturn);
        }
        return toReturn;
    }
    // I think I can just make it not register entirely? to deviate from the suggestion

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}