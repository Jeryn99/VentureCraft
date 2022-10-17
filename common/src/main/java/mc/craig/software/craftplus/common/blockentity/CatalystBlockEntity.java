package mc.craig.software.craftplus.common.blockentity;

import mc.craig.software.craftplus.common.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CatalystBlockEntity extends BlockEntity implements BlockEntityTicker<CatalystBlockEntity> {

    public AnimationState CALM = new AnimationState();
    public AnimationState AGITATED = new AnimationState();

    public CatalystBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.CATALYST.get(), blockPos, blockState);
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, CatalystBlockEntity blockEntity) {
        if(!AGITATED.isStarted()){
            AGITATED.start(1000);
        }
    }
}
