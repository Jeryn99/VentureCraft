package mc.craig.software.craftplus;

import mc.craig.software.craftplus.common.*;
import mc.craig.software.craftplus.common.advancement.TriggerManager;
import mc.craig.software.craftplus.common.command.SkillPointsCommand;
import mc.craig.software.craftplus.common.level.ModOres;
import mc.craig.software.craftplus.handlers.CommonEvents;
import mc.craig.software.craftplus.networking.VCNetwork;
import net.minecraft.resources.ResourceLocation;
import net.threetag.palladiumcore.event.CommandEvents;
import net.threetag.palladiumcore.event.LifecycleEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VentureCraft {

    public static final String MODID = "venturecraft";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static void init() {
        ModItems.ITEMS.register();
        ModBlocks.BLOCKS.register();
        ModEntities.ENTITY_TYPES.register();
        ModSounds.SOUNDS.register();
        ModOres.PLACED_FEATURES.register();
        ModOres.CONFIGURED_FEATURES.register();
        ModBlockEntities.TILES.register();
        VCNetwork.init();
        TriggerManager.init();
        ModEntities.initAttributes();
        CommonEvents.init();

        LifecycleEvents.SETUP.register(ModEntities::initSpawns);
        CommandEvents.REGISTER.register((dispatcher, selection) -> SkillPointsCommand.register(dispatcher));
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }

}
