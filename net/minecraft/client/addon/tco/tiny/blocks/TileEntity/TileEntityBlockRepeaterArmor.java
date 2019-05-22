package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockBase;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockRepeaterArmor;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityBlockRepeaterArmor extends TileEntityBlockBase {

   public int tickPowerTime = 0;
   private int tick;
   public int speedreamaningTime = 100000;
   public boolean isWorking = false;
   boolean isacselerator = false;
   int startItemID;
   public byte thisRenderModel = 0;


   public TileEntityBlockRepeaterArmor() {
      super.RecipesIndex = Manager.INSTANCE.getIndex(this.getClass());
      super.ItemStacks = new ItemStack[3];
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

   public void updateEntity() {
      super.updateEntity();
      boolean flag = false;
      if(++this.tick >= 40 && !super.worldObj.isRemote) {
         this.tick = 0;

         try {
            int var4;
            if(super.ItemStacks[0] != null && super.ItemStacks[1] != null && super.ItemStacks[1].getItemDamage() > 0) {
               if(!this.isWorking) {
                  this.startItemID = super.ItemStacks[1].itemID;
                  var4 = ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getTimeBurnID(super.ItemStacks[1]);
                  if(var4 > -1) {
                     this.speedreamaningTime = var4;
                  }

                  this.thisRenderModel = ((Integer)((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getSlotAsDataItemID(super.ItemStacks[1])[1]).byteValue();
                  this.isWorking = true;
                  this.tickPowerTime = 0;
                  flag = true;
               }

               if(this.startItemID == super.ItemStacks[1].itemID) {
                  ++this.tickPowerTime;
                  if(!this.isacselerator && super.ItemStacks[2] != null) {
                     this.isacselerator = true;
                  }

                  if(this.isacselerator && super.ItemStacks[2] == null) {
                     this.isacselerator = false;
                     this.isWorking = false;
                     flag = true;
                  }
               } else {
                  this.isWorking = false;
                  flag = true;
               }
            } else {
               if(super.ItemStacks[1] == null || super.ItemStacks[1].getItemDamage() != 0) {
                  this.thisRenderModel = 0;
               }

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
               boolean var5 = false;
               var4 = super.ItemStacks[2] != null?((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(2)).getTimeBurnID(super.ItemStacks[2]):0;
               if(var4 > 0 && super.ItemStacks[0].stackSize >= var4 * 3) {
                  super.ItemStacks[0].stackSize -= var4 * 3;
                  if(super.ItemStacks[0].stackSize <= 0) {
                     super.ItemStacks[0] = null;
                  }

                  if(super.ItemStacks[1].getItemDamage() > 0) {
                     if(super.ItemStacks[1].getItemDamage() >= 28 * var4) {
                        super.ItemStacks[1].setItemDamage(super.ItemStacks[1].getItemDamage() - 28 * var4);
                     } else {
                        super.ItemStacks[1].setItemDamage(0);
                     }
                  }
               } else {
                  --super.ItemStacks[0].stackSize;
                  if(super.ItemStacks[0].stackSize <= 0) {
                     super.ItemStacks[0] = null;
                  }

                  if(super.ItemStacks[1].getItemDamage() > 0) {
                     if(super.ItemStacks[1].getItemDamage() >= 28) {
                        super.ItemStacks[1].setItemDamage(super.ItemStacks[1].getItemDamage() - 28);
                     } else {
                        super.ItemStacks[1].setItemDamage(0);
                     }
                  }
               }

               this.onInventoryChanged();
            }

            if(flag) {
               PacketDispatcher.sendPacketToAllAround((double)super.xCoord, (double)super.yCoord, (double)super.zCoord, 50.0D, super.worldObj.provider.dimensionId, this.getDescriptionPacket());
            }
         } catch (Exception var41) {
            ;
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

   String getInventoryName() {
      return "container.RepeaterArmor";
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      if(super.model == null) {
         super.model = new ModelBlockRepeaterArmor();
      }

      if(this.isWorking) {
         if(super.model.value != this.thisRenderModel) {
            super.model.value = this.thisRenderModel;
         }
      } else if(this.thisRenderModel == 0) {
         if(super.model.value != 0) {
            super.model.value = 0;
         }
      } else if(this.thisRenderModel > 0 && this.thisRenderModel < 10) {
         super.model.value = this.thisRenderModel + 10;
      }

      return super.model;
   }
}
