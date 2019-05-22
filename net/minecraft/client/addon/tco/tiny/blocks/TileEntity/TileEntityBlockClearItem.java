package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import cpw.mods.fml.common.FMLCommonHandler;
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

public class TileEntityBlockClearItem extends TileEntityBlockBase {

   public int tickPowerTime = 0;
   private int tick;
   public int speedreamaningTime = 1000;
   public boolean isWorking = false;
   int startItemID;
   int startItemID2;
   public byte thisRenderModel = 0;
   private double[] randomic = new double[]{40.0D, 60.0D};
   public int chance = 100;


   public TileEntityBlockClearItem() {
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

   public String ghammer(ItemStack par1ItemStack) {
      return par1ItemStack.stackTagCompound.getString("lHammerstart");
   }

   public String gstartr(ItemStack par1ItemStack) {
      return par1ItemStack.stackTagCompound.getString("lswordstartr");
   }

   public String gfriz(ItemStack par1ItemStack) {
      return par1ItemStack.stackTagCompound.getString("lStaffFriz");
   }

   public String gsword(ItemStack par1ItemStack) {
      return par1ItemStack.stackTagCompound.getString("lswordstart");
   }

   public String glawar(ItemStack par1ItemStack) {
      return par1ItemStack.stackTagCompound.getString("lawar");
   }

   public String bow(ItemStack par1ItemStack) {
      return par1ItemStack.stackTagCompound.getString("bowstart");
   }

   public String getAll(ItemStack i) {
      return this.ghammer(i) == null?(this.gstartr(i) == null && this.gfriz(i) == null && this.gsword(i) == null && this.glawar(i) == null && this.bow(i) == null?null:"!null"):"!null";
   }

   public boolean setAll(ItemStack par1ItemStack) {
      Utils.getOrCreateNbtData(par1ItemStack);
      par1ItemStack.stackTagCompound.removeTag("lHammerstart");
      par1ItemStack.stackTagCompound.removeTag("lswordstartr");
      par1ItemStack.stackTagCompound.removeTag("lStaffFriz");
      par1ItemStack.stackTagCompound.removeTag("lswordstart");
      par1ItemStack.stackTagCompound.removeTag("lawar");
      par1ItemStack.stackTagCompound.removeTag("bowstart");
      return true;
   }

   public void updateEntity() {
      super.updateEntity();
      boolean flag = false;
      if(++this.tick >= 20 && !super.worldObj.isRemote) {
         this.tick = 0;

         try {
            if(super.ItemStacks[0] != null && super.ItemStacks[1] != null) {
               Integer var4;
               if(!this.isWorking) {
                  var4 = (Integer)((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getSlotAsDataItem(super.ItemStacks[0])[0];
                  if(this.getAll(super.ItemStacks[1]) == null) {
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
                     var4 = (Integer)((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getSlotAsDataItem(super.ItemStacks[0])[0];
                     if(var4.intValue() == 2) {
                        this.chance = 100;
                     } else if(var4.intValue() == 1) {
                        this.chance = 50;
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
               boolean var5 = true;
               Integer var61 = (Integer)((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getSlotAsDataItem(super.ItemStacks[0])[0];
               if(var61.intValue() == 1 && this.getAll(super.ItemStacks[1]) == null) {
                  var5 = false;
               } else if(var61.intValue() == 2 && this.getAll(super.ItemStacks[1]) == null) {
                  var5 = false;
               }

               if(var5) {
                  --super.ItemStacks[0].stackSize;
                  if(super.ItemStacks[0].stackSize <= 0) {
                     super.ItemStacks[0] = null;
                  }

                  if(var61.intValue() == 1) {
                     if(this.getrandom() == 1) {
                        this.setAll(super.ItemStacks[1]);
                     }
                  } else if(var61.intValue() == 2) {
                     this.setAll(super.ItemStacks[1]);
                  }

                  this.onInventoryChanged();
               }
            }

            if(flag) {
               PacketDispatcher.sendPacketToAllAround((double)super.xCoord, (double)super.yCoord, (double)super.zCoord, 50.0D, super.worldObj.provider.dimensionId, this.getDescriptionPacket());
            }
         } catch (Exception var41) {
            FMLCommonHandler.instance().getFMLLogger().warning(this.getClass().getSimpleName() + " " + super.xCoord + " " + super.yCoord + " " + super.zCoord + " " + var41.getMessage());
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
      return "container.ClearItem";
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }
}
