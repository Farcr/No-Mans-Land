package com.farcr.nomansland.core.content.blockentity;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class RemainsBlockEntity extends BrushableBlockEntity {
    public RemainsBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(pPos, pBlockState);
    }

    @Override
    public void brushingCompleted(Player pPlayer) {
        if (this.level != null && this.level.getServer() != null) {
            Block remainsBlockState = this.getBlockState().getBlock();
            if (remainsBlockState instanceof BrushableBlock) {
                float a = new Random().nextFloat();
                // TODO: possibly tweak this
                if (a <= 0.05F) {
                    Pig pig = EntityType.PIG.create(level);
                    if (pig != null) {
                        pig.moveTo((double) worldPosition.getX() + 0.5D, (double) worldPosition.getY() + 1, (double) worldPosition.getZ() + 0.5D, 0.0F, 0.0F);
                        level.addFreshEntity(pig);
                        pig.spawnAnim();
                    }
                }
            }
        }
        super.brushingCompleted(pPlayer);
    }
}
