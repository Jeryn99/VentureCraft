package mc.craig.software.craftplus.data.forge;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.common.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

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
    }
}
