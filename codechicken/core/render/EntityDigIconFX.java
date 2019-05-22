package codechicken.core.render;

import codechicken.core.vec.Cuboid6;
import codechicken.core.vec.Vector3;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class EntityDigIconFX extends EntityFX {

   public EntityDigIconFX(World world, double x, double y, double z, double dx, double dy, double dz, Icon icon) {
      super(world, x, y, z, dx, dy, dz);
      super.particleIcon = icon;
      super.particleGravity = 1.0F;
      super.particleRed = super.particleGreen = super.particleBlue = 0.6F;
      super.particleScale /= 2.0F;
   }

   public int getFXLayer() {
      return 1;
   }

   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
      float f6 = ((float)super.particleTextureIndexX + super.particleTextureJitterX / 4.0F) / 16.0F;
      float f7 = f6 + 0.015609375F;
      float f8 = ((float)super.particleTextureIndexY + super.particleTextureJitterY / 4.0F) / 16.0F;
      float f9 = f8 + 0.015609375F;
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
      par1Tessellator.setColorOpaque_F(f14 * super.particleRed, f14 * super.particleGreen, f14 * super.particleBlue);
      par1Tessellator.addVertexWithUV((double)(f11 - par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 - par5 * f10 - par7 * f10), (double)f6, (double)f9);
      par1Tessellator.addVertexWithUV((double)(f11 - par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 - par5 * f10 + par7 * f10), (double)f6, (double)f8);
      par1Tessellator.addVertexWithUV((double)(f11 + par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 + par5 * f10 + par7 * f10), (double)f7, (double)f8);
      par1Tessellator.addVertexWithUV((double)(f11 + par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 + par5 * f10 - par7 * f10), (double)f7, (double)f9);
   }

   public static void addBlockHitEffects(World world, Cuboid6 bounds, int side, Icon icon, EffectRenderer effectRenderer) {
      float border = 0.1F;
      Vector3 diff = bounds.max.copy().subtract(bounds.min).add((double)(-2.0F * border));
      diff.x *= world.rand.nextDouble();
      diff.y *= world.rand.nextDouble();
      diff.z *= world.rand.nextDouble();
      Vector3 pos = diff.add(bounds.min).add((double)border);
      if(side == 0) {
         diff.y = bounds.min.y - (double)border;
      }

      if(side == 1) {
         diff.y = bounds.max.y + (double)border;
      }

      if(side == 2) {
         diff.z = bounds.min.z - (double)border;
      }

      if(side == 3) {
         diff.z = bounds.max.z + (double)border;
      }

      if(side == 4) {
         diff.x = bounds.min.x - (double)border;
      }

      if(side == 5) {
         diff.x = bounds.max.x + (double)border;
      }

      effectRenderer.addEffect((new EntityDigIconFX(world, pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D, icon)).multiplyVelocity(0.2F).multipleParticleScaleBy(0.6F));
   }

   public static void addBlockDestroyEffects(World world, Cuboid6 bounds, Icon[] icons, EffectRenderer effectRenderer) {
      Vector3 diff = bounds.max.copy().subtract(bounds.min);
      Vector3 center = bounds.min.copy().add(bounds.max).multiply(0.5D);
      Vector3 density = diff.copy().multiply(4.0D);
      density.x = Math.ceil(density.x);
      density.y = Math.ceil(density.y);
      density.z = Math.ceil(density.z);

      for(int i = 0; (double)i < density.x; ++i) {
         for(int j = 0; (double)j < density.y; ++j) {
            for(int k = 0; (double)k < density.z; ++k) {
               double x = bounds.min.x + ((double)i + 0.5D) * diff.x / density.x;
               double y = bounds.min.y + ((double)j + 0.5D) * diff.y / density.y;
               double z = bounds.min.z + ((double)k + 0.5D) * diff.z / density.z;
               effectRenderer.addEffect(new EntityDigIconFX(world, x, y, z, x - center.x, y - center.y, z - center.z, icons[world.rand.nextInt(icons.length)]));
            }
         }
      }

   }
}
