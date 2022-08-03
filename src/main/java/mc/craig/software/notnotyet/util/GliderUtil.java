package mc.craig.software.notnotyet.util;

import mc.craig.software.notnotyet.common.items.ParagliderItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class GliderUtil {

    public static boolean hasParagliderEquipped(LivingEntity livingEntity) {
        return livingEntity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ParagliderItem;
    }

    public static boolean isGliderActive(LivingEntity livingEntity) {
        ItemStack stack = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
        if (stack.getItem() instanceof ParagliderItem) {
            return ParagliderItem.glidingEnabled(stack);
        }
        return false;
    }

    public static boolean isPlayerOnGroundOrWater(LivingEntity livingEntity) {
        return livingEntity.isOnGround() || livingEntity.isInWater();
    }

    public static boolean isGlidingWithActiveGlider(LivingEntity livingEntity) {

        if (livingEntity instanceof Player player) {
            if (player.getAbilities().flying) {
                return false;
            }
        }

        return hasParagliderEquipped(livingEntity) && isGliderActive(livingEntity) && !livingEntity.isOnGround() && !livingEntity.isInWater();
    }

}
