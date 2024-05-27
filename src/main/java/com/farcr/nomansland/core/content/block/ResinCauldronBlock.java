package com.farcr.nomansland.core.content.block;

import com.farcr.nomansland.core.registry.NMLItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Map;
import java.util.function.Predicate;

public class ResinCauldronBlock extends LayeredCauldronBlock {
    public ResinCauldronBlock(Properties pProperties, Predicate<Biome.Precipitation> pFillPredicate, Map<Item, CauldronInteraction> pInteractions) {
        super(pProperties, pFillPredicate, pInteractions);
    }

    public void receiveResin(BlockState state, Level level, BlockPos pos) {
        int additionalLevel = state.getValue(LEVEL) + 1;
        BlockState newState = additionalLevel == 0 ? Blocks.CAULDRON.defaultBlockState() : state.setValue(LEVEL, additionalLevel);
        level.setBlockAndUpdate(pos, newState);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newState));
    }


    @Override
    protected boolean canReceiveStalactiteDrip(Fluid pFluid) {
        return false;
    }
    @Override
    public void handlePrecipitation(BlockState pState, Level pLevel, BlockPos pPos, Biome.Precipitation pPrecipitation) {
    }
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack resin = new ItemStack(NMLItems.RESIN.get(), pLevel.random.nextInt(2, 5));
        if (!pPlayer.addItem(resin)) {
            pPlayer.drop(resin, false);
        } else {
            pLevel.playSound(pPlayer,
                    pPlayer.getX(),
                    pPlayer.getY(),
                    pPlayer.getZ(),
                    SoundEvents.ITEM_PICKUP,
                    SoundSource.PLAYERS,
                    0.2F,
                    (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 1.4F + 2.0F);
        }
        lowerFillLevel(pState, pLevel, pPos);
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }
}
