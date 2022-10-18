package mc.craig.software.craftplus.common.block;

import mc.craig.software.craftplus.common.blockentity.BigBellBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class BellBlock extends BaseEntityBlock {

    public static final VoxelShape EAST_WEST = Stream.of(
            Block.box(14, 0, 0, 16, 11, 2),
            Block.box(14, 0, 14, 16, 11, 16),
            Block.box(2, 6, 14.5, 14, 10, 15.5),
            Block.box(2, 6, 0.5, 14, 10, 1.5),
            Block.box(0, 0, 0, 2, 11, 2),
            Block.box(0, 0, 14, 2, 11, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get();

    public static final VoxelShape NORTH_SOUTH = Stream.of(
            Block.box(14, 0, 0, 16, 11, 2),
            Block.box(14, 0, 14, 16, 11, 16),
            Block.box(2, 6, 14.5, 14, 10, 15.5),
            Block.box(2, 6, 0.5, 14, 10, 1.5),
            Block.box(0, 0, 0, 2, 11, 2),
            Block.box(0, 0, 14, 2, 11, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get();

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public BellBlock(Properties properties) {
        super(properties.noOcclusion());
    }

/*    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        if (direction == Direction.NORTH || direction == Direction.UP || direction == Direction.DOWN) {
            return EAST_WEST;
        }
        return EAST_WEST;
    }*/

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getHorizontalDirection().getOpposite();
        return this.defaultBlockState().setValue(FACING, direction);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BigBellBlockEntity(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level currentLevel, @NotNull BlockState blockState, @NotNull BlockEntityType<T> blockEntityType) {
        return (level1, blockPos, block, t) -> {
            if (t instanceof BigBellBlockEntity blockEntity) {
                blockEntity.tick(currentLevel, blockPos, blockState, blockEntity);
            }
        };
    }
}
