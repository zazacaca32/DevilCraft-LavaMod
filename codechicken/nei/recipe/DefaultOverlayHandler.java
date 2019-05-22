package codechicken.nei.recipe;

import codechicken.core.inventory.InventoryUtils;
import codechicken.nei.FastTransferManger;
import codechicken.nei.PositionedStack;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.recipe.IRecipeHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class DefaultOverlayHandler implements IOverlayHandler {

   int offsetx;
   int offsety;


   public DefaultOverlayHandler(int x, int y) {
      this.offsetx = x;
      this.offsety = y;
   }

   public DefaultOverlayHandler() {
      this(5, 11);
   }

   public void overlayRecipe(GuiContainer gui, IRecipeHandler recipe, int recipeIndex, boolean shift) {
      ArrayList ingredients = recipe.getIngredientStacks(recipeIndex);
      List ingredStacks = this.getPermutationIngredients(ingredients);
      this.findInventoryQuantities(gui, ingredStacks);
      List assignedIngredients = this.assignIngredients(ingredients, ingredStacks);
      if(assignedIngredients != null) {
         this.assignIngredSlots(gui, ingredients, assignedIngredients);
         int quantity = this.calculateRecipeQuantity(assignedIngredients);
         if(quantity != 0) {
            this.moveIngredients(gui, assignedIngredients, quantity);
         }

      }
   }

   private void moveIngredients(GuiContainer gui, List assignedIngredients, int quantity) {
      Iterator var5 = assignedIngredients.iterator();

      while(var5.hasNext()) {
         DefaultOverlayHandler.IngredientDistribution distrib = (DefaultOverlayHandler.IngredientDistribution)var5.next();
         ItemStack pstack = distrib.permutation;
         int transferCap = quantity * pstack.stackSize;
         int transferred = 0;
         int destSlotIndex = 0;
         Slot dest = distrib.slots[0];
         int slotTransferred = 0;
         int slotTransferCap = pstack.getMaxStackSize();
         Iterator var14 = gui.inventorySlots.inventorySlots.iterator();

         while(var14.hasNext()) {
            Slot slot = (Slot)var14.next();
            if(slot.getHasStack() && slot.inventory instanceof InventoryPlayer) {
               ItemStack stack = slot.getStack();
               if(InventoryUtils.canStack(stack, pstack)) {
                  FastTransferManger.clickSlot(gui, slot.slotNumber);
                  int amount = Math.min(transferCap - transferred, stack.stackSize);

                  for(int c = 0; c < amount; ++c) {
                     FastTransferManger.clickSlot(gui, dest.slotNumber, 1);
                     ++transferred;
                     ++slotTransferred;
                     if(slotTransferred >= slotTransferCap) {
                        ++destSlotIndex;
                        if(destSlotIndex == distrib.slots.length) {
                           dest = null;
                           break;
                        }

                        dest = distrib.slots[destSlotIndex];
                        slotTransferred = 0;
                     }
                  }

                  FastTransferManger.clickSlot(gui, slot.slotNumber);
                  if(transferred >= transferCap || dest == null) {
                     break;
                  }
               }
            }
         }
      }

   }

   private int calculateRecipeQuantity(List assignedIngredients) {
      int quantity = Integer.MAX_VALUE;

      DefaultOverlayHandler.DistributedIngred istack;
      int allSlots;
      for(Iterator var4 = assignedIngredients.iterator(); var4.hasNext(); quantity = Math.min(quantity, allSlots / istack.distributed)) {
         DefaultOverlayHandler.IngredientDistribution distrib = (DefaultOverlayHandler.IngredientDistribution)var4.next();
         istack = distrib.distrib;
         if(istack.numSlots == 0) {
            return 0;
         }

         allSlots = istack.invAmount;
         if(allSlots / istack.numSlots > istack.stack.getMaxStackSize()) {
            allSlots = istack.numSlots * istack.stack.getMaxStackSize();
         }
      }

      return quantity;
   }

   private Slot[][] assignIngredSlots(GuiContainer gui, List ingredients, List assignedIngredients) {
      Slot[][] recipeSlots = this.mapIngredSlots(gui, ingredients);
      HashMap distribution = new HashMap();

      int i;
      for(int avaliableSlots = 0; avaliableSlots < recipeSlots.length; ++avaliableSlots) {
         Slot[] i1;
         i = (i1 = recipeSlots[avaliableSlots]).length;

         for(int assignedSlots = 0; assignedSlots < i; ++assignedSlots) {
            Slot remainingIngreds = i1[assignedSlots];
            if(!distribution.containsKey(remainingIngreds)) {
               distribution.put(remainingIngreds, Integer.valueOf(-1));
            }
         }
      }

      HashSet var17 = new HashSet(distribution.keySet());
      HashSet var18 = new HashSet();
      ArrayList var19 = new ArrayList();

      for(i = 0; i < ingredients.size(); ++i) {
         var18.add(Integer.valueOf(i));
         var19.add(new LinkedList());
      }

      while(var17.size() > 0 && var18.size() > 0) {
         Iterator var20 = var18.iterator();

         while(var20.hasNext()) {
            int var21 = ((Integer)var20.next()).intValue();
            boolean assigned = false;
            DefaultOverlayHandler.DistributedIngred istack = ((DefaultOverlayHandler.IngredientDistribution)assignedIngredients.get(var21)).distrib;
            Slot[] var16;
            int var15 = (var16 = recipeSlots[var21]).length;
            int var14 = 0;

            while(true) {
               if(var14 < var15) {
                  label93: {
                     Slot slot = var16[var14];
                     if(var17.contains(slot)) {
                        var17.remove(slot);
                        if(!slot.getHasStack()) {
                           ++istack.numSlots;
                           ((LinkedList)var19.get(var21)).add(slot);
                           assigned = true;
                           break label93;
                        }
                     }

                     ++var14;
                     continue;
                  }
               }

               if(!assigned || istack.numSlots * istack.stack.getMaxStackSize() >= istack.invAmount) {
                  var20.remove();
               }
               break;
            }
         }
      }

      for(i = 0; i < ingredients.size(); ++i) {
         ((DefaultOverlayHandler.IngredientDistribution)assignedIngredients.get(i)).slots = (Slot[])((LinkedList)var19.get(i)).toArray(new Slot[0]);
      }

      return recipeSlots;
   }

   private List assignIngredients(List ingredients, List ingredStacks) {
      ArrayList assignedIngredients = new ArrayList();
      Iterator var5 = ingredients.iterator();

      while(var5.hasNext()) {
         PositionedStack posstack = (PositionedStack)var5.next();
         DefaultOverlayHandler.DistributedIngred biggestIngred = null;
         ItemStack permutation = null;
         int biggestSize = 0;
         ItemStack[] var12 = posstack.items;
         int var11 = posstack.items.length;

         for(int var10 = 0; var10 < var11; ++var10) {
            ItemStack pstack = var12[var10];

            for(int j = 0; j < ingredStacks.size(); ++j) {
               DefaultOverlayHandler.DistributedIngred istack = (DefaultOverlayHandler.DistributedIngred)ingredStacks.get(j);
               if(InventoryUtils.canStack(pstack, istack.stack) && istack.invAmount - istack.distributed >= pstack.stackSize) {
                  int relsize = (istack.invAmount - istack.invAmount / istack.recipeAmount * istack.distributed) / pstack.stackSize;
                  if(relsize > biggestSize) {
                     biggestSize = relsize;
                     biggestIngred = istack;
                     permutation = pstack;
                     break;
                  }
               }
            }
         }

         if(biggestIngred == null) {
            return null;
         }

         biggestIngred.distributed += permutation.stackSize;
         assignedIngredients.add(new DefaultOverlayHandler.IngredientDistribution(biggestIngred, permutation));
      }

      return assignedIngredients;
   }

   private void findInventoryQuantities(GuiContainer gui, List ingredStacks) {
      Iterator var4 = gui.inventorySlots.inventorySlots.iterator();

      while(var4.hasNext()) {
         Slot slot = (Slot)var4.next();
         if(slot.getHasStack() && slot.inventory instanceof InventoryPlayer) {
            ItemStack pstack = slot.getStack();
            DefaultOverlayHandler.DistributedIngred istack = this.findIngred(ingredStacks, pstack);
            if(istack != null) {
               istack.invAmount += pstack.stackSize;
            }
         }
      }

   }

   private List getPermutationIngredients(List ingredients) {
      ArrayList ingredStacks = new ArrayList();
      Iterator var4 = ingredients.iterator();

      while(var4.hasNext()) {
         PositionedStack posstack = (PositionedStack)var4.next();
         ItemStack[] var8 = posstack.items;
         int var7 = posstack.items.length;

         for(int var6 = 0; var6 < var7; ++var6) {
            ItemStack pstack = var8[var6];
            DefaultOverlayHandler.DistributedIngred istack = this.findIngred(ingredStacks, pstack);
            if(istack == null) {
               ingredStacks.add(istack = new DefaultOverlayHandler.DistributedIngred(pstack));
            }

            istack.recipeAmount += pstack.stackSize;
         }
      }

      return ingredStacks;
   }

   public Slot[][] mapIngredSlots(GuiContainer gui, List ingredients) {
      Slot[][] recipeSlotList = new Slot[ingredients.size()][];
      int i = 0;

      while(i < ingredients.size()) {
         LinkedList recipeSlots = new LinkedList();
         PositionedStack pstack = (PositionedStack)ingredients.get(i);
         Iterator var8 = gui.inventorySlots.inventorySlots.iterator();

         while(true) {
            if(var8.hasNext()) {
               Slot slot = (Slot)var8.next();
               if(slot.xDisplayPosition != pstack.relx + this.offsetx || slot.yDisplayPosition != pstack.rely + this.offsety) {
                  continue;
               }

               recipeSlots.add(slot);
            }

            recipeSlotList[i] = (Slot[])recipeSlots.toArray(new Slot[0]);
            ++i;
            break;
         }
      }

      return recipeSlotList;
   }

   public void clickSlot(GuiContainer window, int slotIndex, int button, int modifier) {
      Container container = window.inventorySlots;
      Slot slot = null;
      if(slotIndex >= 0 && slotIndex < container.inventorySlots.size()) {
         slot = container.getSlot(slotIndex);
      }

      window.handleMouseClick(slot, slotIndex, button, modifier);
   }

   public DefaultOverlayHandler.DistributedIngred findIngred(List ingredStacks, ItemStack pstack) {
      Iterator var4 = ingredStacks.iterator();

      while(var4.hasNext()) {
         DefaultOverlayHandler.DistributedIngred istack = (DefaultOverlayHandler.DistributedIngred)var4.next();
         if(InventoryUtils.canStack(pstack, istack.stack)) {
            return istack;
         }
      }

      return null;
   }

   public static class DistributedIngred {

      public ItemStack stack;
      public int invAmount;
      public int distributed;
      public int numSlots;
      public int recipeAmount;


      public DistributedIngred(ItemStack item) {
         this.stack = InventoryUtils.copyStack(item, 1);
      }
   }

   public static class IngredientDistribution {

      public DefaultOverlayHandler.DistributedIngred distrib;
      public ItemStack permutation;
      public Slot[] slots;


      public IngredientDistribution(DefaultOverlayHandler.DistributedIngred distrib, ItemStack permutation) {
         this.distrib = distrib;
         this.permutation = permutation;
      }
   }
}
