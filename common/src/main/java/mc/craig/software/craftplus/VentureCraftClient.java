package mc.craig.software.craftplus;

import mc.craig.software.craftplus.client.layers.OwlShoulderLayer;
import mc.craig.software.craftplus.client.layers.PlayerGliderLayer;
import mc.craig.software.craftplus.client.models.Models;
import mc.craig.software.craftplus.client.renderers.blockentity.RenderBell;
import mc.craig.software.craftplus.client.renderers.blockentity.RenderCatalyst;
import mc.craig.software.craftplus.client.renderers.blockentity.RenderLootChest;
import mc.craig.software.craftplus.client.renderers.blockentity.RenderPedastal;
import mc.craig.software.craftplus.client.renderers.entity.RenderAdvancedArrow;
import mc.craig.software.craftplus.client.renderers.entity.RenderOwl;
import mc.craig.software.craftplus.client.renderers.entity.RenderStalker;
import mc.craig.software.craftplus.common.ModBlockEntities;
import mc.craig.software.craftplus.common.ModEntities;
import mc.craig.software.craftplus.common.ModItems;
import mc.craig.software.craftplus.common.items.ParagliderItem;
import mc.craig.software.craftplus.common.items.TierArmorItem;
import mc.craig.software.craftplus.handlers.ClientEvents;
import mc.craig.software.craftplus.util.ClientUtil;
import net.minecraft.client.Minecraft;
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
        // Event Handler
        ClientEvents.init();

        // Entity Renderers
        EntityRendererRegistry.register(ModEntities.STALKER, RenderStalker::new);
        EntityRendererRegistry.register(ModEntities.ADVANCED_ARROW, RenderAdvancedArrow::new);
        EntityRendererRegistry.register(ModEntities.OWL, RenderOwl::new);

        // Block Entity Renderers
        BlockEntityRendererRegistry.register(ModBlockEntities.LOOT_CHEST, RenderLootChest::new);
        BlockEntityRendererRegistry.register(ModBlockEntities.PEDASTAL, new RenderPedastal());
        BlockEntityRendererRegistry.register(ModBlockEntities.CATALYST, RenderCatalyst::new);
        BlockEntityRendererRegistry.register(ModBlockEntities.BELL, RenderBell::new);

        // Model Layers
        Models.init();

        // Render Layers
        EntityRendererRegistry.addRenderLayerToPlayer(renderLayerParent -> new PlayerGliderLayer(renderLayerParent));
        EntityRendererRegistry.addRenderLayerToPlayer(renderLayerParent -> new OwlShoulderLayer(renderLayerParent, Minecraft.getInstance().getEntityModels()));

        LifecycleEvents.CLIENT_SETUP.register(() -> {
            // Item Predicates
            for (RegistrySupplier<Item> supplier : ModItems.ITEMS) {
                if (supplier.get() instanceof ParagliderItem paragliderItem) {
                    ClientUtil.addPredicate(paragliderItem, new ResourceLocation("copper_mod"), (stack, p_call_2_, livingEntity, something) -> ParagliderItem.hasCopperMod(stack) ? 1 : 0);
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
