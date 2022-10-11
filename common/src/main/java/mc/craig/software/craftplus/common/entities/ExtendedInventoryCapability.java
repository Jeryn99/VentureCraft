package mc.craig.software.craftplus.common.capability;

import mc.craig.software.craftplus.common.items.ItemStackHandlerExt;
import mc.craig.software.craftplus.networking.Network;
import mc.craig.software.craftplus.networking.packets.MessageSyncExtendedInv;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.BiConsumer;

public class ExtendedInventoryCapability implements IExtendedInventory, BiConsumer<ItemStackHandlerExt, Integer> {

    public static final Capability<IExtendedInventory> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });

    public final Player player;
    public final ItemStackHandlerExt hotbar = new ItemStackHandlerExt(11).setChangedCallback(this);
    public final ItemStackHandlerExt mainInv = new ItemStackHandlerExt(11 * 3).setChangedCallback(this);

    public ExtendedInventoryCapability(Player player) {
        this.player = player;
    }

    @Override
    public void accept(ItemStackHandlerExt itemStackHandlerExt, Integer integer) {
        // TODO specified slot syncing
        this.sync();
    }

    @Override
    public void sync() {
        if(!this.player.level.isClientSide) {
            CompoundTag tag = new CompoundTag();
            this.deserializeNBT(tag);
            Network.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> this.player), new MessageSyncExtendedInv(this.player.getId(), tag));
        }
    }

    @Override
    public void syncTo(ServerPlayer target) {
        if(!this.player.level.isClientSide) {
            CompoundTag tag = new CompoundTag();
            this.deserializeNBT(tag);
            Network.INSTANCE.send(PacketDistributor.PLAYER.with(() -> target), new MessageSyncExtendedInv(this.player.getId(), tag));
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.put("Hotbar", this.hotbar.serializeNBT());
        tag.put("MainInv", this.mainInv.serializeNBT());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.hotbar.deserializeNBT(nbt.getCompound("Hotbar"));
        this.mainInv.deserializeNBT(nbt.getCompound("MainInv"));
    }

    @Override
    public IItemHandler getHotbar() {
        return this.hotbar;
    }

    @Override
    public IItemHandler getMainInventory() {
        return this.mainInv;
    }

    @Override
    public IItemHandler getFullInventory() {
        return new CombinedInvWrapper(this.mainInv, this.hotbar);
    }
}
