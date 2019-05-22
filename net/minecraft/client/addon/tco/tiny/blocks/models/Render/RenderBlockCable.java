package net.minecraft.client.addon.tco.tiny.blocks.models.Render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.addon.tco.tiny.Utils.Direction;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityCable;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class RenderBlockCable implements ISimpleBlockRenderingHandler {

   private static final Direction[] directions = Direction.values();
   public static int renderId;


   public RenderBlockCable() {
      renderId = RenderingRegistry.getNextAvailableRenderId();
   }

   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

   public boolean renderWorldBlock(IBlockAccess iBlockAccess, int x, int y, int z, Block block, int modelId, RenderBlocks renderblocks) {
      TileEntity te = iBlockAccess.getBlockTileEntity(x, y, z);
      if(!(te instanceof TileEntityCable)) {
         return true;
      } else {
         TileEntityCable cable = (TileEntityCable)te;
         float th = cable.getCableThickness();
         float sp = (1.0F - th) / 2.0F;
         int connectivity = 0;
         int renderSide = 0;
         int mask = 1;
         Direction[] textures = directions;
         int tessellator = textures.length;

         for(int var25 = 0; var25 < tessellator; ++var25) {
            Direction var271 = textures[var25];
            TileEntity var28 = var271.applyToTileEntity(cable);
            if(cable.canInteractWith(var28)) {
               connectivity |= mask;
               if(var28 instanceof TileEntityCable && ((TileEntityCable)var28).getCableThickness() < th) {
                  renderSide |= mask;
               }
            }

            mask *= 2;
         }

         Icon[] var251 = new Icon[6];

         for(tessellator = 0; tessellator < 6; ++tessellator) {
            Icon var26 = block.getBlockTexture(iBlockAccess, x, y, z, tessellator);
            var251[tessellator] = var26 != null?var26:renderblocks.minecraftRB.renderEngine.getMissingIcon(0);
         }

         Tessellator var271 = Tessellator.instance;
         double var281 = (double)x;
         double var27 = (double)y;
         double zD = (double)z;
         var271.setBrightness(block.getMixedBrightnessForBlock(iBlockAccess, x, y, z));
         if(connectivity == 0) {
            block.setBlockBounds(sp, sp, sp, sp + th, sp + th, sp + th);
            renderblocks.setRenderBoundsFromBlock(block);
            var271.setColorOpaque_F(0.5F, 0.5F, 0.5F);
            renderblocks.renderFaceYNeg(block, var281, var27, zD, var251[0]);
            var271.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            renderblocks.renderFaceYPos(block, var281, var27, zD, var251[1]);
            var271.setColorOpaque_F(0.8F, 0.8F, 0.8F);
            renderblocks.renderFaceZNeg(block, var281, var27, zD, var251[2]);
            renderblocks.renderFaceZPos(block, var281, (double)y, zD, var251[3]);
            var271.setColorOpaque_F(0.6F, 0.6F, 0.6F);
            renderblocks.renderFaceXNeg(block, var281, var27, zD, var251[4]);
            renderblocks.renderFaceXPos(block, var281, var27, zD, var251[5]);
         } else if(connectivity == 3) {
            block.setBlockBounds(0.0F, sp, sp, 1.0F, sp + th, sp + th);
            renderblocks.setRenderBoundsFromBlock(block);
            var271.setColorOpaque_F(0.5F, 0.5F, 0.5F);
            renderblocks.renderFaceYNeg(block, var281, var27, zD, var251[0]);
            var271.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            renderblocks.renderFaceYPos(block, var281, var27, zD, var251[1]);
            var271.setColorOpaque_F(0.8F, 0.8F, 0.8F);
            renderblocks.renderFaceZNeg(block, var281, var27, zD, var251[2]);
            renderblocks.renderFaceZPos(block, var281, (double)y, zD, var251[3]);
            if((renderSide & 1) != 0) {
               var271.setColorOpaque_F(0.6F, 0.6F, 0.6F);
               renderblocks.renderFaceXNeg(block, var281, var27, zD, var251[4]);
            }

            if((renderSide & 2) != 0) {
               var271.setColorOpaque_F(0.6F, 0.6F, 0.6F);
               renderblocks.renderFaceXPos(block, var281, var27, zD, var251[5]);
            }
         } else if(connectivity == 12) {
            block.setBlockBounds(sp, 0.0F, sp, sp + th, 1.0F, sp + th);
            renderblocks.setRenderBoundsFromBlock(block);
            var271.setColorOpaque_F(0.8F, 0.8F, 0.8F);
            renderblocks.renderFaceZNeg(block, var281, var27, zD, var251[2]);
            renderblocks.renderFaceZPos(block, var281, (double)y, zD, var251[3]);
            var271.setColorOpaque_F(0.6F, 0.6F, 0.6F);
            renderblocks.renderFaceXNeg(block, var281, var27, zD, var251[4]);
            renderblocks.renderFaceXPos(block, var281, var27, zD, var251[5]);
            if((renderSide & 4) != 0) {
               var271.setColorOpaque_F(0.5F, 0.5F, 0.5F);
               renderblocks.renderFaceYNeg(block, var281, var27, zD, var251[0]);
            }

            if((renderSide & 8) != 0) {
               var271.setColorOpaque_F(1.0F, 1.0F, 1.0F);
               renderblocks.renderFaceYPos(block, var281, var27, zD, var251[1]);
            }
         } else if(connectivity == 48) {
            block.setBlockBounds(sp, sp, 0.0F, sp + th, sp + th, 1.0F);
            renderblocks.setRenderBoundsFromBlock(block);
            var271.setColorOpaque_F(0.5F, 0.5F, 0.5F);
            renderblocks.renderFaceYNeg(block, var281, var27, zD, var251[0]);
            var271.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            renderblocks.renderFaceYPos(block, var281, var27, zD, var251[1]);
            var271.setColorOpaque_F(0.6F, 0.6F, 0.6F);
            renderblocks.renderFaceXNeg(block, var281, var27, zD, var251[4]);
            renderblocks.renderFaceXPos(block, var281, var27, zD, var251[5]);
            if((renderSide & 16) != 0) {
               var271.setColorOpaque_F(0.8F, 0.8F, 0.8F);
               renderblocks.renderFaceZNeg(block, var281, (double)y, zD, var251[2]);
            }

            if((renderSide & 32) != 0) {
               var271.setColorOpaque_F(0.8F, 0.8F, 0.8F);
               renderblocks.renderFaceZPos(block, var281, var27, zD, var251[3]);
            }
         } else {
            if((connectivity & 1) == 0) {
               block.setBlockBounds(sp, sp, sp, sp + th, sp + th, sp + th);
               renderblocks.setRenderBoundsFromBlock(block);
               var271.setColorOpaque_F(0.6F, 0.6F, 0.6F);
               renderblocks.renderFaceXNeg(block, var281, var27, zD, var251[4]);
            } else {
               block.setBlockBounds(0.0F, sp, sp, sp, sp + th, sp + th);
               renderblocks.setRenderBoundsFromBlock(block);
               var271.setColorOpaque_F(0.5F, 0.5F, 0.5F);
               renderblocks.renderFaceYNeg(block, var281, var27, zD, var251[0]);
               var271.setColorOpaque_F(1.0F, 1.0F, 1.0F);
               renderblocks.renderFaceYPos(block, var281, var27, zD, var251[1]);
               var271.setColorOpaque_F(0.8F, 0.8F, 0.8F);
               renderblocks.renderFaceZNeg(block, var281, var27, zD, var251[2]);
               renderblocks.renderFaceZPos(block, var281, (double)y, zD, var251[3]);
               if((renderSide & 1) != 0) {
                  var271.setColorOpaque_F(0.6F, 0.6F, 0.6F);
                  renderblocks.renderFaceXNeg(block, var281, var27, zD, var251[4]);
               }
            }

            if((connectivity & 2) == 0) {
               block.setBlockBounds(sp, sp, sp, sp + th, sp + th, sp + th);
               renderblocks.setRenderBoundsFromBlock(block);
               var271.setColorOpaque_F(0.6F, 0.6F, 0.6F);
               renderblocks.renderFaceXPos(block, var281, var27, zD, var251[5]);
            } else {
               block.setBlockBounds(sp + th, sp, sp, 1.0F, sp + th, sp + th);
               renderblocks.setRenderBoundsFromBlock(block);
               var271.setColorOpaque_F(0.5F, 0.5F, 0.5F);
               renderblocks.renderFaceYNeg(block, var281, var27, zD, var251[0]);
               var271.setColorOpaque_F(1.0F, 1.0F, 1.0F);
               renderblocks.renderFaceYPos(block, var281, var27, zD, var251[1]);
               var271.setColorOpaque_F(0.8F, 0.8F, 0.8F);
               renderblocks.renderFaceZNeg(block, var281, var27, zD, var251[2]);
               renderblocks.renderFaceZPos(block, var281, (double)y, zD, var251[3]);
               if((renderSide & 2) != 0) {
                  var271.setColorOpaque_F(0.6F, 0.6F, 0.6F);
                  renderblocks.renderFaceXPos(block, var281, var27, zD, var251[5]);
               }
            }

            if((connectivity & 4) == 0) {
               block.setBlockBounds(sp, sp, sp, sp + th, sp + th, sp + th);
               renderblocks.setRenderBoundsFromBlock(block);
               var271.setColorOpaque_F(0.5F, 0.5F, 0.5F);
               renderblocks.renderFaceYNeg(block, var281, var27, zD, var251[0]);
            } else {
               block.setBlockBounds(sp, 0.0F, sp, sp + th, sp, sp + th);
               renderblocks.setRenderBoundsFromBlock(block);
               var271.setColorOpaque_F(0.8F, 0.8F, 0.8F);
               renderblocks.renderFaceZNeg(block, var281, var27, zD, var251[2]);
               renderblocks.renderFaceZPos(block, var281, (double)y, zD, var251[3]);
               var271.setColorOpaque_F(0.6F, 0.6F, 0.6F);
               renderblocks.renderFaceXNeg(block, var281, var27, zD, var251[4]);
               renderblocks.renderFaceXPos(block, var281, var27, zD, var251[5]);
               if((renderSide & 4) != 0) {
                  var271.setColorOpaque_F(0.5F, 0.5F, 0.5F);
                  renderblocks.renderFaceYNeg(block, var281, var27, zD, var251[0]);
               }
            }

            if((connectivity & 8) == 0) {
               block.setBlockBounds(sp, sp, sp, sp + th, sp + th, sp + th);
               renderblocks.setRenderBoundsFromBlock(block);
               var271.setColorOpaque_F(1.0F, 1.0F, 1.0F);
               renderblocks.renderFaceYPos(block, var281, var27, zD, var251[1]);
            } else {
               block.setBlockBounds(sp, sp + th, sp, sp + th, 1.0F, sp + th);
               renderblocks.setRenderBoundsFromBlock(block);
               var271.setColorOpaque_F(0.8F, 0.8F, 0.8F);
               renderblocks.renderFaceZNeg(block, var281, var27, zD, var251[2]);
               renderblocks.renderFaceZPos(block, var281, (double)y, zD, var251[3]);
               var271.setColorOpaque_F(0.6F, 0.6F, 0.6F);
               renderblocks.renderFaceXNeg(block, var281, var27, zD, var251[4]);
               renderblocks.renderFaceXPos(block, var281, var27, zD, var251[5]);
               if((renderSide & 8) != 0) {
                  var271.setColorOpaque_F(1.0F, 1.0F, 1.0F);
                  renderblocks.renderFaceYPos(block, var281, var27, zD, var251[1]);
               }
            }

            if((connectivity & 16) == 0) {
               block.setBlockBounds(sp, sp, sp, sp + th, sp + th, sp + th);
               renderblocks.setRenderBoundsFromBlock(block);
               var271.setColorOpaque_F(0.8F, 0.8F, 0.8F);
               renderblocks.renderFaceZNeg(block, var281, (double)y, zD, var251[2]);
            } else {
               block.setBlockBounds(sp, sp, 0.0F, sp + th, sp + th, sp);
               renderblocks.setRenderBoundsFromBlock(block);
               var271.setColorOpaque_F(0.5F, 0.5F, 0.5F);
               renderblocks.renderFaceYNeg(block, var281, var27, zD, var251[0]);
               var271.setColorOpaque_F(1.0F, 1.0F, 1.0F);
               renderblocks.renderFaceYPos(block, var281, var27, zD, var251[1]);
               var271.setColorOpaque_F(0.6F, 0.6F, 0.6F);
               renderblocks.renderFaceXNeg(block, var281, var27, zD, var251[4]);
               renderblocks.renderFaceXPos(block, var281, var27, zD, var251[5]);
               if((renderSide & 16) != 0) {
                  var271.setColorOpaque_F(0.8F, 0.8F, 0.8F);
                  renderblocks.renderFaceZNeg(block, var281, (double)y, zD, var251[2]);
               }
            }

            if((connectivity & 32) == 0) {
               block.setBlockBounds(sp, sp, sp, sp + th, sp + th, sp + th);
               renderblocks.setRenderBoundsFromBlock(block);
               var271.setColorOpaque_F(0.8F, 0.8F, 0.8F);
               renderblocks.renderFaceZPos(block, var281, var27, zD, var251[3]);
            } else {
               block.setBlockBounds(sp, sp, sp + th, sp + th, sp + th, 1.0F);
               renderblocks.setRenderBoundsFromBlock(block);
               var271.setColorOpaque_F(0.5F, 0.5F, 0.5F);
               renderblocks.renderFaceYNeg(block, var281, var27, zD, var251[0]);
               var271.setColorOpaque_F(1.0F, 1.0F, 1.0F);
               renderblocks.renderFaceYPos(block, var281, var27, zD, var251[1]);
               var271.setColorOpaque_F(0.6F, 0.6F, 0.6F);
               renderblocks.renderFaceXNeg(block, var281, var27, zD, var251[4]);
               renderblocks.renderFaceXPos(block, var281, var27, zD, var251[5]);
               if((renderSide & 32) != 0) {
                  var271.setColorOpaque_F(0.8F, 0.8F, 0.8F);
                  renderblocks.renderFaceZPos(block, var281, var27, zD, var251[3]);
               }
            }
         }

         block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
         renderblocks.setRenderBoundsFromBlock(block);
         return true;
      }
   }

   public boolean shouldRender3DInInventory() {
      return false;
   }

   public int getRenderId() {
      return renderId;
   }

}
