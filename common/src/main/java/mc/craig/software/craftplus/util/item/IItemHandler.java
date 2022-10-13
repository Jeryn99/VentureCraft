package mc.craig.software.craftplus.util.item;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface IItemHandler {

    int getSlots();

    @NotNull
    ItemStack getStackInSlot(int slot);

    @NotNull
    ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate);

    @NotNull
    ItemStack extractItem(int slot, int amount, boolean simulate);

    void setStackInSlot(int slot, @NotNull ItemStack stack);

    int getSlotLimit(int slot);

    boolean isItemValid(int slot, @NotNull ItemStack stack);

}
