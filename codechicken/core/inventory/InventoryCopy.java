package codechicken.core.inventory;

import codechicken.core.inventory.InventoryRange;
import codechicken.core.inventory.InventoryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryCopy implements IInventory {

   public boolean[] accessible;
   public ItemStack[] items;
   public IInventory inv;


   public InventoryCopy(IInventory inv) {
      this.items = new ItemStack[inv.getSizeInventory()];
      this.accessible = new boolean[inv.getSizeInventory()];
      this.inv = inv;
      this.update();
   }

   public void update() {
      for(int i = 0; i < this.items.length; ++i) {
         ItemStack stack = this.inv.getStackInSlot(i);
         if(stack != null) {
            this.items[i] = stack.copy();
         }
      }

   }

   public InventoryCopy open(InventoryRange access) {
      int lslot = access.lastSlot();
      if(lslot > this.accessible.length) {
         boolean[] slot = new boolean[lslot];
         ItemStack[] l_items = new ItemStack[lslot];
         System.arraycopy(this.accessible, 0, slot, 0, this.accessible.length);
         System.arraycopy(this.items, 0, l_items, 0, this.items.length);
         this.accessible = slot;
         this.items = l_items;
      }

      int[] var6 = access.slots;
      int var5 = access.slots.length;

      for(int var8 = 0; var8 < var5; ++var8) {
         int var7 = var6[var8];
         this.accessible[var7] = true;
      }

      return this;
   }

   public int getSizeInventory() {
      return this.items.length;
   }

   public ItemStack getStackInSlot(int slot) {
      return this.items[slot];
   }

   public ItemStack decrStackSize(int slot, int amount) {
      return InventoryUtils.decrStackSize(this, slot, amount);
   }

   public ItemStack getStackInSlotOnClosing(int slot) {
      return InventoryUtils.getStackInSlotOnClosing(this, slot);
   }

   public void setInventorySlotContents(int slot, ItemStack stack) {
      this.items[slot] = stack;
      this.onInventoryChanged();
   }

   public String getInvName() {
      return "copy";
   }

   public boolean isUseableByPlayer(EntityPlayer player) {
      return true;
   }

   public void openChest() {}

   public void closeChest() {}

   public int getInventoryStackLimit() {
      return 64;
   }

   public void onInventoryChanged() {}

   public boolean isStackValidForSlot(int i, ItemStack itemstack) {
      return this.inv.isStackValidForSlot(i, itemstack);
   }

   public boolean isInvNameLocalized() {
      return true;
   }
}
