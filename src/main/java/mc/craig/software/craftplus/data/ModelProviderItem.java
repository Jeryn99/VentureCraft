package mc.craig.software.craftplus.data;

import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.common.ModItems;
import mc.craig.software.craftplus.common.items.ParagliderItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModelProviderItem extends ItemModelProvider {

    public ModelProviderItem(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MinecraftPlus.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegistryObject<Item> entry : ModItems.ITEMS.getEntries()) {
            if (entry.get() instanceof ForgeSpawnEggItem) continue;

            if (entry.get() instanceof ParagliderItem) {
                basicItem(new ResourceLocation(MinecraftPlus.MODID, ForgeRegistries.ITEMS.getKey(entry.get()).getPath() + "_copper_mod"));
                continue;
            }

            basicItem(entry.get());
        }
    }
}
