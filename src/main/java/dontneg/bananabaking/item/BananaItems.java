package dontneg.bananabaking.item;

import dontneg.bananabaking.BananaBaking;
import dontneg.bananabaking.block.BananaBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BananaItems {
    public static final Item BANANA = registerItem("banana",
            new Item(new FabricItemSettings().food(BananaFood.BANANA)));
    public static final Item BANANA_BREAD = registerItem("banana_bread",
            new Item(new FabricItemSettings().food(BananaFood.BANANA_BREAD)));
    public static final Item BANANA_PIE = registerItem("banana_pie",
            new Item(new FabricItemSettings().food(BananaFood.BANANA_PIE)));
    public static final Item BANANA_PIE_SLICE = registerItem("banana_pie_slice",
            new Item(new FabricItemSettings().food(BananaFood.BANANA_PIE_SLICE)));
    public static final Item BANANA_SMOOTHIE = registerItem("banana_smoothie",
            new Item(new FabricItemSettings().food(BananaFood.BANANA_SMOOTHIE)));
    public static final Item BANANA_CHOCOLATE = registerItem("banana_chocolate",
            new Item(new FabricItemSettings().food(BananaFood.BANANA_CHOCOLATE)));
    public static final Item BANANA_OILED = registerItem("banana_oiled",
            new Item(new FabricItemSettings().food(BananaFood.BANANA_OILED)));
    public static final Item BANANA_FRIED = registerItem("banana_fried",
            new Item(new FabricItemSettings().food(BananaFood.BANANA_FRIED)));
    public static final Item BANANA_COOKIE = registerItem("banana_cookie",
            new Item(new FabricItemSettings().food(BananaFood.BANANA_COOKIE)));
    public static final Item BANANA_PUDDING = registerItem("banana_pudding",
            new Item(new FabricItemSettings().food(BananaFood.BANANA_PUDDING)));
    public static final Item BANANA_DONUT = registerItem("banana_donut",
            new Item(new FabricItemSettings().food(BananaFood.BANANA_DONUT)));
    public static final Item VANILLA_BEAN = registerItem("vanilla_bean",
            new Item(new FabricItemSettings()));
    public static final Item BANANA_BUNCH = registerItem("banana_bunch",
            new Item(new FabricItemSettings()));
    public static final Item VANILLA_BEAN_SEEDS = registerItem("vanilla_bean_seeds",
            new AliasedBlockItem(BananaBlocks.VANILLA_CROP, new FabricItemSettings()));
    public static final Item BANANA_SEEDS = registerItem("banana_seeds",
            new AliasedBlockItem(BananaBlocks.BANANA_CROP, new FabricItemSettings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BananaBaking.MODID, name), item);
    }

    public static void registerItems() {
        BananaBaking.LOGGER.info("Banana Baking - Item Initializing!");

    }
}