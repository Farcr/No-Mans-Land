package com.farcr.nomansland.core.content.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class MissingIntegrationItem extends Item {
    public MissingIntegrationItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.nomansland.missing_interaction_item"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
