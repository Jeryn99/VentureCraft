package mc.craig.software.notnotyet.common.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
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

public class ParagliderItem extends Item implements Wearable {


    private int fixedFlightTimeTicks;

    public ParagliderItem(Properties itemProperties, int fixedFlightTime) {
        super(itemProperties);
        fixedFlightTimeTicks = fixedFlightTime;
    }

    public int getFixedFlightTimeTicks() {
        return fixedFlightTimeTicks;
    }

    public static boolean glidingEnabled(ItemStack itemStack) {
        CompoundTag compound = itemStack.getOrCreateTag();
        if (!compound.contains("glide")) return false;

        int maxTime = 200;
        if (itemStack.getItem() instanceof ParagliderItem paragliderItem) {
            maxTime = paragliderItem.getFixedFlightTimeTicks();
        }

        return compound.getBoolean("glide") && itemStack.getMaxDamage() != itemStack.getDamageValue() && timeInAir(itemStack) < maxTime;
    }

    public static void setGlide(ItemStack itemStack, boolean canGlide) {
        CompoundTag compound = itemStack.getOrCreateTag();
        compound.putBoolean("glide", canGlide);
    }

    public static int timeInAir(ItemStack itemStack) {
        CompoundTag compound = itemStack.getOrCreateTag();
        if (!compound.contains("timeInAir")) return 0;
        return compound.getInt("timeInAir");
    }

    public static void setTimeInAir(ItemStack itemStack, int timeLeft) {
        CompoundTag compound = itemStack.getOrCreateTag();
        compound.putInt("timeInAir", timeLeft);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        super.onArmorTick(stack, level, player);
        if (!player.isOnGround() && !player.getAbilities().flying && glidingEnabled(stack) && !player.getCooldowns().isOnCooldown(this)) {
            player.fallDistance = 0;

            // Handle Movement
            Vec3 m = player.getDeltaMovement();
            if (m.y < -0.05) player.setDeltaMovement(new Vec3(m.x, -0.05, m.z));

            // Handle Cooldown
            setTimeInAir(stack, timeInAir(stack) + 1);
            if (timeInAir(stack) >= getFixedFlightTimeTicks()) {
                player.getCooldowns().addCooldown(this, 200);
            }
            System.out.println(timeInAir(stack));

            // Damage Glider as used
            if (player.tickCount % 200 == 0) {
                stack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(EquipmentSlot.CHEST));
            }

        } else {
            // Reset Gliding status when on Ground
            setGlide(stack, false);

            // Reset time in air when cooldown ends
            if (!player.getCooldowns().isOnCooldown(this)) {
                setTimeInAir(stack, 0);
            }
        }
    }


    @Override
    public @Nullable EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.CHEST;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(Component.literal(String.valueOf(glidingEnabled(stack))));
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
}
