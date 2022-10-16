package mc.craig.software.craftplus.common.block;

import mc.craig.software.craftplus.common.ModBlockEntities;
import mc.craig.software.craftplus.common.blockentity.LockedLootChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class LockedLootChestBlock extends ChestBlock {

    private final VCChestTypes chestType;


    public LockedLootChestBlock(Properties properties, VCChestTypes chestType) {
        super(properties.noOcclusion(), () -> ModBlockEntities.LOOT_CHEST.get());
        this.chestType = chestType;
    }

    public VCChestTypes getChestType() {
        return chestType;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new LockedLootChestBlockEntity(p_153215_, p_153216_);
    }

    @Override
    public DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> combine(BlockState p_51544_, Level p_51545_, BlockPos p_51546_, boolean p_51547_) {
        return super.combine(p_51544_, p_51545_, p_51546_, false);
    }
}
