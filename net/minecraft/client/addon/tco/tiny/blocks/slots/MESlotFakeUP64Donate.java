package net.minecraft.client.addon.tco.tiny.blocks.slots;

import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.donate.api.IDonateUser;
import net.minecraft.client.addon.tco.tiny.Utils.ILANetworkInventory;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class MESlotFakeUP64Donate extends MESlotBase {

   final int invSlot;
   final ILANetworkInventory cellinvfake;
   private LAItemStack realItem;
   public boolean isValid = true;
   private int stopDublicateItemSlot;
   IDonateUser idu;


   public MESlotFakeUP64Donate(ILANetworkInventory cellinvfake, IDonateUser idu, int idx, int x, int y, int stopDublicateItemSlot) {
      super((IInventory)null, idx, x, y);
      this.cellinvfake = cellinvfake;
      this.invSlot = idx;
      this.stopDublicateItemSlot = stopDublicateItemSlot;
      this.realItem = LAItemStack.create(new ItemStack(LavaChestPlate.ItemDonate, 1, 1));
      this.idu = idu;
      this.realItem.setStackSize((long)idu.getCoin());
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
         return null;
      } else {
         LAItemStack is = this.getAEStackLA();
         if(is != null) {
            ItemStack listItem = Utils.getSharedItemStack(is);
            return listItem;
         } else {
            this.realItem = null;
            return null;
         }
      }
   }

   public ItemStack getDisplayStack() {
      return null;
   }

   public LAItemStack getAEStackLA() {
      this.realItem.setStackSize((long)this.idu.getCoin());
      return this.realItem;
   }

   public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
      this.onSlotChanged();
   }

   public boolean isItemValid(ItemStack a) {
      return false;
   }

   public void onSlotChanged() {}

   public int getSlotStackLimit() {
      return 2240;
   }

   public boolean isSlotInInventory(IInventory par1IInventory, int par2) {
      return false;
   }
}
