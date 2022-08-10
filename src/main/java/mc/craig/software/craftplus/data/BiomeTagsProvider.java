package mc.craig.software.craftplus.data;

import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.util.Tags;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class BiomeTagsProvider extends TagsProvider<Biome> {

    public BiomeTagsProvider(DataGenerator dataGenerator, Registry<Biome> biomes, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, biomes, MinecraftPlus.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {

        for (Map.Entry<ResourceKey<Biome>, Biome> entry : ForgeRegistries.BIOMES.getEntries()) {
            Biome biome = entry.getValue();
            if(ForgeRegistries.BIOMES.getKey(biome).getPath().contains("jungle")){
                add(Tags.OWL_SPAWNS, biome);
            }
        }
    }

    public void add(TagKey<Biome> branch, Biome biome) {
        this.tag(branch).add(biome);
    }

    public void add(TagKey<Biome> branch, Biome... biome) {
        this.tag(branch).add(biome);
    }
}
