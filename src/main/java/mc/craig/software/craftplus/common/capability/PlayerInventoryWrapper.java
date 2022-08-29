package mc.craig.software.craftplus.common.capability;

import com.google.common.collect.ImmutableList;
import mc.craig.software.craftplus.mixin.InventoryAccessor;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class PlayerInventoryWrapper extends Inventory {

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
        // Fix offhand thing (currently offhand is index=40)
        return super.getSlotWithRemainingSpace(pStack);
    }
}
