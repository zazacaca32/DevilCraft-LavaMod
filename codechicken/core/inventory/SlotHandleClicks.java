package codechicken.core.inventory;

import codechicken.core.inventory.ContainerExtended;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class SlotHandleClicks extends Slot {

   public SlotHandleClicks(IInventory inv, int slot, int x, int y) {
      super(inv, slot, x, y);
   }

   public abstract ItemStack slotClick(ContainerExtended var1, EntityPlayer var2, int var3, int var4);
}
