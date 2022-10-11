package mc.craig.software.craftplus.client.models;

import mc.craig.software.craftplus.VentureCraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.threetag.palladiumcore.registry.client.EntityRendererRegistry;

public class Models {

    public static ModelLayerLocation GLIDER = new ModelLayerLocation(new ResourceLocation(VentureCraft.MODID, "model"), "glider");
    public static ModelLayerLocation OWL = new ModelLayerLocation(new ResourceLocation(VentureCraft.MODID, "model"), "owl");
    public static ModelLayerLocation X_WING = new ModelLayerLocation(new ResourceLocation(VentureCraft.MODID, "model"), "x_wing");
    public static ModelLayerLocation PLAYER = new ModelLayerLocation(new ResourceLocation(VentureCraft.MODID, "model"), "player");
    public static ModelLayerLocation CHEST = new ModelLayerLocation(new ResourceLocation(VentureCraft.MODID, "model"), "chest");

    public static void init() {
        EntityRendererRegistry.registerModelLayer(GLIDER, GliderModel::getModelData);
        EntityRendererRegistry.registerModelLayer(OWL, OwlModel::createBodyLayer);
        EntityRendererRegistry.registerModelLayer(X_WING, XWingModel::createBodyLayer);
        EntityRendererRegistry.registerModelLayer(PLAYER, PlayerModelChanges::createBodyLayer);
        EntityRendererRegistry.registerModelLayer(CHEST, ChestModel::getModelData);
    }

}
