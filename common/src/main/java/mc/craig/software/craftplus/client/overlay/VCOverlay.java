package mc.craig.software.craftplus.client.overlay;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.common.entities.VenturePlayerData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;

public class VCOverlay {


    public static final ResourceLocation WIDGET_BAR = new ResourceLocation(VentureCraft.MODID, "textures/gui/bar_widget.png");



    public static void renderAll(PoseStack poseStack){
        Window window = Minecraft.getInstance().getWindow();
        LocalPlayer player = Minecraft.getInstance().player;

        VenturePlayerData.get(player).ifPresent(venturePlayerData -> {
            // Render Stamina Duration
            if (!player.isCreative()) {
                int allowedDuration = venturePlayerData.getMaxStamina();
                int durationUsed = venturePlayerData.getStamina();
                float progress = (float) durationUsed / allowedDuration;
                renderDurationBar(poseStack, window, progress);
            }
        });
    }


    public static void renderDurationBar(PoseStack stack, Window window, float progress) {
        Minecraft minecraft = Minecraft.getInstance();
        stack.pushPose();
        RenderSystem.setShaderTexture(0, WIDGET_BAR);
        int winWid = window.getGuiScaledWidth() / 2 - 91;
        int winHeight = window.getGuiScaledHeight() - 32 + 3;
        minecraft.gui.blit(stack, winWid, winHeight, 0, 64, 182, 5);
        int status = (int) (progress * 182F);
        if (status > 0) {
            minecraft.gui.blit(stack, winWid, winHeight, 0, 69, status, 5);
        }
        stack.popPose();
    }

}
