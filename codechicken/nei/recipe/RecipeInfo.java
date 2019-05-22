package codechicken.nei.recipe;

import codechicken.nei.OffsetPositioner;
import codechicken.nei.api.API;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.api.IStackPositioner;
import codechicken.nei.recipe.BrewingOverlayHandler;
import codechicken.nei.recipe.BrewingRecipeHandler;
import codechicken.nei.recipe.DefaultOverlayHandler;
import codechicken.nei.recipe.FireworkRecipeHandler;
import codechicken.nei.recipe.FuelRecipeHandler;
import codechicken.nei.recipe.FurnaceRecipeHandler;
import codechicken.nei.recipe.ShapedRecipeHandler;
import codechicken.nei.recipe.ShapelessRecipeHandler;
import com.google.common.base.Objects;
import java.util.HashMap;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.gui.inventory.GuiInventory;

public class RecipeInfo {

   static HashMap overlayMap = new HashMap();
   static HashMap positionerMap = new HashMap();
   static HashMap offsets = new HashMap();


   public static void registerOverlayHandler(Class classz, IOverlayHandler handler, String ident) {
      overlayMap.put(new RecipeInfo.OverlayKey(classz, ident), handler);
   }

   public static void registerGuiOverlay(Class classz, String ident, IStackPositioner positioner) {
      positionerMap.put(new RecipeInfo.OverlayKey(classz, ident), positioner);
      if(positioner instanceof OffsetPositioner && !offsets.containsKey(classz)) {
         OffsetPositioner p = (OffsetPositioner)positioner;
         setGuiOffset(classz, p.offsetx, p.offsety);
      }

   }

   public static void setGuiOffset(Class classz, int x, int y) {
      offsets.put(classz, new int[]{x, y});
   }

   public static boolean hasDefaultOverlay(GuiContainer gui, String ident) {
      return positionerMap.containsKey(new RecipeInfo.OverlayKey(gui.getClass(), ident));
   }

   public static boolean hasOverlayHandler(GuiContainer gui, String ident) {
      return overlayMap.containsKey(new RecipeInfo.OverlayKey(gui.getClass(), ident));
   }

   public static IOverlayHandler getOverlayHandler(GuiContainer gui, String ident) {
      return (IOverlayHandler)overlayMap.get(new RecipeInfo.OverlayKey(gui.getClass(), ident));
   }

   public static IStackPositioner getStackPositioner(GuiContainer gui, String ident) {
      return (IStackPositioner)positionerMap.get(new RecipeInfo.OverlayKey(gui.getClass(), ident));
   }

   public static int[] getGuiOffset(GuiContainer gui) {
      int[] offset = (int[])offsets.get(gui.getClass());
      return offset == null?new int[]{5, 11}:offset;
   }

   public static void load() {
      API.registerRecipeHandler(new ShapedRecipeHandler());
      API.registerUsageHandler(new ShapedRecipeHandler());
      API.registerRecipeHandler(new ShapelessRecipeHandler());
      API.registerUsageHandler(new ShapelessRecipeHandler());
      API.registerRecipeHandler(new FireworkRecipeHandler());
      API.registerUsageHandler(new FireworkRecipeHandler());
      API.registerRecipeHandler(new FurnaceRecipeHandler());
      API.registerUsageHandler(new FurnaceRecipeHandler());
      API.registerRecipeHandler(new BrewingRecipeHandler());
      API.registerUsageHandler(new BrewingRecipeHandler());
      API.registerRecipeHandler(new FuelRecipeHandler());
      API.registerUsageHandler(new FuelRecipeHandler());
      API.registerGuiOverlay(GuiCrafting.class, "crafting");
      API.registerGuiOverlay(GuiInventory.class, "crafting2x2", 63, 20);
      API.registerGuiOverlay(GuiFurnace.class, "smelting");
      API.registerGuiOverlay(GuiFurnace.class, "fuel");
      API.registerGuiOverlay(GuiBrewingStand.class, "brewing");
      API.registerGuiOverlayHandler(GuiCrafting.class, new DefaultOverlayHandler(), "crafting");
      API.registerGuiOverlayHandler(GuiBrewingStand.class, new BrewingOverlayHandler(), "brewing");
   }

   private static class OverlayKey {

      String ident;
      Class guiClass;


      public OverlayKey(Class classz, String ident) {
         this.guiClass = classz;
         this.ident = ident;
      }

      public boolean equals(Object obj) {
         if(!(obj instanceof RecipeInfo.OverlayKey)) {
            return false;
         } else {
            RecipeInfo.OverlayKey key = (RecipeInfo.OverlayKey)obj;
            return Objects.equal(this.ident, key.ident) && this.guiClass == key.guiClass;
         }
      }

      public int hashCode() {
         return Objects.hashCode(new Object[]{this.ident, this.guiClass});
      }
   }
}
