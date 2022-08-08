package mc.craig.software.craftplus.client.renderers;

import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.client.models.Models;
import mc.craig.software.craftplus.client.models.StalkerModel;
import mc.craig.software.craftplus.common.entities.StalkerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderStalker extends LivingEntityRenderer<StalkerEntity, StalkerModel> {

    public RenderStalker(EntityRendererProvider.Context context) {
        super(context, new StalkerModel(context.bakeLayer(Models.STALKER)), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(StalkerEntity stalkerEntity) {
       return new ResourceLocation(MinecraftPlus.MODID, "textures/entity/stalker/stalker_"+stalkerEntity.getStalkerPose().getEmotion().id()+".png");
    }
}
