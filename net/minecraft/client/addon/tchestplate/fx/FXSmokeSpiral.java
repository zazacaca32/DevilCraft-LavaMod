package net.minecraft.client.addon.tchestplate.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXSmokeSpiral extends EntityFX
{
    private float radius = 1.0F;
    private int start = 0;
    private int miny = 0;

    public FXSmokeSpiral(World var1, double var2, double var4, double var6, float var8, int var9, int var10)
    {
        super(var1, var2, var4, var6, 0.0D, 0.0D, 0.0D);
        super.particleGravity = -0.01F;
        super.motionZ = 0.0D;
        super.motionY = 0.0D;
        super.motionX = 0.0D;
        super.particleScale *= 1.0F;
        super.particleMaxAge = 20 + var1.rand.nextInt(10);
        super.noClip = false;
        this.setSize(0.01F, 0.01F);
        super.prevPosX = super.posX;
        super.prevPosY = super.posY;
        super.prevPosZ = super.posZ;
        this.radius = var8;
        this.start = var9;
        this.miny = var10;
    }

    public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        var1.draw();
        GL11.glPushMatrix();
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/fx/particles.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F);
        int var8 = 5 + super.particleAge % 4;
        float var9 = (float)this.start + 720.0F * (((float)super.particleAge + var2) / (float)super.particleMaxAge);
        float var10 = 90.0F - 180.0F * (((float)super.particleAge + var2) / (float)super.particleMaxAge);
        float var11 = -MathHelper.sin(var9 / 180.0F * (float)Math.PI) * MathHelper.cos(var10 / 180.0F * (float)Math.PI);
        float var12 = MathHelper.cos(var9 / 180.0F * (float)Math.PI) * MathHelper.cos(var10 / 180.0F * (float)Math.PI);
        float var13 = -MathHelper.sin(var10 / 180.0F * (float)Math.PI);
        float var14 = (float)(var8 % 16) / 16.0F;
        float var15 = var14 + 0.0624375F;
        float var16 = (float)(var8 / 16) / 16.0F;
        float var17 = var16 + 0.0624375F;
        float var18 = 0.15F * super.particleScale;
        float var19 = (float)(super.posX + (double)(var11 * this.radius) - EntityFX.interpPosX);
        float var20 = (float)(Math.max(super.posY + (double)(var13 * this.radius), (double)((float)this.miny + 0.1F)) - EntityFX.interpPosY);
        float var21 = (float)(super.posZ + (double)(var12 * this.radius) - EntityFX.interpPosZ);
        float var22 = 1.0F;
        var1.startDrawingQuads();
        var1.setBrightness(this.getBrightnessForRender(var2));
        var1.setColorRGBA_F(super.particleRed * var22, super.particleGreen * var22, super.particleBlue * var22, 0.66F);
        var1.addVertexWithUV((double)(var19 - var3 * var18 - var6 * var18), (double)(var20 - var4 * var18), (double)(var21 - var5 * var18 - var7 * var18), (double)var15, (double)var17);
        var1.addVertexWithUV((double)(var19 - var3 * var18 + var6 * var18), (double)(var20 + var4 * var18), (double)(var21 - var5 * var18 + var7 * var18), (double)var15, (double)var16);
        var1.addVertexWithUV((double)(var19 + var3 * var18 + var6 * var18), (double)(var20 + var4 * var18), (double)(var21 + var5 * var18 + var7 * var18), (double)var14, (double)var16);
        var1.addVertexWithUV((double)(var19 + var3 * var18 - var6 * var18), (double)(var20 - var4 * var18), (double)(var21 + var5 * var18 - var7 * var18), (double)var14, (double)var17);
        var1.draw();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture("/particles.png");
        var1.startDrawingQuads();
    }

    public void onUpdate()
    {
        if (super.particleAge++ >= super.particleMaxAge)
        {
            this.setDead();
        }
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
