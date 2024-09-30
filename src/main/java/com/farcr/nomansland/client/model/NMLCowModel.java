package com.farcr.nomansland.client.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Cow;

public class NMLCowModel {
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.offset(0, 0, 0));

        PartDefinition bodyAdult = body.addOrReplaceChild("body_adult", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -7.0F, -11.5F, 14.0F, 14.0F, 23.0F), PartPose.offset(0, 5, 0));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(67, 0).addBox(-4.0F, -6.0F, -6.0F, 8.0F, 11.0F, 8.0F)
                .texOffs(74, 19).addBox(-4.0F, -6.0F, -6.0F, 8.0F, 11.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0, -2.0F, -11.7F));

        PartDefinition leftEar = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(15, 16).mirror().addBox(0, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F).mirror(false), PartPose.offset(4.0F, -3.0F, -3.5F));

        PartDefinition rightEar = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F), PartPose.offset(-4.0F, -3.0F, -3.5F));

        PartDefinition leftHorn = head.addOrReplaceChild("left_horn", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(0, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F).mirror(false)
                .texOffs(8, 16).addBox(3.0F, -2.0F, -1.0F, 1.0F, 1.0F, 2.0F), PartPose.offset(4.0F, -5.0F, -3.0F));

        PartDefinition rightHorn = head.addOrReplaceChild("right_horn", CubeListBuilder.create().texOffs(0, 19).addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F)
                .texOffs(8, 16).mirror().addBox(-4.0F, -2.0F, -1.0F, 1.0F, 1.0F, 2.0F).mirror(false), PartPose.offset(-4.0F, -5.0F, -3.0F));

        PartDefinition leftBigHorn = head.addOrReplaceChild("left_big_horn", CubeListBuilder.create().texOffs(51, 16).mirror().addBox(0, -1.5F, -1.0F, 6.0F, 3.0F, 2.0F).mirror(false)
                .texOffs(65, 19).mirror().addBox(4.0F, -3.5F, -1.0F, 2.0F, 2.0F, 2.0F).mirror(false), PartPose.offset(2.0F, -5.5F, -3.0F));

        PartDefinition rightBigHorn = head.addOrReplaceChild("right_big_horn", CubeListBuilder.create().texOffs(51, 16).addBox(-6.0F, -1.5F, -1.0F, 6.0F, 3.0F, 2.0F)
                .texOffs(65, 19).addBox(-6.0F, -3.5F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offset(-2.0F, -5.5F, -3.0F));

        PartDefinition tail = bodyAdult.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(3, 0).addBox(-1.5F, 0, 0, 3.0F, 0, 13.0F), PartPose.offsetAndRotation(0, -7.0F, 11.5F, -1.3963F, 0, 0));

        PartDefinition udders = bodyAdult.addOrReplaceChild("udders", CubeListBuilder.create().texOffs(91, 0).addBox(-3.0F, 0, -3.0F, 6.0F, 2.0F, 6.0F), PartPose.offset(0, 7.0F, 8.5F));

        PartDefinition leftFrontLeg = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-5.0F, 12.0F, -9.5F));

        PartDefinition rightFrontLeg = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, 0, -2.0F, 4.0F, 12.0F, 4.0F).mirror(false), PartPose.offset(5.0F, 12.0F, -9.5F));

        PartDefinition leftHindLeg = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(51, 0).addBox(-2.0F, 0, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-5.0F, 12.0F, 9.5F));

        PartDefinition rightHindLeg = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(51, 0).mirror().addBox(-2.0F, 0, -2.0F, 4.0F, 12.0F, 4.0F).mirror(false), PartPose.offset(5.0F, 12.0F, 9.5F));

        // Baby
        PartDefinition bodyBaby = body.addOrReplaceChild("body_baby", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3.0F, -5.0F, 7.0F, 6.0F, 10.0F), PartPose.offset(0.0F, 5.0F, 0.0F));

        PartDefinition rightFrontLegBaby = bodyBaby.addOrReplaceChild("right_front_leg_baby", CubeListBuilder.create().texOffs(20, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F).mirror(false), PartPose.offset(-2.5F, 3.0F, -4.0F));

        PartDefinition leftFrontLegBaby = bodyBaby.addOrReplaceChild("left_front_leg_baby", CubeListBuilder.create().texOffs(20, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F), PartPose.offset(2.5F, 3.0F, -4.0F));

        PartDefinition rightHindLegBaby = bodyBaby.addOrReplaceChild("right_hind_leg_baby", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F).mirror(false), PartPose.offset(-2.5F, 3.0F, 4.0F));

        PartDefinition leftHindLegBaby = bodyBaby.addOrReplaceChild("left_hind_leg_baby", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F), PartPose.offset(2.5F, 3.0F, 4.0F));

        PartDefinition headBaby = body.addOrReplaceChild("head_baby", CubeListBuilder.create().texOffs(0, 16).addBox(-2.5F, -3.0F, -3.0F, 5.0F, 6.0F, 5.0F)
                .texOffs(34, 0).addBox(-2.5F, -3.0F, -3.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -2.0F, -11));

        PartDefinition rightEarBaby = headBaby.addOrReplaceChild("right_ear_baby", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F).mirror(false), PartPose.offset(-2.5F, -1.0F, -1.5F));

        PartDefinition leftEarBaby = headBaby.addOrReplaceChild("left_ear_baby", CubeListBuilder.create().texOffs(0, 3).addBox(0.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F), PartPose.offset(2.5F, -1.0F, -1.5F));

        PartDefinition tailBaby = bodyBaby.addOrReplaceChild("tail_baby", CubeListBuilder.create().texOffs(20, 16).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 8.0F), PartPose.offsetAndRotation(0.0F, -3.0F, 5.0F, -1.2217F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    public static void setupAnim(Cow cow, ModelPart root, float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
        ModelPart head = root.getChild("head");
        ModelPart body = root.getChild("body");
        ModelPart bodyAdult = body.getChild("body_adult");
        ModelPart rightHindLeg = root.getChild("right_hind_leg");
        ModelPart leftHindLeg = root.getChild("left_hind_leg");
        ModelPart rightFrontLeg = root.getChild("right_front_leg");
        ModelPart leftFrontLeg = root.getChild("left_front_leg");
        ModelPart headBaby = body.getChild("head_baby");
        ModelPart bodyBaby = body.getChild("body_baby");
        ModelPart rightFrontLegBaby = bodyBaby.getChild("right_front_leg_baby");
        ModelPart leftFrontLegBaby = bodyBaby.getChild("left_front_leg_baby");
        ModelPart rightHindLegBaby = bodyBaby.getChild("right_hind_leg_baby");
        ModelPart leftHindLegBaby = bodyBaby.getChild("left_hind_leg_baby");
        boolean baby = cow.isBaby();

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
        bodyBaby.visible = baby;
        headBaby.visible = baby;

        rightHindLegBaby.xRot = rightHindLeg.xRot;
        leftHindLegBaby.xRot = leftHindLeg.xRot;
        rightFrontLegBaby.xRot = rightFrontLeg.xRot;
        leftFrontLegBaby.xRot = leftFrontLeg.xRot;
    }
}

