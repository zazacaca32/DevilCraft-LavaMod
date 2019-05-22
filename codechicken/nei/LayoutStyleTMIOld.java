package codechicken.nei;

import codechicken.nei.Button;
import codechicken.nei.Image;
import codechicken.nei.LayoutManager;
import codechicken.nei.LayoutStyleDefault;
import codechicken.nei.forge.GuiContainerManager;

public class LayoutStyleTMIOld extends LayoutStyleDefault {

   int stateButtonCount;
   int clickButtonCount;


   public String getName() {
      return "Стиль \"TMI\"";
   }

   public void init() {
      LayoutManager.delete.icon = new Image(24, 12, 12, 12);
      LayoutManager.creative.icon = new Image(12, 12, 12, 12);
      LayoutManager.creative.icon2 = new Image(36, 12, 12, 12);
      LayoutManager.rain.icon = new Image(0, 12, 12, 12);
      LayoutManager.magnet.icon = new Image(60, 24, 12, 12);
      LayoutManager.timeButtons[0].icon = new Image(12, 24, 12, 12);
      LayoutManager.timeButtons[1].icon = new Image(0, 24, 12, 12);
      LayoutManager.timeButtons[2].icon = new Image(24, 24, 12, 12);
      LayoutManager.timeButtons[3].icon = new Image(36, 24, 12, 12);
      LayoutManager.heal.icon = new Image(48, 24, 12, 12);
   }

   public void reset() {
      this.stateButtonCount = this.clickButtonCount = 0;
   }

   public void layoutButton(Button button, GuiContainerManager gui) {
      byte offsetx = 2;
      byte offsety = 2;
      if((button.state & 4) != 0) {
         button.x = offsetx + this.stateButtonCount * 22;
         button.y = offsety;
         ++this.stateButtonCount;
      } else {
         button.x = offsetx + this.clickButtonCount % 4 * 22;
         button.y = offsety + (1 + this.clickButtonCount / 4) * 17;
         ++this.clickButtonCount;
      }

      button.height = 14;
      button.setOwnWidth(gui);
   }

   public void drawBackground(GuiContainerManager gui) {
      if(this.clickButtonCount != 0 || this.stateButtonCount != 0) {
         int maxx = Math.max(this.stateButtonCount, this.clickButtonCount);
         if(maxx > 4) {
            maxx = 4;
         }

         int maxy = this.clickButtonCount == 0?1:this.clickButtonCount / 4 + 2;
         gui.drawRect(0, 0, 2 + 22 * maxx, 1 + maxy * 17, -16777216);
      }
   }
}
