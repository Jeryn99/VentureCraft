package mc.craig.software.craftplus.common;

import mc.craig.software.craftplus.common.entities.Owl;
import mc.craig.software.craftplus.common.entities.Stalker;
import mc.craig.software.craftplus.common.entities.projectile.AdvancedArrow;
import mc.craig.software.craftplus.util.ModSpawningRules;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.EntityAttributeRegistry;
import net.threetag.palladiumcore.registry.RegistrySupplier;

import static mc.craig.software.craftplus.VentureCraft.MODID;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(MODID, Registry.ENTITY_TYPE_REGISTRY);

    public static final RegistrySupplier<EntityType<Stalker>> STALKER = ENTITY_TYPES.register("stalker", () ->
            EntityType.Builder.of(Stalker::new, MobCategory.CREATURE)
                    .build(MODID + ":stalker"));

    public static final RegistrySupplier<EntityType<Owl>> OWL = ENTITY_TYPES.register("owl", () ->
            EntityType.Builder.of(Owl::new, MobCategory.AMBIENT)
                    .sized(0.5F, 0.9F)
                    .build(MODID + ":owl"));

    public static final RegistrySupplier<EntityType<AdvancedArrow>> ADVANCED_ARROW = ENTITY_TYPES.register("advanced_arrow", () -> EntityType.Builder.<AdvancedArrow>of(AdvancedArrow::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build(MODID + ":advanced_arrow"));

    public static void initAttributes() {
        EntityAttributeRegistry.register(STALKER, Stalker::createAttributes);
        EntityAttributeRegistry.register(OWL, Owl::createAttributes);
    }

    public static void initSpawns() {
        SpawnPlacements.register(ModEntities.OWL.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, ModSpawningRules::checkOwlSpawnRules);
        SpawnPlacements.register(ModEntities.STALKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, ModSpawningRules::checkStalkerSpawnRules);
    }

}
