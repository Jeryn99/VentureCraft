package mc.craig.software.notnotyet.common.capability;

import mc.craig.software.notnotyet.networking.Network;
import mc.craig.software.notnotyet.networking.packets.MessageSyncCap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nonnull;

public class ModCapability implements ICap {


    public static final Capability<ICap> CAPABILITY = CapabilityManager.get(new CapabilityToken<ICap>() {
    });

    public static int MAX_CLIMB_TIME = 600;
    private Player player;
    private boolean isClimbing = false;
    private int timeClimbing = 0;

    public ModCapability() {

    }

    public ModCapability(Player livingEntity) {
        this.player = livingEntity;
    }

    @Nonnull
    public static LazyOptional<ICap> get(LivingEntity player) {
        return player.getCapability(ModCapability.CAPABILITY, null);
    }

    @Override
    public void tick(LivingEntity livingEntity) {

        if(!player.level.isClientSide) {
            setClimbing(canClimb(livingEntity));
            if (isClimbing()) {
                setTimeClimbed(timeClimbed() + 1);
                sync();
            }
            return;
        }

        if (canClimb(livingEntity)) {
            player.resetFallDistance();
            Vec3 livingMovement = player.getDeltaMovement();
            player.setDeltaMovement(livingMovement.x, 0.2D, livingMovement.z);
        }
    }

    @Override
    public boolean isClimbing() {
        return isClimbing;
    }

    @Override
    public void setClimbing(boolean climbing) {
        this.isClimbing = climbing;
    }

    @Override
    public int timeClimbed() {
        return timeClimbing;
    }

    @Override
    public void setTimeClimbed(int time) {
        this.timeClimbing = time;
    }

    @Override
    public boolean canClimb(LivingEntity livingEntity) {
        return timeClimbing < MAX_CLIMB_TIME && collided(livingEntity) && livingEntity.isCrouching();
    }

    public boolean collided(LivingEntity livingEntity) {
        Vec3 playerMovement = livingEntity.getDeltaMovement();
        Vec3 collidedMovement = livingEntity.collide(playerMovement);
        boolean collidedX = !Mth.equal(playerMovement.x, collidedMovement.x);
        boolean collidedZ = !Mth.equal(playerMovement.z, collidedMovement.z);
        return collidedX || collidedZ;
    }

    @Override
    public void sync() {
        if (player != null && player.level.isClientSide) {
            throw new IllegalStateException("Don't sync client -> server");
        }

        CompoundTag nbt = serializeNBT();
        Network.INSTANCE.send(PacketDistributor.DIMENSION.with(() -> player.getCommandSenderWorld().dimension()), new MessageSyncCap(this.player.getUUID(), nbt));
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt("climbTime", timeClimbing);
        compoundTag.putBoolean("isClimbing", isClimbing);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        isClimbing = nbt.getBoolean("isClimbing");
        timeClimbing = nbt.getInt("climbTime");
    }
}
