package mc.craig.software.craftplus.data.forge;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.common.ModItems;
import mc.craig.software.craftplus.common.block.LockedLootChestBlock;
import mc.craig.software.craftplus.common.items.ClimbingGearItem;
import mc.craig.software.craftplus.common.items.ParagliderItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.threetag.palladiumcore.item.PalladiumSpawnEggItem;
import net.threetag.palladiumcore.registry.RegistrySupplier;

import java.util.Objects;

public class ModelProviderItem extends ItemModelProvider {

    public ModelProviderItem(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, VentureCraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegistrySupplier<Item> entry : ModItems.ITEMS.getEntries()) {
            if (entry.get() instanceof PalladiumSpawnEggItem) continue;

            if (entry.get() instanceof ParagliderItem) {
                ResourceLocation gliderId = ForgeRegistries.ITEMS.getKey(entry.get());
                layeredItem(new ResourceLocation(gliderId.getNamespace(), gliderId.getPath() + "_copper_mod"), Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(entry.get())), new ResourceLocation(VentureCraft.MODID, "glider_copper_mod"));
                continue;
            }

            if(entry.get() instanceof ClimbingGearItem climbingGearItem){
                basicItem(entry.get());
                continue;
            }

            if (entry.get() instanceof ArmorItem) {
                layeredItem(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(entry.get())), new ResourceLocation(VentureCraft.MODID, ForgeRegistries.ITEMS.getKey(entry.get()).getPath() + "_overlay"));
                continue;
            }

            if(entry.get() instanceof TieredItem){
                toolItem(entry.get());
                continue;
            }

            if (entry.get() instanceof BlockItem blockItem) {

                if(blockItem.getBlock() instanceof LockedLootChestBlock){
                    continue;
                }

                blockItem(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(entry.get())));
                continue;
            }

            basicItem(entry.get());
        }
    }

    public ItemModelBuilder toolItem(Item item)
    {
        return toolItem(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)));
    }

    public ItemModelBuilder toolItem(ResourceLocation item)
    {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), "item/" + item.getPath()));
    }


    public ItemModelBuilder blockItem(ResourceLocation item) {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(item.getNamespace(), "block/" + item.getPath())));
    }

    public ItemModelBuilder layeredItem(ResourceLocation destination, ResourceLocation item, ResourceLocation resourceLocation) {
        return getBuilder(destination.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), "item/" + item.getPath()))
                .texture("layer1", new ResourceLocation(resourceLocation.getNamespace(), "item/" + resourceLocation.getPath()));
    }

    public ItemModelBuilder layeredItem(ResourceLocation item, ResourceLocation resourceLocation) {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), "item/" + item.getPath()))
                .texture("layer1", new ResourceLocation(resourceLocation.getNamespace(), "item/" + resourceLocation.getPath()));
    }
}
