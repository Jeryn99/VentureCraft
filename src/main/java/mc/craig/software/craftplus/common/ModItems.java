package mc.craig.software.craftplus.common;

import mc.craig.software.craftplus.common.items.AdvancedArrowItem;
import mc.craig.software.craftplus.common.items.ClimbingGearItem;
import mc.craig.software.craftplus.common.items.ParagliderItem;
import mc.craig.software.craftplus.common.items.TierArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import static mc.craig.software.craftplus.MinecraftPlus.MODID;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);


    public static CreativeModeTab MAIN = new CreativeModeTab(MODID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.PARAGLIDER_SAPPHIRE.get());
        }
    };


    // Gliders
    public static final RegistryObject<ParagliderItem> PARAGLIDER_WOOD = ITEMS.register("paraglider_wood", () -> new ParagliderItem((new Item.Properties()).durability(30).tab(MAIN).rarity(Rarity.COMMON), ModItems.REINFORCED_PAPER));
    public static final RegistryObject<ParagliderItem> PARAGLIDER_IRON = ITEMS.register("paraglider_iron", () -> new ParagliderItem((new Item.Properties()).durability(50).tab(MAIN).rarity(Rarity.UNCOMMON), ModItems.REINFORCED_PAPER_IRON));
    public static final RegistryObject<ParagliderItem> PARAGLIDER_GOLD = ITEMS.register("paraglider_gold", () -> new ParagliderItem((new Item.Properties()).durability(70).tab(MAIN).rarity(Rarity.UNCOMMON), ModItems.REINFORCED_PAPER_GOLD));
    public static final RegistryObject<ParagliderItem> PARAGLIDER_DIAMOND = ITEMS.register("paraglider_diamond", () -> new ParagliderItem((new Item.Properties()).durability(200).tab(MAIN).rarity(Rarity.RARE), ModItems.REINFORCED_PAPER_DIAMOND));
    public static final RegistryObject<ParagliderItem> PARAGLIDER_NETHERITE = ITEMS.register("paraglider_netherite", () -> new ParagliderItem((new Item.Properties()).durability(350).tab(MAIN).rarity(Rarity.EPIC), ModItems.REINFORCED_PAPER_NETHERITE));
    public static final RegistryObject<ParagliderItem> PARAGLIDER_SAPPHIRE = ITEMS.register("paraglider_sapphire", () -> new ParagliderItem((new Item.Properties()).durability(200).tab(MAIN).rarity(Rarity.EPIC), ModItems.REINFORCED_PAPER_NETHERITE));

    // Re-inforced Paper
    public static final RegistryObject<Item> REINFORCED_PAPER = ITEMS.register("reinforced_paper", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> REINFORCED_PAPER_IRON = ITEMS.register("reinforced_paper_iron", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> REINFORCED_PAPER_GOLD = ITEMS.register("reinforced_paper_gold", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> REINFORCED_PAPER_DIAMOND = ITEMS.register("reinforced_paper_diamond", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> REINFORCED_PAPER_NETHERITE = ITEMS.register("reinforced_paper_netherite", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> REINFORCED_PAPER_SAPPHIRE = ITEMS.register("reinforced_paper_sapphire", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> COPPER_FILAMENT = ITEMS.register("copper_filament", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.COMMON)));


    // Run Armor 
    public static final RegistryObject<TierArmorItem> LEATHER_ARMOR_HEAD = ITEMS.register("leather_helmet", () -> new TierArmorItem(TierArmorItem.Tier.LEATHER, EquipmentSlot.HEAD, new Item.Properties().tab(MAIN)));
    public static final RegistryObject<TierArmorItem> LEATHER_ARMOR_CHEST = ITEMS.register("leather_chestplate", () -> new TierArmorItem(TierArmorItem.Tier.LEATHER, EquipmentSlot.CHEST, new Item.Properties().tab(MAIN)));
    public static final RegistryObject<TierArmorItem> LEATHER_ARMOR_LEGGINGS = ITEMS.register("leather_leggings", () -> new TierArmorItem(TierArmorItem.Tier.LEATHER, EquipmentSlot.LEGS, new Item.Properties().tab(MAIN)));
    public static final RegistryObject<TierArmorItem> LEATHER_ARMOR_FEET = ITEMS.register("leather_boots", () -> new TierArmorItem(TierArmorItem.Tier.LEATHER, EquipmentSlot.FEET, new Item.Properties().tab(MAIN)));

    // Spawn Eggs 
    public static final RegistryObject<SpawnEggItem> STALKERS_EGG = ITEMS.register("stalker_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.STALKER, DyeColor.BLACK.getTextColor(), DyeColor.ORANGE.getTextColor(), new Item.Properties().tab(MAIN)));
    public static final RegistryObject<SpawnEggItem> OWL_SPAWN_EGG = ITEMS.register("owl_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.OWL, DyeColor.BROWN.getTextColor(), DyeColor.YELLOW.getTextColor(), new Item.Properties().tab(MAIN)));

    public static final RegistryObject<Item> OWL_FEATHER = ITEMS.register("owl_feather", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> SAPPHIRE_GEM = ITEMS.register("sapphire_gem", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> UNREFINED_RUBY = ITEMS.register("unrefined_ruby", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CLIMBING_GEAR = ITEMS.register("climbing_gear", () -> new ClimbingGearItem(ArmorMaterials.LEATHER, EquipmentSlot.FEET, new Item.Properties().tab(MAIN).rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> TOTEM = ITEMS.register("totem", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.RARE)));

    // Keys 
    public static final RegistryObject<Item> IRON_KEY = ITEMS.register("iron_key", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> GOLD_KEY = ITEMS.register("gold_key", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> SAPPHIRE_KEY = ITEMS.register("sapphire_key", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> VOID_KEY = ITEMS.register("void_key", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.COMMON)));

    // Sapphire Tools
    public static final RegistryObject<Item> SAPPHIRE_SWORD = ITEMS.register("sapphire_sword", () -> new SwordItem(Tiers.DIAMOND, 3, -2.4F, (new Item.Properties()).tab(MAIN)));
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = ITEMS.register("sapphire_shovel", () -> new ShovelItem(Tiers.DIAMOND, 1.5F, -3.0F, (new Item.Properties()).tab(MAIN)));
    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = ITEMS.register("sapphire_pickaxe", () -> new PickaxeItem(Tiers.DIAMOND, 1, -2.8F, (new Item.Properties()).tab(MAIN)));
    public static final RegistryObject<Item> SAPPHIRE_AXE = ITEMS.register("sapphire_axe", () -> new AxeItem(Tiers.DIAMOND, 5.0F, -3.0F, (new Item.Properties()).tab(MAIN)));
    public static final RegistryObject<Item> SAPPHIRE_HOE = ITEMS.register("sapphire_hoe", () -> new HoeItem(Tiers.DIAMOND, -3, 0.0F, (new Item.Properties()).tab(MAIN)));

    public static final RegistryObject<Item> ADVANCED_ARROW = ITEMS.register("advanced_arrow", () -> new AdvancedArrowItem((new Item.Properties()).tab(MAIN)));

}
