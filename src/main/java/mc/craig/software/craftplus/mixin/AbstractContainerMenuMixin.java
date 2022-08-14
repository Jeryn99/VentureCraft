package mc.craig.software.craftplus.mixin;

import mc.craig.software.craftplus.common.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.GrindstoneMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractContainerMenu.class)
public class AbstractContainerMenuMixin {

    @Shadow
    @Final
    public NonNullList<Slot> slots;

    @Shadow
    @Final
    private NonNullList<ItemStack> lastSlots;

    @Shadow
    @Final
    private NonNullList<ItemStack> remoteSlots;

    // This is horrible, but Forge has forced my hand
    @Inject(at = @At("HEAD"), method = "addSlot(Lnet/minecraft/world/inventory/Slot;)Lnet/minecraft/world/inventory/Slot;")
    private void addSlot(Slot slot, CallbackInfoReturnable<Slot> cir) {
        if (((Object) this) instanceof GrindstoneMenu && slot.index == 0 && slot.x == 49) {

            Slot newSlot = new Slot(slot.container, 0, 49, 19) {
                @Override
                public boolean mayPlace(ItemStack itemStack) {
                    return slot.mayPlace(itemStack) || itemStack.getItem() == ModItems.UNREFINED_RUBY.get();
                }
            };

            hackSlots(newSlot);

        }
    }

    protected Slot hackSlots(Slot slot) {
        slot.index = slots.size();
        slots.add(slot);
        lastSlots.add(ItemStack.EMPTY);
        remoteSlots.add(ItemStack.EMPTY);
        return slot;
    }
}
