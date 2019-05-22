package codechicken.nei;

import codechicken.nei.Image;
import codechicken.nei.LayoutManager;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.Widget;
import codechicken.nei.forge.GuiContainerManager;
import java.util.List;
import org.lwjgl.opengl.GL11;

public abstract class Button extends Widget {

   public String label;
   public Image icon;
   public boolean iconHighlight;
   public int state;
   public static final Image stateOff = new Image(48, 0, 8, 12);
   public static final Image stateOn = new Image(56, 0, 8, 12);
   public static final Image stateDisabled = new Image(64, 0, 8, 12);


   public Button(String s) {
      this.label = s;
   }

   public Button() {
      this.label = "";
   }

   public int contentWidth(GuiContainerManager gui) {
      int textw = this.getRenderIcon() == null?gui.getStringWidth(this.label):this.getRenderIcon().width;
      if(NEIClientConfig.getLayoutStyle() == 0) {
         textw += 4;
      } else if((this.state & 4) != 0) {
         textw += stateOff.width;
      }

      return textw;
   }

   public void setOwnWidth(GuiContainerManager gui) {
      super.width = this.contentWidth(gui) + this.getMargin();
   }

   public int getMargin() {
      return 2;
   }

   public void draw(GuiContainerManager gui, int mousex, int mousey) {
      boolean mouseover = this.contains(mousex, mousey);
      Image renderIcon = this.getRenderIcon();
      int iconx;
      int icony;
      if(NEIClientConfig.getLayoutStyle() == 1) {
         int tex = this.contentWidth(gui);
         iconx = super.x + (super.width - tex) / 2;
         icony = super.y + (super.height - 8) / 2;
         gui.drawRect(super.x, super.y, super.width, super.height, this.contains(mousex, mousey)?-297791480:-301989888);
         if(renderIcon == null) {
            gui.drawText(iconx, icony, this.label, -1);
         } else {
            int icony1 = super.y + (super.height - renderIcon.height) / 2;
            LayoutManager.drawIcon(gui.window, iconx, icony1, renderIcon);
            if((this.state & 3) == 2) {
               gui.drawRect(iconx, icony1, renderIcon.width, renderIcon.height, Integer.MIN_VALUE);
            }

            if((this.state & 4) != 0) {
               Image stateimage;
               if((this.state & 3) == 1) {
                  stateimage = stateOn;
               } else if((this.state & 3) == 2) {
                  stateimage = stateDisabled;
               } else {
                  stateimage = stateOff;
               }

               LayoutManager.drawIcon(gui.window, iconx + renderIcon.width, icony1, stateimage);
            }
         }
      } else {
         GL11.glDisable(2896);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         byte tex1;
         if((this.state & 3) == 1) {
            tex1 = 2;
         } else if((this.state & 3) == 2) {
            tex1 = 0;
         } else {
            tex1 = 1;
         }

         LayoutManager.drawButtonBackground(gui, super.x, super.y, super.width, super.height, true, tex1);
         if(renderIcon == null) {
            if((!mouseover || (this.state & 3) == 2) && (this.state & 3) != 1) {
               if((this.state & 3) == 2) {
                  gui.drawTextCentered(this.label, super.x + super.width / 2, super.y + (super.height - 8) / 2, 6295568);
               } else {
                  gui.drawTextCentered(this.label, super.x + super.width / 2, super.y + (super.height - 8) / 2, 14737632);
               }
            } else {
               gui.drawTextCentered(this.label, super.x + super.width / 2, super.y + (super.height - 8) / 2, 16777120);
            }
         } else {
            if(!this.iconHighlight) {
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            iconx = super.x + (super.width - this.icon.width) / 2;
            icony = super.y + (super.height - this.icon.height) / 2;
            LayoutManager.drawIcon(gui.window, iconx, icony, renderIcon);
         }
      }

   }

   public boolean handleClick(int i, int j, int k) {
      if((k == 1 || k == 0) && this.onButtonPress(k == 1)) {
         NEIClientUtils.mc().sndManager.playSoundFX("random.click", 1.0F, 1.0F);
      }

      return true;
   }

   public abstract boolean onButtonPress(boolean var1);

   public Image getRenderIcon() {
      return this.icon;
   }

   public List handleTooltip(int mx, int my, List tooltip) {
      if(!this.contains(mx, my)) {
         return tooltip;
      } else {
         String tip = this.getButtonTip();
         if(tip != null) {
            tooltip.add(tip);
         }

         return tooltip;
      }
   }

   public String getButtonTip() {
      return null;
   }
}
