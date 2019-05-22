package codechicken.core.gui;

import codechicken.core.gui.GuiWidget;
import codechicken.core.gui.IGuiActionListener;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiScreenWidget extends GuiScreen implements IGuiActionListener {

   public ArrayList widgets;
   public int xSize;
   public int ySize;
   public int guiTop;
   public int guiLeft;


   public GuiScreenWidget() {
      this(176, 166);
   }

   public GuiScreenWidget(int xSize, int ySize) {
      this.widgets = new ArrayList();
      this.xSize = xSize;
      this.ySize = ySize;
   }

   public void initGui() {
      this.guiTop = (super.height - this.ySize) / 2;
      this.guiLeft = (super.width - this.xSize) / 2;
   }

   public void reset() {
      this.initGui();
      this.widgets.clear();
      this.addWidgets();
   }

   public void setWorldAndResolution(Minecraft mc, int i, int j) {
      boolean init = super.mc == null;
      super.setWorldAndResolution(mc, i, j);
      if(init) {
         this.addWidgets();
      }

   }

   public void add(GuiWidget widget) {
      this.widgets.add(widget);
      widget.onAdded(this);
   }

   public void drawScreen(int mousex, int mousey, float f) {
      GL11.glTranslated((double)this.guiLeft, (double)this.guiTop, 0.0D);
      this.drawBackground();
      Iterator var5 = this.widgets.iterator();

      while(var5.hasNext()) {
         GuiWidget widget = (GuiWidget)var5.next();
         widget.draw(mousex - this.guiLeft, mousey - this.guiTop, f);
      }

      this.drawForeground();
      GL11.glTranslated((double)(-this.guiLeft), (double)(-this.guiTop), 0.0D);
   }

   public void drawBackground() {}

   public void drawForeground() {}

   protected void mouseClicked(int x, int y, int button) {
      super.mouseClicked(x, y, button);
      Iterator var5 = this.widgets.iterator();

      while(var5.hasNext()) {
         GuiWidget widget = (GuiWidget)var5.next();
         widget.mouseClicked(x - this.guiLeft, y - this.guiTop, button);
      }

   }

   protected void mouseMovedOrUp(int x, int y, int button) {
      super.mouseMovedOrUp(x, y, button);
      Iterator var5 = this.widgets.iterator();

      while(var5.hasNext()) {
         GuiWidget widget = (GuiWidget)var5.next();
         widget.mouseMovedOrUp(x - this.guiLeft, y - this.guiTop, button);
      }

   }

   protected void func_85041_a(int x, int y, int button, long time) {
      super.func_85041_a(x, y, button, time);
      Iterator var7 = this.widgets.iterator();

      while(var7.hasNext()) {
         GuiWidget widget = (GuiWidget)var7.next();
         widget.mouseDragged(x - this.guiLeft, y - this.guiTop, button, time);
      }

   }

   public void updateScreen() {
      super.updateScreen();
      if(super.mc.currentScreen == this) {
         Iterator var2 = this.widgets.iterator();

         while(var2.hasNext()) {
            GuiWidget widget = (GuiWidget)var2.next();
            widget.update();
         }
      }

   }

   public void keyTyped(char c, int keycode) {
      super.keyTyped(c, keycode);
      Iterator var4 = this.widgets.iterator();

      while(var4.hasNext()) {
         GuiWidget widget = (GuiWidget)var4.next();
         widget.keyTyped(c, keycode);
      }

   }

   public void handleMouseInput() {
      super.handleMouseInput();
      int i = Mouse.getEventDWheel();
      if(i != 0) {
         Point p = this.getMousePosition();
         int scroll = i > 0?1:-1;
         Iterator var5 = this.widgets.iterator();

         while(var5.hasNext()) {
            GuiWidget widget = (GuiWidget)var5.next();
            widget.mouseScrolled(p.x, p.y, scroll);
         }
      }

   }

   public Point getMousePosition() {
      ScaledResolution scaledresolution = new ScaledResolution(super.mc.gameSettings, super.mc.displayWidth, super.mc.displayHeight);
      int w = scaledresolution.getScaledWidth();
      int h = scaledresolution.getScaledHeight();
      return new Point(Mouse.getX() * w / super.mc.displayWidth, h - Mouse.getY() * h / super.mc.displayHeight - 1);
   }

   public void actionPerformed(String ident, Object ... params) {}

   public void addWidgets() {}
}
