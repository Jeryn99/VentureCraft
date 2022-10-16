package mc.craig.software.craftplus.client.renderers.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.client.models.ChestModel;
import mc.craig.software.craftplus.client.models.Models;
import mc.craig.software.craftplus.common.blockentity.LockedLootChestBlockEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class RenderLootChest implements BlockEntityRenderer<LockedLootChestBlockEntity>, BlockEntityRendererProvider<LockedLootChestBlockEntity> {

    private static final ResourceLocation GOLD_TEXTURE = new ResourceLocation(VentureCraft.MODID, "textures/block_entity/gold_loot_chest.png");
    private static ChestModel chestModel;

    public RenderLootChest(Context context) {
        chestModel = new ChestModel(context.bakeLayer(Models.CHEST));
    }

    @Override
    public void render(@NotNull LockedLootChestBlockEntity tileEntityIn, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 1.5F, 0.5F); //Translate to blockpos
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180F)); // Make model not upside down
        BlockState blockState = tileEntityIn.getBlockState();
        float rotation = blockState.getValue(ChestBlock.FACING).toYRot();
        poseStack.mulPose(Vector3f.YP.rotationDegrees(rotation));
        chestModel.animate(tileEntityIn);
        chestModel.renderToBuffer(poseStack, bufferIn.getBuffer(RenderType.entityCutout(getTextureForChest(tileEntityIn))), combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);
        poseStack.popPose();
    }

    private ResourceLocation getTextureForChest(LockedLootChestBlockEntity lootChestBlock) {
        return new ResourceLocation(VentureCraft.MODID, "textures/block_entity/" + lootChestBlock.getChestType().name().toLowerCase() + "_loot_chest.png");
    }


    @Override
    public @NotNull BlockEntityRenderer<LockedLootChestBlockEntity> create(@NotNull Context context) {
        return new RenderLootChest(context);
    }
}