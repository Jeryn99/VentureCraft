package mc.craig.software.craftplus.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.craftplus.client.models.OwlModel;
import mc.craig.software.craftplus.common.entities.Owl;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class OwlHeldItemLayer extends RenderLayer<Owl, OwlModel<Owl>> {

    private final ItemInHandRenderer itemInHandRenderer;

    public OwlHeldItemLayer(RenderLayerParent<Owl, OwlModel<Owl>> renderLayerParent, ItemInHandRenderer itemInHandRenderer) {
        super(renderLayerParent);
        this.itemInHandRenderer = itemInHandRenderer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, Owl owl, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
        poseStack.pushPose();
        ItemStack itemstack = owl.getItemBySlot(EquipmentSlot.MAINHAND);
        getParentModel().leftLeg.translateAndRotate(poseStack);
        poseStack.translate(0,0.8,1.1 - 0.01);
        this.itemInHandRenderer.renderItem(owl, itemstack, ItemTransforms.TransformType.GROUND, false, poseStack, multiBufferSource, light);
        poseStack.popPose();
    }
}
