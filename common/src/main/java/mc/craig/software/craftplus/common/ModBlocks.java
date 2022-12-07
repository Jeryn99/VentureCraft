package mc.craig.software.craftplus.common;

import com.google.common.base.Supplier;
import mc.craig.software.craftplus.common.block.*;
import net.minecraft.core.Registry;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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

    public static RegistrySupplier<Block> POT = register("pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.STONE).color(MaterialColor.DEEPSLATE).strength(0.2F, 0.2F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> BLACK_PEDESTAL = register("black_pedastal", () -> new PedastalBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> BLUE_PEDASTAL = register("blue_pedastal", () -> new PedastalBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> BROWN_PEDASTAL = register("brown_pedastal", () -> new PedastalBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> CYAN_PEDASTAL = register("cyan_pedastal", () -> new PedastalBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> GREEN_PEDASTAL = register("green_pedastal", () -> new PedastalBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> GREY_PEDASTAL = register("grey_pedastal", () -> new PedastalBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> LIGHT_BLUE_PEDASTAL = register("light_blue_pedastal", () -> new PedastalBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> LIGHT_GREY_PEDASTAL = register("light_grey_pedastal", () -> new PedastalBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> LIME_PEDASTAL = register("lime_pedastal", () -> new PedastalBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> MAGENTA_PEDASTAL = register("magenta_pedastal", () -> new PedastalBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> ORANGE_PEDASTAL = register("orange_pedastal", () -> new PedastalBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> PINK_PEDASTAL = register("pink_pedastal", () -> new PedastalBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> PURPLE_PEDASTAL = register("purple_pedastal", () -> new PedastalBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> RED_PEDASTAL = register("red_pedastal", () -> new PedastalBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> WHITE_PEDASTAL = register("white_pedastal", () -> new PedastalBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> YELLOW_PEDASTAL = register("yellow_pedastal", () -> new PedastalBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> BELL = register("bell", () -> new BellBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);
    public static RegistrySupplier<Block> CATALYST = register("catalyst", () -> new CatalystBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), ModItems.MAIN);

    public static RegistrySupplier<Block> RUBY_ORE = register("ruby_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), UniformInt.of(3, 7)), ModItems.MAIN);
    public static RegistrySupplier<Block> DEEPSLATE_RUBY_ORE = register("deepslate_ruby_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(EMERALD_ORE).color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE), UniformInt.of(3, 7)), ModItems.MAIN);
    public static RegistrySupplier<Block> SAPPHIRE_LOOT_CHEST = register("sapphire_loot_chest", () -> new LockedLootChestBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD), VCChestTypes.SAPPHIRE), ModItems.MAIN);
    public static RegistrySupplier<Block> GOLD_LOOT_CHEST = register("gold_loot_chest", () -> new LockedLootChestBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD), VCChestTypes.GOLD), ModItems.MAIN);
    public static RegistrySupplier<Block> VOID_LOOT_CHEST = register("void_loot_chest", () -> new LockedLootChestBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD), VCChestTypes.VOID), ModItems.MAIN);
    public static RegistrySupplier<Block> IRON_LOOT_CHEST = register("iron_loot_chest", () -> new LockedLootChestBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD), VCChestTypes.IRON), ModItems.MAIN);

    public static RegistrySupplier<Block> TRINKET_HOVERBOARD = register("trinket_hoverboard", () -> new TrinketBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).noOcclusion().sound(SoundType.WOOD)), ModItems.MAIN);
    public static RegistrySupplier<Block> TRINKET_TARDIS = register("trinket_tardis", () -> new TrinketBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).noOcclusion().sound(SoundType.WOOD)), ModItems.MAIN);
    public static RegistrySupplier<Block> TRINKET_LIBERTY = register("trinket_liberty", () -> new TrinketBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).noOcclusion().sound(SoundType.WOOD)), ModItems.MAIN);

    /**
     * Registers a Block and BlockItem to the ItemGroup of your choice
     */
    private static <T extends Block> RegistrySupplier<T> register(String id, Supplier<T> blockSupplier, CreativeModeTab itemGroup) {
        RegistrySupplier<T> RegistrySupplier = BLOCKS.register(id, blockSupplier);
        ModItems.ITEMS.register(id, () -> new BlockItem(RegistrySupplier.get(), new Item.Properties().tab(itemGroup)));
        return RegistrySupplier;
    }

}
