package com.farcr.nomansland.core.content.blockentity;

import com.farcr.nomansland.core.registry.NMLBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NMLHangingSignBlockEntity extends SignBlockEntity {
    public NMLHangingSignBlockEntity (BlockPos pPos, BlockState pBlockstate) {
        super(NMLBlockEntities.NML_HANGING_SIGN.get(), pPos, pBlockstate);
    }
        @Override
        public BlockEntityType<?> getType() {
            return NMLBlockEntities.NML_HANGING_SIGN.get();
        }
}
