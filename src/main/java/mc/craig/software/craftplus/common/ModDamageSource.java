package mc.craig.software.craftplus.common;

import mc.craig.software.craftplus.MinecraftPlus;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class ModDamageSource extends DamageSource {

    public static ModDamageSource OWL_CLAWS = new ModDamageSource("owl_claws");
    public static ModDamageSource LIGHTNING_GLIDER = new ModDamageSource("lightning_glider");
    public static ModDamageSource STATUE = new ModDamageSource("statue");

    private String message = "";

    public ModDamageSource(String damageTypeIn) {
        super(damageTypeIn);
        message = damageTypeIn;
    }

    @Override
    public @NotNull Component getLocalizedDeathMessage(LivingEntity entityLivingBaseIn) {
        return Component.translatable(MinecraftPlus.MODID +".source." + message, entityLivingBaseIn.getName());
    }


    public String getKey(){
        return MinecraftPlus.MODID +".source." + message;
    }


}
