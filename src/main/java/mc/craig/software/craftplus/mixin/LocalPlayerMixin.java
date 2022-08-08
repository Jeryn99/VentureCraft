package mc.craig.software.craftplus.mixin;

import mc.craig.software.craftplus.networking.Network;
import mc.craig.software.craftplus.networking.packets.MessageToggleGlide;
import mc.craig.software.craftplus.util.GliderUtil;
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
