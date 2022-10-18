package mc.craig.software.craftplus.common.blockentity;

import mc.craig.software.craftplus.common.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public class BigBellBlockEntity extends BlockEntity implements BlockEntityTicker<BigBellBlockEntity> {

    public AnimationState BELL_RING = new AnimationState();
    public AnimationState BELL_STOP = new AnimationState();

    public BigBellBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.BELL.get(), blockPos, blockState);
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, BigBellBlockEntity blockEntity) {
        if (level.hasNeighborSignal(blockPos)) {

            if (level.getDayTime() % 40 == 0) {
                playBellNoise();
            }

            BELL_STOP.stop();
            if (!BELL_RING.isStarted()) {
                playBellNoise();
                BELL_RING.start(0);
            }
        } else {
            BELL_RING.stop();
            if (!BELL_STOP.isStarted()) {
                BELL_STOP.start(0);
            }
        }
    }

    public void playBellNoise() {
        level.playSound(null, worldPosition, SoundEvents.BELL_BLOCK, SoundSource.BLOCKS, 2.0F, 1.0F);
    }


}