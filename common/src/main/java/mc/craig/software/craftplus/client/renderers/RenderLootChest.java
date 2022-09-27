package mc.craig.software.craftplus.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.client.models.Models;
import mc.craig.software.craftplus.common.blockentity.LockedLootChestBlockEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RenderLootChest implements BlockEntityRenderer<LockedLootChestBlockEntity>, BlockEntityRendererProvider<LockedLootChestBlockEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(VentureCraft.MODID, "textures/block_entity/chest_gold.png");
    private static ModelPart chestModel;

    public RenderLootChest(Context context) {
        chestModel = context.bakeLayer(Models.CHEST);
    }

    @Override
    public void render(@NotNull LockedLootChestBlockEntity tileEntityIn, float partialTicks, @NotNull PoseStack matrixStack, @NotNull MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStack.pushPose();
        matrixStack.translate(0.5F, 1.5F, 0.5F); //Translate to blockpos
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180F)); // Make model not upside down
        chestModel.render(matrixStack, bufferIn.getBuffer(RenderType.entityCutout(TEXTURE)), combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);
        matrixStack.popPose();
    }


    @Override
    public @NotNull BlockEntityRenderer<LockedLootChestBlockEntity> create(@NotNull Context context) {
        return new RenderLootChest(context);
    }
}