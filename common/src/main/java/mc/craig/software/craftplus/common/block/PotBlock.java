package mc.craig.software.craftplus.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class PotBlock extends Block {

    public static final VoxelShape POT_SHAPE = Stream.of(
            Block.box(0, 0.025, 0, 16, 0.025, 16),
            Block.box(3, 0, 3, 13, 8, 13),
            Block.box(5, 8, 5, 11, 10, 11),
            Block.box(4, 10, 4, 12, 11, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get();

    public PotBlock(Properties properties) {
        super(properties.noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return POT_SHAPE;
    }
}
