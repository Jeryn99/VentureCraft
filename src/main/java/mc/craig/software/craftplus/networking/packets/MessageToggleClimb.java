package mc.craig.software.craftplus.networking.packets;

import mc.craig.software.craftplus.common.capability.ModCapability;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class MessageToggleClimb {

    public MessageToggleClimb() {
    }

    public MessageToggleClimb(FriendlyByteBuf buffer) {
    }

    public static void handle(MessageToggleClimb message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().getSender().getServer().submitAsync(() -> {
            @Nullable ServerPlayer sender = ctx.get().getSender();
            if (sender != null) {
                ModCapability.get(sender).ifPresent(iCap -> {
                    iCap.setClimbingEnabled(true);
                    iCap.sync();
                });
            }
        });
        ctx.get().setPacketHandled(true);
    }

    public void toBytes(FriendlyByteBuf buf) {

    }

}