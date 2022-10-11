package mc.craig.software.craftplus.forge;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.VentureCraftClient;
import mc.craig.software.craftplus.data.forge.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.threetag.palladiumcore.util.Platform;

@Mod(VentureCraft.MODID)
@Mod.EventBusSubscriber(modid = VentureCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VentureCraftForge {

    public VentureCraftForge() {
        VentureCraft.init();

        if (Platform.isClient()) {
            VentureCraftClient.init();
        }
    }

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        ExistingFileHelper existingFileHelper = e.getExistingFileHelper();

        generator.addProvider(e.includeClient(), new LangProviderEnglish(generator));
        generator.addProvider(e.includeClient(), new ModelProviderItem(generator, existingFileHelper));
        generator.addProvider(e.includeClient(), new ModelProviderBlock(generator, existingFileHelper));
        generator.addProvider(e.includeClient(), new SoundProvider(generator, existingFileHelper));

        BlockTagsProvider blockTagsProvider = new BlockTagsProvider(generator, existingFileHelper);
        generator.addProvider(e.includeServer(), new RecipeProvider(generator));
        generator.addProvider(e.includeServer(), new ModLootTableProvider(generator));
        generator.addProvider(e.includeServer(), blockTagsProvider);
        generator.addProvider(e.includeServer(), new ItemTagsProvider(generator, blockTagsProvider, existingFileHelper));
        generator.addProvider(e.includeServer(), new BiomeTagsProvider(generator, existingFileHelper));
        generator.addProvider(e.includeServer(), new EntityTypeTagsProvider(generator, existingFileHelper));
        generator.addProvider(e.includeServer(), new BiomeModifierProvider(generator));
        generator.addProvider(e.includeServer(), new AdvancementsProvider(generator));
    }

}
