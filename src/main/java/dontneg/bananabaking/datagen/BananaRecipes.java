package dontneg.bananabaking.datagen;

import dontneg.bananabaking.item.BananaItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BananaRecipes extends FabricRecipeProvider {
    public BananaRecipes(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerShapelessRecipeWithMultipleTags(exporter,BananaItems.BANANA_SEEDS, List.of(
                TagKey.of(RegistryKeys.ITEM,new Identifier("minecraft","villager_plantable_seeds")),
                TagKey.of(RegistryKeys.ITEM,new Identifier("c","yellow_dyes")),
                TagKey.of(RegistryKeys.ITEM,new Identifier("c","yellow_dyes"))),
                "",1);
        offerShapelessRecipeWithMultipleTags(exporter,BananaItems.VANILLA_BEAN_SEEDS, List.of(
                        TagKey.of(RegistryKeys.ITEM,new Identifier("minecraft","villager_plantable_seeds")),
                        TagKey.of(RegistryKeys.ITEM,new Identifier("c","brown_dyes")),
                        TagKey.of(RegistryKeys.ITEM,new Identifier("c","brown_dyes"))),
                "",1);
    }
    public static void offerShapelessRecipeWithMultipleInputs(RecipeExporter exporter, ItemConvertible output, List<ItemConvertible> inputs, @Nullable String group, int outputCount) {
        ShapelessRecipeJsonBuilder builder = ShapelessRecipeJsonBuilder
                .create(RecipeCategory.MISC, output, outputCount);
        for (ItemConvertible item : inputs) {
            builder = builder.input(item);
        }
        builder.criterion("tick", TickCriterion.Conditions.createTick());
        builder = builder.group(group);
        builder.offerTo(exporter, RecipeProvider.getItemPath(output) + "_shapeless");
    }
    public static void offerShapelessRecipeWithMultipleTags(RecipeExporter exporter, ItemConvertible output, List<TagKey<Item>> inputs, @Nullable String group, int outputCount) {
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
