package mc.craig.software.craftplus.common.entities;

import dev.architectury.injectables.annotations.ExpectPlatform;
import mc.craig.software.craftplus.networking.packets.MessageSyncExtendedInv;
import mc.craig.software.craftplus.util.item.AdvItemStackHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;

public class ExtendedInventory implements AdvItemStackHandler.ItemChanged {

    public final Player player;
    public final AdvItemStackHandler upgrade1 = new AdvItemStackHandler(8).setChangedCallback(this);
    public final AdvItemStackHandler upgrade2 = new AdvItemStackHandler(8).setChangedCallback(this);
    public final AdvItemStackHandler upgrade3 = new AdvItemStackHandler(8).setChangedCallback(this);

    public ExtendedInventory(Player player) {
        this.player = player;
    }

    @Override
    public void onItemChanged(AdvItemStackHandler itemHandler, int slot) {
        // TODO only sync relevant item!
        this.sync();
    }

    public void sync() {
        if (!this.player.level.isClientSide) {
            new MessageSyncExtendedInv(this.player.getId(), this.serializeNBT()).sendToTracking(this.player);
        }
    }

    public void syncTo(ServerPlayer target) {
        if (!this.player.level.isClientSide) {
            new MessageSyncExtendedInv(this.player.getId(), this.serializeNBT()).send(target);
        }
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.put("Upgrade1", this.upgrade1.serializeNBT());
        tag.put("Upgrade2", this.upgrade2.serializeNBT());
        tag.put("Upgrade3", this.upgrade3.serializeNBT());
        return tag;
    }

    public void deserializeNBT(CompoundTag nbt) {
        this.upgrade1.deserializeNBT(nbt.getCompound("Upgrade1"));
        this.upgrade2.deserializeNBT(nbt.getCompound("Upgrade2"));
        this.upgrade3.deserializeNBT(nbt.getCompound("Upgrade2"));
    }

    @ExpectPlatform
    public static Optional<ExtendedInventory> get(Player player) {
        throw new AssertionError();
    }

}
