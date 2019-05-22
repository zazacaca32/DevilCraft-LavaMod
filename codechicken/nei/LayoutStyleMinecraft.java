package codechicken.nei;

import codechicken.nei.Button;
import codechicken.nei.Image;
import codechicken.nei.LayoutManager;
import codechicken.nei.LayoutStyleDefault;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.forge.GuiContainerManager;

public class LayoutStyleMinecraft extends LayoutStyleDefault {

   int stateButtonCount;
   int clickButtonCount;


   public String getName() {
      return "Стиль \"Minecraft\"";
   }

   public void init() {
      LayoutManager.delete.icon = new Image(144, 12, 12, 12);
      LayoutManager.creative.icon = new Image(132, 12, 12, 12);
      LayoutManager.creative.icon2 = new Image(156, 12, 12, 12);
      LayoutManager.rain.icon = new Image(120, 12, 12, 12);
      LayoutManager.magnet.icon = new Image(180, 24, 12, 12);
      LayoutManager.timeButtons[0].icon = new Image(132, 24, 12, 12);
      LayoutManager.timeButtons[1].icon = new Image(120, 24, 12, 12);
      LayoutManager.timeButtons[2].icon = new Image(144, 24, 12, 12);
      LayoutManager.timeButtons[3].icon = new Image(156, 24, 12, 12);
      LayoutManager.heal.icon = new Image(168, 24, 12, 12);
   }

   public void reset() {
      this.stateButtonCount = this.clickButtonCount = 0;
   }

   public void layoutButton(Button button, GuiContainerManager gui) {
      boolean edgeAlign = NEIClientConfig.getBooleanSetting("options.edge-align buttons");
      int offsetx = edgeAlign?0:6;
      int offsety = edgeAlign?0:3;
      if((button.state & 4) != 0) {
         button.x = offsetx + this.stateButtonCount * 20;
         button.y = offsety;
         ++this.stateButtonCount;
      } else {
         button.x = offsetx + this.clickButtonCount % 4 * 20;
         button.y = offsety + (1 + this.clickButtonCount / 4) * 18;
         ++this.clickButtonCount;
      }

      button.height = 17;
      button.setOwnWidth(gui);
   }
}
