package mc.craig.software.craftplus.client.renderers.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.client.models.CatalystModel;
import mc.craig.software.craftplus.client.models.Models;
import mc.craig.software.craftplus.common.blockentity.CatalystBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RenderCatalyst implements BlockEntityRenderer<CatalystBlockEntity>, BlockEntityRendererProvider<CatalystBlockEntity> {

    private static CatalystModel catalystModel;

    public RenderCatalyst(Context context) {
        catalystModel = new CatalystModel(context.bakeLayer(Models.CATALYST_MODEL));
    }

    @Override
    public void render(@NotNull CatalystBlockEntity tileEntityIn, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 1.5F, 0.5F); //Translate to blockpos
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180F)); // Make model not upside down
        catalystModel.animate(tileEntityIn);
        catalystModel.renderToBuffer(poseStack, bufferIn.getBuffer(RenderType.entityCutout(getTexture(tileEntityIn))), combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);
        poseStack.popPose();
    }

    private ResourceLocation getTexture(CatalystBlockEntity lootChestBlock) {
        return new ResourceLocation(VentureCraft.MODID, "textures/block_entity/catalyst.png");
    }


    @Override
    public @NotNull BlockEntityRenderer<CatalystBlockEntity> create(@NotNull Context context) {
        return new RenderCatalyst(context);
    }
}