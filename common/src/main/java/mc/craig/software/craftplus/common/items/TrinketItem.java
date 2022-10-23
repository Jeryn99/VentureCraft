package mc.craig.software.craftplus.common.items;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TrinketItem extends Item {
    public TrinketItem(Properties properties) {
        super(properties);
    }

    public enum Trinket {
        TARDIS, LIBRETY, HOVER
    }

    @Override
    public void fillItemCategory(CreativeModeTab category, NonNullList<ItemStack> items) {
        if (this.allowedIn(category)) {
            for (Trinket value : Trinket.values()) {
                ItemStack stack = new ItemStack(this);
                System.out.println(value + " " + value.ordinal());
                setTrinket(value.ordinal(), stack);
                items.add(stack);
            }
        }
    }

    public static void setTrinket(int trinketId, ItemStack stack){
        CompoundTag stackTag = stack.getOrCreateTag();
        stackTag.putInt("trinket", trinketId);
    }

    public static int getTrinket(ItemStack stack){
        CompoundTag stackTag = stack.getOrCreateTag();
        return stackTag.getInt("trinket");
    }
}
