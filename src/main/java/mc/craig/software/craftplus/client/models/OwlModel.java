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


    public static final AnimationDefinition FLYING = AnimationDefinition.Builder.withLength(0.25f).looping().addAnimation("LWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, -107.5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, 0f, -40f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, -107.5f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("RWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 107.5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, 0f, 40f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f,  107.5f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("Head", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations.posVec(0f, 0.33f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.08333333333333333f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.20833333333333334f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0.33f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.125f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).build();
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart root;

    public OwlModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("Head");
        this.body = root.getChild("Body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(18, 7).addBox(-3.0F, -3.0F, -3.5833F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.2F))
                .texOffs(0, 13).addBox(-3.0F, -3.0F, -3.5833F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(-0.5F, 0.0F, -4.5833F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, 0.5833F));

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(18, 18).addBox(-3.0F, -9.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Tail_r1 = Body.addOrReplaceChild("Tail_r1", CubeListBuilder.create().texOffs(18, 0).addBox(-3.0F, 8.6F, 2.15F, 6.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-3.0F, 1.6F, -3.85F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition LWing = Body.addOrReplaceChild("LWing", CubeListBuilder.create(), PartPose.offset(3.5F, -7.25F, 0.0F));

        PartDefinition LWing_r1 = LWing.addOrReplaceChild("LWing_r1", CubeListBuilder.create().texOffs(0, 24).addBox(-0.5F, -0.124F, -2.4745F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition RWing = Body.addOrReplaceChild("RWing", CubeListBuilder.create(), PartPose.offset(-3.5F, -7.25F, 0.0F));

        PartDefinition RWing_r1 = RWing.addOrReplaceChild("RWing_r1", CubeListBuilder.create().texOffs(12, 28).addBox(-0.5F, -0.124F, -2.4745F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition LFoot = Body.addOrReplaceChild("LFoot", CubeListBuilder.create().texOffs(0, 2).addBox(1.0F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 1).addBox(0.5F, 1.5F, -1.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, -0.5F));

        PartDefinition RFoot = Body.addOrReplaceChild("RFoot", CubeListBuilder.create().texOffs(0, 13).addBox(-2.0F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-2.5F, 1.5F, -1.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, -0.5F));

        return LayerDefinition.create(meshdefinition, 48, 48);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch) {

        this.root().getAllParts().forEach(ModelPart::resetPose);

        if (entity.isFlying()) {
            this.animate(entity.flyingAnimationState, FLYING, ageInTicks);
        }

        this.head.yRot = netheadYaw * ((float)Math.PI / 180F);


    }

    @Override
    public ModelPart root() {
        return root;
    }

    public void renderOnShoulder(PoseStack p_103224_, VertexConsumer p_103225_, int p_103226_, int p_103227_, HumanoidModel humanoidModel) {
        this.head.yRot = humanoidModel.getHead().yRot;
        this.root.render(p_103224_, p_103225_, p_103226_, p_103227_);
    }
}