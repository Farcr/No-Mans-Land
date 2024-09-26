package com.farcr.nomansland.common.mixin;

import com.farcr.nomansland.common.registry.NMLBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.farcr.nomansland.common.block.FrostedGrassBlock.SNOWLOGGED;

@Mixin(Biome.class)
public class BiomeMixin {

    /**
     * This method checks if snow can be placed in this block position under the appropriate weather,
     * this mixin makes it return true if it is unsnowlogged grass at the block position rather than just air,
     * as it is snowlogged later in the ServerLevelMixin
     */
    @Inject(method = "shouldSnow", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/LevelReader;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;", shift = At.Shift.AFTER), cancellable = true)
    private void shouldSnow(LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockState blockstate = level.getBlockState(pos);
        if (blockstate.is(Blocks.SHORT_GRASS) || (blockstate.is(NMLBlocks.FROSTED_GRASS.get()) && !blockstate.getValue(SNOWLOGGED))) {
            cir.setReturnValue(true);
        }
    }
}
