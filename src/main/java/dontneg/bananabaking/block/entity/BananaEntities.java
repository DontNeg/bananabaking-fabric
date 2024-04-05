package dontneg.bananabaking.block.entity;

import dontneg.bananabaking.BananaBaking;
import dontneg.bananabaking.block.BananaBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BananaEntities {
    public static final BlockEntityType<BakingOvenEntity> BAKING_OVEN_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(BananaBaking.MODID, "baking_oven"),
                    FabricBlockEntityTypeBuilder.create(BakingOvenEntity::new,
                            BananaBlocks.BAKING_OVEN).build());

    public static void registerBlockEntities() {
        BananaBaking.LOGGER.info("Banana Baking - Block Entity Initializing!");
    }
}
