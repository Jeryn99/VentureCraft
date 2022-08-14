package mc.craig.software.craftplus.mixin;

import mc.craig.software.craftplus.common.ModItems;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.GrindstoneMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GrindstoneMenu.class)
public class GrindstoneMenuMixin {

    @Shadow
    @Final
    private Container repairSlots;

    @Shadow
    @Final
    private Container resultSlots;

    @Inject(at = @At("HEAD"), cancellable = true, method = "createResult()V")
    private void createResult(CallbackInfo callbackInfo) {
        ItemStack repairSlot = repairSlots.getItem(0);

        if (repairSlot.is(ModItems.UNREFINED_RUBY.get())) {
            resultSlots.setItem(0, new ItemStack(ModItems.RUBY.get(), repairSlot.getCount()));
            callbackInfo.cancel();
        }
    }


}
