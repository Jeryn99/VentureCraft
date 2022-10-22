package mc.craig.software.craftplus.common.blockentity;

import mc.craig.software.craftplus.common.ModBlockEntities;
import mc.craig.software.craftplus.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PedastalBlockEntity extends BlockEntity {

    private ItemStack heldItem;


    public PedastalBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.PEDASTAL.get(), blockPos, blockState);
        this.heldItem = ItemStack.EMPTY;
    }

    public void dropItemIfPresent(Player player) {
        ItemStack currentlyHeldItem = getHeldItem();
        dropItem(currentlyHeldItem);
        Utils.sendUpdates(this, level, worldPosition, getBlockState());
    }

    private void dropItem(ItemStack currentlyHeldItem) {
        Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY() + 1, worldPosition.getZ(), currentlyHeldItem);
        clearContent();
    }

    public void clearContent() {
        this.setHeldItem(ItemStack.EMPTY);
    }

    public void setHeldItem(ItemStack heldItem) {
        this.heldItem = heldItem;
    }

    public ItemStack getHeldItem() {
        return heldItem;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (tag.contains("HeldItem", 10)) {
            this.setHeldItem(ItemStack.of(tag.getCompound("HeldItem")));
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        tag.put("HeldItem", this.getHeldItem().save(new CompoundTag()));
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


}
