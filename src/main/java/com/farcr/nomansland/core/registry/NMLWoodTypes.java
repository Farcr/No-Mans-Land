package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.world.level.block.state.properties.WoodType;

import static net.minecraft.world.level.block.state.properties.WoodType.register;

public class NMLWoodTypes {
    public static final WoodType PINE = register(new WoodType(NoMansLand.MODID + ":pine", NMLBlockSetTypes.PINE));
}
