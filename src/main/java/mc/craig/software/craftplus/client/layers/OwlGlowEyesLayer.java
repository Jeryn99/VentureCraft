package mc.craig.software.craftplus.client.layers;

import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.client.models.OwlModel;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OwlGlowEyesLayer extends EyesLayer {
    private static final RenderType OWL_EYES = RenderType.eyes(new ResourceLocation(MinecraftPlus.MODID, "textures/entity/owl/eye_glow.png"));

    public OwlGlowEyesLayer(RenderLayerParent p_117507_) {
        super(p_117507_);
    }

    public RenderType renderType() {
        return OWL_EYES;
    }
}