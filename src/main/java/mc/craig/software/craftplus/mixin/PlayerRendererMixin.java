package mc.craig.software.craftplus.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.craftplus.util.AnimationUtil;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {

    @Inject(at = @At("HEAD"), cancellable = true, method = "setupRotations(Lnet/minecraft/client/player/AbstractClientPlayer;Lcom/mojang/blaze3d/vertex/PoseStack;FFF)V")
    private void setupRotations(AbstractClientPlayer abstractClientPlayer, PoseStack poseStack, float p_117804_, float p_117805_, float p_117806_, CallbackInfo callbackInfo) {
        AnimationUtil.setupRotations(abstractClientPlayer, poseStack, p_117804_, p_117805_, p_117806_, callbackInfo);
    }
}
