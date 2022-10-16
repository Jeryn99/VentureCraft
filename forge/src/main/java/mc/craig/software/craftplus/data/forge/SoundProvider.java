package mc.craig.software.craftplus.data.forge;

import mc.craig.software.craftplus.VentureCraft;
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
        super(generator, VentureCraft.MODID, helper);
    }

    @Override
    public void registerSounds() {
        createDefinitionAndAdd(ModSounds.GLIDER_OPEN.get(), SoundDefinition.SoundType.SOUND, "glider_open", "glider/glider_open_0", "glider/glider_open_1");
        createDefinitionAndAdd(ModSounds.SPACE_GLIDE.get(), SoundDefinition.SoundType.SOUND, "space_glide", "glider/space_glide");
        createDefinitionAndAdd(ModSounds.SPACE_DEPLOY.get(), SoundDefinition.SoundType.SOUND, "space_deploy", "glider/space_deploy");
        createDefinitionAndAdd(ModSounds.OWL_HOOTS.get(), SoundDefinition.SoundType.SOUND, "owl_hoots", "owl/owl_single_hoot", "owl/owl_double_hoot");
        createDefinitionAndAdd(ModSounds.OWL_HURT.get(), SoundDefinition.SoundType.SOUND, "owl_hurt", "owl/owl_hurt");
        createDefinitionAndAdd(ModSounds.STALKER_BREATH.get(), SoundDefinition.SoundType.SOUND, "stalker_breath", "stalker/stalker_breath");
        createDefinitionAndAdd(ModSounds.STALKER_LAUGH.get(), SoundDefinition.SoundType.SOUND, "stalker_laugh", "stalker/stalker_giggle_1", "stalker/stalker_giggle_2", "stalker/stalker_giggle_3");
        createDefinitionAndAdd(ModSounds.STALKER_MOVE.get(), SoundDefinition.SoundType.SOUND, "stalker_move", "stalker/stalker_glide_1", "stalker/stalker_glide_2", "stalker/stalker_glide_3", "stalker/stalker_glide_4");
        createDefinitionAndAdd(ModSounds.STALKER_STRIKE.get(), SoundDefinition.SoundType.SOUND, "stalker_strike", "stalker/stalker_striking");

    }

    public void createDefinitionAndAdd(SoundEvent mainSound, SoundDefinition.SoundType soundType, String subtitle, String... soundEvent) {
        SoundDefinition def = SoundDefinition.definition().subtitle("subtitle." + VentureCraft.MODID + "." + subtitle);
        for (String event : soundEvent) {
            def.with(SoundDefinition.Sound.sound(new ResourceLocation(VentureCraft.MODID, event), soundType));
        }
        add(mainSound, def);
    }

}
