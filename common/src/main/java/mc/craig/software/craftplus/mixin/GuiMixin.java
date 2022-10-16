package mc.craig.software.craftplus.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.craftplus.client.overlay.VCOverlay;
import mc.craig.software.craftplus.util.GliderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

    @Inject(at = @At("HEAD"), cancellable = true, method = "renderExperienceBar(Lcom/mojang/blaze3d/vertex/PoseStack;I)V")
    private void stopXpBar(PoseStack poseStack, int xPos, CallbackInfo callbackInfo) {
        if (GliderUtil.isGlidingWithActiveGlider(Minecraft.getInstance().player)) {
            VCOverlay.renderAll(poseStack);
            callbackInfo.cancel();
        }
    }

}
