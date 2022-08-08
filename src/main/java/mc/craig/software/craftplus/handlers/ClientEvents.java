package mc.craig.software.craftplus.handlers;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.client.layers.GlideLayer;
import mc.craig.software.craftplus.client.sound.GlideSound;
import mc.craig.software.craftplus.common.capability.ModCapability;
import mc.craig.software.craftplus.common.items.ParagliderItem;
import mc.craig.software.craftplus.util.GliderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinecraftPlus.MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onJoinWorld(EntityJoinLevelEvent event) {
        Entity living = event.getEntity();
        if (living instanceof Player player) {
            Minecraft.getInstance().getSoundManager().play(new GlideSound(player));
        }
    }



    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent event) {

        LocalPlayer living = Minecraft.getInstance().player;
        ItemStack stack = living.getItemBySlot(EquipmentSlot.CHEST);

        PoseStack posestack = event.getPoseStack();
        if (stack.getItem() instanceof ParagliderItem && GliderUtil.isGlidingWithActiveGlider(living)) {
            event.setCanceled(true);
            posestack.pushPose();
            posestack.translate(0, 2.2, -0.5);
            posestack.scale(1.5F, 1.5F, 1.5F);
            posestack.mulPose(Vector3f.XP.rotationDegrees(180));

            GlideLayer.gliderModel.setupAnim(living, 0, 0, living.tickCount, 0, 0);
            GlideLayer.gliderModel.renderToBuffer(posestack, event.getMultiBufferSource().getBuffer(RenderType.entityCutoutNoCull(GlideLayer.getGliderTexture(stack))), event.getPackedLight(), OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
            posestack.popPose();
        }
    }

    @SubscribeEvent
    public static void onMovement(MovementInputUpdateEvent event){
        if(GliderUtil.isGlidingWithActiveGlider(Minecraft.getInstance().player)){
            event.getInput().shiftKeyDown = false;
        }
    }


    public static final ResourceLocation TEX = new ResourceLocation(MinecraftPlus.MODID, "textures/gui/bar_widget.png");

    @SubscribeEvent
    public static void onRenderGameOverlayPre(RenderGuiOverlayEvent.Pre e) {

        PoseStack stack = e.getPoseStack();
        LocalPlayer player = Minecraft.getInstance().player;
        ItemStack itemStack = player.getItemBySlot(EquipmentSlot.CHEST);

        ModCapability.get(player).ifPresent(iCap -> {

            Window window = Minecraft.getInstance().getWindow();

            // Render Glider Duration
            if (itemStack.getItem() instanceof ParagliderItem paragliderItem && (GliderUtil.isGlidingWithActiveGlider(player) || !GliderUtil.isGlidingWithActiveGlider(player) && iCap.getStamina() > 0)) {
                if (e.getOverlay().id() == VanillaGuiOverlay.EXPERIENCE_BAR.id()) {
                    e.setCanceled(true);
                    return;
                }
                int allowedDuration = iCap.getMaxStamina();
                int durationUsed = iCap.getStamina();
                float progress = (float) durationUsed / allowedDuration;
                renderDurationBar(stack, window, progress);
            }

            // Climbing
            ModCapability.get(player).ifPresent(iClimb -> {
                if (iClimb.isClimbing()) {
                    int allowedDuration = ModCapability.MAX_CLIMB_TIME;
                    int durationUsed = iClimb.timeClimbed();
                    float progress = (float) durationUsed / allowedDuration;
                    renderDurationBar(stack, window, progress);
                }
            });

        });
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
