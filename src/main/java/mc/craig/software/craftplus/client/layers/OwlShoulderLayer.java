package mc.craig.software.craftplus.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mc.craig.software.craftplus.client.models.Models;
import mc.craig.software.craftplus.client.models.OwlModel;
import mc.craig.software.craftplus.client.renderers.RenderOwl;
import mc.craig.software.craftplus.common.Entities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ParrotRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OwlShoulderLayer<T extends Player> extends RenderLayer<T, PlayerModel<T>> {
    private final OwlModel model;

    public OwlShoulderLayer(RenderLayerParent<T, PlayerModel<T>> renderLayerParent, EntityModelSet p_174512_) {
        super(renderLayerParent);
        this.model = new OwlModel(p_174512_.bakeLayer(Models.OWL));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int p_117309_, T living, float p_117311_, float p_117312_, float p_117313_, float p_117314_, float p_117315_, float p_117316_) {
        this.render(poseStack, multiBufferSource, p_117309_, living, p_117311_, p_117312_, p_117315_, p_117316_, true);
        this.render(poseStack, multiBufferSource, p_117309_, living, p_117311_, p_117312_, p_117315_, p_117316_, false);
    }

    private void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int p_117320_, T living, float p_117322_, float p_117323_, float p_117324_, float p_117325_, boolean p_117326_) {
        CompoundTag compoundtag = p_117326_ ? living.getShoulderEntityLeft() : living.getShoulderEntityRight();
        EntityType.byString(compoundtag.getString("id")).filter((p_117294_) -> p_117294_ == Entities.OWL.get()).ifPresent((p_117338_) -> {
            poseStack.pushPose();
            poseStack.translate(p_117326_ ? (double)0.4F : (double)-0.4F, living.isCrouching() ? (double)-1.3F : -1.5D, 0.0D);
            VertexConsumer vertexconsumer = multiBufferSource.getBuffer(this.model.renderType(RenderOwl.OWL_LOCATIONS[compoundtag.getInt("Variant")]));
            this.model.renderOnShoulder(poseStack, vertexconsumer, p_117320_, OverlayTexture.NO_OVERLAY, getParentModel());
            poseStack.popPose();
        });
    }
}