package mc.craig.software.craftplus.util;

import mc.craig.software.craftplus.VentureCraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static TagKey<Item> OWL_FOOD = makeItem("owl_food");
    public static TagKey<Item> BIRD_POISON = makeItem("bird_poison");
    public static TagKey<Item> TOTEMS = makeItem("totems");
    public static TagKey<Biome> OWL_SPAWNS = makeBiome("owl_spawns");

    public static TagKey<Block> OWL_SIT = makeBlock("owl_sit");

    public static TagKey<EntityType<?>> OWL_ATTACK = makeEntityType("owl_attack");

    public static TagKey<Block> makeBlock(String path) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(VentureCraft.MODID, path));
    }

    private static TagKey<Biome> makeBiome(String name) {
        return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(VentureCraft.MODID, name));
    }

    private static TagKey<EntityType<?>> makeEntityType(String name) {
        return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(VentureCraft.MODID, name));
    }

    public static TagKey<Item> makeItem(String path) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(VentureCraft.MODID, path));
    }


}
