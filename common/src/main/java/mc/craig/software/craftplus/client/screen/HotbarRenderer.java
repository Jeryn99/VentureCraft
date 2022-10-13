package mc.craig.software.craftplus.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.AttackIndicatorStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class HotbarRenderer {

    public static void render(Gui gui, Minecraft minecraft, Player player, int screenWidth, int screenHeight, float pPartialTick, PoseStack pPoseStack, RenderSlot renderSlot) {
        if (player != null) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, AbstractWidget.WIDGETS_LOCATION);
            ItemStack offHandItem = player.getOffhandItem();
            HumanoidArm humanoidarm = player.getMainArm().getOpposite();
            int i = screenWidth / 2;
            int j = gui.getBlitOffset();
            int k = 182;
            int leftShift = 111;
            gui.setBlitOffset(-90);
            gui.blit(pPoseStack, i - leftShift, screenHeight - 22, 0, 0, 182, 22);
            gui.blit(pPoseStack, i - leftShift + 181, screenHeight - 22, 141, 0, 41, 22);
            gui.blit(pPoseStack, i - leftShift - 1 + player.getInventory().selected * 20, screenHeight - 22 - 1, 0, 22, 24, 22);
            if (!offHandItem.isEmpty()) {
                if (humanoidarm == HumanoidArm.LEFT) {
                    gui.blit(pPoseStack, i - leftShift - 29, screenHeight - 23, 24, 22, 29, 24);
                } else {
                    gui.blit(pPoseStack, i + leftShift, screenHeight - 23, 53, 22, 29, 24);
                }
            }

            gui.setBlitOffset(j);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            int i1 = 1;

            for (int j1 = 0; j1 < Inventory.getSelectionSize(); ++j1) {
                int k1 = i - 110 + j1 * 20 + 2;
                int l1 = screenHeight - 16 - 3;
                renderSlot.renderSlot(k1, l1, pPartialTick, player, player.getInventory().items.get(j1), i1++);
            }

            if (!offHandItem.isEmpty()) {
                int j2 = screenHeight - 16 - 3;
                if (humanoidarm == HumanoidArm.LEFT) {
                    renderSlot.renderSlot(i - leftShift - 26, j2, pPartialTick, player, offHandItem, i1++);
                } else {
                    renderSlot.renderSlot(i + leftShift + 10, j2, pPartialTick, player, offHandItem, i1++);
                }
            }

            if (minecraft.options.attackIndicator().get() == AttackIndicatorStatus.HOTBAR) {
                float f = minecraft.player.getAttackStrengthScale(0.0F);
                if (f < 1.0F) {
                    int k2 = screenHeight - 20;
                    int l2 = i + 91 + 6;
                    if (humanoidarm == HumanoidArm.RIGHT) {
                        l2 = i - 91 - 22;
                    }

                    RenderSystem.setShaderTexture(0, GuiComponent.GUI_ICONS_LOCATION);
                    int i2 = (int) (f * 19.0F);
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    gui.blit(pPoseStack, l2, k2, 0, 94, 18, 18);
                    gui.blit(pPoseStack, l2, k2 + 18 - i2, 18, 112 - i2, 18, i2);
                }
            }

            RenderSystem.disableBlend();
        }
    }

    public interface RenderSlot {

        void renderSlot(int pX, int pY, float pPartialTick, Player pPlayer, ItemStack pStack, int p_168683_);

    }

}
