package mc.craig.software.craftplus.fabric;

import mc.craig.software.craftplus.VentureCraftClient;
import mc.craig.software.craftplus.common.menu.ExtendedInventoryMenu;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.renderer.texture.TextureAtlas;

public class VentureCraftClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        VentureCraftClient.init();
        ClientSpriteRegistryCallback.event(TextureAtlas.LOCATION_BLOCKS).register((atlasTexture, registry) -> {
            registry.register(ExtendedInventoryMenu.EMPTY_TOTEM_SLOT);
            registry.register(ExtendedInventoryMenu.EMPTY_TOOL_SLOT_COMPASS);
            registry.register(ExtendedInventoryMenu.EMPTY_TOOL_SLOT_CLOCK);
            registry.register(ExtendedInventoryMenu.EMPTY_TOOL_SLOT_MAP);
            registry.register(ExtendedInventoryMenu.EMPTY_GEAR_SLOT_CLIMBING);
            registry.register(ExtendedInventoryMenu.EMPTY_GEAR_SLOT_ELYTRA);
            registry.register(ExtendedInventoryMenu.EMPTY_GEAR_SLOT_BACKPACK);
        });
    }
}
