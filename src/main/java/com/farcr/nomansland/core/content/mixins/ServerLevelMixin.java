package com.farcr.nomansland.core.content.mixins;

import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.farcr.nomansland.core.content.block.FrostedGrassBlock.SNOWLOGGED;
import static net.minecraft.world.level.block.SnowyDirtBlock.SNOWY;

@Mixin(value = ServerLevel.class)
public abstract class ServerLevelMixin {
    @Shadow public abstract ServerLevel getLevel();

    /**
    * This mixin snowlogs grass when it's snowing
    */
    @Inject(method = "tickPrecipitation", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z", ordinal = 2, shift = At.Shift.BEFORE), cancellable = true)
    private void tickPrecipitation(BlockPos pos, CallbackInfo ci) {
        ServerLevel level = this.getLevel();
        pos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, pos);
        BlockState state = level.getBlockState(pos);

        if (state.is(Blocks.SHORT_GRASS) || state.is(NMLBlocks.FROSTED_GRASS.get())) {
            level.setBlockAndUpdate(pos, NMLBlocks.FROSTED_GRASS.get().defaultBlockState().setValue(SNOWLOGGED, true));
            BlockPos posBelow = pos.below();
            BlockState stateUnder = level.getBlockState(posBelow);
            if (stateUnder.getBlock() instanceof SnowyDirtBlock)
                level.setBlockAndUpdate(posBelow, stateUnder.setValue(SNOWY, true));
            ci.cancel();
        }
    }
}
