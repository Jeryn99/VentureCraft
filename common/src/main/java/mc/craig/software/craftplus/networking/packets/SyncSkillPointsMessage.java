package mc.craig.software.craftplus.networking.packets;

import mc.craig.software.craftplus.common.entities.VenturePlayerData;
import mc.craig.software.craftplus.networking.VCNetwork;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.threetag.palladiumcore.network.MessageContext;
import net.threetag.palladiumcore.network.MessageS2C;
import net.threetag.palladiumcore.network.MessageType;
import org.jetbrains.annotations.NotNull;

public class SyncSkillPointsMessage extends MessageS2C {

    public final int skillXP;
    public final int skillPoints;

    public SyncSkillPointsMessage(int skillXP, int skillPoints) {
        this.skillXP = skillXP;
        this.skillPoints = skillPoints;
    }

    public SyncSkillPointsMessage(FriendlyByteBuf buf) {
        this.skillXP = buf.readInt();
        this.skillPoints = buf.readInt();
    }

    @NotNull
    @Override
    public MessageType getType() {
        return VCNetwork.SYNC_SKILL_POINTS;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.skillXP);
        buf.writeInt(this.skillPoints);
    }

    @Override
    public void handle(MessageContext context) {
        this.handleClient();
    }

    @Environment(EnvType.CLIENT)
    public void handleClient() {
        VenturePlayerData.get(Minecraft.getInstance().player).ifPresent(data -> {
            data.setSkillXP(this.skillXP);
            data.setSkillPoints(this.skillPoints);
        });
    }
}
