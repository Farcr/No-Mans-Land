package com.farcr.nomansland.core.content.block;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.LayeredCauldronBlock;

import java.util.Map;
import java.util.function.Predicate;

public class ResinCauldronBlock extends LayeredCauldronBlock {
    public ResinCauldronBlock(Properties pProperties, Predicate<Biome.Precipitation> pFillPredicate, Map<Item, CauldronInteraction> pInteractions) {
        super(pProperties, pFillPredicate, pInteractions);
    }
//    @Override
//    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
//        ItemStack resin = new ItemStack(NMLItems.RESIN.get());
//        if (!pPlayer.addItem(resin)) {
//            pPlayer.drop(resin, false);
//        } else {
//            pLevel.playSound(pPlayer,
//                    pPlayer.getX(),
//                    pPlayer.getY(),
//                    pPlayer.getZ(),
//                    SoundEvents.ITEM_PICKUP,
//                    SoundSource.PLAYERS,
//                    0.2F,
//                    (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 1.4F + 2.0F);
//        }
//        pLevel.setBlock(pPos, this.defaultBlockState(), 3);
//        return InteractionResult.sidedSuccess(pLevel.isClientSide);
//    }
}
