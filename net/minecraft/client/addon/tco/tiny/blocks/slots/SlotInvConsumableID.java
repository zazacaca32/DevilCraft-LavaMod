package net.minecraft.client.addon.tco.tiny.blocks.slots;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInv;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotInvConsumableID extends SlotInv {

   private final Set itemIds = new HashSet();


   public SlotInvConsumableID(SlotInv.Access access, IInventory par1iInventory, int SlotIndex, int X, int Y, float ... itemIds) {
      super(access, par1iInventory, SlotIndex, X, Y);
      float[] arr$ = itemIds;
      int len$ = itemIds.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         float itemId = arr$[i$];
         this.itemIds.add(Float.valueOf(itemId));
      }

   }

   public boolean accept(ItemStack stack) {
      return this.itemIds.contains(Float.valueOf((float)stack.itemID));
   }
}
