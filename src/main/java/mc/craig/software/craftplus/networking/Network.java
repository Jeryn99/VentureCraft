package mc.craig.software.craftplus.networking;

import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.networking.packets.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class Network {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MinecraftPlus.MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void init() {
        INSTANCE.registerMessage(0, MessageToggleGlide.class, MessageToggleGlide::toBytes, MessageToggleGlide::new, MessageToggleGlide::handle);
        INSTANCE.registerMessage(1, MessageSyncCap.class, MessageSyncCap::toBytes, MessageSyncCap::new, MessageSyncCap::handle);
        INSTANCE.registerMessage(2, MessagePlaySound.class, MessagePlaySound::toBytes, MessagePlaySound::new, MessagePlaySound::handle);
        INSTANCE.registerMessage(3, MessageOpenInventory.class, MessageOpenInventory::toBytes, MessageOpenInventory::new, MessageOpenInventory::handle);
        INSTANCE.registerMessage(4, MessageSyncExtendedInv.class, MessageSyncExtendedInv::toBytes, MessageSyncExtendedInv::new, MessageSyncExtendedInv::handle);
    }
}
