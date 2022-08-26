package mc.craig.software.craftplus.common.menu;

import mc.craig.software.craftplus.common.ModMenus;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class ExtendedInventoryMenu extends AbstractContainerMenu {

    public static final Component CONTAINER_TITLE = Component.translatable("container.minecraft_plus.extended_inventory");

    public ExtendedInventoryMenu(int pContainerId, Inventory pPlayerInventory) {
        super(ModMenus.EXTENDED_INVENTORY.get(), pContainerId);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
