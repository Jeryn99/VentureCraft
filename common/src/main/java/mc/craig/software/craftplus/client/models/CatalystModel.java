package mc.craig.software.craftplus.client.models;

import mc.craig.software.craftplus.common.blockentity.CatalystBlockEntity;
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

public class CatalystModel extends HierarchicalModel implements AnimatableBEModel<CatalystBlockEntity> {



    public static final AnimationDefinition CORE_AGITATED = AnimationDefinition.Builder.withLength(1.4583333333333333f).looping().addAnimation("catalyst_core", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations.posVec(-0.25f, 0.25f, -0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.041666666666666664f, KeyframeAnimations.posVec(-0.25f, -0.25f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.08333333333333333f, KeyframeAnimations.posVec(0.25f, 0.25f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.125f, KeyframeAnimations.posVec(0.25f, -0.25f, -0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.16666666666666666f, KeyframeAnimations.posVec(-0.25f, 0f, -0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.20833333333333331f, KeyframeAnimations.posVec(-0.25f, 0.25f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.25f, KeyframeAnimations.posVec(0.25f, -0.25f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.29166666666666663f, KeyframeAnimations.posVec(0.25f, 0.25f, -0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.3333333333333333f, KeyframeAnimations.posVec(-0.25f, -0.25f, -0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.375f, KeyframeAnimations.posVec(-0.25f, 0f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.41666666666666663f, KeyframeAnimations.posVec(0.25f, 0.25f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.4583333333333333f, KeyframeAnimations.posVec(0.25f, -0.25f, -0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5f, KeyframeAnimations.posVec(-0.25f, 0.25f, -0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5416666666666666f, KeyframeAnimations.posVec(-0.25f, -0.25f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5833333333333334f, KeyframeAnimations.posVec(0.25f, 0f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.625f, KeyframeAnimations.posVec(0.25f, 0.25f, -0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.6666666666666666f, KeyframeAnimations.posVec(-0.25f, -0.25f, -0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.7083333333333333f, KeyframeAnimations.posVec(-0.25f, 0.25f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.75f, KeyframeAnimations.posVec(0.25f, -0.25f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.7916666666666666f, KeyframeAnimations.posVec(0.25f, 0.25f, -0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.8333333333333334f, KeyframeAnimations.posVec(-0.25f, -0.25f, -0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.875f, KeyframeAnimations.posVec(-0.25f, 0.25f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.9166666666666667f, KeyframeAnimations.posVec(0.25f, -0.25f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.9583333333333334f, KeyframeAnimations.posVec(0.25f, 0.25f, -0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1f, KeyframeAnimations.posVec(-0.25f, -0.25f, -0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.0416666666666667f, KeyframeAnimations.posVec(-0.25f, 0.25f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.0833333333333333f, KeyframeAnimations.posVec(0.25f, -0.25f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.125f, KeyframeAnimations.posVec(0.25f, 0.25f, -0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.1666666666666667f, KeyframeAnimations.posVec(-0.25f, -0.25f, -0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.2083333333333335f, KeyframeAnimations.posVec(-0.25f, 0.25f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25f, KeyframeAnimations.posVec(0.25f, -0.25f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.2916666666666667f, KeyframeAnimations.posVec(0.25f, 0.25f, -0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.3333333333333333f, KeyframeAnimations.posVec(-0.25f, -0.25f, -0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.375f, KeyframeAnimations.posVec(-0.25f, 0.25f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.4166666666666665f, KeyframeAnimations.posVec(0.25f, -0.25f, 0.25f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.4583333333333333f, KeyframeAnimations.posVec(0.25f, 0f, -0.25f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("core_mid", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.4583333333333333f, KeyframeAnimations.degreeVec(-360f, -360f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("core_outer", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.4583333333333333f, KeyframeAnimations.degreeVec(0f, 360f, 0f), AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition CORE_CALM = AnimationDefinition.Builder.withLength(5f).looping().addAnimation("catalyst_core", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations.posVec(0f, -2f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2.5f, KeyframeAnimations.posVec(0f, 2f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(5f, KeyframeAnimations.posVec(0f, -2f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("core_mid", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(5f, KeyframeAnimations.degreeVec(0f, -360f, 360f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("core_outer", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(5f, KeyframeAnimations.degreeVec(0f, 360f, 0f), AnimationChannel.Interpolations.CATMULLROM))).build();
    private final ModelPart catalyst_casing;
    private final ModelPart root;

    public CatalystModel(ModelPart root) {
        this.root = root;
        this.catalyst_casing = root.getChild("catalyst_casing");
    }

    @Override
    public ModelPart root() {
        return root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition catalyst_casing = partdefinition.addOrReplaceChild("catalyst_casing", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -32.0F, -8.0F, 16.0F, 5.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(48, 42).addBox(-5.0F, -27.0F, -5.0F, 10.0F, 22.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 43).addBox(-6.0F, -25.0F, -6.0F, 12.0F, 18.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 21).addBox(-8.0F, -5.0F, -8.0F, 16.0F, 5.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition catalyst_core = catalyst_casing.addOrReplaceChild("catalyst_core", CubeListBuilder.create(), PartPose.offset(0.0F, -16.0F, 0.0F));

        PartDefinition core_outer = catalyst_core.addOrReplaceChild("core_outer", CubeListBuilder.create().texOffs(48, 21).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition core_mid = catalyst_core.addOrReplaceChild("core_mid", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition core_centre = catalyst_core.addOrReplaceChild("core_centre", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -17.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
    }

    @Override
    public void animate(CatalystBlockEntity blockentity) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        boolean close = Minecraft.getInstance().player.distanceToSqr(blockentity.getBlockPos().getX(), blockentity.getBlockPos().getY(), blockentity.getBlockPos().getZ()) < 5;
        animate(blockentity.AGITATED, close ? CORE_AGITATED : CORE_CALM, Minecraft.getInstance().player.tickCount);
    }
}
