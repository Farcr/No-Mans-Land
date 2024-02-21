package com.farcr.nomansland.core.registry;

import net.minecraft.world.food.FoodProperties;

public class NMLFoods {
    public static final FoodProperties MASHED_POTATOES_WITH_MUSHROOMS = new FoodProperties.Builder().nutrition(12).saturationMod(1.5f).build();
    public static final FoodProperties GRILLED_MUSHROOMS = new FoodProperties.Builder().nutrition(4).saturationMod(1.1f).build();

    public static final FoodProperties FROG_LEG = new FoodProperties.Builder().nutrition(2).saturationMod(0.6f).build();
    public static final FoodProperties COOKED_FROG_LEG = new FoodProperties.Builder().nutrition(7).saturationMod(1.3f).build();
}
