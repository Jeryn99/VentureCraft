package mc.craig.software.craftplus.data.forge;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.util.ModTags;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class EntityTypeTagsProvider extends net.minecraft.data.tags.EntityTypeTagsProvider {

    public EntityTypeTagsProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, VentureCraft.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        add(ModTags.OWL_ATTACK, EntityType.RABBIT, EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.CHICKEN, EntityType.TROPICAL_FISH, EntityType.PUFFERFISH, EntityType.COD, EntityType.SALMON);
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
