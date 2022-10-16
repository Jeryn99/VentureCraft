package mc.craig.software.craftplus.common.block;

import mc.craig.software.craftplus.common.ModItems;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public enum VCChestTypes {
    VOID(ModItems.VOID_KEY), SAPPHIRE(ModItems.SAPPHIRE_KEY), GOLD(ModItems.GOLD_KEY), IRON(ModItems.IRON_KEY);

    private final Supplier<Item> unlockedBy;

    VCChestTypes(Supplier<Item> item) {
        this.unlockedBy = item;
    }

    public Item getUnlockedBy() {
        return unlockedBy.get();
    }
}
