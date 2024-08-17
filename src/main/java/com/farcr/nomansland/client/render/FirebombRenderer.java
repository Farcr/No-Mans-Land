package com.farcr.nomansland.client.render;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.entity.bombs.FirebombEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

public class FirebombRenderer extends ThrowableBombRenderer<FirebombEntity> {

    private static final ModelResourceLocation MODEL = ModelResourceLocation.standalone(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "entity/firebomb"));

    public FirebombRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ModelResourceLocation getModelLocation(FirebombEntity entity) {
        return MODEL;
    }
}