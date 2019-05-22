package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import net.minecraft.client.addon.tco.tiny.IDarkNode;
import net.minecraft.client.addon.tco.tiny.Utils.DarkNode;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.entity.player.EntityPlayer;

public abstract class TileEntityDarkEnergyBase extends BaseTileEntityBlock implements IDarkNode {

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

   public boolean isUseableByPlayer(EntityPlayer entityplayer) {
      return super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord) != this?false:entityplayer.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
   }
}
