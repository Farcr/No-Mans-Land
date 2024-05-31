package com.farcr.nomansland.core.content.block;

import com.farcr.nomansland.core.content.blockentity.NMLHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class WallHangingSignBlock extends net.minecraft.world.level.block.WallHangingSignBlock {
    public WallHangingSignBlock(WoodType pType, Properties pProperties) {
        super(pType, pProperties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState){
        return new NMLHangingSignBlockEntity(pPos, pState);
    }
}
