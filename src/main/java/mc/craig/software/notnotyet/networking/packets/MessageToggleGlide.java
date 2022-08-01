package mc.craig.software.notnotyet.networking.packets;

import mc.craig.software.notnotyet.common.items.ParagliderItem;
import mc.craig.software.notnotyet.util.GliderUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class MessageToggleGlide {

    public MessageToggleGlide() {
    }

    public MessageToggleGlide(FriendlyByteBuf buffer) {
    }

    public static void handle(MessageToggleGlide message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().getSender().getServer().submitAsync(() -> {
            @Nullable ServerPlayer sender = ctx.get().getSender();
            if (sender != null) {
                if (GliderUtil.hasParagliderEquipped(sender)) {
                    ItemStack chestItem = sender.getItemBySlot(EquipmentSlot.CHEST);
                    ParagliderItem.setGlide(chestItem, !ParagliderItem.glidingEnabled(chestItem));
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }

    public void toBytes(FriendlyByteBuf buf) {

    }

}