package com.farcr.nomansland.core.content.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class FrostedGrassBlock extends GrassBlock {
    public FrostedGrassBlock(Properties p_53685_) {
        super(p_53685_);
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(SNOWY);
    }
}
