package dontneg.bananabaking.datagen;

import dontneg.bananabaking.block.BananaBlocks;
import dontneg.bananabaking.item.BananaItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
public class BananaRecipes extends FabricRecipeProvider {
    public BananaRecipes(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerShapelessRecipeWithMultipleInputs(exporter, BananaItems.BANANA_SEEDS, List.of(), List.of(
                TagKey.of(RegistryKeys.ITEM,Identifier.of("minecraft","villager_plantable_seeds")),
                TagKey.of(RegistryKeys.ITEM,Identifier.of("c","yellow_dyes")),
                TagKey.of(RegistryKeys.ITEM,Identifier.of("c","yellow_dyes"))),
                "",1);
        offerShapelessRecipeWithMultipleInputs(exporter, BananaItems.VANILLA_BEAN_SEEDS, List.of() ,List.of(
                TagKey.of(RegistryKeys.ITEM,Identifier.of("minecraft","villager_plantable_seeds")),
                TagKey.of(RegistryKeys.ITEM,Identifier.of("c","brown_dyes")),
                TagKey.of(RegistryKeys.ITEM,Identifier.of("c","brown_dyes"))),
                "",1);
        offerShapelessRecipeWithMultipleInputs(exporter, BananaItems.BANANA_BUNCH, List.of(
                BananaItems.BANANA,
                BananaItems.BANANA,
                BananaItems.BANANA,
                BananaItems.BANANA,
                BananaItems.BANANA,
                BananaItems.BANANA,
                BananaItems.BANANA,
                BananaItems.BANANA,
                BananaItems.BANANA),
                List.of(), "", 1);
        offerShapelessRecipeWithMultipleInputs(exporter, BananaItems.BANANA, List.of(BananaItems.BANANA_BUNCH), List.of(),"",9);
        offerShapelessRecipeWithMultipleInputs(exporter, BananaItems.BANANA_OILED, List.of(BananaItems.BANANA), List.of(),"",1);
        offerShapelessRecipeWithMultipleInputs(exporter, BananaItems.BANANA_PIE_SLICE, List.of(BananaItems.BANANA_PIE), List.of(),"",4);
        offerShapelessRecipeWithMultipleInputs(exporter, BananaItems.BANANA_PIE, List.of(
                BananaItems.BANANA_PIE_SLICE,
                BananaItems.BANANA_PIE_SLICE,
                BananaItems.BANANA_PIE_SLICE,
                BananaItems.BANANA_PIE_SLICE), List.of(),"",1);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, BananaBlocks.BAKING_OVEN.asItem(), 1)
                .pattern("CCC")
                .pattern("CFC")
                .pattern("BBB")
                .input('B', Items.BRICKS)
                .input('F', TagKey.of(RegistryKeys.ITEM,Identifier.of("minecraft","coals")))
                .input('C', Items.WHITE_CONCRETE_POWDER)
                .criterion("tick", TickCriterion.Conditions.createTick())
                .offerTo(exporter, Identifier.of(getRecipeName(BananaBlocks.BAKING_OVEN.asItem())));
    }
    @SuppressWarnings("unused")
    public static void offerShapelessRecipeWithMultipleInputs(RecipeExporter exporter, ItemConvertible output, List<ItemConvertible> inputItems, List<TagKey<Item>> inputTags, @Nullable String group, int outputCount) {
        ShapelessRecipeJsonBuilder builder = ShapelessRecipeJsonBuilder
                .create(RecipeCategory.MISC, output, outputCount);
        if(!inputItems.isEmpty()){
            for (ItemConvertible item : inputItems) {
                builder = builder.input(item);
            }
        }
        if(!inputTags.isEmpty()){
            for (TagKey<Item> tagKey : inputTags) {
                builder = builder.input(tagKey);
            }
        }
        builder.criterion("tick", TickCriterion.Conditions.createTick());
        builder = builder.group(group);
        builder.offerTo(exporter, RecipeProvider.getItemPath(output) + "_shapeless");
    }
    @SuppressWarnings("unused")
    public static void offerShapelessRecipeWithMultipleInputItems(RecipeExporter exporter, ItemConvertible output, List<ItemConvertible> inputs, @Nullable String group, int outputCount) {
        ShapelessRecipeJsonBuilder builder = ShapelessRecipeJsonBuilder
                .create(RecipeCategory.MISC, output, outputCount);
        for (ItemConvertible item : inputs) {
            builder = builder.input(item);
        }
        builder.criterion("tick", TickCriterion.Conditions.createTick());
        builder = builder.group(group);
        builder.offerTo(exporter, RecipeProvider.getItemPath(output) + "_shapeless");
    }
    @SuppressWarnings("unused")
    public static void offerShapelessRecipeWithMultipleInputTags(RecipeExporter exporter, ItemConvertible output, List<TagKey<Item>> inputs, @Nullable String group, int outputCount) {
        ShapelessRecipeJsonBuilder builder = ShapelessRecipeJsonBuilder
                .create(RecipeCategory.MISC, output, outputCount);
        for (TagKey<Item> tagKey : inputs) {
            builder = builder.input(tagKey);
        }
        builder.criterion("tick", TickCriterion.Conditions.createTick());
        builder = builder.group(group);
        builder.offerTo(exporter, RecipeProvider.getItemPath(output) + "_shapeless");
    }
}
