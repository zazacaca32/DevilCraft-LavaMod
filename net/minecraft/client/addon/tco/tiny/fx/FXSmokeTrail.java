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

public class FXSmokeTrail extends EntityFX {

   private Entity target;
   public int particle = 24;


   public FXSmokeTrail(World par1World, double x, double y, double z, Entity target, float r, float g, float b) {
      super(par1World, x, y, z, 0.0D, 0.0D, 0.0D);
      super.particleRed = r;
      super.particleGreen = g;
      super.particleBlue = b;
      super.particleScale = super.rand.nextFloat() * 0.5F + 0.5F;
      this.target = target;
      double dx = target.posX - super.posX;
      double dy = target.posY - super.posY;
      double dz = target.posZ - super.posZ;
      int base = (int)(MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz) * 3.0F);
      if(base < 1) {
         base = 1;
      }

      super.particleMaxAge = base / 2 + super.rand.nextInt(base);
      float f3 = 0.1F;
      super.motionX = (double)((super.rand.nextFloat() - super.rand.nextFloat()) * 0.1F);
      super.motionY = (double)((super.rand.nextFloat() - super.rand.nextFloat()) * 0.1F);
      super.motionZ = (double)((super.rand.nextFloat() - super.rand.nextFloat()) * 0.1F);
      super.particleGravity = 0.2F;
      super.noClip = false;
      EntityLiving renderentity = ModLoader.getMinecraftInstance().renderViewEntity;
      byte visibleDistance = 64;
      if(!ModLoader.getMinecraftInstance().gameSettings.fancyGraphics) {
         visibleDistance = 32;
      }

      if(renderentity.getDistance(super.posX, super.posY, super.posZ) > (double)visibleDistance) {
         super.particleMaxAge = 0;
      }

   }

   public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
      tessellator.draw();
      GL11.glPushMatrix();
      float bob = MathHelper.sin((float)super.particleAge / 3.0F) * 0.33F + 0.66F;
      GL11.glDepthMask(false);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      Minecraft.getMinecraft().renderEngine.bindTexture("/mods/fx/particles.png");
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.33F);
      int part = 5 + super.particleAge % 4;
      float var8 = (float)(part % 16) / 16.0F;
      float var9 = var8 + 0.0624375F;
      float var10 = (float)(part / 16) / 16.0F;
      float var11 = var10 + 0.0624375F;
      float var12 = 0.3F * super.particleScale * bob;
      float var13 = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)f - EntityFX.interpPosX);
      float var14 = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)f - EntityFX.interpPosY);
      float var15 = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)f - EntityFX.interpPosZ);
      float var16 = 1.0F;
      tessellator.startDrawingQuads();
      tessellator.setBrightness(this.getBrightnessForRender(f));
      tessellator.setColorRGBA_F(super.particleRed * 1.0F, super.particleGreen * 1.0F, super.particleBlue * 1.0F, 0.33F);
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

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      if(super.particleAge++ < super.particleMaxAge && this.getDistanceSqToEntity(this.target) >= 1.0D) {
         if(!super.noClip) {
            this.pushOutOfBlocks(super.posX, super.posY, super.posZ);
         }

         this.moveEntity(super.motionX, super.motionY, super.motionZ);
         super.motionX *= 0.985D;
         super.motionY *= 0.985D;
         super.motionZ *= 0.985D;
         double dx = this.target.posX - super.posX;
         double dy = this.target.posY - super.posY;
         double dz = this.target.posZ - super.posZ;
         double d13 = 0.3D;
         double d14 = (double)MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
         if(d14 < 4.0D) {
            super.particleScale *= 0.9F;
            d13 = 0.6D;
         }

         super.motionX += dx / d14 * d13;
         super.motionY += dy / d14 * d13;
         super.motionZ += dz / d14 * d13;
         super.motionX = (double)MathHelper.clamp_float((float)super.motionX, -0.35F, 0.35F);
         super.motionY = (double)MathHelper.clamp_float((float)super.motionY, -0.35F, 0.35F);
         super.motionZ = (double)MathHelper.clamp_float((float)super.motionZ, -0.35F, 0.35F);
      } else {
         this.setDead();
      }

   }

   public void setGravity(float value) {
      super.particleGravity = value;
   }

   protected boolean pushOutOfBlocks(double par1, double par3, double par5) {
      int var7 = MathHelper.floor_double(par1);
      int var8 = MathHelper.floor_double(par3);
      int var9 = MathHelper.floor_double(par5);
      double var10 = par1 - (double)var7;
      double var11 = par3 - (double)var8;
      double var12 = par5 - (double)var9;
      if(!super.worldObj.isAirBlock(var7, var8, var9) && !super.worldObj.isAnyLiquid(super.boundingBox)) {
         boolean var13 = !super.worldObj.isBlockNormalCube(var7 - 1, var8, var9);
         boolean var14 = !super.worldObj.isBlockNormalCube(var7 + 1, var8, var9);
         boolean var15 = !super.worldObj.isBlockNormalCube(var7, var8 - 1, var9);
         boolean var16 = !super.worldObj.isBlockNormalCube(var7, var8 + 1, var9);
         boolean var17 = !super.worldObj.isBlockNormalCube(var7, var8, var9 - 1);
         boolean var18 = !super.worldObj.isBlockNormalCube(var7, var8, var9 + 1);
         byte var19 = -1;
         double var20 = 9999.0D;
         if(var13 && var10 < var20) {
            var20 = var10;
            var19 = 0;
         }

         if(var14 && 1.0D - var10 < var20) {
            var20 = 1.0D - var10;
            var19 = 1;
         }

         if(var15 && var11 < var20) {
            var20 = var11;
            var19 = 2;
         }

         if(var16 && 1.0D - var11 < var20) {
            var20 = 1.0D - var11;
            var19 = 3;
         }

         if(var17 && var12 < var20) {
            var20 = var12;
            var19 = 4;
         }

         if(var18 && 1.0D - var12 < var20) {
            var20 = 1.0D - var12;
            var19 = 5;
         }

         float var21 = super.rand.nextFloat() * 0.05F + 0.025F;
         float var22 = (super.rand.nextFloat() - super.rand.nextFloat()) * 0.1F;
         double n6;
         if(var19 == 0) {
            super.motionX = (double)(-var21);
            n6 = (double)var22;
            super.motionZ = n6;
            super.motionY = n6;
         }

         if(var19 == 1) {
            super.motionX = (double)var21;
            n6 = (double)var22;
            super.motionZ = n6;
            super.motionY = n6;
         }

         if(var19 == 2) {
            super.motionY = (double)(-var21);
            n6 = (double)var22;
            super.motionZ = n6;
            super.motionX = n6;
         }

         if(var19 == 3) {
            super.motionY = (double)var21;
            n6 = (double)var22;
            super.motionZ = n6;
            super.motionX = n6;
         }

         if(var19 == 4) {
            super.motionZ = (double)(-var21);
            n6 = (double)var22;
            super.motionX = n6;
            super.motionY = n6;
         }

         if(var19 == 5) {
            super.motionZ = (double)var21;
            n6 = (double)var22;
            super.motionX = n6;
            super.motionY = n6;
         }

         return true;
      } else {
         return false;
      }
   }
}
