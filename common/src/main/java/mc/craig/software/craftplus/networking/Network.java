package mc.craig.software.craftplus.networking;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.networking.packets.MessagePlaySound;
import mc.craig.software.craftplus.networking.packets.MessageSyncCap;
import mc.craig.software.craftplus.networking.packets.MessageToggleGlide;
import net.threetag.palladiumcore.network.MessageType;
import net.threetag.palladiumcore.network.NetworkManager;

public class Network {

    public static final NetworkManager INSTANCE = NetworkManager.create(VentureCraft.id("main"));

    public static final MessageType TOGGLE_GLIDE = INSTANCE.registerC2S("toggle_glide", MessageToggleGlide::new);
    public static final MessageType SYNC_CAP = INSTANCE.registerS2C("sync_cap", MessageSyncCap::new);
    public static final MessageType PLAY_SOUND = INSTANCE.registerS2C("play_sound", MessagePlaySound::new);

    public static void init() {

    }
}
