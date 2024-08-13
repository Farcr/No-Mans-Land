package com.farcr.nomansland.core.content.block.signs;

import com.farcr.nomansland.core.content.blockentity.NMLSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class StandingSignBlock extends net.minecraft.world.level.block.StandingSignBlock {
    public StandingSignBlock(WoodType pType, Properties pProperties) {
        super(pType, pProperties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new NMLSignBlockEntity(pPos, pState);
    }
}
