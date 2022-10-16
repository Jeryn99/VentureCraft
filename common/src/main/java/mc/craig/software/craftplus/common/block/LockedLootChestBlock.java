package mc.craig.software.craftplus.common.block;

import mc.craig.software.craftplus.common.ModBlockEntities;
import mc.craig.software.craftplus.common.blockentity.LockedLootChestBlockEntity;
import mc.craig.software.craftplus.common.items.KeyItem;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.ChestBlock.FACING;

public class LockedLootChestBlock extends BaseEntityBlock {

    private final VCChestTypes chestType;


    public LockedLootChestBlock(Properties properties, VCChestTypes chestType) {
        super(properties.noOcclusion());
        this.chestType = chestType;
    }

    public VCChestTypes getChestType() {
        return chestType;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        LockedLootChestBlockEntity lootChestBlock = (LockedLootChestBlockEntity) level.getBlockEntity(pos);

        ItemStack item = player.getItemInHand(hand);
        if (item.getItem() instanceof KeyItem keyItem) {
            if (keyItem.getChestType() == chestType) {
                lootChestBlock.DENY.stop();
                lootChestBlock.OPEN.start(player.tickCount);
                MenuProvider menuProvider = this.getMenuProvider(state, level, pos);
                if (menuProvider != null) {
                    player.openMenu(menuProvider);
                    player.awardStat(Stats.CUSTOM.get(Stats.OPEN_CHEST));
                    PiglinAi.angerNearbyPiglins(player, true);
                }
                return super.use(state, level, pos, player, hand, hit);
            }
        }

        lootChestBlock.OPEN.stop();
        lootChestBlock.DENY.start(player.tickCount);


        return InteractionResult.FAIL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new LockedLootChestBlockEntity(blockPos, blockState, chestType);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level currentLevel, @NotNull BlockState blockState, @NotNull BlockEntityType<T> blockEntityType) {
        return (level1, blockPos, block, t) -> {
            if (t instanceof LockedLootChestBlockEntity chestBlock) {
                chestBlock.tick(currentLevel, blockPos, blockState, chestBlock);
            }
        };
    }
}
