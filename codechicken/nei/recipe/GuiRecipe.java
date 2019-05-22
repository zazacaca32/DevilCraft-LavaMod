package codechicken.nei.recipe;

import codechicken.nei.GuiNEIButton;
import codechicken.nei.LayoutManager;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.api.IGuiContainerOverlay;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.api.IRecipeOverlayRenderer;
import codechicken.nei.forge.GuiContainerManager;
import codechicken.nei.recipe.ContainerRecipe;
import codechicken.nei.recipe.IRecipeHandler;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public abstract class GuiRecipe extends GuiContainer implements IGuiContainerOverlay {

   public int page;
   public int recipetype;
   public ContainerRecipe slotcontainer;
   public GuiContainer firstGui;
   public GuiContainer prevGui;
   public GuiButton nextpage;
   public GuiButton prevpage;
   public GuiButton overlay1;
   public GuiButton overlay2;
   public ArrayList currenthandlers = new ArrayList();


   protected GuiRecipe(GuiContainer prevgui) {
      super(new ContainerRecipe());
      this.slotcontainer = (ContainerRecipe)super.inventorySlots;
      this.prevGui = prevgui;
      this.firstGui = prevgui;
      if(prevgui instanceof IGuiContainerOverlay) {
         this.firstGui = ((IGuiContainerOverlay)prevgui).getFirstScreen();
      }

   }

   public boolean isClientOnly() {
      return true;
   }

   public void initGui() {
      super.initGui();
      this.currenthandlers = this.getCurrentRecipeHandlers();
      GuiNEIButton nexttype = new GuiNEIButton(0, super.width / 2 - 70, (super.height - super.ySize) / 2 + 3, 13, 12, "<");
      GuiNEIButton prevtype = new GuiNEIButton(1, super.width / 2 + 57, (super.height - super.ySize) / 2 + 3, 13, 12, ">");
      this.nextpage = new GuiNEIButton(2, super.width / 2 - 70, (super.height + super.ySize) / 2 - 18, 13, 12, "<");
      this.prevpage = new GuiNEIButton(3, super.width / 2 + 57, (super.height + super.ySize) / 2 - 18, 13, 12, ">");
      this.overlay1 = new GuiNEIButton(4, super.width / 2 + 65, (super.height - super.ySize) / 2 + 63, 13, 12, "?");
      this.overlay2 = new GuiNEIButton(5, super.width / 2 + 65, (super.height - super.ySize) / 2 + 128, 13, 12, "?");
      super.buttonList.add(nexttype);
      super.buttonList.add(prevtype);
      super.buttonList.add(this.nextpage);
      super.buttonList.add(this.prevpage);
      super.buttonList.add(this.overlay1);
      super.buttonList.add(this.overlay2);
      if(this.currenthandlers.size() == 1) {
         nexttype.drawButton = false;
         prevtype.drawButton = false;
      }

      this.refreshPage();
   }

   public void keyTyped(char c, int i) {
      if(i == 1) {
         this.firstGui.refresh();
         super.mc.displayGuiScreen(this.firstGui);
      } else if(!((GuiContainerManager) super.manager).lastKeyTyped(i, c)) {
         IRecipeHandler recipehandler = (IRecipeHandler)this.currenthandlers.get(this.recipetype);

         for(int recipe = this.page * recipehandler.recipiesPerPage(); recipe < recipehandler.numRecipes() && recipe < (this.page + 1) * recipehandler.recipiesPerPage(); ++recipe) {
            if(recipehandler.keyTyped(this, c, i, recipe)) {
               return;
            }
         }

         if(i == super.mc.gameSettings.keyBindInventory.keyCode) {
            this.firstGui.refresh();
            super.mc.displayGuiScreen(this.firstGui);
         } else if(i == NEIClientConfig.getKeyBinding("back")) {
            this.firstGui.refresh();
            super.mc.displayGuiScreen(this.prevGui);
         }

      }
   }

   protected void mouseClicked(int par1, int par2, int par3) {
      IRecipeHandler recipehandler = (IRecipeHandler)this.currenthandlers.get(this.recipetype);

      for(int recipe = this.page * recipehandler.recipiesPerPage(); recipe < recipehandler.numRecipes() && recipe < (this.page + 1) * recipehandler.recipiesPerPage(); ++recipe) {
         if(recipehandler.mouseClicked(this, par3, recipe)) {
            return;
         }
      }

      super.mouseClicked(par1, par2, par3);
   }

   protected void actionPerformed(GuiButton guibutton) {
      super.actionPerformed(guibutton);
      switch(guibutton.id) {
      case 0:
         this.prevType();
         break;
      case 1:
         this.nextType();
         break;
      case 2:
         this.prevPage();
         break;
      case 3:
         this.nextPage();
         break;
      case 4:
         this.overlayRecipe(this.page * ((IRecipeHandler)this.currenthandlers.get(this.recipetype)).recipiesPerPage());
         break;
      case 5:
         this.overlayRecipe(this.page * ((IRecipeHandler)this.currenthandlers.get(this.recipetype)).recipiesPerPage() + 1);
      }

   }

   public void updateScreen() {
      super.updateScreen();
      ((IRecipeHandler)this.currenthandlers.get(this.recipetype)).onUpdate();
      this.refreshPage();
   }

   public List handleTooltip(int mousex, int mousey, List currenttip) {
      IRecipeHandler recipehandler = (IRecipeHandler)this.currenthandlers.get(this.recipetype);

      for(int i = this.page * recipehandler.recipiesPerPage(); i < recipehandler.numRecipes() && i < (this.page + 1) * recipehandler.recipiesPerPage(); ++i) {
         currenttip = recipehandler.handleTooltip(this, currenttip, i);
      }

      return currenttip;
   }

   public List handleItemTooltip(ItemStack stack, int mousex, int mousey, List currenttip) {
      IRecipeHandler recipehandler = (IRecipeHandler)this.currenthandlers.get(this.recipetype);

      for(int i = this.page * recipehandler.recipiesPerPage(); i < recipehandler.numRecipes() && i < (this.page + 1) * recipehandler.recipiesPerPage(); ++i) {
         currenttip = recipehandler.handleItemTooltip(this, stack, currenttip, i);
      }

      return currenttip;
   }

   private void nextPage() {
      ++this.page;
      if(this.page > (((IRecipeHandler)this.currenthandlers.get(this.recipetype)).numRecipes() - 1) / ((IRecipeHandler)this.currenthandlers.get(this.recipetype)).recipiesPerPage()) {
         this.page = 0;
      }

   }

   private void prevPage() {
      --this.page;
      if(this.page < 0) {
         this.page = (((IRecipeHandler)this.currenthandlers.get(this.recipetype)).numRecipes() - 1) / ((IRecipeHandler)this.currenthandlers.get(this.recipetype)).recipiesPerPage();
      }

   }

   private void nextType() {
      ++this.recipetype;
      if(this.recipetype >= this.currenthandlers.size()) {
         this.recipetype = 0;
      }

      this.page = 0;
   }

   private void prevType() {
      --this.recipetype;
      if(this.recipetype < 0) {
         this.recipetype = this.currenthandlers.size() - 1;
      }

      this.page = 0;
   }

   private void overlayRecipe(int recipe) {
      IRecipeOverlayRenderer renderer = ((IRecipeHandler)this.currenthandlers.get(this.recipetype)).getOverlayRenderer(this.firstGui, recipe);
      IOverlayHandler handler = ((IRecipeHandler)this.currenthandlers.get(this.recipetype)).getOverlayHandler(this.firstGui, recipe);
      boolean shift = NEIClientUtils.shiftKey();
      if(handler != null && (renderer == null || shift)) {
         this.firstGui.refresh();
         super.mc.displayGuiScreen(this.firstGui);
         handler.overlayRecipe(this.firstGui, (IRecipeHandler)this.currenthandlers.get(this.recipetype), recipe, shift);
      } else if(renderer != null && (handler == null || !shift)) {
         this.firstGui.refresh();
         super.mc.displayGuiScreen(this.firstGui);
         LayoutManager.overlayRenderer = renderer;
      }

   }

   public void refreshPage() {
      this.refreshSlots();
      IRecipeHandler handler = (IRecipeHandler)this.currenthandlers.get(this.recipetype);
      boolean multiplepages = handler.numRecipes() > handler.recipiesPerPage();
      this.nextpage.drawButton = multiplepages;
      this.prevpage.drawButton = multiplepages;
      GuiContainer mainInv = this.getFirstScreen();
      this.overlay1.yPosition = (super.height - super.ySize) / 2 + (handler.recipiesPerPage() == 2?63:128);
      this.overlay1.drawButton = handler.hasOverlay(mainInv, mainInv.inventorySlots, this.page * handler.recipiesPerPage());
      this.overlay2.drawButton = handler.recipiesPerPage() == 2 && this.page * handler.recipiesPerPage() + 1 < handler.numRecipes() && handler.hasOverlay(mainInv, mainInv.inventorySlots, this.page * handler.recipiesPerPage() + 1);
   }

   private void refreshSlots() {
      this.slotcontainer.inventorySlots.clear();
      IRecipeHandler recipehandler = (IRecipeHandler)this.currenthandlers.get(this.recipetype);

      for(int i = this.page * recipehandler.recipiesPerPage(); i < recipehandler.numRecipes() && i < (this.page + 1) * recipehandler.recipiesPerPage(); ++i) {
         Point p = this.getRecipePosition(i);
         ArrayList stacks = recipehandler.getIngredientStacks(i);
         Iterator var6 = stacks.iterator();

         PositionedStack result;
         while(var6.hasNext()) {
            result = (PositionedStack)var6.next();
            this.slotcontainer.addSlot(result, p.x, p.y);
         }

         stacks = recipehandler.getOtherStacks(i);
         var6 = stacks.iterator();

         while(var6.hasNext()) {
            result = (PositionedStack)var6.next();
            this.slotcontainer.addSlot(result, p.x, p.y);
         }

         result = recipehandler.getResultStack(i);
         if(result != null) {
            this.slotcontainer.addSlot(result, p.x, p.y);
         }
      }

   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      GuiContainerManager.enable2DRender();
      IRecipeHandler recipehandler = (IRecipeHandler)this.currenthandlers.get(this.recipetype);
      String s = recipehandler.getRecipeName();
      super.fontRenderer.drawString(s, (super.xSize - super.fontRenderer.getStringWidth(s)) / 2, 5, 4210752);
      s = "Стр. " + (this.page + 1) + "/" + ((((IRecipeHandler)this.currenthandlers.get(this.recipetype)).numRecipes() - 1) / recipehandler.recipiesPerPage() + 1);
      super.fontRenderer.drawString(s, (super.xSize - super.fontRenderer.getStringWidth(s)) / 2, super.ySize - 16, 4210752);
      GL11.glPushMatrix();
      GL11.glTranslatef(5.0F, 16.0F, 0.0F);

      for(int i = this.page * recipehandler.recipiesPerPage(); i < recipehandler.numRecipes() && i < (this.page + 1) * recipehandler.recipiesPerPage(); ++i) {
         recipehandler.drawForeground((GuiContainerManager) super.manager, i);
         GL11.glTranslatef(0.0F, 65.0F, 0.0F);
      }

      GL11.glPopMatrix();
   }

   protected void drawGuiContainerBackgroundLayer(float f, int mx, int my) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture("/codechicken/nei/recipebg.png");
      int j = (super.width - super.xSize) / 2;
      int k = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
      GL11.glPushMatrix();
      GL11.glTranslatef((float)(j + 5), (float)(k + 16), 0.0F);
      IRecipeHandler recipehandler = (IRecipeHandler)this.currenthandlers.get(this.recipetype);

      for(int i = this.page * recipehandler.recipiesPerPage(); i < recipehandler.numRecipes() && i < (this.page + 1) * recipehandler.recipiesPerPage(); ++i) {
         recipehandler.drawBackground((GuiContainerManager) super.manager, i);
         GL11.glTranslatef(0.0F, 65.0F, 0.0F);
      }

      GL11.glPopMatrix();
   }

   public GuiContainer getFirstScreen() {
      return this.firstGui;
   }

   public boolean isMouseOver(PositionedStack stack, int recipe) {
      Slot stackSlot = this.slotcontainer.getSlotWithStack(stack, this.getRecipePosition(recipe).x, this.getRecipePosition(recipe).y);
      Point mousepos = ((GuiContainerManager) super.manager).getMousePosition();
      Slot mouseoverSlot = this.getSlotAtPosition(mousepos.x, mousepos.y);
      return stackSlot == mouseoverSlot;
   }

   public Point getRecipePosition(int recipe) {
      return new Point(5, 16 + recipe % ((IRecipeHandler)this.currenthandlers.get(this.recipetype)).recipiesPerPage() * 65);
   }

   public abstract ArrayList getCurrentRecipeHandlers();
}
