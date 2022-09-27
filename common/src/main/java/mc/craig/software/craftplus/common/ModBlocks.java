package mc.craig.software.craftplus.common;

import com.google.common.base.Supplier;
import mc.craig.software.craftplus.common.block.LockedLootChestBlock;
import net.minecraft.core.Registry;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;

import static mc.craig.software.craftplus.VentureCraft.MODID;
import static net.minecraft.world.level.block.Blocks.EMERALD_ORE;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MODID, Registry.BLOCK_REGISTRY);

    public static RegistrySupplier<Block> SAPPHIRE_ORE = register("sapphire_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), UniformInt.of(3, 7)), ModItems.MAIN);
    public static RegistrySupplier<Block> DEEPSLATE_SAPPHIRE_ORE = register("deepslate_sapphire_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE), UniformInt.of(3, 7)), ModItems.MAIN);

    public static RegistrySupplier<Block> RUBY_ORE = register("ruby_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), UniformInt.of(3, 7)), ModItems.MAIN);
    public static RegistrySupplier<Block> DEEPSLATE_RUBY_ORE = register("deepslate_ruby_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(EMERALD_ORE).color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE), UniformInt.of(3, 7)), ModItems.MAIN);
    public static RegistrySupplier<Block> LOOT_CHESTS = register("loot_chest", () -> new LockedLootChestBlock(BlockBehaviour.Properties.copy(EMERALD_ORE).color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);

    /**
     * Registers a Block and BlockItem to the ItemGroup of your choice
     */
    private static <T extends Block> RegistrySupplier<T> register(String id, Supplier<T> blockSupplier, CreativeModeTab itemGroup) {
        RegistrySupplier<T> RegistrySupplier = BLOCKS.register(id, blockSupplier);
        ModItems.ITEMS.register(id, () -> new BlockItem(RegistrySupplier.get(), new Item.Properties().tab(itemGroup)));
        return RegistrySupplier;
    }

}
