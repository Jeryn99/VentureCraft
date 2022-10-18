package mc.craig.software.craftplus.client.renderers.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.client.models.BellModel;
import mc.craig.software.craftplus.client.models.Models;
import mc.craig.software.craftplus.common.block.BellBlock;
import mc.craig.software.craftplus.common.blockentity.BigBellBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;

public class RenderBell implements BlockEntityRenderer<BigBellBlockEntity>, BlockEntityRendererProvider<BigBellBlockEntity> {

    private final ResourceLocation BELL_TEXTURE = new ResourceLocation(VentureCraft.MODID, "textures/block_entity/big_bell.png");
    private final BellModel bellModel;

    public RenderBell(Context context) {
        bellModel = new BellModel(context.bakeLayer(Models.BELL));
    }

    @Override
    public void render(BigBellBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 1.5F, 0.5F); //Translate to blockpos
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180F)); // Make model not upside down
        BlockState blockState = blockEntity.getBlockState();
        float rotation = blockState.getValue(BellBlock.FACING).toYRot();
        poseStack.mulPose(Vector3f.YP.rotationDegrees(rotation));
        bellModel.animate(blockEntity);
        bellModel.renderToBuffer(poseStack, bufferSource.getBuffer(RenderType.entityCutoutNoCull(BELL_TEXTURE)), packedLight, packedOverlay, 1, 1, 1, 1);
        poseStack.popPose();
    }

    @Override
    public BlockEntityRenderer<BigBellBlockEntity> create(Context context) {
        return new RenderBell(context);
    }
}
