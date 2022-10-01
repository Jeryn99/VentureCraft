package mc.craig.software.craftplus.networking.packets;

import mc.craig.software.craftplus.networking.Network;
import mc.craig.software.craftplus.util.ClientUtil;
import mc.craig.software.craftplus.util.GliderUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.threetag.palladiumcore.network.MessageContext;
import net.threetag.palladiumcore.network.MessageS2C;
import net.threetag.palladiumcore.network.MessageType;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class MessagePlaySound extends MessageS2C {
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

    @Override
    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeResourceLocation(this.sound);
        buffer.writeUUID(this.playerUUID);
    }

    @NotNull
    @Override
    public MessageType getType() {
        return Network.PLAY_SOUND;
    }

    @Override
    public void handle(MessageContext context) {
        this.handleClient();
    }

    @Environment(EnvType.CLIENT)
    private void handleClient() {
        if (Minecraft.getInstance().level != null) {
            Player player = Minecraft.getInstance().level.getPlayerByUUID(this.playerUUID);
            if (player != null) {
                ClientUtil.playGliderSound(player, this.sound, SoundSource.PLAYERS, true, () -> !GliderUtil.isGlidingWithActiveGlider(player), 0.1F, RandomSource.create());
            }
        }
    }

}
