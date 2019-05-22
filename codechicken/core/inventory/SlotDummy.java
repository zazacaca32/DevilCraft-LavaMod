package codechicken.core.inventory;

import codechicken.core.inventory.ContainerExtended;
import codechicken.core.inventory.InventoryUtils;
import codechicken.core.inventory.SlotHandleClicks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotDummy extends SlotHandleClicks {

   public final int stackLimit;


   public SlotDummy(IInventory inv, int slot, int x, int y) {
      this(inv, slot, x, y, 64);
   }

   public SlotDummy(IInventory inv, int slot, int x, int y, int limit) {
      super(inv, slot, x, y);
      this.stackLimit = limit;
   }

   public ItemStack slotClick(ContainerExtended container, EntityPlayer player, int button, int modifier) {
      ItemStack held = player.inventory.getItemStack();
      boolean shift = modifier == 1;
      this.slotClick(held, button, shift);
      return null;
   }

   public void slotClick(ItemStack held, int button, boolean shift) {
      ItemStack tstack = this.getStack();
      int inc;
      if(held != null && (tstack == null || !InventoryUtils.canStack(held, tstack))) {
         inc = Math.min(held.stackSize, this.stackLimit);
         if(shift) {
            inc = Math.min(this.stackLimit, held.getMaxStackSize() * 16);
         }

         if(button == 1) {
            inc = 1;
         }

         this.putStack(InventoryUtils.copyStack(held, inc));
      } else if(tstack != null) {
         if(held != null) {
            inc = button == 1?-held.stackSize:held.stackSize;
            if(shift) {
               inc *= 16;
            }
         } else {
            inc = button == 1?-1:1;
            if(shift) {
               inc *= 16;
            }
         }

         int quantity = tstack.stackSize + inc;
         if(quantity <= 0) {
            this.putStack((ItemStack)null);
         } else {
            this.putStack(InventoryUtils.copyStack(tstack, quantity));
         }
      }

   }

   public void putStack(ItemStack stack) {
      if(stack != null && stack.stackSize > this.stackLimit) {
         stack = InventoryUtils.copyStack(stack, this.stackLimit);
      }

      super.putStack(stack);
   }
}
