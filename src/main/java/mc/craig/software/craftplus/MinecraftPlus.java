package mc.craig.software.craftplus;

import mc.craig.software.craftplus.common.ModBlocks;
import mc.craig.software.craftplus.common.ModEntities;
import mc.craig.software.craftplus.common.ModItems;
import mc.craig.software.craftplus.common.ModSounds;
import mc.craig.software.craftplus.common.capability.ICap;
import mc.craig.software.craftplus.common.entities.Owl;
import mc.craig.software.craftplus.common.entities.Stalker;
import mc.craig.software.craftplus.common.level.ModOres;
import mc.craig.software.craftplus.data.*;
import mc.craig.software.craftplus.networking.Network;
import mc.craig.software.craftplus.util.ModSpawningRules;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(MinecraftPlus.MODID)
public class MinecraftPlus {
    public static final String MODID = "minecraft_plus";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public MinecraftPlus() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        ModItems.ITEMS.register(modBus);
        ModBlocks.BLOCKS.register(modBus);
        ModEntities.ENTITY_TYPES.register(modBus);
        ModSounds.SOUNDS.register(modBus);
        ModOres.PLACED_FEATURES.register(modBus);
        ModOres.CONFIGURED_FEATURES.register(modBus);

        modBus.addListener(this::onAttributeAssign);
        modBus.addListener(this::onGatherData);
        modBus.addListener(this::onAddCaps);

    }

    public void onAddCaps(RegisterCapabilitiesEvent capabilitiesEvent) {
        capabilitiesEvent.register(ICap.class);
    }

    public void onAttributeAssign(EntityAttributeCreationEvent event) {
        event.put(ModEntities.STALKER.get(), Stalker.createAttributes().build());
        event.put(ModEntities.OWL.get(), Owl.createAttributes().build());
        SpawnPlacements.register(ModEntities.OWL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, ModSpawningRules::checkOwlSpawnRules);

    }

    public void onGatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        ExistingFileHelper existingFileHelper = e.getExistingFileHelper();
        generator.addProvider(true, new LangProviderEnglish(generator));
        generator.addProvider(true, new ModelProviderItem(generator, existingFileHelper));
        generator.addProvider(true, new RecipeProvider(generator));
        generator.addProvider(true, new SoundProvider(generator, existingFileHelper));
        generator.addProvider(true, new ModLootTableProvider(generator));
        generator.addProvider(true, new ItemTagsProvider(generator, Registry.ITEM, existingFileHelper));
        generator.addProvider(true, new BlockTagsProvider(generator, Registry.BLOCK, existingFileHelper));
        generator.addProvider(true, new BiomeTagsProvider(generator, BuiltinRegistries.BIOME, existingFileHelper));
        generator.addProvider(true, new EntityTypeTagsProvider(generator, Registry.ENTITY_TYPE, existingFileHelper));
        generator.addProvider(true, new BiomeModifierProvider(generator));
        generator.addProvider(true, new ModelProviderBlock(generator, existingFileHelper));
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        Network.init();
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

}
