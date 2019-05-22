package net.minecraft.client.addon.tco.tiny.blocks.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotModuleDisable extends Slot {

   public SlotModuleDisable(IInventory par1iInventory, int slotIndex, int x, int y) {
      super(par1iInventory, slotIndex, x, y);
   }

   public boolean isItemValid(ItemStack par1ItemStack) {
      return false;
   }

   public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
      return false;
   }
}
