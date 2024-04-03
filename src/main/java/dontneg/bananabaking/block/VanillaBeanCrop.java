package dontneg.bananabaking.block;

import dontneg.bananabaking.BananaBaking;
import dontneg.bananabaking.item.BananaItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class VanillaBeanCrop extends CropBlock {


    public static final int MAX_CROP_AGE = 7;
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            //Second Stage
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};

    public static final IntProperty AGE = IntProperty.of("age", 0, 7);
    public static final IntProperty STAGE = IntProperty.of("stage",0,1);
    public static final BooleanProperty SPROUTED = BooleanProperty.of("sprouted");

    public VanillaBeanCrop(Settings settings) {
        super(settings);
        this.setDefaultState(getDefaultState()
                .with(AGE,0)
                .with(STAGE,0)
                .with(SPROUTED,false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[this.getAge(state)];
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int currentAge = this.getAge(state);
            if (currentAge < this.getMaxAge()) {
                float f = getAvailableMoisture(this, world, pos);
                if (random.nextInt((int)(25.0F / f) + 1) == 0) {
                    if(currentAge == MAX_CROP_AGE && world.getBlockState(pos.up(1)).isOf(Blocks.AIR) &&
                        world.getBlockState(pos.down(1)).isOf(Blocks.FARMLAND)) {
                        this.sproutCrop(world,pos);
                    } else if(currentAge!= MAX_CROP_AGE) {
                        this.applyGrowth(world,pos,state);
                    }
                }
            }
        }
    }

    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int currentAge = this.getAge(state);
        int nextAge = this.getAge(state) + this.getGrowthAmount(world);
        int maxAge = this.getMaxAge();
        if(nextAge > maxAge) {
            nextAge = maxAge;
        }
        if(currentAge == MAX_CROP_AGE && world.getBlockState(pos.up(1)).isOf(Blocks.AIR) &&
                world.getBlockState(pos.down(1)).isOf(Blocks.FARMLAND)) {
            this.sproutCrop(world,pos);
        } else {

            world.setBlockState(pos,this.getDefaultState()
                    .with(AGE,nextAge-1)
                    .with(STAGE,world.getBlockState(pos).get(STAGE))
                    .with(SPROUTED,world.getBlockState(pos).get(SPROUTED)));

        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return super.canPlaceAt(state, world, pos) || (world.getBlockState(pos.down(1)).isOf(this) &&
                world.getBlockState(pos.down(1)).get(AGE) == MAX_CROP_AGE) &&
                !world.getBlockState(pos.down(2)).isOf(this);
    }



    @Override
    protected ItemConvertible getSeedsItem() {
        return BananaItems.VANILLA_BEAN_SEEDS;
    }

    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        builder.add(STAGE);
        builder.add(SPROUTED);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BananaBaking.LOGGER.info("Max Age: " + getMaxAge() +
                "\nIs Fertilizable: " + isFertilizable(world, pos, state) +
                "\nIs Mature: " + isMature(state));
        if(isFertilizable(world,pos,state)){
            return ActionResult.PASS;

        }
        return ActionResult.FAIL;
    }

    @Override
    public int getMaxAge() {
        if(this.getDefaultState().get(STAGE)==0 &&
        !this.getDefaultState().get(SPROUTED)){
            return MAX_CROP_AGE+1;
        }
        return MAX_CROP_AGE;
    }

    public void sproutCrop(World world, BlockPos pos){
        world.setBlockState(pos.up(1),this.getDefaultState()
                .with(AGE,0)
                .with(STAGE,1));
        world.setBlockState(pos,this.getDefaultState()
                .with(AGE,world.getBlockState(pos).get(AGE))
                .with(STAGE,world.getBlockState(pos).get(STAGE))
                .with(SPROUTED,true));
    }
}
