package codechicken.core.gui;

import codechicken.core.gui.GuiWidget;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class GuiCCButton extends GuiWidget {

   public String text;
   public String actionCommand;
   private boolean isEnabled = true;
   public boolean drawButton = true;


   public GuiCCButton(int x, int y, int width, int height, String text) {
      super(x, y, width, height);
      this.text = text;
   }

   public void setText(String s) {
      this.text = s;
   }

   public boolean isEnabled() {
      return this.isEnabled;
   }

   public void setEnabled(boolean b) {
      this.isEnabled = b;
   }

   public void mouseClicked(int x, int y, int button) {
      if(this.isEnabled && this.pointInside(x, y) && this.actionCommand != null) {
         this.sendAction(this.actionCommand, new Object[]{Integer.valueOf(button)});
         Minecraft.getMinecraft().sndManager.playSoundFX("random.click", 1.0F, 1.0F);
      }

   }

   public void draw(int mousex, int mousey, float frame) {
      if(this.drawButton) {
         super.renderEngine.bindTexture("/gui/gui.png");
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         boolean mouseover = this.pointInside(mousex, mousey);
         int state = !this.isEnabled?0:(mouseover?2:1);
         this.drawTexturedModalRect(super.x, super.y, 0, 46 + state * 20, super.width / 2, super.height / 2);
         this.drawTexturedModalRect(super.x + super.width / 2, super.y, 200 - super.width / 2, 46 + state * 20, super.width / 2, super.height / 2);
         this.drawTexturedModalRect(super.x, super.y + super.height / 2, 0, 46 + state * 20 + 20 - super.height / 2, super.width / 2, super.height / 2);
         this.drawTexturedModalRect(super.x + super.width / 2, super.y + super.height / 2, 200 - super.width / 2, 46 + state * 20 + 20 - super.height / 2, super.width / 2, super.height / 2);
         this.drawCenteredString(super.fontRenderer, this.text, super.x + super.width / 2, super.y + (super.height - 8) / 2, this.getTextColour(mousex, mousey));
      }
   }

   public int getTextColour(int mousex, int mousey) {
      return !this.isEnabled?-6250336:(this.pointInside(mousex, mousey)?-96:-2039584);
   }

   public GuiCCButton setActionCommand(String string) {
      this.actionCommand = string;
      return this;
   }
}
