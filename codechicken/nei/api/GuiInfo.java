package codechicken.nei.api;

import codechicken.nei.NEIChestGuiHandler;
import codechicken.nei.NEICreativeGuiHandler;
import codechicken.nei.NEIDummySlotHandler;
import codechicken.nei.api.API;
import java.util.Iterator;
import java.util.LinkedList;
import net.minecraft.client.gui.inventory.GuiContainer;

public class GuiInfo {

   public static LinkedList guiHandlers = new LinkedList();


   public static void load() {
      API.registerNEIGuiHandler(new NEICreativeGuiHandler());
      API.registerNEIGuiHandler(new NEIChestGuiHandler());
      API.registerNEIGuiHandler(new NEIDummySlotHandler());
   }

   public static void clearGuiHandlers() {
      Iterator iterator = guiHandlers.iterator();

      while(iterator.hasNext()) {
         if(iterator.next() instanceof GuiContainer) {
            iterator.remove();
         }
      }

   }
}
