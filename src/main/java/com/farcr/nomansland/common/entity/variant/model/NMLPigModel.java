package com.farcr.nomansland.common.entity.variant.model;

// Made with Blockbench 4.11.0
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class NMLPigModel {
    public static LayerDefinition createBodyLayer(CubeDeformation cubeDeformation) {
        MeshDefinition meshdefinition = QuadrupedModel.createBodyMesh(6, cubeDeformation);
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition main = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, cubeDeformation), PartPose.offset(0.0F, 16, -1.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -5.0F, -8.0F, 10.0F, 10.0F, 16.0F, cubeDeformation)
                .texOffs(0, 26).addBox(-5.0F, -5.0F, -9.0F, 10.0F, 5.0F, 1.0F, cubeDeformation)
                .texOffs(22, 26).mirror().addBox(-3.0F, 2.0F, -11.0F, 2.0F, 2.0F, 3.0F, cubeDeformation).mirror(false)
                .texOffs(32, 26).mirror().addBox(1.0F, 2.0F, -11.0F, 2.0F, 2.0F, 3.0F, cubeDeformation).mirror(false), PartPose.offset(0.0F, 16.0F, 0.0F));


        PartDefinition snout = body.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(0, 6).addBox(-2.0F, -1.5F, -2.0F, 4.0F, 3.0F, 2.0F, cubeDeformation), PartPose.offset(0.0F, 1.5F, -8.0F));

        PartDefinition earRight = body.addOrReplaceChild("earRight", CubeListBuilder.create().texOffs(36, 0).mirror().addBox(-1.0F, 0.0F, -2.0F, 1.0F, 4.0F, 4.0F, cubeDeformation).mirror(false), PartPose.offsetAndRotation(-5.0F, -4.0F, -6.0F, 0.0F, 0.0F, 0.2443F));

        PartDefinition earLeft = body.addOrReplaceChild("earLeft", CubeListBuilder.create().texOffs(36, 0).addBox(0.0F, 0.0F, -2.0F, 1.0F, 4.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(5.0F, -4.0F, -6.0F, 0.0F, 0, -0.2443F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 7).addBox(0.0F, -4.5F, 0.0F, 0.0F, 5.0F, 4.0F, cubeDeformation), PartPose.offset(0.0F, -1.5F, 8.0F));

//        PartDefinition saddle = body.addOrReplaceChild("saddle", CubeListBuilder.create().texOffs(0, 32).addBox(-5.0F, -5.0F, -8.0F, 10.0F, 10.0F, 16.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition legFrontRight = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(36, 8).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 5.0F, 3.0F, cubeDeformation), PartPose.offset(-3.4F, 21.0F, -4.5F));

        PartDefinition legFrontLeft = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(36, 8).mirror().addBox(-1.5F, -2.0F, -1.5F, 3.0F, 5.0F, 3.0F, cubeDeformation).mirror(false), PartPose.offset(3.4F, 21.0F, -4.5F));

        PartDefinition legBackRight = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, cubeDeformation).mirror(false), PartPose.offset(-3.4F, 21.0F, 6.5F));

        PartDefinition legBackLeft = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, cubeDeformation).mirror(false), PartPose.offset(3.4F, 21.0F, 6.5F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}
