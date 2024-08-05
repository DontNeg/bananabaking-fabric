//package dontneg.bananabaking.rei;
//
//import dontneg.bananabaking.block.BananaBlocks;
//import dontneg.bananabaking.recipes.BakingRecipe;
//import dontneg.bananabaking.screen.BakingScreen;
//import me.shedaniel.math.Rectangle;
//import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
//import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
//import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
//import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
//import me.shedaniel.rei.api.common.util.EntryStacks;
//
//public class BakingREIClientPlugin implements REIClientPlugin {
//    @Override
//    public void registerCategories(CategoryRegistry registry) {
//        registry.add(new BakingCategory());
//
//        registry.addWorkstations(BakingCategory.BAKING, EntryStacks.of(BananaBlocks.BAKING_OVEN));
//    }
//
//    @Override
//    public void registerDisplays(DisplayRegistry registry) {
//        registry.registerRecipeFiller(BakingRecipe.class, BakingRecipe.Type.INSTANCE,
//                BakingDisplay::new);
//    }
//
//    @Override
//    public void registerScreens(ScreenRegistry registry) {
//        registry.registerClickArea(screen -> new Rectangle(75, 30, 20, 30), BakingScreen.class,
//                BakingCategory.BAKING);
//    }
//}