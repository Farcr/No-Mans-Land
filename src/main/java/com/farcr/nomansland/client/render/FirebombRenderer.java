package com.farcr.nomansland.client.render;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.entity.FirebombEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class FirebombRenderer extends ThrowableBombRenderer<FirebombEntity> {

    private static final ResourceLocation MODEL = new ResourceLocation(NoMansLand.MODID, "entity/firebomb");

    public FirebombRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getModelLocation(FirebombEntity entity) {
        return MODEL;
    }
}