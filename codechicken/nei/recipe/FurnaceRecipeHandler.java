package codechicken.nei.recipe;

import codechicken.core.ReflectionManager;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.forge.GuiContainerManager;
import codechicken.nei.recipe.TemplateRecipeHandler;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class FurnaceRecipeHandler extends TemplateRecipeHandler {

   public static ArrayList afuels;
   public static TreeSet efuels;


   static {
      removeFuels();
      findFuels();
   }

   public void loadTransferRects() {
      super.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(50, 23, 18, 18), "fuel", new Object[0]));
      super.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(74, 23, 24, 18), "smelting", new Object[0]));
   }

   public Class getGuiClass() {
      return GuiFurnace.class;
   }

   public String getRecipeName() {
      return "Печь";
   }

   public void loadCraftingRecipes(String outputId, Object ... results) {
      if(outputId.equals("smelting") && this.getClass() == FurnaceRecipeHandler.class) {
         HashMap metarecipes = null;

         HashMap recipes;
         try {
            recipes = (HashMap)ReflectionManager.getField(FurnaceRecipes.class, HashMap.class, FurnaceRecipes.smelting(), 1);

            try {
               metarecipes = (HashMap)ReflectionManager.getField(FurnaceRecipes.class, HashMap.class, FurnaceRecipes.smelting(), 3);
            } catch (ArrayIndexOutOfBoundsException var8) {
               ;
            }
         } catch (Exception var9) {
            var9.printStackTrace();
            return;
         }

         Iterator var6 = recipes.entrySet().iterator();

         Entry recipe;
         ItemStack item;
         while(var6.hasNext()) {
            recipe = (Entry)var6.next();
            item = (ItemStack)recipe.getValue();
            super.arecipes.add(new FurnaceRecipeHandler.SmeltingPair(new ItemStack(((Integer)recipe.getKey()).intValue(), 1, -1), item));
         }

         if(metarecipes == null) {
            return;
         }

         var6 = metarecipes.entrySet().iterator();

         while(var6.hasNext()) {
            recipe = (Entry)var6.next();
            item = (ItemStack)recipe.getValue();
            super.arecipes.add(new FurnaceRecipeHandler.SmeltingPair(new ItemStack(((Integer)((List)recipe.getKey()).get(0)).intValue(), 1, ((Integer)((List)recipe.getKey()).get(1)).intValue()), item));
         }
      } else {
         super.loadCraftingRecipes(outputId, results);
      }

   }

   public void loadCraftingRecipes(ItemStack result) {
      HashMap metarecipes = null;

      HashMap recipes;
      try {
         recipes = (HashMap)ReflectionManager.getField(FurnaceRecipes.class, HashMap.class, FurnaceRecipes.smelting(), 1);

         try {
            metarecipes = (HashMap)ReflectionManager.getField(FurnaceRecipes.class, HashMap.class, FurnaceRecipes.smelting(), 3);
         } catch (ArrayIndexOutOfBoundsException var7) {
            ;
         }
      } catch (Exception var8) {
         var8.printStackTrace();
         return;
      }

      Iterator var5 = recipes.entrySet().iterator();

      Entry recipe;
      ItemStack item;
      while(var5.hasNext()) {
         recipe = (Entry)var5.next();
         item = (ItemStack)recipe.getValue();
         if(NEIServerUtils.areStacksSameType(item, result)) {
            super.arecipes.add(new FurnaceRecipeHandler.SmeltingPair(new ItemStack(((Integer)recipe.getKey()).intValue(), 1, -1), item));
         }
      }

      if(metarecipes != null) {
         var5 = metarecipes.entrySet().iterator();

         while(var5.hasNext()) {
            recipe = (Entry)var5.next();
            item = (ItemStack)recipe.getValue();
            if(NEIServerUtils.areStacksSameType(item, result)) {
               super.arecipes.add(new FurnaceRecipeHandler.SmeltingPair(new ItemStack(((Integer)((List)recipe.getKey()).get(0)).intValue(), 1, ((Integer)((List)recipe.getKey()).get(1)).intValue()), item));
            }
         }

      }
   }

   public void loadUsageRecipes(String inputId, Object ... ingredients) {
      if(inputId.equals("fuel") && this.getClass() == FurnaceRecipeHandler.class) {
         this.loadCraftingRecipes("smelting", new Object[0]);
      } else {
         super.loadUsageRecipes(inputId, ingredients);
      }

   }

   public void loadUsageRecipes(ItemStack ingredient) {
      HashMap metarecipes = null;

      HashMap recipes;
      try {
         recipes = (HashMap)ReflectionManager.getField(FurnaceRecipes.class, HashMap.class, FurnaceRecipes.smelting(), 1);

         try {
            metarecipes = (HashMap)ReflectionManager.getField(FurnaceRecipes.class, HashMap.class, FurnaceRecipes.smelting(), 3);
         } catch (ArrayIndexOutOfBoundsException var7) {
            ;
         }
      } catch (Exception var8) {
         var8.printStackTrace();
         return;
      }

      Iterator var5 = recipes.entrySet().iterator();

      Entry recipe;
      ItemStack item;
      while(var5.hasNext()) {
         recipe = (Entry)var5.next();
         item = (ItemStack)recipe.getValue();
         if(ingredient.itemID == ((Integer)recipe.getKey()).intValue()) {
            super.arecipes.add(new FurnaceRecipeHandler.SmeltingPair(ingredient, item));
         }
      }

      if(metarecipes != null) {
         var5 = metarecipes.entrySet().iterator();

         while(var5.hasNext()) {
            recipe = (Entry)var5.next();
            item = (ItemStack)recipe.getValue();
            if(ingredient.itemID == ((Integer)((List)recipe.getKey()).get(0)).intValue() && ingredient.getItemDamage() == ((Integer)((List)recipe.getKey()).get(1)).intValue()) {
               super.arecipes.add(new FurnaceRecipeHandler.SmeltingPair(ingredient, item));
            }
         }

      }
   }

   public String getGuiTexture() {
      return "/gui/furnace.png";
   }

   public void drawExtras(GuiContainerManager gui, int recipe) {
      this.drawProgressBar(gui, 51, 25, 176, 0, 14, 14, 48, 7);
      this.drawProgressBar(gui, 74, 23, 176, 14, 24, 16, 48, 0);
   }

   private static void removeFuels() {
      efuels = new TreeSet();
      efuels.add(Integer.valueOf(Block.mushroomCapBrown.blockID));
      efuels.add(Integer.valueOf(Block.mushroomCapRed.blockID));
      efuels.add(Integer.valueOf(Block.signPost.blockID));
      efuels.add(Integer.valueOf(Block.signWall.blockID));
      efuels.add(Integer.valueOf(Block.doorWood.blockID));
      efuels.add(Integer.valueOf(Block.lockedChest.blockID));
   }

   private static void findFuels() {
      afuels = new ArrayList();
      Item[] var3 = Item.itemsList;
      int var2 = Item.itemsList.length;

      for(int var1 = 0; var1 < var2; ++var1) {
         Item item = var3[var1];
         if(item != null && !efuels.contains(Integer.valueOf(item.itemID))) {
            int burnTime = TileEntityFurnace.getItemBurnTime(new ItemStack(item, 1));
            if(burnTime > 0) {
               afuels.add(new FurnaceRecipeHandler.FuelPair(new ItemStack(item, 1), burnTime));

               for(int i = 1; i < 16; ++i) {
                  ItemStack stack = new ItemStack(item, 1, i);
                  if(!NEIClientUtils.isValidItem(stack)) {
                     break;
                  }

                  afuels.add(new FurnaceRecipeHandler.FuelPair(stack, burnTime));
               }
            }
         }
      }

   }

   public String getOverlayIdentifier() {
      return "smelting";
   }

   public class SmeltingPair extends TemplateRecipeHandler.CachedRecipe {

      PositionedStack ingred;
      PositionedStack result;


      public SmeltingPair(ItemStack ingred, ItemStack result) {
         super();
         ingred.stackSize = 1;
         this.ingred = new PositionedStack(ingred, 51, 6);
         this.result = new PositionedStack(result, 111, 24);
      }

      public PositionedStack getIngredient() {
         int cycle = FurnaceRecipeHandler.super.cycleticks / 48;
         if(this.ingred.item.getItemDamage() != -1) {
            return this.ingred;
         } else {
            PositionedStack stack = this.ingred.copy();
            int maxDamage = 0;

            do {
               ++maxDamage;
               stack.item.setItemDamage(maxDamage);
            } while(NEIClientUtils.isValidItem(stack.item));

            stack.item.setItemDamage(cycle % maxDamage);
            return stack;
         }
      }

      public PositionedStack getResult() {
         return this.result;
      }

      public PositionedStack getOtherStack() {
         return ((FurnaceRecipeHandler.FuelPair)FurnaceRecipeHandler.afuels.get(FurnaceRecipeHandler.super.cycleticks / 48 % FurnaceRecipeHandler.afuels.size())).stack;
      }
   }

   public static class FuelPair {

      public PositionedStack stack;
      public int burnTime;


      public FuelPair(ItemStack ingred, int burnTime) {
         this.stack = new PositionedStack(ingred, 51, 42);
         this.burnTime = burnTime;
      }
   }
}
