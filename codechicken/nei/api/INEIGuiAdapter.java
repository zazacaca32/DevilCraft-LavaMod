package codechicken.nei.api;

import codechicken.nei.VisiblityData;
import codechicken.nei.api.INEIGuiHandler;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

public class INEIGuiAdapter implements INEIGuiHandler {

   public VisiblityData modifyVisiblity(GuiContainer gui, VisiblityData currentVisibility) {
      return currentVisibility;
   }

   public int getItemSpawnSlot(GuiContainer gui, ItemStack item) {
      return -1;
   }

   public List getInventoryAreas(GuiContainer gui) {
      return null;
   }

   public boolean handleDragNDrop(GuiContainer gui, int mousex, int mousey, ItemStack draggedStack, int button) {
      return false;
   }
}
