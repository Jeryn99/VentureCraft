package mc.craig.software.craftplus.common.entities.ai.owl;

import mc.craig.software.craftplus.common.entities.Owl;
import mc.craig.software.craftplus.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;

import java.util.EnumSet;

public class OwlSitOnBlocks extends MoveToBlockGoal {
    private final Owl owl;

    public OwlSitOnBlocks(Owl owl, double p_25136_, int p_25137_) {
        super(owl, p_25136_, p_25137_, 6);
        this.owl = owl;
        this.verticalSearchStart = -2;
        this.setFlags(EnumSet.of(Flag.JUMP, Goal.Flag.MOVE));
    }

    public boolean canUse() {
        return this.owl.isTame() && !this.owl.isOrderedToSit() && !this.owl.isInSittingPose() && super.canUse();
    }

    public void start() {
        super.start();
        this.owl.setInSittingPose(false);
    }

    protected int nextStartTick(PathfinderMob p_25140_) {
        return 40;
    }

    public void stop() {
        super.stop();
        this.owl.setOrderedToSit(false);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.isReachedTarget()) {
            this.owl.setOrderedToSit(false);
        } else if (!this.owl.isOrderedToSit()) {
            this.owl.setOrderedToSit(true);
        }

    }

    @Override
    protected boolean isValidTarget(LevelReader levelReader, BlockPos blockPos) {
        return levelReader.isEmptyBlock(blockPos.above()) && levelReader.getBlockState(blockPos).is(ModTags.OWL_SIT);
    }
}