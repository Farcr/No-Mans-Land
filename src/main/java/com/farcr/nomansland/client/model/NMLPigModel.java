package com.farcr.nomansland.client.model;

// Made with Blockbench 4.11.0
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Pig;

public class NMLPigModel<T extends Pig> {
    public static LayerDefinition createBodyLayer(CubeDeformation cubeDeformation) {
        MeshDefinition meshdefinition = QuadrupedModel.createBodyMesh(6, cubeDeformation);
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, cubeDeformation), PartPose.offset(0.0F, 0, -1.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -5.0F, -8.0F, 10.0F, 10.0F, 16.0F, cubeDeformation)
                .texOffs(0, 26).addBox(-5.0F, -5.0F, -9.0F, 10.0F, 5.0F, 1.0F, cubeDeformation)
                .texOffs(22, 26).mirror().addBox(-3.0F, 2.0F, -11.0F, 2.0F, 2.0F, 3.0F, cubeDeformation).mirror(false)
                .texOffs(32, 26).mirror().addBox(1.0F, 2.0F, -11.0F, 2.0F, 2.0F, 3.0F, cubeDeformation).mirror(false), PartPose.offset(0.0F, 16.0F, 0.0F));


        PartDefinition snout = head.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(0, 6).addBox(-2.0F, -1.5F, -2.0F, 4.0F, 3.0F, 2.0F, cubeDeformation), PartPose.offset(0.0F, 1.5F, -8.0F));

        PartDefinition rightEar = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(36, 0).mirror().addBox(-1.0F, 0.0F, -2.0F, 1.0F, 4.0F, 4.0F, cubeDeformation).mirror(false), PartPose.offsetAndRotation(-5.0F, -4.0F, -6.0F, 0.0F, 0.0F, 0.2443F));

        PartDefinition leftEar = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(36, 0).addBox(0.0F, 0.0F, -2.0F, 1.0F, 4.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(5.0F, -4.0F, -6.0F, 0.0F, 0, -0.2443F));

        PartDefinition tail = head.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 7).addBox(0.0F, -4.5F, 0.0F, 0.0F, 5.0F, 4.0F, cubeDeformation), PartPose.offset(0.0F, -1.5F, 8.0F));

//        PartDefinition saddle = body.addOrReplaceChild("saddle", CubeListBuilder.create().texOffs(0, 32).addBox(-5.0F, -5.0F, -8.0F, 10.0F, 10.0F, 16.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightFrontLeg = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(36, 8).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 5.0F, 3.0F, cubeDeformation), PartPose.offset(-3.4F, 21.0F, -4.5F));

        PartDefinition leftFrontLeg = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(36, 8).mirror().addBox(-1.5F, -2.0F, -1.5F, 3.0F, 5.0F, 3.0F, cubeDeformation).mirror(false), PartPose.offset(3.4F, 21.0F, -4.5F));

        PartDefinition rightHindLeg = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, cubeDeformation).mirror(false), PartPose.offset(-3.4F, 21.0F, 6.5F));

        PartDefinition leftHindLeg = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, cubeDeformation).mirror(false), PartPose.offset(3.4F, 21.0F, 6.5F));

        PartDefinition headBaby = body.addOrReplaceChild("head_baby", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.5F, -4.0F, 5.0F, 5.0F, 8.0F, cubeDeformation), PartPose.offset(0.0F, 18.5F, 0.0F));

        PartDefinition snoutBaby = headBaby.addOrReplaceChild("snout_baby", CubeListBuilder.create().texOffs(18, 0).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 1.0F, cubeDeformation), PartPose.offset(0.0F, 1.5F, -4.0F));

        PartDefinition earRightBaby = headBaby.addOrReplaceChild("right_ear_baby", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, 0.0F, -1.5F, 1.0F, 3.0F, 3.0F, cubeDeformation).mirror(false), PartPose.offset(-2.5F, -2.5F, -2.5F));

        PartDefinition earLeftBaby = headBaby.addOrReplaceChild("left_ear_baby", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -1.5F, 1.0F, 3.0F, 3.0F, cubeDeformation), PartPose.offset(2.5F, -2.5F, -2.5F));

        PartDefinition tailBaby = headBaby.addOrReplaceChild("tail_baby", CubeListBuilder.create().texOffs(18, 0).addBox(0.0F, -2.5F, 0.0F, 0.0F, 3.0F, 3.0F, cubeDeformation), PartPose.offset(0.0F, 0.0F, 4.0F));

        PartDefinition rightFrontLegBaby = headBaby.addOrReplaceChild("right_front_leg_baby", CubeListBuilder.create().texOffs(0, 13).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, cubeDeformation).mirror(false), PartPose.offset(-1.5F, 2.5F, -2.0F));

        PartDefinition leftFrontLegBaby = headBaby.addOrReplaceChild("left_front_leg_baby", CubeListBuilder.create().texOffs(0, 13).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offset(1.5F, 2.5F, -2.0F));

        PartDefinition rightHindLegBaby = headBaby.addOrReplaceChild("right_hind_leg_baby", CubeListBuilder.create().texOffs(8, 13).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, cubeDeformation).mirror(false), PartPose.offset(-1.5F, 2.5F, 3.0F));

        PartDefinition leftHindLegBaby = headBaby.addOrReplaceChild("left_hind_leg_baby", CubeListBuilder.create().texOffs(8, 13).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offset(1.5F, 2.5F, 3.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static void doRotations(Pig pig, ModelPart root, float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
        ModelPart head = root.getChild("head");
        ModelPart body = root.getChild("body");
        ModelPart rightHindLeg = root.getChild("right_hind_leg");
        ModelPart leftHindLeg = root.getChild("left_hind_leg");
        ModelPart rightFrontLeg = root.getChild("right_front_leg");
        ModelPart leftFrontLeg = root.getChild("left_front_leg");
        ModelPart headBaby = body.getChild("head_baby");
        ModelPart rightFrontLegBaby = headBaby.getChild("right_front_leg_baby");
        ModelPart leftFrontLegBaby = headBaby.getChild("left_front_leg_baby");
        ModelPart rightHindLegBaby = headBaby.getChild("right_hind_leg_baby");
        ModelPart leftHindLegBaby = headBaby.getChild("left_hind_leg_baby");
        boolean baby = pig.isBaby();

        headBaby.xScale = 2;
        headBaby.yScale = 2;
        headBaby.zScale = 2;

        head.xRot = headPitch * 0.017453292F / 2;
        head.yRot = netHeadYaw * 0.017453292F / 4;
        headBaby.xRot = head.xRot / 2;
        headBaby.yRot = head.yRot / 2;

        rightHindLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.7F * limbSwingAmount;
        leftHindLeg.xRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 1.7F * limbSwingAmount;
        rightFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 1.7F * limbSwingAmount;
        leftFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.7F * limbSwingAmount;

        head.visible = !baby;
        leftHindLeg.visible = !baby;
        rightHindLeg.visible = !baby;
        leftFrontLeg.visible = !baby;
        rightFrontLeg.visible = !baby;
        headBaby.visible = baby;

        rightHindLegBaby.xRot = rightHindLeg.xRot;
        leftHindLegBaby.xRot = leftHindLeg.xRot;
        rightFrontLegBaby.xRot = rightFrontLeg.xRot;
        leftFrontLegBaby.xRot = leftFrontLeg.xRot;
    }
}
