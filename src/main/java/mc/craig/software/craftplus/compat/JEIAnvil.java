package mc.craig.software.craftplus.compat;

import mc.craig.software.craftplus.MinecraftPlus;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IAdvancedRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import mezz.jei.common.plugins.vanilla.anvil.AnvilRecipe;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.Collections;

@JeiPlugin
public class JEIAnvil implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MinecraftPlus.MODID, "anvil");
    }

}
