package com.farcr.nomansland.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class NMLConfig {

    public static ModConfigSpec COMMON_CONFIG;

    public static final String CATEGORY_OVERRIDES = "overrides";
    public static ModConfigSpec.BooleanValue MYCELIUM_SPREADS;
    public static ModConfigSpec.BooleanValue GRASS_SPREADS;
    public static ModConfigSpec.BooleanValue MALEVOLENT_SPAWNER;

    public static final String CATEGORY_TAP = "tap";
    public static ModConfigSpec.DoubleValue FILLING_SPEED_MULTIPLIER;
    public static ModConfigSpec.IntValue TIME_TO_FILL_CAULDRON;

    public static final String CATEGORY_ANCHOR = "monster_anchor";
    public static ModConfigSpec.IntValue TIME_BETWEEN_RESURRECTIONS;

    public static final String CATEGORY_MISC = "miscellaneous";

    public static ModConfigSpec CLIENT_CONFIG;
    public static ModConfigSpec.IntValue WOODEN_SCAFFOLDING_DISTANCE;

    public static final String CATEGORY_FOG_MODIFIERS = "fog_modifiers";
    public static ModConfigSpec.BooleanValue FOG_MODIFIERS;

    static {

        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        COMMON_BUILDER.push(CATEGORY_OVERRIDES);
        GRASS_SPREADS = COMMON_BUILDER.comment("If grass or mycelium spread to nearby dirt.").define("grassSpreads", true);
        MYCELIUM_SPREADS = COMMON_BUILDER.define("myceliumSpreads", true);
        MALEVOLENT_SPAWNER = COMMON_BUILDER.comment("If monster spawners should produce malevolent fire instead of regular fire (like monster anchors).")
                .define("malevolentSpawner", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push(CATEGORY_TAP);
        FILLING_SPEED_MULTIPLIER = COMMON_BUILDER
                .comment("Multiplier on how fast taps should fill up cauldrons with resin. Set it to 0.0 to disable this.")
                .defineInRange("fillingSpeedMultiplier", 1.0, 0.0, 10);
        TIME_TO_FILL_CAULDRON = COMMON_BUILDER
                .comment("The time taken (in ticks) it takes to fill a cauldron with honey using a tap. 20 ticks make one second.")
                .defineInRange("timeToFillCauldron", 80, 1, 400);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push(CATEGORY_ANCHOR);
        TIME_BETWEEN_RESURRECTIONS = COMMON_BUILDER
                .comment("The time between each resurrection from a monster anchor.")
                .defineInRange("timeBetweenResurrections", 80, 10, 400);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push(CATEGORY_MISC);
        WOODEN_SCAFFOLDING_DISTANCE = COMMON_BUILDER
                .comment("The max distance a wooden scaffolding can be from another scaffolding.")
                .defineInRange("woodenScaffoldingDistance", 6, 2, 20);


        COMMON_CONFIG =  COMMON_BUILDER.build();

        ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();

        CLIENT_BUILDER.push(CATEGORY_FOG_MODIFIERS);
        FOG_MODIFIERS = CLIENT_BUILDER
                .comment("If nml's custom fog modifiers are enabled")
                .define("fogModifiers", true);
        CLIENT_BUILDER.pop();

        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

}
