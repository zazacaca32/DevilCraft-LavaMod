package net.minecraft.client.addon.tco.tiny.blocks.slots;

import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class MESlotFake extends MESlotBase {

   int invSlot;


   public MESlotFake(IInventory inv, int idx, int x, int y) {
      super(inv, idx, x, y);
      this.invSlot = idx;
   }

   public void addToInv(ItemStack is) {
      if(is != null) {
         ItemStack current = super.inventory.getStackInSlot(this.invSlot);
         if(current != null && Utils.isSameItem(current, is)) {
            current.stackSize += is.stackSize;
            if(current.stackSize > super.inventory.getInventoryStackLimit()) {
               current.stackSize = super.inventory.getInventoryStackLimit();
            }
         } else {
            current = is.copy();
            if(current.stackSize > super.inventory.getInventoryStackLimit()) {
               current.stackSize = super.inventory.getInventoryStackLimit();
            }

            super.inventory.setInventorySlotContents(this.invSlot, current);
         }
      } else {
         super.inventory.setInventorySlotContents(this.invSlot, (ItemStack)null);
      }

   }

   public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
      return false;
   }

   public ItemStack decrStackSize(int par1) {
      ItemStack current = super.inventory.getStackInSlot(this.invSlot);
      if(current != null) {
         --current.stackSize;
         if(current.stackSize <= 0) {
            super.inventory.setInventorySlotContents(this.invSlot, (ItemStack)null);
         }
      }

      return null;
   }

   public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {}

   public void putStack(ItemStack par1ItemStack) {
      super.inventory.setInventorySlotContents(this.invSlot, par1ItemStack);
   }

   public boolean isItemValid(ItemStack par1ItemStack) {
      return true;
   }

   public LAItemStack getAEStackLA() {
      LAItemStack lit = LAItemStack.create(super.inventory.getStackInSlot(this.invSlot));
      if(lit != null) {
         ;
      }

      return lit;
   }
}
