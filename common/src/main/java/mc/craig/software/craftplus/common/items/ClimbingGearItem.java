package mc.craig.software.craftplus.common.items;

import mc.craig.software.craftplus.common.entities.VenturePlayerData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.threetag.palladiumcore.item.IPalladiumItem;

public class ClimbingGearItem extends ArmorItem implements IPalladiumItem {

    public ClimbingGearItem(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
    }

    @Override
    public void armorTick(ItemStack stack, Level level, Player player) {
        BlockPos pos = player.blockPosition().relative(player.getDirection(), 1);

        boolean isPlayerCollided = !level.getBlockState(pos).getMaterial().isLiquid() && !level.getBlockState(pos).isAir() && !player.isOnGround() && !level.getBlockState(pos).hasProperty(BlockStateProperties.LAYERS);

        VenturePlayerData.get(player).ifPresent(iCap -> {
            if (!level.isClientSide()) {
                iCap.setClimbing(isPlayerCollided);
                if (isPlayerCollided && !player.isCreative() && player.tickCount % 20 == 0) {
                    iCap.setStamina(iCap.getStamina() - 1);
                }
                iCap.sync();
            }

            if (isPlayerCollided && (iCap.getStamina() > 0 || player.isCreative())) {
                Vec3 deltaMovement = player.getDeltaMovement();
                deltaMovement = new Vec3(deltaMovement.x, player.isCrouching() ? -0.05D : 0.05D, deltaMovement.z);
                player.setDeltaMovement(deltaMovement);
            }
        });
    }
}
