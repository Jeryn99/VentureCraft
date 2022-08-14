package mc.craig.software.craftplus.mixin;

import mc.craig.software.craftplus.common.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.GrindstoneMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

    @Inject(at = @At("HEAD"), method = "addSlot(Lnet/minecraft/world/inventory/Slot;)Lnet/minecraft/world/inventory/Slot;")
    private void addSlot(Slot slot, CallbackInfoReturnable<Slot> cir) {
        if (((Object) this) instanceof GrindstoneMenu && slot.index == 0) {

            Slot newSlot = new Slot(slot.container, 0, 49, 19) {
                @Override
                public boolean mayPlace(ItemStack itemStack) {
                    return itemStack.isDamageableItem() || itemStack.is(Items.ENCHANTED_BOOK) || itemStack.isEnchanted() || itemStack.getItem() == ModItems.UNREFINED_RUBY.get();
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
