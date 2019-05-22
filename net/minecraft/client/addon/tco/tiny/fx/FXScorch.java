package net.minecraft.client.addon.tco.tiny.fx;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class FXScorch extends EntityFX {

   public boolean pvp = true;
   public boolean mobs = true;
   public boolean animals = true;
   private double px;
   private double py;
   private double pz;
   private float transferParticleScale;
   Entity partDestEnt;
   private boolean lance = false;


   public FXScorch(World world, double x, double y, double z, Vec3 v, float spread) {
      super(world, x, y, z, 0.0D, 0.0D, 0.0D);
      super.posX = x;
      super.posY = y;
      super.posZ = z;
      this.px = x + v.xCoord * 100.0D;
      this.py = y + v.yCoord * 100.0D;
      this.pz = z + v.zCoord * 100.0D;
      this.px += (double)((super.rand.nextFloat() - super.rand.nextFloat()) * spread);
      this.py += (double)((super.rand.nextFloat() - super.rand.nextFloat()) * spread);
      this.pz += (double)((super.rand.nextFloat() - super.rand.nextFloat()) * spread);
      float n = super.rand.nextFloat() * 0.5F + 2.0F;
      super.particleScale = n;
      this.transferParticleScale = n;
      super.particleMaxAge = 50;
      this.setSize(0.1F, 0.1F);
      this.setParticleTextureIndex(48 + super.rand.nextInt(2));
      super.noClip = false;
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
   }

   public int getBrightnessForRender(float par1) {
      return 210;
   }

   public float getBrightness(float par1) {
      return 1.0F;
   }

   public void onUpdate() {
      double dx = this.px - super.posX;
      double dy = this.py - super.posY;
      double dz = this.pz - super.posZ;
      double distance = (double)MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
      dx = super.motionX = dx / (distance * 1.25D);
      dy = super.motionY = dy / (distance * 1.25D);
      dz = super.motionZ = dz / (distance * 1.25D);
      if(!this.lance) {
         super.motionX *= (double)((super.particleMaxAge - super.particleAge) / super.particleMaxAge);
         super.motionY *= (double)((super.particleMaxAge - super.particleAge) / super.particleMaxAge);
         super.motionZ *= (double)((super.particleMaxAge - super.particleAge) / super.particleMaxAge);
      }

      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      super.motionX += (double)(super.rand.nextFloat() * 0.07F - 0.035F);
      super.motionY += (double)(super.rand.nextFloat() * 0.07F - 0.035F);
      super.motionZ += (double)(super.rand.nextFloat() * 0.07F - 0.035F);
      int var7 = MathHelper.floor_double(super.posX);
      int var8 = MathHelper.floor_double(super.posY);
      int var9 = MathHelper.floor_double(super.posZ);
      if(super.particleAge > 1 && super.worldObj.isBlockOpaqueCube(var7, var8, var9)) {
         super.motionX = 0.0D;
         super.motionY = 0.0D;
         super.motionZ = 0.0D;
         super.particleAge += 10;
      }

      this.pushOutOfBlocks(super.posX, (super.boundingBox.minY + super.boundingBox.maxY) / 2.0D, super.posZ);
      super.posX += super.motionX;
      super.posY += super.motionY;
      super.posZ += super.motionZ;
      ++super.particleAge;
      if(super.particleAge >= super.particleMaxAge) {
         this.setDead();
      }

   }

   public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
      float fs = (float)(super.particleAge / super.particleMaxAge);
      super.particleScale = this.transferParticleScale * (fs + 0.25F) * 2.0F;
      float fc = (float)super.particleAge * 9.0F / (float)super.particleMaxAge;
      if(fc > 1.0F) {
         fc = 1.0F;
      }

      super.particleGreen = fc;
      super.particleRed = fc;
      super.particleBlue = 1.0F;
      super.renderParticle(tessellator, f, f1, f2, f3, f4, f5);
   }
}
