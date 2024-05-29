package com.farcr.nomansland.client.events;

import com.farcr.nomansland.client.NMLModelLayers;
import com.farcr.nomansland.client.models.BuriedModel;
import com.farcr.nomansland.client.models.MooseModel;
import com.farcr.nomansland.client.particle.*;
import com.farcr.nomansland.client.render.BuriedRenderer;
import com.farcr.nomansland.client.render.FirebombRenderer;
import com.farcr.nomansland.client.render.MooseRenderer;
import com.farcr.nomansland.client.render.NMLBoatRenderer;
import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.registry.NMLBlockEntities;
import com.farcr.nomansland.core.registry.NMLEntities;
import com.farcr.nomansland.core.registry.NMLParticleTypes;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = NoMansLand.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(NMLEntities.BOAT.get(), pContext -> new NMLBoatRenderer(pContext, false));
        EntityRenderers.register(NMLEntities.CHEST_BOAT.get(), pContext -> new NMLBoatRenderer(pContext, true));

        EntityRenderers.register(NMLEntities.BURIED.get(), BuriedRenderer::new);
        EntityRenderers.register(NMLEntities.MOOSE.get(), MooseRenderer::new);

        EntityRenderers.register(NMLEntities.FIREBOMB.get(), FirebombRenderer::new);
    }

    @SubscribeEvent
    public static void registerModels(ModelEvent.RegisterAdditional event) {
        event.register(new ResourceLocation(NoMansLand.MODID, "entity/firebomb"));
    }
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

        event.registerLayerDefinition(NMLModelLayers.MOOSE_LAYER, MooseModel::createBodyLayer);
        event.registerLayerDefinition(NMLModelLayers.BURIED_LAYER, BuriedModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(NMLParticleTypes.PALE_CHERRY_LEAVES.get(), pSprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new FallingParticle(clientLevel, d, e, f, pSprites));
        event.registerSpriteSet(NMLParticleTypes.CAVE_DUST.get(), pSprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new FallingParticle(clientLevel, d, e, f, pSprites));
        event.registerSpriteSet(NMLParticleTypes.RESIN_DROPLET.get(), pSprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new ResinFallingParticle(clientLevel, d, e, f, pSprites));
        event.registerSpriteSet(NMLParticleTypes.RESIN_DROPLET_FLAT.get(), pSprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new ResinLandParticle(clientLevel, d, e, f, pSprites));
        event.registerSpriteSet(NMLParticleTypes.SCULK_AMBIENCE.get(), pSprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new SculkAmbienceParticle(clientLevel, d, e, f, pSprites));
        event.registerSpriteSet(NMLParticleTypes.MALEVOLENT_EMBERS.get(), pSprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new EmbersParticle(clientLevel, d, e, f, g, h, i, pSprites));
        event.registerSpriteSet(NMLParticleTypes.MALEVOLENT_FLAME.get(), pSprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new FlameParticle(clientLevel, d, e, f, g, h, i, pSprites));
    }
}
