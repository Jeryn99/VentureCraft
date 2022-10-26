package mc.craig.software.craftplus.common;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.common.block.PedastalBlock;
import mc.craig.software.craftplus.common.block.VCChestTypes;
import mc.craig.software.craftplus.common.blockentity.BigBellBlockEntity;
import mc.craig.software.craftplus.common.blockentity.CatalystBlockEntity;
import mc.craig.software.craftplus.common.blockentity.LockedLootChestBlockEntity;
import mc.craig.software.craftplus.common.blockentity.PedastalBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;

import java.util.ArrayList;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(VentureCraft.MODID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

    public static RegistrySupplier<BlockEntityType<LockedLootChestBlockEntity>> LOOT_CHEST = TILES.register("loot_chest", () -> registerTiles((blockPos, state) -> new LockedLootChestBlockEntity(blockPos, state, VCChestTypes.SAPPHIRE), ModBlocks.VOID_LOOT_CHEST.get(), ModBlocks.GOLD_LOOT_CHEST.get(), ModBlocks.IRON_LOOT_CHEST.get(), ModBlocks.SAPPHIRE_LOOT_CHEST.get()));
    public static RegistrySupplier<BlockEntityType<PedastalBlockEntity>> PEDASTAL = TILES.register("pedastal", () -> registerTiles(PedastalBlockEntity::new, getPedastals()));
    public static RegistrySupplier<BlockEntityType<CatalystBlockEntity>> CATALYST = TILES.register("catalyst", () -> registerTiles(CatalystBlockEntity::new, ModBlocks.CATALYST.get()));
    public static RegistrySupplier<BlockEntityType<BigBellBlockEntity>> BELL = TILES.register("bell", () -> registerTiles(BigBellBlockEntity::new, ModBlocks.BELL.get()));


    private static <T extends BlockEntity> BlockEntityType<T> registerTiles(BlockEntityType.BlockEntitySupplier<T> tile, Block... validBlocks) {
        return BlockEntityType.Builder.of(tile, validBlocks).build(null);
    }


    private static Block[] getPedastals(){
        ArrayList<Block> pedastals = new ArrayList<>();
        for (RegistrySupplier<Block> entry : ModBlocks.BLOCKS.getEntries()) {
            if(entry.get() instanceof PedastalBlock pedastalBlock){
                pedastals.add(pedastalBlock);
            }
        }
        return pedastals.toArray(new Block[0]);
    };

}
