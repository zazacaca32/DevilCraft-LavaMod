package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockBase;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityBlockPetDesk extends TileEntityBlockBase {

   public int tickCookTime = 0;
   private long tick = 0L;
   public int speedcrystalTime = 7200;
   ItemStack start = null;


   public TileEntityBlockPetDesk() {
      super.ItemStacks = new ItemStack[3];
      super.RecipesIndex = Manager.INSTANCE.getIndex(this.getClass());
   }

   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readFromNBT(par1NBTTagCompound);
      this.tickCookTime = par1NBTTagCompound.getInteger("CookTime");
      this.speedcrystalTime = par1NBTTagCompound.getInteger("ct");
   }

   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setInteger("CookTime", this.tickCookTime);
      par1NBTTagCompound.setInteger("ct", this.speedcrystalTime);
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   @SideOnly(Side.CLIENT)
   public float getCookProgressScaled(int par1) {
      return (float)this.tickCookTime * (float)par1 / (float)this.speedcrystalTime;
   }

   @SideOnly(Side.CLIENT)
   public int getBurnTimeR() {
      return this.tickCookTime;
   }

   public void updateEntity() {
      super.updateEntity();
      if(!super.worldObj.isRemote && ++this.tick >= 40L) {
         this.tick = 0L;
         boolean flag1 = false;

         try {
            if(this.canSmelt()) {
               if(this.start != null) {
                  if(!this.start.isItemEqual(super.ItemStacks[1])) {
                     this.speedcrystalTime = ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getTimeBurnID(super.ItemStacks[1]);
                     this.tickCookTime = 0;
                     this.start = super.ItemStacks[1].copy();
                  }
               } else {
                  this.start = super.ItemStacks[1].copy();
                  this.speedcrystalTime = ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getTimeBurnID(super.ItemStacks[1]);
               }
            }

            if(this.canSmelt()) {
               ++this.tickCookTime;
               if(this.tickCookTime >= ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getTimeBurnID(super.ItemStacks[1])) {
                  this.tickCookTime = 0;
                  this.smeltItem();
                  flag1 = true;
               }
            } else {
               this.tickCookTime = 0;
            }

            if(flag1) {
               this.onInventoryChanged();
            }
         } catch (Exception var3) {
            ;
         }
      }

   }

   private boolean canSmelt() {
      return super.ItemStacks[0] == null?false:(super.ItemStacks[1] == null?false:super.ItemStacks[2] == null);
   }

   public void smeltItem() {
      if(this.canSmelt()) {
         ItemStack result_O = ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getSlotAsCraftItem(super.ItemStacks[1]);
         if(result_O == null) {
            return;
         }

         if(super.ItemStacks[2] == null) {
            super.ItemStacks[2] = result_O.copy();
         }

         --super.ItemStacks[0].stackSize;
         if(super.ItemStacks[0].stackSize <= 0) {
            super.ItemStacks[0] = null;
         }

         --super.ItemStacks[1].stackSize;
         if(super.ItemStacks[1].stackSize <= 0) {
            super.ItemStacks[1] = null;
         }
      }

   }

   public void openChest() {}

   public void closeChest() {}

   public boolean isStackValidForSlot(int i, ItemStack itemstack) {
      try {
         return ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(i)).EqualSlotAsItemIDandDamage(itemstack);
      } catch (Exception var4) {
         return false;
      }
   }

   private boolean equalStack(ItemStack itemstack, ItemStack itemstack1) {
      return itemstack.isItemEqual(itemstack1) && ItemStack.areItemStackTagsEqual(itemstack, itemstack1);
   }

   String getInventoryName() {
      return "container.petdesk";
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }
}
