package net.minecraft.client.addon.tco.tiny.blocks.addon;

import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ContainerExt extends ContainerBase {

   public ContainerExt(IInventory base) {
      super(base);
   }

   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
      Slot slot = (Slot)super.inventorySlots.get(par2);
      return slot != null?slot.getStack():null;
   }
}
