package codechicken.nei;

import codechicken.nei.Button;
import codechicken.nei.DropDownFile;
import codechicken.nei.ItemList;
import codechicken.nei.ItemVisibilityHash;
import codechicken.nei.LayoutManager;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.SaveLoadButton;
import codechicken.nei.Widget;
import codechicken.nei.forge.GuiContainerManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class DropDownWidget extends Widget {

   public DropDownFile file;
   private int dropDowna;
   private long lastclicktime;
   private ArrayList mouseovernamestack;
   private boolean mouseoverTickRecorded;
   public boolean canChangeMouseOver;
   public ItemStack hoverItem;
   public SaveLoadButton[] stateButtons;
   public Button[] deleteButtons;
   public int maxheight;
   public int droppedwidth;
   private int relx;
   private int hiddenlevel;
   private LinkedList hiddenstack;
   public static boolean texturedButtons;
   private static final int stacklatency = 4;


   public DropDownWidget() {
      this.file = DropDownFile.dropDownInstance;
      this.mouseovernamestack = new ArrayList();
      this.canChangeMouseOver = true;
      this.stateButtons = new SaveLoadButton[7];
      this.deleteButtons = new Button[7];
      this.hiddenstack = new LinkedList();
      int i1 = 0;
      for(final int i = 0; i < 7; ++i1) {
         this.stateButtons[i] = new SaveLoadButton("VIS") {
            public void onTextChange() {
               NBTTagCompound hashSave = NEIClientConfig.saveCompound.getCompoundTag("vis");
               NEIClientConfig.saveCompound.setTag("vis", hashSave);
               NBTTagCompound statelist = hashSave.getCompoundTag("statename");
               hashSave.setTag("statename", statelist);
               statelist.setString("" + i, super.label.substring(5));
               NEIClientConfig.saveConfig();
            }
            public boolean onButtonPress(boolean rightclick) {
               if(rightclick) {
                  return false;
               } else {
                  if(ItemVisibilityHash.isStateSaved(i)) {
                     NEIClientConfig.vishash.loadState(i);
                  } else {
                     NEIClientConfig.vishash.saveState(i);
                  }

                  return true;
               }
            }
         };
         this.deleteButtons[i] = new Button("x") {
            public boolean onButtonPress(boolean rightclick) {
               if(!rightclick) {
                  NEIClientConfig.vishash.clearState(i);
                  return true;
               } else {
                  return false;
               }
            }
         };
         this.stateButtons[i].height = 20;
         this.deleteButtons[i].width = 16;
         this.deleteButtons[i].height = 16;
      }

   }

   public void draw(GuiContainerManager gui, int mousex, int mousey) {
      this.hoverItem = null;
      boolean mouseover = super.contains(mousex, mousey);
      texturedButtons = NEIClientConfig.getLayoutStyle() == 0;
      if(!texturedButtons) {
         gui.drawRect(super.x, super.y, super.width, super.height, mouseover?-297791480:-301989888);
         gui.drawTextCentered(super.x, super.y, super.width, super.height, "Каталоги Предметов", -1);
      } else {
         GL11.glDisable(2896);
         gui.bindTexture("/gui/gui.png");
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         int tex = mouseover?2:1;
         LayoutManager.drawButtonBackground(gui, super.x - 1, super.y, super.width + 2, super.height, true, tex);
         if(mouseover) {
            gui.drawTextCentered("Каталоги Предметов", super.x + super.width / 2, super.y + (super.height - 8) / 2, 16777120);
         } else {
            gui.drawTextCentered("Каталоги Предметов", super.x + super.width / 2, super.y + (super.height - 8) / 2, 14737632);
         }
      }

      if(this.getDropDown() == 1) {
         if(this.mouseovernamestack.size() == 0) {
            this.setDropDown(0);
            return;
         }

         this.updateMouseOver(mousex, mousey);
         this.updatePosition(gui, mousex, mousey);
         this.file.draw(gui, mousex, mousey);
      } else if(this.getDropDown() == 2) {
         this.updateStatePosition(gui);
         this.drawStateButtons(gui, mousex, mousey);
      }

   }

   private void drawStateButtons(GuiContainerManager gui, int mousex, int mousey) {
      for(int i = 0; i < 7; ++i) {
         this.stateButtons[i].draw(gui, mousex, mousey);
         this.deleteButtons[i].draw(gui, mousex, mousey);
      }

   }

   private void updateStatePosition(GuiContainerManager gui) {
      int maxWidth = 0;

      int buttonx;
      for(buttonx = 0; buttonx < 7; ++buttonx) {
         this.deleteButtons[buttonx].x = -1000;
         this.stateButtons[buttonx].y = super.height + 2 + 22 * buttonx;
         NBTTagCompound i = NEIClientConfig.saveCompound.getCompoundTag("vis");
         NEIClientConfig.saveCompound.setTag("vis", i);
         NBTTagCompound statelist = i.getCompoundTag("statename");
         i.setTag("statename", statelist);
         String name = statelist.getString("" + buttonx);
         if(statelist.getTag("" + buttonx) == null) {
            name = "" + (buttonx + 1);
            statelist.setString("" + buttonx, name);
         }

         this.stateButtons[buttonx].label = (ItemVisibilityHash.isStateSaved(buttonx)?"Load ":"Save ") + name;
         int buttonw = gui.getStringWidth(this.stateButtons[buttonx].label) + 26;
         if(buttonw + 22 > super.width) {
            buttonw = super.width - 22;
         }

         if(buttonw > maxWidth) {
            maxWidth = buttonw;
         }
      }

      buttonx = super.x + (super.width - (maxWidth + 20)) / 2;

      for(int var8 = 0; var8 < 7; ++var8) {
         this.stateButtons[var8].width = maxWidth;
         this.stateButtons[var8].x = buttonx;
         if(ItemVisibilityHash.isStateSaved(var8)) {
            this.deleteButtons[var8].x = this.stateButtons[var8].x + maxWidth + 2;
            this.deleteButtons[var8].y = this.stateButtons[var8].y + 2;
         }
      }

   }

   private void updateMouseOver(int mousex, int mousey) {
      String newmouseover = this.file.updateMouseOver(mousex, mousey, (String)this.mouseovernamestack.get(0));
      if(!this.mouseoverTickRecorded) {
         if(this.canChangeMouseOver) {
            String prevmouseover = (String)this.mouseovernamestack.get(this.mouseovernamestack.size() - 1);
            String currentmouseover = (String)this.mouseovernamestack.get(0);
            if(!newmouseover.equals(prevmouseover)) {
               for(int i = 0; i < this.mouseovernamestack.size(); ++i) {
                  this.mouseovernamestack.set(i, currentmouseover);
               }
            }

            this.mouseovernamestack.add(newmouseover);
         } else {
            this.mouseovernamestack.add((String)this.mouseovernamestack.get(this.mouseovernamestack.size() - 1));
         }

         this.mouseoverTickRecorded = true;
      }

      if(((String)this.mouseovernamestack.get(0)).equals("") && !LayoutManager.dropDown.contains(mousex, mousey)) {
         this.setDropDown(0);
      }

   }

   private void updatePosition(GuiContainerManager gui, int mousex, int mousey) {
      this.rehashMaxHeight(gui);

      while(true) {
         while(true) {
            this.droppedwidth = 0;
            this.file.position(super.x + this.relx, super.y + super.height);
            int levelwidth;
            if(this.droppedwidth <= super.width) {
               if(this.relx >= 0 || super.width - this.droppedwidth <= ((Integer)this.hiddenstack.getLast()).intValue()) {
                  return;
               }

               levelwidth = ((Integer)this.hiddenstack.getLast()).intValue();
               if(!((String)this.mouseovernamestack.get(0)).equals("")) {
                  moveMouse(gui.window.mc, levelwidth, 0);
                  mousex += levelwidth;
               }

               this.relx += levelwidth;
               this.hiddenstack.removeLast();
               --this.hiddenlevel;
            } else {
               levelwidth = this.file.getWidthAtLevel(this.hiddenlevel);
               if(mousex - levelwidth < super.x) {
                  levelwidth = this.droppedwidth - super.width;
               }

               moveMouse(gui.window.mc, -levelwidth, 0);
               mousex -= levelwidth;
               this.relx -= levelwidth;
               this.hiddenstack.addLast(Integer.valueOf(levelwidth));
               ++this.hiddenlevel;
            }
         }
      }
   }

   public static void moveMouse(Minecraft mc, int relx, int rely) {
      ScaledResolution sres = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
      Mouse.setCursorPosition(Mouse.getX() + relx * mc.displayWidth / sres.getScaledWidth(), Mouse.getY() + rely * mc.displayHeight / sres.getScaledHeight());
   }

   private void rehashMaxHeight(GuiContainerManager gui) {
      this.maxheight = gui.window.height - super.height - super.y - 25;
      this.maxheight = this.maxheight / 18 * 18;
   }

   public boolean handleClick(int mousex, int mousey, int button) {
      if(super.contains(mousex, mousey)) {
         if(button == 0) {
            if(System.currentTimeMillis() - this.lastclicktime < 300L) {
               this.file.showAllItems();
               this.file.updateState();
               ItemList.updateSearch();
               NEIClientConfig.vishash.save();
            }

            this.setDropDown(1);
            this.lastclicktime = System.currentTimeMillis();
         } else if(button == 1) {
            if(this.getDropDown() == 2) {
               this.setDropDown(0);
            } else {
               this.setDropDown(2);
            }
         }

         return true;
      } else {
         return this.getDropDown() == 1?this.file.click(mousex, mousey, button):(this.getDropDown() == 2?this.processStateClick(mousex, mousey, button):false);
      }
   }

   private boolean processStateClick(int mousex, int mousey, int button) {
      for(int i = 0; i < 7; ++i) {
         if(this.stateButtons[i].contains(mousex, mousey) && this.stateButtons[i].handleClick(mousex, mousey, button) || this.deleteButtons[i].contains(mousex, mousey) && this.deleteButtons[i].handleClick(mousex, mousey, button)) {
            return true;
         }
      }

      this.setDropDown(0);
      return false;
   }

   public boolean handleKeyPress(int keyID, char keyChar) {
      for(int i = 0; i < 7; ++i) {
         if(this.stateButtons[i].handleKeyPress(keyID, keyChar)) {
            return true;
         }
      }

      return false;
   }

   public void onGuiClick(int mousex, int mousey) {
      if(this.getDropDown() == 2) {
         for(int i = 0; i < 7; ++i) {
            this.stateButtons[i].onGuiClick(mousex, mousey);
         }

         if(!this.contains(mousex, mousey)) {
            this.setDropDown(0);
         }
      }

   }

   public void mouseUp(int mousex, int mousey, int button) {
      if(this.getDropDown() == 1) {
         this.file.mouseUp(mousex, mousey, button);
      }

   }

   public boolean contains(int mousex, int mousey) {
      return super.contains(mousex, mousey) || this.getDropDown() == 1 && this.file.contains(mousex, mousey) || this.getDropDown() == 2 && this.statesContain(mousex, mousey);
   }

   private boolean statesContain(int mousex, int mousey) {
      for(int i = 0; i < 7; ++i) {
         if(this.stateButtons[i].contains(mousex, mousey) || this.deleteButtons[i].contains(mousex, mousey)) {
            return true;
         }
      }

      return false;
   }

   public boolean onMouseWheel(int i, int mousex, int mousey) {
      if(this.getDropDown() == 1) {
         this.file.onMouseWheel(-i);
         return true;
      } else {
         return false;
      }
   }

   public void setHoverItem(ItemStack item) {
      this.hoverItem = item;
   }

   public void update(GuiContainerManager gui) {
      if(this.getDropDown() == 1) {
         if(this.mouseovernamestack.size() == 0) {
            this.setDropDown(0);
            return;
         }

         this.mouseovernamestack.remove(0);
         if(!this.mouseoverTickRecorded) {
            this.mouseovernamestack.add((String)this.mouseovernamestack.get(this.mouseovernamestack.size() - 1));
         }

         this.mouseoverTickRecorded = false;
      } else if(this.getDropDown() == 2) {
         for(int i = 0; i < 7; ++i) {
            this.stateButtons[i].update(gui);
         }
      }

   }

   public void setDropDown(int drop) {
      if(drop == 1) {
         this.mouseoverTickRecorded = false;
         this.mouseovernamestack.clear();

         for(int i = 0; i < 4; ++i) {
            this.mouseovernamestack.add("");
         }
      }

      this.dropDowna = drop;
   }

   public int getDropDown() {
      return this.dropDowna;
   }

   public ItemStack getStackMouseOver(int mousex, int mousey) {
      return this.hoverItem;
   }

   public List handleTooltip(int mx, int my, List tooltip) {
      return tooltip;
   }
}
