package mc.craig.software.craftplus.client.models;

import mc.craig.software.craftplus.common.blockentity.LockedLootChestBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.animation.definitions.WardenAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class ChestModel extends HierarchicalModel implements AnimatableBEModel<LockedLootChestBlockEntity> {

    private final ModelPart bone;
    private final ModelPart bb_main;
    private final ModelPart root;


    public static final AnimationDefinition OPEN_LOCK_IN = AnimationDefinition.Builder.withLength(1.9583333333333333f).addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.375f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.875f, KeyframeAnimations.degreeVec(90f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("bone2", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.375f, KeyframeAnimations.degreeVec(-2.7800000000000002f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.625f, KeyframeAnimations.degreeVec(-25f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.7916666666666666f, KeyframeAnimations.degreeVec(-82.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.9583333333333334f, KeyframeAnimations.degreeVec(-92.44f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.125f, KeyframeAnimations.degreeVec(-85.73f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.2916666666666667f, KeyframeAnimations.degreeVec(-89.94f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.4583333333333333f, KeyframeAnimations.degreeVec(-85.73f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.625f, KeyframeAnimations.degreeVec(-90f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.8333333333333333f, KeyframeAnimations.degreeVec(-90f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.041666666666666664f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.125f, KeyframeAnimations.posVec(0f, -1f, 0f), AnimationChannel.Interpolations.CATMULLROM))).build();
    public static final AnimationDefinition OPEN_LOCK_OFF2 = AnimationDefinition.Builder.withLength(1.9583333333333333f).addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.375f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.875f, KeyframeAnimations.degreeVec(90f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("bone2", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.375f, KeyframeAnimations.degreeVec(-2.7800000000000002f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.625f, KeyframeAnimations.degreeVec(-25f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.7916666666666666f, KeyframeAnimations.degreeVec(-82.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.9583333333333334f, KeyframeAnimations.degreeVec(-92.44f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.125f, KeyframeAnimations.degreeVec(-85.73f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.2916666666666667f, KeyframeAnimations.degreeVec(-89.94f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.4583333333333333f, KeyframeAnimations.degreeVec(-85.73f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.625f, KeyframeAnimations.degreeVec(-90f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.8333333333333333f, KeyframeAnimations.degreeVec(-90f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.041666666666666664f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.125f, KeyframeAnimations.posVec(0f, -1f, 0f), AnimationChannel.Interpolations.CATMULLROM))).build();
    public static final AnimationDefinition OPEN_LOCK_DROPS = AnimationDefinition.Builder.withLength(1.9583333333333333f).addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.375f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.875f, KeyframeAnimations.degreeVec(90f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).addAnimation("bone2", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.375f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.38333333333333336f, KeyframeAnimations.scaleVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.041666666666666664f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.125f, KeyframeAnimations.posVec(0f, -1f, 0f), AnimationChannel.Interpolations.CATMULLROM))).build();
    public static final AnimationDefinition DENY = AnimationDefinition.Builder.withLength(0.7083333333333334f).addAnimation("bone2", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.16666666666666666f, KeyframeAnimations.degreeVec(0f, 0f, -12.5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.3333333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 12.5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.625f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.7083333333333334f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).build();

    public ChestModel(ModelPart root) {
        this.root = root;
        this.bone = root.getChild("bone");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition getModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, 14.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-7.0F, -5.0F, 0.0F, 14.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -7.0F));

        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(2, 6).addBox(-2.0F, -0.5F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 14.5F));

        PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 27).addBox(-2.0F, -7.0F, 7.5F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.5F, -7.5F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 19).addBox(-7.0F, -10.0F, -7.0F, 14.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

    }

    @Override
    public void animate(LockedLootChestBlockEntity blockentity) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        if(blockentity.OPEN.isStarted()) {
            animate(blockentity.OPEN, OPEN_LOCK_DROPS, Minecraft.getInstance().player.tickCount);
        }

        if(blockentity.DENY.isStarted()) {
            animate(blockentity.DENY, DENY, Minecraft.getInstance().player.tickCount);
        }
    }
}
