package mc.craig.software.craftplus.util;

import mc.craig.software.craftplus.MinecraftPlus;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class Tags {

    public static TagKey<Item> OWL_FOOD = makeItem(MinecraftPlus.MODID, "owl_food");
    public static TagKey<Biome> OWL_SPAWNS = makeBiome("owl_spawns");

    public static TagKey<Block> makeBlock(String domain, String path) {
        return BlockTags.create(new ResourceLocation(domain, path));
    }

    private static TagKey<Biome> makeBiome(String name) {
        return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(MinecraftPlus.MODID, name));
    }

    public static TagKey<Item> makeItem(String domain, String path) {
        return ItemTags.create(new ResourceLocation(domain, path));
    }


}
