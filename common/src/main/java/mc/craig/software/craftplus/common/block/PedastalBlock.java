package mc.craig.software.craftplus.common.block;

import mc.craig.software.craftplus.common.blockentity.PedastalBlockEntity;
import mc.craig.software.craftplus.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class PedastalBlock extends Block implements EntityBlock {

    public static final VoxelShape PEDASTAL_SHAPE = Stream.of(
            Block.box(3, 14, 3, 13, 16, 13),
            Block.box(4, 2, 4, 12, 14, 12),
            Block.box(3, 0, 3, 13, 2, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get();

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        if (hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;


        PedastalBlockEntity pedastalBlockEntity = (PedastalBlockEntity) level.getBlockEntity(pos);

        player.swing(hand); // Swing Players Hand

        pedastalBlockEntity.dropItemIfPresent(player); // Drop item if Pedastal has one

        ItemStack pedStack = player.getMainHandItem().copy();
        pedStack.setCount(1);
        ItemStack heldStack = player.getMainHandItem();
        heldStack.shrink(1);

        pedastalBlockEntity.setHeldItem(pedStack);
        player.setItemInHand(InteractionHand.MAIN_HAND, heldStack);
        Utils.sendUpdates(pedastalBlockEntity, level, pos, state);

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level worldIn, @NotNull BlockPos pos, BlockState newState, boolean isMoving) {
        if (!newState.is(this)) {
            if (!worldIn.isClientSide()) {
                PedastalBlockEntity pedastalBlockEntity = (PedastalBlockEntity) worldIn.getBlockEntity(pos);
                pedastalBlockEntity.dropItemIfPresent(null);
            }
        }
        super.onRemove(state, worldIn, pos, newState, isMoving);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new PedastalBlockEntity(blockPos, blockState);
    }

    public PedastalBlock(Properties properties) {
        super(properties.noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return PEDASTAL_SHAPE;
    }
}
