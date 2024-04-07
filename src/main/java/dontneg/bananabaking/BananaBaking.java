package dontneg.bananabaking;

import dontneg.bananabaking.block.BananaBlocks;
import dontneg.bananabaking.block.entity.BananaEntities;
import dontneg.bananabaking.creativetab.BananaBakingTab;
import dontneg.bananabaking.item.BananaItems;
import dontneg.bananabaking.recipe.BananaRecipes;
import dontneg.bananabaking.screen.BananaScreenHandlers;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BananaBaking implements ModInitializer {
	public static final String MODID = "bananabaking";
    public static final Logger LOGGER = LoggerFactory.getLogger("bananabaking");

	@Override
	public void onInitialize() {
		BananaBakingTab.registerCreativeTabs();
		BananaItems.registerItems();
		BananaBlocks.registerBlocks();
		BananaScreenHandlers.registerScreenHandlers();
		BananaEntities.registerBlockEntities();
		BananaRecipes.registerRecipes();
		LOGGER.info("Banana Baking - Mod Initializing");
	}
}