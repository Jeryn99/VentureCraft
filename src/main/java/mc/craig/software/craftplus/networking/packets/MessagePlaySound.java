package mc.craig.software.craftplus.networking.packets;

import mc.craig.software.craftplus.util.ClientUtil;
import mc.craig.software.craftplus.util.GliderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class MessagePlaySound {
    private final ResourceLocation sound;
    private final UUID playerUUID;

    public MessagePlaySound(ResourceLocation sound, UUID playerUUID) {
        this.playerUUID = playerUUID;
        this.sound = sound;
    }

    public MessagePlaySound(FriendlyByteBuf buffer) {
        sound = buffer.readResourceLocation();
        playerUUID = buffer.readUUID();
    }

    public static void handle(MessagePlaySound message, Supplier<NetworkEvent.Context> ctx) {
        Minecraft.getInstance().submitAsync(() -> {
            if (Minecraft.getInstance().level != null) {
                Player player = Minecraft.getInstance().level.getPlayerByUUID(message.playerUUID);
                if (player != null) {
                    ClientUtil.playGliderSound(player, message.sound, SoundSource.PLAYERS, true, () -> !GliderUtil.isGlidingWithActiveGlider(player), 0.1F, RandomSource.create());
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeResourceLocation(this.sound);
        buffer.writeUUID(this.playerUUID);
    }

}
