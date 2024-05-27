package com.farcr.nomansland.core.config;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NoMansLand.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NMLConfig {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static final ForgeConfigSpec.BooleanValue MYCELIUM_SPREADS;
    public static final ForgeConfigSpec.BooleanValue GRASS_SPREADS;

    public static ForgeConfigSpec CLIENT_CONFIG;
    public static ForgeConfigSpec SERVER_CONFIG;

    static {

        ForgeConfigSpec.Builder commonConfig = new ForgeConfigSpec.Builder();

        GRASS_SPREADS = commonConfig.comment("Should grass spread to adjacent dirt?")
                .define("grassSpreads", true);
        MYCELIUM_SPREADS = commonConfig.comment("Should mycelium spread to adjacent dirt?")
                .define("myceliumSpreads", true);

        COMMON_CONFIG = commonConfig.build();

        ForgeConfigSpec.Builder serverConfig = new ForgeConfigSpec.Builder();
        SERVER_CONFIG = serverConfig.build();

        ForgeConfigSpec.Builder clientBuilder = new ForgeConfigSpec.Builder();
        CLIENT_CONFIG = clientBuilder.build();
    }

}
