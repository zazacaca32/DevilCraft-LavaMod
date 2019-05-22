package codechicken.nei;

import codechicken.nei.ItemPanel;
import codechicken.nei.forge.GuiContainerManager;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class UnusedBlock implements ItemPanel.ItemPanelObject {

   int blockID;


   public UnusedBlock(int id) {
      this.blockID = id;
   }

   public void draw(GuiContainerManager gui, int x, int y) {
      gui.drawItem(x, y, new ItemStack(Block.vine));
      gui.drawTextCentered(x, y, 16, 16, "" + this.blockID, 16777215);
   }

   public List handleTooltip(List tooltip) {
      tooltip.add("Unused BlockID: " + this.blockID);
      return tooltip;
   }
}
