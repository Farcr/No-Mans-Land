package com.farcr.nomansland.client.event;

import com.farcr.nomansland.client.NMLModelLayers;
import com.farcr.nomansland.client.model.BuriedModel;
import com.farcr.nomansland.client.model.MooseModel;
import com.farcr.nomansland.client.particle.*;
import com.farcr.nomansland.client.renderer.ExplosiveRenderer;
import com.farcr.nomansland.client.renderer.FirebombRenderer;
import com.farcr.nomansland.client.renderer.NMLBoatRenderer;
import com.farcr.nomansland.NoMansLand;
import com.farcr.nomansland.common.registry.NMLBlockEntities;
import com.farcr.nomansland.common.registry.NMLEntities;
import com.farcr.nomansland.common.registry.NMLParticleTypes;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = NoMansLand.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(NMLEntities.BOAT.get(), pContext -> new NMLBoatRenderer(pContext, false));
        EntityRenderers.register(NMLEntities.CHEST_BOAT.get(), pContext -> new NMLBoatRenderer(pContext, true));

//        EntityRenderers.register(NMLEntities.BURIED.get(), BuriedRenderer::new);
//        EntityRenderers.register(NMLEntities.MOOSE.get(), MooseRenderer::new);

        EntityRenderers.register(NMLEntities.FIREBOMB.get(), FirebombRenderer::new);
        EntityRenderers.register(NMLEntities.EXPLOSIVE.get(), ExplosiveRenderer::new);
    }

    @SubscribeEvent
    public static void registerModels(ModelEvent.RegisterAdditional event) {
        event.register(ModelResourceLocation.standalone(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "entity/firebomb")));
        event.register(ModelResourceLocation.standalone(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "entity/explosive")));
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

        event.registerLayerDefinition(NMLModelLayers.WILLOW_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(NMLModelLayers.WILLOW_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(NMLModelLayers.MOOSE_LAYER, MooseModel::createBodyLayer);
        event.registerLayerDefinition(NMLModelLayers.BURIED_LAYER, BuriedModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(NMLParticleTypes.PALE_CHERRY_LEAVES.get(), sprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new FallingParticle(clientLevel, d, e, f, sprites));
        event.registerSpriteSet(NMLParticleTypes.CAVE_DUST.get(), sprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new CaveDustParticle(clientLevel, d, e, f, sprites));
        event.registerSpriteSet(NMLParticleTypes.RESIN_DROPLET.get(), sprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new FluidFallingParticle(clientLevel, d, e, f, sprites, NMLParticleTypes.RESIN_DROPLET_FLAT.get()));
        event.registerSpriteSet(NMLParticleTypes.RESIN_DROPLET_FLAT.get(), sprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new FluidLandParticle(clientLevel, d, e, f, sprites));
        event.registerSpriteSet(NMLParticleTypes.MAPLE_SYRUP_DROPLET.get(), sprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new FluidFallingParticle(clientLevel, d, e, f, sprites, NMLParticleTypes.MAPLE_SYRUP_DROPLET_FLAT.get()));
        event.registerSpriteSet(NMLParticleTypes.MAPLE_SYRUP_DROPLET_FLAT.get(), sprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new FluidLandParticle(clientLevel, d, e, f, sprites));
        event.registerSpriteSet(NMLParticleTypes.OIL.get(), sprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new FluidFallingParticle(clientLevel, d, e, f, sprites, NMLParticleTypes.OIL_FLAT.get()));
        event.registerSpriteSet(NMLParticleTypes.OIL_FLAT.get(), sprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new FluidLandParticle(clientLevel, d, e, f, sprites));
        event.registerSpriteSet(NMLParticleTypes.RESIN_OIL_BUBBLE.get(), sprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new BubbleParticle(clientLevel, d, e, f, g, h, i, sprites, NMLParticleTypes.RESIN_OIL_BUBBLE_POP.get()));
        event.registerSpriteSet(NMLParticleTypes.RESIN_OIL_BUBBLE_POP.get(), sprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new BubblePopParticle(clientLevel, d, e, f, g, h, i, sprites));
        event.registerSpriteSet(NMLParticleTypes.SCULK_AMBIENCE.get(), sprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new SculkAmbienceParticle(clientLevel, d, e, f, sprites));
        event.registerSpriteSet(NMLParticleTypes.MALEVOLENT_EMBERS.get(), sprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new EmbersParticle(clientLevel, d, e, f, g, h, i, sprites));
        event.registerSpriteSet(NMLParticleTypes.MALEVOLENT_FLAME.get(), sprites
                -> (simpleParticleType, clientLevel, d, e, f, g, h, i)
                -> new FlameParticle(clientLevel, d, e, f, g, h, i, sprites));
    }
}
