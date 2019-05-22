package net.minecraft.client.addon.tco.tiny.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLiving;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXWispArcing extends EntityFX {

   private double field_70568_aq;
   private double field_70567_ar;
   private double field_70566_as;
   float moteParticleScale;
   int moteHalfLife;
   public boolean tinkle;
   public int blendmode;


   public FXWispArcing(World world, double d, double d1, double d2, float f, float red, float green, float blue) {
      super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
      this.tinkle = false;
      this.blendmode = 1;
      if(red == 0.0F) {
         red = 1.0F;
      }

      super.posX = d;
      this.field_70568_aq = d;
      super.posY = d1;
      this.field_70567_ar = d1;
      super.posZ = d2;
      this.field_70566_as = d2;
      super.particleRed = red;
      super.particleGreen = green;
      super.particleBlue = blue;
      super.particleGravity = 0.0F;
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

   public FXWispArcing(World world, double d, double d1, double d2, double x, double y, double z, float f, float red, float green, float blue) {
      this(world, d, d1, d2, f, red, green, blue);
      super.motionX = x - d;
      super.motionY = y - d1;
      super.motionZ = z - d2;
      this.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
   }

   public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
      float agescale = 0.0F;
      agescale = (float)(super.particleAge / this.moteHalfLife);
      if(agescale > 1.0F) {
         agescale = 2.0F - agescale;
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

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      float var1 = (float)(super.particleAge / super.particleMaxAge);
      float var2 = (float)super.particleAge / ((float)super.particleMaxAge / 2.0F);
      var1 = 1.0F - var1;
      var2 = 1.0F - var2;
      var2 *= var2;
      super.posX = this.field_70568_aq + super.motionX * (double)var1;
      super.posY = this.field_70567_ar + super.motionY * (double)var1 - (double)var2 + 1.0D;
      super.posZ = this.field_70566_as + super.motionZ * (double)var1;
      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

   }

   public void setGravity(float value) {
      super.particleGravity = value;
   }
}
