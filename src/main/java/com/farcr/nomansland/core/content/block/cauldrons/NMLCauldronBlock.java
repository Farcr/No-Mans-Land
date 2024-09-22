package com.farcr.nomansland.core.content.block.cauldrons;

import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
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
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.data.internal.NeoForgeBlockTagsProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

public class NMLCauldronBlock extends LayeredCauldronBlock {

    private final Holder<Block> cauldronBlock;
    private final Holder<Item> emptyBottle;
    private final Holder<Item> fullBottle;
    private final Holder<Item> inputItem;
    private final Holder<Item> outputItem;
    private final Holder<Item> containedItem;
    private final DeferredHolder<ParticleType<?>, SimpleParticleType> particleType;
    private final boolean sticky;

    public NMLCauldronBlock(NMLCauldronType cauldronType) {
        super(Biome.Precipitation.NONE, CauldronInteraction.EMPTY, BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
        this.cauldronBlock = cauldronType.getCauldronBlock();
        this.emptyBottle = cauldronType.getEmptyBottle();
        this.fullBottle = cauldronType.getFullBottle();
        this.inputItem = cauldronType.getInputItem();
        this.outputItem = cauldronType.getOutputItem();
        this.containedItem = cauldronType.getContainedItem();
        this.particleType = cauldronType.getParticleType();
        this.sticky = cauldronType.isSticky();
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
        if (emptyBottle!=null && player.isHolding(emptyBottle.value())) {
            player.setItemInHand(hand, ItemUtils.createFilledResult(player.getItemInHand(hand), player, new ItemStack(fullBottle)));
            player.awardStat(Stats.ITEM_USED.get(emptyBottle.value()));
            lowerFillLevel(state, level, pos);
            level.playSound(player, pos, SoundEvents.BOTTLE_FILL, SoundSource.PLAYERS, 1, 1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        } else if (fullBottle!=null && player.isHolding(fullBottle.value()) && !cauldron.isFull(state)) {
            player.setItemInHand(hand, ItemUtils.createFilledResult(player.getItemInHand(hand), player, new ItemStack(emptyBottle)));
            player.awardStat(Stats.ITEM_USED.get(fullBottle.value()));
            fillUp(state, level, pos);
            level.playSound(player, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.PLAYERS, 1, 1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        } else if (inputItem!=null && player.isHolding(inputItem.value())) {
            if (!(player.isCreative() && player.getInventory().hasAnyMatching(s -> s.is(outputItem)))) {
                ItemStack outputStack = new ItemStack(outputItem);
                if (!player.addItem(outputStack)) {
                    player.drop(outputStack, false);
                } else {
                    level.playSound(player, pos, SoundEvents.HONEY_BLOCK_STEP, SoundSource.PLAYERS, 2, 1);
                }
            } else {
                level.playSound(player, pos, SoundEvents.HONEY_BLOCK_STEP, SoundSource.PLAYERS, 2, 1);
            }
            player.setItemInHand(hand, new ItemStack(inputItem, player.getItemInHand(hand).getCount() - 1));
            player.awardStat(Stats.ITEM_USED.get(inputItem.value()));
            lowerFillLevel(state, level, pos);
            level.playSound(player, pos, SoundEvents.HONEY_BLOCK_STEP, SoundSource.PLAYERS, 2, 1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        } else if (containedItem != null) {
            if (player.isHolding(containedItem.value()) && (player.isCreative() || player.getItemInHand(hand).getCount() >= 3) && state.getValue(LEVEL) < 3) {
                if (!player.isCreative())
                    player.setItemInHand(hand, new ItemStack(player.getItemInHand(hand).getItemHolder(), player.getItemInHand(hand).getCount() - 3));
                player.awardStat(Stats.USE_CAULDRON);
                fillUp(state, level, pos);
                level.playSound(null, pos, SoundEvents.HONEY_BLOCK_PLACE, SoundSource.BLOCKS, 1, 1);
            } else if (!(player.isCreative() && player.getInventory().hasAnyMatching(s -> s.is(containedItem)))) {
                lowerFillLevel(state, level, pos);
                ItemStack containedStack = new ItemStack(containedItem, 3);
                if (!player.addItem(containedStack)) {
                    player.drop(containedStack, false);
                } else {
                    level.playSound(player,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            SoundEvents.ITEM_PICKUP,
                            SoundSource.PLAYERS,
                            0.2F,
                            (level.random.nextFloat() - level.random.nextFloat()) * 1.4F + 2.0F);
                }
            } else {
                level.playSound(player,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.ITEM_PICKUP,
                        SoundSource.PLAYERS,
                        0.2F,
                        (level.random.nextFloat() - level.random.nextFloat()) * 1.4F + 2.0F);
            }
            player.awardStat(Stats.USE_CAULDRON);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        return ItemInteractionResult.FAIL;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return Blocks.CAULDRON.getCloneItemStack(state, target, level, pos, player);
    }

    public Holder<Block> getCauldronBlock() {
        return cauldronBlock;
    }

    public SimpleParticleType getParticleType() {
        return particleType.get();
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && entity.isOnFire() && this.isEntityInsideContent(state, pos, entity)) {
            if (cauldronBlock == NMLBlocks.RESIN_OIL_CAULDRON) {
                entity.setRemainingFireTicks(entity.getRemainingFireTicks()+50*state.getValue(LEVEL));
                level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 3, Level.ExplosionInteraction.BLOCK);
            } else {
                entity.clearFire();
                level.playSound(entity, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1, 1);
            }
        }
        if (entity instanceof LivingEntity && sticky) {
            if (entity.getY() > pos.getY()+.6) level.playSound(null, pos, SoundEvents.HONEY_BLOCK_SLIDE, SoundSource.BLOCKS, 1, 1);
            if (state.getValue(LEVEL) > 1) entity.makeStuckInBlock(state, new Vec3(.9, .9, .9));
        }
        BlockState stateUnder = level.getBlockState(pos.below());
        if (entity instanceof ItemEntity item && item.getItem().is(Items.HONEYCOMB) && stateUnder.is(BlockTags.FIRE) && cauldronBlock == NMLBlocks.RESIN_CAULDRON) {
            entity.remove(Entity.RemovalReason.KILLED);
            level.setBlockAndUpdate(pos, NMLBlocks.RESIN_OIL_CAULDRON.get().defaultBlockState());
        }
    }

}
