package mc.craig.software.craftplus.common.entities.fabric;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import mc.craig.software.craftplus.common.entities.VenturePlayerData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Objects;
import java.util.Optional;

public class VenturePlayerDataImpl extends VenturePlayerData implements ComponentV3 {

    public VenturePlayerDataImpl(Player livingEntity) {
        super(livingEntity);
    }

    public static Optional<VenturePlayerData> get(LivingEntity player) {
        try {
            return Optional.of(VCComponents.PLAYER_DATA.get(player));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        this.deserializeNBT(tag);
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        CompoundTag nbt = this.serializeNBT();
        for (String key : nbt.getAllKeys()) {
            tag.put(key, Objects.requireNonNull(nbt.get(key)));
        }
    }
}
