package dontneg.bananabaking.recipe;

import dontneg.bananabaking.BananaBaking;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BananaRecipes {
    public static void registerRecipes(){
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(BananaBaking.MODID, BakingRecipe.Serializer.ID),
                BakingRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(BananaBaking.MODID, BakingRecipe.Type.ID),
                BakingRecipe.Type.INSTANCE);
    }
}
