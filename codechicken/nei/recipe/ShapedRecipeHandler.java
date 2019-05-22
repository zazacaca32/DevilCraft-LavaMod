package codechicken.nei.recipe;

import codechicken.core.ReflectionManager;
import codechicken.nei.NEICompatibility;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.RecipeInfo;
import codechicken.nei.recipe.ShapelessRecipeHandler.CachedShapelessRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;
import codechicken.nei.recipe.weakDependancy_Forge;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;

public class ShapedRecipeHandler extends TemplateRecipeHandler {

   public void loadTransferRects() {
      super.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(84, 23, 24, 18), "crafting", new Object[0]));
   }

   public Class getGuiClass() {
      return GuiCrafting.class;
   }

   public String getRecipeName() {
      return "Простой Крафт";
   }

   public void loadCraftingRecipes(String outputId, Object ... results) {
      if(outputId.equals("crafting") && this.getClass() == ShapedRecipeHandler.class) {
         List allrecipes = CraftingManager.getInstance().getRecipeList();
         Iterator var5 = allrecipes.iterator();

         while(var5.hasNext()) {
            IRecipe irecipe = (IRecipe)var5.next();
            CachedShapedRecipe recipe = null;
            if(irecipe instanceof ShapedRecipes) {
               recipe = new ShapedRecipeHandler.CachedShapedRecipe((ShapedRecipes)irecipe);
            } else if(NEICompatibility.hasForge && weakDependancy_Forge.recipeInstanceShaped(irecipe)) {
               recipe = weakDependancy_Forge.getShapedRecipe(this, irecipe);
            }

            if(recipe != null) {
               super.arecipes.add(recipe);
            }
         }
      } else {
         super.loadCraftingRecipes(outputId, results);
      }

   }

   public void loadCraftingRecipes(ItemStack result) {
      List allrecipes = CraftingManager.getInstance().getRecipeList();
      Iterator var4 = allrecipes.iterator();

      while(var4.hasNext()) {
         IRecipe irecipe = (IRecipe)var4.next();
         if(NEIServerUtils.areStacksSameTypeCrafting(irecipe.getRecipeOutput(), result)) {
            ShapedRecipeHandler.CachedShapedRecipe recipe = null;
            if(irecipe instanceof ShapedRecipes) {
               recipe = new ShapedRecipeHandler.CachedShapedRecipe((ShapedRecipes)irecipe);
            } else if(NEICompatibility.hasForge && weakDependancy_Forge.recipeInstanceShaped(irecipe)) {
               recipe = weakDependancy_Forge.getShapedRecipe(this, irecipe);
            }

            if(recipe != null) {
               super.arecipes.add(recipe);
            }
         }
      }

   }

   public void loadUsageRecipes(ItemStack ingredient) {
      List allrecipes = CraftingManager.getInstance().getRecipeList();
      Iterator var4 = allrecipes.iterator();

      while(var4.hasNext()) {
         IRecipe irecipe = (IRecipe)var4.next();
         ShapedRecipeHandler.CachedShapedRecipe recipe = null;
         if(irecipe instanceof ShapedRecipes) {
            recipe = new ShapedRecipeHandler.CachedShapedRecipe((ShapedRecipes)irecipe);
         } else if(NEICompatibility.hasForge && weakDependancy_Forge.recipeInstanceShaped(irecipe)) {
            recipe = weakDependancy_Forge.getShapedRecipe(this, irecipe);
         }

         if(recipe != null && recipe.contains(recipe.ingredients, ingredient)) {
            recipe.setIngredientPermutation(recipe.ingredients, ingredient);
            super.arecipes.add(recipe);
         }
      }

   }

   public String getGuiTexture() {
      return "/gui/crafting.png";
   }

   public String getOverlayIdentifier() {
      return "crafting";
   }

   public boolean hasOverlay(GuiContainer gui, Container container, int recipe) {
      return super.hasOverlay(gui, container, recipe) || RecipeInfo.hasDefaultOverlay(gui, "crafting2x2");
   }

   public boolean isRecipe2x2(int recipe) {
      Iterator var3 = this.getIngredientStacks(recipe).iterator();

      PositionedStack stack;
      do {
         if(!var3.hasNext()) {
            return true;
         }

         stack = (PositionedStack)var3.next();
      } while(stack.relx <= 43 && stack.rely <= 24);

      return false;
   }

   public class CachedShapedRecipe extends TemplateRecipeHandler.CachedRecipe {

      public ArrayList ingredients;
      public PositionedStack result;


      public CachedShapedRecipe(ShapedRecipes recipe) {
         super();
         this.result = new PositionedStack(recipe.getRecipeOutput(), 119, 24);
         this.ingredients = new ArrayList();
         this.setIngredients(recipe);
      }

      public CachedShapedRecipe(int width, int height, Object[] items, ItemStack out) {
         super();
         this.result = new PositionedStack(out, 119, 24);
         this.ingredients = new ArrayList();
         this.setIngredients(width, height, items);
      }

      public void setIngredients(int width, int height, Object[] items) {
         for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
               if(items[y * width + x] != null) {
                  PositionedStack stack = new PositionedStack(items[y * width + x], 25 + x * 18, 6 + y * 18);
                  stack.setMaxSize(1);
                  this.ingredients.add(stack);
               }
            }
         }

      }

      public void setIngredients(ShapedRecipes recipe) {
         int width;
         int height;
         ItemStack[] items;
         try {
            width = ((Integer)ReflectionManager.getField(ShapedRecipes.class, Integer.class, recipe, 0)).intValue();
            height = ((Integer)ReflectionManager.getField(ShapedRecipes.class, Integer.class, recipe, 1)).intValue();
            items = (ItemStack[])ReflectionManager.getField(ShapedRecipes.class, ItemStack[].class, recipe, 2);
         } catch (Exception var6) {
            var6.printStackTrace();
            return;
         }

         this.setIngredients(width, height, items);
      }

      public ArrayList getIngredients() {
         return this.getCycledIngredients(ShapedRecipeHandler.super.cycleticks / 20, this.ingredients);
      }

      public PositionedStack getResult() {
         return this.result;
      }
   }
}
