package com.farcr.nomansland.common.registry;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class NMLFoods {
    public static final FoodProperties MASHED_POTATOES_WITH_MUSHROOMS = new FoodProperties.Builder().nutrition(10).saturationModifier(0.9f).usingConvertsTo(Items.BOWL).build();
    public static final FoodProperties GRILLED_MUSHROOMS = new FoodProperties.Builder().nutrition(4).saturationModifier(0.5f).build();

    public static final FoodProperties FROG_LEG = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).build();
    public static final FoodProperties COOKED_FROG_LEG = new FoodProperties.Builder().nutrition(6).saturationModifier(0.7f).build();

    public static final FoodProperties RAW_HORSE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build();
    public static final FoodProperties HORSE_STEAK = new FoodProperties.Builder().nutrition(9).saturationModifier(0.8f).build();

    public static final FoodProperties RAW_VENISON = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3f).build();
    public static final FoodProperties COOKED_VENISON = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).build();

    public static final FoodProperties MAPLE_SYRUP_BOTTLE = new FoodProperties.Builder().nutrition(5).saturationModifier(0.2f).alwaysEdible().usingConvertsTo(Items.GLASS_BOTTLE).build();

    public static final FoodProperties PEAR = new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build();
    public static final FoodProperties SYRUPED_PEAR = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).build();
    public static final FoodProperties PEAR_COBBLER = new FoodProperties.Builder().nutrition(7).saturationModifier(0.5f).build();
    public static final FoodProperties PANCAKE = new FoodProperties.Builder().nutrition(7).saturationModifier(0.5f).build();

    public static final FoodProperties HONEYED_APPLE = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).build();

    public static final FoodProperties WALNUTS = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3f).fast().build();
}
