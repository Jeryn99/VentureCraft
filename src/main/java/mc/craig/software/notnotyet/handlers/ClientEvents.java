package mc.craig.software.notnotyet.handlers;

import mc.craig.software.notnotyet.NoNotYet;
import mc.craig.software.notnotyet.client.sound.GlideSound;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NoNotYet.MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onJoinWorld(EntityJoinLevelEvent event) {
        Entity living = event.getEntity();
        if (living instanceof Player player) {
            Minecraft.getInstance().getSoundManager().play(new GlideSound(player));
        }
    }

}
