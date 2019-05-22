package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;

public class TileEntityBlockParta extends BaseTileEntityBlock {

   public boolean canUpdate() {
      return false;
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }
}
