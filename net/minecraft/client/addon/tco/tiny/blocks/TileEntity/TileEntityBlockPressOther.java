package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockBase;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityBlockPressOther extends TileEntityBlockBase {

   public int tickPowerTime = 0;
   private int tick;
   public int speedreamaningTime = 1000;
   public boolean isWorking = false;
   int startItemID;
   int startItemID2;
   public byte thisRenderModel = 0;
   private double[] randomic = new double[]{40.0D, 60.0D};
   public int chance = 100;


   public TileEntityBlockPressOther() {
      super.RecipesIndex = Manager.INSTANCE.getIndex(this.getClass());
      super.ItemStacks = new ItemStack[3];
   }

   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readFromNBT(par1NBTTagCompound);
      this.tickPowerTime = par1NBTTagCompound.getInteger("tickPowerTime");
      this.speedreamaningTime = par1NBTTagCompound.getInteger("srt");
      this.isWorking = par1NBTTagCompound.getBoolean("iw");
      this.startItemID = par1NBTTagCompound.getInteger("sii");
   }

   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setInteger("tickPowerTime", this.tickPowerTime);
      par1NBTTagCompound.setInteger("srt", this.speedreamaningTime);
      par1NBTTagCompound.setBoolean("iw", this.isWorking);
      par1NBTTagCompound.setInteger("sii", this.startItemID);
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   @SideOnly(Side.CLIENT)
   public int getPowerScaled(int i) {
      if(this.tickPowerTime <= 0) {
         return 0;
      } else {
         int r = this.tickPowerTime * i / this.speedreamaningTime;
         if(r > i) {
            r = i;
         }

         return r;
      }
   }

   @SideOnly(Side.CLIENT)
   public boolean isReamaning() {
      return this.tickPowerTime > 0;
   }

   public int getrandom() {
      int sump = 0;

      for(int var61 = 0; var61 < this.randomic.length; ++var61) {
         sump += (int)this.randomic[var61];
      }

      double var6 = Math.random() * (double)sump;
      int num = this.randomic.length;

      for(int j = 0; j < this.randomic.length; ++j) {
         if(var6 <= this.randomic[j]) {
            num = j;
            break;
         }

         var6 -= this.randomic[j];
      }

      return num;
   }

   public void updateEntity() {
      super.updateEntity();
      boolean flag = false;
      if(++this.tick >= 20 && !super.worldObj.isRemote) {
         this.tick = 0;

         try {
            if(super.ItemStacks[0] != null && super.ItemStacks[1] == null && super.ItemStacks[2] != null) {
               if(!this.isWorking && ItemStack.areItemStacksEqual(super.ItemStacks[0], super.ItemStacks[2])) {
                  this.startItemID = super.ItemStacks[0].itemID;
                  this.startItemID2 = super.ItemStacks[2].itemID;
                  int var9 = ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getTimeBurnID(super.ItemStacks[0]);
                  if(var9 > 0) {
                     this.speedreamaningTime = var9;
                  }

                  this.isWorking = true;
                  this.tickPowerTime = 0;
                  flag = true;
               }

               if(this.startItemID == super.ItemStacks[0].itemID && this.startItemID2 == super.ItemStacks[2].itemID && ItemStack.areItemStacksEqual(super.ItemStacks[0], super.ItemStacks[2])) {
                  if(this.isWorking) {
                     ++this.tickPowerTime;
                     Integer var8 = (Integer)((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getSlotAsDataItemID(super.ItemStacks[0])[1];
                     this.chance = var8.intValue();
                  }
               } else {
                  this.isWorking = false;
                  this.tickPowerTime = 0;
                  flag = true;
               }
            } else {
               if(this.isWorking) {
                  this.isWorking = false;
                  flag = true;
               }

               if(this.tickPowerTime > 0) {
                  this.tickPowerTime = 0;
               }
            }

            if(this.tickPowerTime >= this.speedreamaningTime && super.ItemStacks[0] != null && super.ItemStacks[2] != null) {
               this.tickPowerTime = 0;
               if(ItemStack.areItemStacksEqual(super.ItemStacks[0], super.ItemStacks[2])) {
                  short var91 = this.getEnchantOther(super.ItemStacks[0]);
                  short oup2 = this.getEnchantOther(super.ItemStacks[2]);
                  ItemStack result = oup2 > var91?super.ItemStacks[2].copy():super.ItemStacks[0].copy();
                  if(var91 == 0) {
                     var91 = 2;
                  }

                  ItemStack itemStack = super.ItemStacks[0];
                  --itemStack.stackSize;
                  if(super.ItemStacks[0].stackSize <= 0) {
                     super.ItemStacks[0] = null;
                  }

                  ItemStack itemStack2 = super.ItemStacks[2];
                  --itemStack2.stackSize;
                  if(super.ItemStacks[2].stackSize <= 0) {
                     super.ItemStacks[2] = null;
                  }

                  if(this.incEnchantOther(var91, result)) {
                     super.ItemStacks[1] = result;
                  }

                  this.onInventoryChanged();
               }
            }
         } catch (Exception var7) {
            ;
         }
      }

   }

   public short getEnchantOther(ItemStack par1ItemStack) {
      return par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("oup")?par1ItemStack.stackTagCompound.getShort("oup"):0;
   }

   public boolean incEnchantOther(int inc, ItemStack par1ItemStack) {
      Utils.getOrCreateNbtData(par1ItemStack);
      if(par1ItemStack.stackTagCompound == null) {
         return false;
      } else if(par1ItemStack.stackTagCompound.hasKey("oup")) {
         short oup = par1ItemStack.stackTagCompound.getShort("oup");
         oup += (short)inc;
         par1ItemStack.stackTagCompound.setShort("oup", oup);
         return true;
      } else {
         par1ItemStack.stackTagCompound.setShort("oup", (short)inc);
         return true;
      }
   }

   public void openChest() {}

   public void closeChest() {}

   public boolean isStackValidForSlot(int i, ItemStack itemstack) {
      return false;
   }

   String getInventoryName() {
      return "container.PressOther";
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }
}
