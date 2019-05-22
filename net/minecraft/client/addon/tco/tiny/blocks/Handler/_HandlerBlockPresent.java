package net.minecraft.client.addon.tco.tiny.blocks.Handler;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class _HandlerBlockPresent implements ISimpleBlockRenderingHandler {

   private Block block;
   private TileEntity tileEntity;
   int renderID = 0;


   public _HandlerBlockPresent(int renderID, TileEntity tileEntity, Block block) {
      this.tileEntity = tileEntity;
      this.block = block;
   }

   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
      if(block == this.block) {
         TileEntityRenderer.instance.renderTileEntityAt(this.tileEntity, 0.0D, 0.0D, 0.0D, 0.0F);
      }

   }

   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelITd, RenderBlocks renderer) {
      return true;
   }

   public boolean shouldRender3DInInventory() {
      return true;
   }

   public int getRenderId() {
      return this.renderID;
   }
}
