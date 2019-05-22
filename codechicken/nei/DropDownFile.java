package codechicken.nei;

import codechicken.core.config.ConfigFile;
import codechicken.nei.DropDownWidget;
import codechicken.nei.ItemVisibilityHash;
import codechicken.nei.LayoutManager;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.SubSetRangeTag;
import codechicken.nei.forge.GuiContainerManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.opengl.GL11;

public class DropDownFile extends ConfigFile {

   public ArrayList sortedtags = new ArrayList();
   public int slotwidth = 0;
   public boolean hasscroll;
   protected int scrollclicky = -1;
   protected float scrollpercent;
   protected int scrollmousey;
   protected float percentscrolled;
   protected int lastslotclicked = -1;
   protected long lastslotclicktime;
   int x;
   int y;
   int height;
   int width;
   int contentheight;
   public boolean hidden;
   public static final int slotheight = 18;
   public static DropDownFile dropDownInstance = new DropDownFile(new File(Minecraft.getMinecraftDir(), "config/NEISubset.cfg"));


   static {
      dropDownInstance.setComment("You can put your own custom SubSet Ranges in here\nFollow the following format (replace {something} with what you want.\n{Parent}.{Name}=[{item1}],[{item2}],[{item3}-{item4}],[{item5}:{damage}],[{item6}:{damage1}-{damage2}]\nEg. Blocks.Nether = [87-89],[112-115]\nEg2. Birch = [17:2],[6:2]");
   }

   public DropDownFile(File file) {
      super(file);
   }

   public SubSetRangeTag getTag(String tagname) {
      return (SubSetRangeTag)super.getTag(tagname);
   }

   public SubSetRangeTag getTag(String tagname, boolean createnew) {
      return (SubSetRangeTag)super.getTag(tagname, createnew);
   }

   public SubSetRangeTag getNewTag(String name) {
      return new SubSetRangeTag(this, name);
   }

   public void saveConfig() {
      super.saveConfig();
   }

   public boolean thisContains(int mousex, int mousey) {
      return mousex >= this.x && mousex < this.x + this.width && mousey >= this.y && mousey <= this.y + this.height;
   }

   public boolean contains(int mousex, int mousey) {
      if(this.thisContains(mousex, mousey)) {
         return true;
      } else {
         Iterator var4 = this.sortedtags.iterator();

         while(var4.hasNext()) {
            SubSetRangeTag tag = (SubSetRangeTag)var4.next();
            if(tag.contains(mousex, mousey)) {
               return true;
            }
         }

         return false;
      }
   }

   public int getScrollBarWidth() {
      return 5;
   }

   public int getScrollBarHeight() {
      int sbarh = (int)((float)this.height / (float)this.contentheight * (float)this.height);
      return sbarh > this.height?this.height:(sbarh < this.height / 15?this.height / 15:sbarh);
   }

   public int getScrolledSlots() {
      int slots = this.childTagMap().size();
      int shownslots = this.height / 18;
      return (int)(this.percentscrolled * (float)(slots - shownslots) + 0.5F);
   }

   private int getClickedSlot(int mousey) {
      return (mousey - this.y) / 18 + this.getScrolledSlots();
   }

   public void calculatePercentScrolled() {
      int barempty = this.height - this.getScrollBarHeight();
      int sbary;
      if(this.scrollclicky >= 0) {
         sbary = this.scrollmousey - this.scrollclicky;
         this.percentscrolled = (float)sbary / (float)barempty + this.scrollpercent;
      }

      if(this.percentscrolled < 0.0F) {
         this.percentscrolled = 0.0F;
      }

      if(this.percentscrolled > 1.0F) {
         this.percentscrolled = 1.0F;
      }

      sbary = this.y + (int)((double)((float)barempty * this.percentscrolled) + 0.5D);
      this.percentscrolled = (float)(sbary - this.y) / (float)barempty;
   }

