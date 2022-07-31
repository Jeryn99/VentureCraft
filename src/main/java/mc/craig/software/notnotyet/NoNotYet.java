package mc.craig.software.notnotyet;

import com.mojang.logging.LogUtils;
import mc.craig.software.notnotyet.common.Blocks;
import mc.craig.software.notnotyet.common.Entities;
import mc.craig.software.notnotyet.common.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
@Mod(NoNotYet.MODID)
public class NoNotYet {
    public static final String MODID = "nonotyet";
    private static final Logger LOGGER = LogUtils.getLogger();

    public NoNotYet() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        Items.ITEMS.register(modBus);
        Blocks.BLOCKS.register(modBus);
        Entities.ENTITY_TYPES.register(modBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

}
