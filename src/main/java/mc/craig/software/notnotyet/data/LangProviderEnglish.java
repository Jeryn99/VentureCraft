package mc.craig.software.notnotyet.data;

import mc.craig.software.notnotyet.NoNotYet;
import mc.craig.software.notnotyet.common.Entities;
import mc.craig.software.notnotyet.common.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LangProviderEnglish extends LanguageProvider {

    public LangProviderEnglish(DataGenerator gen) {
        super(gen, NoNotYet.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // ===== Entities =====
        add(Entities.STALKER.get(), "Stalker");

        // ===== Items =====
        add(ModItems.PARAGLIDER_WOOD.get(), "Glider (Wood)");
        add(ModItems.PARAGLIDER_IRON.get(), "Glider (Iron)");
        add(ModItems.PARAGLIDER_DIAMOND.get(), "Glider (Diamond)");
        add(ModItems.PARAGLIDER_GOLD.get(), "Glider (Gold)");
        add(ModItems.PARAGLIDER_NETHERITE.get(), "Glider (Netherite)");

        add(ModItems.REINFORCED_PAPER.get(), "Re-Inforced Paper");
        add(ModItems.REINFORCED_PAPER_IRON.get(), "Re-Inforced Paper (Iron)");
        add(ModItems.REINFORCED_PAPER_GOLD.get(), "Re-Inforced Paper (Gold)");
        add(ModItems.REINFORCED_PAPER_DIAMOND.get(), "Re-Inforced Paper (Diamond)");
        add(ModItems.REINFORCED_PAPER_NETHERITE.get(), "Re-Inforced Paper (Netherite)");

        add(ModItems.STALKERS_EGG.get(), "Stalker");


        // ===== Tabs =====
        add("itemGroup." + NoNotYet.MODID, "No..Not yet!");

  /*      add(ModItems.LEATHER_ARMOR_HEAD.get(), "Leather Helmet");
        add(ModItems.LEATHER_ARMOR_CHEST.get(), "Leather Chestplate");
        add(ModItems.LEATHER_ARMOR_LEGGINGS.get(), "Leather Leggings");
        add(ModItems.LEATHER_ARMOR_FEET.get(), "Leather Feet");*/

        // ===== Blocks =====

    }
}
