package dontneg.bananabaking.block.entity;

import dontneg.bananabaking.block.BakingOven;
import dontneg.bananabaking.codec.BakingData;
import dontneg.bananabaking.recipe.BakingRecipe;
import dontneg.bananabaking.recipe.BakingRecipeInput;
import dontneg.bananabaking.screen.BakingScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Optional;

@SuppressWarnings({"rawtypes"})
public class BakingOvenEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(10, ItemStack.EMPTY);
    private static final HashSet<Block> fireBlocks = new HashSet<>();
    private static final int OUTPUT_SLOT = 9;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public BakingOvenEntity(BlockPos pos, BlockState state) {
        super(BananaEntities.BAKING_OVEN_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BakingOvenEntity.this.progress;
                    case 1 -> BakingOvenEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BakingOvenEntity.this.progress = value;
                    case 1 -> BakingOvenEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
        fireBlocks.add(Blocks.FIRE);
        fireBlocks.add(Blocks.CAMPFIRE);
        fireBlocks.add(Blocks.LAVA);
        fireBlocks.add(Blocks.SOUL_CAMPFIRE);
        fireBlocks.add(Blocks.SOUL_FIRE);
        fireBlocks.add(Blocks.LAVA_CAULDRON);
    }

    @Override
    public void markDirty() {
        assert world != null;
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }

    @Override
    public Object getScreenOpeningData(ServerPlayerEntity player) {
        return new BakingData(getPos());
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Baking Oven");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("baking_oven.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("baking_oven.progress");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BakingScreenHandler(syncId, playerInventory, this, this.propertyDelegate, ScreenHandlerContext.create(world,pos));
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }
        if(this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount()) {
            if(hasRecipe() && hasFire(this)) {
                world.setBlockState(pos,world.getBlockState(pos).with(BakingOven.LIT,true));
                this.increaseCraftProgress();
                markDirty(world, pos, state);
                if(hasCraftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
                world.setBlockState(pos,world.getBlockState(pos).with(BakingOven.LIT,false));
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private void craftItem() {
        Optional<RecipeEntry<BakingRecipe>> recipe = getCurrentRecipe();
        Vec3d vec3d = Vec3d.ofCenter(pos);
        for(int i = 0;i<9;i++){
            if(!this.getStack(i).getRecipeRemainder().isEmpty()){
                assert world != null;
                ItemDispenserBehavior.spawnItem(world, this.getStack(i).getRecipeRemainder(), 6, world.getBlockState(pos).get(Properties.HORIZONTAL_FACING), vec3d);
            }
            this.removeStack(i, 1);
        }

        this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUT_SLOT).getCount() + recipe.get().value().getResult(null).getCount()));
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<BakingRecipe>> recipe = getCurrentRecipe();
        return recipe.isPresent() && this.getStack(OUTPUT_SLOT).getCount() + recipe.get().value().getResult(null).getCount() <= 64
                && (this.getStack(OUTPUT_SLOT).getItem() == recipe.get().value().getResult(null).getItem() || this.getStack(OUTPUT_SLOT).isEmpty());
    }

    @SuppressWarnings("DataFlowIssue")
    private static boolean hasFire(BakingOvenEntity entity){
        return fireBlocks.contains(entity.world.getBlockState(entity.getPos().down()).getBlock());
    }

    @SuppressWarnings("DataFlowIssue")
    private Optional<RecipeEntry<BakingRecipe>> getCurrentRecipe() {
        DefaultedList<ItemStack> inv = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        for(int i = 0; i < this.size(); i++) {
            inv.set(i, this.getStack(i));
        }
        return getWorld().getRecipeManager().getFirstMatch(BakingRecipe.Type.INSTANCE, new BakingRecipeInput(inv), getWorld());
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}