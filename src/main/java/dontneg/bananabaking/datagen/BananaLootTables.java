package dontneg.bananabaking.datagen;

import dontneg.bananabaking.block.BananaBlocks;
import dontneg.bananabaking.block.BananaCrop;
import dontneg.bananabaking.block.VanillaBeanCrop;
import dontneg.bananabaking.item.BananaItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BananaLootTables extends FabricBlockLootTableProvider {
    public BananaLootTables(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        //Vanilla Crop
        BlockStatePropertyLootCondition.Builder vanillaBeanDrops = BlockStatePropertyLootCondition
                .builder(BananaBlocks.VANILLA_CROP)
                .properties(StatePredicate.Builder.create()
                .exactMatch(VanillaBeanCrop.AGE, 7));
        addDrop(BananaBlocks.VANILLA_CROP, cropDrops(
                BananaBlocks.VANILLA_CROP, BananaItems.VANILLA_BEANS, BananaItems.VANILLA_BEAN_SEEDS, vanillaBeanDrops));
        //Banana Crop
        BlockStatePropertyLootCondition.Builder bananaDrops = BlockStatePropertyLootCondition
                .builder(BananaBlocks.BANANA_CROP)
                .properties(StatePredicate.Builder.create()
                        .exactMatch(BananaCrop.AGE, 7));
        addDrop(BananaBlocks.BANANA_CROP, cropDrops(
                BananaBlocks.BANANA_CROP, BananaItems.BANANA, BananaItems.BANANA_SEEDS, bananaDrops));
    }
}
