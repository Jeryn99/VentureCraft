package mc.craig.software.craftplus.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.client.layers.OwlEyesLayer;
import mc.craig.software.craftplus.client.layers.OwlHeldItemLayer;
import mc.craig.software.craftplus.client.models.Models;
import mc.craig.software.craftplus.client.models.OwlModel;
import mc.craig.software.craftplus.common.entities.Owl;
import mc.craig.software.craftplus.common.entities.ai.owl.OwlTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class RenderOwl extends MobRenderer<Owl, OwlModel<Owl>> {

    public RenderOwl(EntityRendererProvider.Context context) {
        super(context, new OwlModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(Models.OWL)), 0.2F);
        addLayer(new OwlHeldItemLayer(this, context.getItemInHandRenderer()));
        addLayer(new OwlEyesLayer(this));
    }

    public static ResourceLocation getTextureLocationFromInt(int owl) {
        return new ResourceLocation(VentureCraft.MODID, "textures/entity/owl/" + OwlTypes.values()[owl].name().toLowerCase() + ".png");
    }

    @Override
    public void render(Owl owl, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int packedLightIn) {
        super.render(owl, entityYaw, partialTicks, matrixStack, bufferIn, packedLightIn);
    }

    @Override
    protected void setupRotations(Owl owl, PoseStack poseStack, float p_115687_, float p_115688_, float p_115689_) {
        super.setupRotations(owl, poseStack, p_115687_, p_115688_, p_115689_);
        poseStack.scale(1.4F, 1.4F, 1.4F);
        poseStack.mulPose(Vector3f.XP.rotationDegrees(owl.isFlying() ? -45 : owl.getXRot()));
    }

    @Override
    public ResourceLocation getTextureLocation(Owl owl) {
        return new ResourceLocation(VentureCraft.MODID, "textures/entity/owl/" + OwlTypes.values()[Mth.clamp(owl.getVariant() - 1, 0, OwlTypes.values().length)].name().toLowerCase() + ".png");
    }

}
