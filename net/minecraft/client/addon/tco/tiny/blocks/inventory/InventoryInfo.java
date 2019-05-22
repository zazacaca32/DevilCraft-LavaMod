package net.minecraft.client.addon.tco.tiny.blocks.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryInfo implements IInventory {

   ItemStack[] stacks = new ItemStack[63];


   public int getSizeInventory() {
      return 63;
   }

   public ItemStack getStackInSlot(int i) {
      return this.stacks[i];
   }

   public ItemStack decrStackSize(int i, int j) {
      return null;
   }

   public ItemStack getStackInSlotOnClosing(int i) {
      return null;
   }

   public void setInventorySlotContents(int i, ItemStack itemstack) {
      this.stacks[i] = itemstack;
   }

   public String getInvName() {
      return "tcoHelperInv";
   }

   public boolean isInvNameLocalized() {
      return false;
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public void onInventoryChanged() {}

   public boolean isUseableByPlayer(EntityPlayer entityplayer) {
      return false;
   }

   public void openChest() {}

   public void closeChest() {}

   public boolean isStackValidForSlot(int i, ItemStack itemstack) {
      return false;
   }
}
