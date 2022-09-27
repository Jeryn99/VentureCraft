package mc.craig.software.craftplus.common;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;

public class OreBlock extends DropExperienceBlock {
    public OreBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
