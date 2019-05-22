package net.minecraft.client.addon.tchestplate.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.src.ModLoader;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXSparkle extends EntityFX
{
    public boolean leyLineEffect;
    public int multiplier;
    public boolean shrink;
    public int particle;
    public boolean tinkle;
    public int blendmode;
    public boolean slowdown;
    public int currentColor;
    public boolean invise;
    public boolean inverse;

    public FXSparkle(World var1, double var2, double var4, double var6, float var8, float var9, float var10, float var11, int var12)
    {
        super(var1, var2, var4, var6, 0.0D, 0.0D, 0.0D);
        this.leyLineEffect = false;
        this.multiplier = 2;
        this.shrink = true;
        this.particle = 16;
        this.tinkle = false;
        this.blendmode = 1;
        this.slowdown = true;
        this.currentColor = 0;

        if (var9 == 0.0F)
        {
            var9 = 1.0F;
        }

        super.particleRed = var9;
        super.particleGreen = var10;
        super.particleBlue = var11;
        super.particleGravity = 0.0F;
        super.motionX = super.motionY = super.motionZ = 0.0D;
        super.particleScale *= var8;
        super.particleMaxAge = 3 * var12;
        this.multiplier = var12;
        super.noClip = false;
        this.setSize(0.01F, 0.01F);
        super.prevPosX = super.posX;
        super.prevPosY = super.posY;
        super.prevPosZ = super.posZ;
        this.inverse = false;
    }

    public FXSparkle(World var1, double var2, double var4, double var6, float var8, int var9, int var10)
    {
        this(var1, var2, var4, var6, var8, 0.0F, 0.0F, 0.0F, var10);
        this.currentColor = var9;

        switch (var9)
        {
            case 0:
                super.particleRed = 0.75F + var1.rand.nextFloat() * 0.25F;
                super.particleGreen = 0.25F + var1.rand.nextFloat() * 0.25F;
                super.particleBlue = 0.75F + var1.rand.nextFloat() * 0.25F;
                break;

            case 1:
                super.particleRed = 0.5F + var1.rand.nextFloat() * 0.3F;
                super.particleGreen = 0.5F + var1.rand.nextFloat() * 0.3F;
                super.particleBlue = 0.2F;
                break;

            case 2:
                super.particleRed = 0.2F;
                super.particleGreen = 0.2F;
                super.particleBlue = 0.7F + var1.rand.nextFloat() * 0.3F;
                break;

            case 3:
                super.particleRed = 0.2F;
                super.particleGreen = 0.7F + var1.rand.nextFloat() * 0.3F;
                super.particleBlue = 0.2F;
                break;

            case 4:
                super.particleRed = 0.7F + var1.rand.nextFloat() * 0.3F;
                super.particleGreen = 0.2F;
                super.particleBlue = 0.2F;
                break;

            case 5:
                this.blendmode = 771;
                super.particleRed = var1.rand.nextFloat() * 0.1F;
                super.particleGreen = var1.rand.nextFloat() * 0.1F;
                super.particleBlue = var1.rand.nextFloat() * 0.1F;
                break;

            case 6:
                super.particleRed = 0.8F + var1.rand.nextFloat() * 0.2F;
                super.particleGreen = 0.8F + var1.rand.nextFloat() * 0.2F;
                super.particleBlue = 0.8F + var1.rand.nextFloat() * 0.2F;
                break;

            case 7:
                super.particleRed = 0.2F;
                super.particleGreen = 0.5F + var1.rand.nextFloat() * 0.3F;
                super.particleBlue = 0.6F + var1.rand.nextFloat() * 0.3F;
        }
    }

    public FXSparkle(World var1, double var2, double var4, double var6, double var8, double var10, double var12, float var14, int var15, int var16)
    {
        this(var1, var2, var4, var6, var14, var15, var16);
        double var17 = var8 - super.posX;
        double var19 = var10 - super.posY;
        double var21 = var12 - super.posZ;
        super.motionX = var17 / (double)super.particleMaxAge;
        super.motionY = var19 / (double)super.particleMaxAge;
        super.motionZ = var21 / (double)super.particleMaxAge;
    }

    public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        var1.draw();
        GL11.glPushMatrix();
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);

        if (this.invise)
        {
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        }
        else
        {
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, this.blendmode);
        }

        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/fx/particles.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);

        if (this.multiplier == 0)
        {
            this.multiplier = 1;
        }

        int var8 = this.particle + super.particleAge / this.multiplier;
        float var9 = (float)(var8 % 8) / 8.0F;
        float var10 = var9 + 0.124875F;
        float var11 = (float)(var8 / 8) / 8.0F;
        float var12 = var11 + 0.124875F;
        float var13 = 0.1F * super.particleScale;

        if (this.shrink)
        {
            var13 *= (float)(super.particleMaxAge - super.particleAge + 1) / (float)super.particleMaxAge;
        }

        float var14 = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)var2 - EntityFX.interpPosX);
        float var15 = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)var2 - EntityFX.interpPosY);
        float var16 = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)var2 - EntityFX.interpPosZ);
        float var17 = 1.0F;

        if (this.invise)
        {
            var17 = 0.03F;
        }

        var1.startDrawingQuads();
        var1.setBrightness(240);
        var1.setColorRGBA_F(super.particleRed * var17, super.particleGreen * var17, super.particleBlue * var17, 1.0F);
        var1.addVertexWithUV((double)(var14 - var3 * var13 - var6 * var13), (double)(var15 - var4 * var13), (double)(var16 - var5 * var13 - var7 * var13), (double)var10, (double)var12);
        var1.addVertexWithUV((double)(var14 - var3 * var13 + var6 * var13), (double)(var15 + var4 * var13), (double)(var16 - var5 * var13 + var7 * var13), (double)var10, (double)var11);
        var1.addVertexWithUV((double)(var14 + var3 * var13 + var6 * var13), (double)(var15 + var4 * var13), (double)(var16 + var5 * var13 + var7 * var13), (double)var9, (double)var11);
        var1.addVertexWithUV((double)(var14 + var3 * var13 - var6 * var13), (double)(var15 - var4 * var13), (double)(var16 + var5 * var13 - var7 * var13), (double)var9, (double)var12);
        var1.draw();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture("/particles.png");
        var1.startDrawingQuads();
    }

    public void onUpdate()
    {
        super.prevPosX = super.posX;
        super.prevPosY = super.posY;
        super.prevPosZ = super.posZ;

        if (super.particleAge == 0 && this.tinkle && super.worldObj.rand.nextInt(10) == 0)
        {
            super.worldObj.playSoundAtEntity(this, "random.orb", 0.02F, 0.7F * ((super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.6F + 2.0F));
        }

        if (super.particleAge++ >= super.particleMaxAge)
        {
            this.setDead();
        }

        if (this.inverse)
        {
            super.motionY += 0.04D * (double)super.particleGravity;
        }
        else
        {
            super.motionY -= 0.04D * (double)super.particleGravity;
        }

        if (!super.noClip)
        {
            this.pushOutOfBlocks(super.posX, (super.boundingBox.minY + super.boundingBox.maxY) / 2.0D, super.posZ);
        }

        super.posX += super.motionX;
        super.posY += super.motionY;
        super.posZ += super.motionZ;

        if (this.slowdown)
        {
            super.motionX *= 0.9080000019073486D;
            super.motionY *= 0.9080000019073486D;
            super.motionZ *= 0.9080000019073486D;

            if (super.onGround)
            {
                super.motionX *= 0.699999988079071D;
                super.motionZ *= 0.699999988079071D;
            }
        }

        if (this.leyLineEffect)
        {
            FXSparkle var1 = new FXSparkle(super.worldObj, super.prevPosX + (double)((super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.1F), super.prevPosY + (double)((super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.1F), super.prevPosZ + (double)((super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.1F), 1.0F, this.currentColor, 3 + super.worldObj.rand.nextInt(3));
            var1.noClip = true;
            ModLoader.getMinecraftInstance().effectRenderer.addEffect(var1);
        }
    }

    public void setGravity(float var1)
    {
        super.particleGravity = var1;
    }

    protected boolean pushOutOfBlocks(double var1, double var3, double var5)
    {
        int var7 = MathHelper.floor_double(var1);
        int var8 = MathHelper.floor_double(var3);
        int var9 = MathHelper.floor_double(var5);
        double var10 = var1 - (double)var7;
        double var12 = var3 - (double)var8;
        double var14 = var5 - (double)var9;

        if (!super.worldObj.isAirBlock(var7, var8, var9))
        {
            boolean var16 = !super.worldObj.isBlockNormalCube(var7 - 1, var8, var9);
            boolean var17 = !super.worldObj.isBlockNormalCube(var7 + 1, var8, var9);
            boolean var18 = !super.worldObj.isBlockNormalCube(var7, var8 - 1, var9);
            boolean var19 = !super.worldObj.isBlockNormalCube(var7, var8 + 1, var9);
            boolean var20 = !super.worldObj.isBlockNormalCube(var7, var8, var9 - 1);
            boolean var21 = !super.worldObj.isBlockNormalCube(var7, var8, var9 + 1);
            byte var22 = -1;
            double var23 = 9999.0D;

            if (var16 && var10 < var23)
            {
                var23 = var10;
                var22 = 0;
            }

            if (var17 && 1.0D - var10 < var23)
            {
                var23 = 1.0D - var10;
                var22 = 1;
            }

            if (var18 && var12 < var23)
            {
                var23 = var12;
                var22 = 2;
            }

            if (var19 && 1.0D - var12 < var23)
            {
                var23 = 1.0D - var12;
                var22 = 3;
            }

            if (var20 && var14 < var23)
            {
                var23 = var14;
                var22 = 4;
            }

            if (var21 && 1.0D - var14 < var23)
            {
                var23 = 1.0D - var14;
                var22 = 5;
            }

            float var25 = super.rand.nextFloat() * 0.05F + 0.025F;
            float var26 = (super.rand.nextFloat() - super.rand.nextFloat()) * 0.1F;

            if (var22 == 0)
            {
                super.motionX = (double)(-var25);
                super.motionY = super.motionZ = (double)var26;
            }

            if (var22 == 1)
            {
                super.motionX = (double)var25;
                super.motionY = super.motionZ = (double)var26;
            }

            if (var22 == 2)
            {
                super.motionY = (double)(-var25);
                super.motionX = super.motionZ = (double)var26;
            }

            if (var22 == 3)
            {
                super.motionY = (double)var25;
                super.motionX = super.motionZ = (double)var26;
            }

            if (var22 == 4)
            {
                super.motionZ = (double)(-var25);
                super.motionY = super.motionX = (double)var26;
            }

            if (var22 == 5)
            {
                super.motionZ = (double)var25;
                super.motionY = super.motionX = (double)var26;
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}
