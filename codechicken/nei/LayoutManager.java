package codechicken.nei;

import codechicken.nei.AllowedPropertyMap;
import codechicken.nei.Button;
import codechicken.nei.Button2ActiveState;
import codechicken.nei.DropDownWidget;
import codechicken.nei.GuiExtendedCreativeInv;
import codechicken.nei.GuiNEISettings;
import codechicken.nei.Image;
import codechicken.nei.InterActionMap;
import codechicken.nei.ItemList;
import codechicken.nei.ItemPanel;
import codechicken.nei.ItemQuantityField;
import codechicken.nei.Label;
import codechicken.nei.LayoutStyleMinecraft;
import codechicken.nei.LayoutStyleTMIOld;
import codechicken.nei.NEICPH;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIController;
import codechicken.nei.SaveLoadButton;
import codechicken.nei.SearchField;
import codechicken.nei.TextField;
import codechicken.nei.VisiblityData;
import codechicken.nei.Widget;
import codechicken.nei.WidgetZOrder;
import codechicken.nei.api.API;
import codechicken.nei.api.GuiInfo;
import codechicken.nei.api.INEIGuiHandler;
import codechicken.nei.api.IRecipeOverlayRenderer;
import codechicken.nei.api.ItemInfo;
import codechicken.nei.api.LayoutStyle;
import codechicken.nei.forge.GuiContainerManager;
import codechicken.nei.forge.IContainerDrawHandler;
import codechicken.nei.forge.IContainerInputHandler;
import codechicken.nei.forge.IContainerObjectHandler;
import codechicken.nei.forge.IContainerTooltipHandler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.opengl.GL11;

public class LayoutManager implements IContainerInputHandler, IContainerTooltipHandler, IContainerDrawHandler, IContainerObjectHandler {

   private static LayoutManager instance;
   private static Widget inputFocused;
   private static TreeSet drawWidgets;
   private static TreeSet controlWidgets;
   public static ItemPanel itemPanel;
   public static DropDownWidget dropDown;
   public static TextField searchField;
   public static Button options;
   public static Button prev;
   public static Button next;
   public static Label pageLabel;
   public static Button more;
   public static Button less;
   public static ItemQuantityField quantity;
   public static SaveLoadButton[] stateButtons;
   public static Button[] deleteButtons;
   public static Button delete;
   public static Button2ActiveState creative;
   public static Button rain;
   public static Button magnet;
   public static Button[] timeButtons = new Button[4];
   public static final String[] timeZones = new String[]{"Рассвет", "Полдень", "Сумерки", "Полночь"};
   public static Button heal;
   public static IRecipeOverlayRenderer overlayRenderer;
   public static HashMap layoutStyles = new HashMap();


   public static void load() {
      API.addLayoutStyle(0, new LayoutStyleMinecraft());
      API.addLayoutStyle(1, new LayoutStyleTMIOld());
      instance = new LayoutManager();
      GuiContainerManager.addInputHandler(instance);
      GuiContainerManager.addTooltipHandler(instance);
      GuiContainerManager.addDrawHandler(instance);
      GuiContainerManager.addObjectHandler(instance);
      init();
   }

   public void onPreDraw(GuiContainer gui) {
      if(!NEIClientConfig.isHidden() && NEIClientConfig.isEnabled() && gui instanceof InventoryEffectRenderer) {
         gui.guiLeft = (gui.width - gui.xSize) / 2;
         gui.guiTop = (gui.height - gui.ySize) / 2;
         if(gui instanceof GuiContainerCreative && gui.buttonList.size() >= 2) {
            GuiButton button1 = (GuiButton)gui.buttonList.get(0);
            GuiButton button2 = (GuiButton)gui.buttonList.get(1);
            button1.xPosition = gui.guiLeft;
            button2.xPosition = gui.guiLeft + gui.xSize - 20;
         }
      }

   }

   public void onMouseClicked(GuiContainer gui, int mousex, int mousey, int button) {
      Iterator var6 = controlWidgets.iterator();

      while(var6.hasNext()) {
         Widget widget = (Widget)var6.next();
         widget.onGuiClick(mousex, mousey);
      }

   }

