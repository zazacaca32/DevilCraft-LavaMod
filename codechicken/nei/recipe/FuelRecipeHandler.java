package codechicken.nei.recipe;

import codechicken.core.ReflectionManager;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.FurnaceRecipeHandler;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class FuelRecipeHandler extends FurnaceRecipeHandler {

   public static ArrayList mfurnace;


   public FuelRecipeHandler() {
      this.loadAllSmelting();
   }

   public String getRecipeName() {
      return "Топливо";
   }

   public void loadAllSmelting() {
      if(mfurnace == null) {
         mfurnace = new ArrayList();
         HashMap metarecipes = null;

         HashMap recipes;
         try {
            recipes = (HashMap)ReflectionManager.getField(FurnaceRecipes.class, HashMap.class, FurnaceRecipes.smelting(), 1);

            try {
               metarecipes = (HashMap)ReflectionManager.getField(FurnaceRecipes.class, HashMap.class, FurnaceRecipes.smelting(), 3);
            } catch (ArrayIndexOutOfBoundsException var9) {
               ;
            }
         } catch (Exception var10) {
            var10.printStackTrace();
            return;
         }

         Iterator var4 = recipes.entrySet().iterator();

         Entry recipe;
         ItemStack item;
         while(var4.hasNext()) {
            recipe = (Entry)var4.next();
            item = (ItemStack)recipe.getValue();
            int ingred = ((Integer)recipe.getKey()).intValue();
            mfurnace.add(new FurnaceRecipeHandler.SmeltingPair(new ItemStack(ingred, 1, 0), item));

            for(int i = 1; i < 16; ++i) {
               ItemStack stack = new ItemStack(ingred, 1, i);
               if(!NEIClientUtils.isValidItem(stack)) {
                  break;
               }

               mfurnace.add(new FurnaceRecipeHandler.SmeltingPair(stack, item));
            }
         }

         if(metarecipes != null) {
            var4 = metarecipes.entrySet().iterator();

            while(var4.hasNext()) {
               recipe = (Entry)var4.next();
               item = (ItemStack)recipe.getValue();
               mfurnace.add(new FurnaceRecipeHandler.SmeltingPair(new ItemStack(((Integer)((List)recipe.getKey()).get(0)).intValue(), 1, ((Integer)((List)recipe.getKey()).get(1)).intValue()), item));
            }

         }
      }
   }

   public void loadCraftingRecipes(String outputId, Object ... results) {
      if(outputId.equals("fuel") && this.getClass() == FuelRecipeHandler.class) {
         Iterator var4 = FurnaceRecipeHandler.afuels.iterator();

         while(var4.hasNext()) {
            FurnaceRecipeHandler.FuelPair fuel = (FurnaceRecipeHandler.FuelPair)var4.next();
            super.arecipes.add(new FuelRecipeHandler.CachedFuelRecipe(fuel));
         }
      }

   }

   public void loadUsageRecipes(ItemStack ingredient) {
      Iterator var3 = FurnaceRecipeHandler.afuels.iterator();

      while(var3.hasNext()) {
         FurnaceRecipeHandler.FuelPair fuel = (FurnaceRecipeHandler.FuelPair)var3.next();
         if(NEIServerUtils.areStacksSameTypeCrafting(ingredient, fuel.stack.item)) {
            super.arecipes.add(new FuelRecipeHandler.CachedFuelRecipe(fuel));
         }
      }

   }

   public String getOverlayIdentifier() {
      return "fuel";
   }

   public List handleItemTooltip(GuiRecipe gui, ItemStack stack, List currenttip, int recipe) {
      FuelRecipeHandler.CachedFuelRecipe crecipe = (FuelRecipeHandler.CachedFuelRecipe)super.arecipes.get(recipe);
      FurnaceRecipeHandler.FuelPair fuel = crecipe.fuel;
      float burnTime = (float)fuel.burnTime / 200.0F;
      String burnString;
      if(gui.isMouseOver(fuel.stack, recipe) && burnTime < 1.0F) {
         burnTime = 1.0F / burnTime;
         burnString = Float.toString(burnTime);
         if(burnTime == (float)Math.round(burnTime)) {
            burnString = Integer.toString((int)burnTime);
         }

         burnString = burnString + " required";
         currenttip.add(burnString);
      } else if((gui.isMouseOver(crecipe.getResult(), recipe) || gui.isMouseOver(crecipe.getIngredient(), recipe)) && burnTime > 1.0F) {
         burnString = Float.toString(burnTime);
         if(burnTime == (float)Math.round(burnTime)) {
            burnString = Integer.toString((int)burnTime);
         }

         burnString = burnString + (gui.isMouseOver(crecipe.getResult(), recipe)?" produced":" processed");
         currenttip.add(burnString);
      }

      return currenttip;
   }

   public class CachedFuelRecipe extends TemplateRecipeHandler.CachedRecipe {

      public FurnaceRecipeHandler.FuelPair fuel;


      public CachedFuelRecipe(FurnaceRecipeHandler.FuelPair fuel) {
         super();
         this.fuel = fuel;
      }

      public PositionedStack getIngredient() {
         return ((FurnaceRecipeHandler.SmeltingPair)FuelRecipeHandler.mfurnace.get(FuelRecipeHandler.super.cycleticks / 48 % FuelRecipeHandler.mfurnace.size())).ingred;
      }

      public PositionedStack getResult() {
         return ((FurnaceRecipeHandler.SmeltingPair)FuelRecipeHandler.mfurnace.get(FuelRecipeHandler.super.cycleticks / 48 % FuelRecipeHandler.mfurnace.size())).result;
      }

      public PositionedStack getOtherStack() {
         return this.fuel.stack;
      }
   }
}
