package mc.craig.software.craftplus.common.block;

import mc.craig.software.craftplus.common.blockentity.CatalystBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class CatalystBlock extends BaseEntityBlock {

    public static final VoxelShape CATALYST = Stream.of(
            Shapes.join(Block.box(5, 14, 5, 11, 18, 11), Shapes.join(Block.box(6, 14, 6, 10, 18, 10), Block.box(7, 15, 7, 9, 17, 9), OR), OR),
            Block.box(0, 27, 0, 16, 32, 16),
            Block.box(3, 5, 3, 13, 27, 13),
            Block.box(2, 7, 2, 14, 25, 14),
            Block.box(0, 0, 0, 16, 5, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get();

    public CatalystBlock(Properties properties) {
        super(properties.noOcclusion());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CatalystBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return CATALYST;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        for(int i = 0; i < 3; ++i) {
            int j = random.nextInt(2) * 2 - 1;
            int k = random.nextInt(2) * 2 - 1;
            double d = (double)pos.getX() + 0.5 + 0.25 * (double)j;
            double e = (float)pos.getY() + random.nextFloat();
            double f = (double)pos.getZ() + 0.5 + 0.25 * (double)k;
            double g = random.nextFloat() * (float)j;
            double h = ((double)random.nextFloat() - 0.5) * 0.125;
            double l = random.nextFloat() * (float)k;
            level.addParticle(ParticleTypes.PORTAL, d, e, f, g, h, l);
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level currentLevel, @NotNull BlockState blockState, @NotNull BlockEntityType<T> blockEntityType) {
        return (level1, blockPos, block, t) -> {
            if (t instanceof CatalystBlockEntity catalystBlockEntity) {
                catalystBlockEntity.tick(currentLevel, blockPos, blockState, catalystBlockEntity);
            }
        };
    }
}
