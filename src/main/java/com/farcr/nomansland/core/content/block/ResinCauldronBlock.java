//package com.farcr.nomansland.core.content.block;
//
//import com.farcr.nomansland.core.registry.NMLItems;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.cauldron.CauldronInteraction;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.stats.Stats;
//import net.minecraft.world.InteractionResult;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.LevelReader;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.LayeredCauldronBlock;
//import net.minecraft.world.level.block.state.BlockBehaviour;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.gameevent.GameEvent;
//import net.minecraft.world.level.material.Fluid;
//import net.minecraft.world.phys.BlockHitResult;
//import net.minecraft.world.phys.HitResult;
//
//public class ResinCauldronBlock extends LayeredCauldronBlock {
//    public ResinCauldronBlock(Biome.Precipitation precipitation, CauldronInteraction.InteractionMap interactionMap, BlockBehaviour.Properties properties) {
//        super(precipitation, interactionMap, properties);
//    }
//
//    public void fillUp(BlockState state, Level level, BlockPos pos) {
//        int additionalLevel = state.getValue(LEVEL) + 1;
//        BlockState newState = additionalLevel == 0 ? Blocks.CAULDRON.defaultBlockState() : state.setValue(LEVEL, additionalLevel);
//        level.setBlockAndUpdate(pos, newState);
//        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newState));
//    }
//
//    @Override
//    protected boolean canReceiveStalactiteDrip(Fluid pFluid) {
//        return false;
//    }
//
//    @Override
//    public void handlePrecipitation(BlockState pState, Level pLevel, BlockPos pPos, Biome.Precipitation pPrecipitation) {
//    }
//
//    // TODO: ensure this works
//    @Override
//    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
//        ItemStack resin = new ItemStack(NMLItems.RESIN.get(), level.random.nextInt(2, 5));
//        if (!player.addItem(resin)) {
//            if (!player.isCreative()) player.drop(resin, false);
//        }
//        player.awardStat(Stats.USE_CAULDRON);
//        lowerFillLevel(state, level, pos);
//        level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.2F, (level.random.nextFloat() - level.random.nextFloat()) * 1.4F + 2.0F);
//        return InteractionResult.sidedSuccess(level.isClientSide);
//    }
//
//    @Override
//    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
//        return Blocks.CAULDRON.getCloneItemStack(state, target, level, pos, player);
//    }
//}
