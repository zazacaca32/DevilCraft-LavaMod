package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.network.packet.Packet;

public class TileEntityBlockPresentNewYear extends BaseTileEntityBlock {

   public boolean canUpdate() {
      return false;
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }

   public Packet getDescriptionPacket() {
      super.worldObj.addBlockEvent(super.xCoord, super.yCoord, super.zCoord, this.getBlockType().blockID, 3, this.getFacing());
      return null;
   }
}
