package dontneg.bananabaking.block;

import dontneg.bananabaking.BananaBaking;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class BananaBlocks {

    public static final Block BANANA_CAKE = registerBlock("banana_cake",
            new BananaCake(FabricBlockSettings.copyOf(Blocks.CAKE)),1);
    public static final Block VANILLA_CROP = Registry.register(Registries.BLOCK,new Identifier(BananaBaking.MODID,"vanilla_crop"),
            new VanillaBeanCrop(FabricBlockSettings.copyOf(Blocks.WHEAT).burnable()));
    public static final Block BANANA_CROP = Registry.register(Registries.BLOCK,new Identifier(BananaBaking.MODID,"banana_crop"),
            new BananaCrop(FabricBlockSettings.copyOf(Blocks.WHEAT).burnable()));
    public static final Block BAKING_OVEN = registerBlock("baking_oven",
            new BakingOven(FabricBlockSettings.copyOf(Blocks.BRICKS).nonOpaque().luminance(state -> state.get(BakingOven.LIT) ? 10 : 0)));

    @SuppressWarnings("SameParameterValue")
    private static Block registerBlock(String key, Block block){
        registerBlockItem(key, block,64);
        return Registry.register(Registries.BLOCK, new Identifier(BananaBaking.MODID, key), block);
    }

    @SuppressWarnings("SameParameterValue")
    private static Block registerBlock(String key, Block block, int maxCount){
        registerBlockItem(key, block, maxCount);
        return Registry.register(Registries.BLOCK, new Identifier(BananaBaking.MODID, key), block);
    }

    private static void registerBlockItem(String key, Block block, int maxCount){
        Registry.register(Registries.ITEM, new Identifier(BananaBaking.MODID, key),
                new BlockItem(block, new FabricItemSettings().maxCount(maxCount)));
    }

    public static void registerBlocks(){

    }
}