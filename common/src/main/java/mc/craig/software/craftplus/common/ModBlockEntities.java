package mc.craig.software.craftplus.common;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.common.blockentity.LockedLootChestBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(VentureCraft.MODID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

    public static RegistrySupplier<BlockEntityType<LockedLootChestBlockEntity>> LOOT_CHEST = TILES.register("loot_chest", () -> registerTiles(LockedLootChestBlockEntity::new, ModBlocks.LOOT_CHESTS.get()));


    private static <T extends BlockEntity> BlockEntityType<T> registerTiles(BlockEntityType.BlockEntitySupplier<T> tile, Block validBlock) {
        return BlockEntityType.Builder.of(tile, validBlock).build(null);
    }


}
