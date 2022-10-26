package mc.craig.software.craftplus.common.block;

import mc.craig.software.craftplus.common.ModBlocks;
import mc.craig.software.craftplus.util.Utils;
import mc.craig.software.craftplus.util.VoxelShapeUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class TrinketBlock extends HorizontalDirectionalBlock {


    public static final Map<String, VoxelShape> HOVER_SHAPES = new HashMap<>();



    public static final VoxelShape HOVERBOARD = Stream.of(
            Shapes.join(Block.box(5.71757, 0.17725, -0.44364, 10.71757, 0.9368, 15.88353), Shapes.join(Block.box(4.71757, 0.0618, -0.44364, 5.71757, 1.0618, 15.93136), Shapes.join(Block.box(10.71757, 0.0618, -0.44364, 11.71757, 1.0618, 15.93136), Shapes.join(Block.box(6.21757, 0.8118, 11.43136, 10.21757, 1.0618, 15.43136), Shapes.join(Block.box(4.88914, 0.3118, 9.93136, 5.88914, 1.1868, 14.58761), Shapes.join(Block.box(6.34257, 0.0618, 11.43136, 10.09257, 0.5618, 15.18136), Shapes.join(Block.box(6.34257, 0.0618, 3.18136, 10.09257, 0.5618, 6.93136), Block.box(7.21757, 0.09305, 2.43136, 9.21757, 0.90555, 15.93136), OR), OR), OR), OR), OR), OR), OR),
            Shapes.join(Block.box(10.71757, -2.4738, 15.98668, 11.71757, -1.4738, 19.36168), Shapes.join(Block.box(4.71757, -2.47377, 15.98668, 5.71757, -1.47377, 19.36168), Shapes.join(Block.box(5.71757, -2.3488, 15.98668, 10.71757, -1.5988, 19.23668), Block.box(5.71757, -2.47377, 18.36168, 10.71757, -1.4738, 19.36168), OR), OR), OR),
            Block.box(5.09257, 0.0618, -0.88928, 11.34257, 1.0618, 0.11072)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get();

    public static final VoxelShape TARDIS = Stream.of(
            Block.box(5, 0, 5, 11, 10, 11),
            Block.box(7.5, 10, 7.5, 8.5, 11, 8.5),
            Block.box(5, 0, 5, 11, 9, 11),
            Block.box(5, 0, 5, 11, 10, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get();


    public TrinketBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        if(state.getBlock() == ModBlocks.TRINKET_HOVERBOARD.get()){
            return HOVERBOARD;
        }
        if(state.getBlock() == ModBlocks.TRINKET_TARDIS.get()){
            return TARDIS;
        }

        return super.getShape(state, level, pos, context);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
