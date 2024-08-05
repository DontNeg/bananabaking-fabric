package dontneg.bananabaking.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.util.collection.DefaultedList;

public class BakingRecipeInput implements RecipeInput {
    private final RecipeMatcher matcher = new RecipeMatcher();
    public final DefaultedList<ItemStack> input;

    public BakingRecipeInput(DefaultedList<ItemStack> input) {
        this.input = input;
        for(ItemStack stack: input){
            if(stack.isEmpty()) continue;
            this.matcher.addInput(stack,1);
        }
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return input.get(slot);
    }

    @Override
    public int getSize() {
        return 10;
    }

    public RecipeMatcher getMatcher(){
        return matcher;
    }
}
