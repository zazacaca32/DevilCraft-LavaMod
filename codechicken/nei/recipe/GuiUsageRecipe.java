package codechicken.nei.recipe;

import codechicken.nei.NEIClientUtils;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.IUsageHandler;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;

public class GuiUsageRecipe extends GuiRecipe {

   private ArrayList currenthandlers;
   private static ArrayList usagehandlers = new ArrayList();


   private GuiUsageRecipe(GuiContainer prevgui, ArrayList handlers) {
      super(prevgui);
      this.currenthandlers = handlers;
   }

   public static void registerUsageHandler(IUsageHandler handler) {
      Iterator var2 = usagehandlers.iterator();

      while(var2.hasNext()) {
         IUsageHandler handler1 = (IUsageHandler)var2.next();
         if(handler1.getClass() == handler.getClass()) {
            return;
         }
      }

      usagehandlers.add(handler);
   }

   public static boolean openRecipeGui(String inputId, Object ... ingredients) {
      Minecraft mc = NEIClientUtils.mc();
      if(!(mc.currentScreen instanceof GuiContainer)) {
         return false;
      } else {
         GuiContainer prevscreen = (GuiContainer)mc.currentScreen;
         ArrayList handlers = new ArrayList();
         Iterator var6 = usagehandlers.iterator();

         while(var6.hasNext()) {
            IUsageHandler usagehandler = (IUsageHandler)var6.next();
            IUsageHandler handler = usagehandler.getUsageHandler(inputId, ingredients);
            if(handler.numRecipes() > 0) {
               handlers.add(handler);
            }
         }

         if(handlers.isEmpty()) {
            return false;
         } else {
            NEIClientUtils.overlayScreen(new GuiUsageRecipe(prevscreen, handlers));
            return true;
         }
      }
   }

   public ArrayList getCurrentRecipeHandlers() {
      return this.currenthandlers;
   }
}
