package mc.craig.software.craftplus.common.items;

import net.minecraft.world.entity.LivingEntity;

public interface ArmorAction {

    void tick(LivingEntity entity);
    void onAdded(LivingEntity entity);
    void onRemoved(LivingEntity entity);

    ArmorAction getInstance();

    int getColor();
}
