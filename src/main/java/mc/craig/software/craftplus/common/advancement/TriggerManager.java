package mc.craig.software.craftplus.common.advancement;

import net.minecraft.advancements.CriteriaTriggers;

public class TriggerManager {

    public static final BaseTrigger FIRST_TIME_FLYER = new BaseTrigger("first_time_flyer");
    public static final BaseTrigger COPPER_MODDER = new BaseTrigger("copper_modder");
    public static final BaseTrigger GLIDER_LIGHTNING = new BaseTrigger("glider_lightning");

    public static void init() {
        CriteriaTriggers.register(FIRST_TIME_FLYER);
        CriteriaTriggers.register(COPPER_MODDER);
        CriteriaTriggers.register(GLIDER_LIGHTNING);
    }

}