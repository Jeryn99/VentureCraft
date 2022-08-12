package mc.craig.software.craftplus.data;

import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.util.Tags;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class EntityTypeTagsProvider extends TagsProvider<EntityType<?>> {

    public EntityTypeTagsProvider(DataGenerator dataGenerator, Registry<EntityType<?>> registry, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, registry, MinecraftPlus.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        add(Tags.OWL_ATTACK, EntityType.RABBIT, EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.CHICKEN, EntityType.TROPICAL_FISH, EntityType.PUFFERFISH, EntityType.COD, EntityType.SALMON);
    }

    public void add(TagKey<EntityType<?>> branch, EntityType<?> item) {
        this.tag(branch).add(item);
    }

    public void add(TagKey<EntityType<?>> branch, TagKey<EntityType<?>>... item) {
        this.tag(branch).addTags(item);
    }

    public void add(TagKey<EntityType<?>> branch, EntityType<?>... item) {
        this.tag(branch).add(item);
    }
}
