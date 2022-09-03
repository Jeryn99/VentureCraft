package mc.craig.software.craftplus.common;

import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.common.blockentity.LockedLootChestBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MinecraftPlus.MODID);

    public static RegistryObject<BlockEntityType<LockedLootChestBlockEntity>> LOOT_CHEST = TILES.register("loot_chest", () -> registerTiles(LockedLootChestBlockEntity::new, ModBlocks.LOOT_CHESTS.get()));


    private static <T extends BlockEntity> BlockEntityType<T> registerTiles(BlockEntityType.BlockEntitySupplier<T> tile, Block validBlock) {
        return BlockEntityType.Builder.of(tile, validBlock).build(null);
    }


}
