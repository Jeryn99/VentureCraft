package mc.craig.software.craftplus.common;

import mc.craig.software.craftplus.MinecraftPlus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static mc.craig.software.craftplus.MinecraftPlus.MODID;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);

    public static final RegistryObject<SoundEvent> GLIDER_OPEN = SOUNDS.register("glider_open", () -> setUpSound("glider_open"));
    public static final RegistryObject<SoundEvent> SPACE_GLIDE = SOUNDS.register("space_glide", () -> setUpSound("space_glide"));
    public static final RegistryObject<SoundEvent> SPACE_DEPLOY = SOUNDS.register("space_deploy", () -> setUpSound("space_deploy"));
    public static final RegistryObject<SoundEvent> OWL_HOOTS = SOUNDS.register("owl_hoots", () -> setUpSound("owl_hoots"));
    public static final RegistryObject<SoundEvent> OWL_HURT = SOUNDS.register("owl_hurt", () -> setUpSound("owl_hurt"));

    private static SoundEvent setUpSound(String soundName) {
        return new SoundEvent(new ResourceLocation(MinecraftPlus.MODID, soundName));
    }


}
