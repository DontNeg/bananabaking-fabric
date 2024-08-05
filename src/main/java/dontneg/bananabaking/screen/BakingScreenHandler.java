package dontneg.bananabaking.screen;

import dontneg.bananabaking.block.entity.BakingOvenEntity;
import dontneg.bananabaking.codec.BakingData;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;

public class BakingScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final BakingOvenEntity blockEntity;
    public final ScreenHandlerContext context;

    public BakingScreenHandler(int syncId, PlayerInventory inventory, BakingData data) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(data.pos()), new ArrayPropertyDelegate(10),
                ScreenHandlerContext.create(inventory.player.getWorld(),inventory.player.getBlockPos()));
    }

    public BakingScreenHandler(int syncId, PlayerInventory playerInventory,
                               BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate, ScreenHandlerContext context) {
        super(BananaScreenHandlers.BAKING_OVEN_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) blockEntity), 10);
        this.inventory = ((Inventory) blockEntity);
        this.context = context;
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = ((BakingOvenEntity) blockEntity);

        this.addSlot(new Slot(inventory, 0, 20, 16));
        this.addSlot(new Slot(inventory, 1, 38, 16));
        this.addSlot(new Slot(inventory, 2, 56, 16));
        this.addSlot(new Slot(inventory, 3, 20, 34));
        this.addSlot(new Slot(inventory, 4, 38, 34));
        this.addSlot(new Slot(inventory, 5, 56, 34));
        this.addSlot(new Slot(inventory, 6, 20, 52));
        this.addSlot(new Slot(inventory, 7, 38, 52));
        this.addSlot(new Slot(inventory, 8, 56, 52));
        this.addSlot(new Slot(inventory, 9, 126, 35){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        addProperties(arrayPropertyDelegate);
    }

    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int progressArrowSize = 23;
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }
            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}