package com.farcr.nomansland.common.block;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class MissingIntegrationBlock extends BlockItem {
    public MissingIntegrationBlock(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.nomansland.missing_interaction_block"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
