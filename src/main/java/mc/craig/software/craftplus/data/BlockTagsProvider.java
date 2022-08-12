package mc.craig.software.craftplus.data;

import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.util.Tags;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class BlockTagsProvider extends TagsProvider<Block> {

    public BlockTagsProvider(DataGenerator dataGenerator, Registry<Block> itemRegistry, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, itemRegistry, MinecraftPlus.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        add(Tags.OWL_SIT, BlockTags.BEDS, BlockTags.LEAVES, BlockTags.FENCES);
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
