package net.minecraft.client.addon.tco.tiny.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.src.ModLoader;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXBeam extends EntityFX {

   public int particle = 16;
   boolean updated = false;
   double movX = 0.0D;
   double movY = 0.0D;
   double movZ = 0.0D;
   private float length = 0.0F;
   private float rotYaw = 0.0F;
   private float rotPitch = 0.0F;
   private float prevYaw = 0.0F;
   private float prevPitch = 0.0F;
   private Entity targetEntity = null;
   private double tX = 0.0D;
   private double tY = 0.0D;
   private double tZ = 0.0D;
   private int type = 0;
   private float endMod = 1.0F;
   private boolean reverse = false;
   private boolean pulse = true;
   private int rotationspeed = 5;
   private float prevSize = 0.0F;


   public FXBeam(World par1World, double x, double y, double z, double tx, double ty, double tz, float red, float green, float blue, int age) {
      super(par1World, x, y, z, 0.0D, 0.0D, 0.0D);
      super.particleRed = red;
      super.particleGreen = green;
      super.particleBlue = blue;
      this.setSize(0.02F, 0.02F);
      super.noClip = true;
      super.motionX = 0.0D;
      super.motionY = 0.0D;
      super.motionZ = 0.0D;
      this.tX = tx;
      this.tY = ty;
      this.tZ = tz;
      float xd = (float)(super.posX - this.tX);
      float yd = (float)(super.posY - this.tY);
      float zd = (float)(super.posZ - this.tZ);
      this.length = MathHelper.sqrt_float(xd * xd + yd * yd + zd * zd);
      double var7 = (double)MathHelper.sqrt_double((double)(xd * xd + zd * zd));
      this.rotYaw = (float)(Math.atan2((double)xd, (double)zd) * 180.0D / 3.141592653589793D);
      this.rotPitch = (float)(Math.atan2((double)yd, var7) * 180.0D / 3.141592653589793D);
      this.prevYaw = this.rotYaw;
      this.prevPitch = this.rotPitch;
      super.particleMaxAge = age;
      EntityLiving renderentity = ModLoader.getMinecraftInstance().renderViewEntity;
      byte visibleDistance = 50;
      if(!ModLoader.getMinecraftInstance().gameSettings.fancyGraphics) {
         visibleDistance = 25;
      }

      if(renderentity.getDistance(super.posX, super.posY, super.posZ) > (double)visibleDistance) {
         super.particleMaxAge = 0;
      }

   }

   public FXBeam(World par1World, double x, double y, double z, Entity entity, float red, float green, float blue, int age) {
      super(par1World, x, y, z, 0.0D, 0.0D, 0.0D);
      super.particleRed = red;
      super.particleGreen = green;
      super.particleBlue = blue;
      this.setSize(0.02F, 0.02F);
      super.noClip = true;
      super.motionX = 0.0D;
      super.motionY = 0.0D;
      super.motionZ = 0.0D;
      this.targetEntity = entity;
      this.tX = this.targetEntity.posX;
      this.tY = this.targetEntity.posY + (double)this.targetEntity.getEyeHeight() - (double)(this.targetEntity.height / 2.0F);
      this.tZ = this.targetEntity.posZ;
      float xd = (float)(super.posX - this.tX);
      float yd = (float)(super.posY - this.tY);
      float zd = (float)(super.posZ - this.tZ);
      this.length = MathHelper.sqrt_float(xd * xd + yd * yd + zd * zd);
      double var7 = (double)MathHelper.sqrt_double((double)(xd * xd + zd * zd));
      this.rotYaw = (float)(Math.atan2((double)xd, (double)zd) * 180.0D / 3.141592653589793D);
      this.rotPitch = (float)(Math.atan2((double)yd, var7) * 180.0D / 3.141592653589793D);
      this.prevYaw = this.rotYaw;
      this.prevPitch = this.rotPitch;
      super.particleMaxAge = age;
      EntityLiving renderentity = ModLoader.getMinecraftInstance().renderViewEntity;
      byte visibleDistance = 50;
      if(!ModLoader.getMinecraftInstance().gameSettings.fancyGraphics) {
         visibleDistance = 25;
      }

      if(renderentity.getDistance(super.posX, super.posY, super.posZ) > (double)visibleDistance) {
         super.particleMaxAge = 0;
      }

   }

   public void updateBeam(double xs, double ys, double zs, double x, double y, double z) {
      this.movX = xs;
      this.movY = ys;
      this.movZ = zs;
      this.tX = x;
      this.tY = y;

      for(this.tZ = z; super.particleMaxAge - super.particleAge < 4; ++super.particleMaxAge) {
         ;
      }

      this.updated = true;
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      if(this.updated) {
         super.posX = this.movX;
         super.posY = this.movY;
         super.posZ = this.movZ;
         this.updated = false;
      }

      this.prevYaw = this.rotYaw;
      this.prevPitch = this.rotPitch;
      if(this.targetEntity != null) {
         this.tX = this.targetEntity.posX;
         this.tY = this.targetEntity.posY + (double)this.targetEntity.getEyeHeight() - (double)(this.targetEntity.height / 2.0F);
         this.tZ = this.targetEntity.posZ;
      }

      float xd = (float)(super.posX - this.tX);
      float yd = (float)(super.posY - this.tY);
      float zd = (float)(super.posZ - this.tZ);
      this.length = MathHelper.sqrt_float(xd * xd + yd * yd + zd * zd);
      double var7 = (double)MathHelper.sqrt_double((double)(xd * xd + zd * zd));
      this.rotYaw = (float)(Math.atan2((double)xd, (double)zd) * 180.0D / 3.141592653589793D);
      this.rotPitch = (float)(Math.atan2((double)yd, var7) * 180.0D / 3.141592653589793D);
      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

   }

   public void setRGB(float r, float g, float b) {
      super.particleRed = r;
      super.particleGreen = g;
      super.particleBlue = b;
   }

   public void setType(int type) {
      this.type = type;
   }

   public void setEndMod(float endMod) {
      this.endMod = endMod;
   }

   public void setReverse(boolean reverse) {
      this.reverse = reverse;
   }

   public void setPulse(boolean pulse) {
      this.pulse = pulse;
   }

   public void setRotationspeed(int rotationspeed) {
      this.rotationspeed = rotationspeed;
   }

   public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
      tessellator.draw();
      GL11.glPushMatrix();
      float var9 = 1.0F;
      float slide = (float)super.worldObj.getTotalWorldTime();
      float rot = (float)(super.worldObj.provider.getWorldTime() % (long)(360 / this.rotationspeed) * (long)this.rotationspeed) + (float)this.rotationspeed * f;
      float size = 1.0F;
      if(this.pulse) {
         size = Math.min((float)super.particleAge / 4.0F, 1.0F);
         size = this.prevSize + (size - this.prevSize) * f;
      }

      float op = 0.4F;
      if(this.pulse && super.particleMaxAge - super.particleAge <= 4) {
         op = 0.4F - (float)(4 - (super.particleMaxAge - super.particleAge)) * 0.1F;
      }

      switch(this.type) {
      case 1:
         Minecraft.getMinecraft().renderEngine.bindTexture("/mods/fx/beam1.png");
         break;
      case 2:
         Minecraft.getMinecraft().renderEngine.bindTexture("/mods/fx/beam2.png");
         break;
      default:
         Minecraft.getMinecraft().renderEngine.bindTexture("/mods/fx/beam.png");
      }

      GL11.glTexParameterf(3553, 10242, 10497.0F);
      GL11.glTexParameterf(3553, 10243, 10497.0F);
      GL11.glDisable(2884);
      float var10 = slide + f;
      if(this.reverse) {
         var10 *= -1.0F;
      }

      float var11 = -var10 * 0.2F - (float)MathHelper.floor_float(-var10 * 0.1F);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 1);
      GL11.glDepthMask(false);
      float xx = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)f - EntityFX.interpPosX);
      float yy = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)f - EntityFX.interpPosY);
      float zz = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)f - EntityFX.interpPosZ);
      GL11.glTranslated((double)xx, (double)yy, (double)zz);
      float ry = this.prevYaw + (this.rotYaw - this.prevYaw) * f;
      float rp = this.prevPitch + (this.rotPitch - this.prevPitch) * f;
      GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(180.0F + ry, 0.0F, 0.0F, -1.0F);
      GL11.glRotatef(rp, 1.0F, 0.0F, 0.0F);
      double var12 = -0.15D * (double)size;
      double var13 = 0.15D * (double)size;
      double var44b = -0.15D * (double)size * (double)this.endMod;
      double var17b = 0.15D * (double)size * (double)this.endMod;
      GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);

      for(int t = 0; t < 3; ++t) {
         double var14 = (double)(this.length * size * 1.0F);
         double var15 = 0.0D;
         double var16 = 1.0D;
         double var17 = (double)(-1.0F + var11 + (float)t / 3.0F);
         double var18 = (double)(this.length * size * 1.0F) + var17;
         GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
         tessellator.startDrawingQuads();
         tessellator.setBrightness(200);
         tessellator.setColorRGBA_F(super.particleRed, super.particleGreen, super.particleBlue, op);
         tessellator.addVertexWithUV(var44b, var14, 0.0D, 1.0D, var18);
         tessellator.addVertexWithUV(var12, 0.0D, 0.0D, 1.0D, var17);
         tessellator.addVertexWithUV(var13, 0.0D, 0.0D, 0.0D, var17);
         tessellator.addVertexWithUV(var17b, var14, 0.0D, 0.0D, var18);
         tessellator.draw();
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glEnable(2884);
      GL11.glPopMatrix();
      Minecraft.getMinecraft().renderEngine.bindTexture("/particles.png");
      tessellator.startDrawingQuads();
      this.prevSize = size;
   }
}
