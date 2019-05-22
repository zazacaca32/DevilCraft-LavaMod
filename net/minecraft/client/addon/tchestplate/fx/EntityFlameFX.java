package net.minecraft.client.addon.tchestplate.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class EntityFlameFX extends EntityFX
{
    private float flameScale;
    public boolean invise;

    public EntityFlameFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12)
    {
        super(var1, var2, var4, var6, var8, var10, var12);
        super.motionX = super.motionX * 0.009999999776482582D + var8;
        super.motionY = super.motionY * 0.009999999776482582D + var10;
        super.motionZ = super.motionZ * 0.009999999776482582D + var12;
        double var10000 = var2 + (double)((super.rand.nextFloat() - super.rand.nextFloat()) * 0.05F);
        var10000 = var4 + (double)((super.rand.nextFloat() - super.rand.nextFloat()) * 0.05F);
        var10000 = var6 + (double)((super.rand.nextFloat() - super.rand.nextFloat()) * 0.05F);
        this.flameScale = super.particleScale;
        super.particleBlue = 1.0F;
        super.particleGreen = 1.0F;
        super.particleRed = 1.0F;
        super.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
        super.noClip = true;
        this.setParticleTextureIndex(48);
    }

    public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        float var8 = ((float)super.particleAge + var2) / (float)super.particleMaxAge;
        super.particleScale = this.flameScale * (1.0F - var8 * var8 * 0.5F);
        float var9 = (float)super.particleTextureIndexX / 16.0F;
        float var10 = var9 + 0.0624375F;
        float var11 = (float)super.particleTextureIndexY / 16.0F;
        float var12 = var11 + 0.0624375F;
        float var13 = 0.1F * super.particleScale;

        if (super.particleIcon != null)
        {
            var9 = super.particleIcon.getMinU();
            var10 = super.particleIcon.getMaxU();
            var11 = super.particleIcon.getMinV();
            var12 = super.particleIcon.getMaxV();
        }

        float var14 = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)var2 - EntityFX.interpPosX);
        float var15 = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)var2 - EntityFX.interpPosY);
        float var16 = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)var2 - EntityFX.interpPosZ);
        float var17 = 1.0F;
        var1.draw();
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);

        if (this.invise)
        {
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
            var17 = 0.03F;
        }

        var1.startDrawingQuads();
        var1.setColorRGBA_F(super.particleRed * var17, super.particleGreen * var17, super.particleBlue * var17, super.particleAlpha);
        var1.addVertexWithUV((double)(var14 - var3 * var13 - var6 * var13), (double)(var15 - var4 * var13), (double)(var16 - var5 * var13 - var7 * var13), (double)var10, (double)var12);
        var1.addVertexWithUV((double)(var14 - var3 * var13 + var6 * var13), (double)(var15 + var4 * var13), (double)(var16 - var5 * var13 + var7 * var13), (double)var10, (double)var11);
        var1.addVertexWithUV((double)(var14 + var3 * var13 + var6 * var13), (double)(var15 + var4 * var13), (double)(var16 + var5 * var13 + var7 * var13), (double)var9, (double)var11);
        var1.addVertexWithUV((double)(var14 + var3 * var13 - var6 * var13), (double)(var15 - var4 * var13), (double)(var16 + var5 * var13 - var7 * var13), (double)var9, (double)var12);
        var1.draw();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
        var1.startDrawingQuads();
    }

    public int getBrightnessForRender(float var1)
    {
        float var2 = ((float)super.particleAge + var1) / (float)super.particleMaxAge;

        if (var2 < 0.0F)
        {
            var2 = 0.0F;
        }

        if (var2 > 1.0F)
        {
            var2 = 1.0F;
        }

        int var3 = super.getBrightnessForRender(var1);
        int var4 = var3 & 255;
        int var5 = var3 >> 16 & 255;

        if ((var4 += (int)(var2 * 15.0F * 16.0F)) > 240)
        {
            var4 = 240;
        }

        return var4 | var5 << 16;
    }

    public float getBrightness(float var1)
    {
        float var2 = ((float)super.particleAge + var1) / (float)super.particleMaxAge;

        if (var2 < 0.0F)
        {
            var2 = 0.0F;
        }

        if (var2 > 1.0F)
        {
            var2 = 1.0F;
        }

        float var3 = super.getBrightness(var1);
        return var3 * var2 + (1.0F - var2);
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
