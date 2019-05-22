package net.minecraft.client.addon.tco.tiny.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLiving;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXBubble extends EntityFX {

   public int particle = 16;
   public double bubblespeed = 0.002D;


   public FXBubble(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, int age) {
      super(par1World, par2, par4, par6, par8, par10, par12);
      super.particleRed = 1.0F;
      super.particleGreen = 0.0F;
      super.particleBlue = 0.5F;
      this.setParticleTextureIndex(32);
      this.setSize(0.02F, 0.02F);
      super.noClip = true;
      super.particleScale *= super.rand.nextFloat() * 0.3F + 0.2F;
      super.motionX = par8 * 0.2000000029802322D + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.02F);
      super.motionY = par10 * 0.2000000029802322D + (double)((float)Math.random() * 0.02F);
      super.motionZ = par12 * 0.2000000029802322D + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.02F);
      super.particleMaxAge = (int)((double)(age + 2) + 8.0D / (Math.random() * 0.8D + 0.2D));
      EntityLiving renderentity = ModLoader.getMinecraftInstance().renderViewEntity;
      byte visibleDistance = 50;
      if(!ModLoader.getMinecraftInstance().gameSettings.fancyGraphics) {
         visibleDistance = 25;
      }

      if(renderentity.getDistance(super.posX, super.posY, super.posZ) > (double)visibleDistance) {
         super.particleMaxAge = 0;
      }

      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
   }

   public void setFroth() {
      super.particleScale *= 0.75F;
      super.particleMaxAge = 4 + super.rand.nextInt(3);
      this.bubblespeed = -0.001D;
      super.motionX /= 5.0D;
      super.motionY /= 10.0D;
      super.motionZ /= 5.0D;
   }

   public void setFroth2() {
      super.particleScale *= 0.75F;
      super.particleMaxAge = 12 + super.rand.nextInt(12);
      this.bubblespeed = -0.005D;
      super.motionX /= 5.0D;
      super.motionY /= 10.0D;
      super.motionZ /= 5.0D;
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      super.motionY += this.bubblespeed;
      if(this.bubblespeed > 0.0D) {
         super.motionX += (double)((super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.01F);
         super.motionZ += (double)((super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.01F);
      }

      super.posX += super.motionX;
      super.posY += super.motionY;
      super.posZ += super.motionZ;
      super.motionX *= 0.8500000238418579D;
      super.motionY *= 0.8500000238418579D;
      super.motionZ *= 0.8500000238418579D;
      if(super.particleMaxAge-- <= 0) {
         this.setDead();
      } else if(super.particleMaxAge <= 2) {
         ++this.particle;
      }

   }

   public void setRGB(float r, float g, float b) {
      super.particleRed = r;
      super.particleGreen = g;
      super.particleBlue = b;
   }

   public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
      tessellator.draw();
      GL11.glPushMatrix();
      GL11.glDepthMask(false);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 1);
      Minecraft.getMinecraft().renderEngine.bindTexture("/mods/fx/particles.png");
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F);
      float var8 = (float)(this.particle % 16) / 16.0F;
      float var9 = var8 + 0.0624375F;
      float var10 = (float)(this.particle / 16) / 16.0F;
      float var11 = var10 + 0.0624375F;
      float var12 = 0.1F * super.particleScale;
      float var13 = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)f - EntityFX.interpPosX);
      float var14 = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)f - EntityFX.interpPosY);
      float var15 = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)f - EntityFX.interpPosZ);
      float var16 = 1.0F;
      tessellator.startDrawingQuads();
      tessellator.setBrightness(240);
      tessellator.setColorRGBA_F(super.particleRed * 1.0F, super.particleGreen * 1.0F, super.particleBlue * 1.0F, 0.66F);
      tessellator.addVertexWithUV((double)(var13 - f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 - f3 * var12 - f5 * var12), (double)var9, (double)var11);
      tessellator.addVertexWithUV((double)(var13 - f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 - f3 * var12 + f5 * var12), (double)var9, (double)var10);
      tessellator.addVertexWithUV((double)(var13 + f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 + f3 * var12 + f5 * var12), (double)var8, (double)var10);
      tessellator.addVertexWithUV((double)(var13 + f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 + f3 * var12 - f5 * var12), (double)var8, (double)var11);
      tessellator.draw();
      GL11.glDisable(3042);
      GL11.glDepthMask(true);
      GL11.glPopMatrix();
      Minecraft.getMinecraft().renderEngine.bindTexture("/particles.png");
      tessellator.startDrawingQuads();
   }
}
