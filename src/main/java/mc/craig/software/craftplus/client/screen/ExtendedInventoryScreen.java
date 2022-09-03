package mc.craig.software.craftplus.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.craftplus.MinecraftPlus;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;

import java.util.Objects;

public class ExtendedInventoryScreen extends AbstractContainerScreen<InventoryMenu> {

    public static final ResourceLocation TEXTURE = MinecraftPlus.id("textures/gui/inventory.png");

    public ExtendedInventoryScreen(Player player) {
        super(player.inventoryMenu, player.getInventory(), Component.translatable("container.crafting"));
        this.imageWidth = 388;
        this.imageHeight = 332;
        this.titleLabelY = -100;
        this.inventoryLabelY = -100;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pPoseStack, pMouseX, pMouseY);

//        for (Slot slot : this.menu.slots) {
//            this.font.draw(pPoseStack, slot.index + "", this.leftPos + slot.x, this.topPos + slot.y, 0xfefefe);
//        }
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = this.leftPos;
        int j = this.topPos;

        // Main Inv
        this.blit(pPoseStack, i + 87, j + 216, 0, 0, 212, 108);

        // Slots around player
        for (int k = 0; k < 4; k++) {
            this.blit(pPoseStack, i + 110, j + k * 36, 212, 0, 28, 28);
            this.blit(pPoseStack, i + 250, j + k * 36, 212, 0, 28, 28);
        }

        // Triple Slot Thingies
        this.blit(pPoseStack, i + 16, j, 0, 193, 68, 28);
        this.blit(pPoseStack, i + 304, j, 0, 193, 68, 28);

        // Skill XP
        this.blit(pPoseStack, i, j + 31, 100, 154, 84, 46);

        // Crafting
        this.blit(pPoseStack, i + 304, j + 31, 100, 108, 84, 46);

        // Player
        int xPlayer = this.imageWidth / 2;
        int yPlayer = 140;
        InventoryScreen.renderEntityInInventory(i + xPlayer, j + yPlayer, 80, (float) (i + xPlayer) - pMouseX, (float) (j + yPlayer - 18) - pMouseY, Objects.requireNonNull(this.minecraft.player));
    }
}
