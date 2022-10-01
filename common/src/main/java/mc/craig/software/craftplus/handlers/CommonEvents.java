package mc.craig.software.craftplus.handlers;

import mc.craig.software.craftplus.common.ModDamageSource;
import mc.craig.software.craftplus.common.ModItems;
import mc.craig.software.craftplus.common.advancement.TriggerManager;
import mc.craig.software.craftplus.common.entities.Owl;
import mc.craig.software.craftplus.common.entities.VenturePlayerData;
import mc.craig.software.craftplus.common.items.ParagliderItem;
import mc.craig.software.craftplus.util.GliderUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.threetag.palladiumcore.event.EntityEvents;
import net.threetag.palladiumcore.event.EventResult;
import net.threetag.palladiumcore.event.LivingEntityEvents;
import net.threetag.palladiumcore.event.PlayerEvents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CommonEvents implements EntityEvents.JoinLevel, LivingEntityEvents.Hurt, PlayerEvents.AnvilUpdate, EntityEvents.LightningStrike, PlayerEvents.Tracking, LivingEntityEvents.Tick, LivingEntityEvents.Attack, LivingEntityEvents.ItemUse {

    public static void init() {
        var instance = new CommonEvents();
        EntityEvents.JOIN_LEVEL.register(instance);
        LivingEntityEvents.HURT.register(instance);
        LivingEntityEvents.ATTACK.register(instance);
        PlayerEvents.ANVIL_UPDATE.register(instance);
        EntityEvents.LIGHTNING_STRIKE.register(instance);
        PlayerEvents.START_TRACKING.register(instance);
        LivingEntityEvents.TICK.register(instance);
        LivingEntityEvents.ITEM_USE_START.register(instance);
        LivingEntityEvents.ITEM_USE_TICK.register(instance);
        LivingEntityEvents.ITEM_USE_STOP.register(instance);
    }

    @Override
    public void entityJoinLevel(Entity entity, Level level) {
        // Make Cats attack Owls
        if (entity instanceof Cat cat) {
            cat.targetSelector.addGoal(1, new NonTameRandomTargetGoal<>(cat, Owl.class, false, null));
        }

        // Stop all kinds of XP
        if (entity instanceof ExperienceOrb) {
            entity.discard();
        }
    }

    @Override
    public EventResult anvilUpdate(Player player, ItemStack left, ItemStack right, @Nullable String name, AtomicInteger cost, AtomicInteger materialCost, AtomicReference<ItemStack> output) {
        if (left.getItem() instanceof ParagliderItem && right.getItem() == ModItems.COPPER_FILAMENT.get()) {
            ItemStack copiedStack = left.copy();
            ParagliderItem.setCopper(copiedStack, true);
            cost.set(5);
            output.set(copiedStack);

            if (player instanceof ServerPlayer serverPlayer) {
                TriggerManager.COPPER_MODDER.trigger(serverPlayer);
            }
        }

        return EventResult.pass();
    }

    @Override
    public void lightningStrike(List<Entity> entities, LightningBolt lightningBolt) {
        for (Entity struckEntity : entities) {
            if (struckEntity instanceof ServerPlayer player) {
                ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);
                boolean hasCopperMod = ParagliderItem.hasCopperMod(chestItem);
                boolean isGliding = GliderUtil.isGlidingWithActiveGlider(player);
                if (hasCopperMod && isGliding) {
                    ParagliderItem.setStruck(chestItem, true);
                    if (ParagliderItem.hasBeenStruck(chestItem)) {
                        player.hurt(ModDamageSource.LIGHTNING_GLIDER, 2F);
                        TriggerManager.GLIDER_LIGHTNING.trigger(player);
                    }
                }
            }
        }
    }

    @Override
    public EventResult livingEntityHurt(LivingEntity entity, DamageSource damageSource, float amount) {
        if (entity instanceof Player player) {
            ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);
            boolean hasCopperMod = ParagliderItem.hasCopperMod(chestItem);
            boolean isGliding = GliderUtil.isGlidingWithActiveGlider(player);
            boolean isLightning = damageSource == DamageSource.LIGHTNING_BOLT;
            if (hasCopperMod && isGliding && isLightning) {
                return EventResult.cancel();
            }
        }
        return EventResult.pass();
    }

    @Override
    public void playerTracking(Player tracker, Entity trackedEntity) {
        // Don't sync to all, just sync to the tracker
        if (trackedEntity instanceof Player trackedPlayer && tracker instanceof ServerPlayer trackerPlayer) {
            VenturePlayerData.get(trackedPlayer).ifPresent(data -> {
                data.syncTo(trackerPlayer);
            });
        }
    }

    @Override
    public void livingEntityTick(LivingEntity entity) {
        if (entity instanceof Player player) {
            VenturePlayerData.get(player).ifPresent(data -> data.tick(player));
        }
    }

    @Override
    public EventResult livingEntityAttack(LivingEntity entity, DamageSource damageSource, float amount) {
        if (damageSource.getEntity() instanceof LivingEntity livingEntity) {
            if (GliderUtil.isGlidingWithActiveGlider(livingEntity)) {
                return EventResult.cancel();
            }
        }

        return EventResult.pass();
    }

    @Override
    public EventResult livingEntityItemUse(LivingEntity entity, @NotNull ItemStack stack, AtomicInteger duration) {
        return GliderUtil.isGlidingWithActiveGlider(entity) ? EventResult.cancel() : EventResult.pass();
    }
}
