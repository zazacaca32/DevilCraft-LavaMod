package net.minecraft.client.addon.tco.tiny.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXBurst extends EntityFX {

   public FXBurst(World world, double d, double d1, double d2, float f) {
      super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
      super.particleRed = 1.0F;
      super.particleGreen = 1.0F;
      super.particleBlue = 1.0F;
      super.particleGravity = 0.0F;
      super.motionZ = 0.0D;
      super.motionY = 0.0D;
      super.motionX = 0.0D;
      super.particleScale *= f;
      super.particleMaxAge = 31;
      super.noClip = false;
      this.setSize(0.01F, 0.01F);
   }

   public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
      tessellator.draw();
      GL11.glPushMatrix();
      GL11.glDepthMask(false);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 1);
      Minecraft.getMinecraft().renderEngine.bindTexture("/mods/fx/burst.png");
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
      float var8 = (float)(super.particleAge % 32) / 32.0F;
      float var9 = var8 + 0.03125F;
      float var10 = 0.0F;
      float var11 = 1.0F;
      float var12 = super.particleScale;
      float var13 = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)f - EntityFX.interpPosX);
      float var14 = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)f - EntityFX.interpPosY);
      float var15 = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)f - EntityFX.interpPosZ);
      float var16 = 1.0F;
      tessellator.startDrawingQuads();
      tessellator.setBrightness(240);
      tessellator.setColorRGBA_F(super.particleRed * 1.0F, super.particleGreen * 1.0F, super.particleBlue * 1.0F, 1.0F);
      tessellator.addVertexWithUV((double)(var13 - f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 - f3 * var12 - f5 * var12), (double)var9, 1.0D);
      tessellator.addVertexWithUV((double)(var13 - f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 - f3 * var12 + f5 * var12), (double)var9, 0.0D);
      tessellator.addVertexWithUV((double)(var13 + f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 + f3 * var12 + f5 * var12), (double)var8, 0.0D);
      tessellator.addVertexWithUV((double)(var13 + f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 + f3 * var12 - f5 * var12), (double)var8, 1.0D);
      tessellator.draw();
      GL11.glDisable(3042);
      GL11.glDepthMask(true);
      GL11.glPopMatrix();
      Minecraft.getMinecraft().renderEngine.bindTexture("/particles.png");
      tessellator.startDrawingQuads();
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

   }

   public void setGravity(float value) {
      super.particleGravity = value;
   }
}
