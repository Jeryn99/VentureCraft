package mc.craig.software.craftplus.compat;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.common.ModItems;
import mc.craig.software.craftplus.common.items.ParagliderItem;
import mc.craig.software.craftplus.common.items.Repairable;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.vanilla.IJeiAnvilRecipe;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.threetag.palladiumcore.registry.RegistrySupplier;

import java.util.ArrayList;
import java.util.Collections;


@JeiPlugin
public class JEIAnvil implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(VentureCraft.MODID, "anvil");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IVanillaRecipeFactory factory = registration.getVanillaRecipeFactory();

        ArrayList<IJeiAnvilRecipe> anvilRecipes = new ArrayList<>();

        for (RegistrySupplier<Item> entry : ModItems.ITEMS.getEntries()) {
            // Glider Mods
            if (entry.get() instanceof ParagliderItem paragliderItem) {

                // Copper Filament
                ItemStack gliderItem = new ItemStack(paragliderItem);
                ItemStack copperMod = new ItemStack(ModItems.COPPER_FILAMENT.get());
                ItemStack result = gliderItem.copy();
                ParagliderItem.setCopper(result, true);
                IJeiAnvilRecipe copperRecipe = factory.createAnvilRecipe(gliderItem, Collections.singletonList(copperMod), Collections.singletonList(result));
                anvilRecipes.add(copperRecipe);
            }

            // Items that can be repaired
            if (entry.get() instanceof Repairable repairable) {
                ItemStack stack = new ItemStack(entry.get());
                ItemStack stackDamaged = new ItemStack(entry.get());
                stackDamaged.setDamageValue(stackDamaged.getMaxDamage() / 2);

                IJeiAnvilRecipe copperRecipe = factory.createAnvilRecipe(stackDamaged, Collections.singletonList(new ItemStack(repairable.getRepairItem())), Collections.singletonList(stack));
                anvilRecipes.add(copperRecipe);
            }
        }


        registration.addRecipes(RecipeTypes.ANVIL, anvilRecipes);
    }
}
