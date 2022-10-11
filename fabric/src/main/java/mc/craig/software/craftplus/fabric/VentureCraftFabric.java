package mc.craig.software.craftplus.fabric;

import mc.craig.software.craftplus.VentureCraft;
import net.fabricmc.api.ModInitializer;

public class VentureCraftFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        VentureCraft.init();
    }

}
