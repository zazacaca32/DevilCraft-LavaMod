package codechicken.nei;

import codechicken.nei.Widget;
import codechicken.nei.forge.GuiContainerManager;

public class Label extends Widget {

   boolean centered;
   int colour;
   String text;


   public Label(String s, boolean center, int color) {
      this.text = s;
      this.centered = center;
      this.colour = color;
   }

   public Label(String s, boolean center) {
      this(s, center, -1);
   }

   public void draw(GuiContainerManager gui, int mousex, int mousey) {
      if(this.centered) {
         gui.drawTextCentered(this.text, super.x, super.y, this.colour);
      } else {
         gui.drawText(super.x, super.y, this.text, this.colour);
      }

   }
}
