package codechicken.nei.api;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public interface IInfiniteItemHandler {

   void onPickup(ItemStack var1);

   void onPlaceInfinite(ItemStack var1);

   boolean canHandleItem(ItemStack var1);

   boolean isItemInfinite(ItemStack var1);

   void replenishInfiniteStack(InventoryPlayer var1, int var2);

   ItemStack getInfiniteItem(ItemStack var1);
}
