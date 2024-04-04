package dontneg.bananabaking.block;

import dontneg.bananabaking.item.BananaItems;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class BananaCrop extends CropBlock {
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(0.0, 14.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 12.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 10.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 8.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 6.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 4.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 2.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};
    public BananaCrop(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[this.getAge(state)];
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.up()).isIn(TagKey.of(RegistryKeys.BLOCK,new Identifier("minecraft","leaves"))) &&
                world.getBlockState(pos.down()).isOf(Blocks.AIR);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return BananaItems.BANANA_SEEDS;
    }
}
