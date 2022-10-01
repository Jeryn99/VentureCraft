package mc.craig.software.craftplus.common.entities.fabric;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import mc.craig.software.craftplus.VentureCraft;

public class VCComponents implements EntityComponentInitializer {

    public static final ComponentKey<VenturePlayerDataImpl> PLAYER_DATA =
            ComponentRegistryV3.INSTANCE.getOrCreate(VentureCraft.id("player_data"), VenturePlayerDataImpl.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(PLAYER_DATA, VenturePlayerDataImpl::new, RespawnCopyStrategy.CHARACTER);
    }
}
