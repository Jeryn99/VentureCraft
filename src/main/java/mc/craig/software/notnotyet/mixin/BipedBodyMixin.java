package mc.craig.software.notnotyet.mixin;

import mc.craig.software.notnotyet.client.AnimationHandler;
import mc.craig.software.notnotyet.client.Animations;
import mc.craig.software.notnotyet.handlers.ClientEvents;
import mc.craig.software.notnotyet.handlers.CommonEvents;
import mc.craig.software.notnotyet.util.AnimationUtil;
import mc.craig.software.notnotyet.util.GliderUtil;
import net.minecraft.client.animation.definitions.WardenAnimation;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public class BipedBodyMixin {

    @Inject(at = @At("TAIL"), method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V")
    private void setupAnim(LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callbackInfo) {
        HumanoidModel<LivingEntity> bipedModel = (HumanoidModel) (Object) this;
        AnimationHandler.setupAnim(livingEntity, bipedModel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        AnimationUtil.animate(bipedModel, CommonEvents.glideAnimation, Animations.GLIDING, ageInTicks, 1);
        bipedModel.hat.copyFrom(bipedModel.head);
    }


}