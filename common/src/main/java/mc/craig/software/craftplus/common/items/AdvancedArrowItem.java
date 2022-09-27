package mc.craig.software.craftplus.common.items;

import mc.craig.software.craftplus.common.ModEntities;
import mc.craig.software.craftplus.common.entities.projectile.AdvancedArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AdvancedArrowItem extends ArrowItem {

    public AdvancedArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack itemStack, LivingEntity livingEntity) {
        return new AdvancedArrow(ModEntities.ADVANCED_ARROW.get(), level);
    }

}
