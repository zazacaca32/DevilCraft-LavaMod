package codechicken.nei;

import codechicken.core.config.ConfigTag;
import codechicken.core.inventory.ItemKey;
import codechicken.nei.DropDownFile;
import codechicken.nei.DropDownWidget;
import codechicken.nei.ItemList;
import codechicken.nei.ItemRange;
import codechicken.nei.ItemVisibilityHash;
import codechicken.nei.LayoutManager;
import codechicken.nei.MultiItemRange;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.forge.GuiContainerManager;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.opengl.GL11;

public class SubSetRangeTag extends ConfigTag {

   public ArrayList sortedtags = new ArrayList();
   public boolean saveTag = true;
   public MultiItemRange validranges;
   public byte state;
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
   public boolean expanded;
   public boolean hidden;
   private static final int slotheight = 18;


   public SubSetRangeTag(DropDownFile parent, String name) {
      super(parent, name);
   }

   public SubSetRangeTag(SubSetRangeTag parent, String name) {
      super(parent, name);
      this.saveTag = parent.saveTag;
   }

   public ConfigTag onLoaded() {
      this.saveTag = true;
      return this;
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

   public void setDefaultValue(MultiItemRange range) {
      if(this.value == null) {
         this.setRange(range);
      }

   }

   public void setValue(String value) {
      this.value = value;
      if(this.saveTag) {
         this.saveConfig();
      }

      if(this.validranges == null) {
         this.validranges = new MultiItemRange(value);
      }

   }

   public void setRange(MultiItemRange range) {
      this.validranges = range;
      this.setValue(this.validranges.toString());
   }

   public MultiItemRange getRange() {
      return this.validranges;
   }

   public boolean thisContains(int mousex, int mousey) {
      return mousex >= this.x && mousex < this.x + this.width && mousey >= this.y && mousey < this.y + this.height;
   }

   public boolean contains(int mousex, int mousey) {
      if(!this.expanded) {
         return false;
      } else if(this.thisContains(mousex, mousey)) {
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

   public int getNumSlots() {
      return this.childTagMap().size() + (this.validranges == null?0:this.validranges.getNumSlots());
   }

   public int getScrollBarWidth() {
      return 5;
   }

   public int getScrollBarHeight() {
      int sbarh = (int)((float)this.height / (float)this.contentheight * (float)this.height);
      return sbarh > this.height?this.height:(sbarh < this.height / 15?this.height / 15:sbarh);
   }

   public int getScrolledSlots() {
      int slots = this.getNumSlots();
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
      this.expanded = mouseovername.equals(this.qualifiedname) || mouseovername.length() > this.qualifiedname.length() && mouseovername.startsWith(this.qualifiedname) && mouseovername.charAt(this.qualifiedname.length()) == 46;
      if(!this.expanded) {
         this.scrollclicky = -1;
         return "";
      } else {
         this.processScrollMouse(mousex, mousey);
         String currentmouseover = "";
         if(this.contains(mousex, mousey)) {
            currentmouseover = this.qualifiedname + "." + "-";
         }

         int sloty = this.y;
         int slotx = this.x + (this.hasscroll?this.getScrollBarWidth():0);
         int slot = 0;
         Iterator range = this.sortedtags.iterator();

         while(range.hasNext()) {
            SubSetRangeTag tagslots = (SubSetRangeTag)range.next();
            ++slot;
            if(slot > this.getScrolledSlots()) {
               if(slotx <= mousex && this.x + this.width > mousex && sloty <= mousey && sloty + 18 > mousey) {
                  currentmouseover = tagslots.qualifiedname;
               }

               String s = tagslots.updateMouseOver(mousex, mousey, mouseovername);
               if(!s.equals("")) {
                  currentmouseover = s;
               }

               sloty += 18;
            }
         }

         int var12 = slot;
         if(this.validranges != null) {
            Iterator var14 = this.validranges.ranges.iterator();

            while(var14.hasNext()) {
               ItemRange var13 = (ItemRange)var14.next();
               if(slot + var13.encompasseditems.size() <= this.getScrolledSlots()) {
                  slot += var13.encompasseditems.size();
               } else {
                  for(int item = 0; item < var13.encompasseditems.size(); ++item) {
                     ++slot;
                     if(slot > this.getScrolledSlots()) {
                        if(slotx <= mousex && this.x + this.width > mousex && sloty <= mousey && sloty + 18 > mousey) {
                           currentmouseover = this.qualifiedname + "." + (slot - var12);
                           break;
                        }

                        sloty += 18;
                        if(sloty >= this.y + this.height) {
                           break;
                        }
                     }
                  }

                  if(sloty >= this.y + this.height) {
                     break;
                  }
               }
            }
         }

         return currentmouseover;
      }
   }

   public boolean click(int mousex, int mousey, int button) {
      if(!this.expanded) {
         return false;
      } else {
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
         if(this.hasscroll && button == 0 && this.getScrollBarHeight() < this.height && mousex >= this.x && mousex < this.x + this.getScrollBarWidth() && mousey >= this.y && mousey < this.y + this.height) {
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
               LayoutManager.dropDown.canChangeMouseOver = false;
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

         this.percentscrolled += (float)i / (float)this.contentheight * 100.0F;
         if(this.percentscrolled > 1.0F) {
            this.percentscrolled = 1.0F;
         } else if(this.percentscrolled < 0.0F) {
            this.percentscrolled = 0.0F;
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

      if(this.validranges != null) {
         this.validranges.slotClicked(slot - i, button, doubleclick);
      }

   }

   public void onClick(int button, boolean doubleclick) {
      if(button == 0 && !doubleclick) {
         if(NEIClientUtils.shiftKey()) {
            LayoutManager.searchField.setText("@" + this.qualifiedname);
            return;
         }

         this.showAllItems();
      } else if(button == 0 && doubleclick) {
         DropDownFile.dropDownInstance.hideAllItems();
         this.showAllItems();
      } else if(button == 1) {
         this.hideAllItems();
      }

      DropDownFile.dropDownInstance.updateState();
      ItemList.updateSearch();
      NEIClientConfig.vishash.save();
   }

   public void hideAllItems() {
      if(this.validranges != null) {
         this.validranges.hideAllItems();
      }

      Iterator var2 = this.sortedtags.iterator();

      while(var2.hasNext()) {
         SubSetRangeTag tag = (SubSetRangeTag)var2.next();
         tag.hideAllItems();
      }

   }

   public void showAllItems() {
      if(this.validranges != null) {
         this.validranges.showAllItems();
      }

      Iterator var2 = this.sortedtags.iterator();

      while(var2.hasNext()) {
         SubSetRangeTag tag = (SubSetRangeTag)var2.next();
         tag.showAllItems();
      }

   }

   public void mouseUp(int mousex, int mousey, int button) {
      if(this.scrollclicky >= 0 && button == 0) {
         this.scrollclicky = -1;
         LayoutManager.dropDown.canChangeMouseOver = true;
      }

      Iterator var5 = this.sortedtags.iterator();

      while(var5.hasNext()) {
         SubSetRangeTag tag = (SubSetRangeTag)var5.next();
         tag.mouseUp(mousex, mousey, button);
      }

   }

   public void draw(GuiContainerManager gui, int mousex, int mousey) {
      if(this.expanded) {
         this.drawScrollBar(gui);
         int drawy = this.y;
         int drawx = this.x + (this.hasscroll?this.getScrollBarWidth():0);
         int slot = 0;
         Iterator var8 = this.sortedtags.iterator();

         int var16;
         while(var8.hasNext()) {
            SubSetRangeTag range = (SubSetRangeTag)var8.next();
            ++slot;
            if(slot > this.getScrolledSlots()) {
               if(!this.hidden) {
                  if(!DropDownWidget.texturedButtons) {
                     boolean item = mousex >= drawx && mousex < drawx + this.slotwidth && mousey >= drawy && mousey < drawy + 18;
                     gui.drawRect(drawx, drawy, this.slotwidth, 18, item?-12578808:-16777216);
                     gui.drawTextCentered(drawx, drawy, this.slotwidth, 18, range.name, range.getColourFromState(), range.state == 0);
                  } else {
                     gui.bindTexture("/gui/gui.png");
                     if(range.state == 1) {
                        GL11.glColor4f(0.65F, 0.65F, 0.65F, 1.0F);
                     } else {
                        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                     }

                     var16 = range.state == 0?0:1;
                     LayoutManager.drawButtonBackground(gui, drawx, drawy, this.slotwidth, 18, false, var16);
                     int itemh = range.state == 2?-2039584:-6250336;
                     gui.drawTextCentered(drawx, drawy, this.slotwidth, 18, range.name, itemh);
                  }
               }

               range.draw(gui, mousex, mousey);
               drawy += 18;
               if(drawy >= this.y + this.height) {
                  break;
               }
            }
         }

         if(this.validranges != null && drawy < this.y + this.height) {
            var8 = this.validranges.ranges.iterator();

            while(var8.hasNext()) {
               ItemRange var15 = (ItemRange)var8.next();
               if(slot + var15.encompasseditems.size() <= this.getScrolledSlots()) {
                  slot += var15.encompasseditems.size();
               } else {
                  for(var16 = 0; var16 < var15.encompasseditems.size(); ++var16) {
                     ++slot;
                     if(slot > this.getScrolledSlots()) {
                        ItemKey var17 = (ItemKey)var15.encompasseditems.get(var16);
                        int itemx = drawx + this.slotwidth / 2 - 8;
                        int itemy = drawy + 1;
                        boolean itemvisible = !NEIClientConfig.vishash.isItemHidden(var17);
                        if(!DropDownWidget.texturedButtons) {
                           gui.drawRect(drawx, drawy, this.slotwidth, 18, itemvisible?-16764928:-13631488);
                        } else {
                           int stack = itemvisible?1:0;
                           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                           LayoutManager.drawButtonBackground(gui, drawx, drawy, this.slotwidth, 18, false, stack);
                        }

                        ItemStack var18 = var17.item;
                        gui.drawItem(itemx, itemy, var18);
                        if(itemx <= mousex && itemx + 16 > mousex && itemy + 1 <= mousey && itemy + 16 > mousey) {
                           LayoutManager.dropDown.setHoverItem(var17.item);
                        }

                        drawy += 18;
                        if(drawy >= this.y + this.height) {
                           break;
                        }
                     }
                  }

                  if(drawy >= this.y + this.height) {
                     break;
                  }
               }
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
      if(this.expanded) {
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
   }

   public void recalcSize() {
      int maxheight = LayoutManager.dropDown.y + LayoutManager.dropDown.maxheight + LayoutManager.dropDown.height - this.y;
      int extraheight = this.y - LayoutManager.dropDown.height;
      this.contentheight = this.getNumSlots() * 18;
      if(this.contentheight > maxheight) {
         if(this.contentheight <= maxheight + extraheight) {
            this.y -= this.contentheight - maxheight;
            maxheight = this.contentheight;
         } else {
            this.y -= extraheight;
            maxheight += extraheight;
         }
      }

      if(this.contentheight > maxheight) {
         this.height = maxheight / 18 * 18;
         this.hasscroll = true;
      } else {
         this.hasscroll = false;
         this.height = this.contentheight;
      }

      this.slotwidth = 0;
      FontRenderer fontRenderer = NEIClientUtils.mc().fontRenderer;
      Iterator var5 = this.sortedtags.iterator();

      while(var5.hasNext()) {
         SubSetRangeTag totalwidth = (SubSetRangeTag)var5.next();
         int tagwidth = fontRenderer.getStringWidth(totalwidth.name);
         if(tagwidth > this.slotwidth) {
            this.slotwidth = tagwidth;
         }
      }

      int totalwidth1;
      if(this.validranges != null) {
         totalwidth1 = this.validranges.getWidth();
         if(totalwidth1 > this.slotwidth) {
            this.slotwidth = totalwidth1;
         }
      }

      this.slotwidth += 2;
      this.width = this.slotwidth;
      if(this.hasscroll) {
         this.width += this.getScrollBarWidth();
      }

      totalwidth1 = this.x + this.width - LayoutManager.dropDown.x;
      if(this.expanded && totalwidth1 > LayoutManager.dropDown.droppedwidth) {
         LayoutManager.dropDown.droppedwidth = totalwidth1;
      }

      this.hidden = totalwidth1 <= 0;
   }

   public int getWidthAtLevel(int hiddenlevel) {
      if(!this.expanded) {
         return 0;
      } else if(hiddenlevel == 0) {
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

   public void resetHashes() {
      if(this.validranges != null) {
         this.validranges.resetHashes();
      }

      this.sortedtags = this.getSortedTagList();
      Iterator var2 = this.sortedtags.iterator();

      while(var2.hasNext()) {
         SubSetRangeTag tag = (SubSetRangeTag)var2.next();
         tag.resetHashes();
      }

   }

   public void updateState(ItemVisibilityHash vis) {
      boolean allshown = false;
      boolean allhidden = false;
      Iterator var5 = this.sortedtags.iterator();

      SubSetRangeTag rstate;
      while(var5.hasNext()) {
         rstate = (SubSetRangeTag)var5.next();
         rstate.updateState(vis);
      }

      var5 = this.sortedtags.iterator();

      while(var5.hasNext()) {
         rstate = (SubSetRangeTag)var5.next();
         if(rstate.state == 1) {
            this.state = 1;
            return;
         }

         if(rstate.state == 0) {
            if(allshown) {
               this.state = 1;
               return;
            }

            allhidden = true;
         } else {
            if(allhidden) {
               this.state = 1;
               return;
            }

            allshown = true;
         }
      }

      if(this.validranges != null) {
         this.validranges.updateState(vis);
         byte rstate1 = this.validranges.state;
         if(rstate1 == 1) {
            this.state = 1;
            return;
         }

         if(rstate1 == 0) {
            if(allshown) {
               this.state = 1;
               return;
            }

            allhidden = true;
         } else {
            if(allhidden) {
               this.state = 1;
               return;
            }

            allshown = true;
         }
      }

      if(allshown) {
         this.state = 2;
      } else {
         this.state = 0;
      }

   }

   public void addItemIfInRange(int item, int damage, NBTTagCompound compound) {
      if(this.validranges != null) {
         this.validranges.addItemIfInRange(item, damage, compound);
      }

      Iterator var5 = this.sortedtags.iterator();

      while(var5.hasNext()) {
         SubSetRangeTag tag = (SubSetRangeTag)var5.next();
         tag.addItemIfInRange(item, damage, compound);
      }

   }

   public boolean isItemInRange(int item, int damage) {
      if(this.validranges != null && this.validranges.isItemInRange(item, damage)) {
         return true;
      } else {
         Iterator var4 = this.sortedtags.iterator();

         while(var4.hasNext()) {
            SubSetRangeTag tag = (SubSetRangeTag)var4.next();
            if(tag.isItemInRange(item, damage)) {
               return true;
            }
         }

         return false;
      }
   }

   public int getColourFromState() {
      return this.state == 0?-10481648:(this.state == 1?-8359824:-1);
   }

   public void setSave(boolean save) {
      this.saveTag = save;
      this.saveConfig();
   }

   public void save(PrintWriter writer, int tabs, String bracequalifier, boolean first) {
      if(this.saveTag) {
         super.save(writer, tabs, bracequalifier, first);
      } else {
         this.saveTagTree(writer, tabs, bracequalifier);
      }

   }

   public SubSetRangeTag useBraces() {
      return (SubSetRangeTag)super.useBraces();
   }

   public SubSetRangeTag setComment(String string) {
      return (SubSetRangeTag)super.setComment(string);
   }

   public SubSetRangeTag setPosition(int pos) {
      this.position = pos;
      if(this.saveTag) {
         this.saveConfig();
      }

      return this;
   }

   public String toString() {
      return this.qualifiedname;
   }

   public void addChildTags(List tags) {
      tags.add(this);
      Iterator var3 = this.sortedtags.iterator();

      while(var3.hasNext()) {
         SubSetRangeTag tag = (SubSetRangeTag)var3.next();
         tag.addChildTags(tags);
      }

   }
}
