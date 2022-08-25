package mc.craig.software.craftplus.client.animation;

import mc.craig.software.craftplus.client.PlayerAnimations;
import mc.craig.software.craftplus.common.capability.ModCapability;
import mc.craig.software.craftplus.util.GliderUtil;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class AnimationHandler {


    public static void setupAnimPre(HumanoidModel<?> humanoidModel, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callbackInfo) {

        ModCapability.get(livingEntity).ifPresent(iCap -> {
            // Falling Animation
            if (livingEntity instanceof Player) {
                if (iCap.isFalling()) {
                    resetPoseAll(humanoidModel);
                    AnimationUtil.animate(humanoidModel, iCap.getAnimation(ModCapability.AnimationStates.FALLING), PlayerAnimations.FALLING, ageInTicks, 1);
                    fixLayers(humanoidModel);
                    callbackInfo.cancel();
                }
            }
        });

    }

    public static void setupAnimPost(HumanoidModel<?> humanoidModel, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callbackInfo) {
        ModCapability.get(livingEntity).ifPresent(iCap -> {


            float horizontalDistance = (float) livingEntity.getDeltaMovement().horizontalDistance();
            if (horizontalDistance < 0.01F && !livingEntity.isCrouching()) {
                humanoidModel.leftArm.getAllParts().forEach(ModelPart::resetPose);
                humanoidModel.rightArm.getAllParts().forEach(ModelPart::resetPose);
                humanoidModel.body.getAllParts().forEach(ModelPart::resetPose);
                resetPose(humanoidModel.body, humanoidModel.leftArm, humanoidModel.rightArm);
                AnimationUtil.animate(humanoidModel, iCap.getAnimation(ModCapability.AnimationStates.BREATHING), PlayerAnimations.BREATHING, ageInTicks, 1);
                fixLayers(humanoidModel);
            }

            if (GliderUtil.isGlidingWithActiveGlider(livingEntity)) {
                resetPose(humanoidModel.body, humanoidModel.leftArm, humanoidModel.rightArm, humanoidModel.leftLeg, humanoidModel.rightLeg);
                AnimationUtil.animate(humanoidModel, iCap.getAnimation(ModCapability.AnimationStates.GLIDING), PlayerAnimations.GLIDING, ageInTicks, 1);
                fixLayers(humanoidModel);
            }

        });
    }


    public static void resetPose(ModelPart... modelParts) {
        for (ModelPart part : modelParts) {
            part.resetPose();
        }
    }

    private static void resetPoseAll(HumanoidModel<?> bipedModel) {
        bipedModel.head.getAllParts().forEach(ModelPart::resetPose);
        bipedModel.body.getAllParts().forEach(ModelPart::resetPose);
        bipedModel.leftArm.getAllParts().forEach(ModelPart::resetPose);
        bipedModel.rightArm.getAllParts().forEach(ModelPart::resetPose);
        bipedModel.leftLeg.getAllParts().forEach(ModelPart::resetPose);
        bipedModel.rightLeg.getAllParts().forEach(ModelPart::resetPose);
    }

    private static void fixLayers(HumanoidModel<?> bipedModel) {
        if (bipedModel instanceof PlayerModel<?> playerModel) {
            playerModel.jacket.copyFrom(bipedModel.body);
            playerModel.leftPants.copyFrom(bipedModel.leftLeg);
            playerModel.rightPants.copyFrom(bipedModel.rightLeg);
            playerModel.leftSleeve.copyFrom(bipedModel.leftArm);
            playerModel.rightSleeve.copyFrom(bipedModel.rightArm);
            playerModel.hat.copyFrom(bipedModel.head);
        }
    }
}
