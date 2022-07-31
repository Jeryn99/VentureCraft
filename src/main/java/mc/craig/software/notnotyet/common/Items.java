package mc.craig.software.notnotyet.common;

import mc.craig.software.notnotyet.common.items.ParagliderItem;
import mc.craig.software.notnotyet.common.items.TierArmor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static mc.craig.software.notnotyet.NoNotYet.MODID;

public class Items {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final RegistryObject<SpawnEggItem> STALKERS_EGG = ITEMS.register("stalker_spawn_egg", () -> new ForgeSpawnEggItem(Entities.STALKER, 0x000000, 0x3F0000, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<ParagliderItem> PARAGLIDER = ITEMS.register("paraglider", () -> new ParagliderItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<TierArmor> LEATHER_ARMOR_HEAD = ITEMS.register("leather_armor_head", () -> new TierArmor(TierArmor.Tier.LEATHER, EquipmentSlot.HEAD, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<TierArmor> LEATHER_ARMOR_CHEST = ITEMS.register("leather_armor_chest", () -> new TierArmor(TierArmor.Tier.LEATHER, EquipmentSlot.CHEST, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<TierArmor> LEATHER_ARMOR_LEGGINGS = ITEMS.register("leather_armor_leggings", () -> new TierArmor(TierArmor.Tier.LEATHER, EquipmentSlot.LEGS, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<TierArmor> LEATHER_ARMOR_FEET = ITEMS.register("leather_armor_feet", () -> new TierArmor(TierArmor.Tier.LEATHER, EquipmentSlot.FEET, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


}
