package mc.craig.software.craftplus.common;

import mc.craig.software.craftplus.VentureCraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;

import static mc.craig.software.craftplus.VentureCraft.MODID;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(MODID, Registry.SOUND_EVENT_REGISTRY);

    public static final RegistrySupplier<SoundEvent> GLIDER_OPEN = SOUNDS.register("glider_open", () -> setUpSound("glider_open"));
    public static final RegistrySupplier<SoundEvent> SPACE_GLIDE = SOUNDS.register("space_glide", () -> setUpSound("space_glide"));
    public static final RegistrySupplier<SoundEvent> SPACE_DEPLOY = SOUNDS.register("space_deploy", () -> setUpSound("space_deploy"));
    public static final RegistrySupplier<SoundEvent> OWL_HOOTS = SOUNDS.register("owl_hoots", () -> setUpSound("owl_hoots"));
    public static final RegistrySupplier<SoundEvent> OWL_HURT = SOUNDS.register("owl_hurt", () -> setUpSound("owl_hurt"));
    public static final RegistrySupplier<SoundEvent> STALKER_BREATH = SOUNDS.register("stalker_breath", () -> setUpSound("stalker_breath"));
    public static final RegistrySupplier<SoundEvent> STALKER_LAUGH = SOUNDS.register("stalker_laugh", () -> setUpSound("stalker_laugh"));
    public static final RegistrySupplier<SoundEvent> STALKER_MOVE = SOUNDS.register("stalker_move", () -> setUpSound("stalker_move"));
    public static final RegistrySupplier<SoundEvent> STALKER_STRIKE = SOUNDS.register("stalker_strike", () -> setUpSound("stalker_strike"));

    private static SoundEvent setUpSound(String soundName) {
        return new SoundEvent(new ResourceLocation(VentureCraft.MODID, soundName));
    }


}
