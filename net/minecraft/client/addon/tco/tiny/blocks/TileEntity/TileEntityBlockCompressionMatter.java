package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockBase;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockCompressionMatter;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityBlockCompressionMatter extends TileEntityBlockBase {

   private int tick;
   public int MaxUtilTank = 5000;
   public int CountUtilTank = 0;
   public short progress_getUtilScale = 0;
   public byte thisRenderModel = 0;
   float UtilIDandMeta = 0.0F;
   private long timeL = 0L;
   public BaseModelBlock model;


   public TileEntityBlockCompressionMatter() {
      super.RecipesIndex = Manager.INSTANCE.getIndex(this.getClass());
      super.ItemStacks = new ItemStack[20];
   }

   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readFromNBT(par1NBTTagCompound);
      this.CountUtilTank = par1NBTTagCompound.getInteger("cut");
      this.thisRenderModel = par1NBTTagCompound.getByte("rm");
   }

   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setInteger("cut", this.CountUtilTank);
      par1NBTTagCompound.setByte("rm", this.thisRenderModel);
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public void UpdateProgressBar() {
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
      boolean flag = false;
      if(++this.tick % 120 == 0 && !super.worldObj.isRemote) {
         try {
            if(this.timeL > System.currentTimeMillis()) {
               return;
            }

            if(this.CountUtilTank < this.MaxUtilTank) {
               for(int var51 = 0; var51 < 18; ++var51) {
                  if(super.ItemStacks[var51] != null) {
                     flag = true;
                     if(this.CountUtilTank + super.ItemStacks[var51].stackSize < this.MaxUtilTank) {
                        this.CountUtilTank += super.ItemStacks[var51].stackSize;
                        super.ItemStacks[var51] = null;
                     } else {
                        if(super.ItemStacks[var51].stackSize > this.MaxUtilTank - this.CountUtilTank) {
                           ItemStack itemStack = super.ItemStacks[var51];
                           itemStack.stackSize -= this.MaxUtilTank - this.CountUtilTank;
                           this.CountUtilTank += this.MaxUtilTank - this.CountUtilTank;
                           break;
                        }

                        this.CountUtilTank += super.ItemStacks[var51].stackSize;
                        super.ItemStacks[var51] = null;
                     }
                  }
               }
            }

            if(super.ItemStacks[18] != null && super.ItemStacks[18].stackSize > 0 && super.ItemStacks[19] == null && this.CountUtilTank >= 1000) {
               this.CountUtilTank -= 1000;
               ItemStack var5 = super.ItemStacks[18];
               if(--var5.stackSize <= 0) {
                  super.ItemStacks[18] = null;
               }

               if(super.ItemStacks[19] == null) {
                  super.ItemStacks[19] = ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(19)).getSlotItem(0).copy();
               }

               flag = true;
            }

            if(flag) {
               this.onInventoryChanged();
            }

            this.UpdateProgressBar();
         } catch (Exception var4) {
            ;
         }
      }

      super.updateEntity();
   }

   public void openChest() {}

   public void closeChest() {}

   public boolean isStackValidForSlot(int i, ItemStack itemstack) {
      try {
         if(((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(i)).EqualSlotAsItemIDandDamage(itemstack)) {
            return true;
         }
      } catch (Exception var4) {
         ;
      }

      return false;
   }

   String getInventoryName() {
      return "container.CompressionMatter";
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      if(this.model == null) {
         this.model = new ModelBlockCompressionMatter();
      }

      return this.model;
   }

   public void setTimeL() {
      this.timeL = System.currentTimeMillis() + 2000L;
   }
}
