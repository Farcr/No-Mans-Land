package com.farcr.nomansland.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class NMLConfig {

    public static ModConfigSpec COMMON_CONFIG;
    public static ModConfigSpec.BooleanValue MYCELIUM_SPREADS;
    public static ModConfigSpec.BooleanValue GRASS_SPREADS;

    static {

        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        COMMON_BUILDER.comment("Should mycelium or grass spread to nearby dirt?");
        GRASS_SPREADS = COMMON_BUILDER.define("grassSpreads", true);
        MYCELIUM_SPREADS = COMMON_BUILDER.define("grassSpreads", true);

        COMMON_CONFIG =  COMMON_BUILDER.build();
    }

}