   public boolean mouseClicked(GuiContainer gui, int mousex, int mousey, int button) {
      if(NEIClientConfig.isHidden()) {
         return false;
      } else if(!NEIClientConfig.isEnabled()) {
         return options.contains(mousex, mousey) && options.handleClick(mousex, mousey, button);
      } else {
         Iterator var6 = controlWidgets.iterator();

         while(true) {
            if(!var6.hasNext()) {
               return false;
            }

            Widget widget = (Widget)var6.next();
            widget.onGuiClick(mousex, mousey);
            if(widget.contains(mousex, mousey)) {
               if(widget.handleClick(mousex, mousey, button)) {
                  break;
               }
            } else if(widget.handleClickExt(mousex, mousey, button)) {
               break;
            }
         }

         return true;
      }
   }

   public boolean objectUnderMouse(GuiContainer gui, int mousex, int mousey) {
      if(NEIClientConfig.isEnabled() && !NEIClientConfig.isHidden()) {
         Iterator var5 = controlWidgets.iterator();

         while(var5.hasNext()) {
            Widget widget = (Widget)var5.next();
            if(widget.contains(mousex, mousey)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public boolean keyTyped(GuiContainer gui, char keyChar, int keyID) {
      if(NEIClientConfig.isEnabled() && !NEIClientConfig.isHidden()) {
         if(inputFocused != null) {
            return inputFocused.handleKeyPress(keyID, keyChar);
         }

         Iterator var5 = controlWidgets.iterator();

         while(var5.hasNext()) {
            Widget widget = (Widget)var5.next();
            if(widget.handleKeyPress(keyID, keyChar)) {
               return true;
            }
         }
      }

      return false;
   }

   public void onKeyTyped(GuiContainer gui, char keyChar, int keyID) {}

   public boolean lastKeyTyped(GuiContainer gui, char keyChar, int keyID) {
      if(keyID == NEIClientConfig.getKeyBinding("hide")) {
         NEIClientConfig.toggleBooleanSetting("options.hidden");
         return true;
      } else {
         return false;
      }
   }

   public void onMouseUp(GuiContainer gui, int mx, int my, int button) {
      try {
         if(!NEIClientConfig.isEnabled() || NEIClientConfig.isHidden()) {
            return;
         }

         Iterator var6 = controlWidgets.iterator();

         while(var6.hasNext()) {
            Widget exception = (Widget)var6.next();
            exception.mouseUp(mx, my, button);
         }
      } catch (Exception var7) {
         NEIClientUtils.reportException(var7);
         NEIClientConfig.setEnabled(false);
      }

   }

   public void onMouseDragged(GuiContainer gui, int mx, int my, int button, long heldTime) {
      try {
         if(!NEIClientConfig.isEnabled() || NEIClientConfig.isHidden()) {
            return;
         }

         Iterator var8 = controlWidgets.iterator();

         while(var8.hasNext()) {
            Widget exception = (Widget)var8.next();
            exception.mouseDragged(mx, my, button, heldTime);
         }
      } catch (Exception var9) {
         NEIClientUtils.reportException(var9);
         NEIClientConfig.setEnabled(false);
      }

   }

   public ItemStack getStackUnderMouse(GuiContainer gui, int mousex, int mousey) {
      if(NEIClientConfig.isEnabled() && !NEIClientConfig.isHidden()) {
         Iterator var5 = controlWidgets.iterator();

         while(var5.hasNext()) {
            Widget widget = (Widget)var5.next();
            ItemStack stack = widget.getStackMouseOver(mousex, mousey);
            if(stack != null) {
               return stack;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public void renderObjects(GuiContainer gui, int mousex, int mousey) {
      if(!NEIClientConfig.isHidden()) {
         layout(gui);
         if(NEIClientConfig.isEnabled()) {
            getLayoutStyle().drawBackground(gui.manager);
            Iterator var5 = drawWidgets.iterator();

            while(var5.hasNext()) {
               Widget widget = (Widget)var5.next();
               widget.draw(gui.manager, mousex, mousey);
            }
         } else {
            options.draw(gui.manager, mousex, mousey);
         }

         GL11.glEnable(2896);
         GL11.glEnable(2929);
      }

   }

   public void postRenderObjects(GuiContainer gui, int mousex, int mousey) {
      if(!NEIClientConfig.isHidden() && NEIClientConfig.isEnabled()) {
         Iterator var5 = drawWidgets.iterator();

         while(var5.hasNext()) {
            Widget widget = (Widget)var5.next();
            widget.postDraw(gui.manager, mousex, mousey);
         }
      }

   }

   public List handleTooltipFirst(GuiContainer gui, int mousex, int mousey, List currenttip) {
      Widget widget;
      if(!NEIClientConfig.isHidden() && NEIClientConfig.isEnabled() && ((GuiContainerManager) gui.manager).shouldShowTooltip()) {
         for(Iterator var6 = controlWidgets.iterator(); var6.hasNext(); currenttip = widget.handleTooltip(mousex, mousey, currenttip)) {
            widget = (Widget)var6.next();
         }
      }

      return currenttip;
   }

   public List handleItemTooltip(GuiContainer gui, ItemStack itemstack, List currenttip) {
      String overridename = ItemInfo.getOverrideName(itemstack.itemID, itemstack.getItemDamage());
      if(overridename != null) {
         currenttip.set(0, overridename);
      }

      String mainname = (String)currenttip.get(0);
      if(NEIClientConfig.showIDs()) {
         mainname = mainname + " " + itemstack.itemID;
         if(itemstack.getItemDamage() != 0) {
            mainname = mainname + ":" + itemstack.getItemDamage();
         }

         currenttip.set(0, mainname);
      }

      return currenttip;
   }

   public static void layout(GuiContainer gui) {
      VisiblityData visiblity = new VisiblityData();
      if(NEIClientConfig.isHidden()) {
         visiblity.showNEI = false;
      }

      if(gui.height - gui.ySize <= 40) {
         visiblity.showSearchSection = false;
      }

      if(gui.guiLeft - 4 < 76) {
         visiblity.showWidgets = false;
      }

      Iterator var3 = GuiInfo.guiHandlers.iterator();

      while(var3.hasNext()) {
         INEIGuiHandler handler = (INEIGuiHandler)var3.next();
         handler.modifyVisiblity(gui, visiblity);
      }

      visiblity.translateDependancies();
      getLayoutStyle().layout(gui, visiblity);
      updateWidgetVisiblities(gui, visiblity);
   }

   private static void init() {
      itemPanel = new ItemPanel();
      dropDown = new DropDownWidget();
      searchField = new SearchField("search");
      options = new Button("Опции NEI") {
         public boolean onButtonPress(boolean rightclick) {
            if(!rightclick) {
               NEIClientUtils.overlayScreen(new GuiNEISettings(NEIClientUtils.getGuiContainer()));
               return true;
            } else {
               return false;
            }
         }
      };
      prev = new Button("<-") {
         public boolean onButtonPress(boolean rightclick) {
            if(!rightclick) {
               LayoutManager.itemPanel.scroll(-1);
               return true;
            } else {
               return false;
            }
         }
      };
      next = new Button("->") {
         public boolean onButtonPress(boolean rightclick) {
            if(!rightclick) {
               LayoutManager.itemPanel.scroll(1);
               return true;
            } else {
               return false;
            }
         }
      };
      pageLabel = new Label("(0/0)", true);
      more = new Button("+") {
         public boolean onButtonPress(boolean rightclick) {
            if(rightclick) {
               return false;
            } else {
               int modifier = NEIClientUtils.controlKey()?64:(NEIClientUtils.shiftKey()?10:1);
               int quantity = NEIClientConfig.getItemQuantity() + modifier;
               if(quantity < 0) {
                  quantity = 0;
               }

               NEIClientUtils.setItemQuantity(quantity);
               return true;
            }
         }
      };
      less = new Button("-") {
         public boolean onButtonPress(boolean rightclick) {
            if(rightclick) {
               return false;
            } else {
               int modifier = NEIClientUtils.controlKey()?-64:(NEIClientUtils.shiftKey()?-10:-1);
               int quantity = NEIClientConfig.getItemQuantity() + modifier;
               if(quantity < 0) {
                  quantity = 0;
               }

               NEIClientUtils.setItemQuantity(quantity);
               return true;
            }
         }
      };
      quantity = new ItemQuantityField("quantity");
      stateButtons = new SaveLoadButton[7];
      deleteButtons = new Button[7];

      final int i = 0;
      int i1 = 0;
      for(i1 = 0; i < 7; ++i1) {
         stateButtons[i] = new SaveLoadButton("") {
            public boolean onButtonPress(boolean rightclick) {
               if(NEIClientConfig.isStateSaved(i)) {
                  NEIClientConfig.loadState(i);
               } else {
                  NEIClientConfig.saveState(i);
               }

               return true;
            }
            public void onTextChange() {
               NBTTagCompound statelist = NEIClientConfig.saveCompound.getCompoundTag("statename");
               NEIClientConfig.saveCompound.setTag("statename", statelist);
               statelist.setString("" + i, super.label.substring(5));
               NEIClientConfig.saveConfig();
            }
         };
         deleteButtons[i] = new Button("x") {
            public boolean onButtonPress(boolean rightclick) {
               if(!rightclick) {
                  NEIClientConfig.clearState(i);
                  return true;
               } else {
                  return false;
               }
            }
         };
      }
   }

   private static String getStateTip(String name, int state) {
      return (state & 3) == 2?"Включить " + name:" " + name + ((state & 3) == 1?" Выкл":" Вкл");
   }

   private static String getTimeTip(String zone, String next, int state) {
      return (state & 3) == 2?"Включить " + zone + "-" + next:"Время Суток: " + zone;
   }

   private static boolean handleDisabledButtonPress(String ident, boolean rightclick) {
      return !AllowedPropertyMap.nameSet.contains(ident)?false:(rightclick && !NEIClientConfig.isPropertyDisabled(ident)?setPropertyDisabled(ident, true):(!rightclick && NEIClientConfig.isPropertyDisabled(ident)?setPropertyDisabled(ident, false):false));
   }

   private static boolean setPropertyDisabled(String ident, boolean disable) {
      if(disable && ((Integer)AllowedPropertyMap.nameToIDMap.get(ident)).intValue() < 4) {
         int count = 0;

         for(int i = 0; i < 4; ++i) {
            if(NEIClientConfig.isPropertyDisabled((String)AllowedPropertyMap.idToNameMap.get(Integer.valueOf(i)))) {
               ++count;
            }
         }

         if(count == 3) {
            return false;
         }
      }

      NEIClientConfig.setPropertyDisabled(ident, disable);
      return true;
   }

   public void load(GuiContainer gui) {
      if(NEIClientConfig.isEnabled()) {
         setInputFocused((Widget)null);
         ItemList.loadItems();
         overlayRenderer = null;
         getLayoutStyle().init();
         layout(gui);
      }

      NEIController.load(gui);
      if(this.checkCreativeInv(gui)) {
         if(gui.mc.currentScreen instanceof GuiContainerCreative) {
            gui.mc.displayGuiScreen((GuiScreen)null);
         }

      }
   }

   public void refresh(GuiContainer gui) {}

   public boolean checkCreativeInv(GuiContainer gui) {
      if(gui instanceof GuiContainerCreative && NEIClientConfig.invCreativeMode()) {
         NEICPH.sendCreativeInv(true);
         return true;
      } else if(gui instanceof GuiExtendedCreativeInv && !NEIClientConfig.invCreativeMode()) {
         NEICPH.sendCreativeInv(false);
         return true;
      } else {
         return false;
      }
   }

   public static void updateWidgetVisiblities(GuiContainer gui, VisiblityData visiblity) {
      drawWidgets = new TreeSet(new WidgetZOrder(false));
      controlWidgets = new TreeSet(new WidgetZOrder(true));
      if(visiblity.showNEI) {
         addWidget(options);
         if(visiblity.showItemPanel) {
            addWidget(itemPanel);
            addWidget(prev);
            addWidget(next);
            addWidget(pageLabel);
            if(NEIClientConfig.isActionPermissable(InterActionMap.ITEM)) {
               addWidget(more);
               addWidget(less);
               addWidget(quantity);
            }
         }

         if(visiblity.showSearchSection) {
            addWidget(dropDown);
            addWidget(searchField);
         }

         int i;
         if(NEIClientConfig.isActionPermissable(InterActionMap.ITEM) && visiblity.showStateButtons) {
            for(i = 0; i < 7; ++i) {
               addWidget(stateButtons[i]);
               if(NEIClientConfig.isStateSaved(i)) {
                  addWidget(deleteButtons[i]);
               }
            }
         }

         if(visiblity.showUtilityButtons) {
            if(NEIClientConfig.isActionPermissable(InterActionMap.TIME)) {
               for(i = 0; i < 4; ++i) {
                  addWidget(timeButtons[i]);
               }
            }

            if(NEIClientConfig.isActionPermissable(InterActionMap.RAIN)) {
               addWidget(rain);
            }

            if(NEIClientConfig.isActionPermissable(InterActionMap.HEAL)) {
               addWidget(heal);
            }

            if(NEIClientConfig.isActionPermissable(InterActionMap.MAGNET)) {
               addWidget(magnet);
            }

            if(NEIClientConfig.isActionPermissable(InterActionMap.CREATIVE)) {
               addWidget(creative);
            }

            if(NEIClientConfig.isActionPermissable(InterActionMap.DELETE)) {
               addWidget(delete);
            }
         }

      }
   }

   public static LayoutStyle getLayoutStyle() {
      LayoutStyle style = (LayoutStyle)layoutStyles.get(Integer.valueOf(NEIClientConfig.getLayoutStyle()));
      if(style == null) {
         style = (LayoutStyle)layoutStyles.get(Integer.valueOf(0));
      }

      return style;
   }

   private static void addWidget(Widget widget) {
      drawWidgets.add(widget);
      controlWidgets.add(widget);
   }

   public void guiTick(GuiContainer gui) {
      if(!this.checkCreativeInv(gui)) {
         if(NEIClientConfig.isEnabled()) {
            Iterator var3 = controlWidgets.iterator();

            while(var3.hasNext()) {
               Widget widget = (Widget)var3.next();
               widget.update((GuiContainerManager) gui.manager);
            }

         }
      }
   }

   public boolean mouseScrolled(GuiContainer gui, int mousex, int mousey, int scrolled) {
      if(!NEIClientConfig.isHidden() && NEIClientConfig.isEnabled()) {
         Iterator var6 = controlWidgets.iterator();

         while(var6.hasNext()) {
            Widget widget = (Widget)var6.next();
            if(widget.onMouseWheel(scrolled, mousex, mousey)) {
               return true;
            }
         }

         return NEIController.mouseScrolled(scrolled);
      } else {
         return false;
      }
   }

   public void onMouseScrolled(GuiContainer gui, int mousex, int mousey, int scrolled) {}

   public boolean shouldShowTooltip(GuiContainer gui) {
      return itemPanel.draggedStack == null;
   }

   public static Widget getInputFocused() {
      return inputFocused;
   }

   public static void setInputFocused(Widget widget) {
      if(inputFocused != null) {
         inputFocused.loseFocus();
      }

      inputFocused = widget;
      if(inputFocused != null) {
         inputFocused.gainFocus();
      }

   }

   public void renderSlotUnderlay(GuiContainer gui, Slot slot) {
      if(overlayRenderer != null) {
         overlayRenderer.renderOverlay((GuiContainerManager) gui.manager, slot);
      }

   }

   public void renderSlotOverlay(GuiContainer window, Slot slot) {
      ItemStack item = slot.getStack();
      if(NEIClientConfig.getBooleanSetting("options.searchinventories")) {
         if(item == null) {
            if(NEIClientConfig.getSearchExpression().equals("")) {
               return;
            }
         } else if(ItemList.itemMatchesSearch(item)) {
            return;
         }

         GL11.glDisable(2896);
         GL11.glTranslatef(0.0F, 0.0F, 150.0F);
         (window.manager).drawRect(slot.xDisplayPosition, slot.yDisplayPosition, 16, 16, Integer.MIN_VALUE);
         GL11.glTranslatef(0.0F, 0.0F, -150.0F);
         GL11.glEnable(2896);
      }

   }

   public static void drawIcon(GuiContainer window, int x, int y, Image image) {
      ((GuiContainerManager) window.manager).bindTexture("/codechicken/nei/nei_sprites.png");
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      window.drawTexturedModalRect(x, y, image.x, image.y, image.width, image.height);
      GL11.glDisable(3042);
   }

   public static void drawButtonBackground(GuiContainerManager manager, int x, int y, int w, int h, boolean edges, int type) {
      int wtiles = 0;
      int ew = w;
      if(w / 2 > 100) {
         wtiles = (w - 200) / 50 + 1;
         ew = 200;
      }

      int w1 = ew / 2;
      int h1 = h / 2;
      int w2 = (ew + 1) / 2;
      int h2 = (h + 1) / 2;
      int x2 = x + w - w2;
      int y2 = y + h - h2;
      int ty = 46 + type * 20;
      int te = edges?0:1;
      int ty1 = ty + te;
      byte tx3 = 75;
      int ty2 = ty + 20 - h2 - te;
      int tx2 = 200 - w2 - te;
      manager.bindTexture("/gui/gui.png");
      manager.drawTexturedModalRect(x, y, te, ty1, w1, h1);
      manager.drawTexturedModalRect(x, y2, te, ty2, w1, h2);

      for(int tile = 0; tile < wtiles; ++tile) {
         int tilex = x + w1 + 50 * tile;
         manager.drawTexturedModalRect(tilex, y, tx3, ty1, 50, h1);
         manager.drawTexturedModalRect(tilex, y2, tx3, ty2, 50, h2);
      }

      manager.drawTexturedModalRect(x2, y, tx2, ty1, w2, h1);
      manager.drawTexturedModalRect(x2, y2, tx2, ty2, w2, h2);
   }

   public static LayoutManager instance() {
      return instance;
   }
}
