package mc.craig.software.craftplus.common.entities;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.animal.ShoulderRidingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class OwlEntity extends ShoulderRidingEntity implements FlyingAnimal {


    public OwlEntity(EntityType<? extends ShoulderRidingEntity> shoulder, Level level) {
        super(shoulder, level);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    public boolean isFlying() {
        return !this.onGround;
    }
}
