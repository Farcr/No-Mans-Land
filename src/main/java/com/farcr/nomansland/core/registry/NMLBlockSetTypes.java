package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import static net.minecraft.world.level.block.state.properties.BlockSetType.register;

public class NMLBlockSetTypes {
    public static final BlockSetType PINE = register(new BlockSetType(NoMansLand.MODID + ":pine"));
}
