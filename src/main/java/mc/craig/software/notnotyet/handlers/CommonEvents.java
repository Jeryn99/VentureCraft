package mc.craig.software.notnotyet.handlers;

import mc.craig.software.notnotyet.util.GliderUtil;
import mc.craig.software.notnotyet.util.Utils;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommonEvents {

    //TOOD TESTING ONLY
    public static AnimationState glideAnimation = new AnimationState();



/*
    @SubscribeEvent
    public static void onTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (GliderUtil.isGlidingWithActiveGlider(player)) {
                if (player.tickCount % 5 == 0) {
                    glideAnimation.start(player.tickCount);
                }
            } else {
                glideAnimation.stop();
            }
        }
    }
*/


    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {

    }

    @SubscribeEvent
    public static void onUseItem(LivingEntityUseItemEvent useItemEvent) {
        if (GliderUtil.isGlidingWithActiveGlider(useItemEvent.getEntity())) {
            useItemEvent.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent attackEvent) {
        if (attackEvent.getSource().getEntity() instanceof LivingEntity livingEntity)
            if (GliderUtil.isGlidingWithActiveGlider(livingEntity)) {
                attackEvent.setCanceled(true);
            }
    }

}
