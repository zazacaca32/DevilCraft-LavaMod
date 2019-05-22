package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import net.minecraft.client.addon.tco.tiny.IDarkNode;
import net.minecraft.client.addon.tco.tiny.Utils.DarkNode;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockBase;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkEnergyControler;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityBlockDarkMatterFabricator extends TileEntityBlockBase implements IDarkNode {

   public boolean isWorking = false;
   public short countDarkEnergy;
   public short maxDarkEnergy = 500;
   public short DarkEnergyController = 0;
   int tick = 0;
   boolean isInit = false;


   public TileEntityBlockDarkMatterFabricator() {
      super.RecipesIndex = Manager.INSTANCE.getIndex(this.getClass());
      super.ItemStacks = new ItemStack[2];
   }

   public void validate() {
      super.validate();
      if(!this.isInit) {
         if(!super.worldObj.isRemote) {
            DarkNode.add(super.xCoord, super.yCoord, super.zCoord, this);
         }

         this.isInit = true;
      }

   }

   public void invalidate() {
      super.invalidate();
      if(this.isInit && !super.worldObj.isRemote) {
         DarkNode.remove(super.xCoord, super.yCoord, super.zCoord, this.getIDNode());
      }

   }

   public void updateEntity() {
      if(!super.worldObj.isRemote && this.tick++ > 20) {
         this.update();
         this.tick = 0;
      }

   }

   public void update() {
      if(this.isWorking) {
         try {
            if(this.countDarkEnergy > 0) {
               this.countDarkEnergy = (short)(this.countDarkEnergy - 5);
               if(super.ItemStacks[1] != null) {
                  this.countDarkEnergy = 0;
               }
            } else {
               if(super.ItemStacks[0] == null) {
                  super.ItemStacks[0] = ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getSlotItem(0).copy();
                  --super.ItemStacks[0].stackSize;
               }

               if(super.ItemStacks[0].stackSize < 64) {
                  ++super.ItemStacks[0].stackSize;
                  this.isWorking = false;
               }
            }
         } catch (Exception var2) {
            ;
         }
      }

   }

   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readFromNBT(par1NBTTagCompound);
      this.countDarkEnergy = par1NBTTagCompound.getShort("cde");
      this.isWorking = par1NBTTagCompound.getBoolean("cdb");
   }

   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setShort("cde", this.countDarkEnergy);
      par1NBTTagCompound.setBoolean("cdb", this.isWorking);
   }

   public int getIDNode() {
      return 3;
   }

   public void update(TileEntityBlockDarkEnergyControler controller) {
      if(controller.darkEnergy > (long)this.maxDarkEnergy) {
         if(!this.isWorking) {
            this.countDarkEnergy = this.maxDarkEnergy;
            controller.darkEnergy -= (long)this.maxDarkEnergy;
            this.isWorking = true;
         }

         this.DarkEnergyController = this.maxDarkEnergy;
      } else {
         this.DarkEnergyController = (short)((int)controller.darkEnergy);
      }

   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }

   public boolean isStackValidForSlot(int i, ItemStack itemstack) {
      if(i == 0) {
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
      return "container.DarkMatterFab";
   }

   public void stop(TileEntityBlockDarkEnergyControler controller) {
      this.isWorking = false;
   }
}
