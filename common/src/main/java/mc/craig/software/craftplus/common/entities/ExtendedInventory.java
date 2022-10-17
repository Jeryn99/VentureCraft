package mc.craig.software.craftplus.common.entities;

import dev.architectury.injectables.annotations.ExpectPlatform;
import mc.craig.software.craftplus.common.items.ClimbingGearItem;
import mc.craig.software.craftplus.networking.packets.MessageSyncExtendedInv;
import mc.craig.software.craftplus.util.item.AdvItemStackHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;

import java.util.Optional;

public class ExtendedInventory implements AdvItemStackHandler.ItemChanged {

    public static final int GEAR_SLOT_CLIMBING = 0;
    public static final int GEAR_SLOT_ELYTRA = 1;
    public static final int GEAR_SLOT_BACKPACK = 2;
    public static final int TOOL_SLOT_COMPASS = 0;
    public static final int TOOL_SLOT_CLOCK = 1;
    public static final int TOOL_SLOT_MAP = 2;

    public final Player player;

    // TODO validation via tags
    public final AdvItemStackHandler totems = new AdvItemStackHandler(3).setChangedCallback(this).setValidator((itemHandler, slot, stack) -> stack.getItem() == Items.TOTEM_OF_UNDYING);
    public final AdvItemStackHandler tools = new AdvItemStackHandler(3).setChangedCallback(this).setValidator((itemHandler, slot, stack) -> {
        if (slot == 0)
            return stack.getItem() instanceof CompassItem;
        else if (slot == 1)
            return stack.getItem() == Items.CLOCK;
        else
            return stack.getItem() instanceof MapItem;
    });
    public final AdvItemStackHandler gear = new AdvItemStackHandler(3).setChangedCallback(this).setValidator((itemHandler, slot, stack) -> {
        if (slot == 0)
            return stack.getItem() instanceof ClimbingGearItem;
        else if (slot == 1)
            return stack.getItem() instanceof ElytraItem;
        else
            return stack.getItem() == Items.APPLE;
    });
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
        tag.put("Totems", this.totems.serializeNBT());
        tag.put("Tools", this.tools.serializeNBT());
        tag.put("Gear", this.gear.serializeNBT());
        tag.put("Upgrade1", this.upgrade1.serializeNBT());
        tag.put("Upgrade2", this.upgrade2.serializeNBT());
        tag.put("Upgrade3", this.upgrade3.serializeNBT());
        return tag;
    }

    public void deserializeNBT(CompoundTag nbt) {
        this.totems.deserializeNBT(nbt.getCompound("Totems"));
        this.tools.deserializeNBT(nbt.getCompound("Tools"));
        this.gear.deserializeNBT(nbt.getCompound("Gear"));
        this.upgrade1.deserializeNBT(nbt.getCompound("Upgrade1"));
        this.upgrade2.deserializeNBT(nbt.getCompound("Upgrade2"));
        this.upgrade3.deserializeNBT(nbt.getCompound("Upgrade2"));
    }

    @ExpectPlatform
    public static Optional<ExtendedInventory> get(Player player) {
        throw new AssertionError();
    }

}
