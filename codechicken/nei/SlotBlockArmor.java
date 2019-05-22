package codechicken.nei;

import codechicken.core.CommonUtils;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.SlotArmor;
import net.minecraft.item.ItemStack;

public class SlotBlockArmor extends SlotArmor {

   public SlotBlockArmor(ContainerPlayer container, InventoryPlayer invPlayer, int i, int j, int k, int armourslot) {
      super(container, invPlayer, i, j, k, armourslot);
   }

   public boolean isItemValid(ItemStack par1ItemStack) {
      return super.isItemValid(par1ItemStack) || CommonUtils.isBlock(par1ItemStack.itemID);
   }
}
