package mc.craig.software.craftplus.client.models;

import net.minecraft.world.level.block.entity.BlockEntity;

public interface AnimatableBEModel<T extends BlockEntity> {

    void animate(T blockentity);

}
