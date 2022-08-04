package mc.craig.software.notnotyet.networking;

import mc.craig.software.notnotyet.NoNotYet;
import mc.craig.software.notnotyet.networking.packets.MessageSyncCap;
import mc.craig.software.notnotyet.networking.packets.MessageToggleGlide;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class Network {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(NoNotYet.MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void init() {
        int id = 0;
        INSTANCE.registerMessage(id++, MessageToggleGlide.class, MessageToggleGlide::toBytes, MessageToggleGlide::new, MessageToggleGlide::handle);
        INSTANCE.registerMessage(id++, MessageSyncCap.class, MessageSyncCap::toBytes, MessageSyncCap::new, MessageSyncCap::handle);

    }
}
