package mc.craig.software.craftplus.common.entities.projectile;

import mc.craig.software.craftplus.common.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class AdvancedArrow extends AbstractArrow {


    protected AdvancedArrow(EntityType<? extends AbstractArrow> abstractArrow, double p_36712_, double p_36713_, double p_36714_, Level level) {
        super(abstractArrow, p_36712_, p_36713_, p_36714_, level);
    }

    public AdvancedArrow(EntityType<? extends AbstractArrow> abstractArrow, LivingEntity livingEntity, Level level) {
        super(abstractArrow, livingEntity, level);
    }

    public AdvancedArrow(EntityType<AdvancedArrow> advancedArrowEntityType, Level level) {
        super(advancedArrowEntityType, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.ADVANCED_ARROW.get());
    }

    @Override
    public double getBaseDamage() {
        return 4;
    }

}
