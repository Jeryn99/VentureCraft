package mc.craig.software.craftplus.common.blockentity;

import mc.craig.software.craftplus.common.ModBlockEntities;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.Iterator;

public class CatalystBlockEntity extends BlockEntity implements BlockEntityTicker<CatalystBlockEntity> {

    public AnimationState CALM = new AnimationState();
    public AnimationState AGITATED = new AnimationState();

    public CatalystBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.CATALYST.get(), blockPos, blockState);
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, CatalystBlockEntity blockEntity) {
        if(!AGITATED.isStarted()){
            AGITATED.start(0);
        }

        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();

        for (ServerPlayer serverPlayer : level.getEntitiesOfClass(ServerPlayer.class, (new AABB(x, y, x, x, y - 4, z)).inflate(10.0, 5.0, 10.0))) {
            if (serverPlayer.gameMode.isSurvival()) {
                serverPlayer.gameMode.changeGameModeForPlayer(GameType.ADVENTURE);
            }
        }
    }
}
