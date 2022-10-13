package mc.craig.software.craftplus.networking.packets;

import mc.craig.software.craftplus.common.entities.ExtendedInventory;
import mc.craig.software.craftplus.networking.VCNetwork;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.threetag.palladiumcore.network.MessageContext;
import net.threetag.palladiumcore.network.MessageS2C;
import net.threetag.palladiumcore.network.MessageType;
import org.jetbrains.annotations.NotNull;

public class MessageSyncExtendedInv extends MessageS2C {

    public int entityID;
    public CompoundTag nbt;

    public MessageSyncExtendedInv(int entityID, CompoundTag nbt) {
        this.entityID = entityID;
        this.nbt = nbt;
    }

    public MessageSyncExtendedInv(FriendlyByteBuf buf) {
        this.entityID = buf.readInt();
        this.nbt = buf.readNbt();
    }

    @NotNull
    @Override
    public MessageType getType() {
        return VCNetwork.SYNC_EXTENDED_INV;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.entityID);
        buf.writeNbt(this.nbt);
    }

    @Override
    public void handle(MessageContext context) {
        this.handleClient();
    }

    @Environment(EnvType.CLIENT)
    public void handleClient() {
        var entity = Minecraft.getInstance().level.getEntity(this.entityID);

        if (entity instanceof Player player) {
            ExtendedInventory.get(player).ifPresent(inv -> inv.deserializeNBT(this.nbt));
        }
    }

}
