package mc.craig.software.craftplus.data.forge;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.common.ModBlocks;
import mc.craig.software.craftplus.common.block.PedastalBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.threetag.palladiumcore.registry.RegistrySupplier;

public class ModelProviderBlock extends BlockStateProvider {

    public ModelProviderBlock(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, VentureCraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.SAPPHIRE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
        simpleBlock(ModBlocks.RUBY_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_RUBY_ORE.get());

        for (RegistrySupplier<Block> entry : ModBlocks.BLOCKS.getEntries()) {
            if (entry.get() instanceof PedastalBlock pedastalBlock) {
                ResourceLocation blockKey = key(pedastalBlock);
                simpleBlock(pedastalBlock, pedastal(name(pedastalBlock), blockTexture(pedastalBlock)));
            }
        }
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    public BlockModelBuilder pedastal(String name, ResourceLocation texture) {
        return models().singleTexture(name, new ResourceLocation(VentureCraft.MODID, ModelProvider.BLOCK_FOLDER + "/pedastal"), "0", texture);
    }
}
