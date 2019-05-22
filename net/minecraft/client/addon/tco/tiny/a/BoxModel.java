package net.minecraft.client.addon.tco.tiny.a;

import net.minecraft.client.addon.tco.tiny.a.Gui10Lollipop;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;

public abstract class BoxModel {

   Gui10Lollipop base;
   float x;
   float y;
   float width;
   float height;
   float rotate;
   Rectangle bounds;
   protected float zLevel = 0.0F;
   boolean isDead = false;
   private boolean isDebug = true;


   public BoxModel(Gui10Lollipop base, float x, float y, float width, float height) {
      this.base = base;
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.bounds = new Rectangle((int)x, (int)y, (int)width, (int)height);
   }

   public void tick() {}

   public void render() {}

   public void mouseClicked(int par1, int par2, int par3) {}

   public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
      float f = 0.00390625F;
      float f2 = 0.00390625F;
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      float xz1 = (float)(par5 / 2);
      float yz1 = (float)(par6 / 2);
      tessellator.addVertexWithUV((double)((float)par1 - xz1 + 1.0F), (double)((float)(par2 + par6) - yz1 + 1.0F), (double)this.zLevel, (double)((float)(par3 + 0) * 0.00390625F), (double)((float)(par4 + par6) * 0.00390625F));
      tessellator.addVertexWithUV((double)((float)(par1 + par5) - xz1 + 1.0F), (double)((float)(par2 + par6) - yz1 + 1.0F), (double)this.zLevel, (double)((float)(par3 + par5) * 0.00390625F), (double)((float)(par4 + par6) * 0.00390625F));
      tessellator.addVertexWithUV((double)((float)(par1 + par5) - xz1 + 1.0F), (double)((float)(par2 + 0) - yz1 + 1.0F), (double)this.zLevel, (double)((float)(par3 + par5) * 0.00390625F), (double)((float)(par4 + 0) * 0.00390625F));
      tessellator.addVertexWithUV((double)((float)par1 - xz1 + 1.0F), (double)((float)(par2 + 0) - yz1 + 1.0F), (double)this.zLevel, (double)((float)(par3 + 0) * 0.00390625F), (double)((float)(par4 + 0) * 0.00390625F));
      tessellator.draw();
   }

   protected void drawGradientRectTexture(int x, int y, int u, int v, int weigth, int heigth, int color, int color1) {
      float f = (float)(color >> 24 & 255) / 255.0F;
      float f2 = (float)(color >> 16 & 255) / 255.0F;
      float f3 = (float)(color >> 8 & 255) / 255.0F;
      float f4 = (float)(color & 255) / 255.0F;
      float f5 = (float)(color1 >> 24 & 255) / 255.0F;
      float f6 = (float)(color1 >> 16 & 255) / 255.0F;
      float f7 = (float)(color1 >> 8 & 255) / 255.0F;
      float f8 = (float)(color1 & 255) / 255.0F;
      GL11.glEnable(3042);
      GL11.glDisable(3008);
      GL11.glBlendFunc(770, 771);
      GL11.glShadeModel(7425);
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      tessellator.setColorRGBA_F(f2, f3, f4, f);
      float f9 = 0.00217625F;
      float f10 = 0.00343625F;
      tessellator.addVertexWithUV((double)(x + weigth), (double)(y + 0), (double)this.zLevel, (double)((float)(u + weigth) * 0.00217625F), (double)((float)(v + 0) * 0.00343625F));
      tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0), (double)this.zLevel, (double)((float)(u + 0) * 0.00217625F), (double)((float)(v + 0) * 0.00343625F));
      tessellator.setColorRGBA_F(f6, f7, f8, f5);
      tessellator.addVertexWithUV((double)(x + 0), (double)(y + heigth), (double)this.zLevel, (double)((float)(u + 0) * 0.00217625F), (double)((float)(v + heigth) * 0.00343625F));
      tessellator.addVertexWithUV((double)(x + weigth), (double)(y + heigth), (double)this.zLevel, (double)((float)(u + weigth) * 0.00217625F), (double)((float)(v + heigth) * 0.00343625F));
      tessellator.draw();
      GL11.glShadeModel(7424);
      GL11.glDisable(3042);
      GL11.glEnable(3008);
   }
}
