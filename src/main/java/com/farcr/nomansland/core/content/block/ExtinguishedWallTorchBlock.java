package com.farcr.nomansland.core.content.block;

import com.farcr.nomansland.core.registry.NMLTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ExtinguishedWallTorchBlock extends WallTorchBlock {

    public final Block mainBlock;
    public ExtinguishedWallTorchBlock(Properties pProperties, Block mainBlock, ParticleOptions pFlameParticle) {
        super(pProperties, pFlameParticle);
        this.mainBlock = mainBlock;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {}

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.getItemInHand(pHand).is(NMLTags.FIRESTARTERS))
        {
            pLevel.playSound(pPlayer,
                    pPlayer.getX(),
                    pPlayer.getY(),
                    pPlayer.getZ(),
                    SoundEvents.FLINTANDSTEEL_USE,
                    SoundSource.BLOCKS,
                    1.0F,
                    (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 0.8F + 1.8F);
            pLevel.setBlock(pPos, mainBlock.withPropertiesOf(pState), 3);
            //TODO:pFlameParticle spark

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    //TODO:Produce Smoke on break
}
