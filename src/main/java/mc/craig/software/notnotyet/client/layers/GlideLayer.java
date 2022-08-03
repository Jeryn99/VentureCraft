package mc.craig.software.notnotyet.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.notnotyet.client.models.GliderModel;
import mc.craig.software.notnotyet.client.models.Models;
import mc.craig.software.notnotyet.util.GliderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class GlideLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {

    public final GliderModel gliderModel;

    public GlideLayer(RenderLayerParent<T, M> p_117346_) {
        super(p_117346_);
        gliderModel = new GliderModel(Minecraft.getInstance().getEntityModels().bakeLayer(Models.GLIDER));
    }

    public static ResourceLocation getGliderTexture(ItemStack stack) {
        ResourceLocation itemLoc = ForgeRegistries.ITEMS.getKey(stack.getItem());
        return new ResourceLocation(itemLoc.getNamespace(), "textures/entity/glider/" + itemLoc.getPath() + ".png");
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource p_117350_, int p_117351_, T living, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
        if (living.isInvisibleTo(Minecraft.getInstance().player)) return;

        // Render above players when gliding
        if (GliderUtil.isGlidingWithActiveGlider(living)) {
            ItemStack stack = living.getItemBySlot(EquipmentSlot.CHEST);
            poseStack.pushPose();
            poseStack.translate(0, -1.8, 0);
            gliderModel.renderToBuffer(poseStack, p_117350_.getBuffer(RenderType.entityCutoutNoCull(getGliderTexture(stack))), p_117351_, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
            poseStack.popPose();
            return;
        }

     /*   // Render on players back
        if (GliderUtil.hasParagliderEquipped(living)) {
            poseStack.pushPose();
            poseStack.mulPose(Vector3f.XP.rotationDegrees(-90));
            poseStack.translate(0, -0.8, 0.5);
            poseStack.scale(0.8F, 0.8F,0.8F);
            //gliderModel.handles.visible = false;
            gliderModel.renderToBuffer(poseStack, p_117350_.getBuffer(RenderType.entityCutout(new ResourceLocation(NoNotYet.MODID, "textures/entity/glider/glider.png"))), p_117351_, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
            poseStack.popPose();
        }*/
    }

}
