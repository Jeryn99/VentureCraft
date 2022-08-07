package mc.craig.software.notnotyet.client.models;

import mc.craig.software.notnotyet.MinecraftPlus;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class Models {

    public static ModelLayerLocation STALKER = new ModelLayerLocation(new ResourceLocation(MinecraftPlus.MODID, "model"), "stalker");
    public static ModelLayerLocation GLIDER = new ModelLayerLocation(new ResourceLocation(MinecraftPlus.MODID, "model"), "glider");

    public static void init(EntityRenderersEvent.RegisterLayerDefinitions registerLayerDefinitions) {
        registerLayerDefinitions.registerLayerDefinition(STALKER, StalkerModel::getModelData);
        registerLayerDefinitions.registerLayerDefinition(GLIDER, GliderModel::getModelData);
    }

}
