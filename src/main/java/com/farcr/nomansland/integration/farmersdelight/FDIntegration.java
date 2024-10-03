package com.farcr.nomansland.integration.farmersdelight;

import com.farcr.nomansland.common.registry.NMLBlocks;
import com.farcr.nomansland.common.registry.NMLItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import vectorwing.farmersdelight.common.block.MushroomColonyBlock;

import java.util.function.Supplier;

public class FDIntegration {
    public static Supplier<? extends Block> fieldMushroomColony() {
        return () -> new MushroomColonyBlock(NMLItems.FIELD_MUSHROOM, Block.Properties.ofFullCopy(NMLBlocks.FIELD_MUSHROOM.get()));
    }

    public static Supplier<? extends Block> cabinetBlock() {
        return () -> new NMLCabinetBlock(Block.Properties.ofFullCopy(Blocks.BARREL));
    }

    public static Supplier<? extends BlockEntityType<?>> cabinetBlockEntity() {
        return () -> BlockEntityType.Builder.of(NMLCabinetBlockEntity::new,
                NMLBlocks.PINE_CABINET.get(),
                NMLBlocks.MAPLE_CABINET.get(),
                NMLBlocks.WALNUT_CABINET.get(),
                NMLBlocks.WILLOW_CABINET.get()
        ).build(null);
    }
}

