package mc.craig.software.craftplus.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Utils {

    public static int secondsToTicks(int seconds) {
        return seconds * 20;
    }

    public static void sendUpdates(BlockEntity block, Level level, BlockPos blockPos, BlockState blockState) {
        level.updateNeighbourForOutputSignal(blockPos, blockState.getBlock());
        level.sendBlockUpdated(blockPos, level.getBlockState(blockPos), level.getBlockState(blockPos), 3);
        block.setChanged();
    }

    public static VoxelShape rotateCuboidShape(final VoxelShape shape, final Rotation rotationDir) {
        double x1 = shape.min(Direction.Axis.X), x2 = shape.max(Direction.Axis.X);
        final double y1 = shape.min(Direction.Axis.Y), y2 = shape.max(Direction.Axis.Y);
        double z1 = shape.min(Direction.Axis.Z), z2 = shape.max(Direction.Axis.Z);

        if (rotationDir == Rotation.CLOCKWISE_90 || rotationDir == Rotation.COUNTERCLOCKWISE_90) {
            double temp = z1; // ]
            z1 = x1;   // ] x1 <-> z1
            x1 = temp; // ]

            temp = z2; // ]
            z2 = x2;   // ] x2 <-> z2
            x2 = temp; // ]
        }

        if (rotationDir == Rotation.CLOCKWISE_90 || rotationDir == Rotation.CLOCKWISE_180) {
            x1 = 1 - x1; // clockwise
            x2 = 1 - x2;
        }
        if (rotationDir == Rotation.COUNTERCLOCKWISE_90 || rotationDir == Rotation.CLOCKWISE_180) {
            z1 = 1 - z1; // counterclockwise
            z2 = 1 - z2;
        }

        return Shapes.box(x1, y1, z1, x2, y2, z2);
    }

}
