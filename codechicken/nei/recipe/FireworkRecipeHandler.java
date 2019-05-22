package codechicken.nei.recipe;

import codechicken.nei.InventoryCraftingDummy;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.forge.GuiContainerManager;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.ShapelessRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeFireworks;
import net.minecraft.world.World;

public class FireworkRecipeHandler extends ShapelessRecipeHandler {

   public String[] tooltips = new String[]{"Можно Добавлять Красители Для Подбора  Цвета ", "Перекраска Уберет Цвета В Заряде", "Несколько Зарядов в Ракете Добавят Количество Взрывов"};
   private InventoryCrafting inventoryCrafting = new InventoryCraftingDummy();
   private RecipeFireworks recipeFireworks = new RecipeFireworks();
   public ArrayList mfireworks = new ArrayList();


   public FireworkRecipeHandler() {
      super.stackorder = new int[][]{new int[2], {1, 0}, {2, 0}, {0, 1}, {1, 1}, {2, 1}, {0, 2}, {1, 2}, {2, 2}};
      this.loadAllFireworks();
   }

   private void loadAllFireworks() {
      Item[] shapes = new Item[]{null, Item.fireballCharge, Item.goldNugget, Item.feather, Item.skull};
      Item[] effects = new Item[]{null, Item.diamond, Item.lightStoneDust};
      Item[] var6 = shapes;
      int var5 = shapes.length;

      for(int var4 = 0; var4 < var5; ++var4) {
         Item charge = var6[var4];
         Item[] var10 = effects;
         int var9 = effects.length;

         for(int var8 = 0; var8 < var9; ++var8) {
            Item effect = var10[var8];
            this.genRecipe(new Object[]{Item.gunpowder, charge, effect, Item.dyePowder, Item.dyePowder, Integer.valueOf(0)});
         }
      }

      this.genRecipe(new Object[]{Item.gunpowder, Item.paper, Item.fireworkCharge, Integer.valueOf(2)});
      this.genRecipe(new Object[]{Item.gunpowder, Item.gunpowder, Item.paper, Item.fireworkCharge, Integer.valueOf(2)});
      this.genRecipe(new Object[]{Item.gunpowder, Item.gunpowder, Item.gunpowder, Item.paper, Item.fireworkCharge, Integer.valueOf(2)});

      for(int var11 = 0; var11 < 9; ++var11) {
         this.inventoryCrafting.setInventorySlotContents(var11, (ItemStack)null);
      }

      this.inventoryCrafting.setInventorySlotContents(0, new ItemStack(Item.gunpowder));
      this.inventoryCrafting.setInventorySlotContents(1, new ItemStack(Item.dyePowder));
      this.recipeFireworks.matches(this.inventoryCrafting, (World)null);
      ItemStack var12 = this.recipeFireworks.getCraftingResult((InventoryCrafting)null);
      this.genRecipe(new Object[]{var12, Item.dyePowder, Item.dyePowder, Integer.valueOf(1)});
   }

   private void genRecipe(Object ... params) {
      int numIngreds = 0;

      int ingreds;
      for(ingreds = 0; ingreds < params.length - 2; ++ingreds) {
         if(params[ingreds] != null) {
            ++numIngreds;
         }
      }

      for(ingreds = 0; ingreds < params.length - 1; ++ingreds) {
         if(params[ingreds] instanceof Item) {
            params[ingreds] = new ItemStack((Item)params[ingreds], 1, -1);
         }
      }

      Object[] var6 = new Object[numIngreds];
      int i = 0;

      for(int j = 0; i < params.length - 2; ++i) {
         if(params[i] != null) {
            var6[j++] = params[i];
         }
      }

      this.mfireworks.add(new FireworkRecipeHandler.CachedFireworkRecipe(var6, params[params.length - 2], ((Integer)params[params.length - 1]).intValue()));
   }

