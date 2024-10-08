package com.farcr.nomansland.common.mixin;

import com.farcr.nomansland.common.block.torches.ExtinguishedTorchBlock;
import com.farcr.nomansland.common.block.torches.ExtinguishedWallTorchBlock;
import com.farcr.nomansland.common.registry.NMLBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrownPotion.class)
public abstract class ThrownPotionMixin extends ProjectileMixin {
    @Inject(method = "dowseFire", at = @At("TAIL"))
    private void dowseFire(BlockPos pos, CallbackInfo ci) {
        BlockState state = this.level().getBlockState(pos);
        if (state.getBlock() instanceof TorchBlock) {
        this.level().gameEvent(this.getOwner(), GameEvent.BLOCK_CHANGE, pos);
        this.level().playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);

        if (state.is(Blocks.TORCH)) this.level().setBlock(pos, NMLBlocks.EXTINGUISHED_TORCH.get().withPropertiesOf(state), 11);
        if (state.is(Blocks.WALL_TORCH)) this.level().setBlock(pos, NMLBlocks.EXTINGUISHED_WALL_TORCH.get().withPropertiesOf(state), 11);
        if (state.is(Blocks.SOUL_TORCH)) this.level().setBlock(pos, NMLBlocks.EXTINGUISHED_SOUL_TORCH.get().withPropertiesOf(state), 11);
        if (state.is(Blocks.SOUL_WALL_TORCH)) this.level().setBlock(pos, NMLBlocks.EXTINGUISHED_SOUL_WALL_TORCH.get().withPropertiesOf(state), 11);
        if (state.is(NMLBlocks.SCONCE_TORCH)) this.level().setBlock(pos, NMLBlocks.EXTINGUISHED_SCONCE_TORCH.get().withPropertiesOf(state), 11);
        if (state.is(NMLBlocks.SCONCE_WALL_TORCH)) this.level().setBlock(pos, NMLBlocks.EXTINGUISHED_SCONCE_WALL_TORCH.get().withPropertiesOf(state), 11);
        if (state.is(NMLBlocks.SCONCE_SOUL_TORCH)) this.level().setBlock(pos, NMLBlocks.EXTINGUISHED_SCONCE_SOUL_TORCH.get().withPropertiesOf(state), 11);
        if (state.is(NMLBlocks.SCONCE_SOUL_WALL_TORCH)) this.level().setBlock(pos, NMLBlocks.EXTINGUISHED_SCONCE_SOUL_WALL_TORCH.get().withPropertiesOf(state), 11);
        }
    }
}
