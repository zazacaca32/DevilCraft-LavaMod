package codechicken.nei;

import codechicken.core.inventory.InventoryUtils;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class InventoryCraftingDummy extends InventoryCrafting {

   public InventoryCraftingDummy() {
      super((Container)null, 3, 3);
   }

   public ItemStack decrStackSize(int par1, int par2) {
      return InventoryUtils.decrStackSize(this, par1, par2);
   }

   public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
      super.stackList[par1] = par2ItemStack;
   }
}
