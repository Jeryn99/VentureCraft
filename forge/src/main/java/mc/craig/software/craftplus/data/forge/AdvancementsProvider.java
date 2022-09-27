package mc.craig.software.craftplus.data.forge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.common.ModItems;
import mc.craig.software.craftplus.common.advancement.BaseTrigger;
import mc.craig.software.craftplus.common.advancement.TriggerManager;
import mc.craig.software.craftplus.common.items.ParagliderItem;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.FrameType;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AdvancementsProvider implements DataProvider {

    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private static final List<Advancement> advancements = new ArrayList<>();
    private final DataGenerator generator;

    public AdvancementsProvider(DataGenerator generatorIn) {
        this.generator = generatorIn;
    }

    private static Path getPath(Path pathIn, Advancement advancementIn) {
        return pathIn.resolve("data/" + advancementIn.getId().getNamespace() + "/advancements/" + advancementIn.getId().getPath() + ".json");
    }


    @Override
    public void run(@NotNull CachedOutput hashCache) throws IOException {
        Path path = this.generator.getOutputFolder();

        Advancement firstTimePlayerPassage = this.createAdvancement("first_time_flyer", new ItemStack(ModItems.PARAGLIDER_WOOD.get()), new BaseTrigger.Instance(TriggerManager.FIRST_TIME_FLYER.getId()), null, FrameType.GOAL);

        Advancement copperModder = this.createAdvancement("copper_modder", new ItemStack(ModItems.COPPER_FILAMENT.get()), new BaseTrigger.Instance(TriggerManager.COPPER_MODDER.getId()), firstTimePlayerPassage, FrameType.TASK);
        Advancement lightningStrike = this.createAdvancement("lightning_strike", ParagliderItem.setCopper(new ItemStack(ModItems.PARAGLIDER_SAPPHIRE.get()), true), new BaseTrigger.Instance(TriggerManager.GLIDER_LIGHTNING.getId()), copperModder, FrameType.GOAL);


        for (Advancement adv : advancements) {
            saveStable(hashCache, adv.deconstruct().serializeToJson(), getPath(path, adv));
        }
    }

    /**
     * Gets a name for this provider, to use in logging.
     */
    public @NotNull String getName() {
        return "Advancements";
    }

    public Advancement create(String name, String title, ItemStack display, Advancement parent, FrameType frameType, CriterionTriggerInstance... inst) {

        Advancement.Builder adv = Advancement.Builder.advancement()
                .display(
                        display.getItem(),
                        Component.translatable("advancements." + VentureCraft.MODID + ".title." + title),
                        Component.translatable("advancements." + VentureCraft.MODID + ".desc." + title),
                        new ResourceLocation(VentureCraft.MODID, "textures/block/deepslate_sapphire_ore.png"),
                        frameType,
                        true,
                        true,
                        false);
        int i = 0;

        for (CriterionTriggerInstance in : inst) {
            adv = adv.addCriterion(i + "", in);
            i++;
        }

        if (parent != null) {
            adv.parent(parent);
        }

        return adv.build(new ResourceLocation(VentureCraft.MODID, name));
    }

    public Advancement createAdvancement(String name, ItemStack display, CriterionTriggerInstance inst, Advancement parent) {
        return createAdvancement(name, display, inst, parent, FrameType.TASK);
    }

    public Advancement createAdvancement(String name, ItemStack display, CriterionTriggerInstance inst, Advancement parent, FrameType frameType) {
        Advancement advance = this.create(name, name, display, parent, frameType, inst);
        advancements.add(advance);
        return advance;
    }
}