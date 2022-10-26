package mc.craig.software.craftplus.fabric;

import mc.craig.software.craftplus.VentureCraftClient;
import mc.craig.software.craftplus.common.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

public class VentureCraftClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        VentureCraftClient.init();
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TRINKET_LIBERTY.get(), RenderType.cutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TRINKET_TARDIS.get(), RenderType.cutoutMipped());
    }
}
