package mc.craig.software.craftplus.util;

import mc.craig.software.craftplus.common.entities.Owl;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ServerLevelAccessor;

public class ModSpawningRules {

    public static boolean checkOwlSpawnRules(EntityType<Owl> owlEntityEntityType, ServerLevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        return Monster.isDarkEnoughToSpawn(levelAccessor, blockPos, randomSource);
    }

}
