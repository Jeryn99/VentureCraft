package mc.craig.software.craftplus.util.item;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class AdvItemStackHandler extends ItemStackHandler {

    private ItemValidator validator;
    private ItemChanged changedCallback;

    public AdvItemStackHandler(int size) {
        super(size);
    }

    public AdvItemStackHandler(NonNullList<ItemStack> stacks) {
        super(stacks);
    }

    public AdvItemStackHandler setValidator(ItemValidator validator) {
        this.validator = validator;
        return this;
    }

    public AdvItemStackHandler setChangedCallback(ItemChanged changedCallback) {
        this.changedCallback = changedCallback;
        return this;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return this.validator == null || this.validator.isValid(this, slot, stack);
    }

    @Override
    protected void onContentsChanged(int slot) {
        if (this.changedCallback != null)
            this.changedCallback.onItemChanged(this, slot);
    }

    @FunctionalInterface
    public interface ItemValidator {

        boolean isValid(AdvItemStackHandler itemHandler, int slot, ItemStack stack);

    }

    @FunctionalInterface
    public interface ItemChanged {

        void onItemChanged(AdvItemStackHandler itemHandler, int slot);

    }

}
