package mc.craig.software.notnotyet.common.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class StalkerEntity extends QuantumLockedLifeform {

    private static final EntityDataAccessor<String> POSE = SynchedEntityData.defineId(StalkerEntity.class, EntityDataSerializers.STRING);

    public StalkerEntity(EntityType<? extends QuantumLockedLifeform> type, Level worldIn) {
        super(worldIn, type);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(POSE, Pose.ANGRY.id());
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public boolean shouldShowName() {
        return false;
    }

    @Override
    public void invokeSeen(Player player) {
        super.invokeSeen(player);
        if (getSeenTime() == 1) {
            setStalkerPose(Pose.randomPose(random));
        }
    }

    public Pose getStalkerPose() {
        return Pose.getPoseByName(getEntityData().get(POSE));
    }

    public void setStalkerPose(Pose pose) {
        getEntityData().set(POSE, pose.id());
    }

    @Override
    public boolean hurt(DamageSource damageSource, float amount) {
        return false;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Override
    public void deserializeNBT(CompoundTag compound) {
        super.deserializeNBT(compound);
        setStalkerPose(Pose.getPoseByName(compound.getString("stalker_pose")));
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compound = super.serializeNBT();
        compound.putString("stalker_pose", getStalkerPose().id());
        return compound;
    }


    // ==== Pose & Emotion ====
    public enum Pose {
        FURIOUS(Emotion.SCREAM), HIDING(Emotion.IDLE), APPROACH(Emotion.ANGRY), IDLE(Emotion.IDLE), SHY(Emotion.ANGRY), ANGRY(Emotion.ANGRY);

        private final Emotion emotion;

        Pose(Emotion emotion) {
            this.emotion = emotion;
        }

        Pose() {
            this.emotion = Emotion.IDLE;
        }

        public String id() {
            return name().toLowerCase();
        }

        public Emotion getEmotion() {
            return emotion;
        }

        public static Pose randomPose(RandomSource randomSource) {
            int x = randomSource.nextInt(Pose.class.getEnumConstants().length);
            return Pose.class.getEnumConstants()[x];
        }

        public static Pose getPoseByName(String name) {
            for (Pose pose : Pose.values()) {
                if (pose.name().toLowerCase().equalsIgnoreCase(name)) {
                    return pose;
                }
            }
            return IDLE;
        }
    }

    public enum Emotion {
        IDLE, SCREAM, ANGRY;

        public String id() {
            return name().toLowerCase();
        }
    }
}
