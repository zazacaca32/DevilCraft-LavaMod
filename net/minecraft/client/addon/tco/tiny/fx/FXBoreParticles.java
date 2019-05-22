package net.minecraft.client.addon.tco.tiny.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLiving;
import net.minecraft.src.ModLoader;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class FXBoreParticles extends EntityFX {

   private Block blockInstance;
   private int side;
   private double targetX;
   private double targetY;
   private double targetZ;


   public FXBoreParticles(World par1World, double par2, double par4, double par6, double tx, double ty, double tz, Block par14Block, int par15, int par16, RenderEngine par17RenderEngine) {
      super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
      this.blockInstance = par14Block;
      this.setParticleIcon(par17RenderEngine, par14Block.getIcon(par15, par16));
      super.particleGravity = par14Block.blockParticleGravity;
      super.particleBlue = 0.6F;
      super.particleGreen = 0.6F;
      super.particleRed = 0.6F;
      super.particleScale = super.rand.nextFloat() * 0.3F + 0.4F;
      this.side = par15;
      this.targetX = tx;
      this.targetY = ty;
      this.targetZ = tz;
      double dx = tx - super.posX;
      double dy = ty - super.posY;
      double dz = tz - super.posZ;
      int base = (int)(MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz) * 3.0F);
      if(base < 1) {
         base = 1;
      }

      super.particleMaxAge = base / 2 + super.rand.nextInt(base);
      float f3 = 0.01F;
      super.motionX = (double)((float)super.worldObj.rand.nextGaussian() * 0.01F);
      super.motionY = (double)((float)super.worldObj.rand.nextGaussian() * 0.01F);
      super.motionZ = (double)((float)super.worldObj.rand.nextGaussian() * 0.01F);
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

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      if(super.particleAge++ < super.particleMaxAge && (MathHelper.floor_double(super.posX) != MathHelper.floor_double(this.targetX) || MathHelper.floor_double(super.posY) != MathHelper.floor_double(this.targetY) || MathHelper.floor_double(super.posZ) != MathHelper.floor_double(this.targetZ))) {
         if(!super.noClip) {
            this.pushOutOfBlocks(super.posX, super.posY, super.posZ);
         }

         this.moveEntity(super.motionX, super.motionY, super.motionZ);
         super.motionX *= 0.985D;
         super.motionY *= 0.985D;
         super.motionZ *= 0.985D;
         double dx = this.targetX - super.posX;
         double dy = this.targetY - super.posY;
         double dz = this.targetZ - super.posZ;
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

   public FXBoreParticles func_70596_a(int par1, int par2, int par3) {
      if(this.blockInstance == Block.grass && this.side != 1) {
         return this;
      } else {
         int var4 = this.blockInstance.colorMultiplier(super.worldObj, par1, par2, par3);
         super.particleRed *= (float)(var4 >> 16 & 255) / 255.0F;
         super.particleGreen *= (float)(var4 >> 8 & 255) / 255.0F;
         super.particleBlue *= (float)(var4 & 255) / 255.0F;
         return this;
      }
   }

   public FXBoreParticles applyRenderColor(int par1) {
      if(this.blockInstance == Block.grass) {
         return this;
      } else {
         int var2 = this.blockInstance.getRenderColor(par1);
         super.particleRed *= (float)(var2 >> 16 & 255) / 255.0F;
         super.particleGreen *= (float)(var2 >> 8 & 255) / 255.0F;
         super.particleBlue *= (float)(var2 & 255) / 255.0F;
         return this;
      }
   }

   public int getFXLayer() {
      return 1;
   }

   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
      float f6 = ((float)super.particleTextureIndexX + super.particleTextureJitterX / 4.0F) / 16.0F;
      float f7 = f6 + 0.01560938F;
      float f8 = ((float)super.particleTextureIndexY + super.particleTextureJitterY / 4.0F) / 16.0F;
      float f9 = f8 + 0.01560938F;
      float f10 = 0.1F * super.particleScale;
      if(super.particleIcon != null) {
         f6 = super.particleIcon.getInterpolatedU((double)(super.particleTextureJitterX / 4.0F * 16.0F));
         f7 = super.particleIcon.getInterpolatedU((double)((super.particleTextureJitterX + 1.0F) / 4.0F * 16.0F));
         f8 = super.particleIcon.getInterpolatedV((double)(super.particleTextureJitterY / 4.0F * 16.0F));
         f9 = super.particleIcon.getInterpolatedV((double)((super.particleTextureJitterY + 1.0F) / 4.0F * 16.0F));
      }

      float f11 = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)par2 - EntityFX.interpPosX);
      float f12 = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)par2 - EntityFX.interpPosY);
      float f13 = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)par2 - EntityFX.interpPosZ);
      float f14 = 1.0F;
      par1Tessellator.setColorOpaque_F(1.0F * super.particleRed, 1.0F * super.particleGreen, 1.0F * super.particleBlue);
      par1Tessellator.addVertexWithUV((double)(f11 - par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 - par5 * f10 - par7 * f10), (double)f6, (double)f9);
      par1Tessellator.addVertexWithUV((double)(f11 - par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 - par5 * f10 + par7 * f10), (double)f6, (double)f8);
      par1Tessellator.addVertexWithUV((double)(f11 + par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 + par5 * f10 + par7 * f10), (double)f7, (double)f8);
      par1Tessellator.addVertexWithUV((double)(f11 + par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 + par5 * f10 - par7 * f10), (double)f7, (double)f9);
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
