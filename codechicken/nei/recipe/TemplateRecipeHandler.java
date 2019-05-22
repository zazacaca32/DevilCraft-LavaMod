package codechicken.nei.recipe;

import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.api.DefaultOverlayRenderer;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.api.IRecipeOverlayRenderer;
import codechicken.nei.api.IStackPositioner;
import codechicken.nei.forge.GuiContainerManager;
import codechicken.nei.forge.IContainerInputHandler;
import codechicken.nei.forge.IContainerTooltipHandler;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.recipe.IUsageHandler;
import codechicken.nei.recipe.RecipeInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public abstract class TemplateRecipeHandler implements ICraftingHandler, IUsageHandler {

   public int cycleticks = Math.abs((int)System.currentTimeMillis());
   public ArrayList arecipes = new ArrayList();
   public LinkedList transferRects = new LinkedList();


   static {
      GuiContainerManager.addInputHandler(new TemplateRecipeHandler.RecipeTransferRectHandler());
      GuiContainerManager.addTooltipHandler(new TemplateRecipeHandler.RecipeTransferRectHandler());
   }

   public TemplateRecipeHandler() {
      this.loadTransferRects();
      TemplateRecipeHandler.RecipeTransferRectHandler.registerRectsToGuis(this.getRecipeTransferRectGuis(), this.transferRects);
   }

   public void loadTransferRects() {}

   public void loadCraftingRecipes(String outputId, Object ... results) {
      if(outputId.equals("item")) {
         this.loadCraftingRecipes((ItemStack)results[0]);
      }

   }

   public void loadCraftingRecipes(ItemStack result) {}

   public void loadUsageRecipes(String inputId, Object ... ingredients) {
      if(inputId.equals("item")) {
         this.loadUsageRecipes((ItemStack)ingredients[0]);
      }

   }

   public void loadUsageRecipes(ItemStack ingredient) {}

   public abstract String getGuiTexture();

   public String getOverlayIdentifier() {
      return null;
   }

   public void drawExtras(GuiContainerManager gui, int recipe) {}

   public void drawProgressBar(GuiContainerManager gui, int x, int y, int tx, int ty, int w, int h, int ticks, int direction) {
      this.drawProgressBar(gui, x, y, tx, ty, w, h, (float)(this.cycleticks % ticks) / (float)ticks, direction);
   }

   public void drawProgressBar(GuiContainerManager gui, int x, int y, int tx, int ty, int w, int h, float completion, int direction) {
      if(direction > 3) {
         completion = 1.0F - completion;
         direction %= 4;
      }

      int var = (int)(completion * (float)(direction % 2 == 0?w:h));
      switch(direction) {
      case 0:
         gui.drawTexturedModalRect(x, y, tx, ty, var, h);
         break;
      case 1:
         gui.drawTexturedModalRect(x, y, tx, ty, w, var);
         break;
      case 2:
         gui.drawTexturedModalRect(x + w - var, y, tx + w - var, ty, var, h);
         break;
      case 3:
         gui.drawTexturedModalRect(x, y + h - var, tx, ty + h - var, w, var);
      }

   }

   public List getRecipeTransferRectGuis() {
      Class clazz = this.getGuiClass();
      if(clazz != null) {
         LinkedList list = new LinkedList();
         list.add(clazz);
         return list;
      } else {
         return null;
      }
   }

   public Class getGuiClass() {
      return null;
   }

   public TemplateRecipeHandler newInstance() {
      try {
         return (TemplateRecipeHandler)this.getClass().newInstance();
      } catch (Exception var2) {
         throw new RuntimeException(var2);
      }
   }

   public ICraftingHandler getRecipeHandler(String outputId, Object ... results) {
      TemplateRecipeHandler handler = this.newInstance();
      handler.loadCraftingRecipes(outputId, results);
      return handler;
   }

   public IUsageHandler getUsageHandler(String inputId, Object ... ingredients) {
      TemplateRecipeHandler handler = this.newInstance();
      handler.loadUsageRecipes(inputId, ingredients);
      return handler;
   }

   public int numRecipes() {
      return this.arecipes.size();
   }

   public void drawBackground(GuiContainerManager gui, int recipe) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      gui.bindTexture(this.getGuiTexture());
      gui.drawTexturedModalRect(0, 0, 5, 11, 166, 65);
   }

   public void drawForeground(GuiContainerManager gui, int recipe) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2896);
      gui.bindTexture(this.getGuiTexture());
      this.drawExtras(gui, recipe);
   }

   public ArrayList getIngredientStacks(int recipe) {
      return ((TemplateRecipeHandler.CachedRecipe)this.arecipes.get(recipe)).getIngredients();
   }

   public PositionedStack getResultStack(int recipe) {
      return ((TemplateRecipeHandler.CachedRecipe)this.arecipes.get(recipe)).getResult();
   }

   public ArrayList getOtherStacks(int recipe) {
      return ((TemplateRecipeHandler.CachedRecipe)this.arecipes.get(recipe)).getOtherStacks();
   }

   public void onUpdate() {
      if(!NEIClientUtils.shiftKey()) {
         ++this.cycleticks;
      }

   }

   public boolean hasOverlay(GuiContainer gui, Container container, int recipe) {
      return RecipeInfo.hasDefaultOverlay(gui, this.getOverlayIdentifier()) || RecipeInfo.hasOverlayHandler(gui, this.getOverlayIdentifier());
   }

   public IRecipeOverlayRenderer getOverlayRenderer(GuiContainer gui, int recipe) {
      IStackPositioner positioner = RecipeInfo.getStackPositioner(gui, this.getOverlayIdentifier());
      return positioner == null?null:new DefaultOverlayRenderer(this.getIngredientStacks(recipe), positioner);
   }

   public IOverlayHandler getOverlayHandler(GuiContainer gui, int recipe) {
      return RecipeInfo.getOverlayHandler(gui, this.getOverlayIdentifier());
   }

   public int recipiesPerPage() {
      return 2;
   }

   public List handleTooltip(GuiRecipe gui, List currenttip, int recipe) {
      if(((GuiContainerManager) gui.manager).shouldShowTooltip() && currenttip.size() == 0) {
         Point offset = gui.getRecipePosition(recipe);
         currenttip = transferRectTooltip(gui, this.transferRects, offset.x, offset.y, currenttip);
      }

      return currenttip;
   }

   public List handleItemTooltip(GuiRecipe gui, ItemStack stack, List currenttip, int recipe) {
      return currenttip;
   }

   public boolean keyTyped(GuiRecipe gui, char keyChar, int keyCode, int recipe) {
      return keyCode == NEIClientConfig.getKeyBinding("recipe")?this.transferRect(gui, recipe, false):(keyCode == NEIClientConfig.getKeyBinding("usage")?this.transferRect(gui, recipe, true):false);
   }

   public boolean mouseClicked(GuiRecipe gui, int button, int recipe) {
      return button == 0?this.transferRect(gui, recipe, false):(button == 1?this.transferRect(gui, recipe, true):false);
   }

   private boolean transferRect(GuiRecipe gui, int recipe, boolean usage) {
      Point offset = gui.getRecipePosition(recipe);
      return transferRect(gui, this.transferRects, offset.x, offset.y, usage);
   }

   private static boolean transferRect(GuiContainer gui, Collection transferRects, int offsetx, int offsety, boolean usage) {
      Point pos = ((GuiContainerManager) gui.manager).getMousePosition();
      Point relMouse = new Point(pos.x - gui.guiLeft - offsetx, pos.y - gui.guiTop - offsety);
      Iterator var8 = transferRects.iterator();

      while(true) {
         TemplateRecipeHandler.RecipeTransferRect rect;
         do {
            if(!var8.hasNext()) {
               return false;
            }

            rect = (TemplateRecipeHandler.RecipeTransferRect)var8.next();
         } while(!rect.rect.contains(relMouse));

         if(usage) {
            if(GuiUsageRecipe.openRecipeGui(rect.outputId, rect.results)) {
               break;
            }
         } else if(GuiCraftingRecipe.openRecipeGui(rect.outputId, rect.results)) {
            break;
         }
      }

      return true;
   }

   private static List transferRectTooltip(GuiContainer gui, Collection transferRects, int offsetx, int offsety, List currenttip) {
      Point pos = ((GuiContainerManager) gui.manager).getMousePosition();
      Point relMouse = new Point(pos.x - gui.guiLeft - offsetx, pos.y - gui.guiTop - offsety);
      Iterator var8 = transferRects.iterator();

      while(var8.hasNext()) {
         TemplateRecipeHandler.RecipeTransferRect rect = (TemplateRecipeHandler.RecipeTransferRect)var8.next();
         if(rect.rect.contains(relMouse)) {
            currenttip.add("Рецепты");
            break;
         }
      }

      return currenttip;
   }

   public static class RecipeTransferRectHandler implements IContainerInputHandler, IContainerTooltipHandler {

      private static HashMap guiMap = new HashMap();


      public static void registerRectsToGuis(List classes, List rects) {
         if(classes != null) {
            HashSet set;
            for(Iterator var3 = classes.iterator(); var3.hasNext(); set.addAll(rects)) {
               Class clazz = (Class)var3.next();
               set = (HashSet)guiMap.get(clazz);
               if(set == null) {
                  set = new HashSet();
                  guiMap.put(clazz, set);
               }
            }

         }
      }

      public boolean canHandle(GuiContainer gui) {
         return guiMap.containsKey(gui.getClass());
      }

      public boolean lastKeyTyped(GuiContainer gui, char keyChar, int keyCode) {
         return !this.canHandle(gui)?false:(keyCode == NEIClientConfig.getKeyBinding("recipe")?this.transferRect(gui, false):(keyCode == NEIClientConfig.getKeyBinding("usage")?this.transferRect(gui, true):false));
      }

      public boolean mouseClicked(GuiContainer gui, int mousex, int mousey, int button) {
         return !this.canHandle(gui)?false:(button == 0?this.transferRect(gui, false):(button == 1?this.transferRect(gui, true):false));
      }

      private boolean transferRect(GuiContainer gui, boolean usage) {
         int[] offset = RecipeInfo.getGuiOffset(gui);
         return TemplateRecipeHandler.transferRect(gui, (Collection)guiMap.get(gui.getClass()), offset[0], offset[1], usage);
      }

      public void onKeyTyped(GuiContainer gui, char keyChar, int keyID) {}

      public void onMouseClicked(GuiContainer gui, int mousex, int mousey, int button) {}

      public void onMouseUp(GuiContainer gui, int mousex, int mousey, int button) {}

      public boolean keyTyped(GuiContainer gui, char keyChar, int keyID) {
         return false;
      }

      public boolean mouseScrolled(GuiContainer gui, int mousex, int mousey, int scrolled) {
         return false;
      }

      public void onMouseScrolled(GuiContainer gui, int mousex, int mousey, int scrolled) {}

      public List handleTooltipFirst(GuiContainer gui, int mousex, int mousey, List currenttip) {
         if(!this.canHandle(gui)) {
            return currenttip;
         } else {
            if(((GuiContainerManager) gui.manager).shouldShowTooltip() && currenttip.size() == 0) {
               int[] offset = RecipeInfo.getGuiOffset(gui);
               currenttip = TemplateRecipeHandler.transferRectTooltip(gui, (Collection)guiMap.get(gui.getClass()), offset[0], offset[1], currenttip);
            }

            return currenttip;
         }
      }

      public List handleItemTooltip(GuiContainer gui, ItemStack itemstack, List currenttip) {
         return currenttip;
      }

      public void onMouseDragged(GuiContainer gui, int mousex, int mousey, int button, long heldTime) {}
   }

   public static class RecipeTransferRect {

      Rectangle rect;
      String outputId;
      Object[] results;


      @Deprecated
      public RecipeTransferRect(TemplateRecipeHandler handler, Rectangle rectangle, String outputId, Object ... results) {
         this(rectangle, outputId, results);
      }

      public RecipeTransferRect(Rectangle rectangle, String outputId, Object ... results) {
         this.rect = rectangle;
         this.outputId = outputId;
         this.results = results;
      }

      public boolean equals(Object obj) {
         return !(obj instanceof TemplateRecipeHandler.RecipeTransferRect)?false:this.rect.equals(((TemplateRecipeHandler.RecipeTransferRect)obj).rect);
      }

      public int hashCode() {
         return this.rect.hashCode();
      }
   }

   public abstract class CachedRecipe {

      final long offset = System.currentTimeMillis();


      public abstract PositionedStack getResult();

      public ArrayList getIngredients() {
         ArrayList stacks = new ArrayList();
         PositionedStack stack = this.getIngredient();
         if(stack != null) {
            stacks.add(stack);
         }

         return stacks;
      }

      public PositionedStack getIngredient() {
         return null;
      }

      public ArrayList getOtherStacks() {
         ArrayList stacks = new ArrayList();
         PositionedStack stack = this.getOtherStack();
         if(stack != null) {
            stacks.add(stack);
         }

         return stacks;
      }

      public PositionedStack getOtherStack() {
         return null;
      }

      public ArrayList getCycledIngredients(int cycle, ArrayList ingredients) {
         for(int itemIndex = 0; itemIndex < ingredients.size(); ++itemIndex) {
            PositionedStack stack = (PositionedStack)ingredients.get(itemIndex);
            Random rand = new Random((long)(cycle + itemIndex) + this.offset);
            stack.setPermutationToRender(Math.abs(rand.nextInt()) % stack.items.length);
         }

         return ingredients;
      }

      public void setIngredientPermutation(Collection ingredients, ItemStack ingredient) {
         Iterator var4 = ingredients.iterator();

         while(var4.hasNext()) {
            PositionedStack stack = (PositionedStack)var4.next();

            for(int i = 0; i < stack.items.length; ++i) {
               if(NEIServerUtils.areStacksSameTypeCrafting(ingredient, stack.items[i])) {
                  stack.item = stack.items[i];
                  stack.item.setItemDamage(ingredient.getItemDamage());
                  stack.items = new ItemStack[]{stack.item};
                  stack.setPermutationToRender(0);
                  break;
               }
            }
         }

      }

      public boolean contains(Collection ingredients, ItemStack ingredient) {
         Iterator var4 = ingredients.iterator();

         while(var4.hasNext()) {
            PositionedStack stack = (PositionedStack)var4.next();
            ItemStack[] var8 = stack.items;
            int var7 = stack.items.length;

            for(int var6 = 0; var6 < var7; ++var6) {
               ItemStack item = var8[var6];
               if(NEIServerUtils.areStacksSameTypeCrafting(item, ingredient)) {
                  return true;
               }
            }
         }

         return false;
      }
   }
}
