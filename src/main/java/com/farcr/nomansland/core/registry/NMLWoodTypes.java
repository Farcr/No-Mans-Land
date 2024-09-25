package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.world.level.block.state.properties.WoodType;

import static net.minecraft.world.level.block.state.properties.WoodType.register;

public class NMLWoodTypes {
    public static final WoodType PINE = register(new WoodType(NoMansLand.MODID + ":pine", NMLBlockSetTypes.PINE));
    public static final WoodType MAPLE = register(new WoodType(NoMansLand.MODID + ":maple", NMLBlockSetTypes.MAPLE));
    public static final WoodType WALNUT = register(new WoodType(NoMansLand.MODID + ":walnut", NMLBlockSetTypes.WALNUT));
    public static final WoodType WILLOW = register(new WoodType(NoMansLand.MODID + ":willow", NMLBlockSetTypes.WILLOW));
}
