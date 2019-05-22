package net.minecraft.client.addon.tco.tiny.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXSmokeSpiral extends EntityFX {

   private float radius = 1.0F;
   private int start = 0;
   private int miny = 0;


   public FXSmokeSpiral(World world, double d, double d1, double d2, float radius, int start, int miny) {
      super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
      super.particleGravity = -0.01F;
      super.motionZ = 0.0D;
      super.motionY = 0.0D;
      super.motionX = 0.0D;
      super.particleScale *= 1.0F;
      super.particleMaxAge = 20 + world.rand.nextInt(10);
      super.noClip = false;
      this.setSize(0.01F, 0.01F);
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      this.radius = radius;
      this.start = start;
      this.miny = miny;
   }

   public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
      tessellator.draw();
      GL11.glPushMatrix();
      GL11.glDepthMask(false);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      Minecraft.getMinecraft().renderEngine.bindTexture("/mods/fx/particles.png");
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F);
      int particle = 5 + super.particleAge % 4;
      float r1 = (float)this.start + 720.0F * (((float)super.particleAge + f) / (float)super.particleMaxAge);
      float r2 = 90.0F - 180.0F * (((float)super.particleAge + f) / (float)super.particleMaxAge);
      float mX = -MathHelper.sin(r1 / 180.0F * 3.141593F) * MathHelper.cos(r2 / 180.0F * 3.141593F);
      float mZ = MathHelper.cos(r1 / 180.0F * 3.141593F) * MathHelper.cos(r2 / 180.0F * 3.141593F);
      float mY = -MathHelper.sin(r2 / 180.0F * 3.141593F);
      float var8 = (float)(particle % 16) / 16.0F;
      float var9 = var8 + 0.0624375F;
      float var10 = (float)(particle / 16) / 16.0F;
      float var11 = var10 + 0.0624375F;
      float var12 = 0.15F * super.particleScale;
      float var13 = (float)(super.posX + (double)(mX * this.radius) - EntityFX.interpPosX);
      float var14 = (float)(Math.max(super.posY + (double)(mY * this.radius), (double)((float)this.miny + 0.1F)) - EntityFX.interpPosY);
      float var15 = (float)(super.posZ + (double)(mZ * this.radius) - EntityFX.interpPosZ);
      float var16 = 1.0F;
      tessellator.startDrawingQuads();
      tessellator.setBrightness(this.getBrightnessForRender(f));
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

   public void onUpdate() {
      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

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
