package codechicken.nei.recipe;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerRecipe extends Container {

   private ContainerRecipe.RecipeInventory recipeInventory = new ContainerRecipe.RecipeInventory((ContainerRecipe.RecipeInventory)null);


   public void clearInventory() {
      super.inventoryItemStacks.clear();
      super.inventorySlots.clear();
   }

   public ItemStack slotClick(int slot, int button, boolean flag, EntityPlayer entityplayer) {
      if(slot < 0) {
         return null;
      } else {
         ItemStack stack = this.recipeInventory.getStackInSlot(slot);
         if(stack != null) {
            if(button == 0) {
               GuiCraftingRecipe.openRecipeGui("item", new Object[]{stack});
            } else if(button == 1) {
               GuiUsageRecipe.openRecipeGui("item", new Object[]{stack});
            }
         }

         return null;
      }
   }

   public void addSlot(PositionedStack stack, int recipex, int recipey) {
      final int slot = super.inventorySlots.size();
      this.addSlotToContainer(new Slot(this.recipeInventory, slot, recipex + stack.relx, recipey + stack.rely) {
         public boolean isItemValid(ItemStack par1ItemStack) {
            return false;
         }
      });
      this.recipeInventory.setInventorySlotContents(slot, stack.item);
   }

   public Slot getSlotWithStack(PositionedStack stack, int recipex, int recipey) {
      for(int i = 0; i < super.inventorySlots.size(); ++i) {
         Slot slot = (Slot)super.inventorySlots.get(i);
         if(slot.xDisplayPosition == stack.relx + recipex && slot.yDisplayPosition == stack.rely + recipey) {
            return slot;
         }
      }

      return null;
   }

   public boolean canInteractWith(EntityPlayer entityplayer) {
      return true;
   }

   public void putStackInSlot(int par1, ItemStack par2ItemStack) {}

   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
      return null;
   }

   private class RecipeInventory implements IInventory {

      private RecipeInventory() {}

      public boolean isUseableByPlayer(EntityPlayer entityplayer) {
         return true;
      }

      public int getSizeInventory() {
         return ContainerRecipe.super.inventorySlots.size();
      }

      public ItemStack getStackInSlot(int i) {
         return i >= 0 && i <= ContainerRecipe.super.inventoryItemStacks.size()?(ItemStack)ContainerRecipe.super.inventoryItemStacks.get(i):null;
      }

      public ItemStack decrStackSize(int i, int j) {
         return null;
      }

      public void setInventorySlotContents(int slot, ItemStack itemstack) {
         if(slot >= 0 && slot < ContainerRecipe.super.inventoryItemStacks.size()) {
            ContainerRecipe.super.inventoryItemStacks.set(slot, itemstack);
         }
      }

      public String getInvName() {
         return null;
      }

      public int getInventoryStackLimit() {
         return 1;
      }

      public void onInventoryChanged() {}

      public void openChest() {}

      public void closeChest() {}

      public ItemStack getStackInSlotOnClosing(int var1) {
         return null;
      }

      public boolean isStackValidForSlot(int i, ItemStack itemstack) {
         return false;
      }

      public boolean isInvNameLocalized() {
         return false;
      }

      // $FF: synthetic method
      RecipeInventory(ContainerRecipe.RecipeInventory var2) {
         this();
      }
   }
}
