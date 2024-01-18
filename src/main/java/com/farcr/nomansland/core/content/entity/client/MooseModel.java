package com.farcr.nomansland.core.content.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class MooseModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart moose;
    private final ModelPart head;
    private final ModelPart rightHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart leftFrontLeg;

    public MooseModel(ModelPart root) {
        this.moose = root.getChild("moose");
        this.head = moose.getChild("head");
        this.rightHindLeg = moose.getChild("rightHindLeg");
        this.rightFrontLeg = moose.getChild("rightHindLeg");
        this.leftHindLeg = moose.getChild("rightHindLeg");
        this.leftFrontLeg = moose.getChild("leftFrontLeg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition moose = partdefinition.addOrReplaceChild("moose", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition torso = moose.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -25.0F, -15.0F, 16.0F, 22.0F, 30.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightHindLeg = moose.addOrReplaceChild("rightHindLeg", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -3.0F, 10.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightFrontLeg = moose.addOrReplaceChild("rightFrontLeg", CubeListBuilder.create().texOffs(0, 5).addBox(-8.0F, -3.0F, -12.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftHindLeg = moose.addOrReplaceChild("leftHindLeg", CubeListBuilder.create().texOffs(8, 5).addBox(6.0F, -3.0F, 10.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftFrontLeg = moose.addOrReplaceChild("leftFrontLeg", CubeListBuilder.create().texOffs(8, 0).addBox(6.0F, -3.0F, -12.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = moose.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 10).addBox(-1.0F, -32.0F, -17.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(62, 0).addBox(-8.0F, -40.0F, -15.0F, 16.0F, 15.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 52).addBox(-24.0F, -46.0F, -15.0F, 48.0F, 15.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.rightHindLeg.xRot = Mth.cos(limbSwingAmount * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftHindLeg.xRot = Mth.cos(limbSwingAmount * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.rightFrontLeg.xRot = Mth.cos(limbSwingAmount * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leftFrontLeg.xRot = Mth.cos(limbSwingAmount * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        moose.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}