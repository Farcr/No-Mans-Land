package com.farcr.nomansland.common.block.signs;

import com.farcr.nomansland.common.blockentity.NMLHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class CeilingHangingSignBlock extends net.minecraft.world.level.block.CeilingHangingSignBlock {
    public CeilingHangingSignBlock(WoodType pType, Properties pProperties) {
        super(pType, pProperties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new NMLHangingSignBlockEntity(pPos, pState);
    }
}
