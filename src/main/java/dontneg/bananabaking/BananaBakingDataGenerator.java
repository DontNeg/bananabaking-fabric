package dontneg.bananabaking;

import dontneg.bananabaking.datagen.BananaLootTables;
import dontneg.bananabaking.datagen.BananaRecipes;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BananaBakingDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(BananaLootTables::new);
		pack.addProvider(BananaRecipes::new);
	}
}
