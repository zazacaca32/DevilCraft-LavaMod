package codechicken.nei;

import codechicken.nei.ItemPanel;
import codechicken.nei.forge.GuiContainerManager;
import java.util.List;
import net.minecraft.item.ItemStack;

public class ItemPanelStack implements ItemPanel.ItemPanelObject {

   ItemStack item;


   public ItemPanelStack(ItemStack itemstack) {
      this.item = itemstack;
   }

   public void draw(GuiContainerManager gui, int x, int y) {
      gui.drawItem(x, y, this.item);
   }

   public List handleTooltip(List tooltip) {
      return tooltip;
   }
}
