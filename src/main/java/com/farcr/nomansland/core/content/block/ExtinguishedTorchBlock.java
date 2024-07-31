package com.farcr.nomansland.core.content.block;

import com.farcr.nomansland.core.registry.NMLTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ExtinguishedTorchBlock extends TorchBlock {

    public final Block mainBlock;

    public ExtinguishedTorchBlock(SimpleParticleType pFlameParticle, Properties pProperties, Block mainBlock) {
        super(pFlameParticle, pProperties);
        this.mainBlock = mainBlock;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
    }

    // TODO: ensure this works


    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (player.getItemInHand(hand).is(NMLTags.FIRESTARTERS)) {
            level.playSound(player,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.FLINTANDSTEEL_USE,
                    SoundSource.BLOCKS,
                    1.0F,
                    (level.random.nextFloat() - level.random.nextFloat()) * 0.8F + 1.8F);
            level.setBlock(pos, mainBlock.defaultBlockState(), 3);
            //TODO:pFlameParticle spark

            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.FAIL;
    }

    //TODO:Produce Smoke on break
}
