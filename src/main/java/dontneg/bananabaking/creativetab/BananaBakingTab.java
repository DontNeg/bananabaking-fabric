package dontneg.bananabaking.creativetab;

import dontneg.bananabaking.BananaBaking;
import dontneg.bananabaking.block.BananaBlocks;
import dontneg.bananabaking.item.BananaItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BananaBakingTab {

    @SuppressWarnings("unused")
    public static final ItemGroup BANANA_BAKING_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(BananaBaking.MODID, "banana_baking"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.bananabaking"))
                    .icon(() -> new ItemStack(BananaItems.BANANA_BREAD)).entries((displayContext, entries) -> {
                        entries.add(BananaItems.BANANA);
                        entries.add(BananaItems.BANANA_BUNCH);
                        entries.add(BananaItems.BANANA_BREAD);
                        entries.add(BananaItems.BANANA_CHOCOLATE);
                        entries.add(BananaItems.BANANA_COOKIE);
                        entries.add(BananaItems.BANANA_FRIED);
                        entries.add(BananaItems.BANANA_OILED);
                        entries.add(BananaItems.BANANA_SMOOTHIE);
                        entries.add(BananaItems.BANANA_PUDDING);
                        entries.add(BananaItems.BANANA_PIE_SLICE);
                        entries.add(BananaItems.BANANA_PIE);
                        entries.add(BananaItems.BANANA_DONUT);
                        entries.add(BananaBlocks.BANANA_CAKE.asItem());
                        entries.add(BananaItems.VANILLA_BEANS);
                        entries.add(BananaItems.VANILLA_EXTRACT);
                        entries.add(BananaItems.VANILLA_BEAN_SEEDS);
                        entries.add(BananaItems.BANANA_SEEDS);
                        entries.add(BananaBlocks.BAKING_OVEN.asItem());
                    }).build());

    public static void registerCreativeTabs() {

    }
}
