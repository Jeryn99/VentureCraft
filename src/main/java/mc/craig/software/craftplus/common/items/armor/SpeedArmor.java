package mc.craig.software.craftplus.common.items.armor;

import mc.craig.software.craftplus.common.items.ArmorAction;
import net.minecraft.world.entity.LivingEntity;

public class SpeedArmor implements ArmorAction {

    private static SpeedArmor INSTANCE = new SpeedArmor();

    @Override
    public void tick(LivingEntity entity) {

    }

    @Override
    public void onAdded(LivingEntity entity) {

    }

    @Override
    public void onRemoved(LivingEntity entity) {

    }

    @Override
    public ArmorAction getInstance() {
        return INSTANCE;
    }
}
