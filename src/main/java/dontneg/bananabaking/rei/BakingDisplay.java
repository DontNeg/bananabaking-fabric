package dontneg.bananabaking.rei;

import dontneg.bananabaking.recipe.BakingRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BakingDisplay extends BasicDisplay {
    public BakingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    public BakingDisplay(RecipeEntry<BakingRecipe> recipes) {
        super(getInputList(recipes.value()), List.of(EntryIngredient.of(EntryStacks.of(recipes.value().getResult(null)))));
    }

    private static List<EntryIngredient> getInputList(BakingRecipe recipes) {
        if(recipes == null) return Collections.emptyList();
        List<EntryIngredient> list = new ArrayList<>(EntryIngredients.ofIngredients(recipes.getIngredients()));
        for(int i = list.size();i<9;){
            list.add(EntryIngredient.of(EntryIngredient.empty()));
            i = list.size();
        }
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return BakingCategory.BAKING;
    }

}