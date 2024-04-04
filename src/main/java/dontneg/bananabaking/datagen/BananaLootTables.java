package dontneg.bananabaking.datagen;

import dontneg.bananabaking.block.BananaBlocks;
import dontneg.bananabaking.block.VanillaBeanCrop;
import dontneg.bananabaking.item.BananaItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;

public class BananaLootTables extends FabricBlockLootTableProvider {
    public BananaLootTables(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        BlockStatePropertyLootCondition.Builder vanillaBeanDrops = BlockStatePropertyLootCondition
                .builder(BananaBlocks.VANILLA_CROP)
                .properties(StatePredicate.Builder.create()
                .exactMatch(VanillaBeanCrop.AGE, 7));
        addDrop(BananaBlocks.VANILLA_CROP, cropDrops(
                BananaBlocks.VANILLA_CROP, BananaItems.VANILLA_BEAN, BananaItems.VANILLA_BEAN_SEEDS, vanillaBeanDrops));

    }
}
