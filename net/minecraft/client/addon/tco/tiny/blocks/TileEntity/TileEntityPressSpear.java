package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.lavamobs.LavaModMobs;
import net.minecraft.client.addon.spearaddon.SpearAddon;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockBase;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityPressSpear extends TileEntityBlockBase {

   public int tickPowerTime = 0;
   private int tick;
   public int speedreamaningTime = 1000;
   public boolean isWorking = false;
   int startItemID;
   public int percent = 0;
   int startItemID2;
   public byte thisRenderModel = 0;
   private double[] randomic = new double[]{30.0D, 70.0D};
   public int chance = 70;


   public TileEntityPressSpear() {
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

      for(int var6 = 0; var6 < this.randomic.length; ++var6) {
         sump = (int)((double)sump + this.randomic[var6]);
      }

      double var61 = Math.random() * (double)sump;
      int num = this.randomic.length;

      for(int i = 0; i < this.randomic.length; ++i) {
         if(var61 <= this.randomic[i]) {
            num = i;
            break;
         }

         var61 -= this.randomic[i];
      }

      return num;
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

   public int getSpear(ItemStack par1ItemStack) {
      return par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("sup")?par1ItemStack.stackTagCompound.getInteger("sup"):0;
   }

   public boolean incSpear(int inc, ItemStack par1ItemStack) {
      Utils.getOrCreateNbtData(par1ItemStack);
      if(par1ItemStack.stackTagCompound == null) {
         return false;
      } else if(par1ItemStack.stackTagCompound.hasKey("sup")) {
         int sup = par1ItemStack.stackTagCompound.getInteger("sup");
         sup += inc;
         par1ItemStack.stackTagCompound.setInteger("sup", sup);
         return true;
      } else {
         par1ItemStack.stackTagCompound.setInteger("sup", inc);
         return true;
      }
   }

   public boolean incSpear2(int inc, ItemStack par1ItemStack) {
      Utils.getOrCreateNbtData(par1ItemStack);
      if(par1ItemStack.stackTagCompound == null) {
         return false;
      } else if(par1ItemStack.stackTagCompound.hasKey("sup")) {
         par1ItemStack.stackTagCompound.setInteger("sup", inc);
         return true;
      } else {
         par1ItemStack.stackTagCompound.setInteger("sup", inc);
         return true;
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
                  if((super.ItemStacks[0].itemID != Tiny.trap1.itemID || super.ItemStacks[1].itemID != SpearAddon.DemonSpear.itemID) && (super.ItemStacks[0].itemID != Tiny.trap2.itemID || super.ItemStacks[1].itemID != SpearAddon.Usums.itemID) && (super.ItemStacks[0].itemID != Tiny.trap3.itemID || super.ItemStacks[1].itemID != SpearAddon.HeroSpear.itemID) && (super.ItemStacks[0].itemID != Tiny.trap4.itemID || super.ItemStacks[1].itemID != LavaModMobs.LavaLBowItem.itemID) && (super.ItemStacks[0].itemID != Tiny.trap5.itemID || super.ItemStacks[1].itemID != LavaModMobs.LavaBowItem.itemID) && (super.ItemStacks[0].itemID != Tiny.trap6.itemID || super.ItemStacks[1].itemID != LavaModMobs.LavaUltBowItem.itemID) && (super.ItemStacks[0].itemID != Tiny.trap7.itemID || super.ItemStacks[1].itemID != SpearAddon.shore.itemID) && (super.ItemStacks[0].itemID != Tiny.trap8.itemID || super.ItemStacks[1].itemID != LavaChestPlate.laitemfriz.itemID)) {
                     this.isWorking = false;
                  } else if(this.getSpear(super.ItemStacks[1]) == 16 && var5.intValue() == 1) {
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
                     if(var5.intValue() == 1) {
                        this.chance = 70;
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
               if(var61.intValue() == 1 && this.getSpear(super.ItemStacks[1]) != 0 && this.getSpear(super.ItemStacks[1]) == 16) {
                  var51 = false;
               }

               if(var51) {
                  --super.ItemStacks[0].stackSize;
                  if(super.ItemStacks[0].stackSize <= 0) {
                     super.ItemStacks[0] = null;
                  }

                  if(var61.intValue() == 1) {
                     if(this.getrandom() == 1) {
                        this.incSpear(1, super.ItemStacks[1]);
                     } else if(this.getSpear(super.ItemStacks[1]) > 0) {
                        this.incSpear2(0, super.ItemStacks[1]);
                     }
                  } else if(this.getSpear(super.ItemStacks[1]) == 0) {
                     this.incSpear(0, super.ItemStacks[1]);
                  }
               } else if(this.getSpear(super.ItemStacks[1]) == -1) {
                  this.incSpear(1, super.ItemStacks[1]);
               }
            } else if(this.getSpear(super.ItemStacks[1]) == -1) {
               this.incSpear(1, super.ItemStacks[1]);
            } else if(this.getSpear(super.ItemStacks[1]) == -2) {
               this.incSpear(2, super.ItemStacks[1]);
            } else if(this.getSpear(super.ItemStacks[1]) == -3) {
               this.incSpear(3, super.ItemStacks[1]);
            } else if(this.getSpear(super.ItemStacks[1]) == -4) {
               this.incSpear(4, super.ItemStacks[1]);
            } else if(this.getSpear(super.ItemStacks[1]) == -5) {
               this.incSpear(5, super.ItemStacks[1]);
            } else if(this.getSpear(super.ItemStacks[1]) == -6) {
               this.incSpear(6, super.ItemStacks[1]);
            } else if(this.getSpear(super.ItemStacks[1]) == -7) {
               this.incSpear(7, super.ItemStacks[1]);
            } else if(this.getSpear(super.ItemStacks[1]) == -8) {
               this.incSpear(8, super.ItemStacks[1]);
            } else if(this.getSpear(super.ItemStacks[1]) == -9) {
               this.incSpear(9, super.ItemStacks[1]);
            } else if(this.getSpear(super.ItemStacks[1]) == -10) {
               this.incSpear(10, super.ItemStacks[1]);
            } else if(this.getSpear(super.ItemStacks[1]) == -11) {
               this.incSpear(11, super.ItemStacks[1]);
            } else if(this.getSpear(super.ItemStacks[1]) == -12) {
               this.incSpear(12, super.ItemStacks[1]);
            } else if(this.getSpear(super.ItemStacks[1]) == -13) {
               this.incSpear(13, super.ItemStacks[1]);
            } else if(this.getSpear(super.ItemStacks[1]) == -14) {
               this.incSpear(14, super.ItemStacks[1]);
            } else if(this.getSpear(super.ItemStacks[1]) == -15) {
               this.incSpear(15, super.ItemStacks[1]);
            }

            this.onInventoryChanged();
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
      try {
         boolean var4 = i == 0 && super.ItemStacks[1] != null && this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0?false:((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(i)).EqualSlotAsItemID(itemstack);
         return var4;
      } catch (Exception var41) {
         return false;
      }
   }

   String getInventoryName() {
      return "container.PressSpear";
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }
}
