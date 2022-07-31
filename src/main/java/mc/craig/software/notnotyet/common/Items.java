package mc.craig.software.notnotyet.common;

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

}
