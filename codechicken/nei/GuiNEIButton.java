package codechicken.nei;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;

public class GuiNEIButton extends GuiButton {

   public GuiNEIButton(int i, int j, int k, int l, int i1, String s) {
      super(i, j, k, l, i1, s);
   }

   public void drawButton(Minecraft minecraft, int i, int j) {
      if(super.drawButton) {
         FontRenderer fontrenderer = minecraft.fontRenderer;
         minecraft.renderEngine.bindTexture("/gui/gui.png");
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         boolean flag = i >= super.xPosition && j >= super.yPosition && i < super.xPosition + super.width && j < super.yPosition + super.height;
         int k = this.getHoverState(flag);
         this.drawTexturedModalRect(super.xPosition, super.yPosition, 0, 46 + k * 20, super.width / 2, super.height / 2);
         this.drawTexturedModalRect(super.xPosition + super.width / 2, super.yPosition, 200 - super.width / 2, 46 + k * 20, super.width / 2, super.height / 2);
         this.drawTexturedModalRect(super.xPosition, super.yPosition + super.height / 2, 0, 46 + k * 20 + 20 - super.height / 2, super.width / 2, super.height / 2);
         this.drawTexturedModalRect(super.xPosition + super.width / 2, super.yPosition + super.height / 2, 200 - super.width / 2, 46 + k * 20 + 20 - super.height / 2, super.width / 2, super.height / 2);
         this.mouseDragged(minecraft, i, j);
         if(!super.enabled) {
            this.drawCenteredString(fontrenderer, super.displayString, super.xPosition + super.width / 2, super.yPosition + (super.height - 8) / 2, -6250336);
         } else if(flag) {
            this.drawCenteredString(fontrenderer, super.displayString, super.xPosition + super.width / 2, super.yPosition + (super.height - 8) / 2, 16777120);
         } else {
            this.drawCenteredString(fontrenderer, super.displayString, super.xPosition + super.width / 2, super.yPosition + (super.height - 8) / 2, 14737632);
         }

      }
   }
}
