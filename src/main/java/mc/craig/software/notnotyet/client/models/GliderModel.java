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

        PartDefinition glider = partdefinition.addOrReplaceChild("glider", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition cube_r1 = glider.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(40, 0).addBox(-0.9F, 0.0F, 0.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 4).addBox(-0.9F, 0.0F, 14.9F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.1F, -4.0F, -8.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition cube_r2 = glider.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 26).addBox(-4.25F, -1.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-6.0F, -3.0F, -8.0F, 0.0F, 0.1309F, -0.7854F));

        PartDefinition cube_r3 = glider.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(40, 2).addBox(-10.3F, 0.0F, -1.1F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 6).addBox(-10.3F, 0.0F, -16.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.1F, -4.0F, 8.0F, 0.0F, 0.0F, -0.1309F));

        PartDefinition cube_r4 = glider.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 28).addBox(-4.25F, -1.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-6.0F, -3.0F, 8.0F, 0.0F, -0.1309F, -0.7854F));

        PartDefinition cube_r5 = glider.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 30).addBox(0.25F, -1.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(6.0F, -3.0F, 8.0F, 0.0F, 0.1309F, 0.7854F));

        PartDefinition cube_r6 = glider.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 32).addBox(0.25F, -1.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(6.0F, -3.0F, -8.0F, 0.0F, -0.1309F, 0.7854F));

        PartDefinition cube_r7 = glider.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, 0.0F, -0.25F, 12.0F, 0.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-12.8F, 10.8F, 4.5F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 32).addBox(-12.8F, -0.5F, -0.75F, 1.0F, 1.0F, 17.0F, new CubeDeformation(0.0F))
                .texOffs(19, 37).addBox(-11.8F, -0.5F, 15.75F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(19, 39).addBox(-11.8F, -0.5F, -0.75F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -8.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r8 = glider.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(9, 0).addBox(-0.0112F, -0.0047F, -0.0308F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.3F, 4.25F, -7.0F, 0.8029F, 0.0F, -0.3927F));

        PartDefinition cube_r9 = glider.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(9, 8).addBox(-0.0112F, -0.0047F, -1.7192F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.3F, 4.25F, 7.0F, -0.8029F, 0.0F, -0.3927F));

        PartDefinition cube_r10 = glider.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(4, 16).addBox(-4.5838F, -7.55F, -0.2092F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.65F, 4.25F, -7.0F, 0.1396F, 0.0F, 0.3927F));

        PartDefinition cube_r11 = glider.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 8).addBox(-1.4888F, -0.0047F, -0.0308F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.3F, 4.25F, -7.0F, 0.8029F, 0.0F, 0.3927F));

        PartDefinition cube_r12 = glider.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 0).addBox(-1.4888F, -0.0047F, -1.7192F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.3F, 4.25F, 7.0F, -0.8029F, 0.0F, 0.3927F));

        PartDefinition cube_r13 = glider.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 16).addBox(-4.5838F, -7.55F, -1.5408F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.65F, 4.25F, 7.0F, -0.1396F, 0.0F, 0.3927F));

        PartDefinition cube_r14 = glider.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(8, 16).addBox(3.0838F, -7.55F, -0.2092F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.65F, 4.25F, -7.0F, 0.1396F, 0.0F, -0.3927F));

        PartDefinition cube_r15 = glider.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(12, 16).addBox(3.0838F, -7.55F, -1.5408F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.65F, 4.25F, 7.0F, -0.1396F, 0.0F, -0.3927F));

        PartDefinition cube_r16 = glider.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 8).addBox(11.2F, 10.75F, 4.5F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(0.0F, 0.0F, -0.25F, 12.0F, 0.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(19, 33).addBox(-0.2F, -0.5F, 15.75F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(19, 35).addBox(-0.2F, -0.5F, -0.75F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(23, 15).addBox(11.8F, -0.5F, -0.75F, 1.0F, 1.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -8.0F, 0.0F, 0.0F, 0.3927F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        glider.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}