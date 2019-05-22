package net.minecraft.client.addon.tco.tiny.entity.inventory;

import net.minecraft.client.addon.tco.tiny.entity.inventory.IMerchant;
import net.minecraft.client.addon.tco.tiny.entity.inventory.MerchantRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryMerchantAdm implements IInventory {

   private final IMerchant theMerchant;
   private ItemStack[] theInventory = new ItemStack[3];
   private final EntityPlayer thePlayer;
   private MerchantRecipe currentRecipe;
   private int currentRecipeIndex;


   public InventoryMerchantAdm(EntityPlayer par1EntityPlayer, IMerchant par2IMerchant) {
      this.thePlayer = par1EntityPlayer;
      this.theMerchant = par2IMerchant;
   }

   public int getSizeInventory() {
      return this.theInventory.length;
   }

   public ItemStack getStackInSlot(int par1) {
      return this.theInventory[par1];
   }

   public ItemStack decrStackSize(int par1, int par2) {
      if(this.theInventory[par1] == null) {
         return null;
      } else {
         ItemStack itemstack;
         if(par1 == 2) {
            itemstack = this.theInventory[par1];
            this.theInventory[par1] = null;
            return itemstack;
         } else if(this.theInventory[par1].stackSize <= par2) {
            itemstack = this.theInventory[par1];
            this.theInventory[par1] = null;
            if(this.inventoryResetNeededOnSlotChange(par1)) {
               this.resetRecipeAndSlots();
            }

            return itemstack;
         } else {
            itemstack = this.theInventory[par1].splitStack(par2);
            if(this.theInventory[par1].stackSize == 0) {
               this.theInventory[par1] = null;
            }

            if(this.inventoryResetNeededOnSlotChange(par1)) {
               this.resetRecipeAndSlots();
            }

            return itemstack;
         }
      }
   }

   private boolean inventoryResetNeededOnSlotChange(int par1) {
      return par1 == 0 || par1 == 1;
   }

   public ItemStack getStackInSlotOnClosing(int par1) {
      if(this.theInventory[par1] != null) {
         ItemStack itemstack = this.theInventory[par1];
         this.theInventory[par1] = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
      this.theInventory[par1] = par2ItemStack;
      if(par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
         par2ItemStack.stackSize = this.getInventoryStackLimit();
      }

      if(this.inventoryResetNeededOnSlotChange(par1)) {
         this.resetRecipeAndSlots();
      }

   }

   public String getInvName() {
      return "mob.LavavillagerAdm";
   }

   public boolean isInvNameLocalized() {
      return false;
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
      return this.theMerchant.getCustomer() == par1EntityPlayer;
   }

   public void openChest() {}

   public void closeChest() {}

   public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack) {
      return true;
   }

   public void onInventoryChanged() {
      this.resetRecipeAndSlots();
   }

   public void resetRecipeAndSlots() {}

   public MerchantRecipe getCurrentRecipe() {
      return this.currentRecipe;
   }

   public void setCurrentRecipeIndex(int par1) {
      this.currentRecipeIndex = par1;
      this.resetRecipeAndSlots();
   }
}
