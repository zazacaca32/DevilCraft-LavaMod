package codechicken.nei.recipe;

import codechicken.nei.NEIClientUtils;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.ICraftingHandler;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;

public class GuiCraftingRecipe extends GuiRecipe {

   private ArrayList currenthandlers;
   private static ArrayList craftinghandlers = new ArrayList();


   private GuiCraftingRecipe(GuiContainer prevgui, ArrayList handlers) {
      super(prevgui);
      this.currenthandlers = handlers;
   }

   public static void registerRecipeHandler(ICraftingHandler handler) {
      Iterator var2 = craftinghandlers.iterator();

      while(var2.hasNext()) {
         ICraftingHandler handler1 = (ICraftingHandler)var2.next();
         if(handler1.getClass() == handler.getClass()) {
            return;
         }
      }

      craftinghandlers.add(handler);
   }

   public static boolean openRecipeGui(String outputId, Object ... results) {
      Minecraft mc = NEIClientUtils.mc();
      if(!(mc.currentScreen instanceof GuiContainer)) {
         return false;
      } else {
         GuiContainer prevscreen = (GuiContainer)mc.currentScreen;
         ArrayList handlers = new ArrayList();
         Iterator var6 = craftinghandlers.iterator();

         while(var6.hasNext()) {
            ICraftingHandler craftinghandler = (ICraftingHandler)var6.next();
            ICraftingHandler handler = craftinghandler.getRecipeHandler(outputId, results);
            if(handler.numRecipes() > 0) {
               handlers.add(handler);
            }
         }

         if(handlers.isEmpty()) {
            return false;
         } else {
            NEIClientUtils.overlayScreen(new GuiCraftingRecipe(prevscreen, handlers));
            return true;
         }
      }
   }

   public ArrayList getCurrentRecipeHandlers() {
      return this.currenthandlers;
   }
}
