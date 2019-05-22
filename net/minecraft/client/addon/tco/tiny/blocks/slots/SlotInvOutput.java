package net.minecraft.client.addon.tco.tiny.blocks.slots;

import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInv;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotInvOutput extends SlotInv {

   public SlotInvOutput(IInventory par1iInventory, int SlotIndex, int X, int Y) {
      super(SlotInv.Access.O, par1iInventory, SlotIndex, X, Y);
   }

   public boolean accept(ItemStack stack) {
      return false;
   }
}
