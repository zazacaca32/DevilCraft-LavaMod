package codechicken.nei.recipe;

import codechicken.nei.recipe.DefaultOverlayHandler;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;

public class BrewingOverlayHandler extends DefaultOverlayHandler {

   public Slot[][] mapIngredSlots(GuiContainer gui, List ingredients) {
      Slot[][] map = super.mapIngredSlots(gui, ingredients);
      Slot[] potSlots = new Slot[3];

      for(int i = 0; i < 3; ++i) {
         potSlots[i] = (Slot)gui.inventorySlots.inventorySlots.get(i);
      }

      map[1] = potSlots;
      return map;
   }
}
