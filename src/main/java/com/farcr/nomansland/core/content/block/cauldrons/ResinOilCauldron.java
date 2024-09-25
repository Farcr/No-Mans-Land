package com.farcr.nomansland.core.content.block.cauldrons;

import com.farcr.nomansland.core.registry.NMLParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ResinOilCauldron extends NMLCauldronBlock {
    public ResinOilCauldron() {
        super(NMLCauldronType.RESIN_OIL);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        BlockState stateUnder = level.getBlockState(pos.below());
        if (stateUnder.is(BlockTags.FIRE) || stateUnder.is(BlockTags.CAMPFIRES)) {
            double d0 = pos.getX() + 0.5 + random.nextInt(-40, 40)*0.01;
            double d1 = pos.getY() + 0.5 + state.getValue(LEVEL)*0.15;
            double d2 = pos.getZ() + 0.5 + random.nextInt(-40, 40)*0.01;
            level.addParticle(NMLParticleTypes.RESIN_OIL_BUBBLE.get(), d0, d1, d2, 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && entity.isOnFire() && this.isEntityInsideContent(state, pos, entity)) {
            entity.setRemainingFireTicks(entity.getRemainingFireTicks()+50*state.getValue(LEVEL));
            level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
            level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 3, Level.ExplosionInteraction.BLOCK);
        }
    }
}
