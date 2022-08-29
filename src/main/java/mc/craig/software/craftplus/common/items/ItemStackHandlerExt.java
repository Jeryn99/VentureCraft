package mc.craig.software.craftplus.common.items;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.TriPredicate;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;

public class ItemStackHandlerExt extends ItemStackHandler {

    private TriPredicate<ItemStackHandlerExt, Integer, ItemStack> validator;
    private BiConsumer<ItemStackHandlerExt, Integer> changedCallback;

    public ItemStackHandlerExt(int size) {
        super(size);
    }

    public ItemStackHandlerExt(NonNullList<ItemStack> stacks) {
        super(stacks);
    }

    public ItemStackHandlerExt setValidator(TriPredicate<ItemStackHandlerExt, Integer, ItemStack> validator) {
        this.validator = validator;
        return this;
    }

    public ItemStackHandlerExt setChangedCallback(BiConsumer<ItemStackHandlerExt, Integer> changedCallback) {
        this.changedCallback = changedCallback;
        return this;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return this.validator == null || this.validator.test(this, slot, stack);
    }

    @Override
    protected void onContentsChanged(int slot) {
        if (this.changedCallback != null)
            this.changedCallback.accept(this, slot);
    }
}
