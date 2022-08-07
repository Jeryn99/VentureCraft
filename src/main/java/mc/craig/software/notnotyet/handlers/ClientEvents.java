package mc.craig.software.notnotyet.handlers;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.notnotyet.MinecraftPlus;
import mc.craig.software.notnotyet.client.sound.GlideSound;
import mc.craig.software.notnotyet.common.capability.ModCapability;
import mc.craig.software.notnotyet.common.items.ParagliderItem;
import mc.craig.software.notnotyet.util.GliderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
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
        PoseStack posestack = event.getPoseStack();

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

        Window window = Minecraft.getInstance().getWindow();

        // Render Glider Duration
        if (itemStack.getItem() instanceof ParagliderItem paragliderItem && (GliderUtil.isGlidingWithActiveGlider(player) || player.isOnGround() && ParagliderItem.timeInAir(itemStack) < paragliderItem.getFixedFlightTimeTicks())) {
            if (e.getOverlay().id() == VanillaGuiOverlay.EXPERIENCE_BAR.id()) {
                e.setCanceled(true);
                return;
            }
            int allowedDuration = paragliderItem.getFixedFlightTimeTicks();
            int durationUsed = ParagliderItem.timeInAir(itemStack);
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
            minecraft.gui.blit(stack, winWid + 182 - status, winHeight, 182 - status, 69, status, 5);
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
