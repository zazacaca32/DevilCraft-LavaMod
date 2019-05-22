package codechicken.nei.forge;

import codechicken.nei.forge.IContainerSlotClickHandler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;

public class DefaultSlotClickHandler implements IContainerSlotClickHandler {

   public void beforeSlotClick(GuiContainer gui, int slotIndex, int button, Slot slot, int modifier) {}

   public boolean handleSlotClick(GuiContainer gui, int slotIndex, int button, Slot slot, int modifier, boolean eventconsumed) {
      if(!eventconsumed) {
         gui.handleMouseClick(slot, slotIndex, button, slotIndex != -999?modifier:0);
      }

      return true;
   }

   public void afterSlotClick(GuiContainer gui, int slotIndex, int button, Slot slot, int modifier) {}
}
