package net.minecraft.client.addon.tco.tiny.blocks.models.Render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.addon.tco.tiny.Utils.Direction;
import net.minecraft.client.addon.tco.tiny.blocks.BlockTrader;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTradeBase;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTrader;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderCoin;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBlockSide implements ISimpleBlockRenderingHandler {

   private static final Direction[] directions = Direction.values();
   public static int renderId;
   public static RenderBlockSide instance;


   public RenderBlockSide() {
      renderId = RenderingRegistry.getNextAvailableRenderId();
      instance = this;
   }

   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
      renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
      GL11.glPushMatrix();
      Tessellator var5 = Tessellator.instance;
      GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
      var5.startDrawingQuads();
      var5.setNormal(0.0F, -1.0F, 0.0F);
      renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, ((BlockTrader)block).icon[0]);
      var5.draw();
      var5.startDrawingQuads();
      var5.setNormal(0.0F, 1.0F, 0.0F);
      renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, ((BlockTrader)block).icon[1]);
      var5.draw();
      var5.startDrawingQuads();
      var5.setNormal(0.0F, 0.0F, -1.0F);
      renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, ((BlockTrader)block).icon[2]);
      var5.draw();
      var5.startDrawingQuads();
      var5.setNormal(0.0F, 0.0F, 1.0F);
      renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, ((BlockTrader)block).icon[3]);
      var5.draw();
      var5.startDrawingQuads();
      var5.setNormal(-1.0F, 0.0F, 0.0F);
      renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, ((BlockTrader)block).icon[4]);
      var5.draw();
      var5.startDrawingQuads();
      var5.setNormal(1.0F, 0.0F, 0.0F);
      renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, ((BlockTrader)block).icon[5]);
      var5.draw();
      GL11.glTranslatef(0.5F, 0.5F, 0.5F);
      GL11.glPopMatrix();
      renderer.uvRotateBottom = 0;
      renderer.uvRotateTop = 0;
      renderer.uvRotateSouth = 0;
      renderer.uvRotateNorth = 0;
      renderer.uvRotateWest = 0;
      renderer.uvRotateEast = 0;
   }

   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
      TileEntity te = world.getBlockTileEntity(x, y, z);
      if(block instanceof BlockTrader && ((BlockTrader)block).hasSpecialRenderer(world, x, y, z)) {
         return ((BlockTrader)block).specialRenderer(world, x, y, z, renderer);
      } else if(te instanceof TileEntityBlockTrader) {
         TileEntityBlockTrader ate21 = (TileEntityBlockTrader)te;
         return ate21.renderWorldBlock(world, x, y, z, block, modelId, renderer);
      } else if(te instanceof TileEntityBlockTraderCoin) {
         TileEntityBlockTraderCoin ate2 = (TileEntityBlockTraderCoin)te;
         return ate2.renderWorldBlock(world, x, y, z, block, modelId, renderer);
      } else {
         return TileEntityBlockTradeBase.defaultRenderWorldBlock(world, x, y, z, block, modelId, renderer);
      }
   }

   public boolean shouldRender3DInInventory() {
      return true;
   }

   public int getRenderId() {
      if(renderId == -1) {
         renderId = RenderingRegistry.getNextAvailableRenderId();
      }

      return renderId;
   }

}
