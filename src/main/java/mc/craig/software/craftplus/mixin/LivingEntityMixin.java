package mc.craig.software.craftplus.mixin;

import mc.craig.software.craftplus.common.capability.ModCapability;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(at = @At("HEAD"), cancellable = true, method = "onClimbable()Z")
    private void onClimbable(CallbackInfoReturnable<Boolean> callbackInfo) {
       /* LivingEntity living = (LivingEntity) (Object) this;
        ModCapability.get(living).ifPresent(iCap -> {
            if (iCap.canClimb(living)) {
                callbackInfo.setReturnValue(true);
            }
        });*/
    }
}
