package mc.craig.software.craftplus.data;

import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.util.Tags;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ItemTagsProvider extends TagsProvider<Item> {

    public ItemTagsProvider(DataGenerator dataGenerator, Registry<Item> itemRegistry, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, itemRegistry, MinecraftPlus.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {

        for (Map.Entry<ResourceKey<Item>, Item> entry : ForgeRegistries.ITEMS.getEntries()) {
            Item item = entry.getValue();
            if(ForgeRegistries.ITEMS.getKey(item).getPath().contains("seed")){
                add(Tags.OWL_FOOD, item);
            }
        }

        add(Tags.OWL_FOOD, Items.RABBIT, Items.COOKED_RABBIT, Items.RABBIT_FOOT, Items.RABBIT_HIDE, Items.TURTLE_EGG, Items.SPIDER_EYE, Items.CHICKEN, Items.COOKED_CHICKEN);
        add(Tags.OWL_FOOD, ItemTags.FISHES);

        add(Tags.BIRD_POISON, Items.COOKIE, Items.CAKE);

    }

    public void add(TagKey<Item> branch, Item item) {
        this.tag(branch).add(item);
    }

    public void add(TagKey<Item> branch, TagKey<Item>... item) {
        this.tag(branch).addTags(item);
    }

    public void add(TagKey<Item> branch, Item... item) {
        this.tag(branch).add(item);
    }
}
