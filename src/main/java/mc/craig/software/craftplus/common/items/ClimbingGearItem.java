package mc.craig.software.craftplus.common.items;

import mc.craig.software.craftplus.common.capability.ModCapability;
import net.minecraft.core.BlockPos;
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

        BlockPos pos = player.blockPosition().relative(player.getDirection(), 1);

        boolean isPlayerCollided = !level.getBlockState(pos).isAir();
        ModCapability.get(player).ifPresent(iCap -> {
            if (!level.isClientSide()) {
                iCap.setClimbing(isPlayerCollided);

                if (isPlayerCollided) {
                    iCap.setStamina(iCap.getStamina() - 1);
                }
                iCap.sync();
            }

            if (isPlayerCollided && iCap.getStamina() > 0) {
                Vec3 deltaMovement = player.getDeltaMovement();
                deltaMovement = new Vec3(deltaMovement.x, player.isCrouching() ? -0.2D : 0.2D, deltaMovement.z);
                float speed = player.isSprinting() ? 0.9F : 0.8F;
                player.setDeltaMovement(deltaMovement.multiply(speed, deltaMovement.y, speed));
            }
        });
    }
}
