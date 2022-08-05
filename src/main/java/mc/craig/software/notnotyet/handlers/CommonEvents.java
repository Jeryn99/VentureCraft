package mc.craig.software.notnotyet.handlers;

import com.mojang.math.Vector3f;
import mc.craig.software.notnotyet.common.capability.ICap;
import mc.craig.software.notnotyet.common.capability.ModCapability;
import mc.craig.software.notnotyet.util.GliderUtil;
import mc.craig.software.notnotyet.util.ModConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber
public class CommonEvents {

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {

    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            event.addCapability(ModConstants.CAPABILITY_ID, new ICapabilitySerializable<CompoundTag>() {
                final ModCapability cap = new ModCapability(player);
                final LazyOptional<ICap> capInstance = LazyOptional.of(() -> cap);

                @Nonnull
                @Override
                public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
                    return cap == ModCapability.CAPABILITY ? (LazyOptional<T>) capInstance : LazyOptional.empty();
                }

                @Override
                public CompoundTag serializeNBT() {
                    return cap.serializeNBT();
                }

                @Override
                public void deserializeNBT(CompoundTag nbt) {
                    cap.deserializeNBT(nbt);
                }
            });
        }
    }

    @SubscribeEvent
    public static void onTrackPlayer(PlayerEvent.StartTracking startTracking) {
        ModCapability.get(startTracking.getEntity()).ifPresent(ICap::sync);
    }

    @SubscribeEvent
    public static void living(LivingEvent.LivingTickEvent event) {
        if(event.getEntity() instanceof Player player){
            ModCapability.get(player).ifPresent(iCap -> iCap.tick(player));
        }
    }

    @SubscribeEvent
    public static void onUseItem(LivingEntityUseItemEvent useItemEvent) {
        if (GliderUtil.isGlidingWithActiveGlider(useItemEvent.getEntity())) {
            useItemEvent.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent attackEvent) {
        if (attackEvent.getSource().getEntity() instanceof LivingEntity livingEntity)
            if (GliderUtil.isGlidingWithActiveGlider(livingEntity)) {
                attackEvent.setCanceled(true);
            }
    }

}
