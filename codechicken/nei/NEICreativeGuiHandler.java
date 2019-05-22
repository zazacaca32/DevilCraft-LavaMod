package codechicken.nei;

import codechicken.nei.VisiblityData;
import codechicken.nei.api.INEIGuiAdapter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.creativetab.CreativeTabs;

public class NEICreativeGuiHandler extends INEIGuiAdapter {

   public VisiblityData modifyVisiblity(GuiContainer gui, VisiblityData currentVisibility) {
      if(!(gui instanceof GuiContainerCreative)) {
         return currentVisibility;
      } else {
         if(((GuiContainerCreative)gui).func_74230_h() != CreativeTabs.tabInventory.getTabIndex()) {
            currentVisibility.showItemSection = currentVisibility.enableDeleteMode = false;
         }

         return currentVisibility;
      }
   }
}
