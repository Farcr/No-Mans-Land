package com.farcr.nomansland.core.content.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.block.RichSoilBlock;
import vectorwing.farmersdelight.common.tag.ModTags;

@Mixin(RichSoilBlock.class)
public class RichSoilBlockMixin {

    /**
     * Due to how Rich Soil works with Mushrooms and growing them into Colonies, this mixin is required to let the Field Mushroom turn into a colony on top of rich soil.
     */
    @Inject(method = "randomTick", at = @At("HEAD"))
    private void tryConvertFieldMushroom(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand, CallbackInfo ci) {
        if (!level.isClientSide) {
            BlockPos abovePos = pos.above();
            BlockState aboveState = level.getBlockState(abovePos);
            Block aboveBlock = aboveState.getBlock();
            if (aboveState.is(ModTags.UNAFFECTED_BY_RICH_SOIL) || aboveBlock instanceof TallFlowerBlock) {
                return;
            }

//            if (level.getBlockState(pos.above()).getBlock() == NMLBlocks.FIELD_MUSHROOM.get()) {
//                level.setBlockAndUpdate(pos.above(), (NMLBlocks.FIELD_MUSHROOM_COLONY.get()).defaultBlockState());
//            }
        }
    }
}
