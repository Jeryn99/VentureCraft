package mc.craig.software.craftplus;

import mc.craig.software.craftplus.client.layers.OwlShoulderLayer;
import mc.craig.software.craftplus.client.layers.PlayerGliderLayer;
import mc.craig.software.craftplus.client.models.Models;
import mc.craig.software.craftplus.client.renderers.RenderAdvancedArrow;
import mc.craig.software.craftplus.client.renderers.RenderLootChest;
import mc.craig.software.craftplus.client.renderers.RenderOwl;
import mc.craig.software.craftplus.client.renderers.RenderStalker;
import mc.craig.software.craftplus.common.ModBlockEntities;
import mc.craig.software.craftplus.common.ModEntities;
import mc.craig.software.craftplus.common.ModItems;
import mc.craig.software.craftplus.common.items.ParagliderItem;
import mc.craig.software.craftplus.common.items.TierArmorItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.threetag.palladiumcore.event.LifecycleEvents;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.palladiumcore.registry.client.BlockEntityRendererRegistry;
import net.threetag.palladiumcore.registry.client.ColorHandlerRegistry;
import net.threetag.palladiumcore.registry.client.EntityRendererRegistry;

public class VentureCraftClient {

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void init() {
        // Entity Renderers
        EntityRendererRegistry.register(ModEntities.STALKER, RenderStalker::new);
        EntityRendererRegistry.register(ModEntities.ADVANCED_ARROW, RenderAdvancedArrow::new);
        EntityRendererRegistry.register(ModEntities.OWL, RenderOwl::new);

        // Block Entity Renderers
        BlockEntityRendererRegistry.register(ModBlockEntities.LOOT_CHEST, RenderLootChest::new);

        // Model Layers
        Models.init();

        // Render Layers
        EntityRendererRegistry.addRenderLayerToPlayer(renderLayerParent -> new PlayerGliderLayer(renderLayerParent));
        EntityRendererRegistry.addRenderLayerToPlayer(renderLayerParent -> new OwlShoulderLayer(renderLayerParent, Minecraft.getInstance().getEntityModels()));

        LifecycleEvents.CLIENT_SETUP.register(() -> {
            // Item Predicates
            for (RegistrySupplier<Item> supplier : ModItems.ITEMS) {
                if (supplier.get() instanceof ParagliderItem paragliderItem) {
                    ItemProperties.register(paragliderItem, new ResourceLocation("copper_mod"), (stack, p_call_2_, livingEntity, something) -> ParagliderItem.hasCopperMod(stack) ? 1 : 0);
                }
            }

            // Color Handlers
            for (RegistrySupplier<Item> entry : ModItems.ITEMS.getEntries()) {
                if (entry.get() instanceof TierArmorItem dyeableArmorItem) {
                    ColorHandlerRegistry.registerItemColors((itemStack, index) -> dyeableArmorItem.getTier().getArmorAction().getColor(), entry);
                }
            }
        });

    }

}