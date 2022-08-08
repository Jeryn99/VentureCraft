package mc.craig.software.notnotyet.data;

import mc.craig.software.notnotyet.MinecraftPlus;
import mc.craig.software.notnotyet.common.Entities;
import mc.craig.software.notnotyet.common.ModItems;
import mc.craig.software.notnotyet.util.ModConstants;
import net.minecraft.ChatFormatting;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LangProviderEnglish extends LanguageProvider {

    public LangProviderEnglish(DataGenerator gen) {
        super(gen, MinecraftPlus.MODID, "en_us");
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
        add(ModItems.COPPER_WIRE.get(), "Copper Conductor");
        add(ModItems.STALKERS_EGG.get(), "Stalker");

        // Messages
        add(ModConstants.INSTALLED_MODS, ChatFormatting.BOLD + "Installed Modifications");
        add(ModConstants.COPPER_MOD, "Copper Conductor");


        // ===== Tabs =====
        add("itemGroup." + MinecraftPlus.MODID, "Minecraft+");

        add(ModItems.LEATHER_ARMOR_HEAD.get(), "Leather Helmet");
        add(ModItems.LEATHER_ARMOR_CHEST.get(), "Leather Chestplate");
        add(ModItems.LEATHER_ARMOR_LEGGINGS.get(), "Leather Leggings");
        add(ModItems.LEATHER_ARMOR_FEET.get(), "Leather Feet");

        // ===== Blocks =====

    }
}
