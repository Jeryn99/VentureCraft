package mc.craig.software.craftplus.common.items;

import mc.craig.software.craftplus.common.capability.ModCapability;
import mc.craig.software.craftplus.util.GliderUtil;
import mc.craig.software.craftplus.util.ModConstants;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Wearable;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class ParagliderItem extends Item implements Wearable, Repairable {

    private final double SPEED_INCREASE = 2;
    private final AttributeModifier SPEED_MODIFIER = new AttributeModifier("b44790fb-65c0-4ec7-8b63-a1749c614b1e", SPEED_INCREASE, AttributeModifier.Operation.MULTIPLY_BASE);
    private final Supplier<Item> repairItem;

    public ParagliderItem(Properties itemProperties, Supplier<Item> itemSupplier) {
        super(itemProperties);
        this.repairItem = itemSupplier;
    }

    public static boolean isSpaceGlider(ItemStack stack) {
        return stack.getDisplayName().getString().contains("xwing");
    }

    public static void setCopper(ItemStack itemStack, boolean copper) {
        CompoundTag compound = itemStack.getOrCreateTag();
        compound.putBoolean("copper_mod", copper);
    }


    public static boolean hasCopperMod(ItemStack itemStack) {
        CompoundTag compound = itemStack.getOrCreateTag();
        if (!compound.contains("copper_mod")) return false;
        return compound.getBoolean("copper_mod");
    }

    public static boolean glidingEnabled(ItemStack itemStack) {
        CompoundTag compound = itemStack.getOrCreateTag();
        if (!compound.contains("glide")) return false;
        return compound.getBoolean("glide") && itemStack.getMaxDamage() != itemStack.getDamageValue();
    }

    public static void setGlide(ItemStack itemStack, boolean canGlide) {
        CompoundTag compound = itemStack.getOrCreateTag();
        compound.putBoolean("glide", canGlide);
    }

    public static void setStruck(ItemStack itemStack, boolean isStruck) {
        CompoundTag compound = itemStack.getOrCreateTag();
        compound.putBoolean("struck", isStruck);
    }

    public static boolean hasBeenStruck(ItemStack itemStack) {
        CompoundTag compound = itemStack.getOrCreateTag();
        if (!compound.contains("struck")) return false;
        return compound.getBoolean("struck");
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        super.onArmorTick(stack, level, player);

        ModCapability.get(player).ifPresent(iCap -> {

        boolean playerCanGlide = !GliderUtil.isPlayerOnGroundOrWater(player) && !player.getAbilities().flying;
        boolean gliderCanGlide = glidingEnabled(stack) && iCap.getStamina() > 0;

        if (playerCanGlide && gliderCanGlide) {

            // Handle Cooldown
            if(!player.isCreative()) {
                iCap.setStamina(iCap.getStamina() - 1);
            }

            player.resetFallDistance();

            player.getAbilities().mayfly = true; // Stop Servers kicking survival players
            // Handle Movement
            Vec3 m = player.getDeltaMovement();
            boolean hasSpeedMods = hasCopperMod(stack) && hasBeenStruck(stack);

            // Particles
            float horizonalSpeed = (float) player.getDeltaMovement().horizontalDistance();
            if (isSpaceGlider(stack) && horizonalSpeed >= 0.01F) {
                for (int i = 0; i < 2; ++i) {

                    player.level.addParticle(ParticleTypes.DRAGON_BREATH, player.getRandomX(0.5D), player.getY() + 2, player.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
                }
            }

            // Speed Modifications
            if (hasSpeedMods) {
                if (!player.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPEED_MODIFIER)) {
                    player.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(SPEED_MODIFIER);
                }
                for (int i = 0; i < 2; ++i) {
                    if (horizonalSpeed >= 0.01F) {
                        player.level.addParticle(ParticleTypes.GLOW, player.getRandomX(0.5D), player.getY() + 2.5, player.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
                    }
                }
            }

            if (m.y < -0.05) player.setDeltaMovement(new Vec3(m.x, -0.05, m.z));
            return;
        }

        player.getAbilities().mayfly = player.isCreative();

        if (GliderUtil.isPlayerOnGroundOrWater(player)) {

            if (player.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPEED_MODIFIER)) {
                player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(SPEED_MODIFIER);
            }

            // Reset Gliding status when on Ground
            setGlide(stack, false);
            setStruck(stack, false);

            if (iCap.getStamina() < iCap.getMaxStamina()) {
                // Reset time in air when cooldown ends
                iCap.setStamina(iCap.getStamina() + 1);
            }
        }
    });
}

    @Override
    public boolean isValidRepairItem(ItemStack p_41402_, ItemStack material) {
        return material.getItem() == repairItem.get();
    }

    @Override
    public @Nullable EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.CHEST;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

        if (hasCopperMod(stack)) {
            tooltip.add(Component.translatable(ModConstants.INSTALLED_MODS));
            tooltip.add(Component.literal("- ").append(Component.translatable(ModConstants.COPPER_MOD)));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        EquipmentSlot equipmentslot = Mob.getEquipmentSlotForItem(itemstack);
        ItemStack equipmentSlotStack = player.getItemBySlot(equipmentslot);
        if (equipmentSlotStack.isEmpty()) {
            player.setItemSlot(equipmentslot, itemstack.copy());
            if (!level.isClientSide()) {
                player.awardStat(Stats.ITEM_USED.get(this));
            }

            itemstack.setCount(0);
            return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }

    @Override
    public Item getRepairItem() {
        return repairItem.get();
    }
}
