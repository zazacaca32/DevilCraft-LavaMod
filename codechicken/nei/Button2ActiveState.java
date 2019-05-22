package codechicken.nei;

import codechicken.nei.Button;
import codechicken.nei.Image;

public abstract class Button2ActiveState extends Button {

   public Image icon2;


   public Image getRenderIcon() {
      return (super.state & 8) != 0?this.icon2:super.icon;
   }
}
