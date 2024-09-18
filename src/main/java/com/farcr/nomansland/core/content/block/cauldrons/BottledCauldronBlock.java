package com.farcr.nomansland.core.content.block.cauldrons;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class BottledCauldronBlock extends LayeredCauldronBlock {

    private final Holder<Block> cauldronBlock;
    private final Holder<Item> emptyBottle;
    private final Holder<Item> fullBottle;

    public BottledCauldronBlock(BottledCauldronType cauldronType) {
        super(Biome.Precipitation.NONE, CauldronInteraction.EMPTY, BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
        this.cauldronBlock = cauldronType.getCauldronBlock();
        this.emptyBottle = cauldronType.getEmptyBottle();
        this.fullBottle = cauldronType.getFullBottle();
    }

    public void fillUp(BlockState state, Level level, BlockPos pos) {
        BlockState newState = state.setValue(LEVEL, state.getValue(LEVEL) + 1);
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
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        AbstractCauldronBlock cauldron = (AbstractCauldronBlock) state.getBlock();
        if (player.isHolding(emptyBottle.value())) {
            player.setItemInHand(hand, ItemUtils.createFilledResult(player.getItemInHand(hand), player, new ItemStack(fullBottle)));
            player.awardStat(Stats.USE_CAULDRON);
            player.awardStat(Stats.ITEM_USED.get(emptyBottle.value()));
            lowerFillLevel(state, level, pos);
            level.playSound(player, pos, SoundEvents.BOTTLE_FILL, SoundSource.PLAYERS, 1.0F, 1.0F);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        } else if (player.isHolding(fullBottle.value()) && !cauldron.isFull(state)) {
            player.setItemInHand(hand, ItemUtils.createFilledResult(player.getItemInHand(hand), player, new ItemStack(emptyBottle)));
            player.awardStat(Stats.USE_CAULDRON);
            player.awardStat(Stats.ITEM_USED.get(fullBottle.value()));
            fillUp(state, level, pos);
            level.playSound(player, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.PLAYERS, 1.0F, 1.0F);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        } else
        return ItemInteractionResult.FAIL;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return Blocks.CAULDRON.getCloneItemStack(state, target, level, pos, player);
    }

    public Holder<Block> getCauldronBlock() {
        return cauldronBlock;
    }
}
