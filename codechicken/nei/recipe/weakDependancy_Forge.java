package codechicken.nei.recipe;

import codechicken.core.ReflectionManager;
import codechicken.nei.SpawnerRenderer;
import codechicken.nei.WorldOverlayRenderer;
import codechicken.nei.recipe.ShapedRecipeHandler;
import codechicken.nei.recipe.ShapedRecipeHandler.CachedShapedRecipe;
import codechicken.nei.recipe.ShapelessRecipeHandler;
import codechicken.nei.recipe.ShapelessRecipeHandler.CachedShapelessRecipe;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class weakDependancy_Forge {

   public static void addMobSpawnerRenderer() {
      MinecraftForgeClient.registerItemRenderer(Block.mobSpawner.blockID, new SpawnerRenderer());
   }

   public static boolean recipeInstanceShaped(IRecipe irecipe) {
      return irecipe instanceof ShapedOreRecipe;
   }

   public static boolean recipeInstanceShapeless(IRecipe irecipe) {
      return irecipe instanceof ShapelessOreRecipe;
   }

   public static CachedShapedRecipe getShapedRecipe(ShapedRecipeHandler handler, IRecipe irecipe) {
      ShapedOreRecipe recipe = (ShapedOreRecipe)irecipe;

      int width;
      int height;
      Object[] items;
      try {
         width = ((Integer)ReflectionManager.getField(ShapedOreRecipe.class, Integer.class, recipe, 4)).intValue();
         height = ((Integer)ReflectionManager.getField(ShapedOreRecipe.class, Integer.class, recipe, 5)).intValue();
         items = (Object[])ReflectionManager.getField(ShapedOreRecipe.class, Object[].class, recipe, 3);
      } catch (Exception var7) {
         var7.printStackTrace();
         return null;
      }

      for(int i = 0; i < items.length; ++i) {
         if(items[i] instanceof List && ((List)items[i]).isEmpty()) {
            return null;
         }
      }


      handler.getClass();
      CachedShapedRecipe var10000 = null;
	return var10000;
   }

   public static ShapelessRecipeHandler.CachedShapelessRecipe getShapelessRecipe(ShapelessRecipeHandler handler, IRecipe irecipe) {
      ShapelessOreRecipe recipe = (ShapelessOreRecipe)irecipe;

      ArrayList items;
      try {
         items = (ArrayList)ReflectionManager.getField(ShapelessOreRecipe.class, ArrayList.class, recipe, 1);
      } catch (Exception var5) {
         var5.printStackTrace();
         return null;
      }

      for(int i = 0; i < items.size(); ++i) {
         if(items.get(i) instanceof List && ((List)items.get(i)).isEmpty()) {
            return null;
         }
      }

      
      handler.getClass();

      CachedShapelessRecipe var10000 = null;
	return var10000;
   }

   public static void load() {
      MinecraftForge.EVENT_BUS.register(new WorldOverlayRenderer());
   }
}
