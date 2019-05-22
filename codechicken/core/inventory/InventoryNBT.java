package codechicken.core.inventory;

import codechicken.core.inventory.InventoryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InventoryNBT implements IInventory {

   protected ItemStack[] items;
   protected NBTTagCompound tag;


   public InventoryNBT(int size, NBTTagCompound tag) {
      this.tag = tag;
      this.items = new ItemStack[size];
      this.readNBT();
   }

   private void writeNBT() {
      this.tag.setTag("items", InventoryUtils.writeItemStacksToTag(this.items, this.getInventoryStackLimit()));
   }

   private void readNBT() {
      if(this.tag.hasKey("items")) {
         InventoryUtils.readItemStacksFromTag(this.items, this.tag.getTagList("items"));
      }

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
      return "NBT";
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public void onInventoryChanged() {
      this.writeNBT();
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
}
