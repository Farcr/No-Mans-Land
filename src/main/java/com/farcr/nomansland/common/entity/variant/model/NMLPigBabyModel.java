package com.farcr.nomansland.common.entity.variant.model;

// Made with Blockbench 4.11.0
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.farcr.nomansland.NoMansLand;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class NMLPigBabyModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "pig_baby"), "main");
    private final ModelPart body;
    private final ModelPart snout;
    private final ModelPart earRight;
    private final ModelPart earLeft;
    private final ModelPart tail;
    private final ModelPart legFrontRight;
    private final ModelPart legFrontLeft;
    private final ModelPart legBackRight;
    private final ModelPart legBackLeft;

    public NMLPigBabyModel(ModelPart root) {
        this.body = root.getChild("body");
        this.snout = root.getChild("snout");
        this.earRight = root.getChild("earRight");
        this.earLeft = root.getChild("earLeft");
        this.tail = root.getChild("tail");
        this.legFrontRight = root.getChild("legFrontRight");
        this.legFrontLeft = root.getChild("legFrontLeft");
        this.legBackRight = root.getChild("legBackRight");
        this.legBackLeft = root.getChild("legBackLeft");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.5F, -4.0F, 5.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.5F, 0.0F));

        PartDefinition snout = body.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(18, 0).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, -4.0F));

        PartDefinition earRight = body.addOrReplaceChild("earRight", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, 0.0F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.5F, -2.5F, -2.5F));

        PartDefinition earLeft = body.addOrReplaceChild("earLeft", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -2.5F, -2.5F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(18, 0).addBox(0.0F, -2.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

        PartDefinition legFrontRight = body.addOrReplaceChild("legFrontRight", CubeListBuilder.create().texOffs(0, 13).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.5F, 2.5F, -2.0F));

        PartDefinition legFrontLeft = body.addOrReplaceChild("legFrontLeft", CubeListBuilder.create().texOffs(0, 13).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 2.5F, -2.0F));

        PartDefinition legBackRight = body.addOrReplaceChild("legBackRight", CubeListBuilder.create().texOffs(8, 13).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.5F, 2.5F, 3.0F));

        PartDefinition legBackLeft = body.addOrReplaceChild("legBackLeft", CubeListBuilder.create().texOffs(8, 13).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 2.5F, 3.0F));

        return LayerDefinition.create(meshdefinition, 32, 16);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
