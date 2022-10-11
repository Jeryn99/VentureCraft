package mc.craig.software.craftplus.client.renderers;

import mc.craig.software.craftplus.common.entities.projectile.AdvancedArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class RenderAdvancedArrow extends ArrowRenderer<AdvancedArrow> {
    public RenderAdvancedArrow(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(AdvancedArrow advancedArrow) {
        return null;
    }
}
