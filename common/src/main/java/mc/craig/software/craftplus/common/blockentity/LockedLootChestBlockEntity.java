package mc.craig.software.craftplus.common.blockentity;

import mc.craig.software.craftplus.common.ModBlockEntities;
import mc.craig.software.craftplus.common.block.LockedLootChestBlock;
import mc.craig.software.craftplus.common.block.VCChestTypes;
import mc.craig.software.craftplus.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LockedLootChestBlockEntity extends ChestBlockEntity implements BlockEntityTicker<LockedLootChestBlockEntity> {


    private VCChestTypes chestType;
    public AnimationState OPEN = new AnimationState();
    public AnimationState DENY = new AnimationState();

    public LockedLootChestBlockEntity(BlockPos blockPos, BlockState state, VCChestTypes chestType) {
        super(ModBlockEntities.LOOT_CHEST.get(), blockPos, state);
        this.chestType = chestType;
    }



    public VCChestTypes getChestType() {
        return chestType;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (chestType != null) {
            tag.putString("chestType", chestType.name());
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("chestType")) {
            chestType = VCChestTypes.find(tag.getString(tag.getString("chestType")));
        } else {
            chestType = VCChestTypes.SAPPHIRE;
        }
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

        if (chestLidController.shouldBeOpen) {
            OPEN.stop();
        }

        if (blockState.getBlock() instanceof LockedLootChestBlock block) {
            if (block.getChestType() != getChestType()) {
                chestType = block.getChestType();
                Utils.sendUpdates(this, level, worldPosition, getBlockState());
            }
        }
    }
}
