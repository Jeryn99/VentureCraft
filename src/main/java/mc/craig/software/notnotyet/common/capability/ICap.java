package mc.craig.software.notnotyet.common.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.util.INBTSerializable;

public interface ICap extends INBTSerializable<CompoundTag> {

    void tick(LivingEntity livingEntity);
    boolean isClimbing();
    void setClimbing(boolean climbing);
    int timeClimbed();

    void setTimeClimbed(int time);

    boolean canClimb(LivingEntity livingEntity);

    void sync();

}
