package mc.craig.software.craftplus.common;

import mc.craig.software.craftplus.common.entities.OwlEntity;
import mc.craig.software.craftplus.common.entities.StalkerEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static mc.craig.software.craftplus.MinecraftPlus.MODID;

public class Entities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<StalkerEntity>> STALKER = ENTITY_TYPES.register("stalker", () ->
            EntityType.Builder.of(StalkerEntity::new, MobCategory.CREATURE)
                    .setTrackingRange(80)
                    .setUpdateInterval(3)
                    .setCustomClientFactory((ent, world) -> Entities.STALKER.get().create(world))
                    .setShouldReceiveVelocityUpdates(true)
                    .build(MODID + ":stalker"));

    public static final RegistryObject<EntityType<OwlEntity>> OWL = ENTITY_TYPES.register("owl", () ->
            EntityType.Builder.of(OwlEntity::new, MobCategory.AMBIENT)
                    .setTrackingRange(80)
                    .setUpdateInterval(3)
                    .setCustomClientFactory((ent, world) -> Entities.OWL.get().create(world))
                    .setShouldReceiveVelocityUpdates(true)
                    .build(MODID + ":owl"));

}
