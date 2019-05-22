package net.minecraft.client.addon.tchestplate.fx;

import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXLightningBolt extends EntityFX
{
    private int type = 0;
    private float field_70130_N = 0.03F;
    public FXLightningBoltCommon main;

    public FXLightningBolt(World var1, WRVector3 var2, WRVector3 var3, long var4)
    {
        super(var1, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.main = new FXLightningBoltCommon(var1, var2, var3, var4);
        this.setupFromMain();
    }

    public FXLightningBolt(World var1, Entity var2, Entity var3, long var4)
    {
        super(var1, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.main = new FXLightningBoltCommon(var1, var2, var3, var4);
        this.setupFromMain();
    }

    public FXLightningBolt(World var1, Entity var2, Entity var3, long var4, int var6)
    {
        super(var1, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.main = new FXLightningBoltCommon(var1, var2, var3, var4, var6);
        this.setupFromMain();
    }

    public FXLightningBolt(World var1, TileEntity var2, Entity var3, long var4)
    {
        super(var1, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.main = new FXLightningBoltCommon(var1, var2, var3, var4);
        this.setupFromMain();
    }

    public FXLightningBolt(World var1, double var2, double var4, double var6, double var8, double var10, double var12, long var14, int var16, float var17)
    {
        super(var1, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.main = new FXLightningBoltCommon(var1, var2, var4, var6, var8, var10, var12, var14, var16, var17);
        this.setupFromMain();
    }

    public FXLightningBolt(World var1, double var2, double var4, double var6, double var8, double var10, double var12, long var14, int var16, float var17, int var18)
    {
        super(var1, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.main = new FXLightningBoltCommon(var1, var2, var4, var6, var8, var10, var12, var14, var16, var17, var18);
        this.setupFromMain();
    }

    public FXLightningBolt(World var1, double var2, double var4, double var6, double var8, double var10, double var12, long var14, int var16)
    {
        super(var1, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.main = new FXLightningBoltCommon(var1, var2, var4, var6, var8, var10, var12, var14, var16, 1.0F);
        this.setupFromMain();
    }

    public FXLightningBolt(World var1, TileEntity var2, double var3, double var5, double var7, long var9)
    {
        super(var1, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.main = new FXLightningBoltCommon(var1, var2, var3, var5, var7, var9);
        this.setupFromMain();
    }

    private void setupFromMain()
    {
        super.particleAge = this.main.particleMaxAge;
        this.setPosition((double)this.main.start.x, (double)this.main.start.y, (double)this.main.start.z);
        this.setVelocity(0.0D, 0.0D, 0.0D);
    }

    public void defaultFractal()
    {
        this.main.defaultFractal();
    }

    public void fractal(int var1, float var2, float var3, float var4, float var5)
    {
        this.main.fractal(var1, var2, var3, var4, var5);
    }

    public void finalizeBolt()
    {
        this.main.finalizeBolt();
        ModLoader.getMinecraftInstance().effectRenderer.addEffect(this);
    }

    public void setType(int var1)
    {
        this.type = var1;
        this.main.type = var1;
    }

    public void setMultiplier(float var1)
    {
        this.main.multiplier = var1;
    }

    public void setWidth(float var1)
    {
        this.field_70130_N = var1;
    }

    public void onUpdate()
    {
        this.main.onUpdate();

        if (this.main.particleAge >= this.main.particleMaxAge)
        {
            this.setDead();
        }
    }

    private static WRVector3 getRelativeViewVector(WRVector3 var0)
    {
        EntityClientPlayerMP var1 = ModLoader.getMinecraftInstance().thePlayer;
        return new WRVector3((double)((float)var1.posX - var0.x), (double)((float)var1.posY - var0.y), (double)((float)var1.posZ - var0.z));
    }

    private void renderBolt(Tessellator var1, float var2, float var3, float var4, float var5, float var6, int var7, float var8)
    {
        WRVector3 var9 = new WRVector3((double)(var5 * -var4), (double)(-var6 / var3), (double)(var3 * var4));
        float var10 = this.main.particleAge >= 0 ? (float)(this.main.particleAge / this.main.particleMaxAge) : 0.0F;

        if (var7 == 0)
        {
            var8 = (1.0F - var10) * 0.4F;
        }
        else
        {
            var8 = 1.0F - var10 * 0.5F;
        }

        int var11 = (int)(((float)this.main.particleAge + var2 + (float)((int)(this.main.length * 3.0F))) / (float)((int)(this.main.length * 3.0F)) * (float)this.main.numsegments0);
        Iterator var12 = this.main.segments.iterator();

        while (var12.hasNext())
        {
            FXLightningBoltCommon.Segment var13 = (FXLightningBoltCommon.Segment)var12.next();

            if (var13.segmentno <= var11)
            {
                float var14 = this.field_70130_N * (getRelativeViewVector(var13.startpoint.point).length() / 5.0F + 1.0F) * (1.0F + var13.light) * 0.5F;
                WRVector3 var15 = WRVector3.crossProduct(var9, var13.prevdiff).scale(var14 / var13.sinprev);
                WRVector3 var16 = WRVector3.crossProduct(var9, var13.nextdiff).scale(var14 / var13.sinnext);
                WRVector3 var17 = var13.startpoint.point;
                WRVector3 var18 = var13.endpoint.point;
                float var19 = (float)((double)var17.x - EntityFX.interpPosX);
                float var20 = (float)((double)var17.y - EntityFX.interpPosY);
                float var21 = (float)((double)var17.z - EntityFX.interpPosZ);
                float var22 = (float)((double)var18.x - EntityFX.interpPosX);
                float var23 = (float)((double)var18.y - EntityFX.interpPosY);
                float var24 = (float)((double)var18.z - EntityFX.interpPosZ);
                var1.setColorRGBA_F(super.particleRed, super.particleGreen, super.particleBlue, var8 * var13.light);
                var1.addVertexWithUV((double)(var22 - var16.x), (double)(var23 - var16.y), (double)(var24 - var16.z), 0.5D, 0.0D);
                var1.addVertexWithUV((double)(var19 - var15.x), (double)(var20 - var15.y), (double)(var21 - var15.z), 0.5D, 0.0D);
                var1.addVertexWithUV((double)(var19 + var15.x), (double)(var20 + var15.y), (double)(var21 + var15.z), 0.5D, 1.0D);
                var1.addVertexWithUV((double)(var22 + var16.x), (double)(var23 + var16.y), (double)(var24 + var16.z), 0.5D, 1.0D);
                WRVector3 var25;
                float var27;
                float var26;
                float var28;

                if (var13.next == null)
                {
                    var25 = var13.endpoint.point.copy().add(var13.diff.copy().normalize().scale(var14));
                    var26 = (float)((double)var25.x - EntityFX.interpPosX);
                    var27 = (float)((double)var25.y - EntityFX.interpPosY);
                    var28 = (float)((double)var25.z - EntityFX.interpPosZ);
                    var1.addVertexWithUV((double)(var26 - var16.x), (double)(var27 - var16.y), (double)(var28 - var16.z), 0.0D, 0.0D);
                    var1.addVertexWithUV((double)(var22 - var16.x), (double)(var23 - var16.y), (double)(var24 - var16.z), 0.5D, 0.0D);
                    var1.addVertexWithUV((double)(var22 + var16.x), (double)(var23 + var16.y), (double)(var24 + var16.z), 0.5D, 1.0D);
                    var1.addVertexWithUV((double)(var26 + var16.x), (double)(var27 + var16.y), (double)(var28 + var16.z), 0.0D, 1.0D);
                }

                if (var13.prev == null)
                {
                    var25 = var13.startpoint.point.copy().sub(var13.diff.copy().normalize().scale(var14));
                    var26 = (float)((double)var25.x - EntityFX.interpPosX);
                    var27 = (float)((double)var25.y - EntityFX.interpPosY);
                    var28 = (float)((double)var25.z - EntityFX.interpPosZ);
                    var1.addVertexWithUV((double)(var19 - var15.x), (double)(var20 - var15.y), (double)(var21 - var15.z), 0.5D, 0.0D);
                    var1.addVertexWithUV((double)(var26 - var15.x), (double)(var27 - var15.y), (double)(var28 - var15.z), 0.0D, 0.0D);
                    var1.addVertexWithUV((double)(var26 + var15.x), (double)(var27 + var15.y), (double)(var28 + var15.z), 0.0D, 1.0D);
                    var1.addVertexWithUV((double)(var19 + var15.x), (double)(var20 + var15.y), (double)(var21 + var15.z), 0.5D, 1.0D);
                }
            }
        }
    }

    public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        EntityClientPlayerMP var8 = ModLoader.getMinecraftInstance().thePlayer;
        byte var9 = 100;

        if (!ModLoader.getMinecraftInstance().gameSettings.fancyGraphics)
        {
            var9 = 50;
        }

        if (var8.getDistance(super.posX, super.posY, super.posZ) <= (double)var9)
        {
            var1.draw();
            GL11.glPushMatrix();
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_BLEND);
            super.particleBlue = 1.0F;
            super.particleGreen = 1.0F;
            super.particleRed = 1.0F;
            float var10 = 1.0F;

            switch (this.type)
            {
                case 0:
                    super.particleRed = 0.6F;
                    super.particleGreen = 0.3F;
                    super.particleBlue = 0.6F;
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                    break;

                case 1:
                    super.particleRed = 0.6F;
                    super.particleGreen = 0.6F;
                    super.particleBlue = 0.1F;
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                    break;

                case 2:
                    super.particleRed = 0.1F;
                    super.particleGreen = 0.1F;
                    super.particleBlue = 0.6F;
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                    break;

                case 3:
                    super.particleRed = 0.1F;
                    super.particleGreen = 1.0F;
                    super.particleBlue = 0.1F;
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                    break;

                case 4:
                    super.particleRed = 0.6F;
                    super.particleGreen = 0.1F;
                    super.particleBlue = 0.1F;
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                    break;

                case 5:
                    super.particleRed = 0.6F;
                    super.particleGreen = 0.2F;
                    super.particleBlue = 0.6F;
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    break;

                case 6:
                    super.particleRed = 0.75F;
                    super.particleGreen = 1.0F;
                    super.particleBlue = 1.0F;
                    var10 = 0.2F;
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            }

            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/p_large.png");
            var1.startDrawingQuads();
            var1.setBrightness(15728880);
            this.renderBolt(var1, var2, var3, var4, var5, var7, 0, var10);
            var1.draw();

            switch (this.type)
            {
                case 0:
                    super.particleRed = 1.0F;
                    super.particleGreen = 0.6F;
                    super.particleBlue = 1.0F;
                    break;

                case 1:
                    super.particleRed = 1.0F;
                    super.particleGreen = 1.0F;
                    super.particleBlue = 0.1F;
                    break;

                case 2:
                    super.particleRed = 0.1F;
                    super.particleGreen = 0.1F;
                    super.particleBlue = 1.0F;
                    break;

                case 3:
                    super.particleRed = 0.1F;
                    super.particleGreen = 0.6F;
                    super.particleBlue = 0.1F;
                    break;

                case 4:
                    super.particleRed = 1.0F;
                    super.particleGreen = 0.1F;
                    super.particleBlue = 0.1F;
                    break;

                case 5:
                    super.particleRed = 0.0F;
                    super.particleGreen = 0.0F;
                    super.particleBlue = 0.0F;
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    break;

                case 6:
                    super.particleRed = 0.75F;
                    super.particleGreen = 1.0F;
                    super.particleBlue = 1.0F;
                    var10 = 0.2F;
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            }

            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/p_small.png");
            var1.startDrawingQuads();
            var1.setBrightness(15728880);
            this.renderBolt(var1, var2, var3, var4, var5, var7, 1, var10);
            var1.draw();
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDepthMask(true);
            GL11.glPopMatrix();
            Minecraft.getMinecraft().renderEngine.bindTexture("/particles.png");
            var1.startDrawingQuads();
        }
    }

    public int getRenderPass()
    {
        return 2;
    }
}
