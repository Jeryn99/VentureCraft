package mc.craig.software.craftplus.client.models;// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mc.craig.software.craftplus.common.entities.OwlEntity;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class OwlModel<T extends OwlEntity> extends HierarchicalModel<T> {


    public static final AnimationDefinition FLYING = AnimationDefinition.Builder.withLength(2.4583333333333335f).addAnimation("LRoot", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.041666666666666664f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.2916666666666667f, KeyframeAnimations.degreeVec(-90f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.9166666666666666f, KeyframeAnimations.degreeVec(-90f, 12.5f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("LRFeathers", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.2916666666666667f, KeyframeAnimations.degreeVec(0f, -10f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5416666666666666f, KeyframeAnimations.degreeVec(0f, -10f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("LExtention", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.16666666666666666f, KeyframeAnimations.degreeVec(-180f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.125f, KeyframeAnimations.degreeVec(67.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.6666666666666667f, KeyframeAnimations.degreeVec(67.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("Head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.13305333333333338f, KeyframeAnimations.degreeVec(-60f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5416666666666666f, KeyframeAnimations.degreeVec(-60f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("LEFeathers", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.041666666666666664f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.08333333333333333f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("LEFeathers", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.2916666666666667f, KeyframeAnimations.degreeVec(0f, -170f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5416666666666666f, KeyframeAnimations.degreeVec(0f, -170f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("LShoulder", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.2916666666666667f, KeyframeAnimations.degreeVec(0f, -107.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5416666666666666f, KeyframeAnimations.degreeVec(0f, -107.5f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("RShoulder", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.2916666666666667f, KeyframeAnimations.degreeVec(0f, 107.5f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5416666666666666f, KeyframeAnimations.degreeVec(0f, 107.5f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("RRoot", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.041666666666666664f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.2916666666666667f, KeyframeAnimations.degreeVec(-90f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.9166666666666666f, KeyframeAnimations.degreeVec(-90f, -12.5f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("RRFeathers", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.2916666666666667f, KeyframeAnimations.degreeVec(0f, 10f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5416666666666666f, KeyframeAnimations.degreeVec(0f, 10f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("RExtention", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.16666666666666666f, KeyframeAnimations.degreeVec(-180f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("REFeathers", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.125f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.16666666666666666f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("REFeathers", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.2916666666666667f, KeyframeAnimations.degreeVec(0f, 170f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5416666666666666f, KeyframeAnimations.degreeVec(0f, 170f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("LSFeathers", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.2916666666666667f, KeyframeAnimations.degreeVec(0f, 0f, 10f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5416666666666666f, KeyframeAnimations.degreeVec(0f, 0f, 10f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("RSFeathers", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.2916666666666667f, KeyframeAnimations.degreeVec(0f, 0f, -10f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5416666666666666f, KeyframeAnimations.degreeVec(0f, 0f, -10f), AnimationChannel.Interpolations.CATMULLROM))).build();
    private final ModelPart Body;
    private final ModelPart root;
    private final ModelPart Head;

    public OwlModel(ModelPart root) {
        this.root = root;
        this.Body = root.getChild("Body");
        this.Head = Body.getChild("Head");
    }



    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.1F))
                .texOffs(9, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.21F))
                .texOffs(18, 0).addBox(-1.5F, 3.0F, 1.5F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 21.0F, -0.5F, 0.3491F, 0.0F, 0.0F));

        PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 9).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F))
                .texOffs(9, 0).addBox(-0.5F, -0.6F, -1.95F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -4.05F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition LWing = Body.addOrReplaceChild("LWing", CubeListBuilder.create(), PartPose.offset(1.65F, -2.5F, 1.25F));

        PartDefinition LShoulder = LWing.addOrReplaceChild("LShoulder", CubeListBuilder.create().texOffs(12, 1).addBox(0.0F, -0.5F, -2.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.025F, 0.0F, -0.125F));

        PartDefinition LSFeathers = LShoulder.addOrReplaceChild("LSFeathers", CubeListBuilder.create().texOffs(6, 12).addBox(0.005F, 0.0F, -1.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -1.0F));

        PartDefinition LRoot = LShoulder.addOrReplaceChild("LRoot", CubeListBuilder.create().texOffs(2, 18).addBox(0.0F, 0.0F, 0.0F, 0.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, -2.5F));

        PartDefinition LRFeathers = LRoot.addOrReplaceChild("LRFeathers", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 2.5F, 1.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition Feathers_r1 = LRFeathers.addOrReplaceChild("Feathers_r1", CubeListBuilder.create().texOffs(12, 15).addBox(0.005F, -2.5F, -2.0F, 0.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition LExtention = LRoot.addOrReplaceChild("LExtention", CubeListBuilder.create().texOffs(4, 18).addBox(0.0F, -5.0F, -0.5F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.5F));

        PartDefinition LEFeathers = LExtention.addOrReplaceChild("LEFeathers", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -3.5F, 0.5F, 0.0F, 1.5708F, 0.0F));

        PartDefinition Feathers_r2 = LEFeathers.addOrReplaceChild("Feathers_r2", CubeListBuilder.create().texOffs(16, 14).addBox(0.0F, -1.5F, -2.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition RWing = Body.addOrReplaceChild("RWing", CubeListBuilder.create(), PartPose.offset(-1.65F, -2.5F, 1.25F));

        PartDefinition RShoulder = RWing.addOrReplaceChild("RShoulder", CubeListBuilder.create().texOffs(12, 0).addBox(-0.005F, -0.5F, -2.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.025F, 0.0F, -0.125F));

        PartDefinition RSFeathers = RShoulder.addOrReplaceChild("RSFeathers", CubeListBuilder.create().texOffs(0, 12).addBox(-0.005F, 0.0F, -1.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -1.0F));

        PartDefinition RRoot = RShoulder.addOrReplaceChild("RRoot", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, 0.0F, 0.0F, 0.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, -2.5F));

        PartDefinition RRFeathers = RRoot.addOrReplaceChild("RRFeathers", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 2.5F, 1.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition Feathers_r3 = RRFeathers.addOrReplaceChild("Feathers_r3", CubeListBuilder.create().texOffs(12, 9).addBox(0.0F, -2.5F, -2.0F, 0.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition RExtention = RRoot.addOrReplaceChild("RExtention", CubeListBuilder.create().texOffs(18, 3).addBox(0.0F, -5.0F, -0.5F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.5F));

        PartDefinition REFeathers = RExtention.addOrReplaceChild("REFeathers", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -3.5F, 0.5F, 0.0F, -1.5708F, 0.0F));

        PartDefinition Feathers_r4 = REFeathers.addOrReplaceChild("Feathers_r4", CubeListBuilder.create().texOffs(16, 9).addBox(0.0F, -1.5F, -2.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch) {

        this.root().getAllParts().forEach(ModelPart::resetPose);

        if (entity.isFlying()) {
            this.animate(entity.flyingAnimationState, FLYING, ageInTicks);
        }
        this.Head.yRot = netheadYaw * ((float) Math.PI / 180F);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    public void renderOnShoulder(PoseStack p_103224_, VertexConsumer p_103225_, int p_103226_, int p_103227_, HumanoidModel humanoidModel) {
        this.Head.yRot = humanoidModel.getHead().yRot;
        this.root.render(p_103224_, p_103225_, p_103226_, p_103227_);
    }
}