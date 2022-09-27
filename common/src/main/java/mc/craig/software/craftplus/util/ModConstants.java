package mc.craig.software.craftplus.util;

import mc.craig.software.craftplus.VentureCraft;
import net.minecraft.resources.ResourceLocation;

public class ModConstants {

    // ResourceLocations
    public static final ResourceLocation CAPABILITY_ID = new ResourceLocation(VentureCraft.MODID, "capability");

    // Tooltip
    public static final String INSTALLED_MODS = createToolTip("installed_mods");
    public static final String COPPER_MOD = createToolTip("copper_mod");

    // Messages


    public static String createToolTip(String id){
        return "tooltip." + VentureCraft.MODID + "." + id;
    }

    public static String createMessage(String id){
        return "message." + VentureCraft.MODID + "." + id;
    }

}
