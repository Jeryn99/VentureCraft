package mc.craig.software.craftplus.data;

import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.common.ModBlocks;
import mc.craig.software.craftplus.util.Tags;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

public class BlockTagsProvider extends TagsProvider<Block> {

    public BlockTagsProvider(DataGenerator dataGenerator, Registry<Block> itemRegistry, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, itemRegistry, MinecraftPlus.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        add(Tags.OWL_SIT, BlockTags.BEDS, BlockTags.LEAVES, BlockTags.FENCES);
        add(BlockTags.MINEABLE_WITH_PICKAXE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), ModBlocks.SAPPHIRE_ORE.get(), ModBlocks.RUBY_ORE.get());
        add(net.minecraftforge.common.Tags.Blocks.NEEDS_GOLD_TOOL, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), ModBlocks.SAPPHIRE_ORE.get(), ModBlocks.RUBY_ORE.get());
        add(net.minecraftforge.common.Tags.Blocks.ORES, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), ModBlocks.SAPPHIRE_ORE.get(), ModBlocks.RUBY_ORE.get());
        add(net.minecraftforge.common.Tags.Blocks.ORES_IN_GROUND_DEEPSLATE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
        add(net.minecraftforge.common.Tags.Blocks.ORES_IN_GROUND_STONE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), ModBlocks.RUBY_ORE.get());

    }

    public void add(TagKey<Block> branch, Block item) {
        this.tag(branch).add(item);
    }

    public void add(TagKey<Block> branch, TagKey<Block>... item) {
        this.tag(branch).addTags(item);
    }

    public void add(TagKey<Block> branch, Block... item) {
        this.tag(branch).add(item);
    }
}
