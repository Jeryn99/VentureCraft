package mc.craig.software.craftplus.handlers;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.util.GliderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.resources.ResourceLocation;


public class ClientEvents  {


    public static int lightLevel = 0;


/*    @SubscribeEvent
    public static void onRenderLevelLast(RenderLevelStageEvent event) {


        RenderBuffers bufferSource = Minecraft.getInstance().renderBuffers();

        LocalPlayer living = Minecraft.getInstance().player;
        ItemStack stack = living.getItemBySlot(EquipmentSlot.CHEST);

        PoseStack posestack = event.getPoseStack();
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS && Minecraft.getInstance().options.getCameraType() == CameraType.FIRST_PERSON && stack.getItem() instanceof ParagliderItem && GliderUtil.isGlidingWithActiveGlider(living)) {
            posestack.pushPose();
            posestack.mulPose(Vector3f.XP.rotationDegrees(180));
            posestack.mulPose(Vector3f.YP.rotationDegrees(living.getViewYRot(1F)));


            posestack.translate(0, -2.4, -0.5);
            posestack.scale(1.5F, 1.5F, 1.5F);

            if(ParagliderItem.isSpaceGlider(stack)) {
                posestack.translate(0, -0.2, 0);
                PlayerGliderLayer.xWingModel.setupAnim(living, 0, 0, living.tickCount, 0, 0);
                PlayerGliderLayer.xWingModel.renderToBuffer(posestack, bufferSource.bufferSource().getBuffer(RenderType.entityCutoutNoCull(PlayerGliderLayer.getGliderTexture(stack))), lightLevel, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
            } else {
                PlayerGliderLayer.gliderModel.setupAnim(living, 0, 0, living.tickCount, 0, 0);
                PlayerGliderLayer.gliderModel.renderToBuffer(posestack, bufferSource.bufferSource().getBuffer(RenderType.entityCutoutNoCull(PlayerGliderLayer.getGliderTexture(stack))), lightLevel, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
            }
            posestack.popPose();
        }
    }*/

/*
    @SubscribeEvent
    public static void onMovement(MovementInputUpdateEvent event){
        if(GliderUtil.isGlidingWithActiveGlider(Minecraft.getInstance().player)){
            event.getInput().shiftKeyDown = false;
        }
    }
*/





}
