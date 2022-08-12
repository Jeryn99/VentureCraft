package mc.craig.software.craftplus.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.client.layers.OwlEyesLayer;
import mc.craig.software.craftplus.client.layers.OwlHeldItemLayer;
import mc.craig.software.craftplus.client.models.Models;
import mc.craig.software.craftplus.client.models.OwlModel;
import mc.craig.software.craftplus.common.entities.Owl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderOwl extends MobRenderer<Owl, OwlModel<Owl>> {

    public static final ResourceLocation[] OWL_LOCATIONS = new ResourceLocation[]{
            new ResourceLocation(MinecraftPlus.MODID, "textures/entity/owl/owl_0.png"),
            new ResourceLocation(MinecraftPlus.MODID, "textures/entity/owl/owl_1.png"),
            new ResourceLocation(MinecraftPlus.MODID, "textures/entity/owl/owl_2.png"),
            new ResourceLocation(MinecraftPlus.MODID, "textures/entity/owl/owl_3.png"),
            new ResourceLocation(MinecraftPlus.MODID, "textures/entity/owl/owl_4.png"),
            new ResourceLocation(MinecraftPlus.MODID, "textures/entity/owl/owl_5.png"),
            new ResourceLocation(MinecraftPlus.MODID, "textures/entity/owl/owl_6.png")};

    public RenderOwl(EntityRendererProvider.Context context) {
        super(context, new OwlModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(Models.OWL)), 0.2F);
        addLayer(new OwlHeldItemLayer(this, context.getItemInHandRenderer()));
        addLayer(new OwlEyesLayer(this));
    }

    @Override
    protected void setupRotations(Owl owl, PoseStack poseStack, float p_115687_, float p_115688_, float p_115689_) {
        super.setupRotations(owl, poseStack, p_115687_, p_115688_, p_115689_);
        poseStack.scale(1.4F,1.4F,1.4F);
        poseStack.mulPose(Vector3f.XP.rotationDegrees(owl.isFlying() ? -45 : owl.getXRot()));
    }

    @Override
    public ResourceLocation getTextureLocation(Owl owl) {
        return OWL_LOCATIONS[owl.getVariant()];
    }

}
