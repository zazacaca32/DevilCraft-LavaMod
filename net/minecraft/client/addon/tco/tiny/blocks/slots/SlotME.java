package net.minecraft.client.addon.tco.tiny.blocks.slots;

import net.minecraft.client.addon.tco.tiny.NetworkedIMEI;
import net.minecraft.client.addon.tco.tiny.Utils.LAInternalInventory;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotME extends MESlotBase {

   ItemStack realItem;
   ItemStack displayItem;
   ItemStack AttunedSlot;
   private final NetworkedIMEI cp;
   private int SlotIndex;
   private final int realSlotIndex;


   public SlotME(NetworkedIMEI inv, int SlotIndex, int x, int y) {
      super((IInventory)null, 0, x, y);
      this.cp = inv;
      this.SlotIndex = this.realSlotIndex = SlotIndex;
   }

   public void setScroll(int rows, int perRow) {
      this.SlotIndex = this.realSlotIndex + rows * perRow;
   }

   public void resetSlot() {
      this.AttunedSlot = null;
   }

   public boolean attuneSlot(ItemStack i) {
      this.AttunedSlot = i;
      return true;
   }

   public void onSlotChange(ItemStack par1ItemStack, ItemStack par2ItemStack) {
      if(par1ItemStack != null && par2ItemStack != null && par1ItemStack.itemID == par2ItemStack.itemID) {
         int var3 = par2ItemStack.stackSize - par1ItemStack.stackSize;
         if(var3 > 0) {
            this.onCrafting(par1ItemStack, var3);
         }
      }

   }

   protected void onCrafting(ItemStack par1ItemStack, int par2) {}

   protected void onCrafting(ItemStack par1ItemStack) {}

   public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
      this.onSlotChanged();
   }

   public boolean isItemValid(ItemStack a) {
      return true;
   }

   public ItemStack addToInv(ItemStack a) {
      LAInternalInventory ime = this.cp.provideCell();
      if(a == null) {
         return null;
      } else {
         if(a.stackSize < 0) {
            a.stackSize = 1;
         }

         if(ime == null) {
            return a;
         } else {
            LAItemStack o = null;
            int ItemsToAdd = a.stackSize;
            if(ItemsToAdd <= 0) {
               return a;
            } else {
               LAItemStack toAdd = LAItemStack.create(a);
               o = LAItemStack.create(a);
               toAdd.setStackSize((long)ItemsToAdd);
               o.setStackSize((long)(a.stackSize - ItemsToAdd));
               toAdd = ime.addItems(toAdd);
               if(toAdd != null) {
                  o.incStackSize(toAdd.getStackSize());
               }

               if(o.getStackSize() == 0L) {
                  o = null;
               }

               return o == null?null:o.getItemStack();
            }
         }
      }
   }

   public LAItemStack getAEStackLA() {
      LAItemStack is;
      if(Utils.isServer()) {
         is = LAItemStack.create(this.AttunedSlot);
      } else {
         is = this.cp.getAvailableItemsLA()[this.realSlotIndex];
      }

      return is;
   }

   public ItemStack getStack() {
      if(Utils.isServer()) {
         return this.AttunedSlot != null?this.AttunedSlot:null;
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

   public void putStack(ItemStack par1ItemStack) {}

   public void onSlotChanged() {}

   public int getSlotStackLimit() {
      return Integer.MAX_VALUE;
   }

   public ItemStack decrStackSize(int par1) {
      LAInternalInventory ime = this.cp.provideCell();
      if(ime == null) {
         return null;
      } else {
         ItemStack is = this.getStack();
         if(is == null) {
            return null;
         } else {
            LAItemStack i = LAItemStack.create(is);
            i.setStackSize((long)par1);
            if(i.getStackSize() > 0L) {
               LAItemStack rv = ime.extractItems(i);
               if(rv != null) {
                  return rv.getItemStack();
               }
            }

            return null;
         }
      }
   }

   public boolean isSlotInInventory(IInventory par1IInventory, int par2) {
      return false;
   }

   public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
      LAInternalInventory ime = this.cp.provideCell();
      return ime != null;
   }

   public ItemStack getCmpStack() {
      this.getStack();
      return this.realItem;
   }
}
