package mc.craig.software.craftplus.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.craftplus.VentureCraft;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;

import java.util.Objects;

public class ExtendedInventoryScreen extends AbstractContainerScreen<InventoryMenu> {

    public static final ResourceLocation TEXTURE = VentureCraft.id("textures/gui/inventory.png");

    protected final RandomSource random = RandomSource.create();
    protected int tickCount;
    protected int lastHealth;
    protected int displayHealth;
    protected long lastHealthTime;
    protected long healthBlinkTime;

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
    protected void containerTick() {
        super.containerTick();
        this.tickCount++;
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

        // Upgrades
        int upgrades = (Minecraft.getInstance().player.tickCount / 20) % 4;
        for (int u = 0; u < upgrades; u++) {
            this.blit(pPoseStack, i + 69 - (u * 18), j + 216, u == 0 ? 0 : 50, 108, 25, 85);
            this.blit(pPoseStack, i + 292 + (u * 18), j + 216, u == 0 ? 25 : 75, 108, 25, 85);
        }

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

        // Health
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_ICONS_LOCATION);
        this.renderHealth(pPoseStack);
    }

    public void renderHealth(PoseStack poseStack) {
        var screenHeight = this.minecraft.getWindow().getGuiScaledHeight();
        var player = Minecraft.getInstance().player;

        if (player != null) {
            int i = Mth.ceil(player.getHealth());
            boolean flag = this.healthBlinkTime > (long) this.tickCount && (this.healthBlinkTime - (long) this.tickCount) / 3L % 2L == 1L;
            long j = Util.getMillis();
            if (i < this.lastHealth && player.invulnerableTime > 0) {
                this.lastHealthTime = j;
                this.healthBlinkTime = this.tickCount + 20;
            } else if (i > this.lastHealth && player.invulnerableTime > 0) {
                this.lastHealthTime = j;
                this.healthBlinkTime = this.tickCount + 10;
            }

            if (j - this.lastHealthTime > 1000L) {
                this.displayHealth = i;
                this.lastHealthTime = j;
            }

            this.lastHealth = i;
            int k = this.displayHealth;
            this.random.setSeed(this.tickCount * 312871L);
            int x = this.leftPos + (this.imageWidth / 2) - 50;
            int k1 = this.topPos + 151;
            float f = Math.max((float) player.getAttributeValue(Attributes.MAX_HEALTH), (float) Math.max(k, i));
            int l1 = Mth.ceil(player.getAbsorptionAmount());
            int i2 = Mth.ceil((f + (float) l1) / 2.0F / 10.0F);
            int j2 = Math.max(10 - (i2 - 2), 3);
            int j3 = -1;
            if (player.hasEffect(MobEffects.REGENERATION)) {
                j3 = this.tickCount % Mth.ceil(f + 5.0F);
            }

            this.renderHearts(poseStack, player, x, k1, j2, j3, f, i, k, l1, flag);
        }
    }

    private int getVisibleVehicleHeartRows(int mountHealth) {
        return (int) Math.ceil((double) mountHealth / 10.0);
    }

    protected void renderHearts(PoseStack poseStack, Player player, int x, int y, int height, int p, float f, int q, int r, int s, boolean bl) {
        Gui.HeartType heartType = Gui.HeartType.forPlayer(player);
        int hardcoreOffset = 9 * (player.level.getLevelData().isHardcore() ? 5 : 0);
        int j = Mth.ceil((double) f / 2.0);
        int k = Mth.ceil((double) s / 2.0);
        int l = j * 2;

        for (int i1 = j + k - 1; i1 >= 0; --i1) {
            int j1 = i1 / 10;
            int k1 = i1 % 10;
            int heartX = x + k1 * 10;
            int heartY = y - j1 * height;
            if (q + s <= 4) {
                heartY += this.random.nextInt(2);
            }

            if (i1 < j && i1 == p) {
                heartY -= 2;
            }

            this.renderHeart(poseStack, Gui.HeartType.CONTAINER, heartX, heartY, hardcoreOffset, bl, false);
            int j2 = i1 * 2;
            boolean flag = i1 >= j;
            if (flag) {
                int k2 = j2 - l;
                if (k2 < s) {
                    boolean flag1 = k2 + 1 == s;
                    this.renderHeart(poseStack, heartType == Gui.HeartType.WITHERED ? heartType : Gui.HeartType.ABSORBING, heartX, heartY, hardcoreOffset, false, flag1);
                }
            }

            if (bl && j2 < r) {
                boolean flag2 = j2 + 1 == r;
                this.renderHeart(poseStack, heartType, heartX, heartY, hardcoreOffset, true, flag2);
            }

            if (j2 < q) {
                boolean flag3 = j2 + 1 == q;
                this.renderHeart(poseStack, heartType, heartX, heartY, hardcoreOffset, false, flag3);
            }
        }
    }

    private void renderHeart(PoseStack poseStack, Gui.HeartType heartType, int x, int y, int k, boolean bl, boolean bl2) {
        this.blit(poseStack, x, y, heartType.getX(bl2, bl), k, 9, 9);
    }

}
