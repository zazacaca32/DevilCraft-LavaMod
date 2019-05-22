package codechicken.core.inventory;

import codechicken.core.inventory.ContainerExtended;
import codechicken.core.inventory.SlotHandleClicks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotDummyOutput extends SlotHandleClicks {

   public SlotDummyOutput(IInventory inv, int slot, int x, int y) {
      super(inv, slot, x, y);
   }

   public ItemStack slotClick(ContainerExtended container, EntityPlayer player, int button, int modifier) {
      return null;
   }
}
