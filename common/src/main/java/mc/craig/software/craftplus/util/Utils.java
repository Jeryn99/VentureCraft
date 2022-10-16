package mc.craig.software.craftplus.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class Utils {

    public static int secondsToTicks(int seconds) {
        return seconds * 20;
    }

    public static void sendUpdates(BlockEntity block, Level level, BlockPos blockPos, BlockState blockState) {
        level.updateNeighbourForOutputSignal(blockPos, blockState.getBlock());
        level.sendBlockUpdated(blockPos, level.getBlockState(blockPos), level.getBlockState(blockPos), 3);
        block.setChanged();
    }

}
