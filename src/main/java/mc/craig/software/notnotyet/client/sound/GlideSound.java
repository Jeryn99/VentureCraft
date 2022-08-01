package mc.craig.software.notnotyet.client.sound;

import mc.craig.software.notnotyet.util.GliderUtil;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public class GlideSound extends AbstractTickableSoundInstance {
    private final Player player;

    public GlideSound(Player p_119673_) {
        super(SoundEvents.ELYTRA_FLYING, SoundSource.PLAYERS, SoundInstance.createUnseededRandom());
        this.player = p_119673_;
        this.looping = true;
        this.delay = 0;
        this.volume = 0.1F;
    }

    @Override
    public void tick() {
        if (GliderUtil.isGliding(this.player)) {
            this.x = (float) this.player.getX();
            this.y = (float) this.player.getY();
            this.z = (float) this.player.getZ();
            volume = 0.1F;
        } else {
            volume = 0f;
        }
    }
}