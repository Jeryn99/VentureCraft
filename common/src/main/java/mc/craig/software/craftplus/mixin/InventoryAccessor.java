package mc.craig.software.craftplus.mixin;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(Inventory.class)
public interface InventoryAccessor {

    @Mutable
    @Accessor("items")
    void setItems(NonNullList<ItemStack> items);

    @Mutable
    @Accessor("compartments")
    void setCompartments(List<NonNullList<ItemStack>> compartments);

}
