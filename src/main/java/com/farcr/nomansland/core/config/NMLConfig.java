package com.farcr.nomansland.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class NMLConfig {

    public static ModConfigSpec COMMON_CONFIG;

    public static final String CATEGORY_QOL = "quality_of_life";
    public static ModConfigSpec.BooleanValue MYCELIUM_SPREADS;
    public static ModConfigSpec.BooleanValue GRASS_SPREADS;

    static {

        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        COMMON_BUILDER.push(CATEGORY_QOL);
        GRASS_SPREADS = COMMON_BUILDER.comment("Should grass or mycelium spread to nearby dirt?").define("grassSpreads", true);
        MYCELIUM_SPREADS = COMMON_BUILDER.define("myceliumSpreads", true);

        COMMON_CONFIG =  COMMON_BUILDER.build();
    }

}
