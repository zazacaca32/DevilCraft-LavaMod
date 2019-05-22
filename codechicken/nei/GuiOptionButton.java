package codechicken.nei;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSmallButton;

public class GuiOptionButton extends GuiSmallButton {

   public GuiOptionButton(int i, int j, int k, String s) {
      super(i, j, k, s);
   }

   public boolean mousePressed(Minecraft minecraft, int i, int j) {
      return super.drawButton && i >= super.xPosition && j >= super.yPosition && i < super.xPosition + super.width && j < super.yPosition + super.height;
   }
}
