package mc.craig.software.craftplus.common.items;

import mc.craig.software.craftplus.common.items.armor.SpeedArmor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TierArmor extends ArmorItem {
    public Tier armorTier;


    public TierArmor(Tier armorTier, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorTier.getMaterial(), equipmentSlot, properties);
        this.armorTier = armorTier;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        super.onArmorTick(stack, level, player);
        if (checkIfFullTier(player, getTier())) {
            armorTier.getArmorAction().tick(player);
        }
    }

    public boolean checkIfFullTier(LivingEntity livingEntity, Tier tier) {
        EquipmentSlot[] slots = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
        for (EquipmentSlot equipmentSlot : slots) {
            if (livingEntity.getItemBySlot(equipmentSlot).getItem() instanceof TierArmor tierArmor) {
                if (tierArmor.getTier() != tier) {
                    return false;
                }
            }
        }

        return true;
    }

    private Tier getTier() {
        return armorTier;
    }

    public enum Tier {
        LEATHER(ArmorMaterials.LEATHER, new SpeedArmor());

        private final ArmorMaterials material;
        private final ArmorAction armorAction;

        Tier(ArmorMaterials material, ArmorAction armorAction) {
            this.material = material;
            this.armorAction = armorAction;
        }

        public ArmorAction getArmorAction() {
            return armorAction;
        }

        public ArmorMaterials getMaterial() {
            return material;
        }
    }
}
