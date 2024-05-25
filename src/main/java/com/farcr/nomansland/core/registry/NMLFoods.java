package com.farcr.nomansland.core.registry;

import net.minecraft.world.food.FoodProperties;

public class NMLFoods {
    public static final FoodProperties MASHED_POTATOES_WITH_MUSHROOMS = new FoodProperties.Builder().nutrition(10).saturationMod(0.9f).build();
    public static final FoodProperties GRILLED_MUSHROOMS = new FoodProperties.Builder().nutrition(4).saturationMod(0.7f).build();

    public static final FoodProperties FROG_LEG = new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build();
    public static final FoodProperties COOKED_FROG_LEG = new FoodProperties.Builder().nutrition(6).saturationMod(0.7f).build();

    public static final FoodProperties RAW_HORSE = new FoodProperties.Builder().nutrition(4).saturationMod(0.3f).build();
    public static final FoodProperties HORSE_STEAK = new FoodProperties.Builder().nutrition(9).saturationMod(0.8f).build();

    public static final FoodProperties MAPLE_SYRUP_BOTTLE = new FoodProperties.Builder().nutrition(5).saturationMod(0.2f).build();
}
