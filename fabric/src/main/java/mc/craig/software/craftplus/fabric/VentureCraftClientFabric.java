package mc.craig.software.craftplus.fabric;

import mc.craig.software.craftplus.VentureCraftClient;
import net.fabricmc.api.ClientModInitializer;

public class VentureCraftClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        VentureCraftClient.init();
    }
}
