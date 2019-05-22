package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.network.packet.Packet;

public class TileEntityBlockDecor extends BaseTileEntityBlock {

   public boolean canUpdate() {
      return false;
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }

   public Packet getDescriptionPacket() {
      return null;
   }
}
