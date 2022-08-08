package mc.craig.software.notnotyet.common;

import mc.craig.software.notnotyet.common.items.ParagliderItem;
import mc.craig.software.notnotyet.common.items.TierArmor;
import mc.craig.software.notnotyet.util.Utils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import static mc.craig.software.notnotyet.MinecraftPlus.MODID;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);    public static CreativeModeTab MAIN = new CreativeModeTab(MODID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.PARAGLIDER_WOOD.get());
        }
    };


    public static final RegistryObject<SpawnEggItem> STALKERS_EGG = ITEMS.register("stalker_spawn_egg", () -> new ForgeSpawnEggItem(Entities.STALKER, 0x000000, 0x3F0000, new Item.Properties().tab(MAIN)));

    // Gliders
    public static final RegistryObject<ParagliderItem> PARAGLIDER_WOOD = ITEMS.register("paraglider_wood", () -> new ParagliderItem((new Item.Properties()).durability(30).tab(MAIN).rarity(Rarity.COMMON), Utils.secondsToTicks(10), ModItems.REINFORCED_PAPER));
    public static final RegistryObject<ParagliderItem> PARAGLIDER_IRON = ITEMS.register("paraglider_iron", () -> new ParagliderItem((new Item.Properties()).durability(50).tab(MAIN).rarity(Rarity.UNCOMMON), Utils.secondsToTicks(13), ModItems.REINFORCED_PAPER_IRON));
    public static final RegistryObject<ParagliderItem> PARAGLIDER_GOLD = ITEMS.register("paraglider_gold", () -> new ParagliderItem((new Item.Properties()).durability(70).tab(MAIN).rarity(Rarity.UNCOMMON), Utils.secondsToTicks(15), ModItems.REINFORCED_PAPER_GOLD));
    public static final RegistryObject<ParagliderItem> PARAGLIDER_DIAMOND = ITEMS.register("paraglider_diamond", () -> new ParagliderItem((new Item.Properties()).durability(200).tab(MAIN).rarity(Rarity.RARE), Utils.secondsToTicks(20), ModItems.REINFORCED_PAPER_DIAMOND));
    public static final RegistryObject<ParagliderItem> PARAGLIDER_NETHERITE = ITEMS.register("paraglider_netherite", () -> new ParagliderItem((new Item.Properties()).durability(350).tab(MAIN).rarity(Rarity.EPIC), Utils.secondsToTicks(25), ModItems.REINFORCED_PAPER_NETHERITE));

    // Re-inforced Paper
    public static final RegistryObject<Item> REINFORCED_PAPER = ITEMS.register("reinforced_paper", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> REINFORCED_PAPER_IRON = ITEMS.register("reinforced_paper_iron", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> REINFORCED_PAPER_GOLD = ITEMS.register("reinforced_paper_gold", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> REINFORCED_PAPER_DIAMOND = ITEMS.register("reinforced_paper_diamond", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> REINFORCED_PAPER_NETHERITE = ITEMS.register("reinforced_paper_netherite", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> COPPER_WIRE = ITEMS.register("copper_conductor", () -> new Item(new Item.Properties().tab(MAIN).rarity(Rarity.COMMON)));


    public static final RegistryObject<TierArmor> LEATHER_ARMOR_HEAD = ITEMS.register("leather_armor_head", () -> new TierArmor(TierArmor.Tier.LEATHER, EquipmentSlot.HEAD, new Item.Properties().tab(MAIN)));
    public static final RegistryObject<TierArmor> LEATHER_ARMOR_CHEST = ITEMS.register("leather_armor_chest", () -> new TierArmor(TierArmor.Tier.LEATHER, EquipmentSlot.CHEST, new Item.Properties().tab(MAIN)));
    public static final RegistryObject<TierArmor> LEATHER_ARMOR_LEGGINGS = ITEMS.register("leather_armor_leggings", () -> new TierArmor(TierArmor.Tier.LEATHER, EquipmentSlot.LEGS, new Item.Properties().tab(MAIN)));
    public static final RegistryObject<TierArmor> LEATHER_ARMOR_FEET = ITEMS.register("leather_armor_feet", () -> new TierArmor(TierArmor.Tier.LEATHER, EquipmentSlot.FEET, new Item.Properties().tab(MAIN)));



}
