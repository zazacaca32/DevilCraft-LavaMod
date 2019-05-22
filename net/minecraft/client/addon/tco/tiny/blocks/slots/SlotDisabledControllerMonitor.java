package net.minecraft.client.addon.tco.tiny.blocks.slots;

import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderControllerMonitor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotDisabledControllerMonitor extends Slot {

   ContainerTraderControllerMonitor container;


   public SlotDisabledControllerMonitor(ContainerTraderControllerMonitor container, int slotIndex, int x, int y) {
      super((IInventory)null, slotIndex, x, y);
      this.container = container;
   }

   public boolean isItemValid(ItemStack par1ItemStack) {
      return false;
   }

   public ItemStack decrStackSize(int par1) {
      return this.container.decrStackSize(par1);
   }

   public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
      return false;
   }

   public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
      this.onSlotChanged();
   }

   public ItemStack getStack() {
      return this.container.getStackInSlot(this.getSlotIndex());
   }

   public boolean getHasStack() {
      return this.getStack() != null;
   }

   public void putStack(ItemStack par1ItemStack) {
      this.container.setInventorySlotContents(this.getSlotIndex(), par1ItemStack);
      this.onSlotChanged();
   }

   public void onSlotChanged() {
      this.container.onInventoryChanged();
   }

   public int getSlotStackLimit() {
      return 1;
   }
}
