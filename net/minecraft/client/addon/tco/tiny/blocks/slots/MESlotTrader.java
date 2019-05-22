package net.minecraft.client.addon.tco.tiny.blocks.slots;

import net.minecraft.client.addon.tco.tiny.Utils.ILANetworkInventory;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class MESlotTrader extends MESlotBase {

   int invSlot;
   final ILANetworkInventory cellinvfake;
   private ItemStack realItem;
   private ItemStack displayItem;
   private final int slotCompare;


   public MESlotTrader(ILANetworkInventory cellinvfake, int idx, int x, int y, int slotCompare) {
      super((IInventory)null, idx, x, y);
      this.slotCompare = slotCompare;
      this.cellinvfake = cellinvfake;
      this.invSlot = idx;
   }

   public ItemStack addToInv(ItemStack is) {
      ItemStack s1 = this.addToInv1(is);
      this.cellinvfake.getMETileBase().triggerContainerUpdate();
      return s1;
   }

   public synchronized ItemStack addToInv1(ItemStack is) {
      if(is != null) {
         LAItemStack current = this.cellinvfake.getStackInSlot(this.invSlot);
         if(current != null) {
            if(Utils.isSameItem(current.getSharedItemStack(), is)) {
               if(is.getMaxStackSize() < 64 && current.getStackSize() >= (long)is.getMaxStackSize()) {
                  return is;
               } else if(current.getStackSize() + (long)is.stackSize > (long)this.getSlotStackLimit()) {
                  current.setStackSize((long)this.getSlotStackLimit());
                  long size = (long)is.stackSize - ((long)this.getSlotStackLimit() - current.getStackSize());
                  is.stackSize = (int)size;
                  this.cellinvfake.setInventorySlotContents(this.invSlot, current);
                  return is;
               } else {
                  current.incStackSize((long)is.stackSize);
                  this.cellinvfake.setInventorySlotContents(this.invSlot, current);
                  return null;
               }
            } else {
               return is;
            }
         } else {
            current = LAItemStack.create(is.copy());
            if(current.getStackSize() > (long)this.getSlotStackLimit()) {
               current.setStackSize((long)this.getSlotStackLimit());
            }

            this.cellinvfake.setInventorySlotContents(this.invSlot, current);
            return null;
         }
      } else {
         return null;
      }
   }

   public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
      return false;
   }

   public ItemStack decrStackSize(int par1) {
      ItemStack is = this.getStack();
      if(is == null) {
         return null;
      } else {
         LAItemStack i = LAItemStack.create(is);
         i.setStackSize((long)par1);
         if(i.getStackSize() > 0L) {
            LAItemStack rv = this.extractItems(i);
            if(rv != null) {
               return rv.getItemStack();
            }
         }

         return null;
      }
   }

   public synchronized LAItemStack extractItems(LAItemStack request) {
      if(request == null) {
         return null;
      } else {
         LAItemStack l = this.cellinvfake.getStackInSlot(this.invSlot);
         if(l == null) {
            return null;
         } else if(!l.eq(request)) {
            return null;
         } else {
            ItemStack sharedItem = Utils.getSharedItemStack(request);
            int size = sharedItem.stackSize;
            int slimit = sharedItem.getItem().getItemStackLimit();
            if(size > slimit) {
               size = slimit;
            }

            boolean moveBackStacks = true;
            LAItemStack Results = null;
            Results = l.copy();
            if(l.getStackSize() <= (long)size) {
               Results.setStackSize(l.getStackSize());
               this.cellinvfake.getStackInSlot(this.invSlot).decStackSize(l.getStackSize());
               if(this.cellinvfake.getStackInSlot(this.invSlot).getStackSize() <= 0L) {
                  this.cellinvfake.setInventorySlotContents(this.invSlot, (LAItemStack)null);
               }
            } else {
               l.setStackSize(l.getStackSize() - (long)size);
               Results.setStackSize((long)size);
            }

            this.cellinvfake.getMETileBase().triggerContainerUpdate();
            return Results;
         }
      }
   }

   public void putStack(ItemStack par1ItemStack) {}

   public ItemStack getStack() {
      if(Utils.isServer()) {
         return this.getAEStackLA() != null?Utils.getSharedItemStack(this.getAEStackLA()):null;
      } else {
         LAItemStack is = this.getAEStackLA();
         if(is != null) {
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
         } else {
            this.displayItem = null;
            this.realItem = null;
            return null;
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
      if(this.slotCompare == -1) {
         return false;
      } else if(a == null) {
         return false;
      } else if(!this.cellinvfake.getMETileBase().isJob()) {
         return false;
      } else {
         LAItemStack st = this.cellinvfake.getStackInSlot(this.slotCompare);
         return st == null?false:st.eq(a);
      }
   }

   public void onSlotChanged() {}

   public int getSlotStackLimit() {
      return 11000;
   }

   public boolean isSlotInInventory(IInventory par1IInventory, int par2) {
      return false;
   }
}
