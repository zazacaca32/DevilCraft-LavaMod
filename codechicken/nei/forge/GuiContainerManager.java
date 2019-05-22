package codechicken.nei.forge;

import codechicken.nei.forge.DefaultSlotClickHandler;
import codechicken.nei.forge.IContainerDrawHandler;
import codechicken.nei.forge.IContainerInputHandler;
import codechicken.nei.forge.IContainerObjectHandler;
import codechicken.nei.forge.IContainerSlotClickHandler;
import codechicken.nei.forge.IContainerTooltipHandler;
import java.awt.Point;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiContainerManager {

   public GuiContainer window;
   public static RenderItem drawItems = new RenderItem();
   public static final LinkedList tooltipHandlers = new LinkedList();
   public static final LinkedList inputHandlers = new LinkedList();
   public static final LinkedList drawHandlers = new LinkedList();
   public static final LinkedList objectHandlers = new LinkedList();
   public static final LinkedList slotClickHandlers = new LinkedList();
   private static HashSet stackTraces;


   static {
      addSlotClickHandler(new DefaultSlotClickHandler());
      stackTraces = new HashSet();
   }

   public GuiContainerManager(GuiContainer screen) {
      this.window = screen;
   }

   public static void addTooltipHandler(IContainerTooltipHandler handler) {
      tooltipHandlers.add(handler);
   }

   public static void addInputHandler(IContainerInputHandler handler) {
      inputHandlers.add(handler);
   }

   public static void addDrawHandler(IContainerDrawHandler handler) {
      drawHandlers.add(handler);
   }

   public static void addObjectHandler(IContainerObjectHandler handler) {
      objectHandlers.add(handler);
   }

   public static void addSlotClickHandler(IContainerSlotClickHandler handler) {
      slotClickHandlers.addFirst(handler);
   }

   public Point getMousePosition() {
      Minecraft mc = this.window.mc;
      ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
      int w = scaledresolution.getScaledWidth();
      int h = scaledresolution.getScaledHeight();
      return new Point(Mouse.getX() * w / mc.displayWidth, h - Mouse.getY() * h / mc.displayHeight - 1);
   }

   public ItemStack getStackMouseOver() {
      Point mousePos = this.getMousePosition();
      ItemStack item = null;
      Iterator var4 = objectHandlers.iterator();

      while(var4.hasNext()) {
         IContainerObjectHandler slot = (IContainerObjectHandler)var4.next();
         item = slot.getStackUnderMouse(this.window, mousePos.x, mousePos.y);
         if(item != null) {
            return item;
         }
      }

      if(!this.objectUnderMouse(mousePos.x, mousePos.y)) {
         Slot slot1 = this.window.getSlotAtPosition(mousePos.x, mousePos.y);
         if(slot1 != null) {
            item = slot1.getStack();
         }
      }

      return item;
   }

   public int getStringWidth(String s) {
      return s != null && !s.equals("")?getStringWidthNoColours(this.window.fontRenderer, s):0;
   }

   public static int getStringWidthNoColours(FontRenderer fontRenderer, String s) {
      while(true) {
         int pos = s.indexOf(167);
         if(pos == -1) {
            return fontRenderer.getStringWidth(s);
         }

         s = s.substring(0, pos) + s.substring(pos + 2);
      }
   }

   public static List itemDisplayNameMultiline(ItemStack itemstack, GuiContainer gui, boolean includeHandlers) {
      Object namelist = null;

      try {
         boolean var10002 = includeHandlers && Minecraft.getMinecraft().gameSettings.advancedItemTooltips;
         namelist = itemstack.getTooltip(Minecraft.getMinecraft().thePlayer, var10002);
      } catch (Throwable var6) {
         ;
      }

      if(namelist == null) {
         namelist = new ArrayList();
      }

      if(((List)namelist).size() == 0) {
         ((List)namelist).add("Unnamed");
      }

      if(((List)namelist).get(0) == null || ((String)((List)namelist).get(0)).equals("")) {
         ((List)namelist).set(0, "Unnamed");
      }

      IContainerTooltipHandler i;
      if(includeHandlers) {
         for(Iterator var5 = tooltipHandlers.iterator(); var5.hasNext(); namelist = i.handleItemTooltip(gui, itemstack, (List)namelist)) {
            i = (IContainerTooltipHandler)var5.next();
         }
      }

      ((List)namelist).set(0, "§" + Integer.toHexString(itemstack.getRarity().rarityColor) + (String)((List)namelist).get(0));

      for(int var7 = 1; var7 < ((List)namelist).size(); ++var7) {
         ((List)namelist).set(var7, "§7" + (String)((List)namelist).get(var7));
      }

      return (List)namelist;
   }

   public static String itemDisplayNameShort(ItemStack itemstack) {
      List list = itemDisplayNameMultiline(itemstack, (GuiContainer)null, false);
      return (String)list.get(0);
   }

   public static String concatenatedDisplayName(ItemStack itemstack, boolean includeHandlers) {
      List list = itemDisplayNameMultiline(itemstack, (GuiContainer)null, includeHandlers);
      StringBuilder sb = new StringBuilder();
      boolean first = true;

      String s;
      for(Iterator pos = list.iterator(); pos.hasNext(); sb.append(s)) {
         s = (String)pos.next();
         if(first) {
            first = false;
         } else {
            sb.append("#");
         }
      }

      s = sb.toString();

      while(true) {
         int pos1 = s.indexOf(167);
         if(pos1 == -1) {
            return s;
         }

         s = s.substring(0, pos1) + s.substring(pos1 + 2);
      }
   }

   public void drawRect(int x, int y, int w, int h, int colour) {
      this.window.drawGradientRect(x, y, x + w, y + h, colour, colour);
   }

   public void drawGradientRect(int x, int y, int w, int h, int colour1, int colour2) {
      this.window.drawGradientRect(x, y, x + w, y + h, colour1, colour2);
   }

   public void drawTexturedModalRect(int x, int y, int tx, int ty, int w, int h) {
      this.window.drawTexturedModalRect(x, y, tx, ty, w, h);
   }

   public void drawText(int x, int y, String text, int colour, boolean shadow) {
      if(shadow) {
         this.window.fontRenderer.drawStringWithShadow(text, x, y, colour);
      } else {
         this.window.fontRenderer.drawString(text, x, y, colour);
      }

   }

   public void drawTextCentered(int x, int y, int w, int h, String text, int colour, boolean shadow) {
      this.drawText(x + (w - this.getStringWidth(text)) / 2, y + (h - 8) / 2, text, colour, shadow);
   }

   public void drawTextCentered(String text, int x, int y, int colour, boolean shadow) {
      this.drawText(x - this.getStringWidth(text) / 2, y, text, colour, shadow);
   }

   public void drawText(int x, int y, String text, boolean shadow) {
      this.drawText(x, y, text, -1, shadow);
   }

   public void drawText(int x, int y, String text, int colour) {
      this.drawText(x, y, text, colour, true);
   }

   public void drawTextCentered(int x, int y, int w, int h, String text, int colour) {
      this.drawText(x + (w - this.getStringWidth(text)) / 2, y + (h - 8) / 2, text, colour);
   }

   public void drawTextCentered(String text, int x, int y, int colour) {
      this.drawText(x - this.getStringWidth(text) / 2, y, text, colour);
   }

   public void drawText(int x, int y, String text) {
      this.drawText(x, y, text, -1);
   }

   public void drawTip(int x, int y, String text) {
      ArrayList temp = new ArrayList();
      temp.add(text);
      this.drawMultilineTip(x, y, temp);
   }

   public void drawMultilineTip(int x, int y, List list) {
      GL11.glDisable('\u803a');
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable(2896);
      GL11.glDisable(2929);
      if(list.size() > 0) {
         int maxwidth = 0;

         int drawx;
         int drawy;
         for(drawx = 0; drawx < list.size(); ++drawx) {
            drawy = getStringWidthNoColours(this.window.fontRenderer, (String)list.get(drawx));
            if(drawy > maxwidth) {
               maxwidth = drawy;
            }
         }

         if(x + maxwidth > this.window.width - 16) {
            x = this.window.width - maxwidth - 16;
         }

         if(y < 16) {
            y = 16;
         }

         drawx = x - this.window.guiLeft + 12;
         drawy = y - this.window.guiTop - 12;
         int h = 8;
         if(list.size() > 1) {
            h += 2 + (list.size() - 1) * 10;
         }

         this.window.zLevel = 300.0F;
  
         int i4 = -267386864;
         this.drawGradientRect(drawx - 3, drawy - 4, maxwidth + 6, 1, i4, i4);
         this.drawGradientRect(drawx - 3, drawy + h + 3, maxwidth + 6, 1, i4, i4);
         this.drawGradientRect(drawx - 3, drawy - 3, maxwidth + 6, h + 6, i4, i4);
         this.drawGradientRect(drawx - 4, drawy - 3, 1, h + 6, i4, i4);
         this.drawGradientRect(drawx + maxwidth + 3, drawy - 3, 1, h + 6, i4, i4);
         int colour1 = 1347420415;
         int colour2 = (colour1 & 16711422) >> 1 | colour1 & -16777216;
         this.drawGradientRect(drawx - 3, drawy - 2, 1, h + 4, colour1, colour2);
         this.drawGradientRect(drawx + maxwidth + 2, drawy - 2, 1, h + 4, colour1, colour2);
         this.drawGradientRect(drawx - 3, drawy - 3, maxwidth + 6, 1, colour1, colour1);
         this.drawGradientRect(drawx - 3, drawy + h + 2, maxwidth + 6, 1, colour2, colour2);

         for(int i = 0; i < list.size(); ++i) {
            String s = (String)list.get(i);
            this.window.fontRenderer.drawStringWithShadow(s, drawx, drawy, -1);
            if(i == 0) {
               drawy += 2;
            }

            drawy += 10;
         }

         this.window.zLevel = 0.0F;
       
      }

   }

   public void drawItem(int i, int j, ItemStack itemstack) {
      drawItem(i, j, itemstack, this.getFontRenderer(itemstack), this.window.mc.renderEngine);
   }

   public FontRenderer getFontRenderer(ItemStack stack) {
      if(stack != null && stack.getItem() != null) {
         FontRenderer f = stack.getItem().getFontRenderer(stack);
         if(f != null) {
            return f;
         }
      }

      return this.window.fontRenderer;
   }

   public static void drawItem(int i, int j, ItemStack itemstack, FontRenderer fontRenderer, RenderEngine renderEngine) {
      enable3DRender();
      drawItems.zLevel += 100.0F;

      try {
         drawItems.renderItemAndEffectIntoGUI(fontRenderer, renderEngine, itemstack, i, j);
         drawItems.renderItemOverlayIntoGUI(fontRenderer, renderEngine, itemstack, i, j);
      } catch (Exception var8) {
         StringWriter sw = new StringWriter();
         var8.printStackTrace(new PrintWriter(sw));
         String stackTrace = itemstack + sw.toString();
         if(!stackTraces.contains(stackTrace)) {
            System.err.println("Error while rendering: " + itemstack);
            var8.printStackTrace();
            stackTraces.add(stackTrace);
         }

         if(Tessellator.instance.isDrawing) {
            Tessellator.instance.draw();
         }

         drawItems.renderItemIntoGUI(fontRenderer, renderEngine, new ItemStack(51, 1, 0), i, j);
      }

      drawItems.zLevel -= 100.0F;
      enable2DRender();
      if(Tessellator.instance.isDrawing) {
         Tessellator.instance.draw();
      }

   }

   public void setColouredItemRender(boolean enable) {
      drawItems.renderWithColor = !enable;
   }

   public static void enable3DRender() {
      GL11.glEnable(2896);
      GL11.glEnable(2929);
   }

   public static void enable2DRender() {
      GL11.glDisable(2896);
      GL11.glDisable(2929);
   }

   public void bindTexture(String s) {
      this.window.mc.renderEngine.bindTexture(s);
   }

   public void load() {
      Iterator var2 = objectHandlers.iterator();

      while(var2.hasNext()) {
         IContainerObjectHandler objectHandler = (IContainerObjectHandler)var2.next();
         objectHandler.load(this.window);
      }

   }

   public void refresh() {
      Iterator var2 = objectHandlers.iterator();

      while(var2.hasNext()) {
         IContainerObjectHandler objectHandler = (IContainerObjectHandler)var2.next();
         objectHandler.guiTick(this.window);
      }

   }

   public void guiTick() {
      Iterator var2 = objectHandlers.iterator();

      while(var2.hasNext()) {
         IContainerObjectHandler objectHandler = (IContainerObjectHandler)var2.next();
         objectHandler.guiTick(this.window);
      }

   }

   public boolean lastKeyTyped(int keyID, char keyChar) {
      Iterator var4 = inputHandlers.iterator();

      while(var4.hasNext()) {
         IContainerInputHandler inputhander = (IContainerInputHandler)var4.next();
         if(inputhander.lastKeyTyped(this.window, keyChar, keyID)) {
            return true;
         }
      }

      return false;
   }

   public boolean firstKeyTyped(int keyID, char keyChar) {
      Iterator var4 = inputHandlers.iterator();

      IContainerInputHandler inputhander;
      while(var4.hasNext()) {
         inputhander = (IContainerInputHandler)var4.next();
         inputhander.onKeyTyped(this.window, keyChar, keyID);
      }

      var4 = inputHandlers.iterator();

      while(var4.hasNext()) {
         inputhander = (IContainerInputHandler)var4.next();
         if(inputhander.keyTyped(this.window, keyChar, keyID)) {
            return true;
         }
      }

      return false;
   }

   public boolean mouseClicked(int mousex, int mousey, int button) {
      Iterator var5 = inputHandlers.iterator();

      IContainerInputHandler inputhander;
      while(var5.hasNext()) {
         inputhander = (IContainerInputHandler)var5.next();
         inputhander.onMouseClicked(this.window, mousex, mousey, button);
      }

      var5 = inputHandlers.iterator();

      while(var5.hasNext()) {
         inputhander = (IContainerInputHandler)var5.next();
         if(inputhander.mouseClicked(this.window, mousex, mousey, button)) {
            return true;
         }
      }

      return false;
   }

   public void mouseWheel(int scrolled) {
      Point mousepos = this.getMousePosition();
      Iterator var4 = inputHandlers.iterator();

      IContainerInputHandler inputHandler;
      while(var4.hasNext()) {
         inputHandler = (IContainerInputHandler)var4.next();
         inputHandler.onMouseScrolled(this.window, mousepos.x, mousepos.y, scrolled);
      }

      var4 = inputHandlers.iterator();

      while(var4.hasNext()) {
         inputHandler = (IContainerInputHandler)var4.next();
         if(inputHandler.mouseScrolled(this.window, mousepos.x, mousepos.y, scrolled)) {
            return;
         }
      }

   }

   public void mouseUp(int mousex, int mousey, int button) {
      Iterator var5 = inputHandlers.iterator();

      while(var5.hasNext()) {
         IContainerInputHandler inputhander = (IContainerInputHandler)var5.next();
         inputhander.onMouseUp(this.window, mousex, mousey, button);
      }

   }

   public void mouseDragged(int mousex, int mousey, int button, long heldTime) {
      Iterator var7 = inputHandlers.iterator();

      while(var7.hasNext()) {
         IContainerInputHandler inputhander = (IContainerInputHandler)var7.next();
         inputhander.onMouseDragged(this.window, mousex, mousey, button, heldTime);
      }

   }

   public void preDraw() {
      Iterator var2 = drawHandlers.iterator();

      while(var2.hasNext()) {
         IContainerDrawHandler drawHandler = (IContainerDrawHandler)var2.next();
         drawHandler.onPreDraw(this.window);
      }

   }

   public void renderObjects(int mousex, int mousey) {
      Iterator var4 = drawHandlers.iterator();

      IContainerDrawHandler drawHandler;
      while(var4.hasNext()) {
         drawHandler = (IContainerDrawHandler)var4.next();
         drawHandler.renderObjects(this.window, mousex, mousey);
      }

      var4 = drawHandlers.iterator();

      while(var4.hasNext()) {
         drawHandler = (IContainerDrawHandler)var4.next();
         drawHandler.postRenderObjects(this.window, mousex, mousey);
      }

   }

   public void renderToolTips(int mousex, int mousey) {
      List tooltip = this.window.handleTooltip(mousex, mousey, new LinkedList());

      IContainerTooltipHandler stack;
      for(Iterator var5 = tooltipHandlers.iterator(); var5.hasNext(); tooltip = stack.handleTooltipFirst(this.window, mousex, mousey, tooltip)) {
         stack = (IContainerTooltipHandler)var5.next();
      }

      if(tooltip.isEmpty() && this.shouldShowTooltip()) {
         ItemStack stack1 = this.getStackMouseOver();
         if(stack1 != null) {
            tooltip = itemDisplayNameMultiline(stack1, this.window, true);
         }

         tooltip = ((GuiContainer) this.window).handleItemTooltip(stack1, mousex, mousey, tooltip);
      }

      this.drawMultilineTip(mousex, mousey, tooltip);
   }

   public boolean shouldShowTooltip() {
      Iterator var2 = objectHandlers.iterator();

      while(var2.hasNext()) {
         IContainerObjectHandler handler = (IContainerObjectHandler)var2.next();
         if(!handler.shouldShowTooltip(this.window)) {
            return false;
         }
      }

      return this.window.mc.thePlayer.inventory.getItemStack() == null;
   }

   public void renderSlotUnderlay(Slot slot) {
      Iterator var3 = drawHandlers.iterator();

      while(var3.hasNext()) {
         IContainerDrawHandler drawHandler = (IContainerDrawHandler)var3.next();
         drawHandler.renderSlotUnderlay(this.window, slot);
      }

   }

   public void renderSlotOverlay(Slot slot) {
      Iterator var3 = drawHandlers.iterator();

      while(var3.hasNext()) {
         IContainerDrawHandler drawHandler = (IContainerDrawHandler)var3.next();
         drawHandler.renderSlotOverlay(this.window, slot);
      }

   }

   public boolean objectUnderMouse(int mousex, int mousey) {
      Iterator var4 = objectHandlers.iterator();

      while(var4.hasNext()) {
         IContainerObjectHandler objectHandler = (IContainerObjectHandler)var4.next();
         if(objectHandler.objectUnderMouse(this.window, mousex, mousey)) {
            return true;
         }
      }

      return false;
   }

   public void handleMouseClick(Slot slot, int slotIndex, int button, int modifier) {
      Iterator handler = slotClickHandlers.iterator();

      while(handler.hasNext()) {
         IContainerSlotClickHandler eventHandled = (IContainerSlotClickHandler)handler.next();
         eventHandled.beforeSlotClick(this.window, slotIndex, button, slot, modifier);
      }

      boolean eventHandled1 = false;

      Iterator var7;
      IContainerSlotClickHandler handler1;
      for(var7 = slotClickHandlers.iterator(); var7.hasNext(); eventHandled1 = handler1.handleSlotClick(this.window, slotIndex, button, slot, modifier, eventHandled1)) {
         handler1 = (IContainerSlotClickHandler)var7.next();
      }

      var7 = slotClickHandlers.iterator();

      while(var7.hasNext()) {
         handler1 = (IContainerSlotClickHandler)var7.next();
         handler1.afterSlotClick(this.window, slotIndex, button, slot, modifier);
      }

   }
}
