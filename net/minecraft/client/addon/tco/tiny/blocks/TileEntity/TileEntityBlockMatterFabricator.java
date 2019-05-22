package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockBase;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityBlockMatterFabricator extends TileEntityBlockBase {

   private int tick;
   public boolean isWorking = false;
   float PriceOneMatter = 0.0F;
   public int MaxUtilTank = 3200000;
   public int CountUtilTank = 0;
   public float GlobalEnergy = 0.0F;
   public short progress_BurnMatter = 0;
   public short progress_getUtilScale = 0;
   public byte thisRenderModel = 0;
   float UtilIDandMeta = 0.0F;
   public float UtilModify = 0.0F;
   public boolean initialized;
   public boolean sunIsUp;
   private boolean wetBiome;
   public boolean noSunWorld;


   public TileEntityBlockMatterFabricator() {
      super.RecipesIndex = Manager.INSTANCE.getIndex(this.getClass());
      super.ItemStacks = new ItemStack[13];
      this.sunIsUp = false;
      this.initialized = false;
      this.noSunWorld = false;
      this.PriceOneMatter = ((Float)((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(12)).getSlotData(0)[0]).floatValue();
   }

   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readFromNBT(par1NBTTagCompound);
      this.CountUtilTank = par1NBTTagCompound.getInteger("cut");
      this.GlobalEnergy = par1NBTTagCompound.getFloat("ge");
      this.isWorking = par1NBTTagCompound.getBoolean("iw");
      this.thisRenderModel = par1NBTTagCompound.getByte("rm");
      this.UtilModify = par1NBTTagCompound.getFloat("um");
   }

   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setInteger("cut", this.CountUtilTank);
      par1NBTTagCompound.setFloat("ge", this.GlobalEnergy);
      par1NBTTagCompound.setBoolean("iw", this.isWorking);
      par1NBTTagCompound.setByte("rm", this.thisRenderModel);
      par1NBTTagCompound.setFloat("um", this.UtilModify);
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public void UpdateProgressBar() {
      this.progress_BurnMatter = (short)((int)(this.GlobalEnergy / this.PriceOneMatter * 100.0F));
      int r = 0;
      if(this.CountUtilTank > 0 && (r = this.CountUtilTank * 58 / this.MaxUtilTank) > 58) {
         r = 58;
      }

      this.progress_getUtilScale = (short)r;
   }

   public boolean canUpdate() {
      return true;
   }

   public void updateEntity() {
      if(!this.initialized) {
         this.intialize();
      }

      if(!this.noSunWorld) {
         boolean flag = false;
         boolean flag_send_isWorking = false;
         if(++this.tick % 10 == 0 && !super.worldObj.isRemote) {
            try {
               if(super.ItemStacks[12] != null && super.ItemStacks[12].stackSize >= 64) {
                  if(this.GlobalEnergy > 0.0F) {
                     this.GlobalEnergy = 0.0F;
                  }

                  if(this.isWorking) {
                     this.isWorking = false;
                     flag_send_isWorking = true;
                  }

                  return;
               }

               float var9;
               float var91;
               float var10;
               if(super.ItemStacks[11] != null) {
                  boolean var101 = false;
                  var9 = ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(11)).getFloatIDandDamage(super.ItemStacks[11]);
                  if(this.CountUtilTank <= 0) {
                     this.UtilIDandMeta = 0.0F;
                  }

                  if(this.UtilIDandMeta == 0.0F) {
                     this.UtilIDandMeta = var9;
                  }

                  if(this.UtilIDandMeta == var9) {
                     var101 = true;
                  }

                  if(var101) {
                     var91 = (float)this.CountUtilTank;
                     float var111 = var10 = ((Float)((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(11)).getSlotAsDataItem(super.ItemStacks[11])[0]).floatValue();
                     int speedModify = super.ItemStacks[11].stackSize;
                     if(var91 + var111 * (float)super.ItemStacks[11].stackSize <= (float)this.MaxUtilTank) {
                        super.ItemStacks[11] = null;
                        this.CountUtilTank = (int)((float)this.CountUtilTank + var10 * (float)speedModify);
                        flag = true;
                     }
                  }
               }

               if(this.tick % 15 == 0) {
                  if(this.tick >= 400) {
                     this.tick = 0;
                     this.updateVisibility();
                  }

                  int var101 = this.sunIsUp?0:1;
                  var91 = 0.0F;
                  if(super.ItemStacks[10] != null) {
                     var91 = ((Float)((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(10)).getSlotAsDataItem(super.ItemStacks[10])[0]).floatValue();
                  }

                  var10 = 0.0F;

                  for(int var11 = 0; var11 < 10; ++var11) {
                     if(super.ItemStacks[var11] != null) {
                        var10 += (float)super.ItemStacks[var11].stackSize * ((Float)((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(var11)).getSlotAsDataItem(super.ItemStacks[var11])[var101]).floatValue();
                     }
                  }

                  if(var10 > 0.0F) {
                     if(!this.isWorking) {
                        this.isWorking = true;
                        flag_send_isWorking = true;
                     }

                     if(this.CountUtilTank > 0) {
                        this.UtilModify = ((Float)((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(11)).getSlotData(0)[0]).floatValue();
                        var9 = var10;
                        if(var10 > 5000.0F) {
                           var9 = 5000.0F;
                        }

                        if((float)this.CountUtilTank - (var9 *= 40.0F) < 0.0F) {
                           var9 = (float)this.CountUtilTank;
                        }

                        var10 *= 40.0F;
                        var10 += 50000.0F;
                        this.CountUtilTank = (int)((float)this.CountUtilTank - var9);
                     } else {
                        if(this.UtilModify > 0.0F) {
                           this.UtilModify = 0.0F;
                        }

                        var10 *= 40.0F;
                     }

                     this.GlobalEnergy += var10 + var10 * var91 / 100.0F;
                     if(this.GlobalEnergy > this.PriceOneMatter) {
                        var9 = this.GlobalEnergy / this.PriceOneMatter;
                        if(super.ItemStacks[12] == null) {
                           super.ItemStacks[12] = ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(12)).getSlotItem(0).copy();
                           --super.ItemStacks[12].stackSize;
                        }

                        if(var9 > 64.0F) {
                           super.ItemStacks[12].stackSize = 64;
                           this.GlobalEnergy -= 64.0F * this.PriceOneMatter;
                           flag = true;
                        } else if(var9 >= 1.0F) {
                           if(super.ItemStacks[12].stackSize + (int)var9 > 64) {
                              super.ItemStacks[12].stackSize = 64;
                              this.GlobalEnergy -= (float)(super.ItemStacks[12].stackSize + (int)var9 - 64) * this.PriceOneMatter;
                           } else {
                              super.ItemStacks[12].stackSize += (int)var9;
                              this.GlobalEnergy -= (float)((int)var9) * this.PriceOneMatter;
                           }

                           flag = true;
                        }
                     }
                  } else if(this.isWorking) {
                     this.isWorking = false;
                     flag_send_isWorking = true;
                  }

                  this.UpdateProgressBar();
                  if(flag_send_isWorking) {
                     PacketDispatcher.sendPacketToAllAround((double)super.xCoord, (double)super.yCoord, (double)super.zCoord, 50.0D, super.worldObj.provider.dimensionId, this.getDescriptionPacket());
                  }
               }

               if(flag) {
                  this.onInventoryChanged();
               }
            } catch (Exception var91) {
               ;
            }
         }

         super.updateEntity();
      }

   }

   public void intialize() {
      this.wetBiome = super.worldObj.getWorldChunkManager().getBiomeGenAt(super.xCoord, super.zCoord).getIntRainfall() > 0;
      this.noSunWorld = super.worldObj.provider.hasNoSky;
      this.updateVisibility();
      this.initialized = true;
   }

   public void updateVisibility() {
      Boolean rainWeather = Boolean.valueOf(this.wetBiome && (super.worldObj.isRaining() || super.worldObj.isThundering()));
      this.sunIsUp = super.worldObj.isDaytime() && !rainWeather.booleanValue();
   }

   public void openChest() {}

   public void closeChest() {}

   public boolean isStackValidForSlot(int i, ItemStack itemstack) {
      if(i <= 11) {
         return false;
      } else {
         try {
            return ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(i)).EqualSlotAsItemIDandDamage(itemstack);
         } catch (Exception var4) {
            return false;
         }
      }
   }

   String getInventoryName() {
      return "container.MatterFabricator";
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      if(this.isWorking) {
         Tiny.proxy.wispFX3(Tiny.proxy.getClientWorld(), (double)((float)super.xCoord + 0.5F), (double)((float)super.yCoord + 0.5F), (double)((float)super.zCoord + 0.5F), (double)((float)super.xCoord + 0.3F + super.worldObj.rand.nextFloat() * 0.4F), (double)((float)super.yCoord + 0.5F), (double)((float)super.zCoord + 0.3F + super.worldObj.rand.nextFloat() * 0.7F), 0.15F, 5, true, -0.025F);
         Tiny.proxy.wispFX3(Tiny.proxy.getClientWorld(), (double)((float)super.xCoord + 0.5F), (double)((float)super.yCoord + 0.5F), (double)((float)super.zCoord + 0.5F), (double)((float)super.xCoord + 0.4F + super.worldObj.rand.nextFloat() * 0.2F), (double)((float)super.yCoord + 0.5F), (double)((float)super.zCoord + 0.4F + super.worldObj.rand.nextFloat() * 0.2F), 0.07F, 0, true, -0.52F);
      }

      return null;
   }
}
