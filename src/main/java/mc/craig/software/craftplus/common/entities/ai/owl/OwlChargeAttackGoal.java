package mc.craig.software.craftplus.common.entities.ai.owl;

import mc.craig.software.craftplus.common.entities.Owl;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class OwlChargeAttackGoal extends Goal {

    private final Owl owl;

    public OwlChargeAttackGoal(Owl owl) {
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        this.owl = owl;
    }

    @Override
    public boolean canUse() {
        LivingEntity livingentity = owl.getTarget();
        if (livingentity != null && livingentity.isAlive() && !owl.getMoveControl().hasWanted() && owl.level.random.nextInt(reducedTickDelay(7)) == 0) {
            return owl.distanceToSqr(livingentity) > 4.0D;
        } else {
            return false;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return owl.getMoveControl().hasWanted() && owl.getTarget() != null && owl.getTarget().isAlive() && owl.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
    }

    @Override
    public void start() {
        LivingEntity livingentity = owl.getTarget();
        if (livingentity != null) {
            Vec3 vec3 = livingentity.getEyePosition();
            owl.getMoveControl().setWantedPosition(vec3.x, vec3.y, vec3.z, 1.0D);
        }

        //owl.setIsCharging(true);
        owl.playSound(SoundEvents.VEX_CHARGE, 1.0F, 1.0F);
    }

    @Override
    public void stop() {
        //owl.setIsCharging(false);
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        LivingEntity livingentity = owl.getTarget();
        if (livingentity != null) {
            if (owl.getBoundingBox().intersects(livingentity.getBoundingBox())) {
                owl.doHurtTarget(livingentity);
                //owl.setIsCharging(false);
            } else {
                double d0 = owl.distanceToSqr(livingentity);
                if (d0 < 9.0D) {
                    Vec3 vec3 = livingentity.getEyePosition();
                    owl.getMoveControl().setWantedPosition(vec3.x, vec3.y, vec3.z, 1.0D);
                }
            }

        }
    }
}