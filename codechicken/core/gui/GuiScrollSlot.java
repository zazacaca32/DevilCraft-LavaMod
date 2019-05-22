package codechicken.core.gui;

import codechicken.core.alg.MathHelper;
import codechicken.core.gui.GuiWidget;
import net.minecraft.client.gui.GuiButton;

public abstract class GuiScrollSlot extends GuiWidget {

   protected GuiButton scrollupbutton;
   protected GuiButton scrolldownbutton;
   protected String actionCommand;
   protected int scrollclicky = -1;
   protected float scrollpercent;
   protected int scrollmousey;
   protected float percentscrolled;
   protected int lastslotclicked = -1;
   protected int lastbuttonclicked = -1;
   protected long lastslotclicktime;
   public boolean focused;
   public int contentx;
   public int contenty;
   public int contentheight;
   public boolean smoothScroll = true;


   public GuiScrollSlot(int x, int y, int width, int height) {
      super(x, y, width, height);
      this.setContentSize(x + 3, y + 2, height - 2);
   }

   public GuiScrollSlot setActionCommand(String s) {
      this.actionCommand = s;
      return this;
   }

   public void setSmoothScroll(boolean b) {
      this.smoothScroll = b;
   }

   public void setSize(int x, int y, int width, int height) {
      int cx = this.contentx - super.x;
      int cy = this.contenty - super.y;
      int ch = height - this.contentheight;
      super.setSize(x, y, width, height);
      this.setContentSize(x + cx, y + cy, height - ch);
   }

   public void setContentSize(int x, int y, int h) {
      this.contentx = x;
      this.contenty = y;
      this.contentheight = h;
   }

   public void registerButtons(GuiButton up, GuiButton down, String selectionCommand) {
      this.scrollupbutton = up;
      this.scrolldownbutton = down;
      this.actionCommand = selectionCommand;
   }

   public abstract int getSlotHeight();

   protected abstract int getNumSlots();

   public abstract void selectNext();

   public abstract void selectPrev();

   protected abstract void slotClicked(int var1, int var2, int var3, int var4, boolean var5);

   protected abstract boolean isSlotSelected(int var1);

   protected abstract void drawSlot(int var1, int var2, int var3, int var4, int var5, boolean var6, float var7);

   protected void unfocus() {}

   public void setFocused(boolean focus) {
      this.focused = focus;
      if(!this.focused) {
         this.unfocus();
      }

   }

   public void scrollUp() {
      this.scroll(-5);
   }

   public void scrollDown() {
      this.scroll(5);
   }

   public void scroll(int i) {
      this.percentscrolled += (float)i / (float)this.contentheight * 100.0F;
      this.calculatePercentScrolled();
   }

   public int totalContentHeight() {
      return this.getNumSlots() * this.getSlotHeight();
   }

   public int getSlotY(int slot) {
      int scrolledPixels = (int)((double)((float)(this.totalContentHeight() - this.contentheight) * this.percentscrolled) + 0.5D);
      if(!this.smoothScroll) {
         scrolledPixels = (int)((double)scrolledPixels / (double)this.getSlotHeight() + 0.5D) * this.getSlotHeight();
      }

      return this.contenty - scrolledPixels + slot * this.getSlotHeight();
   }

