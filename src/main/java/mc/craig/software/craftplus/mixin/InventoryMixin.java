package mc.craig.software.craftplus.mixin;

import net.minecraft.world.entity.player.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Inventory.class)
public class InventoryMixin {

    @Inject(at = @At("HEAD"), method = "isHotbarSlot", cancellable = true)
    private static void isHotbarSlot(int pIndex, CallbackInfoReturnable<Boolean> ci) {
        ci.setReturnValue(pIndex >= 0 && pIndex < 11);
    }

}
