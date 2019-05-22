package net.minecraft.client.addon.tco.tiny.blocks.slots;

import net.minecraft.client.addon.tco.tiny.Utils.ILANetworkInventory;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class MESlotFakeUP64 extends MESlotBase {

   int invSlot;
   final ILANetworkInventory cellinvfake;
   private ItemStack realItem;
   private ItemStack displayItem;
   public boolean isValid = true;
   private int stopDublicateItemSlot;


   public MESlotFakeUP64(ILANetworkInventory cellinvfake, int idx, int x, int y, int stopDublicateItemSlot) {
      super((IInventory)null, idx, x, y);
      this.cellinvfake = cellinvfake;
      this.invSlot = idx;
      this.stopDublicateItemSlot = stopDublicateItemSlot;
   }

   public void addToInv(ItemStack is) {
      if(is != null) {
         LAItemStack current = this.cellinvfake.getStackInSlot(this.invSlot);
         LAItemStack currentDublicate;
         if(this.stopDublicateItemSlot != -1 && (currentDublicate = this.cellinvfake.getStackInSlot(this.stopDublicateItemSlot)) != null && Utils.isSameItem(currentDublicate.getSharedItemStack(), is)) {
            return;
         }

         if(current != null && Utils.isSameItem(current.getSharedItemStack(), is)) {
            current.incStackSize((long)is.stackSize);
            if(current.getStackSize() > (long)this.getSlotStackLimit()) {
               current.setStackSize((long)this.getSlotStackLimit());
            }

            if(is.getMaxStackSize() < 64 && current.getStackSize() > (long)is.getMaxStackSize()) {
               current.setStackSize((long)is.getMaxStackSize());
            }
         } else {
            current = LAItemStack.create(is.copy());
            if(current.getStackSize() > (long)this.getSlotStackLimit()) {
               current.setStackSize((long)this.getSlotStackLimit());
            }

            this.cellinvfake.setInventorySlotContents(this.invSlot, current);
         }
      } else {
         this.cellinvfake.setInventorySlotContents(this.invSlot, (LAItemStack)null);
      }

      this.cellinvfake.getMETileBase().triggerContainerUpdate();
      this.cellinvfake.getMETileBase().triggerBlockUpdate();
   }

   public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
      return false;
   }

   public ItemStack decrStackSize(int par1) {
      return null;
   }

   public void putStack(ItemStack par1ItemStack) {}

   public ItemStack getStack() {
      if(Utils.isServer()) {
         return this.getAEStackLA() != null?Utils.getSharedItemStack(this.getAEStackLA()):null;
      } else {
         LAItemStack is = this.getAEStackLA();
         if(is == null) {
            this.displayItem = null;
            return this.realItem = null;
         } else {
            ItemStack listItem = Utils.getSharedItemStack(is);
            if(is.getCountRequestable() > 0L && listItem.stackSize == 0) {
               listItem.stackSize = (int)Math.max(-is.getCountRequestable(), -2147483648L);
            }

            if(Utils.isSameItem(this.realItem, listItem)) {
               this.displayItem.stackSize = listItem.stackSize;
               this.realItem.stackSize = listItem.stackSize;
               return this.displayItem;
            } else {
               this.displayItem = listItem.copy();
               this.realItem = Utils.cloneItemStack(listItem);
               return this.displayItem;
            }
         }
      }
   }

   public ItemStack getDisplayStack() {
      this.getStack();
      return this.displayItem;
   }

   public LAItemStack getAEStackLA() {
      return this.cellinvfake.getStackInSlot(this.invSlot);
   }

   public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
      this.onSlotChanged();
   }

   public boolean isItemValid(ItemStack a) {
      return this.cellinvfake.getMETileBase().isJob() && this.isValid;
   }

   public void onSlotChanged() {}

   public int getSlotStackLimit() {
      return 2240;
   }

   public boolean isSlotInInventory(IInventory par1IInventory, int par2) {
      return false;
   }
}
