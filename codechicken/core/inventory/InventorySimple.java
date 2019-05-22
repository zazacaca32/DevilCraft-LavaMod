package codechicken.core.inventory;

import codechicken.core.inventory.InventoryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventorySimple implements IInventory {

   public ItemStack[] items;
   public int limit;
   public String name;


   public InventorySimple(ItemStack[] items, int limit, String name) {
      this.items = items;
      this.limit = limit;
      this.name = name;
   }

   public InventorySimple(ItemStack[] items, String name) {
      this(items, 64, name);
   }

   public InventorySimple(ItemStack[] items, int limit) {
      this(items, limit, "inv");
   }

   public InventorySimple(ItemStack[] items) {
      this(items, 64, "inv");
   }

   public InventorySimple(int size, int limit, String name) {
      this(new ItemStack[size], limit, name);
   }

   public InventorySimple(int size, int limit) {
      this(size, limit, "inv");
   }

   public InventorySimple(int size, String name) {
      this(size, 64, name);
   }

   public InventorySimple(int size) {
      this(size, 64, "inv");
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
      return this.name;
   }

   public int getInventoryStackLimit() {
      return this.limit;
   }

   public boolean isUseableByPlayer(EntityPlayer var1) {
      return true;
   }

   public void openChest() {}

   public void closeChest() {}

   public boolean isStackValidForSlot(int i, ItemStack itemstack) {
      return true;
   }

   public boolean isInvNameLocalized() {
      return true;
   }

   public void onInventoryChanged() {}
}
