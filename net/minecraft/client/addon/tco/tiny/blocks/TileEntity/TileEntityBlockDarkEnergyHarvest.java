package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import net.minecraft.client.addon.tco.tiny.IDarkNode;
import net.minecraft.client.addon.tco.tiny.Utils.DarkNode;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkEnergyControler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBlockDarkEnergyHarvest extends TileEntity implements IDarkNode {

   public int Working = 0;
   public short countDarkEnergy;
   boolean isInit = false;


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

   public boolean canUpdate() {
      return false;
   }

   public void update() {
      if(super.yCoord <= 2) {
         ++this.Working;
         if(super.worldObj.rand.nextFloat() > 0.8F) {
            ++this.countDarkEnergy;
         }
      }

   }

   public boolean isUseableByPlayer(EntityPlayer entityplayer) {
      return super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord) != this?false:entityplayer.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
   }

   public int getIDNode() {
      return 1;
   }

   public void update(TileEntityBlockDarkEnergyControler controller) {
      try {
         this.update();
         if(this.countDarkEnergy > 0) {
            short m = this.countDarkEnergy;
            this.countDarkEnergy = 0;
            controller.addDarkEnergyController(m);
         }
      } catch (Exception var3) {
         ;
      }

   }

   public void stop(TileEntityBlockDarkEnergyControler controller) {}
}
