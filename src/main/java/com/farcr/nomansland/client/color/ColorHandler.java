package com.farcr.nomansland.client.color;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.util.Mth;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@Mod.EventBusSubscriber(modid = NoMansLand.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ColorHandler {
    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.getItemColors().register((stack, index) -> {
                    return GrassColor.get(0.5D, 1.0D);
                },
                NMLBlocks.GRASS_SPROUTS.get(),
                NMLBlocks.FIDDLEHEAD.get()
        );
        //TODO: REMEMBER TO GET THIS FIXED, CAPPIN SAID NEO HAD SOMETHING FOR THIS? OTHERWISE VEIL WILL BE ABLE TO FIX THIS WHEN IT'S STABLE
        event.getItemColors().register((stack, index) -> {
                    int grassColorPacked = GrassColor.get(0.5D, 1.0D);
                    return packColor(unpackRed(grassColorPacked)   / 0.556862745F,
                            unpackGreen(grassColorPacked) / 0.745098039F,
                            unpackBlue(grassColorPacked)  / 0.705882353F);
                },
                NMLBlocks.OAT_GRASS.get()
        );
        event.getItemColors().register((stack, index) -> {
                    return FoliageColor.get(0.5D, 1.0D);
                },
                NMLBlocks.MAPLE_LEAVES.get(),
                NMLBlocks.WALNUT_LEAVES.get()
        );
    }
    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.getBlockColors().register((state, world, pos, tintIndex) -> {
                    return world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5D, 1.0D);
                },
                NMLBlocks.GRASS_SPROUTS.get(),
                NMLBlocks.FIDDLEHEAD.get(),
                NMLBlocks.CUT_SUGAR_CANE.get()
        );
        event.getBlockColors().register((state, world, pos, tintIndex) -> {
                    int grassColorPacked = GrassColor.get(0.5D, 1.0D);
                    if (world != null && pos != null) grassColorPacked = BiomeColors.getAverageGrassColor(world, pos);
                    return packColor(unpackRed(grassColorPacked)   / 0.556862745F,
                            unpackGreen(grassColorPacked) / 0.745098039F,
                            unpackBlue(grassColorPacked)  / 0.705882353F);
                },
                NMLBlocks.OAT_GRASS.get()
        );
        event.getBlockColors().register((state, world, pos, tintIndex) -> {
                    return world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : GrassColor.get(0.5D, 1.0D);
                },
                NMLBlocks.MAPLE_LEAVES.get(),
                NMLBlocks.WALNUT_LEAVES.get()
//                NMLBlocks.CUT_VINE.get()
        );
    }

    private static float unpackRed(int rgb) {
        return ((rgb >> 16) & 0xFF) / 255.0F;
    }
    private static float unpackGreen(int rgb) {
        return ((rgb >> 8)  & 0xFF) / 255.0F;
    }
    private static float unpackBlue(int rgb) {
        return ((rgb >> 0)  & 0xFF) / 255.0F;
    }
    private static int packColor(float r, float g, float b) {
        return (((int) (Mth.clamp(r, 0, 1) * 255.0F) & 0xFF) << 16) |
                (((int) (Mth.clamp(g, 0, 1) * 255.0F) & 0xFF) << 8) |
                (((int) (Mth.clamp(b, 0, 1) * 255.0F) & 0xFF) << 0);
    }

}
