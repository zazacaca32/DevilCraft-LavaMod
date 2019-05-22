package net.minecraft.client.addon.tco.tiny.blocks.models.Render;

import java.util.ArrayList;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class RenderBlockStaticBase extends TileEntitySpecialRenderer {

   protected ArrayList Model;
   private String[] texture;
   private int rotateifAngle = 0;


   public RenderBlockStaticBase(ArrayList Model, int rotateifAngle, String ... texture) {
      this.Model = Model;
      this.texture = texture;
      this.rotateifAngle = rotateifAngle;
   }

   public void renderTileEntityAt(TileEntity tileentity, double d1, double d2, double d3, float f) {
      this.renderTileEntity((BaseTileEntityBlock)tileentity, d1, d2, d3, f);
   }

   public void renderTileEntity(BaseTileEntityBlock tileEntity, double dl, double d2, double d3, float f) {
      int ii = tileEntity.getFacing();
      int metadata = tileEntity.getBlockMetadata();
      byte metadata1 = 0;
      GL11.glPushMatrix();
      GL11.glTranslatef((float)dl + 0.5F, (float)d2 + 1.5F, (float)d3 + 0.5F);
      if(this.rotateifAngle == 0) {
         GL11.glRotatef(180.0F, 1.0F, 0.0F, 1.0F);
         GL11.glRotatef((float)ii, 0.0F, 90.0F, 0.0F);
      } else {
         GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
         byte iii = 0;
         int iii1 = ii * 90;
         GL11.glRotatef((float)iii, 0.0F, 1.0F, 0.0F);
      }

      this.bindTextureByName(this.texture[metadata1]);
      if(tileEntity.newRenderModel(metadata1) != null) {
         tileEntity.newRenderModel(metadata1).render();
      } else {
         ((BaseModelBlock)this.Model.get(metadata1)).render();
      }

      GL11.glPopMatrix();
   }
}
