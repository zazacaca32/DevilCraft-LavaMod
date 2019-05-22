package net.minecraft.client.addon.tco.tiny.blocks.slots;

import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotInaccessable extends MESlotBase {

   ItemStack dspStack = null;


   public SlotInaccessable(IInventory i, int slotIdx, int x, int y) {
      super(i, slotIdx, x, y);
   }

   public ItemStack getDisplayStack() {
      ItemStack dsp;
      if(this.dspStack == null && (dsp = super.getDisplayStack()) != null) {
         this.dspStack = dsp.copy();
      }

      return this.dspStack;
   }

   public void onSlotChanged() {
      super.onSlotChanged();
      this.dspStack = null;
   }

   public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
      return false;
   }

   public boolean isItemValid(ItemStack i) {
      return false;
   }
}
