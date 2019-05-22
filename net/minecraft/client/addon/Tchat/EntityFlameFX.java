package net.minecraft.client.addon.Tchat;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityFlameFX extends EntityFX
{
    private float flameScale;

    public EntityFlameFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        super(par1World, par2, par4, par6, par8, par10, par12);
        super.motionX = super.motionX * 0.009999999776482582D + par8;
        super.motionY = super.motionY * 0.009999999776482582D + par10;
        super.motionZ = super.motionZ * 0.009999999776482582D + par12;
        double var10000 = par2 + (double)((super.rand.nextFloat() - super.rand.nextFloat()) * 0.05F);
        var10000 = par4 + (double)((super.rand.nextFloat() - super.rand.nextFloat()) * 0.05F);
        var10000 = par6 + (double)((super.rand.nextFloat() - super.rand.nextFloat()) * 0.05F);
        this.flameScale = super.particleScale;
        super.particleRed = super.particleGreen = super.particleBlue = 1.0F;
        super.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D) + 4.0D) / 2;
        super.noClip = true;
        this.setParticleTextureIndex(48);
    }

    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        float f6 = ((float)super.particleAge + par2) / (float)super.particleMaxAge;
        super.particleScale = this.flameScale * (1.0F - f6 * f6 * 0.5F);
        super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
    }

    public int getBrightnessForRender(float par1)
    {
        float f1 = ((float)super.particleAge + par1) / (float)super.particleMaxAge;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }

        int i = super.getBrightnessForRender(par1);
        int j = i & 255;
        int k = i >> 16 & 255;
        j += (int)(f1 * 15.0F * 16.0F);

        if (j > 240)
        {
            j = 240;
        }

        return j | k << 16;
    }

    public float getBrightness(float par1)
    {
        float f1 = ((float)super.particleAge + par1) / (float)super.particleMaxAge;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }

        float f2 = super.getBrightness(par1);
        return f2 * f1 + (1.0F - f1);
    }

    public void onUpdate()
    {
        super.prevPosX = super.posX;
        super.prevPosY = super.posY;
        super.prevPosZ = super.posZ;

        if (super.particleAge++ >= super.particleMaxAge)
        {
            this.setDead();
        }

        this.moveEntity(super.motionX, super.motionY, super.motionZ);
        super.motionX *= 0.9599999785423279D;
        super.motionY *= 0.9599999785423279D;
        super.motionZ *= 0.9599999785423279D;

        if (super.onGround)
        {
            super.motionX *= 0.699999988079071D;
            super.motionZ *= 0.699999988079071D;
        }
    }
}
