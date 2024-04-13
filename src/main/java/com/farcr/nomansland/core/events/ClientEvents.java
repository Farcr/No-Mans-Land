package com.farcr.nomansland.core.events;

import com.farcr.nomansland.client.particles.PaleCherryParticle;
import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.client.NMLModelLayers;
import com.farcr.nomansland.core.registry.NMLBlockEntities;
import com.farcr.nomansland.core.registry.NMLParticleTypes;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NoMansLand.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(NMLBlockEntities.NML_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(NMLBlockEntities.NML_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {

        event.registerLayerDefinition(NMLModelLayers.PINE_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(NMLModelLayers.PINE_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(NMLModelLayers.MAPLE_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(NMLModelLayers.MAPLE_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(NMLModelLayers.WALNUT_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(NMLModelLayers.WALNUT_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
    }
    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event){
        event.registerSpriteSet(NMLParticleTypes.PALE_CHERRY_LEAVES.get(),pSprites ->(simpleParticleType, clientLevel, d, e, f, g, h, i) -> new PaleCherryParticle(clientLevel, d, e, f, pSprites) );
    }
}
