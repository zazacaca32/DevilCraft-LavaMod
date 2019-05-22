package codechicken.nei;

import codechicken.nei.forge.IContainerSlotClickHandler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;

public class NEIInventoryHandler implements IContainerSlotClickHandler {

   public void beforeSlotClick(GuiContainer gui, int slotIndex, int button, Slot slot, int modifier) {}

   public boolean handleSlotClick(GuiContainer gui, int slotIndex, int button, Slot slot, int modifier, boolean eventconsumed) {
      if(gui instanceof GuiInventory && !eventconsumed) {
         if(slotIndex >= 0 && button == 0 && slot != null && modifier != 0 && slot.getHasStack()) {
            if(slot.getHasStack() && slot.getStack().getItem() instanceof ItemArmor) {
               ItemArmor armor = (ItemArmor)slot.getStack().getItem();
               Slot destSlot = gui.inventorySlots.getSlot(36 + armor.armorType);
               if(!destSlot.getHasStack()) {
                  gui.handleMouseClick(slot, slotIndex, button, 0);
                  gui.handleMouseClick(destSlot, destSlot.slotNumber, button, 0);
                  return true;
               }
            }

            return eventconsumed;
         } else {
            return eventconsumed;
         }
      } else {
         return eventconsumed;
      }
   }

   public void afterSlotClick(GuiContainer gui, int slotIndex, int button, Slot slot, int modifier) {}
}
