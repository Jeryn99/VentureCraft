package mc.craig.software.craftplus.util;

import mc.craig.software.craftplus.common.entities.Owl;
import mc.craig.software.craftplus.common.entities.Stalker;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ServerLevelAccessor;

public class ModSpawningRules {

    public static boolean checkOwlSpawnRules(EntityType<Owl> owlEntityType, ServerLevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        return Monster.isDarkEnoughToSpawn(levelAccessor, blockPos, randomSource);
    }

    public static boolean checkStalkerSpawnRules(EntityType<Stalker> stalkerEntityType, ServerLevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        return Monster.isDarkEnoughToSpawn(levelAccessor, blockPos, randomSource) && !levelAccessor.canSeeSky(blockPos) && blockPos.getY() < 200;
    }

}
