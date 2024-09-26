package com.farcr.nomansland.client.renderer;

import com.farcr.nomansland.NoMansLand;
import com.farcr.nomansland.common.entity.bombs.ExplosiveEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

public class ExplosiveRenderer extends ThrowableBombRenderer<ExplosiveEntity> {

    private static final ModelResourceLocation MODEL = ModelResourceLocation.standalone(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "entity/explosive"));

    public ExplosiveRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ModelResourceLocation getModelLocation(ExplosiveEntity entity) {
        return MODEL;
    }
}

