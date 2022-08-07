package mc.craig.software.notnotyet.data;

import mc.craig.software.notnotyet.MinecraftPlus;
import mc.craig.software.notnotyet.common.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModelProviderItem extends ItemModelProvider {

    public ModelProviderItem(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MinecraftPlus.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegistryObject<Item> entry : ModItems.ITEMS.getEntries()) {
            if (entry.get() instanceof ForgeSpawnEggItem) continue;
            basicItem(entry.get());
        }
    }
}
