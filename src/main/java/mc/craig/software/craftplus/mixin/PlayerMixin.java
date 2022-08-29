package mc.craig.software.craftplus.mixin;

import mc.craig.software.craftplus.common.capability.PlayerInventoryWrapper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin {

    @Mutable
    @Shadow
    @Final
    private Inventory inventory;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void constructor(CallbackInfo ci) {
        this.inventory = new PlayerInventoryWrapper(this.inventory);
    }

}
