package mc.craig.software.craftplus.client.renderers;

import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.client.models.Models;
import mc.craig.software.craftplus.client.models.PlayerModelChanges;
import mc.craig.software.craftplus.common.entities.Stalker;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.resources.ResourceLocation;

public class RenderStalker extends LivingEntityRenderer<Stalker, PlayerModel<Stalker>> {

    public RenderStalker(EntityRendererProvider.Context context) {
        super(context, new PlayerModelChanges(context.bakeLayer(Models.PLAYER), true), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_SLIM_INNER_ARMOR)), new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_SLIM_OUTER_ARMOR))));
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
        this.addLayer(new ArrowLayer<>(context, this));
        this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
        this.addLayer(new ElytraLayer<>(this, context.getModelSet()));
        this.addLayer(new SpinAttackEffectLayer<>(this, context.getModelSet()));
        this.addLayer(new BeeStingerLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Stalker stalker) {
        return new ResourceLocation(MinecraftPlus.MODID, "textures/entity/stalker/stalker.png");
    }

    @Override
    protected boolean shouldShowName(Stalker stalker) {
        return false;
    }
}
