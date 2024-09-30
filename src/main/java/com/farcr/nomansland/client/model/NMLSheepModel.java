package com.farcr.nomansland.client.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Sheep;

public class NMLSheepModel {
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.offset(0, 0, 0));

        PartDefinition bodyAdult = body.addOrReplaceChild("body_adult", CubeListBuilder.create().texOffs(0, 0).addBox(-4, -4, -7, 8, 8, 14), PartPose.offset(0, 12, -1));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 22).addBox(-2, -5, -7, 4, 5, 8), PartPose.offsetAndRotation(0, -3, -5.5F, 0.6981F, 0, 0));

        PartDefinition rightEar = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(0, 0).addBox(-2, -1, 0, 2, 2, 1), PartPose.offsetAndRotation(-2, -3.8F, -0.7F, -0.6981F, 0, 0));

        PartDefinition leftEar = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0, -1, 0, 2, 2, 1).mirror(false), PartPose.offsetAndRotation(2, -3.8F, -0.7F, -0.6981F, 0, 0));

        PartDefinition rightHorn = head.addOrReplaceChild("right_horn", CubeListBuilder.create().texOffs(0, 7).addBox(-0.5F, -1, 0, 1, 2, 3), PartPose.offset(-1.5F, -4, 1));

        PartDefinition leftHorn = head.addOrReplaceChild("left_horn", CubeListBuilder.create().texOffs(0, 7).addBox(-0.5F, -1, 0, 1, 2, 3), PartPose.offset(1.5F, -4, 1));

        PartDefinition shearedTail = bodyAdult.addOrReplaceChild("sheared_tail", CubeListBuilder.create().texOffs(0, 3).addBox(-1, -1, 0, 2, 2, 2), PartPose.offset(0, -3, 7));

        PartDefinition rightFrontLeg = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(30, 0).mirror().addBox(-1.5F, 0, -1.5F, 3, 8, 3).mirror(false), PartPose.offset(-2.4F, 4, -4.5F));

        PartDefinition rightFrontLegWool = rightFrontLeg.addOrReplaceChild("right_front_leg_wool", CubeListBuilder.create().texOffs(78, 0).addBox(-2, -1, -2, 4, 4, 4), PartPose.offset(0, 0, 0));

        PartDefinition leftFrontLeg = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(42, 0).mirror().addBox(-1.5F, 0, -1.5F, 3, 8, 3).mirror(false), PartPose.offset(2.4F, 4, -4.5F));

        PartDefinition leftFrontLegWool = leftFrontLeg.addOrReplaceChild("left_front_leg_wool", CubeListBuilder.create().texOffs(78, 8).addBox(-2, -1, -2, 4, 4, 4), PartPose.offset(0, 0, 0));

        PartDefinition rightHindLeg = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(24, 22).mirror().addBox(-1.5F, -2, -1.5F, 3, 10, 3).mirror(false), PartPose.offset(-2.4F, 4, 6.5F));

        PartDefinition rightHindLegWool = rightHindLeg.addOrReplaceChild("right_hind_leg_wool", CubeListBuilder.create().texOffs(48, 25).addBox(-2, -1, -2, 4, 4, 4), PartPose.offset(0, 0, 0));

        PartDefinition leftHindLeg = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(36, 22).mirror().addBox(-1.5F, -2, -1.5F, 3, 10, 3).mirror(false), PartPose.offset(2.4F, 4, 6.5F));

        PartDefinition leftHindLegWool = leftHindLeg.addOrReplaceChild("left_hind_leg_wool", CubeListBuilder.create().texOffs(64, 25).addBox(-2, -1, -2, 4, 4, 4), PartPose.offset(0, 0, 0));

        PartDefinition bodyWool = bodyAdult.addOrReplaceChild("body_wool", CubeListBuilder.create().texOffs(45, 1).addBox(-4.5F, -4.5F, -7, 9, 9, 15, new CubeDeformation(0.25F)), PartPose.offset(0, 0, 0));

        PartDefinition tailWool = bodyWool.addOrReplaceChild("tail_wool", CubeListBuilder.create().texOffs(52, 9).addBox(-1, -1.5F, 0, 2, 5, 2, new CubeDeformation(0.2F)), PartPose.offset(0, -2.3F, 8.3F));

        // Baby
        PartDefinition bodyBaby = body.addOrReplaceChild("body_baby", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.5F, -4, 5, 5, 8), PartPose.offset(0, 17.5F, 0));

        PartDefinition tailBaby = bodyBaby.addOrReplaceChild("tail_baby", CubeListBuilder.create().texOffs(0, 0).addBox(-1, -1, 0, 2, 2, 2), PartPose.offset(0, -1.5F, 4));

        PartDefinition headBaby = body.addOrReplaceChild("head_baby", CubeListBuilder.create().texOffs(0, 13).addBox(-1.5F, -1.0778F, -3.0015F, 3, 5, 3), PartPose.offsetAndRotation(0, -1.5F, -3, -0.6981F, 0, 0));

        PartDefinition rightEarBaby = headBaby.addOrReplaceChild("right_ear_baby", CubeListBuilder.create().texOffs(0, 4).mirror().addBox(-1.5F, -1, -0.5F, 2, 2, 1).mirror(false), PartPose.offsetAndRotation(-2, 0.0829F, -1.693F, 0.6981F, 0, 0));

        PartDefinition leftEarBaby = headBaby.addOrReplaceChild("left_ear_baby", CubeListBuilder.create().texOffs(0, 4).addBox(-0.75F, -1, -0.5F, 2, 2, 1), PartPose.offsetAndRotation(2, 0.0829F, -1.693F, 0.6981F, 0, 0));

        PartDefinition rightFrontLegBaby = bodyBaby.addOrReplaceChild("right_front_leg_baby", CubeListBuilder.create().texOffs(14, 13).mirror().addBox(-1, 0, -1, 2, 4, 2).mirror(false), PartPose.offset(-1.5F, 2.5F, -3));

        PartDefinition leftFrontLegBaby = bodyBaby.addOrReplaceChild("left_front_leg_baby", CubeListBuilder.create().texOffs(14, 13).addBox(-1, 0, -1, 2, 4, 2), PartPose.offset(1.5F, 2.5F, -3));

        PartDefinition rightHindLegBaby = bodyBaby.addOrReplaceChild("right_hind_leg_baby", CubeListBuilder.create().texOffs(22, 13).mirror().addBox(-1, 0, -1, 2, 4, 2).mirror(false), PartPose.offset(-1.5F, 2.5F, 3));

        PartDefinition leftHindLegBaby = bodyBaby.addOrReplaceChild("left_hind_leg_baby", CubeListBuilder.create().texOffs(22, 13).addBox(-1, 0, -1, 2, 4, 2), PartPose.offset(1.5F, 2.5F, 3));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    public static void setupAnim(Sheep sheep, ModelPart root, float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
        ModelPart head = root.getChild("head");
        ModelPart body = root.getChild("body");
        ModelPart bodyAdult = body.getChild("body_adult");
        ModelPart bodyWool = bodyAdult.getChild("body_wool");
        ModelPart rightHindLeg = root.getChild("right_hind_leg");
        ModelPart leftHindLeg = root.getChild("left_hind_leg");
        ModelPart rightFrontLeg = root.getChild("right_front_leg");
        ModelPart leftFrontLeg = root.getChild("left_front_leg");
        ModelPart rightHindLegWool = rightHindLeg.getChild("right_hind_leg_wool");
        ModelPart leftHindLegWool = leftHindLeg.getChild("left_hind_leg_wool");
        ModelPart rightFrontLegWool = rightFrontLeg.getChild("right_front_leg_wool");
        ModelPart leftFrontLegWool = leftFrontLeg.getChild("left_front_leg_wool");
        ModelPart headBaby = body.getChild("head_baby");
        ModelPart bodyBaby = body.getChild("body_baby");
        ModelPart rightFrontLegBaby = bodyBaby.getChild("right_front_leg_baby");
        ModelPart leftFrontLegBaby = bodyBaby.getChild("left_front_leg_baby");
        ModelPart rightHindLegBaby = bodyBaby.getChild("right_hind_leg_baby");
        ModelPart leftHindLegBaby = bodyBaby.getChild("left_hind_leg_baby");
        boolean baby = sheep.isBaby();
        boolean sheared = sheep.isSheared();

        headBaby.xScale = 2;
        headBaby.yScale = 2;
        headBaby.zScale = 2;
        bodyBaby.xScale = 2;
        bodyBaby.yScale = 2;
        bodyBaby.zScale = 2;

        headBaby.xRot = head.xRot / 2;
        headBaby.yRot = head.yRot / 2;

        bodyAdult.visible = !baby;
        head.visible = !baby;
        leftHindLeg.visible = !baby;
        rightHindLeg.visible = !baby;
        leftFrontLeg.visible = !baby;
        rightFrontLeg.visible = !baby;
        leftHindLegWool.visible = !sheared;
        rightHindLegWool.visible = !sheared;
        leftFrontLegWool.visible = !sheared;
        rightFrontLegWool.visible = !sheared;
        bodyWool.visible = !sheared;
        bodyBaby.visible = baby;
        headBaby.visible = baby;

        rightHindLegBaby.xRot = rightHindLeg.xRot;
        leftHindLegBaby.xRot = leftHindLeg.xRot;
        rightFrontLegBaby.xRot = rightFrontLeg.xRot;
        leftFrontLegBaby.xRot = leftFrontLeg.xRot;
    }
}

