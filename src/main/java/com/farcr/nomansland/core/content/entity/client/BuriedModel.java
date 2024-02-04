package com.farcr.nomansland.core.content.entity.client;// Made with Blockbench 4.9.3, Made by Farcr

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class BuriedModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart buried;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart rightArm;
    private final ModelPart leftArm;

    public BuriedModel(ModelPart root) {
        this.buried = root.getChild("buried");
        this.leftLeg = buried.getChild("Body").getChild("LeftLeg");
        this.rightLeg = buried.getChild("Body").getChild("RightLeg");
        this.rightArm = buried.getChild("Body").getChild("RightArm");
        this.leftArm = buried.getChild("Body").getChild("LeftArm");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition buried = partdefinition.addOrReplaceChild("buried", CubeListBuilder.create(), PartPose.offset(0.0F, 34.0F, -6.0F));

        PartDefinition Head = buried.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0436F, 0.0F, -0.1309F));

        PartDefinition Body = buried.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 1.4835F, 0.0F, 0.0F));

        PartDefinition LeftLeg = Body.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(-2, 2).mirror().addBox(-1.0F, 6.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 12.0F, 0.0F, -0.0444F, -0.0852F, -0.112F));

        PartDefinition RightLeg = Body.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(32, 16).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(2, 2).mirror().addBox(-1.0F, 5.0F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 13.0F, 1.0F, 0.0F, 0.0F, 0.2182F));

        PartDefinition RightArm = Body.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(2, 0).mirror().addBox(-1.0F, 5.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(40, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 1.0F, 0.0F, -2.7242F, 0.4157F, -0.1073F));

        PartDefinition LeftArm = Body.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(40, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(-2, 0).mirror().addBox(-1.0F, 5.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 1.0F, 0.0F, -2.7213F, -0.0735F, 0.4305F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        AnimationUtils.bobArms(this.rightArm, this.leftArm, pAgeInTicks);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        buried.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}