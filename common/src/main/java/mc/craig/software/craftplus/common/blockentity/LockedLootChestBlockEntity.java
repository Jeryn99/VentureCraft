package mc.craig.software.craftplus.common.blockentity;

import mc.craig.software.craftplus.common.ModBlockEntities;
import mc.craig.software.craftplus.common.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LockedLootChestBlockEntity extends ChestBlockEntity implements BlockEntityTicker<LockedLootChestBlockEntity> {


    private AnimationState OPEN = new AnimationState();

    public LockedLootChestBlockEntity(BlockPos blockPos, BlockState state) {
        super(ModBlockEntities.LOOT_CHEST.get(), blockPos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, LockedLootChestBlockEntity lockedLootChestBlockEntity) {
        if (OPEN.isStarted()) {
            OPEN.start(Math.toIntExact(level.getDayTime()));
        }
    }
}
