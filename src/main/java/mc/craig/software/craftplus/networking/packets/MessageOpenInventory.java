package mc.craig.software.craftplus.networking.packets;

import mc.craig.software.craftplus.common.menu.ExtendedInventoryMenu;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class MessageOpenInventory {

    public MessageOpenInventory() {
    }

    public MessageOpenInventory(FriendlyByteBuf buffer) {
    }

    public static void handle(MessageOpenInventory message, Supplier<NetworkEvent.Context> ctx) {
        Objects.requireNonNull(Objects.requireNonNull(ctx.get().getSender()).getServer()).submitAsync(() -> {
            Objects.requireNonNull(ctx.get().getSender()).openMenu(new SimpleMenuProvider((pContainerId, pPlayerInventory, pPlayer) -> new ExtendedInventoryMenu(pContainerId, pPlayerInventory), ExtendedInventoryMenu.CONTAINER_TITLE));
        });
        ctx.get().setPacketHandled(true);
    }

    public void toBytes(FriendlyByteBuf buf) {

    }

}
