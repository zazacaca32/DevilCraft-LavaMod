package net.minecraft.client.addon.tco.tiny;

import net.minecraft.block.Block;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.renderer.ChestItemRenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.world.World;

public class IronChestRenderHelper extends ChestItemRenderHelper {

   public void renderChest(Block block, int i, float f) {
      if(block == Tiny.blockTiny) {
         TileEntityRenderer.instance.renderTileEntityAt(Tiny.blockTiny.createTileEntity((World)null, 0), 0.0D, 0.0D, 0.0D, 0.0F);
      } else {
         super.renderChest(block, i, f);
      }

   }
}
