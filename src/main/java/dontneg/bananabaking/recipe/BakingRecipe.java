package dontneg.bananabaking.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dontneg.bananabaking.BananaBaking;
import net.fabricmc.fabric.impl.recipe.ingredient.ShapelessMatch;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BakingRecipe implements Recipe<BakingRecipeInput> {
    private final ItemStack output;
    private final List<Ingredient> recipeItems;

    public BakingRecipe(List<Ingredient> ingredients, ItemStack itemStack) {
        this.output = itemStack;
        this.recipeItems = ingredients;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    @Override
    @SuppressWarnings("UnstableApiUsage")
    public boolean matches(BakingRecipeInput inventory, World world) {
        if(world.isClient()) return false;
        List<ItemStack> inputs = new ArrayList<>();
        for(int j = 0; j < 9; ++j) {
            ItemStack itemstack = inventory.getStackInSlot(j);
            if (!itemstack.isEmpty()) {
                inputs.add(itemstack);
            }
        }
        return inputs.size() == this.recipeItems.size() &&
                ShapelessMatch.isMatch(inputs, this.recipeItems);
    }

    @Override
    public ItemStack craft(BakingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(this.recipeItems.size());
        list.addAll(recipeItems);
        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(BakingRecipeInput input) {
        BananaBaking.LOGGER.info("REMAINDER");
        return Recipe.super.getRemainder(input);
    }

    public static class Type implements RecipeType<BakingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "baking";
    }

    public static class Serializer implements RecipeSerializer<BakingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "baking";

        private static final MapCodec<BakingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients").forGetter(BakingRecipe::getIngredients),
                ItemStack.VALIDATED_CODEC.fieldOf("output").forGetter(recipe -> recipe.output)
        ).apply(instance, BakingRecipe::new));

        private static final PacketCodec<RegistryByteBuf, BakingRecipe> PACKET_CODEC = PacketCodec.ofStatic(Serializer::write, Serializer::read);

        public static BakingRecipe read(RegistryByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);
            inputs.replaceAll(ignored -> Ingredient.PACKET_CODEC.decode(buf));
            ItemStack itemStack = ItemStack.PACKET_CODEC.decode(buf);
            return new BakingRecipe(inputs, itemStack);
        }

        public static void write(RegistryByteBuf buf, BakingRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients())
                Ingredient.PACKET_CODEC.encode(buf, ingredient);
            ItemStack.PACKET_CODEC.encode(buf, recipe.getResult(null));
        }

        @Override
        public MapCodec<BakingRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, BakingRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }
}