package mc.craig.software.craftplus.common.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;

public interface IExtendedInventory extends INBTSerializable<CompoundTag> {

    IItemHandler getHotbar();

    IItemHandler getMainInventory();

    IItemHandler getFullInventory();

    void sync();

    void syncTo(ServerPlayer target);

}
