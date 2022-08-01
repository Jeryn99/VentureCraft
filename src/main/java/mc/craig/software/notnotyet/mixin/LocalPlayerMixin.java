package mc.craig.software.notnotyet.mixin;

import mc.craig.software.notnotyet.networking.Network;
import mc.craig.software.notnotyet.networking.packets.MessageToggleGlide;
import mc.craig.software.notnotyet.util.GliderUtil;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {

    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;canElytraFly(Lnet/minecraft/world/entity/LivingEntity;)Z"))
    private void aiStep(CallbackInfo info) {
        LocalPlayer localPlayer = (LocalPlayer) (Object) this;
        if (GliderUtil.hasParagliderEquipped(localPlayer) && !localPlayer.isOnGround()) {
            Network.INSTANCE.sendToServer(new MessageToggleGlide());
        }
    }
}
