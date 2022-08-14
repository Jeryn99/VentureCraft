package mc.craig.software.craftplus.common;

import com.google.common.base.Supplier;
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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static mc.craig.software.craftplus.MinecraftPlus.MODID;
import static net.minecraft.world.level.block.Blocks.EMERALD_ORE;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static RegistryObject<Block> SAPPHIRE_ORE = register("sapphire_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), UniformInt.of(3, 7)), ModItems.MAIN);
    public static RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = register("deepslate_sapphire_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE), UniformInt.of(3, 7)), ModItems.MAIN);

    public static RegistryObject<Block> RUBY_ORE = register("ruby_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), UniformInt.of(3, 7)), ModItems.MAIN);
    public static RegistryObject<Block> DEEPSLATE_RUBY_ORE = register("deepslate_ruby_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(EMERALD_ORE).color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE), UniformInt.of(3, 7)), ModItems.MAIN);

    /**
     * Registers a Block and BlockItem to the ItemGroup of your choice
     */
    private static <T extends Block> RegistryObject<T> register(String id, Supplier<T> blockSupplier, CreativeModeTab itemGroup) {
        RegistryObject<T> registryObject = BLOCKS.register(id, blockSupplier);
        ModItems.ITEMS.register(id, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(itemGroup)));
        return registryObject;
    }

}
