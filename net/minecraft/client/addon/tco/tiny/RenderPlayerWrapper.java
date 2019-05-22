package net.minecraft.client.addon.tco.tiny;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLiving;
import org.lwjgl.opengl.GL11;

public class RenderPlayerWrapper extends RenderPlayer {

   protected void renderLivingLabel(EntityLiving par1EntityLiving, String par2Str, double par3, double par5, double par7, int par9) {
      FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
      par2Str = par2Str + "00000.." + par3;
      double d3 = par1EntityLiving.getDistanceSqToEntity(super.renderManager.livingPlayer);
      float healthLength;
      float healthHeight;
      if(d3 <= (double)(par9 * par9)) {
         fontrenderer = this.getFontRendererFromRenderManager();
         healthLength = 1.6F;
         healthHeight = 0.026666671F;
         GL11.glPushMatrix();
         GL11.glTranslatef((float)par3 + 0.0F, (float)par5 + par1EntityLiving.height + 0.5F, (float)par7);
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-super.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(super.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
         GL11.glScalef(-0.026666671F, -0.026666671F, 0.026666671F);
         GL11.glDisable(2896);
         GL11.glDepthMask(false);
         GL11.glDisable(2929);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         Tessellator p1111 = Tessellator.instance;
         boolean t1111 = false;
         GL11.glDisable(3553);
         p1111.startDrawingQuads();
         int rest1111 = fontrenderer.getStringWidth(par2Str) / 2;
         p1111.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
         p1111.addVertex((double)(-rest1111 - 1), -1.0D, 0.0D);
         p1111.addVertex((double)(-rest1111 - 1), 8.0D, 0.0D);
         p1111.addVertex((double)(rest1111 + 1), 8.0D, 0.0D);
         p1111.addVertex((double)(rest1111 + 1), -1.0D, 0.0D);
         p1111.draw();
         GL11.glEnable(3553);
         fontrenderer.drawString(par2Str, -fontrenderer.getStringWidth(par2Str) / 2, 0, 553648127);
         GL11.glEnable(2929);
         GL11.glDepthMask(true);
         fontrenderer.drawString(par2Str, -fontrenderer.getStringWidth(par2Str) / 2, 0, -1);
         GL11.glEnable(2896);
         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glPopMatrix();
      }

      if(par1EntityLiving != super.renderManager.livingPlayer) {
         healthLength = 1.0F;
         healthHeight = 0.25F;
         EntityClientPlayerMP p11111 = Minecraft.getMinecraft().thePlayer;
         GL11.glDisable(2896);
         GL11.glPushMatrix();
         GL11.glTranslatef((float)par3 + 0.0F, (float)par5 + par1EntityLiving.height + 0.5F, (float)par7);
         GL11.glRotatef(-super.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(super.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(-1.0D, 1.0D, 1.0D);
         Tessellator t11111 = Tessellator.instance;
         Minecraft.getMinecraft().renderEngine.bindTexture("/mods/1.png");
         float rest11111 = 2.7F;
         healthHeight = 0.25F;
         GL11.glTranslated(-1.2999999523162842D, 0.0D, 0.0D);
         t11111.startDrawingQuads();
         t11111.setColorOpaque(255, 0, 0);
         t11111.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
         t11111.addVertexWithUV(2.700000047683716D, 0.0D, 0.0D, 1.0D, 0.0D);
         t11111.addVertexWithUV(2.700000047683716D, (double)healthHeight, 0.0D, 1.0D, 1.0D);
         t11111.addVertexWithUV(0.0D, (double)healthHeight, 0.0D, 0.0D, 1.0D);
         t11111.draw();
         GL11.glPopMatrix();
         GL11.glEnable(2896);
      }

   }
}
