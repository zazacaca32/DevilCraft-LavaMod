package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockBase;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TileEntityBlockTurning extends TileEntityBlockBase implements ITankContainer {

   public int tickWaterTime = 0;
   public int tickCookTime = 0;
   private long tick = 0L;
   public int speedcrystalTime = 7200;
   public final int FullWatertank = 16000;
   ItemStack start = null;
   public int kSpeed = 0;
   public int kDisk = 1;
   public LiquidStack mLiquid = new LiquidStack(0, 0, 0);
   private int whatdisk = 0;


   public TileEntityBlockTurning() {
      super.ItemStacks = new ItemStack[6];
      super.RecipesIndex = Manager.INSTANCE.getIndex(this.getClass());
   }

   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readFromNBT(par1NBTTagCompound);
      this.tickWaterTime = par1NBTTagCompound.getInteger("twt");
      this.tickCookTime = par1NBTTagCompound.getInteger("CookTime");
      this.speedcrystalTime = par1NBTTagCompound.getInteger("ct");
      int liqid = par1NBTTagCompound.getInteger("liqid");
      this.mLiquid = new LiquidStack(liqid, this.tickWaterTime);
      this.kSpeed = par1NBTTagCompound.getInteger("ks");
      this.kDisk = par1NBTTagCompound.getInteger("dsk");
   }

   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setInteger("twt", this.tickWaterTime);
      par1NBTTagCompound.setInteger("CookTime", this.tickCookTime);
      par1NBTTagCompound.setInteger("ct", this.speedcrystalTime);
      par1NBTTagCompound.setInteger("liqid", this.mLiquid.itemID);
      par1NBTTagCompound.setInteger("ks", this.kSpeed);
      par1NBTTagCompound.setInteger("dsk", this.kDisk);
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   @SideOnly(Side.CLIENT)
   public float getCookProgressScaled(int par1) {
      return (float)this.tickCookTime * 100.0F / (float)this.speedcrystalTime;
   }

   @SideOnly(Side.CLIENT)
   public int getWaterTimeRemainingScaled(int par1) {
      return this.tickWaterTime * par1 / 16000;
   }

   @SideOnly(Side.CLIENT)
   public int getBurnTimeR() {
      return this.tickCookTime;
   }

   public boolean isWaterning() {
      return this.mLiquid.amount > 0;
   }

   public void updateEntity() {
      super.updateEntity();
      if(Utils.isServer()) {
         long tick = this.tick + 1L;
         this.tick = tick;
         if(tick >= 40L) {
            this.tick = 0L;
            boolean isLubricated = this.isWaterning();
            boolean stateChangedLastCycle = false;

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

               if(super.ItemStacks[0] != null && super.ItemStacks[4] == null && (this.mLiquid.amount == 0 || this.mLiquid.itemID == Block.waterStill.blockID && super.ItemStacks[0].itemID == 326)) {
                  ItemStack var821 = ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getSlotAsCraftItem(super.ItemStacks[0]);
                  if(this.mLiquid.amount == 0) {
                     this.mLiquid = new LiquidStack(Block.waterStill, 1000);
                     this.tickWaterTime = 1000;
                     this.kSpeed = 1;
                  } else if(this.tickWaterTime + 1000 <= 16000) {
                     this.tickWaterTime += 1000;
                     LiquidStack mLiquid2111 = this.mLiquid;
                     mLiquid2111.amount += 1000;
                  }

                  super.ItemStacks[0] = null;
                  super.ItemStacks[4] = var821.copy();
               }

               if(this.tickWaterTime > 0 && this.canSmelt()) {
                  this.tickCookTime += this.kSpeed * this.kDisk;
                  if(this.tickCookTime >= ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getTimeBurnID(super.ItemStacks[1])) {
                     this.tickCookTime = 0;
                     this.smeltItem();
                     stateChangedLastCycle = true;
                  }
               } else {
                  this.tickCookTime = 0;
               }

               if(isLubricated != this.isWaterning()) {
                  stateChangedLastCycle = true;
               }

               if(stateChangedLastCycle) {
                  this.onInventoryChanged();
               }
            } catch (Exception var7) {
               ;
            }
         }
      }

   }

   public boolean canSmelt() {
      boolean rez = super.ItemStacks[1] != null && (super.ItemStacks[3] == null || super.ItemStacks[3].stackSize < this.getInventoryStackLimit()) && (super.ItemStacks[2] == null || super.ItemStacks[2].stackSize != this.getInventoryStackLimit());
      return rez;
   }

   public void smeltItem() {
      if(this.canSmelt()) {
         ItemStack crystal_O = ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getSlotAsCraftItem(super.ItemStacks[1]);
         ItemStack powder_O = ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(3)).getSlotItem(0);
         int countPowder = -1;

         try {
            countPowder = ((Integer)((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getSlotAsDataItem(super.ItemStacks[1])[1]).intValue();
         } catch (Exception var8) {
            ;
         }

         if(crystal_O == null || powder_O == null) {
            return;
         }

         ItemStack itemStack2;
         if(super.ItemStacks[2] == null) {
            super.ItemStacks[2] = crystal_O.copy();
         } else if(this.equalStack(super.ItemStacks[2], crystal_O)) {
            itemStack2 = super.ItemStacks[2];
            ++itemStack2.stackSize;
         }

         itemStack2 = super.ItemStacks[1];
         --itemStack2.stackSize;
         if(super.ItemStacks[1].stackSize <= 0) {
            super.ItemStacks[1] = null;
         }

         if(super.ItemStacks[3] == null) {
            super.ItemStacks[3] = powder_O.copy();
            super.ItemStacks[3].stackSize = countPowder;
         } else if(super.ItemStacks[3].isItemEqual(powder_O)) {
            ItemStack itemStack3 = super.ItemStacks[3];
            int sizeInventory;
            if(super.ItemStacks[3].stackSize + countPowder >= this.getSizeInventory()) {
               sizeInventory = this.getSizeInventory();
            } else {
               ItemStack itemStack4 = super.ItemStacks[3];
               sizeInventory = itemStack4.stackSize += countPowder;
            }

            itemStack3.stackSize = sizeInventory;
         }

         if(super.ItemStacks[5] != null && this.whatdisk != Tiny.facetingDiscIridium.itemID) {
            super.ItemStacks[5].setItemDamage(super.ItemStacks[5].getItemDamage() + 1);
            if(super.ItemStacks[5].getItemDamage() >= super.ItemStacks[5].getMaxDamage()) {
               super.decrStackSize(5, 1);
            }
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
      return "container.turning";
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }

   public LiquidStack drain(ForgeDirection dir, int arg1, boolean doDrain) {
      return null;
   }

   public LiquidStack drain(int arg0, int arg1, boolean doDrain) {
      return null;
   }

   public int fill(ForgeDirection arg0, LiquidStack lstack, boolean doFill) {
      return this.fill(0, lstack, doFill);
   }

   public int fill(int side, LiquidStack lst, boolean doFill) {
      if(lst == null) {
         return 0;
      } else {
         int portion;
         if(this.mLiquid.amount == 0) {
            if(!lst.isLiquidEqual(new LiquidStack(Block.waterStill, 1)) && !lst.isLiquidEqual(LiquidDictionary.getLiquid("Oil", 1)) && !lst.isLiquidEqual(LiquidDictionary.getLiquid("seedoil", 1))) {
               return 0;
            } else {
               portion = Math.min(lst.amount, 16000);
               if(doFill) {
                  this.mLiquid = lst.copy();
                  this.mLiquid.amount = portion;
                  this.tickWaterTime = portion;
               }

               if(this.mLiquid.itemID == Block.waterStill.blockID) {
                  this.kSpeed = 1;
               } else if(this.mLiquid.isLiquidEqual(LiquidDictionary.getLiquid("Oil", 1))) {
                  this.kSpeed = 2;
               } else {
                  this.kSpeed = 4;
               }

               return portion;
            }
         } else if(lst.isLiquidEqual(this.mLiquid)) {
            portion = Math.min(lst.amount, 16000 - this.mLiquid.amount);
            if(doFill) {
               LiquidStack mLiquid = this.mLiquid;
               mLiquid.amount += portion;
               this.tickWaterTime += portion;
            }

            return portion;
         } else {
            return 0;
         }
      }
   }

   public ILiquidTank getTank(ForgeDirection dir, LiquidStack liq) {
      return liq == null?null:(!liq.containsLiquid(new LiquidStack(Block.waterStill, 1)) && !liq.containsLiquid(LiquidDictionary.getLiquid("Oil", 1))?null:this.getTanks(ForgeDirection.UNKNOWN)[0]);
   }

   public ILiquidTank[] getTanks(ForgeDirection arg0) {
      LiquidTank tank = new LiquidTank(this.mLiquid.copy(), 16000, this);
      tank.setTankPressure(0);
      return (ILiquidTank[])(new LiquidTank[]{tank});
   }
}
