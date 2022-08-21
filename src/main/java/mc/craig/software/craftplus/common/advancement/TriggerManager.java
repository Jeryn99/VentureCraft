package mc.craig.software.craftplus.common.advancement;

import net.minecraft.advancements.CriteriaTriggers;

public class TriggerManager {

    public static final BaseTrigger FIRST_TIME_FLYER = new BaseTrigger("first_time_flyer");

    public static void init() {
        CriteriaTriggers.register(FIRST_TIME_FLYER);
    }

}