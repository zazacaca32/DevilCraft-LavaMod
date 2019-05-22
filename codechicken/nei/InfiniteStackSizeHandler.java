package codechicken.nei;

import codechicken.core.inventory.InventoryUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.api.IInfiniteItemHandler;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class InfiniteStackSizeHandler implements IInfiniteItemHandler {

   public void onPickup(ItemStack heldItem) {
      heldItem.stackSize = 1;
   }

   public void onPlaceInfinite(ItemStack heldItem) {
      heldItem.stackSize = 111;
   }

   public boolean canHandleItem(ItemStack stack) {
      return !stack.isItemStackDamageable();
   }

   public boolean isItemInfinite(ItemStack stack) {
      return stack.stackSize == -1 || stack.stackSize > 100;
   }

   public void replenishInfiniteStack(InventoryPlayer inv, int slotNo) {
      ItemStack stack = inv.getStackInSlot(slotNo);
      stack.stackSize = 111;

      for(int i = 0; i < inv.getSizeInventory(); ++i) {
         if(i != slotNo && NEIServerUtils.areStacksSameType(stack, inv.getStackInSlot(i))) {
            inv.setInventorySlotContents(i, (ItemStack)null);
         }
      }

   }

   public ItemStack getInfiniteItem(ItemStack typeStack) {
      return InventoryUtils.copyStack(typeStack, -1);
   }
}
