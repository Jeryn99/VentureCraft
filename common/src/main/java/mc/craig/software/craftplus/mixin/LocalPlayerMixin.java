package mc.craig.software.craftplus.mixin;

import mc.craig.software.craftplus.networking.packets.MessageToggleGlide;
import mc.craig.software.craftplus.util.GliderUtil;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {

    // Logically, I could just write logic for checking if the player press space while falling, but if the logic already exists in the vanilla game...it does make sense to use it
    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"))
    private void aiStep(CallbackInfo info) {
        LocalPlayer localPlayer = (LocalPlayer) (Object) this;
        if (GliderUtil.hasParagliderEquipped(localPlayer) && !localPlayer.isOnGround()) {
            new MessageToggleGlide().send();
        }
    }
}
