package mc.craig.software.craftplus.common;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.common.block.VCChestTypes;
import mc.craig.software.craftplus.common.items.*;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.threetag.palladiumcore.item.PalladiumSpawnEggItem;
import net.threetag.palladiumcore.registry.CreativeModeTabRegistry;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;

import static mc.craig.software.craftplus.VentureCraft.MODID;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MODID, Registry.ITEM_REGISTRY);
    public static CreativeModeTab MAIN = CreativeModeTabRegistry.create(VentureCraft.id("main"), () -> new ItemStack(ModItems.PARAGLIDER_SAPPHIRE.get()));


    // Gliders
    public static final RegistrySupplier<ParagliderItem> PARAGLIDER_WOOD = ITEMS.register("paraglider_wood", () -> new ParagliderItem((new Item.Properties()).durability(30).tab(MAIN).rarity(Rarity.COMMON), ModItems.REINFORCED_PAPER));
    public static final RegistrySupplier<ParagliderItem> PARAGLIDER_IRON = ITEMS.register("paraglider_iron", () -> new ParagliderItem((new Item.Properties()).durability(50).tab(MAIN).rarity(Rarity.UNCOMMON), ModItems.REINFORCED_PAPER_IRON));
    public static final RegistrySupplier<ParagliderItem> PARAGLIDER_GOLD = ITEMS.register("paraglider_gold", () -> new ParagliderItem((new Item.Properties()).durability(70).tab(MAIN).rarity(Rarity.UNCOMMON), ModItems.REINFORCED_PAPER_GOLD));
    public static final RegistrySupplier<ParagliderItem> PARAGLIDER_DIAMOND = ITEMS.register("paraglider_diamond", () -> new ParagliderItem((new Item.Properties()).durability(200).tab(MAIN).rarity(Rarity.RARE), ModItems.REINFORCED_PAPER_DIAMOND));
    public static final RegistrySupplier<ParagliderItem> PARAGLIDER_NETHERITE = ITEMS.register("paraglider_netherite", () -> new ParagliderItem((new Item.Properties()).durability(350).tab(MAIN).rarity(Rarity.EPIC), ModItems.REINFORCED_PAPER_NETHERITE));
    public static final RegistrySupplier<ParagliderItem> PARAGLIDER_SAPPHIRE = ITEMS.register("paraglider_sapphire", () -> new ParagliderItem((new Item.Properties()).durability(200).tab(MAIN).rarity(Rarity.EPIC), ModItems.REINFORCED_PAPER_NETHERITE));

    // Re-inforced Paper
    public static final RegistrySupplier<Item> REINFORCED_PAPER = ITEMS.register("reinforced_paper", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.COMMON)));
    public static final RegistrySupplier<Item> REINFORCED_PAPER_IRON = ITEMS.register("reinforced_paper_iron", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.UNCOMMON)));
    public static final RegistrySupplier<Item> REINFORCED_PAPER_GOLD = ITEMS.register("reinforced_paper_gold", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.UNCOMMON)));
    public static final RegistrySupplier<Item> REINFORCED_PAPER_DIAMOND = ITEMS.register("reinforced_paper_diamond", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> REINFORCED_PAPER_NETHERITE = ITEMS.register("reinforced_paper_netherite", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> REINFORCED_PAPER_SAPPHIRE = ITEMS.register("reinforced_paper_sapphire", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.EPIC)));

    public static final RegistrySupplier<Item> COPPER_FILAMENT = ITEMS.register("copper_filament", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.COMMON)));


    /*  // Run Armor
      public static final RegistrySupplier<TierArmorItem> LEATHER_ARMOR_HEAD = ITEMS.register("leather_helmet", () -> new TierArmorItem(TierArmorItem.Tier.LEATHER, EquipmentSlot.HEAD, new Item.Properties().tab(MAIN)));
      public static final RegistrySupplier<TierArmorItem> LEATHER_ARMOR_CHEST = ITEMS.register("leather_chestplate", () -> new TierArmorItem(TierArmorItem.Tier.LEATHER, EquipmentSlot.CHEST, new Item.Properties().tab(MAIN)));
      public static final RegistrySupplier<TierArmorItem> LEATHER_ARMOR_LEGGINGS = ITEMS.register("leather_leggings", () -> new TierArmorItem(TierArmorItem.Tier.LEATHER, EquipmentSlot.LEGS, new Item.Properties().tab(MAIN)));
      public static final RegistrySupplier<TierArmorItem> LEATHER_ARMOR_FEET = ITEMS.register("leather_boots", () -> new TierArmorItem(TierArmorItem.Tier.LEATHER, EquipmentSlot.FEET, new Item.Properties().tab(MAIN)));
  */
    // Spawn Eggs 
    public static final RegistrySupplier<SpawnEggItem> STALKERS_EGG = ITEMS.register("stalker_spawn_egg", () -> new PalladiumSpawnEggItem(ModEntities.STALKER, DyeColor.BLACK.getTextColor(), DyeColor.ORANGE.getTextColor(), new Item.Properties().tab(MAIN)));
    public static final RegistrySupplier<SpawnEggItem> OWL_SPAWN_EGG = ITEMS.register("owl_spawn_egg", () -> new PalladiumSpawnEggItem(ModEntities.OWL, DyeColor.BROWN.getTextColor(), DyeColor.YELLOW.getTextColor(), new Item.Properties().tab(MAIN)));

    public static final RegistrySupplier<Item> OWL_FEATHER = ITEMS.register("owl_feather", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.COMMON)));
    public static final RegistrySupplier<Item> SAPPHIRE_GEM = ITEMS.register("sapphire_gem", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> UNREFINED_RUBY = ITEMS.register("unrefined_ruby", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> CLIMBING_GEAR = ITEMS.register("climbing_gear", () -> new ClimbingGearItem(ArmorMaterials.LEATHER, EquipmentSlot.FEET, new Item.Properties().tab(MAIN).rarity(Rarity.COMMON)));

    public static final RegistrySupplier<Item> TOTEM = ITEMS.register("totem", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.RARE)));

    // Keys 
    public static final RegistrySupplier<Item> IRON_KEY = ITEMS.register("iron_key", () -> new KeyItem(new Item.Properties().tab(MAIN).rarity(Rarity.COMMON), VCChestTypes.IRON));
    public static final RegistrySupplier<Item> GOLD_KEY = ITEMS.register("gold_key", () -> new KeyItem(new Item.Properties().tab(MAIN).rarity(Rarity.COMMON), VCChestTypes.GOLD));
    public static final RegistrySupplier<Item> SAPPHIRE_KEY = ITEMS.register("sapphire_key", () -> new KeyItem(new Item.Properties().tab(MAIN).rarity(Rarity.COMMON), VCChestTypes.SAPPHIRE));
    public static final RegistrySupplier<Item> VOID_KEY = ITEMS.register("void_key", () -> new KeyItem(new Item.Properties().tab(MAIN).rarity(Rarity.COMMON), VCChestTypes.VOID));
    public static final RegistrySupplier<Item> BIG_KEY = ITEMS.register("big_key", () -> new KeyItem(new Item.Properties().tab(MAIN).rarity(Rarity.COMMON), null));

    // Sapphire Tools
    public static final RegistrySupplier<Item> SAPPHIRE_SWORD = ITEMS.register("sapphire_sword", () -> new SwordItem(Tiers.DIAMOND, 3, -2.4F, (new Item.Properties()).tab(MAIN)));
    public static final RegistrySupplier<Item> SAPPHIRE_SHOVEL = ITEMS.register("sapphire_shovel", () -> new ShovelItem(Tiers.DIAMOND, 1.5F, -3.0F, (new Item.Properties()).tab(MAIN)));
    public static final RegistrySupplier<Item> SAPPHIRE_PICKAXE = ITEMS.register("sapphire_pickaxe", () -> new PickaxeItem(Tiers.DIAMOND, 1, -2.8F, (new Item.Properties()).tab(MAIN)));
    public static final RegistrySupplier<Item> SAPPHIRE_AXE = ITEMS.register("sapphire_axe", () -> new AxeItem(Tiers.DIAMOND, 5.0F, -3.0F, (new Item.Properties()).tab(MAIN)));
    public static final RegistrySupplier<Item> SAPPHIRE_HOE = ITEMS.register("sapphire_hoe", () -> new HoeItem(Tiers.DIAMOND, -3, 0.0F, (new Item.Properties()).tab(MAIN)));

    public static final RegistrySupplier<Item> ADVANCED_ARROW = ITEMS.register("advanced_arrow", () -> new AdvancedArrowItem((new Item.Properties()).tab(MAIN)));

}