   public int getClickedSlot(int mousey) {
      if(mousey >= this.contenty && mousey < this.contenty + this.contentheight) {
         for(int slot = 0; slot < this.getNumSlots(); ++slot) {
            int sloty = this.getSlotY(slot);
            if(mousey >= sloty && mousey < sloty + this.getSlotHeight()) {
               return slot;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public int getScrollBarWidth() {
      return 5;
   }

   public int getScrollBarHeight() {
      int sbarh = (int)((float)this.contentheight / (float)this.totalContentHeight() * (float)super.height);
      return sbarh > super.height?super.height:(sbarh < super.height / 15?super.height / 15:sbarh);
   }

   public void calculatePercentScrolled() {
      int barempty = super.height - this.getScrollBarHeight();
      if(this.scrollclicky >= 0) {
         int scrolldiff = this.scrollmousey - this.scrollclicky;
         this.percentscrolled = (float)scrolldiff / (float)barempty + this.scrollpercent;
      }

      this.percentscrolled = (float)MathHelper.clip((double)this.percentscrolled, 0.0D, 1.0D);
   }

   public void showSlot(int slot) {
      int sloty = this.getSlotY(slot);
      int diff;
      if(sloty + this.getSlotHeight() > this.contenty + this.contentheight) {
         diff = sloty - (this.contenty + this.contentheight - this.getSlotHeight());
         this.percentscrolled = (float)((double)this.percentscrolled + (double)diff / (double)(this.totalContentHeight() - this.contentheight));
         this.calculatePercentScrolled();
      } else if(sloty < this.contenty) {
         diff = this.contenty - sloty;
         this.percentscrolled = (float)((double)this.percentscrolled - (double)diff / (double)(this.totalContentHeight() - this.contentheight));
         this.calculatePercentScrolled();
      }

   }

   public void processMouse(int mousex, int mousey) {
      if(this.scrollclicky >= 0) {
         int scrolldiff = mousey - this.scrollclicky;
         int barupallowed = (int)((double)((float)(super.height - this.getScrollBarHeight()) * this.scrollpercent) + 0.5D);
         int bardownallowed = super.height - this.getScrollBarHeight() - barupallowed;
         if(-scrolldiff > barupallowed) {
            this.scrollmousey = this.scrollclicky - barupallowed;
         } else if(scrolldiff > bardownallowed) {
            this.scrollmousey = this.scrollclicky + bardownallowed;
         } else {
            this.scrollmousey = mousey;
         }

         this.calculatePercentScrolled();
      }

   }

   public void actionPerformed(GuiButton guibutton) {
      if(guibutton.enabled) {
         if(this.scrollupbutton != null && guibutton.id == this.scrollupbutton.id) {
            this.scrollUp();
         } else if(this.scrolldownbutton != null && guibutton.id == this.scrolldownbutton.id) {
            this.scrollDown();
         }

      }
   }

   public void mouseClicked(int mousex, int mousey, int button) {
      boolean needsfocus = mousex >= super.x && mousex < super.x + super.width && mousey >= super.y && mousey <= super.y + super.height;
      if(needsfocus != this.focused) {
         this.setFocused(needsfocus);
      }

      int barempty = super.height - this.getScrollBarHeight();
      int sbarx = super.x + super.width - this.getScrollBarWidth();
      int sbary = super.y + (int)((double)((float)barempty * this.percentscrolled) + 0.5D);
      if(button == 0 && this.getScrollBarHeight() < super.height && mousex >= sbarx && mousex <= super.x + super.width && mousey >= super.y && mousey <= super.y + super.height) {
         if(mousey < sbary) {
            this.percentscrolled = (float)(mousey - super.y) / (float)barempty;
            this.calculatePercentScrolled();
         } else if(mousey > sbary + this.getScrollBarHeight()) {
            this.percentscrolled = (float)(mousey - super.y - this.getScrollBarHeight() + 1) / (float)barempty;
            this.calculatePercentScrolled();
         } else {
            this.scrollclicky = mousey;
            this.scrollpercent = this.percentscrolled;
            this.scrollmousey = mousey;
         }
      } else if(mousex >= this.contentx && mousex < sbarx && mousey >= this.contenty && mousey <= this.contenty + this.contentheight) {
         int slot = this.getClickedSlot(mousey);
         if(slot >= 0) {
            this.slotClicked(slot, button, mousex - this.contentx, mousey - this.getSlotY(slot), slot == this.lastslotclicked && button == this.lastbuttonclicked && System.currentTimeMillis() - this.lastslotclicktime < 500L);
         }

         this.lastslotclicked = slot;
         this.lastbuttonclicked = button;
         this.lastslotclicktime = System.currentTimeMillis();
      }

   }

   public void mouseMovedOrUp(int mousex, int mousey, int button) {
      if(this.scrollclicky >= 0 && button == 0) {
         this.scrollclicky = -1;
      }

   }

   public void keyTyped(char c, int keycode) {
      if(this.focused) {
         if(keycode == 200) {
            this.selectPrev();
         }

         if(keycode == 208) {
            this.selectNext();
         }

         if(keycode == 28 && this.actionCommand != null) {
            this.sendAction(this.actionCommand, new Object[0]);
         }

      }
   }

   public void drawSlotBox(float frame) {
      drawRect(super.x, super.y, super.x + super.width, super.y + super.height, -16777216);
   }

   public void drawBackground(float frame) {}

   public void drawOverlay(float frame) {
      drawRect(super.x, super.y - 1, super.x + super.width, super.y, -6250336);
      drawRect(super.x, super.y + super.height, super.x + super.width, super.y + super.height + 1, -6250336);
      drawRect(super.x - 1, super.y - 1, super.x, super.y + super.height + 1, -6250336);
      drawRect(super.x + super.width, super.y - 1, super.x + super.width + 1, super.y + super.height + 1, -6250336);
   }

   public void drawScrollBar(float frame) {
      int sbarw = this.getScrollBarWidth();
      int sbarh = this.getScrollBarHeight();
      int sbarx = super.x + super.width - sbarw;
      int sbary = super.y + (int)((double)((float)(super.height - sbarh) * this.percentscrolled) + 0.4999D);
      drawRect(sbarx, sbary, sbarx + sbarw, sbary + sbarh, -7631989);
      drawRect(sbarx, sbary, sbarx + sbarw - 1, sbary + sbarh - 1, -986896);
      drawRect(sbarx + 1, sbary + 1, sbarx + sbarw, sbary + sbarh, -11184811);
      drawRect(sbarx + 1, sbary + 1, sbarx + sbarw - 1, sbary + sbarh - 1, -3750202);
      if(this.drawLineGuide()) {
         drawRect(sbarx - 1, super.y, sbarx, super.y + super.height, -8355712);
      }

   }

   public boolean drawLineGuide() {
      return true;
   }

   public void drawSlots(int mousex, int mousey, float frame) {
      for(int slot = 0; slot < this.getNumSlots(); ++slot) {
         int sloty = this.getSlotY(slot);
         if(sloty > this.contenty - this.getSlotHeight() && sloty < this.contenty + this.contentheight) {
            this.drawSlot(slot, this.contentx, sloty, mousex - this.contentx, mousey - sloty, this.isSlotSelected(slot), frame);
         }
      }

   }

   public void mouseDragged(int x, int y, int button, long time) {
      this.processMouse(x, y);
   }

   public void draw(int mousex, int mousey, float frame) {
      this.drawBackground(frame);
      this.drawSlotBox(frame);
      this.drawSlots(mousex, mousey, frame);
      this.drawOverlay(frame);
      this.drawScrollBar(frame);
   }
}
