package mc.craig.software.craftplus.client.renderers;

import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.common.entities.Stalker;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderStalker extends LivingEntityRenderer<Stalker, PlayerModel<Stalker>> {

    public RenderStalker(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.01f);
    }

    @Override
    public ResourceLocation getTextureLocation(Stalker stalker) {
        //   return new ResourceLocation(MinecraftPlus.MODID, "textures/entity/stalker/stalker_" + stalker.getStalkerPose().getEmotion().id() + ".png");
        return new ResourceLocation(MinecraftPlus.MODID, "textures/entity/stalker/stalker.png");
    }

    @Override
    protected boolean shouldShowName(Stalker p_115333_) {
        return false;
    }
}
