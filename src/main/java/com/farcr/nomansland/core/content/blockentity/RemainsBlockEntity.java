package com.farcr.nomansland.core.content.blockentity;

import com.farcr.nomansland.core.config.NMLConfig;
import com.farcr.nomansland.core.content.mixin.BlockEntityAccessor;
import com.farcr.nomansland.core.registry.NMLBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class RemainsBlockEntity extends BrushableBlockEntity {
    public RemainsBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(pPos, pBlockState);
        ((BlockEntityAccessor) this).setType(NMLBlockEntities.REMAINS.get());
    }

    @Override
    public boolean isValidBlockState(BlockState blockState) {
        return NMLBlockEntities.REMAINS.get().isValid(blockState);
    }

    @Override
    public void brushingCompleted(Player pPlayer) {
        if (level != null && level.getServer() != null) {
            Block remainsBlockState = this.getBlockState().getBlock();
            if (remainsBlockState instanceof BrushableBlock) {
                float a = new Random().nextFloat();
                if (a <= NMLConfig.BURIED_SPAWNING_CHANCE.get()) {
                    //TODO: When Buried is readded/worked on, replace SKELETON with BURIED
                    Skeleton buried = EntityType.SKELETON.create(level);
                    if (buried != null) {
                        Vec3 playerPosition = pPlayer.getPosition(1);
                        BlockPos spawningPosition = worldPosition.relative(this.getHitDirection());
                        if (level.getBlockState(spawningPosition) == Blocks.AIR.defaultBlockState())
                            buried.moveTo(spawningPosition.getCenter());
                        else
                            buried.moveTo((playerPosition.x + worldPosition.getX()) / 2, (playerPosition.y + worldPosition.getY()) / 2, (playerPosition.z + worldPosition.getZ()) / 2);
                        level.addFreshEntity(buried);
                        buried.spawnAnim();
                    }
                }
            }
        }
        super.brushingCompleted(pPlayer);
    }
}
