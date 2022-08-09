package mc.craig.software.craftplus.client.renderers;

import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.client.models.Models;
import mc.craig.software.craftplus.client.models.OwlModel;
import mc.craig.software.craftplus.common.entities.OwlEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderOwl extends MobRenderer<OwlEntity, OwlModel<OwlEntity>> {

    private static final ResourceLocation TEX = new ResourceLocation(MinecraftPlus.MODID, "textures/entity/owl/owl.png");

    public RenderOwl(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new OwlModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(Models.OWL)), 1);
    }

    @Override
    public ResourceLocation getTextureLocation(OwlEntity p_114482_) {
        return TEX;
    }
}
