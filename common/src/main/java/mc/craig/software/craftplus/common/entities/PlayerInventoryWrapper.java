package mc.craig.software.craftplus.common.entities;

import com.google.common.collect.ImmutableList;
import mc.craig.software.craftplus.mixin.InventoryAccessor;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class PlayerInventoryWrapper extends Inventory {

    public static final int OFF_HAND_SLOT = 11 * 5 + 4;

    public PlayerInventoryWrapper(Inventory parent) {
        super(parent.player);

        if (this instanceof InventoryAccessor accessor) {
            accessor.setItems(NonNullList.withSize(11 * 5, ItemStack.EMPTY));
            accessor.setCompartments(ImmutableList.of(this.items, this.armor, this.offhand));
        }
    }

    @Override
    public int getSuitableHotbarSlot() {
        for (int i = 0; i < 11; ++i) {
            int j = (this.selected + i) % 11;
            if (this.items.get(j).isEmpty()) {
                return j;
            }
        }

        for (int k = 0; k < 11; ++k) {
            int l = (this.selected + k) % 11;
            if (!this.items.get(l).isEnchanted()) {
                return l;
            }
        }

        return this.selected;
    }

    @Override
    public void swapPaint(double pDirection) {
        int i = (int) Math.signum(pDirection);

        for (this.selected -= i; this.selected < 0; this.selected += 11) {
        }

        while (this.selected >= 11) {
            this.selected -= 11;
        }
    }

    @Override
    public int getSlotWithRemainingSpace(ItemStack pStack) {
        if (this.hasRemainingSpaceForItem(this.getItem(this.selected), pStack)) {
            return this.selected;
        } else if (this.hasRemainingSpaceForItem(this.getItem(OFF_HAND_SLOT), pStack)) {
            return OFF_HAND_SLOT;
        } else {
            for(int i = 0; i < this.items.size(); ++i) {
                if (this.hasRemainingSpaceForItem(this.items.get(i), pStack)) {
                    return i;
                }
            }

            return -1;
        }
    }

    @Override
    public void setPickedItem(ItemStack stack) {
        int i = this.findSlotMatchingItem(stack);
        if (isHotbarSlot(i)) {
            this.selected = i;
        } else {
            if (i == -1) {
                this.selected = this.getSuitableHotbarSlot();
                if (!this.items.get(this.selected).isEmpty()) {
                    int j = this.getFreeSlot();
                    if (j != -1) {
                        this.items.set(j, this.items.get(this.selected));
                    }
                }

                this.items.set(this.selected, stack);
            } else {
                this.pickSlot(i);
            }

        }
    }

    private boolean hasRemainingSpaceForItem(ItemStack pDestination, ItemStack pOrigin) {
        return !pDestination.isEmpty() && ItemStack.isSameItemSameTags(pDestination, pOrigin) && pDestination.isStackable() && pDestination.getCount() < pDestination.getMaxStackSize() && pDestination.getCount() < this.getMaxStackSize();
    }
}
