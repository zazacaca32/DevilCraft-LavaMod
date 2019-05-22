package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.network.packet.Packet;

public class TileEntityBlockDeadPlayer extends BaseTileEntityBlock {

   private int tickT = 100;


   public void updateEntity() {
      if(this.tickT-- < 0) {
         super.worldObj.setBlockToAir(super.xCoord, super.yCoord, super.zCoord);
         super.worldObj.removeBlockTileEntity(super.xCoord, super.yCoord, super.zCoord);
      }

   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }

   public Packet getDescriptionPacket() {
      super.worldObj.addBlockEvent(super.xCoord, super.yCoord, super.zCoord, this.getBlockType().blockID, 3, this.getFacing());
      return null;
   }
}
