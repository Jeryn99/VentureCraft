package mc.craig.software.craftplus.handlers;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.client.layers.PlayerGliderLayer;
import mc.craig.software.craftplus.common.items.ParagliderItem;
import mc.craig.software.craftplus.util.GliderUtil;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = VentureCraft.MODID, value = Dist.CLIENT)
public class ClientEvents {


    private static int lightLevel;
    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent event) {
        lightLevel = event.getPackedLight(); // My precious little hack, my precious
        Entity camera = Minecraft.getInstance().getCameraEntity();
        if(camera instanceof AbstractClientPlayer livingEntity) {
            if (GliderUtil.isGlidingWithActiveGlider(livingEntity)) {
                event.setCanceled(true);
                ItemInHandRenderer handRender = Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer();
              //  handRender.renderArmWithItem(livingEntity, 0, 0, InteractionHand.MAIN_HAND, 0, ItemStack.EMPTY, 0, event.getPoseStack(), event.getMultiBufferSource(), lightLevel);
              //  handRender.renderArmWithItem(livingEntity, 0, 0, InteractionHand.OFF_HAND, 0, ItemStack.EMPTY, 0, event.getPoseStack(), event.getMultiBufferSource(), lightLevel);

            }
        }
    }


    @SubscribeEvent
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
    }

    @SubscribeEvent
    public static void onMovement(MovementInputUpdateEvent event){
        if(GliderUtil.isGlidingWithActiveGlider(Minecraft.getInstance().player)){
            event.getInput().shiftKeyDown = false;
        }
    }


    public static final ResourceLocation TEX = new ResourceLocation(VentureCraft.MODID, "textures/gui/bar_widget.png");

    @SubscribeEvent
    public static void onRenderGameOverlayPre(RenderGuiOverlayEvent.Pre e) {

        PoseStack stack = e.getPoseStack();
        LocalPlayer player = Minecraft.getInstance().player;
        ItemStack itemStack = player.getItemBySlot(EquipmentSlot.CHEST);

        if (e.getOverlay().id() == VanillaGuiOverlay.EXPERIENCE_BAR.id()) {
            e.setCanceled(true);
            ModCapability.get(player).ifPresent(iCap -> {

                Window window = Minecraft.getInstance().getWindow();

                // Render Stamina Duration
                if (!player.isCreative()) {
                    int allowedDuration = iCap.getMaxStamina();
                    int durationUsed = iCap.getStamina();
                    float progress = (float) durationUsed / allowedDuration;
                    renderDurationBar(stack, window, progress);
                }
            });
        }
    }

    public static void renderDurationBar(PoseStack stack, Window window, float progress) {
        Minecraft minecraft = Minecraft.getInstance();
        stack.pushPose();
        RenderSystem.setShaderTexture(0, TEX);
        int winWid = window.getGuiScaledWidth() / 2 - 91;
        int winHeight = window.getGuiScaledHeight() - 32 + 3;
        minecraft.gui.blit(stack, winWid, winHeight, 0, 64, 182, 5);
        int status = (int) (progress * 182F);
        if (status > 0) {
            minecraft.gui.blit(stack, winWid, winHeight, 0, 69, status, 5);
        }
        stack.popPose();
    }

    public static void drawStringWithOutline(PoseStack stack, String string, int posX, int posY, int fontColor, int outlineColor) {
        Minecraft mc = Minecraft.getInstance();
        mc.font.draw(stack, string, posX + 1, posY, outlineColor);
        mc.font.draw(stack, string, posX - 1, posY, outlineColor);
        mc.font.draw(stack, string, posX, posY + 1, outlineColor);
        mc.font.draw(stack, string, posX, posY - 1, outlineColor);
        mc.font.draw(stack, string, posX, posY, fontColor);
    }


}
