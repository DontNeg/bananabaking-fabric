package dontneg.bananabaking;

import dontneg.bananabaking.block.BananaBlocks;
import dontneg.bananabaking.creativetab.BananaBakingTab;
import dontneg.bananabaking.item.BananaItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BananaBaking implements ModInitializer {
	public static final String MODID = "bananabaking";
    public static final Logger LOGGER = LoggerFactory.getLogger("bananabaking");

	@Override
	public void onInitialize() {
		BananaBakingTab.registerCreativeTabs();
		BananaItems.registerModItems();
		BananaBlocks.registerBlocks();
	}
}