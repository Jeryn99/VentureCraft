package mc.craig.software.notnotyet.handlers;

import mc.craig.software.notnotyet.client.layers.GlideLayer;
import mc.craig.software.notnotyet.client.models.Models;
import mc.craig.software.notnotyet.client.renderers.RenderStalker;
import mc.craig.software.notnotyet.common.Entities;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusClientEvents {

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Entities.STALKER.get(), RenderStalker::new);
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

}