   public void loadCraftingRecipes(ItemStack result) {
      Iterator var3 = this.mfireworks.iterator();

      while(var3.hasNext()) {
         FireworkRecipeHandler.CachedFireworkRecipe recipe = (FireworkRecipeHandler.CachedFireworkRecipe)var3.next();
         if(recipe.result.item.itemID == result.itemID) {
            recipe.cycle();
            super.arecipes.add(recipe);
         }
      }

   }

   public void loadCraftingRecipes(String outputId, Object ... results) {
      if(outputId.equals("crafting") && this.getClass() == FireworkRecipeHandler.class) {
         super.arecipes.addAll(this.mfireworks);
      } else {
         super.loadCraftingRecipes(outputId, results);
      }

   }

   public void loadUsageRecipes(ItemStack ingredient) {
      Iterator var3 = this.mfireworks.iterator();

      while(var3.hasNext()) {
         FireworkRecipeHandler.CachedFireworkRecipe recipe = (FireworkRecipeHandler.CachedFireworkRecipe)var3.next();
         if(recipe.contains(recipe.ingredients, ingredient)) {
            recipe.cycle();
            super.arecipes.add(recipe);
         }
      }

   }

   public void onUpdate() {
      if(!NEIClientUtils.shiftKey()) {
         ++super.cycleticks;
         if(super.cycleticks % 20 == 0) {
            Iterator var2 = super.arecipes.iterator();

            while(var2.hasNext()) {
               TemplateRecipeHandler.CachedRecipe crecipe = (TemplateRecipeHandler.CachedRecipe)var2.next();
               ((FireworkRecipeHandler.CachedFireworkRecipe)crecipe).cycle();
            }
         }
      }

   }

   public String getRecipeName() {
      return "Фейерверк";
   }

   public List handleTooltip(GuiRecipe gui, List currenttip, int recipe) {
      currenttip = super.handleTooltip(gui, currenttip, recipe);
      Point mousepos = ((GuiContainerManager) gui.manager).getMousePosition();
      Point relMouse = new Point(mousepos.x - gui.guiLeft, mousepos.y - gui.guiTop);
      Point recipepos = gui.getRecipePosition(recipe);
      if(currenttip.isEmpty() && ((GuiContainerManager) gui.manager).getStackMouseOver() == null && (new Rectangle(recipepos.x, recipepos.y, 166, 55)).contains(relMouse)) {
         currenttip.add(this.tooltips[((FireworkRecipeHandler.CachedFireworkRecipe)super.arecipes.get(recipe)).recipeType]);
      }

      return currenttip;
   }

   public class CachedFireworkRecipe extends ShapelessRecipeHandler.CachedShapelessRecipe {

      LinkedList itemList = new LinkedList();
      public Object[] baseIngredients;
      public Object extraIngred;
      public int recipeType;


      public CachedFireworkRecipe(Object[] base, Object extra, int type) {
         super(new ItemStack(Item.firework));
         this.baseIngredients = base;
         this.extraIngred = extra;
         this.recipeType = type;
         this.cycle();
      }

      public void cycle() {
         this.itemList.clear();
         Object[] var4 = this.baseIngredients;
         int i = this.baseIngredients.length;

         int ingreds;
         for(ingreds = 0; ingreds < i; ++ingreds) {
            Object extras = var4[ingreds];
            this.itemList.add(extras);
         }

         int var5 = FireworkRecipeHandler.super.cycleticks / 40 % (10 - this.itemList.size());

         for(ingreds = 0; ingreds < var5; ++ingreds) {
            this.itemList.add(this.extraIngred);
         }

         this.setIngredients(this.itemList);
         ArrayList var6 = this.getIngredients();

         for(i = 0; i < 9; ++i) {
            FireworkRecipeHandler.this.inventoryCrafting.setInventorySlotContents(i, i < var6.size()?((PositionedStack)var6.get(i)).item:null);
         }

         if(!FireworkRecipeHandler.this.recipeFireworks.matches(FireworkRecipeHandler.this.inventoryCrafting, (World)null)) {
            throw new RuntimeException("Invalid Recipe?");
         } else {
            this.setResult(FireworkRecipeHandler.this.recipeFireworks.getCraftingResult((InventoryCrafting)null));
         }
      }
   }
}
