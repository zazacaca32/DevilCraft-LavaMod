package net.minecraft.client.addon.tco.tiny.blocks.slots;

import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotDisabled extends MESlotBase {

   public SlotDisabled(IInventory par1iInventory, int slotIndex, int x, int y) {
      super(par1iInventory, slotIndex, x, y);
   }

   public boolean isItemValid(ItemStack par1ItemStack) {
      return false;
   }

   public ItemStack decrStackSize(int par1) {
      return super.decrStackSize(par1);
   }

   public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
      return false;
   }
}
