package mc.craig.software.craftplus.handlers;

import mc.craig.software.craftplus.client.layers.GlideLayer;
import mc.craig.software.craftplus.client.models.Models;
import mc.craig.software.craftplus.client.renderers.RenderOwl;
import mc.craig.software.craftplus.client.renderers.RenderStalker;
import mc.craig.software.craftplus.common.Entities;
import mc.craig.software.craftplus.common.items.ParagliderItem;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusClientEvents {

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Entities.STALKER.get(), RenderStalker::new);
        event.registerEntityRenderer(Entities.OWL.get(), RenderOwl::new);
    }

    @SubscribeEvent
    public static void regModels(EntityRenderersEvent.RegisterLayerDefinitions definitions) {
        Models.init(definitions);
    }

    @SubscribeEvent
    public static void renderLayers(EntityRenderersEvent.AddLayers addLayers) {
        addLayers.getSkins().forEach(skin -> {
            LivingEntityRenderer<? extends Player, ? extends EntityModel<? extends Player>> renderer = addLayers.getSkin(skin);
            renderer.addLayer(new GlideLayer(renderer));
        });
    }

    @SubscribeEvent
    public static void doClientStuff(FMLClientSetupEvent event) {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ModBusClientEvents::itemPredicates);
    }

    private static void itemPredicates() {
        for (Map.Entry<ResourceKey<Item>, Item> entry : ForgeRegistries.ITEMS.getEntries()) {
            if (entry.getValue() instanceof ParagliderItem paragliderItem) {
                ItemProperties.register(paragliderItem, new ResourceLocation("copper_mod"), (stack, p_call_2_, livingEntity, something) -> ParagliderItem.hasCopperMod(stack) ? 1 : 0);
            }
        }
    }

}
