package net.minecraft.client.addon.tchestplate.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXBurst extends EntityFX
{
    public FXBurst(World var1, double var2, double var4, double var6, float var8)
    {
        super(var1, var2, var4, var6, 0.0D, 0.0D, 0.0D);
        super.particleRed = 1.0F;
        super.particleGreen = 1.0F;
        super.particleBlue = 1.0F;
        super.particleGravity = 0.0F;
        super.motionZ = 0.0D;
        super.motionY = 0.0D;
        super.motionX = 0.0D;
        super.particleScale *= var8;
        super.particleMaxAge = 31;
        super.noClip = false;
        this.setSize(0.01F, 0.01F);
    }

    public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        var1.draw();
        GL11.glPushMatrix();
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/fx/burst.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
        float var8 = (float)(super.particleAge % 32) / 32.0F;
        float var9 = var8 + 0.03125F;
        float var10 = 0.0F;
        float var11 = 1.0F;
        float var12 = super.particleScale;
        float var13 = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)var2 - EntityFX.interpPosX);
        float var14 = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)var2 - EntityFX.interpPosY);
        float var15 = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)var2 - EntityFX.interpPosZ);
        float var16 = 1.0F;
        var1.startDrawingQuads();
        var1.setBrightness(240);
        var1.setColorRGBA_F(super.particleRed * var16, super.particleGreen * var16, super.particleBlue * var16, 1.0F);
        var1.addVertexWithUV((double)(var13 - var3 * var12 - var6 * var12), (double)(var14 - var4 * var12), (double)(var15 - var5 * var12 - var7 * var12), (double)var9, (double)var11);
        var1.addVertexWithUV((double)(var13 - var3 * var12 + var6 * var12), (double)(var14 + var4 * var12), (double)(var15 - var5 * var12 + var7 * var12), (double)var9, (double)var10);
        var1.addVertexWithUV((double)(var13 + var3 * var12 + var6 * var12), (double)(var14 + var4 * var12), (double)(var15 + var5 * var12 + var7 * var12), (double)var8, (double)var10);
        var1.addVertexWithUV((double)(var13 + var3 * var12 - var6 * var12), (double)(var14 - var4 * var12), (double)(var15 + var5 * var12 - var7 * var12), (double)var8, (double)var11);
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

        if (super.particleAge++ >= super.particleMaxAge)
        {
            this.setDead();
        }
    }

    public void setGravity(float var1)
    {
        super.particleGravity = var1;
    }
}
