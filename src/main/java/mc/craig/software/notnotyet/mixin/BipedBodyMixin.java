package mc.craig.software.notnotyet.mixin;

import mc.craig.software.notnotyet.client.Animations;
import mc.craig.software.notnotyet.common.capability.ModCapability;
import mc.craig.software.notnotyet.util.AnimationUtil;
import mc.craig.software.notnotyet.util.GliderUtil;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public class BipedBodyMixin {

    @Inject(at = @At("HEAD"), cancellable = true, method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V")
    private void setupAnimHead(LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callbackInfo) {
        HumanoidModel<LivingEntity> bipedModel = (HumanoidModel) (Object) this;

        ModCapability.get(livingEntity).ifPresent(iCap -> {
            // Gliding Animation
            if (GliderUtil.isGlidingWithActiveGlider(livingEntity)) {
                AnimationUtil.animate(bipedModel, iCap.getAnimation(ModCapability.AnimationStates.GLIDING), Animations.GLIDING, ageInTicks, 1);
                fixLayers(bipedModel);
                callbackInfo.cancel();
            }

            // Falling Animation
            if (livingEntity instanceof Player player) {
                AnimationUtil.animate(bipedModel, iCap.getAnimation(ModCapability.AnimationStates.FALLING), Animations.FALLING, ageInTicks, 1);
                fixLayers(bipedModel);
                callbackInfo.cancel();
            }
        });
    }

    private void fixLayers(HumanoidModel<LivingEntity> bipedModel) {
        if (bipedModel instanceof PlayerModel<LivingEntity> playerModel) {
            playerModel.jacket.copyFrom(bipedModel.body);
            playerModel.leftPants.copyFrom(bipedModel.leftLeg);
            playerModel.rightPants.copyFrom(bipedModel.rightLeg);
            playerModel.leftSleeve.copyFrom(bipedModel.leftArm);
            playerModel.rightSleeve.copyFrom(bipedModel.rightArm);
            playerModel.hat.copyFrom(bipedModel.head);
        }
    }

/*    @Inject(at = @At("TAIL"), method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V")
    private void setupAnimTail(LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callbackInfo) {
        HumanoidModel<LivingEntity> bipedModel = (HumanoidModel) (Object) this;
        AnimationHandler.setupAnim(livingEntity, bipedModel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        //AnimationUtil.animate(bipedModel, CommonEvents.glideAnimation, Animations.GLIDING, ageInTicks, 1);
        bipedModel.hat.copyFrom(bipedModel.head);
    }*/


}