   public void processScrollMouse(int mousex, int mousey) {
      if(this.scrollclicky >= 0) {
         int scrolldiff = mousey - this.scrollclicky;
         int barupallowed = (int)((double)((float)(this.height - this.getScrollBarHeight()) * this.scrollpercent) + 0.5D);
         int bardownallowed = this.height - this.getScrollBarHeight() - barupallowed;
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

   public String updateMouseOver(int mousex, int mousey, String mouseovername) {
      this.processScrollMouse(mousex, mousey);
      String currentmouseover = "";
      int sloty = this.y;
      int slotx = this.x + (this.hasscroll?this.getScrollBarWidth():0);
      int slot = 0;
      Iterator var9 = this.sortedtags.iterator();

      while(var9.hasNext()) {
         SubSetRangeTag tag = (SubSetRangeTag)var9.next();
         ++slot;
         if(slot > this.getScrolledSlots()) {
            if(slotx <= mousex && this.x + this.width > mousex && sloty <= mousey && sloty + 18 > mousey) {
               currentmouseover = tag.qualifiedname;
            }

            String s = tag.updateMouseOver(mousex, mousey, mouseovername);
            if(!s.equals("")) {
               currentmouseover = s;
            }

            sloty += 18;
         }
      }

      return currentmouseover;
   }

   public boolean click(int mousex, int mousey, int button) {
      boolean inbounds = this.thisContains(mousex, mousey);
      if(!inbounds) {
         Iterator sbary = this.sortedtags.iterator();

         while(sbary.hasNext()) {
            SubSetRangeTag barempty = (SubSetRangeTag)sbary.next();
            barempty.click(mousex, mousey, button);
         }
      }

      int barempty1 = this.height - this.getScrollBarHeight();
      int sbary1 = this.y + (int)((double)((float)barempty1 * this.percentscrolled) + 0.5D);
      int contentx = this.x + this.getScrollBarWidth();
      if(this.hasscroll && button == 0 && this.getScrollBarHeight() < this.height && mousex >= this.x && mousex <= this.x + this.getScrollBarWidth() && mousey >= this.y && mousey <= this.y + this.height) {
         if(mousey < sbary1) {
            this.percentscrolled = (float)(mousey - this.y) / (float)barempty1;
            this.calculatePercentScrolled();
         } else if(mousey > sbary1 + this.getScrollBarHeight()) {
            this.percentscrolled = (float)(mousey - this.y - this.getScrollBarHeight() + 1) / (float)barempty1;
            this.calculatePercentScrolled();
         } else {
            this.scrollclicky = mousey;
            this.scrollpercent = this.percentscrolled;
            this.scrollmousey = mousey;
         }
      } else if(mousex >= contentx && mousex < this.x + this.width && mousey >= this.y && mousey <= this.y + this.height) {
         int slot = this.getClickedSlot(mousey);
         if(slot == this.lastslotclicked && System.currentTimeMillis() - this.lastslotclicktime < 500L && button == 0) {
            this.slotClicked(slot, button, true);
         } else {
            this.slotClicked(slot, button, false);
         }

         if(button == 0) {
            this.lastslotclicked = slot;
            this.lastslotclicktime = System.currentTimeMillis();
         }
      }

      return true;
   }

   public void onMouseWheel(int i) {
      if(this.scrollclicky == -1) {
         Iterator var3 = this.sortedtags.iterator();

         while(var3.hasNext()) {
            SubSetRangeTag tag = (SubSetRangeTag)var3.next();
            if(tag.expanded) {
               tag.onMouseWheel(i);
               return;
            }
         }

         this.scrollpercent += (float)i / (float)this.contentheight * 10.0F;
         if(this.scrollpercent > 1.0F) {
            this.scrollpercent = 1.0F;
         } else if(this.scrollpercent < 0.0F) {
            this.scrollpercent = 0.0F;
         }

      }
   }

   private void slotClicked(int slot, int button, boolean doubleclick) {
      int i = 0;

      for(Iterator var6 = this.sortedtags.iterator(); var6.hasNext(); ++i) {
         SubSetRangeTag tag = (SubSetRangeTag)var6.next();
         if(slot == i) {
            tag.onClick(button, doubleclick);
            return;
         }
      }

   }

   public void hideAllItems() {
      Iterator var2 = this.sortedtags.iterator();

      while(var2.hasNext()) {
         SubSetRangeTag tag = (SubSetRangeTag)var2.next();
         tag.hideAllItems();
      }

   }

   public void showAllItems() {
      Iterator var2 = this.sortedtags.iterator();

      while(var2.hasNext()) {
         SubSetRangeTag tag = (SubSetRangeTag)var2.next();
         tag.showAllItems();
      }

   }

   public void mouseUp(int mousex, int mousey, int button) {
      if(this.scrollclicky >= 0 && button == 0) {
         this.scrollclicky = -1;
      }

      Iterator var5 = this.sortedtags.iterator();

      while(var5.hasNext()) {
         SubSetRangeTag tag = (SubSetRangeTag)var5.next();
         tag.mouseUp(mousex, mousey, button);
      }

   }

   public void draw(GuiContainerManager gui, int mousex, int mousey) {
      this.drawScrollBar(gui);
      int drawy = this.y;
      int drawx = this.x + (this.hasscroll?this.getScrollBarWidth():0);
      int slot = 0;
      Iterator var8 = this.sortedtags.iterator();

      while(var8.hasNext()) {
         SubSetRangeTag tag = (SubSetRangeTag)var8.next();
         ++slot;
         if(slot > this.getScrolledSlots()) {
            if(!this.hidden) {
               if(!DropDownWidget.texturedButtons) {
                  boolean tex = mousex >= drawx && mousex < drawx + this.slotwidth && mousey >= drawy && mousey < drawy + 18;
                  gui.drawRect(drawx, drawy, this.slotwidth, 18, tex?-12578808:-16777216);
                  gui.drawTextCentered(drawx, drawy, this.slotwidth, 18, tag.name, tag.getColourFromState(), tag.state == 0);
               } else {
                  gui.bindTexture("/gui/gui.png");
                  if(tag.state == 1) {
                     GL11.glColor4f(0.65F, 0.65F, 0.65F, 1.0F);
                  } else {
                     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                  }

                  int var11 = tag.state == 0?0:1;
                  LayoutManager.drawButtonBackground(gui, drawx, drawy, this.slotwidth, 18, false, var11);
                  int textcolour = tag.state == 2?-2039584:-6250336;
                  gui.drawTextCentered(drawx, drawy, this.slotwidth, 18, tag.name, textcolour);
               }
            }

            tag.draw(gui, mousex, mousey);
            drawy += 18;
            if(drawy >= this.y + this.height) {
               break;
            }
         }
      }

   }

   private void drawScrollBar(GuiContainerManager gui) {
      if(this.hasscroll && !this.hidden) {
         int sbary = this.y + (int)((double)((float)(this.height - this.getScrollBarHeight()) * this.percentscrolled) + 0.5D);
         gui.drawRect(this.x, this.y, 5, this.height, -14671840);
         if(DropDownWidget.texturedButtons) {
            gui.drawRect(this.x, sbary, 5, this.getScrollBarHeight(), -7631989);
            gui.drawRect(this.x, sbary, 4, this.getScrollBarHeight() - 1, -986896);
            gui.drawRect(this.x + 1, sbary + 1, 4, this.getScrollBarHeight() - 1, -11184811);
            gui.drawRect(this.x + 1, sbary + 1, 3, this.getScrollBarHeight() - 2, -3750202);
         } else {
            gui.drawRect(this.x, sbary, 5, this.getScrollBarHeight(), -2039584);
         }
      }

   }

   public void position(int px, int py) {
      this.x = px;
      this.y = py;
      this.recalcSize();
      int suby = this.y;
      int subx = this.x + this.width;
      int slot = 0;
      Iterator var7 = this.sortedtags.iterator();

      while(var7.hasNext()) {
         SubSetRangeTag tag = (SubSetRangeTag)var7.next();
         ++slot;
         if(slot > this.getScrolledSlots()) {
            tag.position(subx, suby);
            suby += 18;
            if(suby >= this.y + this.height) {
               break;
            }
         }
      }

   }

   public void recalcSize() {
      int maxheight = NEIClientUtils.getGuiContainer().height - this.y;
      this.contentheight = this.childTagMap().size() * 18;
      if(this.contentheight > maxheight) {
         this.height = maxheight / 18 * 18;
         this.hasscroll = true;
      } else {
         this.hasscroll = false;
         this.height = this.contentheight;
      }

      this.slotwidth = 0;
      FontRenderer fontRenderer = NEIClientUtils.mc().fontRenderer;
      Iterator var4 = this.sortedtags.iterator();

      while(var4.hasNext()) {
         SubSetRangeTag totalwidth = (SubSetRangeTag)var4.next();
         int tagwidth = fontRenderer.getStringWidth(totalwidth.name);
         if(tagwidth > this.slotwidth) {
            this.slotwidth = tagwidth;
         }
      }

      this.slotwidth += 2;
      this.width = this.slotwidth;
      if(this.hasscroll) {
         this.width += 5;
      }

      int totalwidth1 = this.x + this.width - LayoutManager.dropDown.x;
      this.hidden = totalwidth1 <= 0;
   }

   public void resetHashes() {
      this.sortedtags = this.getSortedTagList();
      Iterator var2 = this.sortedtags.iterator();

      while(var2.hasNext()) {
         SubSetRangeTag tag = (SubSetRangeTag)var2.next();
         tag.resetHashes();
      }

   }

   public void updateState() {
      ItemVisibilityHash vis = NEIClientConfig.vishash;
      Iterator var3 = this.sortedtags.iterator();

      while(var3.hasNext()) {
         SubSetRangeTag tag = (SubSetRangeTag)var3.next();
         tag.updateState(vis);
      }

   }

   public void addItemIfInRange(int item, int damage, NBTTagCompound compound) {
      Iterator var5 = this.sortedtags.iterator();

      while(var5.hasNext()) {
         SubSetRangeTag tag = (SubSetRangeTag)var5.next();
         tag.addItemIfInRange(item, damage, compound);
      }

   }

   public int getWidthAtLevel(int hiddenlevel) {
      if(hiddenlevel == 0) {
         return this.width;
      } else {
         Iterator var3 = this.sortedtags.iterator();

         while(var3.hasNext()) {
            SubSetRangeTag tag = (SubSetRangeTag)var3.next();
            int subwidth = tag.getWidthAtLevel(hiddenlevel - 1);
            if(subwidth != 0) {
               return subwidth;
            }
         }

         return 0;
      }
   }

   public Iterable allTags() {
      LinkedList tags = new LinkedList();
      Iterator var3 = this.sortedtags.iterator();

      while(var3.hasNext()) {
         SubSetRangeTag tag = (SubSetRangeTag)var3.next();
         tag.addChildTags(tags);
      }

      return tags;
   }
}
