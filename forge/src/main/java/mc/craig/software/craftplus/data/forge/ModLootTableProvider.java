package mc.craig.software.craftplus.data.forge;

import com.mojang.datafixers.util.Pair;
import mc.craig.software.craftplus.common.ModBlocks;
import mc.craig.software.craftplus.common.ModEntities;
import mc.craig.software.craftplus.common.ModItems;
import mc.craig.software.craftplus.common.block.PedastalBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.EntityLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ClampedInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModLootTableProvider extends LootTableProvider {

    public ModLootTableProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return List.of(Pair.of(ModEntityLoot::new, LootContextParamSets.ENTITY), Pair.of(ModBlockLoot::new, LootContextParamSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
        for (Map.Entry<ResourceLocation, LootTable> entry : map.entrySet())
            LootTables.validate(validationtracker, entry.getKey(), entry.getValue());
    }

    public static class ModBlockLoot extends BlockLoot {
        @Override
        protected void addTables() {
            this.add(ModBlocks.SAPPHIRE_ORE.get(), (block) -> createOreDrop(block, ModItems.SAPPHIRE_GEM.get()));
            this.add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), (block) -> createOreDrop(block, ModItems.SAPPHIRE_GEM.get()));
            this.add(ModBlocks.RUBY_ORE.get(), (block) -> createOreDrop(block, ModItems.UNREFINED_RUBY.get()));
            this.add(ModBlocks.DEEPSLATE_RUBY_ORE.get(), (block) -> createOreDrop(block, ModItems.UNREFINED_RUBY.get()));

            dropSelf(ModBlocks.CATALYST.get());
            dropSelf(ModBlocks.BELL.get());

            dropSelf(ModBlocks.TRINKET_LIBERTY.get());
            dropSelf(ModBlocks.TRINKET_HOVERBOARD.get());
            dropSelf(ModBlocks.TRINKET_TARDIS.get());

            this.add(ModBlocks.SAPPHIRE_LOOT_CHEST.get(), this::createContainerLootDrops);
            this.add(ModBlocks.VOID_LOOT_CHEST.get(), this::createContainerLootDrops);
            this.add(ModBlocks.GOLD_LOOT_CHEST.get(), this::createContainerLootDrops);
            this.add(ModBlocks.IRON_LOOT_CHEST.get(), this::createContainerLootDrops);

            for (RegistrySupplier<Block> entry : ModBlocks.BLOCKS.getEntries()) {
                dropSelf(entry.get());
            }


            /////

            LootPool.Builder pool1 = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(Items.EMERALD)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))));

            LootPool.Builder pool2 = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(Items.BONE).setWeight(10))
                    .add(LootItem.lootTableItem(Items.COAL)
                            .setWeight(10))
                    .add(LootItem.lootTableItem(Items.COBWEB)
                            .setWeight(10)
                    )
                    .add(LootItem.lootTableItem(Items.PAPER)
                            .setWeight(10)
                    )
                    .add(LootItem.lootTableItem(Items.LEATHER)
                            .setWeight(10)
                    )
                    .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
                            .setWeight(10)
                    )
                    .add(LootItem.lootTableItem(Items.ARROW)
                            .setWeight(10)
                    )
                    .add(LootItem.lootTableItem(Items.COD)
                            .setWeight(10)
                    )
                    .add(LootItem.lootTableItem(Items.GUNPOWDER)
                            .setWeight(10)
                    )
                    .add(LootItem.lootTableItem(Items.STICK)
                            .setWeight(10)
                    );

            LootPool.Builder pool3 = LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(ModItems.OWL_FEATHER.get())
                            .setWeight(2)
                    )
                    .add(LootItem.lootTableItem(Items.COAL_BLOCK)
                            .setWeight(2)
                    )
                    .add(LootItem.lootTableItem(Items.REDSTONE)
                            .setWeight(2)
                    )
                    .add(LootItem.lootTableItem(Items.GOLD_NUGGET)
                            .setWeight(2)
                    )
                    .add(LootItem.lootTableItem(Items.IRON_NUGGET)
                            .setWeight(2)
                    );

            LootPool.Builder pool4 = LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(ModItems.UNREFINED_RUBY.get())
                            .setWeight(1)
                    )
                    .add(LootItem.lootTableItem(Items.IRON_INGOT)
                            .setWeight(1)
                    )
                    .add(LootItem.lootTableItem(Items.GOLD_INGOT)
                            .setWeight(1));

            LootPool.Builder pool5 = LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(ModItems.SAPPHIRE_GEM.get())
                            .setWeight(1)
                    )
                    .add(LootItem.lootTableItem(Items.WITHER_SKELETON_SKULL)
                            .setWeight(1)
                    )
                    .add(LootItem.lootTableItem(Items.DIAMOND)
                            .setWeight(1));

            //TODO BIG KEY WILL DUNGEON KEY?
            LootPool.Builder pool6 = LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(ModItems.BIG_KEY.get())
                            .setWeight(1)
                    )
                    .add(LootItem.lootTableItem(ModItems.IRON_KEY.get())
                            .setWeight(1));

            LootTable.Builder table = LootTable.lootTable()
                    .withPool(pool1)
                    .withPool(pool2)
                    .withPool(pool3)
                    .withPool(pool4)
                    .withPool(pool5)
                    .withPool(pool6);

            add(ModBlocks.POT.get(), table);

            ////

        }

        private LootTable.Builder createContainerLootDrops(Block block) {
            return LootTable.lootTable().withPool(applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block).apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY)))));
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            ArrayList<@NotNull Block> blocks = new ArrayList<>();
            for (RegistrySupplier<Block> entry : ModBlocks.BLOCKS.getEntries()) {
                blocks.add(entry.get());
            }
            return blocks;
        }
    }

    public static class ModEntityLoot extends EntityLoot {
        @Override
        protected void addTables() {
            add(ModEntities.OWL.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(ModItems.OWL_FEATHER.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))));
            add(ModEntities.STALKER.get(), LootTable.lootTable());
        }

        @Override
        protected Iterable<EntityType<?>> getKnownEntities() {
            ArrayList<EntityType<?>> entityTypes = new ArrayList<>();
            for (RegistrySupplier<EntityType<?>> entry : ModEntities.ENTITY_TYPES.getEntries()) {
                entityTypes.add(entry.get());
            }
            return entityTypes;
        }
    }
}
