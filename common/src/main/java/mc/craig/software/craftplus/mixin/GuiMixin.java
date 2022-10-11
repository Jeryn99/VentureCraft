package mc.craig.software.craftplus.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.craftplus.client.screen.HotbarRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import mc.craig.software.craftplus.client.overlay.VCOverlay;
import mc.craig.software.craftplus.util.GliderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {

    @Shadow
    protected int screenHeight;

    @Shadow
    protected int screenWidth;

    @Shadow
    @Final
    protected Minecraft minecraft;

    @Shadow
    protected abstract Player getCameraPlayer();

    @Shadow
    protected abstract void renderSlot(int pX, int pY, float pPartialTick, Player pPlayer, ItemStack pStack, int p_168683_);

    @Inject(at = @At(value = "HEAD"), method = "renderHotbar", cancellable = true)
    public void moveToLeft(float pPartialTick, PoseStack pPoseStack, CallbackInfo ci) {
        ci.cancel();
        Gui gui = (Gui) (Object) this;
        Player player = this.getCameraPlayer();
        HotbarRenderer.render(gui, this.minecraft, player, this.screenWidth, this.screenHeight, pPartialTick, pPoseStack, this::renderSlot);
    }

    @Inject(at = @At("HEAD"), cancellable = true, method = "renderExperienceBar(Lcom/mojang/blaze3d/vertex/PoseStack;I)V")
    private void stopXpBar(PoseStack poseStack, int xPos, CallbackInfo callbackInfo) {
        if (GliderUtil.isGlidingWithActiveGlider(Minecraft.getInstance().player)) {
            VCOverlay.renderAll(poseStack);
            callbackInfo.cancel();
        }
    }

}
