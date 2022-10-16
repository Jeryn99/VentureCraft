package mc.craig.software.craftplus.common;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.common.block.VCChestTypes;
import mc.craig.software.craftplus.common.blockentity.LockedLootChestBlockEntity;
import mc.craig.software.craftplus.common.blockentity.PedastalBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(VentureCraft.MODID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

    public static RegistrySupplier<BlockEntityType<LockedLootChestBlockEntity>> LOOT_CHEST = TILES.register("loot_chest", () -> registerTiles((blockPos, state) -> new LockedLootChestBlockEntity(blockPos, state, VCChestTypes.SAPPHIRE), ModBlocks.VOID_LOOT_CHEST.get(), ModBlocks.GOLD_LOOT_CHEST.get(), ModBlocks.IRON_LOOT_CHEST.get(), ModBlocks.SAPPHIRE_LOOT_CHEST.get()));
    public static RegistrySupplier<BlockEntityType<PedastalBlockEntity>> PEDASTAL = TILES.register("pedastal", () -> registerTiles(PedastalBlockEntity::new, ModBlocks.PEDASTAL.get()));


    private static <T extends BlockEntity> BlockEntityType<T> registerTiles(BlockEntityType.BlockEntitySupplier<T> tile, Block... validBlocks) {
        return BlockEntityType.Builder.of(tile, validBlocks).build(null);
    }


}
