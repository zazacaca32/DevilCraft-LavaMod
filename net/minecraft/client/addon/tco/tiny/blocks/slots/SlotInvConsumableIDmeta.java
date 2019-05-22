package net.minecraft.client.addon.tco.tiny.blocks.slots;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInv;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotInvConsumableIDmeta extends SlotInv {

   private final Set itemIds = new HashSet();


   public SlotInvConsumableIDmeta(SlotInv.Access access, IInventory par1iInventory, int SlotIndex, int X, int Y, float ... itemIds) {
      super(access, par1iInventory, SlotIndex, X, Y);
      float[] var7 = itemIds;
      int var8 = itemIds.length;

      for(int var9 = 0; var9 < var8; ++var9) {
         float itemId = var7[var9];
         this.itemIds.add(Float.valueOf(itemId));
      }

   }

   public boolean accept(ItemStack stack) {
      float k = (float)stack.getItemDamage();
      k = k > 100.0F?(k /= 1000.0F):(k > 10.0F?(k /= 100.0F):(k /= 10.0F));
      return this.itemIds.contains(Float.valueOf(Float.valueOf((float)stack.itemID).floatValue() + k));
   }
}
