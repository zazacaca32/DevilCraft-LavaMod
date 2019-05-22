package codechicken.nei.recipe;

import codechicken.core.ReflectionManager;
import codechicken.nei.NEICompatibility;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.RecipeInfo;
import codechicken.nei.recipe.TemplateRecipeHandler;
import codechicken.nei.recipe.weakDependancy_Forge;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;

public class ShapelessRecipeHandler extends TemplateRecipeHandler {

   public int[][] stackorder = new int[][]{new int[2], {1, 0}, {0, 1}, {1, 1}, {0, 2}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};


   public void loadTransferRects() {
      super.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(84, 23, 24, 18), "crafting", new Object[0]));
   }

   public Class getGuiClass() {
      return GuiCrafting.class;
   }

   public String getRecipeName() {
      return "Расширенный Крафт";
   }

   public void loadCraftingRecipes(String outputId, Object ... results) {
      if(outputId.equals("crafting") && this.getClass() == ShapelessRecipeHandler.class) {
         List allrecipes = CraftingManager.getInstance().getRecipeList();
         Iterator var5 = allrecipes.iterator();

         while(var5.hasNext()) {
            IRecipe irecipe = (IRecipe)var5.next();
            ShapelessRecipeHandler.CachedShapelessRecipe recipe = null;
            if(irecipe instanceof ShapelessRecipes) {
               recipe = new ShapelessRecipeHandler.CachedShapelessRecipe((ShapelessRecipes)irecipe);
            } else if(NEICompatibility.hasForge && weakDependancy_Forge.recipeInstanceShapeless(irecipe)) {
               recipe = weakDependancy_Forge.getShapelessRecipe(this, irecipe);
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
            ShapelessRecipeHandler.CachedShapelessRecipe recipe = null;
            if(irecipe instanceof ShapelessRecipes) {
               recipe = new ShapelessRecipeHandler.CachedShapelessRecipe((ShapelessRecipes)irecipe);
            } else if(NEICompatibility.hasForge && weakDependancy_Forge.recipeInstanceShapeless(irecipe)) {
               recipe = weakDependancy_Forge.getShapelessRecipe(this, irecipe);
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
         ShapelessRecipeHandler.CachedShapelessRecipe recipe = null;
         if(irecipe instanceof ShapelessRecipes) {
            recipe = new ShapelessRecipeHandler.CachedShapelessRecipe((ShapelessRecipes)irecipe);
         } else if(NEICompatibility.hasForge && weakDependancy_Forge.recipeInstanceShapeless(irecipe)) {
            recipe = weakDependancy_Forge.getShapelessRecipe(this, irecipe);
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
      return this.getIngredientStacks(recipe).size() <= 4;
   }

   public class CachedShapelessRecipe extends TemplateRecipeHandler.CachedRecipe {

      public ArrayList ingredients;
      public PositionedStack result;


      public CachedShapelessRecipe() {
         super();
         this.ingredients = new ArrayList();
      }

      public CachedShapelessRecipe(ItemStack output) {
         this();
         this.setResult(output);
      }

      public CachedShapelessRecipe(ShapelessRecipes recipe) {
         this(recipe.getRecipeOutput());
         this.setIngredients(recipe);
      }

      public CachedShapelessRecipe(Object[] input, ItemStack output) {
         this(Arrays.asList(input), output);
      }

      public CachedShapelessRecipe(List input, ItemStack output) {
         this(output);
         this.setIngredients(input);
      }

      public void setIngredients(List items) {
         this.ingredients.clear();

         for(int ingred = 0; ingred < items.size(); ++ingred) {
            PositionedStack stack = new PositionedStack(items.get(ingred), 25 + ShapelessRecipeHandler.this.stackorder[ingred][0] * 18, 6 + ShapelessRecipeHandler.this.stackorder[ingred][1] * 18);
            stack.setMaxSize(1);
            this.ingredients.add(stack);
         }

      }

      public void setIngredients(ShapelessRecipes recipe) {
         ArrayList items;
         try {
            items = (ArrayList)ReflectionManager.getField(ShapelessRecipes.class, ArrayList.class, recipe, 1);
         } catch (Exception var4) {
            var4.printStackTrace();
            return;
         }

         this.setIngredients((List)items);
      }

      public void setResult(ItemStack output) {
         this.result = new PositionedStack(output, 119, 24);
      }

      public ArrayList getIngredients() {
         return this.getCycledIngredients(ShapelessRecipeHandler.super.cycleticks / 20, this.ingredients);
      }

      public PositionedStack getResult() {
         return this.result;
      }
   }
}
