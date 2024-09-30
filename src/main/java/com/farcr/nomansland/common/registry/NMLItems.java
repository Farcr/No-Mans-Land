package com.farcr.nomansland.common.registry;

import com.farcr.nomansland.NoMansLand;
import com.farcr.nomansland.common.entity.BoatEntity;
import com.farcr.nomansland.common.item.BoatItem;
import com.farcr.nomansland.common.item.*;
import com.google.common.collect.Sets;
import net.minecraft.core.Direction;
import net.minecraft.world.item.*;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class NMLItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NoMansLand.MODID);
    public static final DeferredItem<Item> NO_MANS_GLOBE = ITEMS.register("no_mans_globe",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TRINKET = ITEMS.register("trinket",
            () -> new Item(new Item.Properties()));
    public static LinkedHashSet<DeferredItem<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();
    //Foods
    public static final DeferredItem<Item> MASHED_POTATOES_WITH_MUSHROOMS = registerItem("mashed_potatoes_with_mushrooms",
            () -> new Item(new Item.Properties().food(NMLFoods.MASHED_POTATOES_WITH_MUSHROOMS).stacksTo(1)));
    public static final DeferredItem<Item> GRILLED_MUSHROOMS = registerItem("grilled_mushrooms",
            () -> new Item(new Item.Properties().food(NMLFoods.GRILLED_MUSHROOMS)));

    public static final DeferredItem<Item> FROG_LEG = registerItem("frog_leg",
            () -> new Item(new Item.Properties().food(NMLFoods.FROG_LEG)));
    public static final DeferredItem<Item> COOKED_FROG_LEG = registerItem("cooked_frog_leg",
            () -> new Item(new Item.Properties().food(NMLFoods.COOKED_FROG_LEG)));
    public static final DeferredItem<Item> RAW_HORSE = registerItem("raw_horse",
            () -> new Item(new Item.Properties().food(NMLFoods.RAW_HORSE)));
    public static final DeferredItem<Item> HORSE_STEAK = registerItem("horse_steak",
            () -> new Item(new Item.Properties().food(NMLFoods.HORSE_STEAK)));
    public static final DeferredItem<Item> RAW_VENISON = registerItem("raw_venison",
            () -> new Item(new Item.Properties().food(NMLFoods.RAW_VENISON)));
    public static final DeferredItem<Item> COOKED_VENISON = registerItem("cooked_venison",
            () -> new Item(new Item.Properties().food(NMLFoods.COOKED_VENISON)));

    public static final DeferredItem<Item> PEAR = registerItem("pear",
            () -> new Item(new Item.Properties().food(NMLFoods.PEAR)));
    public static final DeferredItem<Item> SYRUPED_PEAR = registerItem("syruped_pear",
            () -> new MapleFoodItem(new Item.Properties().food(NMLFoods.SYRUPED_PEAR)));
    public static final DeferredItem<Item> PEAR_COBBLER = registerItem("pear_cobbler",
            () -> new Item(new Item.Properties().food(NMLFoods.PEAR_COBBLER)));
    //TODO: FD compat pear juice and cobbler slice
    public static final DeferredItem<Item> HONEYED_APPLE = registerItem("honeyed_apple",
            () -> new HoneyFoodItem(new Item.Properties().food(NMLFoods.HONEYED_APPLE)));
    public static final DeferredItem<Item> WALNUTS = registerItem("walnuts",
            () -> new Item(new Item.Properties().food(NMLFoods.WALNUTS)));

    public static final DeferredItem<Item> MAPLE_SYRUP_BOTTLE = registerItem("maple_syrup_bottle",
            () -> new MapleSyrupBottleItem(new Item.Properties().food(NMLFoods.MAPLE_SYRUP_BOTTLE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));


    //Materials
//    public static final DeferredItem<Item> FIELD_MUSHROOM_COLONY = registerIntegrationItem("field_mushroom_colony",
//            ModList.get().isLoaded("farmersdelight") ? FDIntegration.mushroomColonyItem() : null, "farmersdelight");
    public static final DeferredItem<Item> RESIN = registerItem("resin",
            () -> new FuelItem(new Item.Properties(), 1000));
    public static final DeferredItem<Item> RESIN_OIL_BOTTLE = registerItem("resin_oil_bottle",
            () -> new Item(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));

    public static final DeferredItem<Item> SCONCE_TORCH = registerItem("sconce_torch",
            () -> new StandingAndWallBlockItem(NMLBlocks.SCONCE_TORCH.get(), NMLBlocks.SCONCE_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));
    public static final DeferredItem<Item> SCONCE_SOUL_TORCH = registerItem("sconce_soul_torch",
            () -> new StandingAndWallBlockItem(NMLBlocks.SCONCE_SOUL_TORCH.get(), NMLBlocks.SCONCE_SOUL_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));

    public static final DeferredItem<Item> FIREBOMB = registerItem("firebomb",
            () -> new FirebombItem(new Item.Properties().stacksTo(8)));
    public static final DeferredItem<Item> EXPLOSIVE = registerItem("explosive",
            () -> new ExplosiveItem(new Item.Properties().stacksTo(8)));

    public static final DeferredItem<Item> WOODEN_SCAFFOLDING = registerItem("wooden_scaffolding",
            () -> new ScaffoldingBlockItem(NMLBlocks.WOODEN_SCAFFOLDING.get(), new Item.Properties()));

    public static final DeferredItem<Item> PINE_SIGN = registerItem("pine_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), NMLBlocks.PINE_SIGN.get(), NMLBlocks.PINE_WALL_SIGN.get()));
    public static final DeferredItem<Item> PINE_HANGING_SIGN = registerItem("pine_hanging_sign",
            () -> new HangingSignItem(NMLBlocks.PINE_HANGING_SIGN.get(), NMLBlocks.PINE_HANGING_WALL_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> PINE_BOAT = registerItem("pine_boat",
            () -> new BoatItem(false, BoatEntity.Type.PINE, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> PINE_CHEST_BOAT = registerItem("pine_chest_boat",
            () -> new BoatItem(true, BoatEntity.Type.PINE, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MAPLE_SIGN = registerItem("maple_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), NMLBlocks.MAPLE_SIGN.get(), NMLBlocks.MAPLE_WALL_SIGN.get()));
    public static final DeferredItem<Item> MAPLE_HANGING_SIGN = registerItem("maple_hanging_sign",
            () -> new HangingSignItem(NMLBlocks.MAPLE_HANGING_SIGN.get(), NMLBlocks.MAPLE_HANGING_WALL_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> MAPLE_BOAT = registerItem("maple_boat",
            () -> new BoatItem(false, BoatEntity.Type.MAPLE, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> MAPLE_CHEST_BOAT = registerItem("maple_chest_boat",
            () -> new BoatItem(true, BoatEntity.Type.MAPLE, new Item.Properties().stacksTo(1)));
//    public static final DeferredItem<Item> MOOSE_SPAWN_EGG = registerItem("moose_spawn_egg",
//            () -> new SpawnEggItem(NMLEntities.MOOSE.get(), 0x8b4513, 0xa52a2a, new Item.Properties()));

    public static final DeferredItem<Item> WALNUT_SIGN = registerItem("walnut_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), NMLBlocks.WALNUT_SIGN.get(), NMLBlocks.WALNUT_WALL_SIGN.get()));
    public static final DeferredItem<Item> WALNUT_HANGING_SIGN = registerItem("walnut_hanging_sign",
            () -> new HangingSignItem(NMLBlocks.WALNUT_HANGING_SIGN.get(), NMLBlocks.WALNUT_HANGING_WALL_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> WALNUT_BOAT = registerItem("walnut_boat",
            () -> new BoatItem(false, BoatEntity.Type.WALNUT, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> WALNUT_CHEST_BOAT = registerItem("walnut_chest_boat",
            () -> new BoatItem(true, BoatEntity.Type.WALNUT, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> WILLOW_SIGN = registerItem("willow_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), NMLBlocks.WILLOW_SIGN.get(), NMLBlocks.WILLOW_WALL_SIGN.get()));
    public static final DeferredItem<Item> WILLOW_HANGING_SIGN = registerItem("willow_hanging_sign",
            () -> new HangingSignItem(NMLBlocks.WILLOW_HANGING_SIGN.get(), NMLBlocks.WILLOW_HANGING_WALL_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> WILLOW_BOAT = registerItem("willow_boat",
            () -> new BoatItem(false, BoatEntity.Type.WILLOW, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> WILLOW_CHEST_BOAT = registerItem("willow_chest_boat",
            () -> new BoatItem(true, BoatEntity.Type.WILLOW, new Item.Properties().stacksTo(1)));

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(NMLBlocks.FADED_STONE_BRICKS.get());
            event.accept(NMLBlocks.POLISHED_STONE.get());
            event.accept(NMLBlocks.POLISHED_STONE_STAIRS.get());
            event.accept(NMLBlocks.POLISHED_STONE_SLAB.get());

            event.accept(NMLBlocks.COBBLESTONE_BRICKS.get());
            event.accept(NMLBlocks.COBBLESTONE_BRICK_STAIRS.get());
            event.accept(NMLBlocks.COBBLESTONE_BRICK_SLAB.get());
            event.accept(NMLBlocks.COBBLESTONE_BRICK_WALL.get());
            event.accept(NMLBlocks.MOSSY_COBBLESTONE_BRICKS.get());
            event.accept(NMLBlocks.MOSSY_COBBLESTONE_BRICK_STAIRS.get());
            event.accept(NMLBlocks.MOSSY_COBBLESTONE_BRICK_SLAB.get());
            event.accept(NMLBlocks.MOSSY_COBBLESTONE_BRICK_WALL.get());

            event.accept(NMLBlocks.MUNDANE_TILES.get());
            event.accept(NMLBlocks.EARTHEN_TILES.get());

            event.accept(NMLBlocks.PINE_LOG.get());
            event.accept(NMLBlocks.PINE_WOOD.get());
            event.accept(NMLBlocks.STRIPPED_PINE_LOG.get());
            event.accept(NMLBlocks.STRIPPED_PINE_WOOD.get());
            event.accept(NMLBlocks.PINE_PLANKS.get());
            event.accept(NMLBlocks.PINE_STAIRS.get());
            event.accept(NMLBlocks.PINE_SLAB.get());
            event.accept(NMLBlocks.TRIMMED_PINE_PLANKS.get());
            event.accept(NMLBlocks.PINE_FENCE.get());
            event.accept(NMLBlocks.PINE_FENCE_GATE.get());
            event.accept(NMLBlocks.PINE_DOOR.get());
            event.accept(NMLBlocks.PINE_TRAPDOOR.get());
            event.accept(NMLBlocks.PINE_PRESSURE_PLATE.get());
            event.accept(NMLBlocks.PINE_BUTTON.get());
//            if (ModList.get().isLoaded("farmersdelight")) {
//                event.accept(NMLBlocks.PINE_CABINET.get());
//            }

            event.accept(NMLBlocks.MAPLE_LOG.get());
            event.accept(NMLBlocks.MAPLE_WOOD.get());
            event.accept(NMLBlocks.STRIPPED_MAPLE_LOG.get());
            event.accept(NMLBlocks.STRIPPED_MAPLE_WOOD.get());
            event.accept(NMLBlocks.MAPLE_PLANKS.get());
            event.accept(NMLBlocks.MAPLE_STAIRS.get());
            event.accept(NMLBlocks.MAPLE_SLAB.get());
            event.accept(NMLBlocks.TRIMMED_MAPLE_PLANKS.get());
            event.accept(NMLBlocks.MAPLE_FENCE.get());
            event.accept(NMLBlocks.MAPLE_FENCE_GATE.get());
            event.accept(NMLBlocks.MAPLE_DOOR.get());
            event.accept(NMLBlocks.MAPLE_TRAPDOOR.get());
            event.accept(NMLBlocks.MAPLE_PRESSURE_PLATE.get());
            event.accept(NMLBlocks.MAPLE_BUTTON.get());
//            if (ModList.get().isLoaded("farmersdelight")) {
//                event.accept(NMLBlocks.MAPLE_CABINET.get());
//            }

            event.accept(NMLBlocks.WALNUT_LOG.get());
            event.accept(NMLBlocks.WALNUT_WOOD.get());
            event.accept(NMLBlocks.STRIPPED_WALNUT_LOG.get());
            event.accept(NMLBlocks.STRIPPED_WALNUT_WOOD.get());
            event.accept(NMLBlocks.WALNUT_PLANKS.get());
            event.accept(NMLBlocks.WALNUT_STAIRS.get());
            event.accept(NMLBlocks.WALNUT_SLAB.get());
            event.accept(NMLBlocks.TRIMMED_WALNUT_PLANKS.get());
            event.accept(NMLBlocks.WALNUT_FENCE.get());
            event.accept(NMLBlocks.WALNUT_FENCE_GATE.get());
            event.accept(NMLBlocks.WALNUT_DOOR.get());
            event.accept(NMLBlocks.WALNUT_TRAPDOOR.get());
            event.accept(NMLBlocks.WALNUT_PRESSURE_PLATE.get());
            event.accept(NMLBlocks.WALNUT_BUTTON.get());
//            if (ModList.get().isLoaded("farmersdelight")) {
//                event.accept(NMLBlocks.WALNUT_CABINET.get());
//            }
            event.accept(NMLBlocks.WILLOW_LOG.get());
            event.accept(NMLBlocks.WILLOW_WOOD.get());
            event.accept(NMLBlocks.STRIPPED_WILLOW_LOG.get());
            event.accept(NMLBlocks.STRIPPED_WILLOW_WOOD.get());
            event.accept(NMLBlocks.WILLOW_PLANKS.get());
            event.accept(NMLBlocks.WILLOW_STAIRS.get());
            event.accept(NMLBlocks.WILLOW_SLAB.get());
            event.accept(NMLBlocks.TRIMMED_WILLOW_PLANKS.get());
            event.accept(NMLBlocks.WILLOW_FENCE.get());
            event.accept(NMLBlocks.WILLOW_FENCE_GATE.get());
            event.accept(NMLBlocks.WILLOW_DOOR.get());
            event.accept(NMLBlocks.WILLOW_TRAPDOOR.get());
            event.accept(NMLBlocks.WILLOW_PRESSURE_PLATE.get());
            event.accept(NMLBlocks.WILLOW_BUTTON.get());
//            if (ModList.get().isLoaded("farmersdelight")) {
//                event.accept(NMLBlocks.WILLOW_CABINET.get());
//            }

            event.accept(NMLBlocks.COD_BARREL.get());
            event.accept(NMLBlocks.SALMON_BARREL.get());
            event.accept(NMLBlocks.PUFFERFISH_BARREL.get());
            event.accept(NMLBlocks.TROPICAL_FISH_BARREL.get());
            event.accept(NMLBlocks.APPLE_CRATE.get());
            event.accept(NMLBlocks.PEAR_CRATE.get());

            event.accept(NMLBlocks.TRIMMED_OAK_PLANKS.get());
            event.accept(NMLBlocks.TRIMMED_SPRUCE_PLANKS.get());
            event.accept(NMLBlocks.TRIMMED_BIRCH_PLANKS.get());
            event.accept(NMLBlocks.TRIMMED_JUNGLE_PLANKS.get());
            event.accept(NMLBlocks.TRIMMED_ACACIA_PLANKS.get());
            event.accept(NMLBlocks.TRIMMED_DARK_OAK_PLANKS.get());
            event.accept(NMLBlocks.TRIMMED_CHERRY_PLANKS.get());
            event.accept(NMLBlocks.TRIMMED_MANGROVE_PLANKS.get());
            event.accept(NMLBlocks.TRIMMED_CRIMSON_PLANKS.get());
            event.accept(NMLBlocks.TRIMMED_WARPED_PLANKS.get());
            event.accept(NMLBlocks.TRIMMED_BAMBOO_PLANKS.get());
        }
        if (event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(NMLBlocks.GRASS_SPROUTS.get());
            event.accept(NMLBlocks.OAT_GRASS.get());
            event.accept(NMLBlocks.SHORT_BEACHGRASS.get());
            event.accept(NMLBlocks.TALL_BEACHGRASS.get());
            event.accept(NMLBlocks.FROSTED_GRASS.get());
            event.accept(NMLBlocks.DRIED_GRASS.get());
            event.accept(NMLBlocks.FIDDLEHEAD.get());
            event.accept(NMLBlocks.CATTAIL.get());
            event.accept(NMLBlocks.DUCKWEED.get());
            event.accept(NMLBlocks.CLOVER_PATCH.get());
            event.accept(NMLBlocks.RED_FLOWERBED.get());
            event.accept(NMLBlocks.YELLOW_FLOWERBED.get());
            event.accept(NMLBlocks.BLUE_FLOWERBED.get());
            event.accept(NMLBlocks.VIOLET_FLOWERBED.get());
            event.accept(NMLBlocks.WHITE_FLOWERBED.get());
            event.accept(NMLBlocks.RED_LUPINE.get());
            event.accept(NMLBlocks.BLUE_LUPINE.get());
            event.accept(NMLBlocks.PINK_LUPINE.get());
            event.accept(NMLBlocks.YELLOW_LUPINE.get());
            event.accept(NMLBlocks.ACONITE.get());
            event.accept(NMLBlocks.WILD_MINT.get());
            event.accept(NMLBlocks.AUTUMN_CROCUS.get());
            event.accept(NMLBlocks.RAFFLESIA.get());
            event.accept(NMLBlocks.BARREL_CACTUS.get());
            event.accept(NMLBlocks.SUCCULENT.get());
            event.accept(NMLBlocks.PICKLEWEED.get());
            event.accept(NMLBlocks.PEBBLES.get());
            event.accept(NMLBlocks.SEASHELLS.get());
            event.accept(NMLBlocks.YELLOW_BIRCH_LEAVES.get());
            event.accept(NMLBlocks.YELLOW_BIRCH_SAPLING);
            event.accept(NMLBlocks.AUTUMNAL_OAK_LEAVES.get());
            event.accept(NMLBlocks.AUTUMNAL_OAK_SAPLING);
            event.accept(NMLBlocks.PALE_CHERRY_LEAVES.get());
            event.accept(NMLBlocks.PALE_CHERRY_SAPLING);
            event.accept(NMLBlocks.FIELD_MUSHROOM.get());
            event.accept(NMLBlocks.FIELD_MUSHROOM_BLOCK.get());
            event.accept(NMLBlocks.SHELF_MUSHROOM.get());
            event.accept(NMLBlocks.SHELF_MUSHROOM_BLOCK.get());
            event.accept(NMLBlocks.FROSTED_LEAVES.get());
            event.accept(NMLBlocks.BEARD_MOSS.get());

//            if (ModList.get().isLoaded("farmersdelight")) {
//                event.accept(NMLBlocks.FIELD_MUSHROOM_COLONY.get());
//            }
            event.accept(NMLBlocks.DIRT_PATH.get());
            event.accept(NMLBlocks.MYCELIUM_PATH.get());
            event.accept(NMLBlocks.PODZOL_PATH.get());
            event.accept(NMLBlocks.SNOWY_GRASS_PATH.get());
            event.accept(NMLBlocks.SNOW_PATH.get());
            event.accept(NMLBlocks.GRAVEL_PATH.get());
            event.accept(NMLBlocks.SAND_PATH.get());
            event.accept(NMLBlocks.RED_SAND_PATH.get());

            event.accept(NMLBlocks.QUARTZITE.get());
            event.accept(NMLBlocks.QUARTZITE_CLUSTER.get());
            event.accept(NMLBlocks.SMALL_QUARTZITE_BUD.get());
            event.accept(NMLBlocks.MEDIUM_QUARTZITE_BUD.get());
            event.accept(NMLBlocks.LARGE_QUARTZITE_BUD.get());
            event.accept(NMLBlocks.BUDDING_QUARTZITE.get());
//            event.accept(NMLBlocks.PETRIFIED_LOG.get());
//            event.accept(NMLBlocks.PETRIFIED_WOOD.get());

//            event.accept(NMLBlocks.REMAINS.get());

            event.accept(NMLBlocks.PINE_LOG.get());
            event.accept(NMLBlocks.PINE_LEAVES.get());
            event.accept(NMLBlocks.PINE_SAPLING);

            event.accept(NMLBlocks.MAPLE_LOG.get());
            event.accept(NMLBlocks.MAPLE_LEAVES.get());
            event.accept(NMLBlocks.MAPLE_SAPLING);
            event.accept(NMLBlocks.RED_MAPLE_LEAVES.get());
            event.accept(NMLBlocks.RED_MAPLE_SAPLING);

            event.accept(NMLBlocks.WALNUT_LOG.get());
            event.accept(NMLBlocks.WALNUT_LEAVES.get());
            event.accept(NMLBlocks.WALNUT_SAPLING);

            event.accept(NMLBlocks.WILLOW_LOG.get());
            event.accept(NMLBlocks.WILLOW_LEAVES.get());
            event.accept(NMLBlocks.WILLOW_SAPLING);

        }
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(NMLBlocks.PINE_BOOKSHELF.get());
            event.accept(NMLBlocks.MAPLE_BOOKSHELF.get());
            event.accept(NMLBlocks.WALNUT_BOOKSHELF.get());
            event.accept(NMLBlocks.SPRUCE_BOOKSHELF.get());
            event.accept(NMLBlocks.BIRCH_BOOKSHELF.get());
            event.accept(NMLBlocks.JUNGLE_BOOKSHELF.get());
            event.accept(NMLBlocks.ACACIA_BOOKSHELF.get());
            event.accept(NMLBlocks.DARK_OAK_BOOKSHELF.get());
            event.accept(NMLBlocks.CHERRY_BOOKSHELF.get());
            event.accept(NMLBlocks.MANGROVE_BOOKSHELF.get());
            event.accept(NMLBlocks.CRIMSON_BOOKSHELF.get());
            event.accept(NMLBlocks.WARPED_BOOKSHELF.get());
            event.accept(NMLBlocks.BAMBOO_BOOKSHELF.get());
            event.accept(NMLItems.PINE_SIGN.get());
            event.accept(NMLItems.PINE_HANGING_SIGN.get());
            event.accept(NMLItems.MAPLE_SIGN.get());
            event.accept(NMLItems.MAPLE_HANGING_SIGN.get());
            event.accept(NMLItems.WALNUT_SIGN.get());
            event.accept(NMLItems.WALNUT_HANGING_SIGN.get());
            event.accept(NMLItems.WILLOW_SIGN.get());
            event.accept(NMLItems.WILLOW_HANGING_SIGN.get());
            event.accept(NMLItems.SCONCE_TORCH.get());
            event.accept(NMLItems.SCONCE_SOUL_TORCH.get());
            event.accept(NMLBlocks.TAP.get());
            event.accept(NMLItems.WOODEN_SCAFFOLDING.get());
        }
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(NMLItems.MASHED_POTATOES_WITH_MUSHROOMS.get());
            event.accept(NMLItems.GRILLED_MUSHROOMS.get());
            event.accept(NMLItems.FROG_LEG.get());
            event.accept(NMLItems.COOKED_FROG_LEG.get());
            event.accept(NMLItems.RAW_HORSE.get());
            event.accept(NMLItems.HORSE_STEAK.get());
            event.accept(NMLItems.RAW_VENISON.get());
            event.accept(NMLItems.COOKED_VENISON.get());
            event.accept(NMLItems.MAPLE_SYRUP_BOTTLE);
            event.accept(NMLItems.PEAR.get());
            event.accept(NMLItems.SYRUPED_PEAR.get());
            event.accept(NMLItems.PEAR_COBBLER.get());
            event.accept(NMLItems.HONEYED_APPLE.get());
            event.accept(NMLItems.WALNUTS.get());
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(NMLItems.PINE_BOAT.get());
            event.accept(NMLItems.PINE_CHEST_BOAT.get());
            event.accept(NMLItems.MAPLE_BOAT.get());
            event.accept(NMLItems.MAPLE_CHEST_BOAT.get());
            event.accept(NMLItems.WALNUT_BOAT.get());
            event.accept(NMLItems.WALNUT_CHEST_BOAT.get());
            event.accept(NMLItems.WILLOW_BOAT.get());
            event.accept(NMLItems.WILLOW_CHEST_BOAT.get());
            // TODO: this crashes the game if you have the bundle experiment on
//            event.accept(Items.BUNDLE);
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(NMLItems.FIREBOMB.get());
            event.accept(NMLItems.EXPLOSIVE.get());
            event.accept(NMLItems.RESIN_OIL_BOTTLE.get());
        }

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(NMLItems.RESIN.get());
            event.accept(NMLItems.RESIN_OIL_BOTTLE.get());
        }

        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(NMLBlocks.SPIKE_TRAP.get());
        }

        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(NMLBlocks.MONSTER_ANCHOR.get());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Item> DeferredItem<T> registerItem(final String name, final Supplier<? extends Item> item) {
        DeferredItem<Item> toReturn = ITEMS.register(name, item);
        CREATIVE_TAB_ITEMS.add(toReturn);
        return (DeferredItem<T>) toReturn;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Item> DeferredItem<T> registerIntegrationItem(final String name, final Supplier<? extends Item> item, String modId) {
        if (!ModList.get().isLoaded(modId)) return null;
        DeferredItem<Item> toReturn = ITEMS.register(name, item);
        CREATIVE_TAB_ITEMS.add(toReturn);
        return (DeferredItem<T>) toReturn;
    }
}