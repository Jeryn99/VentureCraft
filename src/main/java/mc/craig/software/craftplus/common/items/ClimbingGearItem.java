package mc.craig.software.craftplus.common.items;

import mc.craig.software.craftplus.common.capability.ModCapability;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ClimbingGearItem extends ArmorItem {

    public ClimbingGearItem(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        super.onArmorTick(stack, level, player);

        boolean isPlayerCollided = player.horizontalCollision;
        ModCapability.get(player).ifPresent(iCap -> {
            if(!level.isClientSide()) {
                iCap.setClimbing(isPlayerCollided);
                Vec3 deltaMovement = player.getDeltaMovement();
                deltaMovement = new Vec3(deltaMovement.x, player.isCrouching() ? -0.1D : 0.1D, deltaMovement.z);
                float speed = player.isSprinting() ? 0.9F : 0.8F;
                player.setDeltaMovement(deltaMovement.multiply(speed, deltaMovement.y, speed));
            }
        });
    }
}
