package mc.craig.software.craftplus.client.renderers.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.craftplus.common.blockentity.PedastalBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;

public class RenderPedastal implements BlockEntityRenderer<PedastalBlockEntity>, BlockEntityRendererProvider<PedastalBlockEntity> {

    @Override
    public void render(PedastalBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        float floating = Mth.cos(Minecraft.getInstance().player.tickCount * 0.1F) * -0.09F;

        poseStack.translate(0.5, 1.4 + -floating, 0.5);
        poseStack.scale(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation());
        Minecraft.getInstance().getItemRenderer().renderStatic(blockEntity.getHeldItem(), ItemTransforms.TransformType.FIXED, packedLight, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, Minecraft.getInstance().player.getId());
        poseStack.popPose();
    }

    @Override
    public BlockEntityRenderer<PedastalBlockEntity> create(Context context) {
        return new RenderPedastal();
    }
}
