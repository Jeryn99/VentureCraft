package mc.craig.software.craftplus.common;

import mc.craig.software.craftplus.common.entities.Owl;
import mc.craig.software.craftplus.common.entities.Stalker;
import mc.craig.software.craftplus.common.entities.projectile.AdvancedArrow;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static mc.craig.software.craftplus.MinecraftPlus.MODID;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<Stalker>> STALKER = ENTITY_TYPES.register("stalker", () ->
            EntityType.Builder.of(Stalker::new, MobCategory.CREATURE)
                    .setTrackingRange(80)
                    .setUpdateInterval(3)
                    .setCustomClientFactory((ent, world) -> ModEntities.STALKER.get().create(world))
                    .setShouldReceiveVelocityUpdates(true)
                    .build(MODID + ":stalker"));

    public static final RegistryObject<EntityType<Owl>> OWL = ENTITY_TYPES.register("owl", () ->
            EntityType.Builder.of(Owl::new, MobCategory.AMBIENT)
                    .setTrackingRange(80)
                    .setUpdateInterval(3).sized(0.5F, 0.9F)
                    .setCustomClientFactory((ent, world) -> ModEntities.OWL.get().create(world))
                    .setShouldReceiveVelocityUpdates(true)
                    .build(MODID + ":owl"));

    public static final RegistryObject<EntityType<AdvancedArrow>> ADVANCED_ARROW = ENTITY_TYPES.register("advanced_arrow", () -> EntityType.Builder.<AdvancedArrow>of(AdvancedArrow::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build(MODID + ":advanced_arrow"));

}
