package com.farcr.nomansland.common.blockentity;

import com.farcr.nomansland.common.registry.NMLBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NMLSignBlockEntity extends SignBlockEntity {
    public NMLSignBlockEntity(BlockPos pPos, BlockState pBlockstate) {
        super(NMLBlockEntities.NML_SIGN.get(), pPos, pBlockstate);
    }

    @Override
    public BlockEntityType<?> getType() {
        return NMLBlockEntities.NML_SIGN.get();
    }
}
