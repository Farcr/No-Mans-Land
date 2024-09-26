package com.farcr.nomansland.common.registry;

import com.farcr.nomansland.NoMansLand;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import static net.minecraft.world.level.block.state.properties.BlockSetType.register;

public class NMLBlockSetTypes {
    public static final BlockSetType PINE = register(new BlockSetType(NoMansLand.MODID + ":pine"));
    public static final BlockSetType MAPLE = register(new BlockSetType(NoMansLand.MODID + ":maple"));
    public static final BlockSetType WALNUT = register(new BlockSetType(NoMansLand.MODID + ":walnut"));
    public static final BlockSetType WILLOW = register(new BlockSetType(NoMansLand.MODID + ":willow"));
}
