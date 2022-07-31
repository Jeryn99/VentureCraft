package mc.craig.software.notnotyet.common.entities;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.BodyRotationControl;

public class LockBodyRotation extends BodyRotationControl {

    private final Mob mob;

    public LockBodyRotation(Mob mob) {
        super(mob);
        this.mob = mob;
    }

    @Override
    public void clientTick() {
        if (mob instanceof QuantumLockedLifeform weepingAngel) {
            if (!weepingAngel.isSeen()) {
                super.clientTick();
            }
        }
    }


}