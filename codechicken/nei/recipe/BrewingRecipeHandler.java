package codechicken.nei.recipe;

import codechicken.nei.MultiItemRange;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.api.API;
import codechicken.nei.forge.GuiContainerManager;
import codechicken.nei.recipe.TemplateRecipeHandler;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import org.lwjgl.opengl.GL11;

public class BrewingRecipeHandler extends TemplateRecipeHandler {

   public static final HashSet ingredientIDs = new HashSet();
   public static final HashSet apotions = new HashSet();


   public void loadTransferRects() {
      super.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(58, 3, 14, 30), "brewing", new Object[0]));
      super.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(92, 3, 14, 30), "brewing", new Object[0]));
      super.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(68, 23, 28, 18), "brewing", new Object[0]));
   }

   public Class getGuiClass() {
      return GuiBrewingStand.class;
   }

   public String getRecipeName() {
      return "Алхимия";
   }

   public void loadCraftingRecipes(String outputId, Object ... results) {
      if(outputId.equals("brewing") && this.getClass() == BrewingRecipeHandler.class) {
         Iterator var4 = apotions.iterator();

         while(var4.hasNext()) {
            BrewingRecipeHandler.CachedBrewingRecipe recipe = (BrewingRecipeHandler.CachedBrewingRecipe)var4.next();
            super.arecipes.add(recipe);
         }
      } else {
         super.loadCraftingRecipes(outputId, results);
      }

   }

   public void loadCraftingRecipes(ItemStack result) {
      if(result.itemID == Item.potion.itemID) {
         int damage = result.getItemDamage();
         Iterator var4 = apotions.iterator();

         while(var4.hasNext()) {
            BrewingRecipeHandler.CachedBrewingRecipe recipe = (BrewingRecipeHandler.CachedBrewingRecipe)var4.next();
            if(recipe.result.item.getItemDamage() == damage) {
               super.arecipes.add(recipe);
            }
         }

      }
   }

   public void loadUsageRecipes(ItemStack ingredient) {
      if(ingredient.itemID == Item.potion.itemID || ingredientIDs.contains(Integer.valueOf(ingredient.itemID))) {
         Iterator var3 = apotions.iterator();

         while(var3.hasNext()) {
            BrewingRecipeHandler.CachedBrewingRecipe recipe = (BrewingRecipeHandler.CachedBrewingRecipe)var3.next();
            if(NEIServerUtils.areStacksSameType(recipe.ingredient.item, ingredient) || NEIServerUtils.areStacksSameType(recipe.precursorPotion.item, ingredient)) {
               super.arecipes.add(recipe);
            }
         }

      }
   }

   public String getGuiTexture() {
      return "/gui/alchemy.png";
   }

   public void drawForegrounda(GuiContainerManager gui, int recipe) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      gui.bindTexture("/gui/alchemy.png");
      int t = super.cycleticks % 120 / 4;
      gui.drawTexturedModalRect(92, 5, 176, 0, 8, t + 1);
      t = super.cycleticks % 35;
      gui.drawTexturedModalRect(60, 33 - t, 185, 31 - t, 12, t - 1);
   }

   public void drawExtras(GuiContainerManager gui, int recipe) {
      this.drawProgressBar(gui, 92, 5, 176, 0, 8, 30, 120, 1);
      this.drawProgressBar(gui, 60, 1, 185, -2, 12, 30, 35, 3);
   }

   public void searchPotions() {
      TreeSet allPotions = new TreeSet();
      HashSet nextLevelPotions = new HashSet();
      nextLevelPotions.add(Integer.valueOf(0));

      HashSet positivepots;
      Iterator var7;
      do {
         positivepots = new HashSet();
         Iterator neutralpots = nextLevelPotions.iterator();

         while(neutralpots.hasNext()) {
            Integer negativepots = (Integer)neutralpots.next();
            if(!ItemPotion.isSplash(negativepots.intValue())) {
               var7 = ingredientIDs.iterator();

               while(var7.hasNext()) {
                  Integer potionID = (Integer)var7.next();
                  int effectlist = PotionHelper.applyIngredient(negativepots.intValue(), Item.itemsList[potionID.intValue()].getPotionEffect());
                  if(ItemPotion.isSplash(effectlist)) {
                     this.addPotion(potionID.intValue(), negativepots.intValue(), effectlist, allPotions, positivepots);
                  } else {
                     List type = Item.potion.getEffects(negativepots.intValue());
                     List potioneffect = Item.potion.getEffects(effectlist);
                     if((negativepots.intValue() <= 0 || type != potioneffect) && (type == null || !type.equals(potioneffect) && potioneffect != null) && negativepots.intValue() != effectlist && !this.levelModifierChanged(negativepots.intValue(), effectlist)) {
                        this.addPotion(potionID.intValue(), negativepots.intValue(), effectlist, allPotions, positivepots);
                     }
                  }
               }
            }
         }

         nextLevelPotions = positivepots;
      } while(positivepots.size() > 0);

      allPotions.add(Integer.valueOf(0));
      API.setItemDamageVariants(Item.potion.itemID, (Collection)allPotions);
      API.addSetRange("Vanilla.Предметы.Зелья", (new MultiItemRange()).add((Item)Item.potion));
      API.addSetRange("Vanilla.Предметы.Зелья.Взрывные", (new MultiItemRange()).add((Item)Item.potion, 16384, '\u8000'));
      MultiItemRange positivepots1 = new MultiItemRange();
      MultiItemRange negativepots1 = new MultiItemRange();
      MultiItemRange neutralpots1 = new MultiItemRange();
      var7 = allPotions.iterator();

      while(var7.hasNext()) {
         int potionID1 = ((Integer)var7.next()).intValue();
         List effectlist1 = Item.potion.getEffects(potionID1);
         byte type1 = 0;
         if(effectlist1 != null && !effectlist1.isEmpty()) {
            Iterator var11 = effectlist1.iterator();
            if(var11.hasNext()) {
               PotionEffect potioneffect1 = (PotionEffect)var11.next();
               if(Potion.potionTypes[potioneffect1.getPotionID()].isBadEffect()) {
                  type1 = -1;
               } else {
                  type1 = 1;
               }
            }
         }

         if(type1 == 0) {
            neutralpots1.add((Item)Item.potion, potionID1, potionID1);
         } else if(type1 == 1) {
            positivepots1.add((Item)Item.potion, potionID1, potionID1);
         } else if(type1 == -1) {
            negativepots1.add((Item)Item.potion, potionID1, potionID1);
         }
      }

      API.addSetRange("Предметы.Зелья.Позитивные", positivepots1);
      API.addSetRange("Предметы.Зелья.Негативные", negativepots1);
      API.addSetRange("Предметы.Зелья.Нейтральные", neutralpots1);
   }

   private boolean levelModifierChanged(int basePotionID, int result) {
      int basemod = basePotionID & 224;
      int resultmod = result & 224;
      return basemod != 0 && basemod != resultmod;
   }

   private void addPotion(int ingred, int basePotion, int result, TreeSet allPotions, HashSet newPotions) {
      apotions.add(new BrewingRecipeHandler.CachedBrewingRecipe(ingred, basePotion, result));
      if(allPotions.add(Integer.valueOf(result))) {
         newPotions.add(Integer.valueOf(result));
      }

   }

   public String getOverlayIdentifier() {
      return "brewing";
   }

   public class CachedBrewingRecipe extends TemplateRecipeHandler.CachedRecipe {

      int hashcode;
      PositionedStack precursorPotion;
      PositionedStack result;
      PositionedStack ingredient;


      public CachedBrewingRecipe(int ingredID, int basePotionID, int resultDamage) {
         super();
         this.precursorPotion = new PositionedStack(new ItemStack(Item.potion.itemID, 1, basePotionID), 51, 35);
         this.ingredient = new PositionedStack(new ItemStack(ingredID, 1, 0), 74, 6);
         this.result = new PositionedStack(new ItemStack(Item.potion.itemID, 1, resultDamage), 97, 35);
         this.calculateHashcode();
      }

      public PositionedStack getResult() {
         return this.result;
      }

      public ArrayList getIngredients() {
         ArrayList recipestacks = new ArrayList();
         recipestacks.add(this.ingredient);
         recipestacks.add(this.precursorPotion);
         return recipestacks;
      }

      private void calculateHashcode() {
         this.hashcode = this.result.item.getItemDamage() << 16 + this.precursorPotion.item.getItemDamage();
         this.hashcode = 31 * this.hashcode + (this.ingredient.item.itemID << 16 + this.ingredient.item.getItemDamage());
      }

      public boolean equals(Object obj) {
         if(!(obj instanceof BrewingRecipeHandler.CachedBrewingRecipe)) {
            return false;
         } else {
            BrewingRecipeHandler.CachedBrewingRecipe recipe2 = (BrewingRecipeHandler.CachedBrewingRecipe)obj;
            return this.result.item.getItemDamage() == recipe2.result.item.getItemDamage() && this.precursorPotion.item.getItemDamage() == recipe2.precursorPotion.item.getItemDamage() && NEIServerUtils.areStacksSameType(this.ingredient.item, recipe2.ingredient.item);
         }
      }

      public int hashCode() {
         return this.hashcode;
      }
   }
}
