package mc.craig.software.craftplus.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.client.models.OwlModel;
import mc.craig.software.craftplus.common.entities.Owl;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OwlEyesLayer extends EyesLayer<Owl, OwlModel<Owl>> {
    private static final RenderType OWL_EYES = RenderType.eyes(new ResourceLocation(MinecraftPlus.MODID, "textures/entity/owl/owl_eyes.png"));

    public OwlEyesLayer(RenderLayerParent renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(PoseStack p_116983_, MultiBufferSource p_116984_, int p_116985_, Owl owl, float p_116987_, float p_116988_, float p_116989_, float p_116990_, float p_116991_, float p_116992_) {
       if (owl.isAngry()) {
            super.render(p_116983_, p_116984_, p_116985_, owl, p_116987_, p_116988_, p_116989_, p_116990_, p_116991_, p_116992_);
        }
    }

    @Override
    public RenderType renderType() {
        return OWL_EYES;
    }
}