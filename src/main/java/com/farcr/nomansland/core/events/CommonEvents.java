package com.farcr.nomansland.core.events;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CommonEvents {
    @Mod.EventBusSubscriber(modid = NoMansLand.MODID)
    public static class ForgeEvents {
    }

    @Mod.EventBusSubscriber(modid =  NoMansLand.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        }
    }
}
