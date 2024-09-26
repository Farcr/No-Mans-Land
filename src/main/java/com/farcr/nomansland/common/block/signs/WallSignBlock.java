package com.farcr.nomansland.common.block.signs;

import com.farcr.nomansland.common.blockentity.NMLSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class WallSignBlock extends net.minecraft.world.level.block.WallSignBlock {
    public WallSignBlock(WoodType pType, Properties pProperties) {
        super(pType, pProperties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new NMLSignBlockEntity(pPos, pState);
    }
}
