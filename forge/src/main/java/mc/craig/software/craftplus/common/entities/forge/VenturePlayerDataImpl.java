package mc.craig.software.craftplus.common.entities.forge;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.common.entities.VenturePlayerData;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
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
public class VenturePlayerDataImpl implements ICapabilitySerializable<CompoundTag> {

    public static Capability<VenturePlayerData> PLAYER_DATA = CapabilityManager.get(new CapabilityToken<>() {
    });

    @SubscribeEvent
    public static void init(RegisterCapabilitiesEvent e) {
        e.register(VenturePlayerData.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> e) {
        if (e.getObject() instanceof Player player) {
            e.addCapability(VentureCraft.id("player_data"), new VenturePlayerDataImpl(player));
        }
    }

    public final VenturePlayerData capability;
    public final LazyOptional<VenturePlayerData> lazyOptional;

    public VenturePlayerDataImpl(Player player) {
        this.capability = new VenturePlayerData(player);
        this.lazyOptional = LazyOptional.of(() -> this.capability);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
        return capability == PLAYER_DATA ? this.lazyOptional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return this.capability.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag arg) {
        this.capability.deserializeNBT(arg);
    }

    public static Optional<VenturePlayerData> get(LivingEntity player) {
        return player.getCapability(PLAYER_DATA).resolve();
    }
}
