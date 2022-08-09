package mc.craig.software.craftplus.client.models;

import mc.craig.software.craftplus.MinecraftPlus;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class Models {

    public static ModelLayerLocation STALKER = new ModelLayerLocation(new ResourceLocation(MinecraftPlus.MODID, "model"), "stalker");
    public static ModelLayerLocation GLIDER = new ModelLayerLocation(new ResourceLocation(MinecraftPlus.MODID, "model"), "glider");
    public static ModelLayerLocation OWL = new ModelLayerLocation(new ResourceLocation(MinecraftPlus.MODID, "model"), "owl");
    public static ModelLayerLocation X_WING = new ModelLayerLocation(new ResourceLocation(MinecraftPlus.MODID, "model"), "x_wing");

    public static void init(EntityRenderersEvent.RegisterLayerDefinitions registerLayerDefinitions) {
        registerLayerDefinitions.registerLayerDefinition(STALKER, StalkerModel::getModelData);
        registerLayerDefinitions.registerLayerDefinition(GLIDER, GliderModel::getModelData);
        registerLayerDefinitions.registerLayerDefinition(OWL, OwlModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(X_WING, XWingModel::createBodyLayer);
    }

}
