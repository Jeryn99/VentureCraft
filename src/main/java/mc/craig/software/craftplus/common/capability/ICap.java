package mc.craig.software.craftplus.common.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.AnimationState;
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

    boolean isFalling();
    void setFalling(boolean falling);

    AnimationState getAnimation(ModCapability.AnimationStates animationStates);

    void setStamina(int stamina);
    int getStamina();

    void setMaxStamina(int stamina);
    int getMaxStamina();
}
