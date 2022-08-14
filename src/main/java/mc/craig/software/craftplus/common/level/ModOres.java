package mc.craig.software.craftplus.common.level;

import com.google.common.collect.ImmutableList;
import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.common.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModOres {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, MinecraftPlus.MODID);
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, MinecraftPlus.MODID);

    // Configured
    public static RegistryObject<ConfiguredFeature<?, ?>> ORE_SAPPHIRE_CONFIGURED = CONFIGURED_FEATURES.register("ore_sapphire", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SAPPHIRE_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get().defaultBlockState())), 9)));
    public static RegistryObject<ConfiguredFeature<?, ?>> ORE_SAPPHIRE_BURIED_CONFIGURED = CONFIGURED_FEATURES.register("ore_sapphire_buried", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SAPPHIRE_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get().defaultBlockState())), 4)));
    public static RegistryObject<ConfiguredFeature<?, ?>> ORE_SAPPHIRE_LARGE_CONFIGURED = CONFIGURED_FEATURES.register("ore_sapphire_large", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SAPPHIRE_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get().defaultBlockState())), 4)));
    public static RegistryObject<ConfiguredFeature<?, ?>> ORE_RUBY_CONFIGURED = CONFIGURED_FEATURES.register("ore_ruby", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.RUBY_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_RUBY_ORE.get().defaultBlockState())), 3)));
    public static RegistryObject<ConfiguredFeature<?, ?>> ORE_DIAMOND_CONFIGURED = CONFIGURED_FEATURES.register("ore_diamond", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Blocks.DIAMOND_ORE.defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_DIAMOND_ORE.defaultBlockState())), 3)));


    // Placed
    public static RegistryObject<PlacedFeature> ORE_SAPPHIRE_BURIED = PLACED_FEATURES.register("ore_sapphire_buried", () -> new PlacedFeature(Holder.direct(ORE_SAPPHIRE_BURIED_CONFIGURED.get()), List.copyOf(commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))))));
    public static RegistryObject<PlacedFeature> ORE_RUBY = PLACED_FEATURES.register("ore_ruby", () -> new PlacedFeature(Holder.direct(ORE_RUBY_CONFIGURED.get()), List.copyOf(commonOrePlacement(100, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(480))))));
    public static RegistryObject<PlacedFeature> ORE_SAPPHIRE_LARGE = PLACED_FEATURES.register("ore_sapphire_large", () -> new PlacedFeature(Holder.direct(ORE_SAPPHIRE_LARGE_CONFIGURED.get()), List.copyOf(rareOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))))));
    public static RegistryObject<PlacedFeature> ORE_SAPPHIRE = PLACED_FEATURES.register("ore_sapphire", () -> new PlacedFeature(Holder.direct(ORE_SAPPHIRE_CONFIGURED.get()), List.copyOf(commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))))));
    public static RegistryObject<PlacedFeature> ORE_DIAMOND = PLACED_FEATURES.register("ore_diamond", () -> new PlacedFeature(Holder.direct(ORE_DIAMOND_CONFIGURED.get()), List.copyOf(commonOrePlacement(100, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(480))))));


    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }


}
