package net.minecraft.client.addon.tco.tiny.blocks.slots;

import net.minecraft.client.addon.tco.tiny.blocks.slots.ISlotPlayerSide;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotBase;
import net.minecraft.inventory.IInventory;

public class SlotPlayerInv extends MESlotBase implements ISlotPlayerSide {

   public SlotPlayerInv(IInventory par1iInventory, int par2, int par3, int par4) {
      super(par1iInventory, par2, par3, par4);
   }
}
