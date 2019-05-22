package net.minecraft.client.addon.tco.tiny.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXBlockRunes extends EntityFX {

   double ofx = 0.0D;
   double ofy = 0.0D;
   float rotation = 0.0F;
   int runeIndex = 0;


   public FXBlockRunes(World world, double d, double d1, double d2, float f1, float f2, float f3, int m) {
      super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
      if(f1 == 0.0F) {
         f1 = 1.0F;
      }

      this.rotation = (float)(super.rand.nextInt(4) * 90);
      super.particleRed = f1;
      super.particleGreen = f2;
      super.particleBlue = f3;
      super.particleGravity = 0.0F;
      super.motionZ = 0.0D;
      super.motionY = 0.0D;
      super.motionX = 0.0D;
      super.particleMaxAge = 3 * m;
      super.noClip = false;
      this.setSize(0.01F, 0.01F);
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      super.noClip = true;
      this.runeIndex = (int)(Math.random() * 16.0D + 160.0D);
      this.ofx = (double)super.rand.nextFloat() * 0.2D;
      this.ofy = -0.3D + (double)super.rand.nextFloat() * 0.6D;
      super.particleScale = (float)(1.0D + super.rand.nextGaussian() * 0.1000000014901161D);
   }

   public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
      tessellator.draw();
      GL11.glPushMatrix();
      Minecraft.getMinecraft().renderEngine.bindTexture("/mods/fx/particles.png");
      GL11.glDepthMask(false);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 1);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, super.particleAlpha / 2.0F);
      float var13 = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)f - EntityFX.interpPosX);
      float var14 = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)f - EntityFX.interpPosY);
      float var15 = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)f - EntityFX.interpPosZ);
      GL11.glTranslated((double)var13, (double)var14, (double)var15);
      GL11.glRotatef(this.rotation, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
      GL11.glTranslated(this.ofx, this.ofy, -0.51D);
      float var16 = (float)(this.runeIndex % 16) / 16.0F;
      float var17 = var16 + 0.0624375F;
      float var18 = (float)(this.runeIndex / 16) / 16.0F;
      float var19 = var18 + 0.0624375F;
      float var20 = 0.3F * super.particleScale;
      float var21 = 1.0F;
      tessellator.startDrawingQuads();
      tessellator.setBrightness(240);
      tessellator.setColorRGBA_F(super.particleRed * 1.0F, super.particleGreen * 1.0F, super.particleBlue * 1.0F, super.particleAlpha / 2.0F);
      tessellator.addVertexWithUV(-0.5D * (double)var20, 0.5D * (double)var20, 0.0D, (double)var17, (double)var19);
      tessellator.addVertexWithUV(0.5D * (double)var20, 0.5D * (double)var20, 0.0D, (double)var17, (double)var18);
      tessellator.addVertexWithUV(0.5D * (double)var20, -0.5D * (double)var20, 0.0D, (double)var16, (double)var18);
      tessellator.addVertexWithUV(-0.5D * (double)var20, -0.5D * (double)var20, 0.0D, (double)var16, (double)var19);
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
      float threshold = (float)super.particleMaxAge / 5.0F;
      super.particleAlpha = (float)super.particleAge <= threshold?(float)super.particleAge / threshold:(float)((super.particleMaxAge - super.particleAge) / super.particleMaxAge);
      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

      super.motionY -= 0.04D * (double)super.particleGravity;
      super.posX += super.motionX;
      super.posY += super.motionY;
      super.posZ += super.motionZ;
   }

   public void setGravity(float value) {
      super.particleGravity = value;
   }
}
