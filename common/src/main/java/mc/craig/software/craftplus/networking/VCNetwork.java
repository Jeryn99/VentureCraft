package mc.craig.software.craftplus.networking;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.networking.packets.MessagePlaySound;
import mc.craig.software.craftplus.networking.packets.MessageSyncCap;
import mc.craig.software.craftplus.networking.packets.MessageSyncExtendedInv;
import mc.craig.software.craftplus.networking.packets.MessageToggleGlide;
import net.threetag.palladiumcore.network.MessageType;
import net.threetag.palladiumcore.network.NetworkManager;

public class VCNetwork {

    public static final NetworkManager INSTANCE = NetworkManager.create(VentureCraft.id("main"));

    public static MessageType TOGGLE_GLIDE, SYNC_CAP, PLAY_SOUND, SYNC_EXTENDED_INV;

    public static void init() {
        PLAY_SOUND = INSTANCE.registerS2C("play_sound", MessagePlaySound::new);
        SYNC_CAP = INSTANCE.registerS2C("sync_cap", MessageSyncCap::new);
        TOGGLE_GLIDE = INSTANCE.registerC2S("toggle_glide", MessageToggleGlide::new);
        SYNC_EXTENDED_INV = INSTANCE.registerS2C("sync_extended_inv", MessageSyncExtendedInv::new);
    }
}
