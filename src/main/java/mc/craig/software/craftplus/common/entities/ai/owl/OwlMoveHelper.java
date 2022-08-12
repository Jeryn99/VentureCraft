package mc.craig.software.craftplus.common.entities.ai.owl;

import mc.craig.software.craftplus.common.entities.Owl;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;

import static net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED;

public class OwlMoveHelper extends MoveControl {

    private Owl mob;

    private double maxDescentSpeed = 0.1;

    public OwlMoveHelper(Owl owl) {
        super(owl);
        this.mob = owl;
    }

    @Override
    public void tick() {
        if (mob.getNavigation().getPath() != null) {
            double xDelta = wantedX - mob.getX();
            double yDelta = wantedY - mob.getY();
            double zDelta = wantedZ - mob.getZ();

            float moveFactor = 1;
            float moveSpeed = (float) (speedModifier * mob.getAttributeValue(MOVEMENT_SPEED));
            mob.setSpeed(mob.getSpeed() + (moveSpeed - mob.getSpeed()) * moveFactor);

            double distSq = xDelta * xDelta + yDelta * yDelta + zDelta * zDelta;
            double dist = Mth.sqrt((float) distSq);
            yDelta = yDelta / dist;
            if (yDelta > 0) {
                yDelta = Math.max(0.1, yDelta);
            }
            double yMove = mob.getSpeed() * yDelta;
            Vec3 movement = mob.getDeltaMovement();
            mob.setDeltaMovement(movement.x, movement.y + yMove, movement.z);
            if (!mob.isDeadOrDying() && !mob.isOnGround() && movement.y() < -maxDescentSpeed) {
                mob.setDeltaMovement(movement.x, -maxDescentSpeed, movement.z);
            }
            double d7 = mob.getX() + (xDelta / dist * 2.0D);
            double d8 = mob.getEyeHeight() + mob.getY() + (yDelta / dist * 1.0D);
            double d9 = mob.getZ() + (zDelta / dist * 2.0D);

            LookControl entitylookhelper = mob.getLookControl();
            double lookX = entitylookhelper.getWantedX();
            double lookY = entitylookhelper.getWantedY();
            double lookZ = entitylookhelper.getWantedZ();

            if (!entitylookhelper.isLookingAtTarget()) {
                lookX = d7;
                lookY = d8;
                lookZ = d9;
            }
            mob.getLookControl().setLookAt(lookX + (d7 - lookX) * 0.125D, lookY + (d8 - lookY) * 0.125D, lookZ + (d9 - lookZ) * 0.125D, 10.0F, 40.0F);
        } else {
            mob.setSpeed(0.0F);
        }
    }

}