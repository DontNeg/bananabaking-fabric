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
            new BananaCake(FabricBlockSettings.copyOf(Blocks.CAKE)));
    public static final Block VANILLA_CROP = Registry.register(Registries.BLOCK,new Identifier(BananaBaking.MODID,"vanilla_crop"),
            new VanillaBeanCrop(FabricBlockSettings.copyOf(Blocks.WHEAT)));
    public static final Block BANANA_CROP = Registry.register(Registries.BLOCK,new Identifier(BananaBaking.MODID,"banana_crop"),
            new BananaCrop(FabricBlockSettings.copyOf(Blocks.WHEAT)));
    public static final Block BAKING_OVEN = registerBlock("baking_oven",
            new BakingOven(FabricBlockSettings.copyOf(Blocks.BRICKS).nonOpaque()));

    @SuppressWarnings("SameParameterValue")
    private static Block registerBlock(String key, Block block){
        registerBlockItem(key, block);
        return Registry.register(Registries.BLOCK, new Identifier(BananaBaking.MODID, key), block);
    }

    private static void registerBlockItem(String key, Block block){
        Registry.register(Registries.ITEM, new Identifier(BananaBaking.MODID, key),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerBlocks(){
        BananaBaking.LOGGER.info("Banana Baking - Block Initializing!");
    }
}
