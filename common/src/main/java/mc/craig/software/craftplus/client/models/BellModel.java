package mc.craig.software.craftplus.client.models;

import mc.craig.software.craftplus.common.blockentity.BigBellBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class BellModel extends HierarchicalModel implements AnimatableBEModel<BigBellBlockEntity> {

    public static final AnimationDefinition SINGLE_SWING = AnimationDefinition.Builder.withLength(5.25f).addAnimation("rotation_pivot", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-185f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.6666666666666667f, KeyframeAnimations.degreeVec(155f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(3.0416666666666665f, KeyframeAnimations.degreeVec(-140.49f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(4f, KeyframeAnimations.degreeVec(-185f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(5.25f, KeyframeAnimations.degreeVec(-185f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("bell_clapper", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.4166666666666667f, KeyframeAnimations.degreeVec(-14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.75f, KeyframeAnimations.degreeVec(-14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.125f, KeyframeAnimations.degreeVec(-14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.6666666666666667f, KeyframeAnimations.degreeVec(14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2.0833333333333335f, KeyframeAnimations.degreeVec(14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2.375f, KeyframeAnimations.degreeVec(14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2.75f, KeyframeAnimations.degreeVec(14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(3.2083333333333335f, KeyframeAnimations.degreeVec(-14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).build();
    public static final AnimationDefinition RING_START = AnimationDefinition.Builder.withLength(1.625f).addAnimation("rotation_pivot", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-185f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.625f, KeyframeAnimations.degreeVec(155f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("bell_clapper", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(-14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.4166666666666667f, KeyframeAnimations.degreeVec(-14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.75f, KeyframeAnimations.degreeVec(-14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.125f, KeyframeAnimations.degreeVec(-14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.625f, KeyframeAnimations.degreeVec(14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).build();
    public static final AnimationDefinition RING_LOOP = AnimationDefinition.Builder.withLength(2.9583333333333335f).looping().addAnimation("rotation_pivot", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(155f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.4583333333333333f, KeyframeAnimations.degreeVec(-155f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2.9583333333333335f, KeyframeAnimations.degreeVec(155f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("bell_clapper", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.375f, KeyframeAnimations.degreeVec(14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.7083333333333334f, KeyframeAnimations.degreeVec(14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1f, KeyframeAnimations.degreeVec(14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.4583333333333333f, KeyframeAnimations.degreeVec(-14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.8333333333333333f, KeyframeAnimations.degreeVec(-14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2.1666666666666665f, KeyframeAnimations.degreeVec(-14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2.5f, KeyframeAnimations.degreeVec(-14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2.9583333333333335f, KeyframeAnimations.degreeVec(14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).build();
    public static final AnimationDefinition RING_STOP = AnimationDefinition.Builder.withLength(3.5833333333333335f).addAnimation("rotation_pivot", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(155f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.375f, KeyframeAnimations.degreeVec(-140.49f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2.3333333333333335f, KeyframeAnimations.degreeVec(-185f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(3.5833333333333335f, KeyframeAnimations.degreeVec(-185f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("bell_clapper", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.4166666666666667f, KeyframeAnimations.degreeVec(14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.7083333333333334f, KeyframeAnimations.degreeVec(14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.0833333333333333f, KeyframeAnimations.degreeVec(14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.5416666666666667f, KeyframeAnimations.degreeVec(-14f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).build();
    private final ModelPart bell;
    private final ModelPart root;

    public BellModel(ModelPart root) {
        this.root = root;
        this.bell = root.getChild("Bell");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Bell = partdefinition.addOrReplaceChild("Bell", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition rotation_pivot = Bell.addOrReplaceChild("rotation_pivot", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

        PartDefinition bell_clapper = rotation_pivot.addOrReplaceChild("bell_clapper", CubeListBuilder.create().texOffs(16, 27).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 13).addBox(-0.5F, 9.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, -3.5F, 0.0F));

        PartDefinition bell_body = rotation_pivot.addOrReplaceChild("bell_body", CubeListBuilder.create().texOffs(39, 29).addBox(-3.0F, -7.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.2F))
                .texOffs(20, 21).addBox(-3.0F, -7.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 27).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(14, 35).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 3.0F, 8.0F, new CubeDeformation(0.2F))
                .texOffs(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(14, 11).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(14, 11).addBox(-1.0F, -6.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

        PartDefinition Bar_r1 = bell_body.addOrReplaceChild("Bar_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.5F, -2.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-2.0F, -1.5F, 0.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bell_stock = rotation_pivot.addOrReplaceChild("bell_stock", CubeListBuilder.create().texOffs(24, 0).addBox(-6.0F, -4.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 15).addBox(4.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(41, 9).addBox(-6.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(41, 5).addBox(-8.0F, 2.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 11).addBox(4.0F, 2.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

        PartDefinition bell_frame = Bell.addOrReplaceChild("bell_frame", CubeListBuilder.create().texOffs(32, 4).addBox(-8.0F, -3.0F, 6.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(32, 4).addBox(6.0F, -3.0F, 6.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 11).addBox(6.5F, -2.0F, -6.0F, 1.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 11).addBox(-7.5F, -2.0F, -6.0F, 1.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(32, 4).addBox(-8.0F, -3.0F, -8.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(32, 4).addBox(6.0F, -3.0F, -8.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void animate(BigBellBlockEntity blockentity) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        if (blockentity.BELL_RING.isStarted()) {
            animate(blockentity.BELL_RING, RING_LOOP, Minecraft.getInstance().player.tickCount);
        } else {
            animate(blockentity.BELL_STOP, RING_STOP, Minecraft.getInstance().player.tickCount);
        }
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

    }
}
