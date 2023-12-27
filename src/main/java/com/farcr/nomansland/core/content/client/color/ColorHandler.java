package com.farcr.nomansland.core.content.client.color;

import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.GrassColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ColorHandler {
    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.getItemColors().register((stack, index) -> {
                    return GrassColor.get(0.5D, 1.0D);
                },
                NMLBlocks.GRASS_SPROUTS.get(),
                NMLBlocks.MAPLE_LEAVES.get()
        );
    }
    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.getBlockColors().register((state, world, pos, tintIndex) -> {
                    return world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5D, 1.0D);
                },
                NMLBlocks.GRASS_SPROUTS.get(),
                NMLBlocks.MAPLE_LEAVES.get()
        );
    }

}
