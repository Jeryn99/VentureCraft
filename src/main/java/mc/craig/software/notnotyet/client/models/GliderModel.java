package mc.craig.software.notnotyet.client.models;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class GliderModel<T extends Entity> extends EntityModel<T> {

    private final ModelPart glider;

    public GliderModel(ModelPart root) {
        this.glider = root.getChild("glider");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition glider = partdefinition.addOrReplaceChild("glider", CubeListBuilder.create().texOffs(48, 60).addBox(-1.0F, -2.1F, -8.0F, 2.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition LBrace_r1 = glider.addOrReplaceChild("LBrace_r1", CubeListBuilder.create().texOffs(24, 59).addBox(0.5F, -0.75F, -16.0F, 4.0F, 1.0F, 16.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(6.0F, -3.0F, 8.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition RBrace_r1 = glider.addOrReplaceChild("RBrace_r1", CubeListBuilder.create().texOffs(0, 58).addBox(-4.5F, -0.75F, -16.0F, 4.0F, 1.0F, 16.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(-6.0F, -3.0F, 8.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition LStrut_r1 = glider.addOrReplaceChild("LStrut_r1", CubeListBuilder.create().texOffs(42, 42).addBox(-1.25F, 6.9F, -0.25F, 12.0F, 1.0F, 16.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -9.0F, -7.75F, 0.0F, 0.0F, -0.2182F));

        PartDefinition RStrut_r1 = glider.addOrReplaceChild("RStrut_r1", CubeListBuilder.create().texOffs(42, 13).addBox(-10.75F, 6.9F, -0.25F, 12.0F, 1.0F, 16.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -9.0F, -7.75F, 0.0F, 0.0F, 0.2182F));

        PartDefinition LPole_r1 = glider.addOrReplaceChild("LPole_r1", CubeListBuilder.create().texOffs(0, 75).addBox(12.5F, -0.5F, -0.25F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(0.0F, 0.0F, -0.25F, 13.0F, 13.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -7.75F, 0.0F, 0.0F, 0.3927F));

        PartDefinition RPole_r1 = glider.addOrReplaceChild("RPole_r1", CubeListBuilder.create().texOffs(68, 59).addBox(-13.5F, -0.5F, -0.25F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-13.0F, 0.0F, -0.25F, 13.0F, 13.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -7.75F, 0.0F, 0.0F, -0.3927F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        glider.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}