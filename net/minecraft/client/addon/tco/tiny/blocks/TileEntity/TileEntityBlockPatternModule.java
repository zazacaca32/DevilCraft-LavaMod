package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.CFlag;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockBase;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityBlockPatternModule extends TileEntityBlockBase {

   public int tickPowerTime = 0;
   private int tick;
   public int speedreamaningTime = 1000;
   public boolean isWorking = false;
   int startItemID;
   int startItemID2;
   public byte thisRenderModel = 0;
   private double[] randomic = new double[]{40.0D, 60.0D};
   public int chance = 100;


   public TileEntityBlockPatternModule() {
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

   public int getrandom() {
      int sump = 0;

      for(int var61 = 0; var61 < this.randomic.length; ++var61) {
         sump = (int)((double)sump + this.randomic[var61]);
      }

      double var6 = Math.random() * (double)sump;
      int num = this.randomic.length;

      for(int i = 0; i < this.randomic.length; ++i) {
         if(var6 <= this.randomic[i]) {
            num = i;
            break;
         }

         var6 -= this.randomic[i];
      }

      return num;
   }

   public boolean hasId(ItemStack armor) {
      return super.ItemStacks[1].itemID == 31327;
   }

   public void setNextId(ItemStack armor) {
      if(armor.stackSize <= 1) {
         ++ armor.itemID;
      }

   }
   
   public void setArm11(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         armor.itemID = 6983;
	      }

	   }
   
   public void setArm10(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         armor.itemID = 6982;
	      }

	   }
   
   public void setArm9(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         armor.itemID = 6981;
	      }

	   }
   
   public void setArm8(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         armor.itemID = 6980;
	      }

	   }
   
   public void setArm7(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         armor.itemID = 6979;
	      }

	   }
public void setArm6(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         armor.itemID = 6978;
	      }

	   }
public void setArm5(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         armor.itemID = 6977;
	      }

	   }

