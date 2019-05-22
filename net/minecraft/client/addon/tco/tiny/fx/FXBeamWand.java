package net.minecraft.client.addon.tco.tiny.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXBeamWand extends EntityFX {

   public int particle = 16;
   EntityPlayer player = null;
   private double offset = 0.0D;
   private float length = 0.0F;
   private float rotYaw = 0.0F;
   private float rotPitch = 0.0F;
   private float prevYaw = 0.0F;
   private float prevPitch = 0.0F;
   private Entity targetEntity = null;
   private double tX = 0.0D;
   private double tY = 0.0D;
   private double tZ = 0.0D;
   private double ptX = 0.0D;
   private double ptY = 0.0D;
   private double ptZ = 0.0D;
   private int type = 0;
   private float endMod = 1.0F;
   private boolean reverse = false;
   private boolean pulse = true;
   private int rotationspeed = 5;
   private float prevSize = 0.0F;
   public int impact;


   public FXBeamWand(World par1World, EntityPlayer player, double tx, double ty, double tz, float red, float green, float blue, int age) {
      super(par1World, player.posX, player.posY, player.posZ, 0.0D, 0.0D, 0.0D);
      if(player.entityId != Minecraft.getMinecraft().renderViewEntity.entityId) {
         this.offset = (double)(player.height / 2.0F) + 0.25D;
      }

      super.particleRed = red;
      super.particleGreen = green;
      super.particleBlue = blue;
      this.player = player;
      this.setSize(0.02F, 0.02F);
      super.noClip = true;
      super.motionX = 0.0D;
      super.motionY = 0.0D;
      super.motionZ = 0.0D;
      this.tX = tx;
      this.tY = ty;
      this.tZ = tz;
      float xd = (float)(player.posX - this.tX);
      float yd = (float)(player.posY + this.offset - this.tY);
      float zd = (float)(player.posZ - this.tZ);
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

      if(renderentity.getDistance(player.posX, player.posY, player.posZ) > (double)visibleDistance) {
         super.particleMaxAge = 0;
      }

   }

   public void updateBeam(double x, double y, double z) {
      this.tX = x;
      this.tY = y;

      for(this.tZ = z; super.particleMaxAge - super.particleAge < 4; ++super.particleMaxAge) {
         ;
      }

   }

   public void onUpdate() {
      super.prevPosX = this.player.posX;
      super.prevPosY = this.player.posY + this.offset;
      super.prevPosZ = this.player.posZ;
      this.ptX = this.tX;
      this.ptY = this.tY;
      this.ptZ = this.tZ;
      this.prevYaw = this.rotYaw;
      this.prevPitch = this.rotPitch;
      float xd = (float)(this.player.posX - this.tX);
      float yd = (float)(this.player.posY + this.offset - this.tY);
      float zd = (float)(this.player.posZ - this.tZ);
      this.length = MathHelper.sqrt_float(xd * xd + yd * yd + zd * zd);
      double var7 = (double)MathHelper.sqrt_double((double)(xd * xd + zd * zd));
      this.rotYaw = (float)(Math.atan2((double)xd, (double)zd) * 180.0D / 3.141592653589793D);

      for(this.rotPitch = (float)(Math.atan2((double)yd, var7) * 180.0D / 3.141592653589793D); this.rotPitch - this.prevPitch < -180.0F; this.prevPitch -= 360.0F) {
         ;
      }

      while(this.rotPitch - this.prevPitch >= 180.0F) {
         this.prevPitch += 360.0F;
      }

      while(this.rotYaw - this.prevYaw < -180.0F) {
         this.prevYaw -= 360.0F;
      }

      while(this.rotYaw - this.prevYaw >= 180.0F) {
         this.prevYaw += 360.0F;
      }

      if(this.impact > 0) {
         --this.impact;
      }

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
      float slide = (float)super.worldObj.getWorldTime();
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
      double prex = this.player.prevPosX;
      double prey = this.player.prevPosY + this.offset;
      double prez = this.player.prevPosZ;
      double px = this.player.posX;
      double py = this.player.posY + this.offset;
      double pz = this.player.posZ;
      prex -= (double)(MathHelper.cos(this.player.prevRotationYaw / 180.0F * 3.141593F) * 0.16F);
      prey -= 0.01000000014901161D;
      prez -= (double)(MathHelper.sin(this.player.prevRotationYaw / 180.0F * 3.141593F) * 0.16F);
      Vec3 vec3d = this.player.getLook(1.0F);
      px -= (double)(MathHelper.cos(this.player.rotationYaw / 180.0F * 3.141593F) * 0.16F);
      py -= 0.01000000014901161D;
      pz -= (double)(MathHelper.sin(this.player.rotationYaw / 180.0F * 3.141593F) * 0.16F);
      vec3d = this.player.getLook(1.0F);
      px += vec3d.xCoord * 0.24D;
      float xx = (float)(prex + (px - (prex + vec3d.xCoord * 0.24D)) * (double)f - EntityFX.interpPosX);
      py += vec3d.yCoord * 0.24D;
      float yy = (float)(prey + (py - (prey + vec3d.yCoord * 0.24D)) * (double)f - EntityFX.interpPosY);
      pz += vec3d.zCoord * 0.24D;
      float zz = (float)(prez + (pz - (prez + vec3d.zCoord * 0.24D)) * (double)f - EntityFX.interpPosZ);
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
      if(this.impact > 0) {
         this.renderImpact(tessellator, f, f1, f2, f3, f4, f5);
      }

      Minecraft.getMinecraft().renderEngine.bindTexture("/particles.png");
      tessellator.startDrawingQuads();
      this.prevSize = size;
   }

   public void renderImpact(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
      GL11.glPushMatrix();
      GL11.glDepthMask(false);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 1);
      Minecraft.getMinecraft().renderEngine.bindTexture("/mods/fx/particles.png");
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F);
      int part = super.particleAge % 16;
      float var8 = (float)(part % 8) / 8.0F;
      float var9 = var8 + 0.125F;
      float var10 = 0.375F + (float)(part / 8) / 8.0F;
      float var11 = var10 + 0.125F;
      float var12 = this.endMod / 2.0F / (float)(6 - this.impact);
      float var13 = (float)(this.ptX + (this.tX - this.ptX) * (double)f - EntityFX.interpPosX);
      float var14 = (float)(this.ptY + (this.tY - this.ptY) * (double)f - EntityFX.interpPosY);
      float var15 = (float)(this.ptZ + (this.tZ - this.ptZ) * (double)f - EntityFX.interpPosZ);
      float var16 = 1.0F;
      tessellator.startDrawingQuads();
      tessellator.setBrightness(200);
      tessellator.setColorRGBA_F(super.particleRed, super.particleGreen, super.particleBlue, 0.66F);
      tessellator.addVertexWithUV((double)(var13 - f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 - f3 * var12 - f5 * var12), (double)var9, (double)var11);
      tessellator.addVertexWithUV((double)(var13 - f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 - f3 * var12 + f5 * var12), (double)var9, (double)var10);
      tessellator.addVertexWithUV((double)(var13 + f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 + f3 * var12 + f5 * var12), (double)var8, (double)var10);
      tessellator.addVertexWithUV((double)(var13 + f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 + f3 * var12 - f5 * var12), (double)var8, (double)var11);
      tessellator.draw();
      GL11.glDisable(3042);
      GL11.glDepthMask(true);
      GL11.glPopMatrix();
   }
}
