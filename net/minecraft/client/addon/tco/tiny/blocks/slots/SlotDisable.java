package net.minecraft.client.addon.tco.tiny.blocks.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotDisable extends Slot {

   public SlotDisable(IInventory par1iInventory, int slotIndex, int x, int y) {
      super(par1iInventory, slotIndex, x, y);
   }

   public boolean isItemValid(ItemStack par1ItemStack) {
      return false;
   }

   public ItemStack decrStackSize(int par1) {
      return null;
   }

   public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
      return false;
   }

   public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {}
}
