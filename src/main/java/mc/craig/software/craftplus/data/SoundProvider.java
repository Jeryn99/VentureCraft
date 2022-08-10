package mc.craig.software.craftplus.data;

import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.common.ModSounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class SoundProvider extends SoundDefinitionsProvider {
    /**
     * Creates a new instance of this data provider.
     *
     * @param generator The data generator instance provided by the event you are initializing this provider in.
     * @param helper    The existing file helper provided by the event you are initializing this provider in.
     */
    public SoundProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, MinecraftPlus.MODID, helper);
    }

    @Override
    public void registerSounds() {
        createDefinitionAndAdd(ModSounds.GLIDER_OPEN.get(), SoundDefinition.SoundType.SOUND, "glider_open", new SoundEvent(new ResourceLocation(MinecraftPlus.MODID, "glider_open_0")), new SoundEvent(new ResourceLocation(MinecraftPlus.MODID, "glider_open_1")));
        createDefinitionAndAdd(ModSounds.SPACE_GLIDE.get(), SoundDefinition.SoundType.SOUND);
        createDefinitionAndAdd(ModSounds.SPACE_DEPLOY.get(), SoundDefinition.SoundType.SOUND);
    }

    public void createDefinitionAndAdd(SoundEvent mainSound, SoundDefinition.SoundType soundType, String subtitle, SoundEvent... soundEvent) {
        SoundDefinition def = SoundDefinition.definition().subtitle("subtitle." + MinecraftPlus.MODID + "." + subtitle);
        for (SoundEvent event : soundEvent) {
            def.with(SoundDefinition.Sound.sound(event.getLocation(), soundType));
        }
        add(mainSound, def);
    }

    public void createDefinitionAndAdd(SoundEvent soundEvent, SoundDefinition.SoundType soundType) {
        SoundDefinition def = SoundDefinition.definition().subtitle("subtitle." + MinecraftPlus.MODID + "." + soundEvent.getLocation().getPath());
        def.with(SoundDefinition.Sound.sound(soundEvent.getLocation(), soundType));
        add(soundEvent, def);
    }
}
