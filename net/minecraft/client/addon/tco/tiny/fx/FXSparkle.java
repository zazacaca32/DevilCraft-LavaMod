package net.minecraft.client.addon.tco.tiny.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.src.ModLoader;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXSparkle extends EntityFX {

   public boolean leyLineEffect;
   public int multiplier;
   public boolean shrink;
   public int particle;
   public boolean tinkle;
   public int blendmode;
   public boolean slowdown;
   public int currentColor;


   public FXSparkle(World world, double d, double d1, double d2, float f, float f1, float f2, float f3, int m) {
      super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
      this.leyLineEffect = false;
      this.multiplier = 2;
      this.shrink = true;
      this.particle = 16;
      this.tinkle = false;
      this.blendmode = 1;
      this.slowdown = true;
      this.currentColor = 0;
      if(f1 == 0.0F) {
         f1 = 1.0F;
      }

      super.particleRed = f1;
      super.particleGreen = f2;
      super.particleBlue = f3;
      super.particleGravity = 0.0F;
      super.motionZ = 0.0D;
      super.motionY = 0.0D;
      super.motionX = 0.0D;
      super.particleScale *= f;
      super.particleMaxAge = 3 * m;
      this.multiplier = m;
      super.noClip = false;
      this.setSize(0.01F, 0.01F);
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
   }

   public FXSparkle(World world, double d, double d1, double d2, float f, int type, int m) {
      this(world, d, d1, d2, f, 0.0F, 0.0F, 0.0F, m);
      switch(this.currentColor = type) {
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
         break;
      case 7:
         super.particleRed = 0.2F;
         super.particleGreen = 0.5F + world.rand.nextFloat() * 0.3F;
         super.particleBlue = 0.6F + world.rand.nextFloat() * 0.3F;
      }

   }

   public FXSparkle(World world, double d, double d1, double d2, double x, double y, double z, float f, int type, int m) {
      this(world, d, d1, d2, f, type, m);
      double dx = x - super.posX;
      double dy = y - super.posY;
      double dz = z - super.posZ;
      super.motionX = dx / (double)super.particleMaxAge;
      super.motionY = dy / (double)super.particleMaxAge;
      super.motionZ = dz / (double)super.particleMaxAge;
   }

   public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
      tessellator.draw();
      GL11.glPushMatrix();
      GL11.glDepthMask(false);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, this.blendmode);
      Minecraft.getMinecraft().renderEngine.bindTexture("/mods/fx/particles.png");
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
      int part = this.particle + super.particleAge / this.multiplier;
      float var8 = (float)(part % 8) / 8.0F;
      float var9 = var8 + 0.124875F;
      float var10 = (float)(part / 8) / 8.0F;
      float var11 = var10 + 0.124875F;
      float var12 = 0.1F * super.particleScale;
      if(this.shrink) {
         var12 *= (float)((super.particleMaxAge - super.particleAge + 1) / super.particleMaxAge);
      }

      float var13 = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)f - EntityFX.interpPosX);
      float var14 = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)f - EntityFX.interpPosY);
      float var15 = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)f - EntityFX.interpPosZ);
      float var16 = 1.0F;
      tessellator.startDrawingQuads();
      tessellator.setBrightness(240);
      tessellator.setColorRGBA_F(super.particleRed * 1.0F, super.particleGreen * 1.0F, super.particleBlue * 1.0F, 1.0F);
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
      if(super.particleAge == 0 && this.tinkle && super.worldObj.rand.nextInt(10) == 0) {
         super.worldObj.playSoundAtEntity(this, "random.orb", 0.02F, 0.7F * ((super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.6F + 2.0F));
      }

      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

      super.motionY -= 0.04D * (double)super.particleGravity;
      if(!super.noClip) {
         this.pushOutOfBlocks(super.posX, (super.boundingBox.minY + super.boundingBox.maxY) / 2.0D, super.posZ);
      }

      super.posX += super.motionX;
      super.posY += super.motionY;
      super.posZ += super.motionZ;
      if(this.slowdown) {
         super.motionX *= 0.9080000019073486D;
         super.motionY *= 0.9080000019073486D;
         super.motionZ *= 0.9080000019073486D;
         if(super.onGround) {
            super.motionX *= 0.699999988079071D;
            super.motionZ *= 0.699999988079071D;
         }
      }

      if(this.leyLineEffect) {
         FXSparkle fx = new FXSparkle(super.worldObj, super.prevPosX + (double)((super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.1F), super.prevPosY + (double)((super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.1F), super.prevPosZ + (double)((super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.1F), 1.0F, this.currentColor, 3 + super.worldObj.rand.nextInt(3));
         fx.noClip = true;
         ModLoader.getMinecraftInstance().effectRenderer.addEffect(fx);
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
      if(!super.worldObj.isAirBlock(var7, var8, var9)) {
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