public void setArm4(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         armor.itemID = 6976;
	      }

	   }

   public void setArm3(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         armor.itemID = 6975;
	      }

	   }
   public void setArm2(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         armor.itemID = 6974;
	      }

	   }
   public void setArm1(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         armor.itemID = 6973;
	      }

	   }

   public void setArm(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         armor.itemID = 6972;
	      }

	   }

   public void setDarkShieldlvl(ItemStack armor, int lvl) {
      if(armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         if(lvl > 0) {
            nbtData.setByte("dss", (byte)lvl);
         }
      }

   }

   public Integer getDarkShieldCharge(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Integer.valueOf(nbtData.getByte("dss"));
      }
   }

   public void updateEntity() {
      if (CFlag.isSERVER) {
      super.updateEntity();
      boolean flag = false;
      if (++this.tick >= 20 && !super.worldObj.isRemote) {
         this.tick = 0;

         try {
            if (super.ItemStacks[0] != null && super.ItemStacks[1] != null) {
               Integer var51;
               if (!this.isWorking) {
                  var51 = (Integer) ((Slot_R) Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getSlotAsDataItem(super.ItemStacks[0])[0];
                  if (this.hasId(super.ItemStacks[1])) {
                     this.isWorking = false;
                  } else if (super.ItemStacks[1].stackSize > 1) {
                     this.isWorking = false;
                  } else {
                     this.startItemID = super.ItemStacks[1].itemID;
                     this.startItemID2 = super.ItemStacks[0].itemID;
                     int var61 = ((Slot_R) Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getTimeBurnID(super.ItemStacks[1]);
                     if (var61 > -1) {
                        this.speedreamaningTime = var61;
                     }

                     this.thisRenderModel = ((Integer) ((Slot_R) Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getSlotAsDataItemID(super.ItemStacks[1])[1]).byteValue();
                     this.isWorking = true;
                     this.tickPowerTime = 0;
                     flag = true;
                  }
               }

               if (this.startItemID == super.ItemStacks[1].itemID && this.startItemID2 == super.ItemStacks[0].itemID) {
                  if (this.isWorking) {
                     ++this.tickPowerTime;
                     var51 = (Integer) ((Slot_R) Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getSlotAsDataItem(super.ItemStacks[0])[0];
                     if (var51.intValue() == 0) {
                        this.chance = 100;
                     }
                  }
               } else {
                  this.isWorking = false;
                  this.tickPowerTime = 0;
                  flag = true;
               }
            } else {
               if (this.isWorking) {
                  this.isWorking = false;
                  flag = true;
               }

               if (this.tickPowerTime > 0) {
                  this.tickPowerTime = 0;
               }
            }

            if (this.tickPowerTime >= this.speedreamaningTime && super.ItemStacks[0] != null && super.ItemStacks[1] != null) {
               this.tickPowerTime = 0;
               boolean var5 = true;
               int var61;
               var61 = ((Slot_R) Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getMetaArm(super.ItemStacks[1]);
               Integer var6 = (Integer) ((Slot_R) Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getSlotAsDataItem(super.ItemStacks[0])[0];
               if (var6.intValue() == 0 && this.hasId(super.ItemStacks[1])) {
                  var5 = false;
               }

               if (var6.intValue() == 0 && super.ItemStacks[1].stackSize > 1) {
                  var5 = false;
               }

               if (var5) {
                  --super.ItemStacks[0].stackSize;
                  if (super.ItemStacks[0].stackSize <= 0) {
                     super.ItemStacks[0] = null;
                  }

                  if (var6.intValue() == 0 && var61 <= 9) {
                     this.setNextId(super.ItemStacks[1]);
                  }

                  if (var6.intValue() == 0 && var61 == 10) {
                     this.setArm(super.ItemStacks[1]);
                  }
                  if (var6.intValue() == 0 && var61 == 11) {
                     this.setArm1(super.ItemStacks[1]);
                  }
                  if (var6.intValue() == 0 && var61 == 12) {
                     this.setArm2(super.ItemStacks[1]);
                  }

                  if (var6.intValue() == 0 && var61 == 13) {
                     this.setArm3(super.ItemStacks[1]);
                  }
                  if (var6.intValue() == 0 && var61 == 14) {
                     this.setArm4(super.ItemStacks[1]);
                  }
                  if (var6.intValue() == 0 && var61 == 15) {
                     this.setArm5(super.ItemStacks[1]);
                  }
                  if (var6.intValue() == 0 && var61 == 16) {
                     this.setArm6(super.ItemStacks[1]);
                  }
                  if (var6.intValue() == 0 && var61 == 17) {
                     this.setArm7(super.ItemStacks[1]);
                  }


                  if (var6.intValue() == 0 && var61 == 18) {
                     this.setArm8(super.ItemStacks[1]);
                  }
                  if (var6.intValue() == 0 && var61 == 19) {
                     this.setArm9(super.ItemStacks[1]);
                  }
                  if (var6.intValue() == 0 && var61 == 20) {
                     this.setArm10(super.ItemStacks[1]);
                  }
                  if (var6.intValue() == 0 && var61 == 21) {
                     this.setArm11(super.ItemStacks[1]);
                  }

                  this.onInventoryChanged();
               }
            }

            if (flag) {
               PacketDispatcher.sendPacketToAllAround((double) super.xCoord, (double) super.yCoord, (double) super.zCoord, 50.0D, super.worldObj.provider.dimensionId, this.getDescriptionPacket());
            }
         } catch (Exception var4) {
            ;
         }
      }
   }
   }

   public void openChest() {}

   public void closeChest() {}

   public boolean isStackValidForSlot(int i, ItemStack itemstack) {
      try {
         return i == 0 && super.ItemStacks[1] != null && this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0?false:((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(i)).EqualSlotAsItemID(itemstack);
      } catch (Exception var4) {
         return false;
      }
   }

   String getInventoryName() {
      return "container.asdasdasdasd";
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }
}
