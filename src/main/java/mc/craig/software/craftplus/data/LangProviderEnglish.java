package mc.craig.software.craftplus.data;

import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.common.*;
import mc.craig.software.craftplus.util.ModConstants;
import net.minecraft.ChatFormatting;
import net.minecraft.data.DataGenerator;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.LanguageProvider;

public class LangProviderEnglish extends LanguageProvider {

    public LangProviderEnglish(DataGenerator gen) {
        super(gen, MinecraftPlus.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // ===== ModEntities =====
        add(ModEntities.STALKER.get(), "Stalker");
        add(ModEntities.OWL.get(), "Owl");

        // ===== Items =====

        // Gliders
        add(ModItems.PARAGLIDER_WOOD.get(), "Basic Paraglider");
        add(ModItems.PARAGLIDER_IRON.get(), "Iron Paraglider");
        add(ModItems.PARAGLIDER_DIAMOND.get(), "Diamond Paraglider");
        add(ModItems.PARAGLIDER_GOLD.get(), "Gold Paraglider");
        add(ModItems.PARAGLIDER_NETHERITE.get(), "Netherite Paraglider");

        // Paper
        add(ModItems.REINFORCED_PAPER.get(), "Re-Inforced Paper");
        add(ModItems.REINFORCED_PAPER_IRON.get(), "Re-Inforced Paper (Iron)");
        add(ModItems.REINFORCED_PAPER_GOLD.get(), "Re-Inforced Paper (Gold)");
        add(ModItems.REINFORCED_PAPER_DIAMOND.get(), "Re-Inforced Paper (Diamond)");
        add(ModItems.REINFORCED_PAPER_NETHERITE.get(), "Re-Inforced Paper (Netherite)");

        add(ModItems.COPPER_FILAMENT.get(), "Copper Filament");
        add(ModItems.OWL_FEATHER.get(), "Owl Feather");
        add(ModItems.CLIMBING_GEAR.get(), "Climbing Gear");
        add(ModItems.UNREFINED_RUBY.get(), "Unrefined Ruby");
        add(ModItems.RUBY.get(), "Ruby");
        add(ModItems.SAPPHIRE_GEM.get(), "Sapphire Gem");

        // Eggs
        add(ModItems.STALKERS_EGG.get(), "Spawn Stalker");
        add(ModItems.OWL_SPAWN_EGG.get(), "Spawn Owl");

        // ===== Messages =====
        add(ModConstants.INSTALLED_MODS, ChatFormatting.BOLD + "Installed Modifications");
        add(ModConstants.COPPER_MOD, "Copper Filament");

        // ===== Damage Sources =====
        add(ModDamageSource.OWL_CLAWS.getKey(), "%s was clawed to death by a Owl");
        add(ModDamageSource.LIGHTNING_GLIDER.getKey(), "%s's Glider experiment went wrong");

        // ===== Tabs =====
        add("itemGroup." + MinecraftPlus.MODID, "Minecraft+");

        add(ModItems.LEATHER_ARMOR_HEAD.get(), "Leather Helmet");
        add(ModItems.LEATHER_ARMOR_CHEST.get(), "Leather Chestplate");
        add(ModItems.LEATHER_ARMOR_LEGGINGS.get(), "Leather Leggings");
        add(ModItems.LEATHER_ARMOR_FEET.get(), "Leather Feet");

        // ===== Subtitles/Sounds =====
        addSound(ModSounds.GLIDER_OPEN.get(), "Glider opens");
        addSound(ModSounds.SPACE_GLIDE.get(), "Space Glide");
        addSound(ModSounds.SPACE_DEPLOY.get(), "Space Deploy");
        addSound(ModSounds.OWL_HURT.get(), "Owl hurts");
        addSound(ModSounds.OWL_HOOTS.get(), "Space hoots");

        // ===== Blocks =====
        add(ModBlocks.SAPPHIRE_ORE.get(), "Sapphire Ore");
        add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), "Deepslate Sapphire Ore");
        add(ModBlocks.RUBY_ORE.get(), "Ruby Ore");
    }

    private void addSound(SoundEvent soundEvent, String subtitle) {
        add("subtitle." + MinecraftPlus.MODID + "." + soundEvent.getLocation().getPath(), subtitle);
    }
}
