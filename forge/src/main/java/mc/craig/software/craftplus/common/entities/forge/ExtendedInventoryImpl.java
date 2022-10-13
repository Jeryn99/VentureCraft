package mc.craig.software.craftplus.common.entities.forge;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.common.entities.ExtendedInventory;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = VentureCraft.MODID)
public class ExtendedInventoryImpl implements ICapabilitySerializable<CompoundTag> {

    public static Capability<ExtendedInventory> EXTENDED_INVENTORY = CapabilityManager.get(new CapabilityToken<>() {
    });

    @SubscribeEvent
    public static void init(RegisterCapabilitiesEvent e) {
        e.register(ExtendedInventory.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> e) {
        if (e.getObject() instanceof Player player) {
            e.addCapability(VentureCraft.id("extended_inventory"), new ExtendedInventoryImpl(player));
        }
    }

    public final ExtendedInventory capability;
    public final LazyOptional<ExtendedInventory> lazyOptional;

    public ExtendedInventoryImpl(Player player) {
        this.capability = new ExtendedInventory(player);
        this.lazyOptional = LazyOptional.of(() -> this.capability);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
        return capability == EXTENDED_INVENTORY ? this.lazyOptional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return this.capability.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag arg) {
        this.capability.deserializeNBT(arg);
    }

    public static Optional<ExtendedInventory> get(Player player) {
        return player.getCapability(EXTENDED_INVENTORY).resolve();
    }
}
