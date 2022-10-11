package mc.craig.software.craftplus.data.forge;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.util.ModTags;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class BiomeTagsProvider extends net.minecraft.data.tags.BiomeTagsProvider {

    public BiomeTagsProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, VentureCraft.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        for (Map.Entry<ResourceKey<Biome>, Biome> entry : ForgeRegistries.BIOMES.getEntries()) {
            Biome biome = entry.getValue();
            if (biome.getPrecipitation() == Biome.Precipitation.SNOW) {
                add(ModTags.OWL_SPAWNS, biome);
            }
        }
        add(ModTags.OWL_SPAWNS, BiomeTags.IS_FOREST, BiomeTags.IS_TAIGA, BiomeTags.IS_MOUNTAIN);

    }

    public void add(TagKey<Biome> branch, Biome biome) {
        this.tag(branch).add(biome);
    }

    public void add(TagKey<Biome> branch, TagKey<Biome>... biome) {
        this.tag(branch).addTags(biome);
    }

    public void add(TagKey<Biome> branch, Biome... biome) {
        this.tag(branch).add(biome);
    }
}
