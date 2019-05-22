package codechicken.nei;

import codechicken.nei.api.IInfiniteItemHandler;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class InfiniteToolHandler implements IInfiniteItemHandler {

   public void onPickup(ItemStack heldItem) {
      heldItem.setItemDamage(0);
   }

   public void onPlaceInfinite(ItemStack heldItem) {
      heldItem.setItemDamage(-32000);
   }

   public void replenishInfiniteStack(InventoryPlayer inv, int slotNo) {
      inv.getStackInSlot(slotNo).setItemDamage(-32000);
   }

   public boolean canHandleItem(ItemStack stack) {
      return stack.getItem().isDamageable() && stack.getMaxStackSize() == 1;
   }

   public boolean isItemInfinite(ItemStack stack) {
      return stack.getItemDamage() < -30000;
   }

   public ItemStack getInfiniteItem(ItemStack typeStack) {
      return new ItemStack(typeStack.itemID, 1, -32000);
   }
}
