package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockBase;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityBlockHelm extends TileEntityBlockBase {

   public int tickPowerTime = 0;
   private int tick;
   public int speedreamaningTime = 1000;
   public boolean isWorking = false;
   int startItemID;
   int startItemID2;
   public byte thisRenderModel = 0;
   private double[] randomic = new double[]{40.0D, 60.0D};
   public int chance = 100;


   public TileEntityBlockHelm() {
      super.RecipesIndex = Manager.INSTANCE.getIndex(this.getClass());
      super.ItemStacks = new ItemStack[2];
   }

   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readFromNBT(par1NBTTagCompound);
      this.tickPowerTime = par1NBTTagCompound.getInteger("tickPowerTime");
      this.speedreamaningTime = par1NBTTagCompound.getInteger("srt");
      this.isWorking = par1NBTTagCompound.getBoolean("iw");
      this.startItemID = par1NBTTagCompound.getInteger("sii");
      this.thisRenderModel = par1NBTTagCompound.getByte("rm");
   }

   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setInteger("tickPowerTime", this.tickPowerTime);
      par1NBTTagCompound.setInteger("srt", this.speedreamaningTime);
      par1NBTTagCompound.setBoolean("iw", this.isWorking);
      par1NBTTagCompound.setInteger("sii", this.startItemID);
      par1NBTTagCompound.setByte("rm", this.thisRenderModel);
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

   public void setKro(ItemStack armor, int lvl) {
      if(armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         nbtData.setInteger("kro", 2);
      }

   }

   public Integer getKro(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Integer.valueOf(nbtData.getInteger("kro"));
      }
   }

   public void setZ(ItemStack armor, int lvl) {
      if(armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         nbtData.setInteger("inv", 0);
      }

   }

   public Integer getZ(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Integer.valueOf(nbtData.getInteger("inv"));
      }
   }

   public void updateEntity() {
      super.updateEntity();
      boolean flag = false;
      if(++this.tick >= 20 && !super.worldObj.isRemote) {
         this.tick = 0;

         try {
            if(super.ItemStacks[0] != null && super.ItemStacks[1] != null) {
               Integer var5;
               if(!this.isWorking) {
                  var5 = (Integer)((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getSlotAsDataItem(super.ItemStacks[0])[0];
                  if(this.getKro(super.ItemStacks[1]).intValue() > 0 && var5.intValue() == 2010) {
                     this.isWorking = false;
                  } else {
                     this.startItemID = super.ItemStacks[1].itemID;
                     this.startItemID2 = super.ItemStacks[0].itemID;
                     int var6 = ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getTimeBurnID(super.ItemStacks[1]);
                     if(var6 > -1) {
                        this.speedreamaningTime = var6;
                     }

                     this.thisRenderModel = ((Integer)((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getSlotAsDataItemID(super.ItemStacks[1])[1]).byteValue();
                     this.isWorking = true;
                     this.tickPowerTime = 0;
                     flag = true;
                  }
               }

               if(this.startItemID == super.ItemStacks[1].itemID && this.startItemID2 == super.ItemStacks[0].itemID) {
                  if(this.isWorking) {
                     ++this.tickPowerTime;
                     var5 = (Integer)((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getSlotAsDataItem(super.ItemStacks[0])[0];
                     if(var5.intValue() == 2010) {
                        this.chance = 100;
                     }
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

            if(this.tickPowerTime >= this.speedreamaningTime && super.ItemStacks[0] != null && super.ItemStacks[1] != null) {
               this.tickPowerTime = 0;
               boolean var51 = true;
               Integer var61 = (Integer)((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getSlotAsDataItem(super.ItemStacks[0])[0];
               if(var61.intValue() == 2010 && this.getKro(super.ItemStacks[1]) != null && this.getKro(super.ItemStacks[1]).intValue() > 0) {
                  var51 = false;
               }

               if(var51) {
                  --super.ItemStacks[0].stackSize;
                  if(super.ItemStacks[0].stackSize <= 0) {
                     super.ItemStacks[0] = null;
                  }

                  if(var61.intValue() == 2010) {
                     this.setKro(super.ItemStacks[1], var61.intValue());
                  }

                  this.onInventoryChanged();
               }
            }

            if(flag) {
               PacketDispatcher.sendPacketToAllAround((double)super.xCoord, (double)super.yCoord, (double)super.zCoord, 50.0D, super.worldObj.provider.dimensionId, this.getDescriptionPacket());
            }
         } catch (Exception var4) {
            ;
         }
      }

   }

   public void openChest() {}

   public void closeChest() {}

   public boolean isStackValidForSlot(int i, ItemStack itemstack) {
      return true;
   }

   String getInventoryName() {
      return "container.Colorer";
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }
}
