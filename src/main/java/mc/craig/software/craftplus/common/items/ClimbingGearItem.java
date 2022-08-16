package mc.craig.software.craftplus.common.items;

import mc.craig.software.craftplus.common.capability.ModCapability;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;

public class ClimbingGearItem extends ArmorItem {

    public ClimbingGearItem(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        super.onArmorTick(stack, level, player);

        BlockPos pos = player.blockPosition().relative(player.getDirection(), 1);

        boolean isPlayerCollided = !level.getBlockState(pos).getMaterial().isLiquid() && !level.getBlockState(pos).isAir() && !player.isOnGround() && !level.getBlockState(pos).hasProperty(BlockStateProperties.LAYERS);

        ModCapability.get(player).ifPresent(iCap -> {
            if (!level.isClientSide()) {

                if (!iCap.isClimbing()) {
                    iCap.setClimbing(isPlayerCollided);
                    player.getItemBySlot(getSlot()).hurtAndBreak(1, player, e -> e.broadcastBreakEvent(getSlot()));
                }

                if (isPlayerCollided && !player.isCreative()) {
                    iCap.setStamina(iCap.getStamina() - 1);
                }
                iCap.sync();
            }

            if (isPlayerCollided && (iCap.getStamina() > 0 || player.isCreative())) {
                Vec3 deltaMovement = player.getDeltaMovement();
                deltaMovement = new Vec3(deltaMovement.x, player.isCrouching() ? -0.2D : 0.2D, deltaMovement.z);
                player.setDeltaMovement(deltaMovement);
            }
        });
    }
}
