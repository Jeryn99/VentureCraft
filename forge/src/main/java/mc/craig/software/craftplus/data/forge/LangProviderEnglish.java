package mc.craig.software.craftplus.data.forge;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.common.*;
import mc.craig.software.craftplus.util.ModConstants;
import net.minecraft.ChatFormatting;
import net.minecraft.data.DataGenerator;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.LanguageProvider;

public class LangProviderEnglish extends LanguageProvider {

    public LangProviderEnglish(DataGenerator gen) {
        super(gen, VentureCraft.MODID, "en_us");
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
        add(ModItems.PARAGLIDER_SAPPHIRE.get(), "Sapphire Paraglider");

        // Keys
        add(ModItems.GOLD_KEY.get(), "Gold Key");
        add(ModItems.IRON_KEY.get(), "Iron Key");
        add(ModItems.SAPPHIRE_KEY.get(), "Sapphire Key");
        add(ModItems.VOID_KEY.get(), "Void Key");
        add(ModItems.BIG_KEY.get(), "Big Key");

        // Paper
        add(ModItems.REINFORCED_PAPER.get(), "Re-Inforced Paper");
        add(ModItems.REINFORCED_PAPER_IRON.get(), "Re-Inforced Paper (Iron)");
        add(ModItems.REINFORCED_PAPER_GOLD.get(), "Re-Inforced Paper (Gold)");
        add(ModItems.REINFORCED_PAPER_DIAMOND.get(), "Re-Inforced Paper (Diamond)");
        add(ModItems.REINFORCED_PAPER_NETHERITE.get(), "Re-Inforced Paper (Netherite)");
        add(ModItems.REINFORCED_PAPER_SAPPHIRE.get(), "Re-Inforced Paper (Sapphire)");

        add(ModItems.COPPER_FILAMENT.get(), "Copper Filament");
        add(ModItems.OWL_FEATHER.get(), "Owl Feather");
        add(ModItems.CLIMBING_GEAR.get(), "Climbing Gear");

        // Ingots/Mined Items
        add(ModItems.UNREFINED_RUBY.get(), "Unrefined Ruby");
        add(ModItems.RUBY.get(), "Ruby");
        add(ModItems.SAPPHIRE_GEM.get(), "Sapphire Gem");

        // Eggs
        add(ModItems.STALKERS_EGG.get(), "Spawn Stalker");
        add(ModItems.OWL_SPAWN_EGG.get(), "Spawn Owl");

        // Armor
     /*   add(ModItems.LEATHER_ARMOR_HEAD.get(), "Leather Helmet");
        add(ModItems.LEATHER_ARMOR_CHEST.get(), "Leather Chestplate");
        add(ModItems.LEATHER_ARMOR_LEGGINGS.get(), "Leather Leggings");
        add(ModItems.LEATHER_ARMOR_FEET.get(), "Leather Feet");*/

        // Sapphire Tools
        add(ModItems.SAPPHIRE_SWORD.get(), "Sapphire Sword");
        add(ModItems.SAPPHIRE_HOE.get(), "Sapphire Hoe");
        add(ModItems.SAPPHIRE_SHOVEL.get(), "Sapphire Shovel");
        add(ModItems.SAPPHIRE_PICKAXE.get(), "Sapphire Pickaxe");
        add(ModItems.SAPPHIRE_AXE.get(), "Sapphire Axe");

        // ===== Messages =====
        add(ModConstants.INSTALLED_MODS, ChatFormatting.BOLD + "Installed Modifications");
        add(ModConstants.COPPER_MOD, "Copper Filament");

        // ===== Damage Sources =====
        add(ModDamageSource.OWL_CLAWS.getKey(), "%s was clawed to death by a Owl");
        add(ModDamageSource.LIGHTNING_GLIDER.getKey(), "%s's Glider experiment went wrong");
        add(ModDamageSource.STATUE.getKey(), "Something happened to %s...");

        // ===== Tabs =====
        add("itemGroup." + VentureCraft.MODID, "VentureCraft");
        add("itemGroup." + VentureCraft.MODID + ".main", "VentureCraft");


        // ===== Subtitles/Sounds =====
        addSound(ModSounds.GLIDER_OPEN.get(), "Glider opens");
        addSound(ModSounds.SPACE_GLIDE.get(), "Space Glide");
        addSound(ModSounds.SPACE_DEPLOY.get(), "Space Deploy");
        addSound(ModSounds.OWL_HURT.get(), "Owl hurts");
        addSound(ModSounds.OWL_HOOTS.get(), "Space hoots");
        addSound(ModSounds.STALKER_STRIKE.get(), "Stalker Strikes");
        addSound(ModSounds.STALKER_MOVE.get(), "Stalker Moves");
        addSound(ModSounds.STALKER_LAUGH.get(), "Stalker laughs at how hopeless you are");

        // ===== Blocks =====
        add(ModBlocks.SAPPHIRE_ORE.get(), "Sapphire Ore");
        add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), "Deepslate Sapphire Ore");
        add(ModBlocks.RUBY_ORE.get(), "Ruby Ore");
        add(ModBlocks.DEEPSLATE_RUBY_ORE.get(), "Deepslate Ruby Ore");
        add(ModBlocks.IRON_LOOT_CHEST.get(), "Iron Loot Chest");
        add(ModBlocks.VOID_LOOT_CHEST.get(), "Void Loot Chest");
        add(ModBlocks.SAPPHIRE_LOOT_CHEST.get(), "Sapphire Loot Chest");
        add(ModBlocks.GOLD_LOOT_CHEST.get(), "Gold Loot Chest");
        add(ModBlocks.PEDASTAL.get(), "Pedastal");
        add(ModBlocks.POT.get(), "Pot");

        // ===== Advancements =====
        addAdvancement("first_time_flyer", "Paraglide!", "...and the whole worlds at your feet");
        addAdvancement("lightning_strike", "1.21 gigawatts!", "Energize your glider!");
        addAdvancement("copper_modder", "Tinkerer!", "Mod your glider with Copper Filament!");

        // ===== Commands =====
        add("commands.venturecraft.skillpoints.add.points.success.single", "Gave %s skill xp to %s");
        add("commands.venturecraft.skillpoints.add.points.success.multiple", "Gave %s skill xp to %s players");
        add("commands.venturecraft.skillpoints.add.levels.success.single", "Gave %s skill points to %s");
        add("commands.venturecraft.skillpoints.add.levels.success.multiple", "Gave %s skill points to %s players");
        add("commands.venturecraft.skillpoints.set.points.success.single", "Set %s skill xp on %s");
        add("commands.venturecraft.skillpoints.set.points.success.multiple", "Set %s skill xp on %s players");
        add("commands.venturecraft.skillpoints.set.levels.success.single", "Set %s skill points on %s");
        add("commands.venturecraft.skillpoints.set.levels.success.multiple", "Set %s skill points on %s players");
        add("commands.venturecraft.skillpoints.query.points", "%s has %s skill xp");
        add("commands.venturecraft.skillpoints.query.levels", "%s has %s skill points");
    }

    private void addSound(SoundEvent soundEvent, String subtitle) {
        add("subtitle." + VentureCraft.MODID + "." + soundEvent.getLocation().getPath(), subtitle);
    }

    private void addAdvancement(String advancement, String title, String subtitle) {
        add("advancements." + VentureCraft.MODID + ".title." + advancement, title);
        add("advancements." + VentureCraft.MODID + ".desc." + advancement, subtitle);
    }
}
