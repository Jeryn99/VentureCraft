package mc.craig.software.craftplus.common.blockentity;

import mc.craig.software.craftplus.common.ModBlockEntities;
import mc.craig.software.craftplus.common.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public class BigBellBlockEntity extends BlockEntity implements BlockEntityTicker<BigBellBlockEntity> {

    public AnimationState RING_START = new AnimationState();
    public AnimationState RING_STOP = new AnimationState();
    public AnimationState RING_LOOP = new AnimationState();
    private int tickTime = 0;


    public BigBellBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.BELL.get(), blockPos, blockState);
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, BigBellBlockEntity blockEntity) {
        if (level.hasNeighborSignal(blockPos)) {

            // Update timer
            tickTime++;

            // Play Sound every 2 seconds
            if (level.getDayTime() % 40 == 0) {
                playBellNoise();
            }

            // If timer is above 0 and below 33, while powered, we want to play ringing start
            if(tickTime > 0 && tickTime < 33){
                RING_START.start(0);
                RING_LOOP.stop();
                RING_STOP.stop();
                return;
            }

            // If timer is above 33, while powered, we want to play ringing loop
            if(tickTime >= 33){
                RING_LOOP.start(0);
                RING_START.stop();
                RING_STOP.stop();
                return;
            }


        }

        // If we have no power, we reset the tick timer, and also stop all animations bar RING_STOP
        RING_STOP.start(0);
        RING_START.stop();
        RING_LOOP.stop();
        tickTime = 0;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("ring", tickTime);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if(tag.contains("ring")) {
            tickTime = tag.getInt("ring");
        }
    }

    public void playBellNoise() {
        level.playSound(null, worldPosition, ModSounds.BELL_TOLL.get(), SoundSource.BLOCKS, 2.0F, 1.0F);
    }


    public float getTickTime() {
        return tickTime;
    }
}