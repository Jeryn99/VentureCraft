package mc.craig.software.craftplus.client.sound;

import mc.craig.software.craftplus.util.GliderUtil;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public class GlideSound extends AbstractTickableSoundInstance {
    private final Player player;

    public GlideSound(Player player) {
        super(SoundEvents.ELYTRA_FLYING, SoundSource.PLAYERS, SoundInstance.createUnseededRandom());
        this.player = player;
        this.looping = true;
        this.delay = 0;
        this.volume = 0.1F;
    }

    @Override
    public void tick() {
        if (GliderUtil.isGlidingWithActiveGlider(this.player) && player.tickCount > 20) {
            this.x = (float) this.player.getX();
            this.y = (float) this.player.getY();
            this.z = (float) this.player.getZ();
            volume = 0.1F;
        } else {
            volume = 0f;
        }
    }
}