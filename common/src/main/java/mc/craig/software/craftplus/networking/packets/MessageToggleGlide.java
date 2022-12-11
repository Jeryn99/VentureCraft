package mc.craig.software.craftplus.networking.packets;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.common.ModSounds;
import mc.craig.software.craftplus.common.advancement.TriggerManager;
import mc.craig.software.craftplus.common.entities.VenturePlayerData;
import mc.craig.software.craftplus.common.items.ParagliderItem;
import mc.craig.software.craftplus.networking.VCNetwork;
import mc.craig.software.craftplus.util.GliderUtil;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.threetag.palladiumcore.network.MessageC2S;
import net.threetag.palladiumcore.network.MessageContext;
import net.threetag.palladiumcore.network.MessageType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MessageToggleGlide extends MessageC2S {

    public MessageToggleGlide() {
    }

    public MessageToggleGlide(FriendlyByteBuf buffer) {
    }

    @NotNull
    @Override
    public MessageType getType() {
        return VCNetwork.TOGGLE_GLIDE;
    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(MessageContext context) {
        var sender = context.getPlayer();
        if (GliderUtil.hasParagliderEquipped(sender)) {
            ItemStack chestItem = sender.getItemBySlot(EquipmentSlot.CHEST);
            ParagliderItem.setGlide(chestItem, !ParagliderItem.glidingEnabled(chestItem));
            if (ParagliderItem.glidingEnabled(chestItem)) {
                TriggerManager.FIRST_TIME_FLYER.trigger(Objects.requireNonNull(sender));
                sender.level.playSound(null, sender.getX(), sender.getY(), sender.getZ(), ParagliderItem.isSpaceGlider(chestItem) ? ModSounds.SPACE_DEPLOY.get() : ModSounds.GLIDER_OPEN.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

                new MessagePlaySound(ParagliderItem.isSpaceGlider(chestItem) ? ModSounds.SPACE_GLIDE.get().getLocation() : SoundEvents.ELYTRA_FLYING.getLocation(), sender.getUUID()).sendToTracking(sender);

                Minecraft.getInstance().options.setCameraType(VenturePlayerData.get(Minecraft.getInstance().player).get().gliderPovToggle() ? CameraType.THIRD_PERSON_BACK : Minecraft.getInstance().options.getCameraType());

                // Damage Glider as used
                chestItem.hurtAndBreak(1, sender, e -> e.broadcastBreakEvent(EquipmentSlot.CHEST));
            }
        }
    }

}