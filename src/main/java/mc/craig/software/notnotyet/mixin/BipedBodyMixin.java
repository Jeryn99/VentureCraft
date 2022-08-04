package mc.craig.software.notnotyet.mixin;

import mc.craig.software.notnotyet.client.AnimationHandler;
import mc.craig.software.notnotyet.client.Animations;
import mc.craig.software.notnotyet.handlers.CommonEvents;
import mc.craig.software.notnotyet.util.AnimationUtil;
import mc.craig.software.notnotyet.util.GliderUtil;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public class BipedBodyMixin {

/*    @Inject(at = @At("HEAD"), cancellable = true, method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V")
    private void setupAnimHead(LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callbackInfo) {
        HumanoidModel<LivingEntity> bipedModel = (HumanoidModel) (Object) this;
       if(GliderUtil.isGlidingWithActiveGlider(livingEntity)){
           AnimationUtil.animate(bipedModel, CommonEvents.glideAnimation, Animations.GLIDING, ageInTicks, 1);

           if(bipedModel instanceof PlayerModel<LivingEntity> playerModel){
               playerModel.jacket.copyFrom(bipedModel.body);

               playerModel.leftPants.copyFrom(bipedModel.leftLeg);
               playerModel.rightPants.copyFrom(bipedModel.rightLeg);

               playerModel.leftSleeve.copyFrom(bipedModel.leftArm);
               playerModel.rightSleeve.copyFrom(bipedModel.rightArm);

               playerModel.hat.copyFrom(bipedModel.head);

           }

           callbackInfo.cancel();
       }
    }*/

    @Inject(at = @At("TAIL"), method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V")
    private void setupAnimTail(LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callbackInfo) {
        HumanoidModel<LivingEntity> bipedModel = (HumanoidModel) (Object) this;
        AnimationHandler.setupAnim(livingEntity, bipedModel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        //AnimationUtil.animate(bipedModel, CommonEvents.glideAnimation, Animations.GLIDING, ageInTicks, 1);
        bipedModel.hat.copyFrom(bipedModel.head);
    }


}