package mc.craig.software.craftplus.common.menu;

import com.mojang.datafixers.util.Pair;
import mc.craig.software.craftplus.common.entities.ExtendedInventory;
import mc.craig.software.craftplus.common.entities.PlayerInventoryWrapper;
import mc.craig.software.craftplus.util.item.AdvItemStackHandler;
import mc.craig.software.craftplus.util.item.SlotItemHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class ExtendedInventoryMenu extends InventoryMenu {

    public static boolean PREVENT_SLOTS = false;
    public static final Component CONTAINER_TITLE = Component.translatable("container.venturecraft.extended_inventory");
    private static final EquipmentSlot[] SLOT_IDS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    static final ResourceLocation[] TEXTURE_EMPTY_SLOTS = new ResourceLocation[]{EMPTY_ARMOR_SLOT_BOOTS, EMPTY_ARMOR_SLOT_LEGGINGS, EMPTY_ARMOR_SLOT_CHESTPLATE, EMPTY_ARMOR_SLOT_HELMET};

    public ExtendedInventoryMenu(Inventory inventory, boolean pActive, final Player pOwner) {
        super(inventory, pActive, pOwner);
        PREVENT_SLOTS = false;
        var extendedInv = ExtendedInventory.get(pOwner).get();

        // Craft Result Slot
        this.addSlot(new ResultSlot(inventory.player, this.getCraftSlots(), this.resultSlots, 0, 366, 37));

        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                this.addSlot(new Slot(this.getCraftSlots(), j + i * 2, 310 + j * 18, 37 + i * 18));
            }
        }

        // Main Inv
        for (int row = 0; row < 4; ++row) {
            for (int col = 0; col < 11; ++col) {
                this.addSlot(new Slot(inventory, col + (row + 1) * 11, 87 + 8 + col * 18, 216 + 8 + row * 18));
            }
        }

        // Upgrades
        for (int i = 0; i < 3; i++) {
            AdvItemStackHandler inv = i == 0 ? extendedInv.upgrade1 : (i == 1 ? extendedInv.upgrade2 : extendedInv.upgrade3);
            for (int x = 0; x < 2; x++) {
                for (int y = 0; y < 4; y++) {
                    this.addSlot(new SlotItemHandler(inv, x + y * 2, (x == 0 ? 77 : 293) + 18 * (x == 0 ? -1 : 1) * i, 224 + y * 18));
                }
            }
        }

        // Hotbar
        for (int col = 0; col < 11; ++col) {
            this.addSlot(new Slot(inventory, col, 87 + 8 + col * 18, 300));
        }

        // Armor
        for (int k = 0; k < 4; ++k) {
            final EquipmentSlot equipmentslot = SLOT_IDS[k];
            this.addSlot(new Slot(inventory, 11 * 5 + 3 - k, 116, 6 + k * 36) {
                /**
                 * Helper method to put a stack in the slot.
                 */
                public void set(ItemStack stack) {
                    ItemStack itemstack = this.getItem();
                    super.set(stack);
                    pOwner.onEquipItem(equipmentslot, itemstack, stack);
                }

                /**
                 * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in
                 * the case of armor slots)
                 */
                public int getMaxStackSize() {
                    return 1;
                }

                /**
                 * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
                 */
                public boolean mayPlace(ItemStack pStack) {
                    return equipmentslot == Mob.getEquipmentSlotForItem(pStack);
                }

                /**
                 * Return whether this slot's stack can be taken from this slot.
                 */
                public boolean mayPickup(Player pPlayer) {
                    ItemStack itemstack = this.getItem();
                    return (itemstack.isEmpty() || pPlayer.isCreative() || !EnchantmentHelper.hasBindingCurse(itemstack)) && super.mayPickup(pPlayer);
                }

                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(BLOCK_ATLAS, TEXTURE_EMPTY_SLOTS[equipmentslot.getIndex()]);
                }
            });
        }

        // Offhand
        this.addSlot(new Slot(inventory, PlayerInventoryWrapper.OFF_HAND_SLOT, 77, 62) {
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(BLOCK_ATLAS, EMPTY_ARMOR_SLOT_SHIELD);
            }
        });
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
