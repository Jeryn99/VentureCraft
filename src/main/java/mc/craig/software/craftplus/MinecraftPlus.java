package mc.craig.software.craftplus;

import mc.craig.software.craftplus.common.Blocks;
import mc.craig.software.craftplus.common.Entities;
import mc.craig.software.craftplus.common.ModItems;
import mc.craig.software.craftplus.common.ModSounds;
import mc.craig.software.craftplus.common.capability.ICap;
import mc.craig.software.craftplus.common.entities.OwlEntity;
import mc.craig.software.craftplus.common.entities.StalkerEntity;
import mc.craig.software.craftplus.data.*;
import mc.craig.software.craftplus.networking.Network;
import net.minecraft.data.DataGenerator;
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
        Blocks.BLOCKS.register(modBus);
        Entities.ENTITY_TYPES.register(modBus);
        ModSounds.SOUNDS.register(modBus);

        modBus.addListener(this::onAttributeAssign);
        modBus.addListener(this::onGatherData);
        modBus.addListener(this::onAddCaps);

    }

    public void onAddCaps(RegisterCapabilitiesEvent capabilitiesEvent) {
        capabilitiesEvent.register(ICap.class);
    }

    public void onAttributeAssign(EntityAttributeCreationEvent event) {
        event.put(Entities.STALKER.get(), StalkerEntity.createAttributes().build());
        event.put(Entities.OWL.get(), OwlEntity.createAttributes().build());
    }

    public void onGatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        ExistingFileHelper existingFileHelper = e.getExistingFileHelper();
        generator.addProvider(true, new LangProviderEnglish(generator));
        generator.addProvider(true, new ModelProviderItem(generator, existingFileHelper));
        generator.addProvider(true, new RecipeProvider(generator));
        generator.addProvider(true, new SoundProvider(generator, existingFileHelper));
        generator.addProvider(true, new ModLootTableProvider(generator));
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        Network.init();
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

}
