package com.farcr.nomansland.core.events;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.registry.NMLBlocks;
import com.farcr.nomansland.core.registry.NMLTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.Iterator;

@Mod.EventBusSubscriber(modid = NoMansLand.MODID)
public class BoneMealingEvents {

        @SubscribeEvent
        public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
            Level level = event.getLevel();
            BlockPos pos = event.getPos();
            BlockState state = level.getBlockState(pos);
            Player player = event.getEntity();
            ItemStack stack = event.getItemStack();

            //Sugarcane Cutting
            if (event.getFace() != Direction.DOWN && stack.is(Items.SHEARS) && state.is(Blocks.SUGAR_CANE) && !player.isSpectator()) {
                level.playSound(player, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!level.isClientSide()) {
                    stack.hurtAndBreak(1, player, (damage) -> {
                        damage.broadcastBreakEvent(event.getHand());
                    });
                    level.setBlockAndUpdate(pos, NMLBlocks.CUT_SUGAR_CANE.get().defaultBlockState());
                }
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                event.setCanceled(true);

            }
            //Bone-Mealing
            if (stack.is(Items.BONE_MEAL) && !player.isSpectator()) {

                // Bonemealing flowers and such #bonemeal_spreads
                if (state.is(NMLTags.BONEMEAL_SPREADS)) {
                    level.playSound(player, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1F, 1F);
//                    level.addParticle();
                    if (!level.isClientSide && !player.isCreative()) stack.shrink(1);

                    int x = pos.getX();
                    int y = pos.getY();
                    int z = pos.getZ();

                    Iterator<BlockPos> it = BlockPos.betweenClosedStream(x - 3, y - 1, z - 3, x + 3, y + 2, z + 3).iterator();
                    while (it.hasNext()) {
                        BlockPos bp = it.next();
                        Block block = level.getBlockState(bp).getBlock();
                        if (level.random.nextFloat() <= 0.3F && state.canSurvive(level, bp) && level.isEmptyBlock(bp)) {
                            BlockPos particlePosition = bp.above();
                            if (!level.isClientSide) level.setBlockAndUpdate(bp, state);
                            else {
                                for (int i = 0; i <= 3; i++) {
                                    level.addParticle(ParticleTypes.COMPOSTER, particlePosition.getX() + Math.random(), particlePosition.getY() + 0.2 + Math.random(), particlePosition.getZ() + Math.random(), 0, 0, 0);
                                }
                            }
                        }
                    }
                    event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                    event.setCanceled(true);
                }

                //Bone-Mealing things that grow upwards #bonemeal_spreads_above
                if (state.is(NMLTags.BONEMEAL_SPREADS_UPWARDS)) {
                    if (level.isEmptyBlock(pos.above())) {
                        level.playSound(player, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1F, 1F);
                        BlockPos particlePosition = pos.above();
                        if (!level.isClientSide) {
                            if (!player.isCreative()) stack.shrink(1);
                            level.setBlockAndUpdate(pos.above(), state);
                        } else {
                            for (int i = 0; i <= 3; i++) {
                                level.addParticle(ParticleTypes.COMPOSTER, particlePosition.getX() + Math.random(), particlePosition.getY() + 0.2 + Math.random(), particlePosition.getZ() + Math.random(), 0, 0, 0);
                            }
                            event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                            event.setCanceled(true);
                        }
                    } else if (level.getBlockState(pos.above()) == state) {
                        for (int y = 0; y < 128; y++) {
                            BlockPos emptyBlock = pos.relative(Direction.UP, y+1);
                            if (level.getBlockState(pos.relative(Direction.UP, y)) == state && level.isEmptyBlock(emptyBlock)) {
                                level.playSound(player, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1F, 1F);
                                if (!level.isClientSide) {
                                    if (!player.isCreative()) stack.shrink(1);
                                    level.setBlockAndUpdate(emptyBlock, state);
                                } else {
                                    for (int i = 0; i <= 3; i++) {
                                        level.addParticle(ParticleTypes.COMPOSTER, emptyBlock.getX() + Math.random(), emptyBlock.getY() + 0.2 + Math.random(), emptyBlock.getZ() + Math.random(), 0, 0, 0);
                                    }
                                }
                                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                                event.setCanceled(true);
                                break;
                            }
                        }
                    }
                }

                // Bonemealing dirt
                if (state.getBlock().equals(Blocks.DIRT) && !level.getBlockState(pos.above()).isSolid()) {
                    // Ensure the dirt that is being right-clicked has a suitable block such as grass nearby
                    for (Direction d : Direction.values()) {
                        for (Direction d1 : Direction.values()) {
                            if (d == d1) break;
                            BlockPos newBlockPos = pos.relative(d);
                            if (level.getBlockState(newBlockPos).getBlock() instanceof SpreadingSnowyDirtBlock) {
                                if (!player.isCreative()) stack.shrink(1);
                                bonemealDirt(level, pos, level.getBlockState(newBlockPos));
                                level.playSound(player, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1F, 1F);
                                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                                event.setCanceled(true);
                                return;
                            } else if (level.getBlockState(newBlockPos.relative(d1)).getBlock() instanceof SpreadingSnowyDirtBlock) {
                                if (!player.isCreative()) stack.shrink(1);
                                bonemealDirt(level, pos, level.getBlockState(newBlockPos.relative(d1)));
                                level.playSound(player, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1F, 1F);
                                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
                                event.setCanceled(true);
                                return;
                            }
                        }
                    }
                }
            }
        }
    public static void bonemealDirt (Level level, BlockPos pos, BlockState state) {
        if (!level.isClientSide) level.setBlockAndUpdate(pos, state);

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        // Iterate through a cube and find suitable dirt blocks that can be turned into the new block
        Iterator<BlockPos> it = BlockPos.betweenClosedStream(x - 3, y - 1, z - 3, x + 3, y + 2, z + 3).iterator();
        while (it.hasNext()) {
            BlockPos bp = it.next();
            BlockState block = level.getBlockState(bp);
            for (Direction d : Direction.values()) {
                Block newBlock = level.getBlockState(bp.relative(d)).getBlock();
                if (level.random.nextFloat() <= 0.3F && block == Blocks.DIRT.defaultBlockState() && newBlock instanceof SpreadingSnowyDirtBlock && !level.getBlockState(bp.above()).isSolid()) {
                    BlockPos particlePosition = bp.above();
                    if (!level.isClientSide) level.setBlockAndUpdate(bp, state);
                    else {
                        for (int i = 0; i <= 3; i++) {
                            level.addParticle(ParticleTypes.COMPOSTER, particlePosition.getX() + Math.random(), particlePosition.getY() + 0.2 + Math.random(), particlePosition.getZ() + Math.random(), 0, 0, 0);
                        }
                    }
                }
            }
        }
    }
}
