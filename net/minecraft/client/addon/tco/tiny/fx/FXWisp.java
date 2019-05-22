package net.minecraft.client.addon.tco.tiny.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLiving;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXWisp extends EntityFX {

   public boolean shrink;
   float moteParticleScale;
   int moteHalfLife;
   public boolean tinkle;
   public int blendmode;


   public FXWisp(World world, double d, double d1, double d2, float f, float f1, float f2) {
      this(world, d, d1, d2, 1.0F, f, f1, f2);
   }

   public FXWisp(World world, double d, double d1, double d2, float f, float red, float green, float blue) {
      super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
      this.shrink = false;
      this.tinkle = false;
      this.blendmode = 1;
      if(red == 0.0F) {
         red = 1.0F;
      }

      super.particleRed = red;
      super.particleGreen = green;
      super.particleBlue = blue;
      super.particleGravity = 0.0F;
      super.motionZ = 0.0D;
      super.motionY = 0.0D;
      super.motionX = 0.0D;
      super.particleScale *= f;
      this.moteParticleScale = super.particleScale;
      super.particleMaxAge = (int)(36.0D / (Math.random() * 0.3D + 0.7D));
      this.moteHalfLife = super.particleMaxAge / 2;
      super.noClip = false;
      this.setSize(0.01F, 0.01F);
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

   public FXWisp(World world, double d, double d1, double d2, float f, int type) {
      this(world, d, d1, d2, f, 0.0F, 0.0F, 0.0F);
      switch(type) {
      case 0:
         super.particleRed = 0.75F + world.rand.nextFloat() * 0.25F;
         super.particleGreen = 0.25F + world.rand.nextFloat() * 0.25F;
         super.particleBlue = 0.75F + world.rand.nextFloat() * 0.25F;
         break;
      case 1:
         super.particleRed = 0.5F + world.rand.nextFloat() * 0.3F;
         super.particleGreen = 0.5F + world.rand.nextFloat() * 0.3F;
         super.particleBlue = 0.2F;
         break;
      case 2:
         super.particleRed = 0.2F;
         super.particleGreen = 0.2F;
         super.particleBlue = 0.7F + world.rand.nextFloat() * 0.3F;
         break;
      case 3:
         super.particleRed = 0.2F;
         super.particleGreen = 0.7F + world.rand.nextFloat() * 0.3F;
         super.particleBlue = 0.2F;
         break;
      case 4:
         super.particleRed = 0.7F + world.rand.nextFloat() * 0.3F;
         super.particleGreen = 0.2F;
         super.particleBlue = 0.2F;
         break;
      case 5:
         this.blendmode = 771;
         super.particleRed = world.rand.nextFloat() * 0.1F;
         super.particleGreen = world.rand.nextFloat() * 0.1F;
         super.particleBlue = world.rand.nextFloat() * 0.1F;
         break;
      case 6:
         super.particleRed = 0.8F + world.rand.nextFloat() * 0.2F;
         super.particleGreen = 0.8F + world.rand.nextFloat() * 0.2F;
         super.particleBlue = 0.8F + world.rand.nextFloat() * 0.2F;
      }

   }

   public FXWisp(World world, double d, double d1, double d2, double x, double y, double z, float f, int type) {
      this(world, d, d1, d2, f, type);
      if(super.particleMaxAge > 0) {
         double dx = x - super.posX;
         double dy = y - super.posY;
         double dz = z - super.posZ;
         super.motionX = dx / (double)super.particleMaxAge;
         super.motionY = dy / (double)super.particleMaxAge;
         super.motionZ = dz / (double)super.particleMaxAge;
      }

   }

   public FXWisp(World world, double d, double d1, double d2, double x, double y, double z, float f, float red, float green, float blue) {
      this(world, d, d1, d2, f, red, green, blue);
      if(super.particleMaxAge > 0) {
         double dx = x - super.posX;
         double dy = y - super.posY;
         double dz = z - super.posZ;
         super.motionX = dx / (double)super.particleMaxAge;
         super.motionY = dy / (double)super.particleMaxAge;
         super.motionZ = dz / (double)super.particleMaxAge;
      }

   }

   public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
      float agescale = 0.0F;
      if(super.particleMaxAge > 0) {
         if(this.shrink) {
            agescale = (float)((super.particleMaxAge - super.particleAge) / super.particleMaxAge);
         } else {
            agescale = (float)(super.particleAge / this.moteHalfLife);
            if(agescale > 1.0F) {
               agescale = 2.0F - agescale;
            }
         }

         super.particleScale = this.moteParticleScale * agescale;
         tessellator.draw();
         GL11.glPushMatrix();
         GL11.glDepthMask(false);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, this.blendmode);
         Minecraft.getMinecraft().renderEngine.bindTexture("/mods/fx/p_large.png");
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
         float f6 = 0.5F * super.particleScale;
         float f7 = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)f - EntityFX.interpPosX);
         float f8 = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)f - EntityFX.interpPosY);
         float f9 = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)f - EntityFX.interpPosZ);
         tessellator.startDrawingQuads();
         tessellator.setBrightness(240);
         tessellator.setColorRGBA_F(super.particleRed, super.particleGreen, super.particleBlue, 0.5F);
         tessellator.addVertexWithUV((double)(f7 - f1 * f6 - f4 * f6), (double)(f8 - f2 * f6), (double)(f9 - f3 * f6 - f5 * f6), 0.0D, 1.0D);
         tessellator.addVertexWithUV((double)(f7 - f1 * f6 + f4 * f6), (double)(f8 + f2 * f6), (double)(f9 - f3 * f6 + f5 * f6), 1.0D, 1.0D);
         tessellator.addVertexWithUV((double)(f7 + f1 * f6 + f4 * f6), (double)(f8 + f2 * f6), (double)(f9 + f3 * f6 + f5 * f6), 1.0D, 0.0D);
         tessellator.addVertexWithUV((double)(f7 + f1 * f6 - f4 * f6), (double)(f8 - f2 * f6), (double)(f9 + f3 * f6 - f5 * f6), 0.0D, 0.0D);
         tessellator.draw();
         GL11.glDisable(3042);
         GL11.glDepthMask(true);
         GL11.glPopMatrix();
         Minecraft.getMinecraft().renderEngine.bindTexture("/particles.png");
         tessellator.startDrawingQuads();
      }

   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      if(super.particleAge == 0 && this.tinkle && super.worldObj.rand.nextInt(3) == 0) {
         super.worldObj.playSoundAtEntity(this, "random.orb", 0.02F, 0.5F * ((super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.6F + 2.0F));
      }

      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

      super.motionY -= 0.04D * (double)super.particleGravity;
      super.posX += super.motionX;
      super.posY += super.motionY;
      super.posZ += super.motionZ;
      super.motionX *= 0.9800000190734863D;
      super.motionY *= 0.9800000190734863D;
      super.motionZ *= 0.9800000190734863D;
      if(super.onGround) {
         super.motionX *= 0.699999988079071D;
         super.motionZ *= 0.699999988079071D;
      }

   }

   public void setGravity(float value) {
      super.particleGravity = value;
   }
}
