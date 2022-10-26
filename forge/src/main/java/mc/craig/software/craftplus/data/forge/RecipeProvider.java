package mc.craig.software.craftplus.data.forge;

import mc.craig.software.craftplus.common.ModBlocks;
import mc.craig.software.craftplus.common.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {
    public RecipeProvider(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {

        // Inforced Paper
        ShapedRecipeBuilder.shaped(ModItems.REINFORCED_PAPER.get()).pattern("LPL").pattern("PLP").pattern("LPL").define('L', Items.LEATHER).define('P', Items.PAPER).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.REINFORCED_PAPER_GOLD.get()).pattern("GGG").pattern("GPG").pattern("GGG").define('G', Items.GOLD_INGOT).define('P', ModItems.REINFORCED_PAPER.get()).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.REINFORCED_PAPER_DIAMOND.get()).pattern("DDD").pattern("DPD").pattern("DDD").define('D', Items.DIAMOND).define('P', ModItems.REINFORCED_PAPER.get()).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.REINFORCED_PAPER_NETHERITE.get()).pattern("NNN").pattern("NPN").pattern("NNN").define('N', Items.NETHERITE_SCRAP).define('P', ModItems.REINFORCED_PAPER.get()).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.REINFORCED_PAPER_IRON.get()).pattern("III").pattern("IPI").pattern("III").define('I', Items.IRON_INGOT).define('P', ModItems.REINFORCED_PAPER.get()).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.REINFORCED_PAPER_SAPPHIRE.get()).pattern("III").pattern("IPI").pattern("III").define('I', ModItems.SAPPHIRE_GEM.get()).define('P', ModItems.REINFORCED_PAPER.get()).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);

        // Gliders
        ShapedRecipeBuilder.shaped(ModItems.PARAGLIDER_WOOD.get()).pattern("RRR").pattern("SWS").pattern("WOW").define('O', ModItems.OWL_FEATHER.get()).define('R', ModItems.REINFORCED_PAPER.get()).define('W', Items.STICK).define('S', Items.STRING).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.PARAGLIDER_IRON.get()).pattern("RRR").pattern("SWS").pattern("WOW").define('O', ModItems.OWL_FEATHER.get()).define('R', ModItems.REINFORCED_PAPER_IRON.get()).define('W', Items.STICK).define('S', Items.STRING).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.PARAGLIDER_GOLD.get()).pattern("RRR").pattern("SWS").pattern("WOW").define('O', ModItems.OWL_FEATHER.get()).define('R', ModItems.REINFORCED_PAPER_GOLD.get()).define('W', Items.STICK).define('S', Items.STRING).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.PARAGLIDER_DIAMOND.get()).pattern("RRR").pattern("SWS").pattern("WOW").define('O', ModItems.OWL_FEATHER.get()).define('R', ModItems.REINFORCED_PAPER_DIAMOND.get()).define('W', Items.STICK).define('S', Items.STRING).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.PARAGLIDER_NETHERITE.get()).pattern("RRR").pattern("SWS").pattern("WOW").define('O', ModItems.OWL_FEATHER.get()).define('R', ModItems.REINFORCED_PAPER_NETHERITE.get()).define('W', Items.STICK).define('S', Items.STRING).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.PARAGLIDER_SAPPHIRE.get()).pattern("RRR").pattern("SWS").pattern("WOW").define('O', ModItems.OWL_FEATHER.get()).define('R', ModItems.REINFORCED_PAPER_SAPPHIRE.get()).define('W', Items.STICK).define('S', Items.STRING).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);

        // Sapphire Tools
        ShapedRecipeBuilder.shaped(ModItems.SAPPHIRE_AXE.get()).define('#', Items.STICK).define('X', ModItems.SAPPHIRE_GEM.get()).pattern("XX").pattern("X#").pattern(" #").unlockedBy("has_sapphire", has(ModItems.SAPPHIRE_GEM.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SAPPHIRE_PICKAXE.get()).define('#', Items.STICK).define('X', ModItems.SAPPHIRE_GEM.get()).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy("has_sapphire", has(ModItems.SAPPHIRE_GEM.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SAPPHIRE_SHOVEL.get()).define('#', Items.STICK).define('X', ModItems.SAPPHIRE_GEM.get()).pattern("X").pattern("#").pattern("#").unlockedBy("has_sapphire", has(ModItems.SAPPHIRE_GEM.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SAPPHIRE_SWORD.get()).define('#', Items.STICK).define('X', ModItems.SAPPHIRE_GEM.get()).pattern("X").pattern("X").pattern("#").unlockedBy("has_sapphire", has(ModItems.SAPPHIRE_GEM.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SAPPHIRE_HOE.get()).define('#', Items.STICK).define('X', ModItems.SAPPHIRE_GEM.get()).pattern("XX").pattern(" #").pattern(" #").unlockedBy("has_sapphire", has(ModItems.SAPPHIRE_GEM.get())).save(consumer);


        // Dyed Pedastals
        coloredPedastalFromWhitePedastalAndDye(consumer, ModBlocks.BLACK_PEDESTAL.get(), Items.BLACK_DYE);
        coloredPedastalFromWhitePedastalAndDye(consumer, ModBlocks.BLUE_PEDASTAL.get(), Items.BLUE_DYE);
        coloredPedastalFromWhitePedastalAndDye(consumer, ModBlocks.RED_PEDASTAL.get(), Items.RED_DYE);
        coloredPedastalFromWhitePedastalAndDye(consumer, ModBlocks.BROWN_PEDASTAL.get(), Items.BROWN_DYE);
        coloredPedastalFromWhitePedastalAndDye(consumer, ModBlocks.CYAN_PEDASTAL.get(), Items.CYAN_DYE);
        coloredPedastalFromWhitePedastalAndDye(consumer, ModBlocks.GREEN_PEDASTAL.get(), Items.GREEN_DYE);
        coloredPedastalFromWhitePedastalAndDye(consumer, ModBlocks.GREY_PEDASTAL.get(), Items.GRAY_DYE);
        coloredPedastalFromWhitePedastalAndDye(consumer, ModBlocks.LIGHT_BLUE_PEDASTAL.get(), Items.LIGHT_BLUE_DYE);
        coloredPedastalFromWhitePedastalAndDye(consumer, ModBlocks.LIGHT_GREY_PEDASTAL.get(), Items.LIGHT_GRAY_DYE);
        coloredPedastalFromWhitePedastalAndDye(consumer, ModBlocks.LIME_PEDASTAL.get(), Items.LIME_DYE);
        coloredPedastalFromWhitePedastalAndDye(consumer, ModBlocks.MAGENTA_PEDASTAL.get(), Items.MAGENTA_DYE);
        coloredPedastalFromWhitePedastalAndDye(consumer, ModBlocks.ORANGE_PEDASTAL.get(), Items.ORANGE_DYE);
        coloredPedastalFromWhitePedastalAndDye(consumer, ModBlocks.PINK_PEDASTAL.get(), Items.PINK_DYE);
        coloredPedastalFromWhitePedastalAndDye(consumer, ModBlocks.PURPLE_PEDASTAL.get(), Items.PURPLE_DYE);
        coloredPedastalFromWhitePedastalAndDye(consumer, ModBlocks.YELLOW_PEDASTAL.get(), Items.YELLOW_DYE);

        createPedastal(consumer, ModBlocks.BLACK_PEDESTAL.get(), Blocks.BLACK_CARPET);
        createPedastal(consumer, ModBlocks.BLUE_PEDASTAL.get(), Blocks.BLUE_CARPET);
        createPedastal(consumer, ModBlocks.RED_PEDASTAL.get(), Blocks.RED_CARPET);
        createPedastal(consumer, ModBlocks.BROWN_PEDASTAL.get(), Blocks.BROWN_CARPET);
        createPedastal(consumer, ModBlocks.CYAN_PEDASTAL.get(), Blocks.CYAN_CARPET);
        createPedastal(consumer, ModBlocks.GREEN_PEDASTAL.get(), Blocks.GREEN_CARPET);
        createPedastal(consumer, ModBlocks.GREY_PEDASTAL.get(), Blocks.GRAY_CARPET);
        createPedastal(consumer, ModBlocks.LIGHT_BLUE_PEDASTAL.get(), Blocks.LIGHT_BLUE_CARPET);
        createPedastal(consumer, ModBlocks.LIGHT_GREY_PEDASTAL.get(), Blocks.LIGHT_GRAY_CARPET);
        createPedastal(consumer, ModBlocks.LIME_PEDASTAL.get(), Blocks.LIME_CARPET);
        createPedastal(consumer, ModBlocks.MAGENTA_PEDASTAL.get(), Blocks.MAGENTA_CARPET);
        createPedastal(consumer, ModBlocks.ORANGE_PEDASTAL.get(), Blocks.ORANGE_CARPET);
        createPedastal(consumer, ModBlocks.PINK_PEDASTAL.get(), Blocks.PINK_CARPET);
        createPedastal(consumer, ModBlocks.PURPLE_PEDASTAL.get(), Blocks.PURPLE_CARPET);
        createPedastal(consumer, ModBlocks.YELLOW_PEDASTAL.get(), Blocks.YELLOW_CARPET);
    }

    public static void createPedastal(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike dyedPedastal, ItemLike dye){
        ShapedRecipeBuilder.shaped(dyedPedastal).define('C', dye).define('L', Blocks.SMOOTH_STONE_SLAB).define('S', Blocks.CHISELED_STONE_BRICKS).pattern(" C ").pattern(" S ").pattern(" L ").group("pedastal").unlockedBy(getHasName(dye), has(dye)).save(finishedRecipeConsumer);
    }

    protected static void coloredPedastalFromWhitePedastalAndDye(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike dyedPedastal, ItemLike dye) {
        ShapedRecipeBuilder.shaped(dyedPedastal).define('$', ModBlocks.WHITE_PEDASTAL.get()).define('#', dye).pattern("###").pattern("#$#").pattern("###").group("pedastal").unlockedBy("has_white_pedastal", has(ModBlocks.WHITE_PEDASTAL.get())).unlockedBy(getHasName(dye), has(dye)).save(finishedRecipeConsumer, getConversionRecipeName(dyedPedastal, ModBlocks.WHITE_PEDASTAL.get()));
    }

}
