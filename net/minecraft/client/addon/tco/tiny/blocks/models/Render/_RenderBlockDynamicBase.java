package net.minecraft.client.addon.tco.tiny.blocks.models.Render;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class _RenderBlockDynamicBase extends TileEntitySpecialRenderer {

   private String[] texture;


   public _RenderBlockDynamicBase(String ... TexturePatch) {
      this.texture = TexturePatch;
   }

   public void renderTileEntityAt(TileEntity tileentity, double d1, double d2, double d3, float f) {
      this.renderTileEntity((BaseTileEntityBlock)tileentity, d1, d2, d3, f);
   }

   public void renderTileEntity(BaseTileEntityBlock tileEntity, double dl, double d2, double d3, float f) {
      int ii = tileEntity.getFacing();
      int metadata = tileEntity.getBlockMetadata();
      GL11.glPushMatrix();
      GL11.glTranslatef((float)dl + 0.5F, (float)d2 + 1.5F, (float)d3 + 0.5F);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      boolean iii = false;
      int iii1 = ii * 90;
      GL11.glRotatef((float)iii1, 0.0F, 1.0F, 0.0F);
      this.bindTextureByName(this.texture[metadata]);
      if(tileEntity.newRenderModel(metadata) != null) {
         tileEntity.newRenderModel(metadata).render();
      }

      GL11.glPopMatrix();
   }
}
